// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.jomc.ObjectManagementException;
import org.jomc.ObjectManager;
import org.jomc.ObjectManagerFactory;
import org.jomc.model.DefaultModelManager;
import org.jomc.model.Dependency;
import org.jomc.model.Implementation;
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
import org.jomc.spi.Listener;
import org.jomc.spi.Locator;
import org.xml.sax.SAXException;

// SECTION-START[Implementation Comment]
/**
 * Object management and configuration reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.ObjectManager} {@code 1.0}<blockquote>
 * Object applies to Singleton scope.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
)
// SECTION-END
public class DefaultObjectManager implements ObjectManager
{
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    public DefaultObjectManager()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[ObjectManager]

    public Object getObject( final Class specification ) throws ObjectManagementException
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        Object object = null;

        try
        {
            this.initialize();

            final Specification s = this.getModules().getSpecification( specification.getName() );

            if ( s != null )
            {
                final Implementations available =
                    this.getModules().getImplementations( specification.getName() );

                if ( available != null && !available.getImplementation().isEmpty() )
                {
                    if ( s.getMultiplicity() == Multiplicity.ONE )
                    {
                        final Implementation i = available.getImplementation().get( 0 );

                        if ( i.getLocation() != null )
                        {
                            object = this.getObject( s, i.getLocationUri(), this.getClassLoader( specification ) );
                            if ( object == null )
                            {
                                this.log( Level.WARNING, this.getMissingObjectMessage(
                                    i.getIdentifier(), i.getName() ), null );

                            }
                        }
                        else
                        {
                            final Instance instance = this.getModelManager().getInstance(
                                this.getModules(), s, i, this.getClassLoader( specification ) );

                            if ( instance != null )
                            {
                                object = this.getObject( s, instance );
                                if ( object == null )
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        i.getIdentifier(), i.getName() ), null );

                                }
                            }
                            else
                            {
                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                    i.getIdentifier(), i.getName() ), null );

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
                                else
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        i.getIdentifier(), i.getName() ), null );

                                }
                            }
                            else
                            {
                                final Instance instance = this.getModelManager().getInstance(
                                    this.getModules(), s, i, this.getClassLoader( specification ) );

                                if ( instance != null )
                                {
                                    final Object o = this.getObject( s, instance );
                                    if ( o != null )
                                    {
                                        list.add( o );
                                    }
                                    else
                                    {
                                        this.log( Level.WARNING, this.getMissingObjectMessage(
                                            i.getIdentifier(), i.getName() ), null );

                                    }
                                }
                                else
                                {
                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                        i.getIdentifier(), i.getName() ), null );

                                }
                            }
                        }

                        object = list.isEmpty() ? null
                                 : list.toArray( (Object[]) Array.newInstance( specification, list.size() ) );

                    }
                    else
                    {
                        this.log( Level.WARNING, this.getUnsupportedMultiplicityMessage( s.getMultiplicity() ), null );
                    }
                }
                else
                {
                    this.log( Level.WARNING, this.getMissingImplementationsMessage( specification.getName() ), null );
                }
            }
            else
            {
                this.log( Level.WARNING, this.getMissingSpecificationMessage( specification.getName() ), null );
            }
        }
        catch ( InstantiationException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
        catch ( ClassNotFoundException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
        catch ( IOException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return object;
    }

    public Object getObject( final Class specification, final String implementationName )
        throws ObjectManagementException
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

            final Specification s = this.getModules().getSpecification( specification.getName() );
            if ( s != null )
            {
                final Implementations available = this.getModules().getImplementations( specification.getName() );
                if ( available != null && !available.getImplementation().isEmpty() )
                {
                    final Implementation i = available.getImplementationByName( implementationName );

                    if ( i != null )
                    {
                        if ( i.getLocation() != null )
                        {
                            object = this.getObject( s, i.getLocationUri(), this.getClassLoader( specification ) );
                            if ( object == null )
                            {
                                this.log( Level.WARNING, this.getMissingObjectMessage(
                                    i.getIdentifier(), i.getName() ), null );

                            }
                        }
                        else
                        {
                            final Instance instance = this.getModelManager().getInstance(
                                this.getModules(), s, i, this.getClassLoader( specification ) );

                            if ( instance != null )
                            {
                                object = this.getObject( s, instance );
                                if ( object == null )
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        i.getIdentifier(), i.getName() ), null );

                                }
                            }
                            else
                            {
                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                    i.getIdentifier(), i.getName() ), null );

                            }
                        }
                    }
                    else
                    {
                        this.log( Level.WARNING, this.getMissingImplementationMessage(
                            implementationName, s.getIdentifier() ), null );

                    }
                }
                else
                {
                    this.log( Level.WARNING, this.getMissingImplementationsMessage(
                        specification.getName() ), null );

                }
            }
            else
            {
                this.log( Level.WARNING, this.getMissingSpecificationMessage( specification.getName() ), null );
            }
        }
        catch ( InstantiationException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
        catch ( ClassNotFoundException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
        catch ( IOException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return object;
    }

    public Object getDependency( final Object object, final String dependencyName )
        throws ObjectManagementException
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
                        final Dependency dependency = instance.getDependencies().getDependency( dependencyName );

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

                                                if ( o == null )
                                                {
                                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                                        i.getIdentifier(), i.getName() ), null );

                                                }
                                            }
                                            else
                                            {
                                                final Instance di = this.getModelManager().getInstance(
                                                    this.getModules(), ds, i, dependency,
                                                    this.getClassLoader( object.getClass() ) );

                                                if ( di != null )
                                                {
                                                    o = this.getObject( ds, di );
                                                    if ( o == null )
                                                    {
                                                        this.log( Level.WARNING, this.getMissingObjectMessage(
                                                            i.getIdentifier(), i.getName() ), null );

                                                    }
                                                }
                                                else
                                                {
                                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                                        i.getIdentifier(), i.getName() ), null );

                                                }
                                            }
                                        }
                                        else
                                        {
                                            this.log( Level.WARNING, this.getMissingImplementationMessage(
                                                dependency.getImplementationName(), dependency.getIdentifier() ), null );

                                        }
                                    }
                                    else if ( ds.getMultiplicity() == Multiplicity.ONE )
                                    {
                                        final Implementation ref = available.getImplementation().get( 0 );
                                        if ( ref.getLocation() != null )
                                        {
                                            o = this.getObject(
                                                ds, ref.getLocationUri(), this.getClassLoader( object.getClass() ) );

                                            if ( o == null )
                                            {
                                                this.log( Level.WARNING, this.getMissingObjectMessage(
                                                    ref.getIdentifier(), ref.getName() ), null );

                                            }
                                        }
                                        else
                                        {
                                            final Instance di = this.getModelManager().getInstance(
                                                this.getModules(), ds, ref, dependency,
                                                this.getClassLoader( object.getClass() ) );

                                            if ( di != null )
                                            {
                                                o = this.getObject( ds, di );
                                                if ( o == null )
                                                {
                                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                                        ref.getIdentifier(), ref.getName() ), null );

                                                }
                                            }
                                            else
                                            {
                                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                                    ref.getIdentifier(), ref.getName() ), null );

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
                                                else
                                                {
                                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                                        a.getIdentifier(), a.getName() ), null );

                                                }
                                            }
                                            else
                                            {
                                                final Instance di = this.getModelManager().getInstance(
                                                    this.getModules(), ds, a, dependency,
                                                    this.getClassLoader( object.getClass() ) );

                                                if ( di != null )
                                                {
                                                    final Object o2 = this.getObject( ds, di );

                                                    if ( o2 != null )
                                                    {
                                                        list.add( o2 );
                                                    }
                                                    else
                                                    {
                                                        this.log( Level.WARNING, this.getMissingObjectMessage(
                                                            a.getIdentifier(), a.getName() ), null );

                                                    }
                                                }
                                                else
                                                {
                                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                                        a.getIdentifier(), a.getName() ), null );

                                                }
                                            }
                                        }

                                        final Class specClass = Class.forName(
                                            ds.getIdentifier(), true, this.getClassLoader( object.getClass() ) );

                                        o = list.isEmpty() ? null : list.toArray( (Object[]) Array.newInstance(
                                            specClass, list.size() ) );

                                    }

                                    if ( o != null && dependency.isBound() )
                                    {
                                        instance.getDependencyObjects().put( dependencyName, o );
                                    }
                                }
                                else if ( !dependency.isOptional() )
                                {
                                    this.log( Level.WARNING, this.getMissingImplementationsMessage(
                                        dependency.getIdentifier() ), null );

                                }
                            }
                            else
                            {
                                this.log( Level.WARNING, this.getMissingSpecificationMessage(
                                    dependency.getIdentifier() ), null );

                            }
                        }
                        else
                        {
                            this.log( Level.WARNING, this.getMissingDependencyMessage(
                                dependency.getName(), instance.getIdentifier() ), null );

                        }
                    }
                }
            }
            else
            {
                this.log( Level.WARNING, this.getMissingObjectInstanceMessage( object ), null );
            }
        }
        catch ( InstantiationException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
        catch ( ClassNotFoundException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
        catch ( IOException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return o;
    }

    public Object getProperty( final Object object, final String propertyName ) throws ObjectManagementException
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
                        final Property property = instance.getProperties().getProperty( propertyName );

                        if ( property != null )
                        {
                            value = property.getPropertyValue();

                            if ( value != null )
                            {
                                instance.getPropertyObjects().put( propertyName, value );
                            }
                        }
                        else
                        {
                            this.log( Level.WARNING, this.getMissingPropertyMessage(
                                propertyName, object.getClass().getName() ), null );

                        }
                    }
                }
            }
            else
            {
                this.log( Level.WARNING, this.getMissingObjectInstanceMessage( object ), null );
            }
        }
        catch ( InstantiationException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return value;
    }

    public String getMessage( final Object object, final String messageName, final Locale locale,
                              final Object arguments ) throws ObjectManagementException
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
                    final Message message = instance.getMessages().getMessage( messageName );

                    if ( message != null )
                    {
                        final MessageFormat fmt = new MessageFormat( message.getTemplate().
                            getText( locale.getLanguage().toLowerCase( Locale.ENGLISH ) ).getValue(), locale );

                        text = fmt.format( arguments );
                    }
                    else
                    {
                        this.log( Level.WARNING, this.getMissingMessageMessage(
                            messageName, object.getClass().getName() ), null );

                    }
                }
            }
            else
            {
                this.log( Level.WARNING, this.getMissingObjectInstanceMessage( object ), null );
            }
        }
        catch ( InstantiationException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return text;
    }

    // SECTION-END
    // SECTION-START[DefaultObjectManager]
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
    private final Map<org.jomc.model.Scope, org.jomc.spi.Scope> scopes =
        new HashMap<org.jomc.model.Scope, org.jomc.spi.Scope>();

    /** Locators of the instance. */
    private final Map<String, Locator> locators = new HashMap<String, Locator>();

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

    /** {@code DefaultModelManager.Listener} of the instance. */
    private final DefaultModelManager.Listener defaultModelManagerListener = new DefaultModelManager.Listener()
    {

        @Override
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
     */
    public static ObjectManager getObjectManager()
    {
        return (ObjectManager) singleton.getObject( ObjectManager.class );
    }

    /**
     * Gets the flag indicating that classpath resolution is performed.
     * <p>Classpath resolution is performed by default. It can be disabled by setting the system property
     * {@code jomc.classpathAware} to {@code false}.</p>
     *
     * @return {@code true} if the class loader of the instance is searched for resources; {@code false} if no
     * classpath resolution is performed.
     */
    public boolean isClasspathAware()
    {
        if ( this.classpathAware == null )
        {
            this.classpathAware =
                Boolean.valueOf( System.getProperty( "jomc.classpathAware", Boolean.TRUE.toString() ) );

        }

        return this.classpathAware;
    }

    /**
     * Sets the flag indicating that classpath resolution should be performed.
     *
     * @param value {@code true} if the class loader of the instance is searched for resources; {@code false} if no
     * classpath resolution is performed.
     */
    public void setClasspathAware( final boolean value )
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
     * Gets the modules of the instance.
     *
     * @return The modules of the instance.
     */
    public Modules getModules()
    {
        if ( this.modules == null )
        {
            try
            {
                if ( this.isClasspathAware() )
                {
                    final DefaultModelManager defaultModelManager = new DefaultModelManager();
                    defaultModelManager.setClassLoader( this.getClassLoader( this.getClass() ) );
                    defaultModelManager.getListeners().add( this.defaultModelManagerListener );

                    final Modules defaultModules =
                        defaultModelManager.getClasspathModules( defaultModelManager.getDefaultDocumentLocation() );

                    final Module classpathModule = defaultModelManager.getClasspathModule( defaultModules );
                    if ( classpathModule != null )
                    {
                        defaultModules.getModule().add( classpathModule );
                    }

                    defaultModelManager.getListeners().remove( this.defaultModelManagerListener );

                    this.getModelManager().validateModules( defaultModules );
                    this.modules = defaultModules;
                }
            }
            catch ( ModelException e )
            {
                this.log( Level.SEVERE, e.getMessage(), e );
                for ( ModelException.Detail d : e.getDetails() )
                {
                    this.log( d.getLevel(), d.getMessage(), null );
                }
            }
            catch ( IOException e )
            {
                this.log( Level.SEVERE, e.getMessage(), e );
            }
            catch ( SAXException e )
            {
                this.log( Level.SEVERE, e.getMessage(), e );
            }
            catch ( JAXBException e )
            {
                this.log( Level.SEVERE, e.getMessage(), e );
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
     */
    public void setModules( final Modules value )
    {
        this.modules = value;
        this.initialized = false;
    }

    /**
     * Gets the model manager backing the instance.
     *
     * @return The model manager backing the instance.
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
            this.log( Level.WARNING, this.getMissingClassLoaderMessage(), null );
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
     * @throws InstantiationException if getting an object fails.
     * @throws NullPointerException if {@code specification} or {@code instance} is {@code null}.
     */
    public Object getObject( final Specification specification, final Instance instance )
        throws InstantiationException
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
        final org.jomc.spi.Scope scope = this.getScope( instance.getScope() );

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
        else
        {
            this.log( Level.WARNING, this.getMissingScopeMessage( instance.getScope() ), null );
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
     * @throws InstantiationException if instantiating a locator fails.
     * @throws ClassNotFoundException if the class of {@code specification} is not found.
     * @throws IOException if locating the object fails.
     * @throws NullPointerException if {@code specification}, {@code location} or {@code classLoader} is {@code null}.
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
            object = locator.getObject(
                Class.forName( specification.getIdentifier(), true, classLoader ), location );

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
     * @throws InstantiationException if instantiating a scope fails.
     * @throws NullPointerException if {@code modelScope} is {@code null}.
     */
    public org.jomc.spi.Scope getScope( final org.jomc.model.Scope modelScope ) throws InstantiationException
    {
        if ( modelScope == null )
        {
            throw new NullPointerException( "modelScope" );
        }

        synchronized ( this.scopes )
        {
            org.jomc.spi.Scope scope = this.scopes.get( modelScope );

            if ( scope == null )
            {
                // Bootstrap scope loading.
                final Specification scopeSpecification =
                    this.getModules().getSpecification( org.jomc.spi.Scope.class.getName() );

                final Implementations implementations =
                    this.getModules().getImplementations( org.jomc.spi.Scope.class.getName() );

                if ( scopeSpecification != null && implementations != null )
                {
                    for ( Implementation i : implementations.getImplementation() )
                    {
                        final Instance instance = this.getModelManager().getInstance(
                            this.getModules(), scopeSpecification, i, this.getClassLoader( org.jomc.spi.Scope.class ) );

                        final org.jomc.spi.Scope s = (org.jomc.spi.Scope) this.getModelManager().getObject(
                            this.getModules(), scopeSpecification, instance );

                        if ( s.getName().equals( modelScope.value() ) )
                        {
                            scope = s;
                            this.scopes.put( modelScope, scope );
                            break;
                        }
                    }
                }
            }

            if ( scope == null )
            {
                scope = this.getDefaultScope( modelScope );
                if ( scope != null )
                {
                    this.scopes.put( modelScope, scope );
                    this.log( Level.FINE, this.getDefaultScopeInfoMessage( modelScope, scope.getObjects() ), null );
                }
            }

            if ( scope == null )
            {
                this.log( Level.WARNING, this.getMissingImplementationsMessage( org.jomc.spi.Scope.class.getName() ),
                          null );

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
     */
    public org.jomc.spi.Scope getDefaultScope( final org.jomc.model.Scope modelScope )
    {
        if ( modelScope == null )
        {
            throw new NullPointerException( "modelScope" );
        }

        DefaultScope defaultScope = null;

        if ( modelScope == org.jomc.model.Scope.SINGLETON || modelScope == org.jomc.model.Scope.CONTEXT )
        {
            defaultScope = new DefaultScope( modelScope.value(), new HashMap<String, Object>() );
        }
        else if ( modelScope == org.jomc.model.Scope.MULTITON )
        {
            defaultScope = new DefaultScope( modelScope.value(), null );
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
     * @throws InstantiationException if instantiating a locator fails.
     * @throws NullPointerException if {@code location} is {@code null}.
     */
    public Locator getLocator( final URI location ) throws InstantiationException
    {
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        final String scheme = location.getScheme();

        synchronized ( this.locators )
        {
            Locator locator = this.locators.get( scheme );

            if ( locator == null )
            {
                // Bootstrap locator loading.
                final Specification locatorSpecification =
                    this.getModules().getSpecification( Locator.class.getName() );

                final Implementations implementations =
                    this.getModules().getImplementations( Locator.class.getName() );

                if ( locatorSpecification != null && implementations != null )
                {
                    for ( Implementation i : implementations.getImplementation() )
                    {
                        if ( scheme.equals( i.getName() ) )
                        {
                            final Instance instance = this.getModelManager().getInstance(
                                this.getModules(), locatorSpecification, i, this.getClassLoader( Locator.class ) );

                            final Locator l = (Locator) this.getModelManager().getObject(
                                this.getModules(), locatorSpecification, instance );

                            locator = l;
                            this.locators.put( scheme, locator );
                            break;
                        }
                    }
                }
            }

            if ( locator == null )
            {
                locator = this.getDefaultLocator( location );
                if ( locator != null )
                {
                    this.locators.put( scheme, locator );
                    this.log( Level.FINE, this.getDefaultLocatorInfoMessage( scheme ), null );
                }
            }

            if ( locator == null )
            {
                this.log( Level.WARNING, this.getMissingImplementationsMessage( Locator.class.getName() ), null );
            }

            return locator;
        }
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
                final long start = System.currentTimeMillis();
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

                this.getListeners().add( this.bootstrapObjectManagementListener );

                final Instance thisInstance = this.getModelManager().getInstance( this.getModules(), this );
                if ( thisInstance == null )
                {
                    throw new InstantiationException( this.getMissingInstanceMessage(
                        this.getClass().getName(), this.getArtifactNameMessage() ) );

                }

                thisInstance.setScope( org.jomc.model.Scope.SINGLETON );

                final org.jomc.spi.Scope singletons = this.getScope( org.jomc.model.Scope.SINGLETON );
                if ( singletons == null )
                {
                    throw new InstantiationException( this.getMissingScopeMessage( org.jomc.model.Scope.SINGLETON ) );
                }

                singletons.putObject( thisInstance.getIdentifier(), this );

                // Bootstrap listener loading.
                final Specification listenerSpecification = this.getModules().getSpecification(
                    org.jomc.spi.Listener.class.getName() );

                final Implementations implementations = this.getModules().getImplementations(
                    org.jomc.spi.Listener.class.getName() );

                if ( listenerSpecification != null && implementations != null &&
                     !implementations.getImplementation().isEmpty() )
                {
                    for ( Implementation i : implementations.getImplementation() )
                    {
                        final Instance listenerInstance = this.getModelManager().getInstance(
                            this.getModules(), listenerSpecification, i,
                            this.getClassLoader( org.jomc.spi.Listener.class ) );

                        final org.jomc.spi.Listener l = (org.jomc.spi.Listener) this.getModelManager().getObject(
                            this.getModules(), listenerSpecification, listenerInstance );

                        this.getListeners().add( l );
                        this.bootstrapLogRecords.add( new LogRecord( Level.FINE, this.getRegisteredListenerMessage(
                            l.getClass().getName() ) ) );

                    }
                }
                else
                {
                    this.log( Level.WARNING, this.getMissingImplementationsMessage(
                        org.jomc.spi.Listener.class.getName() ), null );

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
                this.log( Level.FINE, this.getModulesReport( this.getModules() ), null );
                this.log( Level.FINE, this.getImplementationInfoMessage(
                    new Date( System.currentTimeMillis() - start ) ), null );

            }
        }
        catch ( InstantiationException e )
        {
            for ( LogRecord r : this.bootstrapLogRecords )
            {
                Logger.getLogger( this.getClass().getName() ).log( r );
            }

            this.scopes.clear();
            this.bootstrapLogRecords.clear();

            if ( this.modelManager instanceof DefaultModelManager )
            {
                ( (DefaultModelManager) this.modelManager ).getListeners().remove(
                    this.defaultModelManagerListener );
            }

            this.modelManager = null;
            this.listeners = null;
            this.modules = null;
            this.initialized = false;
            throw e;
        }
    }

    /**
     * Notifies registered listeners.
     *
     * @param level The level of the event.
     * @param message The message of the event.
     * @param throwable The throwable of the event.
     */
    protected void log( final Level level, final String message, final Throwable throwable )
    {
        for ( Listener l : this.getListeners() )
        {
            l.onLog( level, message, throwable );
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
    private Object createProxy( final Specification specification, final Instance instance, Object object )
        throws InstantiationException
    {
        try
        {
            final Class specClass = Class.forName( specification.getIdentifier(), true, instance.getClassLoader() );
            if ( specClass.isInterface() )
            {
                final Set<Class> interfaces = new HashSet<Class>();
                interfaces.add( specClass );

                if ( instance.getSpecifications() != null )
                {
                    for ( Specification s : instance.getSpecifications().getSpecification() )
                    {
                        final Class clazz = Class.forName( s.getIdentifier(), true, instance.getClassLoader() );
                        if ( clazz.isInterface() )
                        {
                            interfaces.add( clazz );
                        }
                        else
                        {
                            this.log( Level.WARNING, this.getCannotProxySpecificationClassMessage(
                                s.getIdentifier(), instance.getIdentifier() ), null );

                        }
                    }
                    for ( SpecificationReference r : instance.getSpecifications().getReference() )
                    {
                        final Class clazz = Class.forName( r.getIdentifier(), true, instance.getClassLoader() );
                        if ( clazz.isInterface() )
                        {
                            interfaces.add( clazz );
                        }
                        else
                        {
                            this.log( Level.WARNING, this.getCannotProxySpecificationClassMessage(
                                r.getIdentifier(), instance.getIdentifier() ), null );

                        }
                    }
                }

                final Object proxied = object;
                object = Proxy.newProxyInstance(
                    instance.getClassLoader(), interfaces.toArray( new Class[ interfaces.size() ] ),
                    new InvocationHandler()
                    {

                        public Object invoke( final Object proxy, final Method method, final Object[] args )
                            throws Throwable
                        {
                            try
                            {
                                final Method m =
                                    proxied.getClass().getMethod( method.getName(), method.getParameterTypes() );

                                if ( instance.isStateless() )
                                {
                                    return m.invoke( proxied, args );
                                }
                                else
                                {
                                    synchronized ( proxied )
                                    {
                                        return m.invoke( proxied, args );
                                    }
                                }
                            }
                            catch ( NoSuchMethodException e )
                            {
                                log( Level.SEVERE, e.getMessage(), e );
                                throw new ObjectManagementException( e.getMessage(), e );
                            }
                            catch ( SecurityException e )
                            {
                                log( Level.SEVERE, e.getMessage(), e );
                                throw new ObjectManagementException( e.getMessage(), e );
                            }
                            catch ( InvocationTargetException e )
                            {
                                if ( e.getTargetException() != null )
                                {
                                    throw e.getTargetException();
                                }

                                log( Level.SEVERE, e.getMessage(), e );
                                throw new ObjectManagementException( e.getMessage(), e );
                            }
                            catch ( IllegalAccessException e )
                            {
                                log( Level.SEVERE, e.getMessage(), e );
                                throw new ObjectManagementException( e.getMessage(), e );
                            }
                        }

                    } );

            }

            return object;
        }
        catch ( ClassNotFoundException e )
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

    private String getMissingImplementationMessage( final String implementationName,
                                                    final String specification )
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

    private String getMissingDependencyMessage( final String dependency,
                                                final String implementation )
    {
        return this.getMessage( "missingDependency", new Object[]
            {
                dependency, implementation
            } );
    }

    private String getMissingPropertyMessage( final String property,
                                              final String implementation )
    {
        return this.getMessage( "missingProperty", new Object[]
            {
                property, implementation
            } );

    }

    private String getMissingMessageMessage( final String message,
                                             final String implementation )
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

    private String getImplementationInfoMessage( final Date startTime )
    {
        return this.getMessage( "implementationInfo", new Object[]
            {
                startTime
            } );

    }

    private String getDefaultScopeInfoMessage( final org.jomc.model.Scope scope,
                                               final Map objects )
    {
        return this.getMessage( "defaultScopeInfo", new Object[]
            {
                scope.value(), objects == null ? "" : objects.toString()
            } );

    }

    private String getMissingScopeMessage( final org.jomc.model.Scope scope )
    {
        return this.getMessage( "missingScope", new Object[]
            {
                scope.value()
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

    private String getModulesReport( final Modules modules )
    {
        final StringBuffer modulesInfo = new StringBuffer();
        if ( modules.getDocumentation() != null )
        {
            modulesInfo.append( modules.getDocumentation().getText( Locale.getDefault().getLanguage() ).getValue() ).
                append( '\n' );

        }
        else
        {
            modulesInfo.append( '\n' );
        }

        for ( Module m : modules.getModule() )
        {
            modulesInfo.append( "\tM:" ).append( m.getName() ).append( ':' ).append( m.getVersion() ).append( '\n' );

            if ( m.getSpecifications() != null )
            {
                for ( Specification s : m.getSpecifications().getSpecification() )
                {
                    modulesInfo.append( "\t\t" );
                    this.appendSpecificationInfo( s, modulesInfo ).append( '\n' );

                    final Implementations available = modules.getImplementations( s.getIdentifier() );

                    if ( available != null )
                    {
                        for ( Implementation i : available.getImplementation() )
                        {
                            modulesInfo.append( "\t\t\t" );
                            this.appendImplementationInfo( i, modulesInfo ).append( "@" ).
                                append( modules.getModuleOfImplementation( i.getIdentifier() ).getName() ).
                                append( '\n' );

                        }
                    }
                }
            }

            if ( m.getImplementations() != null )
            {
                for ( Implementation i : m.getImplementations().getImplementation() )
                {
                    modulesInfo.append( "\t\t" );
                    this.appendImplementationInfo( i, modulesInfo ).append( '\n' );

                    if ( i.getParent() != null )
                    {
                        modulesInfo.append( "\t\t\t" );
                        this.appendImplementationInfo( modules.getImplementation( i.getParent() ), modulesInfo ).
                            append( '@' ).append( modules.getModuleOfImplementation( i.getParent() ).getName() ).
                            append( '\n' );

                    }
                    if ( i.getSpecifications() != null )
                    {
                        for ( SpecificationReference s : i.getSpecifications().getReference() )
                        {
                            modulesInfo.append( "\t\t\tS:" ).append( s.getIdentifier() ).append( ':' ).
                                append( s.getVersion() ).append( '@' ).append( modules.getModuleOfSpecification(
                                s.getIdentifier() ).getName() ).append( '\n' );

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

                            modulesInfo.append( '@' ).append( modules.getModuleOfSpecification(
                                d.getIdentifier() ).getName() ).append( '\n' );

                            final Implementations available = modules.getImplementations( d.getIdentifier() );

                            if ( available != null )
                            {
                                for ( Implementation di : available.getImplementation() )
                                {
                                    modulesInfo.append( "\t\t\t\t" );
                                    this.appendImplementationInfo( di, modulesInfo ).append( "@" ).
                                        append( modules.getModuleOfImplementation( di.getIdentifier() ).getName() ).
                                        append( '\n' );

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
                                append( '\n' );

                        }
                    }

                    if ( i.getProperties() != null )
                    {
                        for ( Property p : i.getProperties().getProperty() )
                        {
                            modulesInfo.append( "\t\t\tP:" ).append( p.getName() ).append( ':' ).append( p.getType() ).
                                append( ':' ).append( p.getValue() ).append( '\n' );

                        }
                    }
                }
            }
        }

        return modulesInfo.toString();
    }

    private StringBuffer appendSpecificationInfo( final Specification s, final StringBuffer b )
    {
        return b.append( "S:" ).append( s.getIdentifier() ).append( ':' ).append( s.getVersion() ).append( ':' ).
            append( s.getScope() ).append( ':' ).append( s.getMultiplicity() );

    }

    private StringBuffer appendImplementationInfo( final Implementation i, final StringBuffer b )
    {
        return b.append( "I:" ).append( i.getIdentifier() ).append( ':' ).append( i.getName() ).append( ':' ).
            append( i.getVersion() );

    }

    // SECTION-END
}
