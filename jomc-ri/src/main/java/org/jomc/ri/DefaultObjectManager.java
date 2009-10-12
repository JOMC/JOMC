// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@jomc.org>
 *   All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions
 *   are met:
 *
 *     o Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     o Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in
 *       the documentation and/or other materials provided with the
 *       distribution.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE JOMC PROJECT AND CONTRIBUTORS "AS IS"
 *   AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *   THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *   PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE JOMC PROJECT OR
 *   CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *   EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *   PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 *   OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *   WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *   OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *   ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $Id$
 *
 */
// SECTION-END
package org.jomc.ri;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import org.jomc.ObjectManagementException;
import org.jomc.ObjectManager;
import org.jomc.ObjectManagerFactory;
import org.jomc.model.DefaultModelManager;
import org.jomc.model.Dependency;
import org.jomc.model.Implementation;
import org.jomc.model.ImplementationReference;
import org.jomc.model.Implementations;
import org.jomc.model.Instance;
import org.jomc.model.Message;
import org.jomc.model.ModelException;
import org.jomc.model.ModelManager;
import org.jomc.model.Module;
import org.jomc.model.Modules;
import org.jomc.model.Multiplicity;
import org.jomc.model.Property;
import org.jomc.model.Specification;
import org.jomc.model.SpecificationReference;
import org.jomc.spi.Invoker;
import org.jomc.spi.Listener;
import org.jomc.spi.Locator;
import org.jomc.spi.Scope;
import org.xml.sax.SAXException;

// SECTION-START[Documentation]
/**
 * Object management and configuration reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.ObjectManager} {@code 1.0} {@code Singleton}</li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-7-SNAPSHOT/jomc-tools" )
// SECTION-END
public class DefaultObjectManager implements ObjectManager
{
    // SECTION-START[Constructors]

    /** Creates a new {@code DefaultObjectManager} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-7-SNAPSHOT/jomc-tools" )
    public DefaultObjectManager()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[ObjectManager]

    public Object getObject( final Class specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        Object object = null;

        try
        {
            this.initialize();

            final Specification s = this.getModules().getSpecification( specification );

            if ( s != null )
            {
                final Implementations available = this.getModules().getImplementations( s.getIdentifier() );

                if ( available != null && !available.getImplementation().isEmpty() )
                {
                    if ( s.getMultiplicity() == Multiplicity.ONE )
                    {
                        final Implementation i = available.getImplementation().get( 0 );

                        if ( i.getLocation() != null )
                        {
                            object = this.getObject( s, i.getLocationUri(), this.getClassLoader( specification ) );
                            if ( object == null && this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingObjectMessage(
                                    i.getIdentifier(), i.getName() ), new Exception() );

                            }
                        }
                        else if ( !i.isAbstract() )
                        {
                            final Instance instance = this.getModelManager().getInstance(
                                this.getModules(), i, this.getClassLoader( specification ) );

                            if ( instance != null )
                            {
                                object = this.getObject( s, instance );
                                if ( object == null && this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        i.getIdentifier(), i.getName() ), new Exception() );

                                }
                            }
                            else if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                    i.getIdentifier(), i.getName() ), new Exception() );

                            }
                        }
                    }
                    else if ( s.getMultiplicity() == Multiplicity.MANY )
                    {
                        final List<Object> list = new ArrayList<Object>( available.getImplementation().size() );

                        for ( Implementation i : available.getImplementation() )
                        {
                            if ( i.getLocation() != null )
                            {
                                final Object o =
                                    this.getObject( s, i.getLocationUri(), this.getClassLoader( specification ) );

                                if ( o != null )
                                {
                                    list.add( o );
                                }
                                else if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        i.getIdentifier(), i.getName() ), new Exception() );

                                }
                            }
                            else if ( !i.isAbstract() )
                            {
                                final Instance instance = this.getModelManager().getInstance(
                                    this.getModules(), i, this.getClassLoader( specification ) );

                                if ( instance != null )
                                {
                                    final Object o = this.getObject( s, instance );
                                    if ( o != null )
                                    {
                                        list.add( o );
                                    }
                                    else if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( Level.WARNING, this.getMissingObjectMessage(
                                            i.getIdentifier(), i.getName() ), new Exception() );

                                    }
                                }
                                else if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                        i.getIdentifier(), i.getName() ), new Exception() );

                                }
                            }
                        }

                        object = list.isEmpty() ? null
                                 : list.toArray( (Object[]) Array.newInstance( specification, list.size() ) );

                    }
                    else if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getUnsupportedMultiplicityMessage( s.getMultiplicity() ),
                                  new Exception() );

                    }
                }
                else if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingImplementationsMessage( specification.getName() ),
                              new Exception() );

                }
            }
            else if ( this.isLoggable( Level.WARNING ) )
            {
                this.log( Level.WARNING, this.getMissingSpecificationMessage( specification.getName() ),
                          new Exception() );

            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return object;
    }

    public Object getObject( final Class specification, final String implementationName )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }
        if ( implementationName == null )
        {
            throw new NullPointerException( "implementationName" );
        }

        Object object = null;

        try
        {
            this.initialize();

            final Specification s = this.getModules().getSpecification( specification );

            if ( s != null )
            {
                final Implementations available = this.getModules().getImplementations( s.getIdentifier() );
                if ( available != null && !available.getImplementation().isEmpty() )
                {
                    final Implementation i = available.getImplementationByName( implementationName );

                    if ( i != null )
                    {
                        if ( i.getLocation() != null )
                        {
                            object = this.getObject( s, i.getLocationUri(), this.getClassLoader( specification ) );
                            if ( object == null && this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingObjectMessage(
                                    i.getIdentifier(), i.getName() ), new Exception() );

                            }
                        }
                        else if ( !i.isAbstract() )
                        {
                            final Instance instance = this.getModelManager().getInstance(
                                this.getModules(), i, this.getClassLoader( specification ) );

                            if ( instance != null )
                            {
                                object = this.getObject( s, instance );
                                if ( object == null && this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        i.getIdentifier(), i.getName() ), new Exception() );

                                }
                            }
                            else if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                    i.getIdentifier(), i.getName() ), new Exception() );

                            }
                        }
                    }
                    else if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingImplementationMessage(
                            implementationName, s.getIdentifier() ), new Exception() );

                    }
                }
                else if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingImplementationsMessage(
                        specification.getName() ), new Exception() );

                }
            }
            else if ( this.isLoggable( Level.WARNING ) )
            {
                this.log( Level.WARNING, this.getMissingSpecificationMessage( specification.getName() ),
                          new Exception() );

            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return object;
    }

    public Object getDependency( final Object object, final String dependencyName )
    {
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }
        if ( dependencyName == null )
        {
            throw new NullPointerException( "dependencyName" );
        }

        Object o = null;

        try
        {
            this.initialize();
            final Instance instance = this.getModelManager().getInstance( this.getModules(), object );

            if ( instance != null )
            {
                synchronized ( instance )
                {
                    o = instance.getDependencyObjects().get( dependencyName );

                    if ( o == null )
                    {
                        final Dependency dependency = instance.getDependencies() != null
                                                      ? instance.getDependencies().getDependency( dependencyName )
                                                      : null;

                        if ( dependency != null )
                        {
                            final Specification ds = this.getModules().getSpecification( dependency.getIdentifier() );

                            if ( ds != null )
                            {
                                final Implementations available =
                                    this.getModules().getImplementations( ds.getIdentifier() );

                                if ( available != null && !available.getImplementation().isEmpty() )
                                {
                                    if ( dependency.getImplementationName() != null )
                                    {
                                        final Implementation i =
                                            available.getImplementationByName( dependency.getImplementationName() );

                                        if ( i != null )
                                        {
                                            if ( i.getLocation() != null )
                                            {
                                                o = this.getObject(
                                                    ds, i.getLocationUri(), this.getClassLoader( object.getClass() ) );

                                                if ( o == null && this.isLoggable( Level.WARNING ) )
                                                {
                                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                                        i.getIdentifier(), i.getName() ), new Exception() );

                                                }
                                            }
                                            else if ( !i.isAbstract() )
                                            {
                                                final Instance di = this.getModelManager().getInstance(
                                                    this.getModules(), i, dependency,
                                                    this.getClassLoader( object.getClass() ) );

                                                if ( di != null )
                                                {
                                                    o = this.getObject( ds, di );
                                                    if ( o == null && this.isLoggable( Level.WARNING ) )
                                                    {
                                                        this.log( Level.WARNING, this.getMissingObjectMessage(
                                                            i.getIdentifier(), i.getName() ), new Exception() );

                                                    }
                                                }
                                                else if ( this.isLoggable( Level.WARNING ) )
                                                {
                                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                                        i.getIdentifier(), i.getName() ), new Exception() );

                                                }
                                            }
                                        }
                                        else if ( !dependency.isOptional() && this.isLoggable( Level.WARNING ) )
                                        {
                                            this.log( Level.WARNING, this.getMissingImplementationMessage(
                                                dependency.getImplementationName(), dependency.getIdentifier() ),
                                                      new Exception() );

                                        }
                                    }
                                    else if ( ds.getMultiplicity() == Multiplicity.ONE )
                                    {
                                        final Implementation ref = available.getImplementation().get( 0 );
                                        if ( ref.getLocation() != null )
                                        {
                                            o = this.getObject(
                                                ds, ref.getLocationUri(), this.getClassLoader( object.getClass() ) );

                                            if ( o == null && this.isLoggable( Level.WARNING ) )
                                            {
                                                this.log( Level.WARNING, this.getMissingObjectMessage(
                                                    ref.getIdentifier(), ref.getName() ), new Exception() );

                                            }
                                        }
                                        else if ( !ref.isAbstract() )
                                        {
                                            final Instance di = this.getModelManager().getInstance(
                                                this.getModules(), ref, dependency,
                                                this.getClassLoader( object.getClass() ) );

                                            if ( di != null )
                                            {
                                                o = this.getObject( ds, di );
                                                if ( o == null && this.isLoggable( Level.WARNING ) )
                                                {
                                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                                        ref.getIdentifier(), ref.getName() ), new Exception() );

                                                }
                                            }
                                            else if ( this.isLoggable( Level.WARNING ) )
                                            {
                                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                                    ref.getIdentifier(), ref.getName() ), new Exception() );

                                            }
                                        }
                                    }
                                    else
                                    {
                                        final List<Object> list =
                                            new ArrayList<Object>( available.getImplementation().size() );

                                        for ( Implementation a : available.getImplementation() )
                                        {
                                            if ( a.getLocation() != null )
                                            {
                                                final Object o2 = this.getObject(
                                                    ds, a.getLocationUri(), this.getClassLoader( object.getClass() ) );

                                                if ( o2 != null )
                                                {
                                                    list.add( o2 );
                                                }
                                                else if ( this.isLoggable( Level.WARNING ) )
                                                {
                                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                                        a.getIdentifier(), a.getName() ), new Exception() );

                                                }
                                            }
                                            else if ( !a.isAbstract() )
                                            {
                                                final Instance di = this.getModelManager().getInstance(
                                                    this.getModules(), a, dependency,
                                                    this.getClassLoader( object.getClass() ) );

                                                if ( di != null )
                                                {
                                                    final Object o2 = this.getObject( ds, di );

                                                    if ( o2 != null )
                                                    {
                                                        list.add( o2 );
                                                    }
                                                    else if ( this.isLoggable( Level.WARNING ) )
                                                    {
                                                        this.log( Level.WARNING, this.getMissingObjectMessage(
                                                            a.getIdentifier(), a.getName() ), new Exception() );

                                                    }
                                                }
                                                else if ( this.isLoggable( Level.WARNING ) )
                                                {
                                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                                        a.getIdentifier(), a.getName() ), new Exception() );

                                                }
                                            }
                                        }

                                        final Class specClass = Class.forName(
                                            ds.getClazz(), true, this.getClassLoader( object.getClass() ) );

                                        o = list.isEmpty() ? null : list.toArray( (Object[]) Array.newInstance(
                                            specClass, list.size() ) );

                                    }

                                    if ( o != null && dependency.isBound() )
                                    {
                                        instance.getDependencyObjects().put( dependencyName, o );
                                    }
                                }
                                else if ( !dependency.isOptional() && this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingImplementationsMessage(
                                        dependency.getIdentifier() ), new Exception() );

                                }
                            }
                            else if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingSpecificationMessage(
                                    dependency.getIdentifier() ), new Exception() );

                            }
                        }
                        else if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingDependencyMessage(
                                dependencyName, instance.getIdentifier() ), new Exception() );

                        }
                    }
                }
            }
            else if ( this.isLoggable( Level.WARNING ) )
            {
                this.log( Level.WARNING, this.getMissingObjectInstanceMessage( object ), new Exception() );
            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return o;
    }

    public Object getProperty( final Object object, final String propertyName )
    {
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }
        if ( propertyName == null )
        {
            throw new NullPointerException( "propertyName" );
        }

        Object value = null;

        try
        {
            this.initialize();

            final Instance instance = this.getModelManager().getInstance( this.getModules(), object );

            if ( instance != null )
            {
                synchronized ( instance )
                {
                    value = instance.getPropertyObjects().get( propertyName );

                    if ( value == null )
                    {
                        final Property property = instance.getProperties() != null
                                                  ? instance.getProperties().getProperty( propertyName )
                                                  : null;

                        if ( property != null )
                        {
                            value = property.getJavaValue( this.getClassLoader( object.getClass() ) );

                            if ( value != null )
                            {
                                instance.getPropertyObjects().put( propertyName, value );
                            }
                        }
                        else if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingPropertyMessage(
                                propertyName, object.getClass().getName() ), new Exception() );

                        }
                    }
                }
            }
            else if ( this.isLoggable( Level.WARNING ) )
            {
                this.log( Level.WARNING, this.getMissingObjectInstanceMessage( object ), new Exception() );
            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return value;
    }

    public String getMessage( final Object object, final String messageName, final Locale locale,
                              final Object arguments )
    {
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }
        if ( messageName == null )
        {
            throw new NullPointerException( "messageName" );
        }
        if ( locale == null )
        {
            throw new NullPointerException( "locale" );
        }

        String text = null;

        try
        {
            this.initialize();

            final Instance instance = this.getModelManager().getInstance( this.getModules(), object );

            if ( instance != null )
            {
                synchronized ( instance )
                {
                    final Message message = instance.getMessages() != null
                                            ? instance.getMessages().getMessage( messageName )
                                            : null;

                    if ( message != null && message.getTemplate() != null )
                    {
                        final MessageFormat fmt = new MessageFormat( message.getTemplate().
                            getText( locale.getLanguage().toLowerCase( Locale.ENGLISH ) ).getValue(), locale );

                        text = fmt.format( arguments );
                    }
                    else if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingMessageMessage(
                            messageName, object.getClass().getName() ), new Exception() );

                    }
                }
            }
            else if ( this.isLoggable( Level.WARNING ) )
            {
                this.log( Level.WARNING, this.getMissingObjectInstanceMessage( object ), new Exception() );
            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return text;
    }

    // SECTION-END
    // SECTION-START[DefaultObjectManager]
    /** Constant for the {@code Singleton} scope identifier. */
    protected static final String SINGLETON_SCOPE_IDENTIFIER = "Singleton";

    /** Empty {@code URL} array. */
    private static final URL[] NO_URLS =
    {
    };

    /** Singleton instance. */
    private static final ObjectManager singleton = ObjectManagerFactory.newObjectManager();

    /** The modules of the instance. */
    private Modules modules;

    /** The model manager of the instance. */
    private ModelManager modelManager;

    /** Scopes of the instance. */
    private final Map<String, Scope> scopes = new HashMap<String, Scope>();

    /** Locators of the instance. */
    private final Map<String, Locator> locators = new HashMap<String, Locator>();

    /** Invoker of the instance. */
    private Invoker invoker;

    /** Listeners of the instance. */
    private List<Listener> listeners;

    /** Flag indicating that initialization has been performed. */
    private boolean initialized;

    /** Flag indicating classpath awareness. */
    private Boolean classpathAware;

    /**
     * Bootstrap {@code LogRecord}s.
     * @see #initialize()
     */
    private final List<LogRecord> bootstrapLogRecords = new LinkedList<LogRecord>();

    /** Log level of the instance. */
    private Level logLevel;

    /** {@code DefaultModelManager.Listener} of the instance. */
    private final DefaultModelManager.Listener defaultModelManagerListener = new DefaultModelManager.Listener()
    {

        public void onLog( final Level level, final String message, final Throwable t )
        {
            log( level, message, t );
        }

    };

    /** Bootstrap {@code ObjectManagementListener}. */
    private final Listener bootstrapObjectManagementListener = new Listener()
    {

        public void onLog( final Level level, final String message, final Throwable throwable )
        {
            final LogRecord record = new LogRecord( level, message );
            record.setThrown( throwable );
            bootstrapLogRecords.add( record );
        }

    };

    /**
     * Default {@link ObjectManagerFactory#getObjectManager()} implementation backed by static field.
     *
     * @return The default {@code ObjectManager} singleton instance.
     *
     * @see ObjectManagerFactory#getObjectManager()
     */
    public static ObjectManager getObjectManager()
    {
        return (ObjectManager) singleton.getObject( ObjectManager.class );
    }

    /**
     * Gets a flag indicating that classpath resolution is performed.
     * <p>Classpath resolution is performed by default. It can be disabled by setting the system property
     * {@code org.jomc.ri.DefaultObjectManager.classpathAware} to {@code false}.</p>
     *
     * @return {@code true} if the class loader of the instance is searched for resources; {@code false} if no
     * classpath resolution is performed.
     *
     * @see #setClasspathAware(boolean)
     */
    public boolean isClasspathAware()
    {
        if ( this.classpathAware == null )
        {
            this.classpathAware = Boolean.valueOf(
                System.getProperty( "org.jomc.ri.DefaultObjectManager.classpathAware", Boolean.TRUE.toString() ) );

        }

        return this.classpathAware;
    }

    /**
     * Sets the flag indicating that classpath resolution should be performed.
     *
     * @param value {@code true} if the class loader of the instance is searched for resources; {@code false} if no
     * classpath resolution is performed.
     *
     * @see #isClasspathAware()
     */
    public synchronized void setClasspathAware( final boolean value )
    {
        this.classpathAware = value;
        this.initialized = false;
    }

    /**
     * Gets the list of registered listeners.
     *
     * @return The list of registered listeners.
     */
    public List<Listener> getListeners()
    {
        if ( this.listeners == null )
        {
            this.listeners = new LinkedList<Listener>();
        }

        return this.listeners;
    }

    /**
     * Gets the log level of the instance.
     * <p>The default log level of the instance is controlled by system property
     * {@code org.jomc.ri.DefaultObjectManager.logLevel} holding the default log level of the instance.
     * If that system property is not set, a {@code WARNING} log level is returned.</p>
     *
     * @return The log level of the instance.
     *
     * @see #setLogLevel(java.util.logging.Level)
     * @see #isLoggable(java.util.logging.Level)
     * @see Level#parse(java.lang.String)
     */
    public Level getLogLevel()
    {
        if ( this.logLevel == null )
        {
            this.logLevel = Level.parse( System.getProperty( "org.jomc.ri.DefaultObjectManager.logLevel",
                                                             Level.WARNING.getName() ) );

        }

        return this.logLevel;
    }

    /**
     * Sets the log level of the instance.
     *
     * @param value The new log level of the instance or {@code null}.
     *
     * @see #getLogLevel()
     * @see #isLoggable(java.util.logging.Level)
     */
    public void setLogLevel( final Level value )
    {
        this.logLevel = value;
    }

    /**
     * Checks if a message at a given level is provided to the listeners of the instance.
     *
     * @param level The level to test.
     *
     * @return {@code true} if messages at {@code level} are provided to the listeners of the instance;
     * {@code false} if messages at {@code level} are not provided to the listeners of the instance.
     *
     * @throws NullPointerException if {@code level} is {@code null}.
     *
     * @see #getLogLevel()
     * @see #setLogLevel(java.util.logging.Level)
     * @see #log(java.util.logging.Level, java.lang.String, java.lang.Throwable)
     */
    public boolean isLoggable( final Level level )
    {
        if ( level == null )
        {
            throw new NullPointerException( "level" );
        }

        return level.intValue() >= this.getLogLevel().intValue();
    }

    /**
     * Gets the modules of the instance.
     *
     * @return The modules of the instance.
     *
     * @see #setModules(org.jomc.model.Modules)
     */
    public Modules getModules()
    {
        if ( this.modules == null )
        {
            try
            {
                if ( this.isClasspathAware() && this.getModelManager() instanceof DefaultModelManager )
                {
                    final DefaultModelManager defaultModelManager = (DefaultModelManager) this.getModelManager();

                    Modules defaultModules = defaultModelManager.getClasspathModules(
                        defaultModelManager.getDefaultDocumentLocation() );

                    final Module classpathModule = defaultModelManager.getClasspathModule( defaultModules );
                    if ( classpathModule != null )
                    {
                        defaultModules.getModule().add( classpathModule );
                    }

                    final List<Transformer> defaultTransformers = defaultModelManager.getClasspathTransformers(
                        defaultModelManager.getDefaultStylesheetLocation() );

                    for ( Transformer t : defaultTransformers )
                    {
                        defaultModules = defaultModelManager.transformModelObject(
                            defaultModelManager.getObjectFactory().createModules( defaultModules ), t );

                    }

                    this.getModelManager().validateModules( defaultModules );
                    this.modules = defaultModules;
                }
            }
            catch ( final ModelException e )
            {
                if ( this.isLoggable( Level.SEVERE ) )
                {
                    this.log( Level.SEVERE, e.getMessage(), e );
                }

                for ( ModelException.Detail d : e.getDetails() )
                {
                    if ( this.isLoggable( d.getLevel() ) )
                    {
                        this.log( d.getLevel(), d.getMessage(), null );
                    }
                }
            }
            catch ( final TransformerException e )
            {
                if ( this.isLoggable( Level.SEVERE ) )
                {
                    this.log( Level.SEVERE, e.getMessage(), e );
                }
            }
            catch ( final IOException e )
            {
                if ( this.isLoggable( Level.SEVERE ) )
                {
                    this.log( Level.SEVERE, e.getMessage(), e );
                }
            }
            catch ( final SAXException e )
            {
                if ( this.isLoggable( Level.SEVERE ) )
                {
                    this.log( Level.SEVERE, e.getMessage(), e );
                }
            }
            catch ( final JAXBException e )
            {
                if ( this.isLoggable( Level.SEVERE ) )
                {
                    this.log( Level.SEVERE, e.getMessage(), e );
                }
            }
            finally
            {
                if ( this.modules == null )
                {
                    this.modules = new Modules();
                }
            }
        }

        return this.modules;
    }

    /**
     * Sets the modules of the instance.
     *
     * @param value The new modules of the instance.
     *
     * @see #getModules()
     */
    public synchronized void setModules( final Modules value )
    {
        this.modules = value;
        this.initialized = false;
    }

    /**
     * Gets the model manager of the instance.
     *
     * @return The model manager of the instance.
     *
     * @see #setModelManager(org.jomc.model.ModelManager)
     */
    public ModelManager getModelManager()
    {
        if ( this.modelManager == null )
        {
            final DefaultModelManager defaultManager = new DefaultModelManager();
            defaultManager.setClassLoader( this.getClassLoader( this.getClass() ) );
            defaultManager.getListeners().add( this.defaultModelManagerListener );
            this.modelManager = defaultManager;
        }

        return this.modelManager;
    }

    /**
     * Sets the model manager of the instance.
     *
     * @param value The new model manager of the instance.
     *
     * @see #getModelManager()
     */
    public synchronized void setModelManager( final ModelManager value )
    {
        this.modelManager = value;
        this.initialized = false;
    }

    /**
     * Gets the class loader of a given class.
     *
     * @param clazz The class whose class loader to return.
     *
     * @return The class loader of {@code clazz}.
     *
     * @throws NullPointerException if {@code clazz} is {@code null}.
     */
    public ClassLoader getClassLoader( final Class clazz )
    {
        if ( clazz == null )
        {
            throw new NullPointerException( "clazz" );
        }

        ClassLoader cl = clazz.getClassLoader();
        if ( cl == null )
        {
            cl = ClassLoader.getSystemClassLoader();
        }

        if ( cl == null )
        {
            if ( this.isLoggable( Level.WARNING ) )
            {
                this.log( Level.WARNING, this.getMissingClassLoaderMessage(), new Exception() );
            }

            cl = new URLClassLoader( NO_URLS );
        }

        return cl;
    }

    /**
     * Gets an object of a given instance.
     *
     * @param specification The specification specifying the object to return.
     * @param instance The instance of the object to get.
     *
     * @return An object of {@code instance} or {@code null} if nothing could be resolved.
     *
     * @throws NullPointerException if {@code specification} or {@code instance} is {@code null}.
     * @throws InstantiationException if getting an object fails.
     */
    public Object getObject( final Specification specification, final Instance instance ) throws InstantiationException
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }
        if ( instance == null )
        {
            throw new NullPointerException( "instance" );
        }

        Object object = null;
        if ( specification.getScope() != null )
        {
            final Scope scope = this.getScope( specification.getScope() );

            if ( scope != null )
            {
                synchronized ( scope )
                {
                    object = scope.getObject( instance.getIdentifier() );

                    if ( object == null )
                    {
                        scope.putObject( instance.getIdentifier(), instance );

                        try
                        {
                            object = this.getModelManager().getObject( this.getModules(), specification, instance );
                        }
                        finally
                        {
                            if ( object != null )
                            {
                                object = this.createProxy( specification, instance, object );
                            }

                            scope.putObject( instance.getIdentifier(), object );
                        }
                    }
                    else if ( object instanceof Instance )
                    {
                        throw new ObjectManagementException( this.getDependencyCycleMessage(
                            ( (Instance) object ).getIdentifier() ) );

                    }
                }
            }
            else if ( this.isLoggable( Level.WARNING ) )
            {
                this.log( Level.WARNING, this.getMissingScopeMessage( specification.getScope() ), new Exception() );
            }
        }
        else
        {
            try
            {
                object = this.getModelManager().getObject( this.getModules(), specification, instance );
            }
            finally
            {
                if ( object != null )
                {
                    object = this.createProxy( specification, instance, object );
                }
            }
        }

        return object;
    }

    /**
     * Gets an object for a given location URI.
     *
     * @param specification The specification specifying the object to return.
     * @param location The location URI of the object to return.
     * @param classLoader The class loader of {@code specification}.
     *
     * @return An object located at {@code location} or {@code null} if nothing could be resolved.
     *
     * @throws NullPointerException if {@code specification}, {@code location} or {@code classLoader} is {@code null}.
     * @throws InstantiationException if instantiating a locator fails.
     * @throws ClassNotFoundException if the class of {@code specification} is not found.
     * @throws IOException if locating the object fails.
     */
    public Object getObject( final Specification specification, final URI location, final ClassLoader classLoader )
        throws InstantiationException, ClassNotFoundException, IOException
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        Object object = null;
        final Locator locator = this.getLocator( location );

        if ( locator != null )
        {
            object = locator.getObject( Class.forName( specification.getClazz(), true, classLoader ), location );
        }
        else if ( this.isLoggable( Level.WARNING ) )
        {
            this.log( Level.WARNING, this.getMissingLocatorMessage( location ), new Exception() );
        }

        return object;
    }

    /**
     * Gets the scope implementation for a given model scope.
     *
     * @param modelScope The scope to get an implementation of.
     *
     * @return The implementation of {@code modelScope} or {@code null} if no implementation is available implementing
     * {@code modelScope}.
     *
     * @throws NullPointerException if {@code modelScope} is {@code null}.
     * @throws InstantiationException if instantiating a scope fails.
     *
     * @see #getDefaultScope(java.lang.String)
     */
    public Scope getScope( final String modelScope ) throws InstantiationException
    {
        if ( modelScope == null )
        {
            throw new NullPointerException( "modelScope" );
        }

        synchronized ( this.scopes )
        {
            Scope scope = this.scopes.get( modelScope );

            if ( scope == null )
            {
                // Bootstrap scope loading.
                final Specification scopeSpecification = this.getModules().getSpecification( Scope.class );

                if ( scopeSpecification != null )
                {
                    final Implementations implementations =
                        this.getModules().getImplementations( scopeSpecification.getIdentifier() );

                    if ( implementations != null )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            if ( modelScope.equals( i.getName() ) )
                            {
                                final Instance instance = this.getModelManager().getInstance(
                                    this.getModules(), i, this.getClassLoader( Scope.class ) );

                                if ( instance != null )
                                {
                                    scope = (Scope) this.getModelManager().getObject(
                                        this.getModules(), scopeSpecification, instance );

                                    this.scopes.put( modelScope, scope );
                                    if ( this.isLoggable( Level.CONFIG ) )
                                    {
                                        this.log( Level.CONFIG, this.getMessage( "scopeInfo", new Object[]
                                            {
                                                i.getIdentifier(), modelScope
                                            } ), null );

                                    }
                                    break;
                                }
                                else if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                        i.getIdentifier(), i.getName() ), new Exception() );

                                }
                            }
                        }
                    }
                }
                else if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingSpecificationMessage( Scope.class.getName() ),
                              new Exception() );

                }
            }

            if ( scope == null )
            {
                scope = this.getDefaultScope( modelScope );
                if ( scope != null )
                {
                    this.scopes.put( modelScope, scope );
                    if ( this.isLoggable( Level.FINE ) )
                    {
                        this.log( Level.FINE, this.getDefaultScopeInfoMessage( modelScope, scope.getObjects() ), null );
                    }
                }
            }

            return scope;
        }
    }

    /**
     * Gets the default scope implementation for a given model scope.
     *
     * @param modelScope The scope to get the default implementation of.
     *
     * @return The default implementation of {@code modelScope} or {@code null} if no default implementation is
     * available implementing {@code modelScope}.
     *
     * @throws NullPointerException if {@code modelScope} is {@code null}.
     *
     * @see #getScope(java.lang.String)
     */
    public Scope getDefaultScope( final String modelScope )
    {
        if ( modelScope == null )
        {
            throw new NullPointerException( "modelScope" );
        }

        DefaultScope defaultScope = null;

        if ( modelScope.equals( SINGLETON_SCOPE_IDENTIFIER ) )
        {
            defaultScope = new DefaultScope( new HashMap<String, Object>() );
        }

        return defaultScope;
    }

    /**
     * Gets a locator to use with a given location URI.
     *
     * @param location The location URI to get a locator for.
     *
     * @return The locator to use for locating objects at {@code location} or {@code null} if no locator is available.
     *
     * @throws NullPointerException if {@code location} is {@code null}.
     * @throws InstantiationException if instantiating a locator fails.
     *
     * @see #getDefaultLocator(java.net.URI)
     */
    public Locator getLocator( final URI location ) throws InstantiationException
    {
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        Locator locator = null;
        final String scheme = location.getScheme();

        if ( scheme != null )
        {
            synchronized ( this.locators )
            {
                locator = this.locators.get( scheme );

                if ( locator == null )
                {
                    // Bootstrap locator loading.
                    final Specification locatorSpecification = this.getModules().getSpecification( Locator.class );

                    if ( locatorSpecification != null )
                    {
                        final Implementations implementations =
                            this.getModules().getImplementations( locatorSpecification.getIdentifier() );

                        if ( implementations != null )
                        {
                            for ( Implementation i : implementations.getImplementation() )
                            {
                                if ( scheme.equals( i.getName() ) )
                                {
                                    final Instance instance = this.getModelManager().getInstance(
                                        this.getModules(), i, this.getClassLoader( Locator.class ) );

                                    if ( instance != null )
                                    {
                                        locator = (Locator) this.getModelManager().getObject(
                                            this.getModules(), locatorSpecification, instance );

                                        this.locators.put( scheme, locator );
                                        if ( this.isLoggable( Level.CONFIG ) )
                                        {
                                            this.log( Level.CONFIG, this.getMessage( "locatorInfo", new Object[]
                                                {
                                                    i.getIdentifier(), scheme
                                                } ), null );

                                        }

                                        break;
                                    }
                                    else if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( Level.WARNING, this.getMissingInstanceMessage(
                                            i.getIdentifier(), i.getName() ), new Exception() );

                                    }
                                }
                            }
                        }
                    }
                    else if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingSpecificationMessage( Locator.class.getName() ),
                                  new Exception() );

                    }
                }

                if ( locator == null )
                {
                    locator = this.getDefaultLocator( location );
                    if ( locator != null )
                    {
                        this.locators.put( scheme, locator );
                        if ( this.isLoggable( Level.FINE ) )
                        {
                            this.log( Level.FINE, this.getDefaultLocatorInfoMessage( scheme ), null );
                        }
                    }
                }
            }
        }

        return locator;
    }

    /**
     * Gets the default locator implementation for a given location URI.
     *
     * @param location The location URI to get a default locator implementation for.
     *
     * @return The default locator implementation for {@code location} or {@code null} if no default implementation is
     * available for {@code location}.
     *
     * @throws NullPointerException if {@code location} is {@code null}.
     *
     * @see #getLocator(java.net.URI)
     */
    public Locator getDefaultLocator( final URI location )
    {
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        Locator locator = null;
        final DefaultLocator defaultLocator = new DefaultLocator();

        if ( defaultLocator.isLocationSupported( location ) )
        {
            locator = defaultLocator;
        }

        return locator;
    }

    /**
     * Gets the invoker of the instance.
     *
     * @return The invoker of the instance.
     *
     * @throws InstantiationException if instantiating a new invoker fails.
     */
    public Invoker getInvoker() throws InstantiationException
    {
        if ( this.invoker == null )
        {
            final Specification invokerSpecification = this.getModules().getSpecification( Invoker.class );

            if ( invokerSpecification != null )
            {
                final Implementations implementations =
                    this.getModules().getImplementations( invokerSpecification.getIdentifier() );

                if ( implementations != null && !implementations.getImplementation().isEmpty() )
                {
                    for ( Implementation i : implementations.getImplementation() )
                    {
                        if ( this.invoker == null )
                        {
                            final Instance invokerInstance = this.getModelManager().getInstance(
                                this.getModules(), i, this.getClassLoader( Invoker.class ) );

                            if ( invokerInstance != null )
                            {
                                this.invoker = (Invoker) this.getModelManager().getObject(
                                    this.getModules(), invokerSpecification, invokerInstance );

                                if ( this.isLoggable( Level.CONFIG ) )
                                {
                                    this.log( Level.CONFIG, this.getMessage( "invokerInfo", new Object[]
                                        {
                                            i.getIdentifier()
                                        } ), null );

                                }
                            }
                            else if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                    i.getIdentifier(), i.getName() ), new Exception() );

                            }
                        }
                        else if ( this.isLoggable( Level.FINE ) )
                        {
                            this.log( Level.FINE, this.getMessage( "ignoredInvoker", new Object[]
                                {
                                    i.getIdentifier()
                                } ), null );

                        }
                    }
                }
            }
            else if ( this.isLoggable( Level.WARNING ) )
            {
                this.log( Level.WARNING, this.getMissingSpecificationMessage( Invoker.class.getName() ),
                          new Exception() );

            }

            if ( this.invoker == null )
            {
                this.invoker = new DefaultInvoker();
                if ( this.isLoggable( Level.FINE ) )
                {
                    this.log( Level.FINE, this.getMessage( "defaultInvokerInfo", null ), null );
                }
            }
        }

        return this.invoker;
    }

    /**
     * Initializes the instance.
     * <p>This method is called once on first usage of a new instance.</p>
     *
     * @throws InstantiationException if initialization fails.
     */
    public synchronized void initialize() throws InstantiationException
    {
        try
        {
            if ( !this.initialized )
            {
                final long t0 = System.currentTimeMillis();
                this.initialized = true;

                this.scopes.clear();
                this.bootstrapLogRecords.clear();

                if ( this.modelManager instanceof DefaultModelManager )
                {
                    ( (DefaultModelManager) this.modelManager ).getListeners().
                        remove( this.defaultModelManagerListener );

                }

                this.modelManager = null;
                this.listeners = null;
                this.modules = null;
                this.invoker = null;

                this.getListeners().add( this.bootstrapObjectManagementListener );

                final Specification objectManager = this.getModules().getSpecification( ObjectManager.class );
                if ( objectManager == null )
                {
                    throw new InstantiationException( this.getMissingSpecificationMessage(
                        ObjectManager.class.getName() ) );

                }

                final Instance thisInstance = this.getModelManager().getInstance( this.getModules(), this );
                if ( thisInstance == null )
                {
                    throw new InstantiationException( this.getMissingInstanceMessage(
                        this.getClass().getName(), this.getArtifactNameMessage() ) );

                }

                if ( objectManager.getScope() != null )
                {
                    final Scope scope = this.getScope( objectManager.getScope() );
                    if ( scope == null )
                    {
                        throw new InstantiationException( this.getMissingScopeMessage( objectManager.getScope() ) );
                    }

                    scope.putObject( thisInstance.getIdentifier(), this );
                }

                this.getInvoker();

                // Bootstrap listener loading.
                final Specification listenerSpecification = this.getModules().getSpecification( Listener.class );

                if ( listenerSpecification != null )
                {
                    final Implementations implementations =
                        this.getModules().getImplementations( listenerSpecification.getIdentifier() );

                    if ( implementations != null && !implementations.getImplementation().isEmpty() )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            final Instance listenerInstance = this.getModelManager().getInstance(
                                this.getModules(), i, this.getClassLoader( Listener.class ) );

                            final Listener l = (Listener) this.getModelManager().getObject(
                                this.getModules(), listenerSpecification, listenerInstance );

                            this.getListeners().add( l );
                            this.bootstrapLogRecords.add( new LogRecord(
                                Level.FINE, this.getRegisteredListenerMessage( l.getClass().getName() ) ) );

                        }
                    }
                    else if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingImplementationsMessage(
                            listenerSpecification.getIdentifier() ), new Exception() );

                    }
                }
                else if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingSpecificationMessage( Listener.class.getName() ),
                              new Exception() );

                }

                this.getListeners().remove( this.bootstrapObjectManagementListener );

                if ( !this.getListeners().isEmpty() )
                {
                    for ( LogRecord logRecord : this.bootstrapLogRecords )
                    {
                        this.log( logRecord.getLevel(), logRecord.getMessage(), logRecord.getThrown() );
                    }
                }

                this.bootstrapLogRecords.clear();

                if ( this.isLoggable( Level.FINE ) )
                {
                    this.log( Level.FINE, this.getModulesReport( this.getModules() ), null );
                    this.log( Level.FINE, this.getImplementationInfoMessage(
                        Long.valueOf( System.currentTimeMillis() - t0 ) ), null );

                }
            }
        }
        catch ( final InstantiationException e )
        {
            for ( LogRecord r : this.bootstrapLogRecords )
            {
                Logger.getLogger( this.getClass().getName() ).log( r );
            }

            this.scopes.clear();
            this.bootstrapLogRecords.clear();

            if ( this.modelManager instanceof DefaultModelManager )
            {
                ( (DefaultModelManager) this.modelManager ).getListeners().remove( this.defaultModelManagerListener );
            }

            this.modelManager = null;
            this.listeners = null;
            this.modules = null;
            this.invoker = null;
            this.initialized = false;
            throw e;
        }
    }

    /**
     * Notifies registered listeners.
     *
     * @param level The level of the event.
     * @param message The message of the event or {@code null}.
     * @param throwable The throwable of the event or {@code null}.
     *
     * @throws NullPointerException if {@code level} is {@code null}.
     */
    protected void log( final Level level, final String message, final Throwable throwable )
    {
        if ( level == null )
        {
            throw new NullPointerException( "level" );
        }

        if ( this.isLoggable( level ) )
        {
            for ( Listener l : this.getListeners() )
            {
                l.onLog( level, message, throwable );
            }
        }
    }

    /**
     * Creates a proxy for a given object.
     *
     * @param specification The specification to create a proxy for.
     * @param instance The instance of {@code object}.
     * @param object The object to create a proxy for.
     *
     * @return A proxy for {@code object}.
     *
     * @throws InstantiationException if creating a proxy fails.
     */
    private Object createProxy( final Specification specification, final Instance instance, final Object object )
        throws InstantiationException
    {
        Object proxy = object;

        try
        {
            final Class specClass = Class.forName( specification.getClazz(), true, instance.getClassLoader() );
            if ( specClass.isInterface() )
            {
                final Set<Class> interfaces = new HashSet<Class>();
                interfaces.add( specClass );

                if ( instance.getSpecifications() != null )
                {
                    for ( Specification s : instance.getSpecifications().getSpecification() )
                    {
                        final Class clazz = Class.forName( s.getClazz(), true, instance.getClassLoader() );
                        if ( clazz.isInterface() )
                        {
                            interfaces.add( clazz );
                        }
                        else if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getCannotProxySpecificationClassMessage(
                                s.getClazz(), instance.getIdentifier() ), null );

                        }
                    }
                }

                proxy = Proxy.newProxyInstance(
                    instance.getClassLoader(), interfaces.toArray( new Class[ interfaces.size() ] ),
                    new java.lang.reflect.InvocationHandler()
                    {

                        public Object invoke( final Object proxy, final Method method, final Object[] args )
                            throws Throwable
                        {
                            return getInvoker().invoke( object, method, args );
                        }

                    } );

            }

            return proxy;
        }
        catch ( final ClassNotFoundException e )
        {
            throw (InstantiationException) new InstantiationException( e.getMessage() ).initCause( e );
        }
    }

    private String getMessage( final String key, final Object arguments )
    {
        final ResourceBundle bundle =
            ResourceBundle.getBundle( DefaultObjectManager.class.getName().replace( '.', '/' ) );

        return new MessageFormat( bundle.getString( key ) ).format( arguments );
    }

    private String getArtifactNameMessage()
    {
        return this.getMessage( "artifactName", null );
    }

    private String getMissingSpecificationMessage( final String specification )
    {
        return this.getMessage( "missingSpecification", new Object[]
            {
                specification
            } );

    }

    private String getMissingImplementationsMessage( final String specification )
    {
        return this.getMessage( "missingImplementations", new Object[]
            {
                specification
            } );

    }

    private String getMissingImplementationMessage( final String implementationName, final String specification )
    {
        return this.getMessage( "missingImplementation", new Object[]
            {
                implementationName, specification
            } );

    }

    private String getMissingObjectInstanceMessage( final Object object )
    {
        return this.getMessage( "missingObjectInstance", new Object[]
            {
                object.toString()
            } );

    }

    private String getMissingDependencyMessage( final String dependency, final String implementation )
    {
        return this.getMessage( "missingDependency", new Object[]
            {
                dependency, implementation
            } );
    }

    private String getMissingPropertyMessage( final String property, final String implementation )
    {
        return this.getMessage( "missingProperty", new Object[]
            {
                property, implementation
            } );

    }

    private String getMissingMessageMessage( final String message, final String implementation )
    {
        return this.getMessage( "missingMessage", new Object[]
            {
                message, implementation
            } );

    }

    private String getMissingClassLoaderMessage()
    {
        return this.getMessage( "missingClassloader", null );
    }

    private String getMissingInstanceMessage( final String implementation, final String implementationName )
    {
        return this.getMessage( "missingInstance", new Object[]
            {
                implementation, implementationName
            } );

    }

    private String getMissingObjectMessage( final String implementation, final String implementationName )
    {
        return this.getMessage( "missingObject", new Object[]
            {
                implementation, implementationName
            } );

    }

    private String getDependencyCycleMessage( final String implementation )
    {
        return this.getMessage( "dependencyCycle", new Object[]
            {
                implementation
            } );

    }

    private String getImplementationInfoMessage( final Long startMillis )
    {
        return this.getMessage( "implementationInfo", new Object[]
            {
                startMillis
            } );

    }

    private String getDefaultScopeInfoMessage( final String modelScope, final Map objects )
    {
        return this.getMessage( "defaultScopeInfo", new Object[]
            {
                modelScope, objects == null ? "" : objects.toString()
            } );

    }

    private String getMissingScopeMessage( final String modelScope )
    {
        return this.getMessage( "missingScope", new Object[]
            {
                modelScope
            } );

    }

    private String getRegisteredListenerMessage( final String listener )
    {
        return this.getMessage( "registeredListener", new Object[]
            {
                listener
            } );

    }

    private String getUnsupportedMultiplicityMessage( final Multiplicity multiplicity )
    {
        return this.getMessage( "unsupportedMultiplicity", new Object[]
            {
                multiplicity
            } );
    }

    private String getCannotProxySpecificationClassMessage( final String specification, final String instance )
    {
        return this.getMessage( "cannotProxySpecificationClass", new Object[]
            {
                specification, instance
            } );

    }

    private String getDefaultLocatorInfoMessage( final String scheme )
    {
        return this.getMessage( "defaultLocatorInfo", new Object[]
            {
                scheme
            } );

    }

    private String getMissingLocatorMessage( final URI location )
    {
        return this.getMessage( "missingLocator", new Object[]
            {
                location.toString()
            } );

    }

    private String getModulesReport( final Modules mods )
    {
        final StringBuilder modulesInfo = new StringBuilder();
        final String lineSeparator = System.getProperty( "line.separator" );

        if ( mods.getDocumentation() != null )
        {
            modulesInfo.append( mods.getDocumentation().getText( Locale.getDefault().getLanguage() ).getValue() ).
                append( lineSeparator );

        }
        else
        {
            modulesInfo.append( lineSeparator );
        }

        for ( Module m : mods.getModule() )
        {
            modulesInfo.append( "\tM:" ).append( m.getName() ).append( ':' ).append( m.getVersion() ).
                append( lineSeparator );

            if ( m.getSpecifications() != null )
            {
                for ( Specification s : m.getSpecifications().getSpecification() )
                {
                    modulesInfo.append( "\t\t" );
                    this.appendSpecificationInfo( s, modulesInfo ).append( lineSeparator );

                    final Implementations available = mods.getImplementations( s.getIdentifier() );

                    if ( available != null )
                    {
                        for ( Implementation i : available.getImplementation() )
                        {
                            modulesInfo.append( "\t\t\t" );
                            this.appendImplementationInfo( i, modulesInfo ).append( "@" ).
                                append( mods.getModuleOfImplementation( i.getIdentifier() ).getName() ).
                                append( lineSeparator );

                        }
                    }
                }
            }

            if ( m.getImplementations() != null )
            {
                for ( Implementation i : m.getImplementations().getImplementation() )
                {
                    modulesInfo.append( "\t\t" );
                    this.appendImplementationInfo( i, modulesInfo ).append( lineSeparator );

                    if ( i.getImplementations() != null )
                    {
                        modulesInfo.append( "\t\t\t" );
                        for ( ImplementationReference r : i.getImplementations().getReference() )
                        {
                            this.appendImplementationInfo(
                                mods.getImplementation( r.getIdentifier() ), modulesInfo ).append( '@' ).
                                append( mods.getModuleOfImplementation( r.getIdentifier() ).getName() ).
                                append( lineSeparator );

                        }
                    }
                    if ( i.getSpecifications() != null )
                    {
                        for ( SpecificationReference s : i.getSpecifications().getReference() )
                        {
                            modulesInfo.append( "\t\t\tS:" ).append( s.getIdentifier() ).append( ':' ).
                                append( s.getVersion() ).append( '@' ).append( mods.getModuleOfSpecification(
                                s.getIdentifier() ).getName() ).append( lineSeparator );

                        }
                    }

                    if ( i.getDependencies() != null )
                    {
                        for ( Dependency d : i.getDependencies().getDependency() )
                        {
                            modulesInfo.append( "\t\t\tD:" ).append( d.getName() ).append( ':' ).
                                append( d.getIdentifier() );

                            if ( d.getImplementationName() != null )
                            {
                                modulesInfo.append( ":" ).append( d.getImplementationName() );
                            }

                            modulesInfo.append( '@' ).append( mods.getModuleOfSpecification(
                                d.getIdentifier() ).getName() ).append( lineSeparator );

                            final Implementations available = mods.getImplementations( d.getIdentifier() );

                            if ( available != null )
                            {
                                for ( Implementation di : available.getImplementation() )
                                {
                                    modulesInfo.append( "\t\t\t\t" );
                                    this.appendImplementationInfo( di, modulesInfo ).append( "@" ).
                                        append( mods.getModuleOfImplementation( di.getIdentifier() ).getName() ).
                                        append( lineSeparator );

                                }
                            }
                        }
                    }

                    if ( i.getMessages() != null )
                    {
                        for ( Message msg : i.getMessages().getMessage() )
                        {
                            modulesInfo.append( "\t\t\tM:" ).append( msg.getName() ).append( ':' ).
                                append( msg.getTemplate().getText( Locale.getDefault().getLanguage() ).getValue() ).
                                append( lineSeparator );

                        }
                    }

                    if ( i.getProperties() != null )
                    {
                        for ( Property p : i.getProperties().getProperty() )
                        {
                            modulesInfo.append( "\t\t\tP:" ).append( p.getName() ).append( ':' ).
                                append( p.getType() ).append( ':' );

                            try
                            {
                                modulesInfo.append( p.getJavaValue( this.getClassLoader( this.getClass() ) ) );
                            }
                            catch ( final ClassNotFoundException e )
                            {
                                modulesInfo.append( Level.WARNING.getLocalizedName() ).append( " " ).append( e );
                            }
                            catch ( final InstantiationException e )
                            {
                                modulesInfo.append( Level.WARNING.getLocalizedName() ).append( " " ).append( e );
                            }

                            modulesInfo.append( lineSeparator );
                        }
                    }
                }
            }
        }

        return modulesInfo.toString();
    }

    private StringBuilder appendSpecificationInfo( final Specification s, final StringBuilder b )
    {
        b.append( "S:" ).append( s.getIdentifier() ).append( ':' ).append( s.getVersion() ).append( ':' ).
            append( s.getMultiplicity() ).append( ":Scope:" ).
            append( s.getScope() == null ? "Multiton" : s.getScope() ).append( ":Class:" ).append( s.getClazz() );

        return b;
    }

    private StringBuilder appendImplementationInfo( final Implementation i, final StringBuilder b )
    {
        b.append( "I:" ).append( i.getIdentifier() ).append( ':' ).append( i.getName() ).append( ':' ).
            append( i.getVersion() );

        if ( i.getClazz() != null )
        {
            b.append( ":Class:" ).append( i.getClazz() );
        }
        if ( i.getLocation() != null )
        {
            b.append( ":Location:" ).append( i.getLocation() );
        }

        return b;
    }

    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
