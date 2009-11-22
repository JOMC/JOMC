// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
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
// </editor-fold>
// SECTION-END
package org.jomc.ri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBResult;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.validation.Schema;
import org.jomc.ObjectManagementException;
import org.jomc.ObjectManager;
import org.jomc.ObjectManagerFactory;
import org.jomc.model.DefaultModelManager;
import org.jomc.model.DefaultModelObjectValidator;
import org.jomc.model.Dependency;
import org.jomc.model.Implementation;
import org.jomc.model.ImplementationReference;
import org.jomc.model.Implementations;
import org.jomc.model.Instance;
import org.jomc.model.Message;
import org.jomc.model.ModelObjectValidationReport;
import org.jomc.model.ModelObjectValidator;
import org.jomc.model.ModelProvider;
import org.jomc.model.Module;
import org.jomc.model.Modules;
import org.jomc.model.Multiplicity;
import org.jomc.model.ObjectFactory;
import org.jomc.model.Property;
import org.jomc.model.Specification;
import org.jomc.model.SpecificationReference;
import org.jomc.spi.Invocation;
import org.jomc.spi.Invoker;
import org.jomc.spi.Listener;
import org.jomc.spi.Locator;
import org.jomc.spi.Scope;
import org.jomc.util.WeakIdentityHashMap;
import org.xml.sax.SAXException;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Object management and configuration reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.ObjectManager} {@code 1.0} {@code Singleton}</li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-10-SNAPSHOT/jomc-tools" )
// </editor-fold>
// SECTION-END
public class DefaultObjectManager implements ObjectManager
{
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultObjectManager} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-10-SNAPSHOT/jomc-tools" )
    public DefaultObjectManager()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[ObjectManager]

    public Object getObject( final Class specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        try
        {
            this.initialize();

            final ClassLoader classLoader = getClassLoader( specification );
            final Modules model = this.getModules( classLoader );
            final Specification s = model.getSpecification( specification );

            if ( s == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingSpecificationMessage(
                        specification.getName() ), new Exception() );

                }

                return null;
            }

            Scope scope = null;
            if ( s.getScope() != null )
            {
                scope = this.getScope( classLoader, s.getScope() );

                if ( scope == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingScopeMessage( s.getScope() ), null );
                    }

                    return null;
                }
            }

            final Implementations available = model.getImplementations( s.getIdentifier() );
            if ( available == null || available.getImplementation().isEmpty() )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingImplementationsMessage(
                        specification.getName() ), new Exception() );

                }

                return null;
            }

            if ( s.getMultiplicity() == Multiplicity.ONE )
            {
                final Implementation i = available.getImplementation().get( 0 );

                if ( i.getLocation() != null )
                {
                    if ( s.getClazz() == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingSpecificationClassMessage( s ), new Exception() );
                        }

                        return null;
                    }

                    final Object object = this.getObject(
                        Class.forName( s.getClazz(), true, classLoader ), i.getLocationUri(), classLoader );

                    if ( object == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingObjectMessage(
                                i.getIdentifier(), i.getName() ), new Exception() );

                        }

                        return null;
                    }

                    return object;
                }
                else if ( !i.isAbstract() )
                {
                    final Instance instance = model.getInstance( i.getIdentifier() );
                    if ( instance == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingInstanceMessage(
                                i.getIdentifier(), i.getName() ), new Exception() );

                        }

                        return null;
                    }

                    final Object object = this.getObject( scope, instance, classLoader );
                    if ( object == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingObjectMessage(
                                i.getIdentifier(), i.getName() ), new Exception() );

                        }

                        return null;
                    }

                    return object;
                }
            }
            else if ( s.getMultiplicity() == Multiplicity.MANY )
            {
                final List<Object> list = new ArrayList<Object>( available.getImplementation().size() );

                for ( Implementation i : available.getImplementation() )
                {
                    if ( i.getLocation() != null )
                    {
                        if ( s.getClazz() == null )
                        {
                            if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingSpecificationClassMessage( s ),
                                          new Exception() );

                            }

                            return null;
                        }

                        final Object o = this.getObject(
                            Class.forName( s.getClazz(), true, classLoader ), i.getLocationUri(), classLoader );

                        if ( o == null )
                        {
                            if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingObjectMessage(
                                    i.getIdentifier(), i.getName() ), new Exception() );

                            }
                        }
                        else
                        {
                            list.add( o );
                        }
                    }
                    else if ( !i.isAbstract() )
                    {
                        final Instance instance = model.getInstance( i.getIdentifier() );
                        if ( instance == null )
                        {
                            if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                    i.getIdentifier(), i.getName() ), new Exception() );

                            }

                            return null;
                        }

                        final Object o = this.getObject( scope, instance, classLoader );
                        if ( o == null )
                        {
                            if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingObjectMessage(
                                    i.getIdentifier(), i.getName() ), new Exception() );

                            }
                        }
                        else
                        {
                            list.add( o );
                        }
                    }
                }

                return list.isEmpty()
                       ? null : list.toArray( (Object[]) Array.newInstance( specification, list.size() ) );

            }
            else if ( this.isLoggable( Level.WARNING ) )
            {
                this.log( Level.WARNING, this.getUnsupportedMultiplicityMessage(
                    s.getMultiplicity() ), new Exception() );

            }

            return null;
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
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

        try
        {
            this.initialize();

            final ClassLoader classLoader = getClassLoader( specification );
            final Modules model = this.getModules( classLoader );
            final Specification s = model.getSpecification( specification );

            if ( s == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingSpecificationMessage(
                        specification.getName() ), new Exception() );

                }

                return null;
            }

            Scope scope = null;
            if ( s.getScope() != null )
            {
                scope = this.getScope( classLoader, s.getScope() );

                if ( scope == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingScopeMessage( s.getScope() ), null );
                    }

                    return null;
                }
            }

            final Implementations available = model.getImplementations( s.getIdentifier() );
            if ( available == null || available.getImplementation().isEmpty() )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingImplementationsMessage(
                        specification.getName() ), new Exception() );

                }

                return null;
            }

            final Implementation i = available.getImplementationByName( implementationName );
            if ( i == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingImplementationMessage(
                        implementationName, s.getIdentifier() ), new Exception() );

                }

                return null;
            }

            if ( i.getLocation() != null )
            {
                if ( s.getClazz() == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingSpecificationClassMessage( s ), new Exception() );
                    }

                    return null;
                }

                final Object object = this.getObject(
                    Class.forName( s.getClazz(), true, classLoader ), i.getLocationUri(), classLoader );

                if ( object == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingObjectMessage(
                            i.getIdentifier(), i.getName() ), new Exception() );

                    }

                    return null;
                }

                return object;
            }
            else if ( !i.isAbstract() )
            {
                final Instance instance = model.getInstance( i.getIdentifier() );
                if ( instance == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingInstanceMessage(
                            i.getIdentifier(), i.getName() ), new Exception() );

                    }

                    return null;
                }

                final Object object = this.getObject( scope, instance, classLoader );
                if ( object == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingObjectMessage(
                            i.getIdentifier(), i.getName() ), new Exception() );

                    }

                    return null;
                }

                return object;
            }

            return null;
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
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

        try
        {
            this.initialize();

            final ClassLoader classLoader = getClassLoader( object.getClass() );
            final Modules model = this.getModules( classLoader );
            final Instance instance = model.getInstance( object );

            if ( instance == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingObjectInstanceMessage( object ), new Exception() );
                }

                return null;
            }

            synchronized ( instance )
            {
                final Dependency dependency = instance.getDependencies() != null
                                              ? instance.getDependencies().getDependency( dependencyName ) : null;

                if ( dependency == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingDependencyMessage(
                            dependencyName, instance.getIdentifier() ), new Exception() );

                    }

                    return null;
                }

                Object o = instance.getDependencyObjects().get( dependencyName );
                if ( o == null )
                {
                    final Specification ds = model.getSpecification( dependency.getIdentifier() );
                    if ( ds == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingSpecificationMessage(
                                dependency.getIdentifier() ), new Exception() );

                        }

                        return null;
                    }

                    Scope scope = null;
                    if ( ds.getScope() != null )
                    {
                        scope = this.getScope( classLoader, ds.getScope() );

                        if ( scope == null )
                        {
                            if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingScopeMessage( ds.getScope() ), null );
                            }

                            return null;
                        }
                    }

                    final Implementations available = model.getImplementations( ds.getIdentifier() );
                    if ( available == null || available.getImplementation().isEmpty() )
                    {
                        if ( !dependency.isOptional() && this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingImplementationsMessage(
                                dependency.getIdentifier() ), new Exception() );

                        }

                        return null;
                    }

                    if ( dependency.getImplementationName() != null )
                    {
                        final Implementation i =
                            available.getImplementationByName( dependency.getImplementationName() );

                        if ( i == null )
                        {
                            if ( !dependency.isOptional() && this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingImplementationMessage(
                                    dependency.getImplementationName(), dependency.getIdentifier() ), new Exception() );

                            }

                            return null;
                        }

                        if ( i.getLocation() != null )
                        {
                            if ( ds.getClazz() == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingSpecificationClassMessage( ds ),
                                              new Exception() );

                                }

                                return null;
                            }

                            o = this.getObject(
                                Class.forName( ds.getClazz(), true, classLoader ), i.getLocationUri(), classLoader );

                            if ( o == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        i.getIdentifier(), i.getName() ), new Exception() );

                                }

                                return null;
                            }
                        }
                        else if ( !i.isAbstract() )
                        {
                            final Instance di = model.getInstance( i.getIdentifier(), dependency );
                            if ( di == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                        i.getIdentifier(), i.getName() ), new Exception() );

                                }

                                return null;
                            }

                            o = this.getObject( scope, di, classLoader );
                            if ( o == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        i.getIdentifier(), i.getName() ), new Exception() );

                                }

                                return null;
                            }
                        }
                    }
                    else if ( ds.getMultiplicity() == Multiplicity.ONE )
                    {
                        final Implementation ref = available.getImplementation().get( 0 );
                        if ( ref.getLocation() != null )
                        {
                            if ( ds.getClazz() == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingSpecificationClassMessage( ds ),
                                              new Exception() );
                                }

                                return null;
                            }

                            o = this.getObject(
                                Class.forName( ds.getClazz(), true, classLoader ), ref.getLocationUri(), classLoader );

                            if ( o == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        ref.getIdentifier(), ref.getName() ), new Exception() );

                                }

                                return null;
                            }
                        }
                        else if ( !ref.isAbstract() )
                        {
                            final Instance di = model.getInstance( ref.getIdentifier(), dependency );
                            if ( di == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                        ref.getIdentifier(), ref.getName() ), new Exception() );

                                }

                                return null;
                            }

                            o = this.getObject( scope, di, classLoader );
                            if ( o == null )
                            {
                                if ( this.isLoggable( Level.WARNING ) )
                                {
                                    this.log( Level.WARNING, this.getMissingObjectMessage(
                                        ref.getIdentifier(), ref.getName() ), new Exception() );

                                }

                                return null;
                            }
                        }
                    }
                    else
                    {
                        final List<Object> list = new ArrayList<Object>( available.getImplementation().size() );

                        if ( !available.getImplementation().isEmpty() && ds.getClazz() == null )
                        {
                            if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingSpecificationClassMessage( ds ),
                                          new Exception() );

                            }

                            return null;
                        }

                        for ( Implementation a : available.getImplementation() )
                        {
                            if ( a.getLocation() != null )
                            {
                                final Object o2 = this.getObject( Class.forName( ds.getClazz(), true, classLoader ),
                                                                  a.getLocationUri(), classLoader );

                                if ( o2 == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( Level.WARNING, this.getMissingObjectMessage(
                                            a.getIdentifier(), a.getName() ), new Exception() );

                                    }
                                }
                                else
                                {
                                    list.add( o2 );
                                }
                            }
                            else if ( !a.isAbstract() )
                            {
                                final Instance di = model.getInstance( a.getIdentifier(), dependency );
                                if ( di == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( Level.WARNING, this.getMissingInstanceMessage(
                                            a.getIdentifier(), a.getName() ), new Exception() );

                                    }

                                    return null;
                                }

                                final Object o2 = this.getObject( scope, di, classLoader );
                                if ( o2 == null )
                                {
                                    if ( this.isLoggable( Level.WARNING ) )
                                    {
                                        this.log( Level.WARNING, this.getMissingObjectMessage(
                                            a.getIdentifier(), a.getName() ), new Exception() );

                                    }
                                }
                                else
                                {
                                    list.add( o2 );
                                }
                            }
                        }

                        o = list.isEmpty() ? null : list.toArray( (Object[]) Array.newInstance( Class.forName(
                            ds.getClazz(), true, classLoader ), list.size() ) );

                    }
                }

                if ( o != null && dependency.isBound() )
                {
                    instance.getDependencyObjects().put( dependencyName, o );
                }

                return o;
            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
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

        try
        {
            this.initialize();

            final ClassLoader classLoader = getClassLoader( object.getClass() );
            final Modules model = this.getModules( classLoader );
            final Instance instance = model.getInstance( object );

            if ( instance == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingObjectInstanceMessage( object ), new Exception() );
                }

                return null;
            }

            synchronized ( instance )
            {
                Object value = instance.getPropertyObjects().get( propertyName );
                if ( value == null )
                {
                    final Property property =
                        instance.getProperties() != null ? instance.getProperties().getProperty( propertyName ) : null;

                    if ( property == null )
                    {
                        if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingPropertyMessage(
                                propertyName, object.getClass().getName() ), new Exception() );

                        }

                        return null;
                    }

                    value = property.getJavaValue( classLoader );
                    if ( value != null )
                    {
                        instance.getPropertyObjects().put( propertyName, value );
                    }
                }

                return value;
            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
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

        try
        {
            this.initialize();

            final ClassLoader classLoader = getClassLoader( object.getClass() );
            final Modules model = this.getModules( classLoader );
            final Instance instance = model.getInstance( object );

            if ( instance == null )
            {
                if ( this.isLoggable( Level.WARNING ) )
                {
                    this.log( Level.WARNING, this.getMissingObjectInstanceMessage( object ), new Exception() );
                }

                return null;
            }

            synchronized ( instance )
            {
                final Message message =
                    instance.getMessages() != null ? instance.getMessages().getMessage( messageName ) : null;

                if ( message == null || message.getTemplate() == null )
                {
                    if ( this.isLoggable( Level.WARNING ) )
                    {
                        this.log( Level.WARNING, this.getMissingMessageMessage(
                            messageName, object.getClass().getName() ), new Exception() );

                    }

                    return null;
                }

                final MessageFormat fmt = new MessageFormat( message.getTemplate().getText(
                    locale.getLanguage().toLowerCase( Locale.ENGLISH ) ).getValue(), locale );

                return fmt.format( arguments );
            }
        }
        catch ( final Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
    }

    // SECTION-END
    // SECTION-START[DefaultObjectManager]
    /** Constant for the {@code Singleton} scope identifier. */
    protected static final String SINGLETON_SCOPE_IDENTIFIER = "Singleton";

    /**
     * Log level events are logged at by default.
     * @see #getDefaultLogLevel()
     */
    private static final Level DEFAULT_LOG_LEVEL = Level.WARNING;

    /** Default log level. */
    private static volatile Level defaultLogLevel;

    /** Name of the platform's bootstrap class loader class. */
    private static volatile String bootstrapClassLoaderClassName;

    private static volatile boolean bootstrapClassLoaderClassNameInitialized;

    /** {@code ClassLoader} instance representing the bootstrap class loader. */
    private static final ClassLoader BOOTSTRAP_CLASSLOADER = new ClassLoader( null )
    {
    };

    /** Listeners of the instance. */
    private List<Listener> listeners;

    /** Flag indicating that initialization has been performed. */
    private boolean initialized;

    /** Log level of the instance. */
    private Level logLevel;

    /** Modules of the instance. */
    private final Map<ClassLoader, Modules> modules = new WeakIdentityHashMap();

    /** Invokers of the instance. */
    private final Map<ClassLoader, Invoker> invokers = new WeakIdentityHashMap();

    /** Scopes of the instance. */
    private final Map<ClassLoader, Map<String, Scope>> scopes = new WeakIdentityHashMap();

    /** Locators of the instance. */
    private final Map<ClassLoader, Map<String, Locator>> locators = new WeakIdentityHashMap();

    /** Objects of the instance. */
    private final Map<ClassLoader, Map<Object, Instance>> objects = new WeakIdentityHashMap();

    /** {@code ObjectManager} singletons. */
    private static final Map<ClassLoader, ObjectManager> singletons = new WeakIdentityHashMap();

    /**
     * Default {@link ObjectManagerFactory#getObjectManager(ClassLoader)} implementation.
     *
     * @return The default {@code ObjectManager} singleton instance.
     *
     * @see ObjectManagerFactory#getObjectManager(ClassLoader)
     */
    public static ObjectManager getObjectManager( final ClassLoader classLoader )
    {
        synchronized ( singletons )
        {
            final ClassLoader singletonsLoader = getClassLoader( classLoader );
            ObjectManager manager = singletons.get( singletonsLoader );
            if ( manager == null )
            {
                manager = ObjectManagerFactory.newObjectManager( classLoader );
                singletons.put( singletonsLoader, manager );
            }

            return (ObjectManager) manager.getObject( ObjectManager.class );
        }
    }

    /**
     * Gets the list of registered listeners.
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make
     * to the returned list will be present inside the object. This is why there is no {@code set} method for the
     * listeners property.</p>
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
     * Gets the default log level events are logged at.
     * <p>The default log level is controlled by system property
     * {@code org.jomc.ri.DefaultObjectManager.defaultLogLevel} holding the log level to log events at by default.
     * If that property is not set, the {@code WARNING} default is returned.</p>
     *
     * @return The log level events are logged at by default.
     *
     * @see #getLogLevel()
     * @see Level#parse(java.lang.String)
     */
    public static Level getDefaultLogLevel()
    {
        if ( defaultLogLevel == null )
        {
            defaultLogLevel = Level.parse( System.getProperty( "org.jomc.ri.DefaultObjectManager.defaultLogLevel",
                                                               DEFAULT_LOG_LEVEL.getName() ) );

        }

        return defaultLogLevel;
    }

    /**
     * Sets the default log level events are logged at.
     *
     * @param value The new default level events are logged at or {@code null}.
     *
     * @see #getDefaultLogLevel()
     */
    public static void setDefaultLogLevel( final Level value )
    {
        defaultLogLevel = value;
    }

    /**
     * Gets the log level of the instance.
     *
     * @return The log level of the instance.
     *
     * @see #getDefaultLogLevel()
     * @see #setLogLevel(java.util.logging.Level)
     * @see #isLoggable(java.util.logging.Level)
     */
    public Level getLogLevel()
    {
        if ( this.logLevel == null )
        {
            this.logLevel = getDefaultLogLevel();
            this.log( Level.CONFIG, this.getMessage( "defaultLogLevelInfo", new Object[]
                {
                    this.getClass().getCanonicalName(), this.logLevel.getLocalizedName()
                } ), null );

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
     * Gets the name of the platform's bootstrap class loader class.
     * <p>The name of the platform's bootstrap class loader class is controlled by system property
     * {@code org.jomc.ri.DefaultObjectManager.bootstrapClassLoaderClassName} holding the name of the platform's
     * bootstrap class loader class. If that property is not set, the bootstrap class loader is assumed to be
     * represented by a {@code null} parent class loader.</p>
     *
     * @return The name of the platform's bootstrap class loader class or {@code null}.
     *
     * @see #getClassLoader(java.lang.ClassLoader)
     */
    public static String getBootstrapClassLoaderClassName()
    {
        if ( bootstrapClassLoaderClassName == null && !bootstrapClassLoaderClassNameInitialized )
        {
            bootstrapClassLoaderClassName =
                System.getProperty( "org.jomc.ri.DefaultObjectManager.bootstrapClassLoaderClassName" );

            bootstrapClassLoaderClassNameInitialized = true;
        }

        return bootstrapClassLoaderClassName;
    }

    /**
     * Sets the name of the platform's bootstrap class loader class.
     *
     * @param value The new name of the platform's bootstrap class loader class or {@code null}.
     *
     * @see #getBootstrapClassLoaderClassName()
     */
    public static void setBootstrapClassLoaderClassName( final String value )
    {
        bootstrapClassLoaderClassName = value;
        bootstrapClassLoaderClassNameInitialized = false;
    }

    /**
     * Gets the modules of a given class loader.
     *
     * @param classLoader The class loader to get the modules of.
     *
     * @return The modules of the given class loader.
     *
     * @throws NullPointerException if {@code classLoader} is {@code null},
     */
    public Modules getModules( final ClassLoader classLoader )
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        synchronized ( this.modules )
        {
            Modules cachedModules = this.modules.get( classLoader );

            if ( cachedModules == null )
            {
                try
                {
                    final DefaultModelManager defaultModelManager = new DefaultModelManager();
                    defaultModelManager.setLogLevel( this.getLogLevel() );
                    defaultModelManager.getListeners().add( new DefaultModelManager.Listener()
                    {

                        public void onLog( final Level level, final String message, final Throwable t )
                        {
                            log( level, message, t );
                        }

                    } );

                    final ObjectFactory objectFactory = new ObjectFactory();
                    final JAXBContext context = defaultModelManager.getContext( classLoader );
                    final Schema schema = defaultModelManager.getSchema( classLoader );

                    cachedModules = defaultModelManager.getClasspathModules(
                        classLoader, DefaultModelManager.getDefaultModuleLocation() );

                    final Map<String, Class<ModelProvider>> providers =
                        new TreeMap<String, Class<ModelProvider>>( new Comparator<String>()
                    {

                        public int compare( final String key1, final String key2 )
                        {
                            return key1.compareTo( key2 );
                        }

                    } );

                    final File platformProviders = new File( new StringBuilder().append(
                        System.getProperty( "java.home" ) ).append( File.separator ).append( "jre" ).
                        append( File.separator ).append( "lib" ).append( File.separator ).append( "jomc.properties" ).
                        toString() );

                    if ( platformProviders.exists() )
                    {
                        if ( this.isLoggable( Level.CONFIG ) )
                        {
                            this.log( Level.CONFIG, this.getMessage( "processing", new Object[]
                                {
                                    platformProviders.getAbsolutePath()
                                } ), null );

                        }

                        InputStream in = null;
                        final java.util.Properties p = new java.util.Properties();

                        try
                        {
                            in = new FileInputStream( platformProviders );
                            p.load( in );
                        }
                        finally
                        {
                            if ( in != null )
                            {
                                in.close();
                            }
                        }

                        for ( Map.Entry e : p.entrySet() )
                        {
                            if ( e.getKey().toString().startsWith( "org.jomc.model.ModelProvider." ) )
                            {
                                providers.put( e.getKey().toString(), (Class<ModelProvider>) Class.forName(
                                    e.getValue().toString(), true, classLoader ) );

                            }
                        }
                    }

                    final Enumeration<URL> serviceProviders =
                        classLoader.getResources( "META-INF/services/org.jomc.model.ModelProvider" );

                    if ( serviceProviders != null )
                    {
                        while ( serviceProviders.hasMoreElements() )
                        {
                            String line;
                            final URL serviceProvider = serviceProviders.nextElement();

                            if ( this.isLoggable( Level.CONFIG ) )
                            {
                                this.log( Level.CONFIG, this.getMessage( "processing", new Object[]
                                    {
                                        serviceProvider.toExternalForm()
                                    } ), null );

                            }

                            BufferedReader reader = null;
                            try
                            {
                                reader = new BufferedReader( new InputStreamReader(
                                    serviceProvider.openStream(), "UTF-8" ) );

                                while ( ( line = reader.readLine() ) != null )
                                {
                                    if ( line.contains( "#" ) )
                                    {
                                        continue;
                                    }

                                    providers.put( "org.jomc.model.ModelProvider." + providers.size(),
                                                   (Class<ModelProvider>) Class.forName( line, true, classLoader ) );

                                }
                            }
                            finally
                            {
                                if ( reader != null )
                                {
                                    reader.close();
                                }
                            }
                        }
                    }

                    for ( Class<ModelProvider> provider : providers.values() )
                    {
                        if ( this.isLoggable( Level.CONFIG ) )
                        {
                            this.log( Level.CONFIG, this.getMessage( "modelProviderInfo", new Object[]
                                {
                                    provider.getName()
                                } ), null );

                        }

                        final ModelProvider modelProvider = provider.newInstance();
                        final Modules providerModules = modelProvider.getModules( classLoader, cachedModules );
                        if ( providerModules != null )
                        {
                            cachedModules.getModule().addAll( providerModules.getModule() );
                        }
                    }

                    final Module classpathModule =
                        cachedModules.getClasspathModule( Modules.getDefaultClasspathModuleName(), classLoader );

                    if ( classpathModule != null )
                    {
                        cachedModules.getModule().add( classpathModule );
                    }

                    final List<Transformer> defaultTransformers = defaultModelManager.getClasspathTransformers(
                        classLoader, DefaultModelManager.getDefaultTransformerLocation() );

                    for ( Transformer t : defaultTransformers )
                    {
                        final JAXBElement<Modules> e = objectFactory.createModules( cachedModules );
                        final JAXBSource source = new JAXBSource( context, e );
                        final JAXBResult result = new JAXBResult( context );
                        t.transform( source, result );
                        cachedModules = ( (JAXBElement<Modules>) result.getResult() ).getValue();
                    }

                    final ModelObjectValidator modelObjectValidator = new DefaultModelObjectValidator();
                    final ModelObjectValidationReport validationReport = modelObjectValidator.validateModules(
                        objectFactory.createModules( cachedModules ), context, schema );

                    for ( ModelObjectValidationReport.Detail d : validationReport.getDetails() )
                    {
                        if ( this.isLoggable( d.getLevel() ) )
                        {
                            this.log( d.getLevel(), d.getMessage(), null );
                        }
                    }

                    if ( validationReport.isModelObjectValid() )
                    {
                        final ClassLoader objectsLoader = getClassLoader( classLoader );
                        Map<Object, Instance> objectMap = this.objects.get( objectsLoader );
                        if ( objectMap == null )
                        {
                            objectMap = new WeakIdentityHashMap();
                            this.objects.put( objectsLoader, objectMap );
                        }

                        this.modules.put( classLoader, new Modules( cachedModules, objectMap ) );

                        if ( this.isLoggable( Level.FINE ) )
                        {
                            this.log( Level.FINE, this.getModulesReport( cachedModules, classLoader ), null );
                        }
                    }
                    else
                    {
                        cachedModules = null;
                    }
                }
                catch ( final ClassNotFoundException e )
                {
                    if ( this.isLoggable( Level.SEVERE ) )
                    {
                        this.log( Level.SEVERE, e.getMessage(), e );
                    }

                    cachedModules = null;
                }
                catch ( final InstantiationException e )
                {
                    if ( this.isLoggable( Level.SEVERE ) )
                    {
                        this.log( Level.SEVERE, e.getMessage(), e );
                    }

                    cachedModules = null;
                }
                catch ( final IllegalAccessException e )
                {
                    if ( this.isLoggable( Level.SEVERE ) )
                    {
                        this.log( Level.SEVERE, e.getMessage(), e );
                    }

                    cachedModules = null;
                }
                catch ( final TransformerException e )
                {
                    if ( this.isLoggable( Level.SEVERE ) )
                    {
                        this.log( Level.SEVERE, e.getMessage(), e );
                    }

                    cachedModules = null;
                }
                catch ( final IOException e )
                {
                    if ( this.isLoggable( Level.SEVERE ) )
                    {
                        this.log( Level.SEVERE, e.getMessage(), e );
                    }

                    cachedModules = null;
                }
                catch ( final SAXException e )
                {
                    if ( this.isLoggable( Level.SEVERE ) )
                    {
                        this.log( Level.SEVERE, e.getMessage(), e );
                    }

                    cachedModules = null;
                }
                catch ( final JAXBException e )
                {
                    if ( this.isLoggable( Level.SEVERE ) )
                    {
                        this.log( Level.SEVERE, e.getMessage(), e );
                    }

                    cachedModules = null;
                }
                finally
                {
                    if ( cachedModules == null )
                    {
                        cachedModules = new Modules();
                    }
                }
            }

            return cachedModules;
        }
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
    public static ClassLoader getClassLoader( final Class clazz )
    {
        if ( clazz == null )
        {
            throw new NullPointerException( "clazz" );
        }

        ClassLoader cl = clazz.getClassLoader();
        if ( cl == null )
        {
            cl = BOOTSTRAP_CLASSLOADER;
        }

        return cl;
    }

    /**
     * Gets the parent class loader of a given class loader recursively.
     * <p>This method recursively finds the parent class loader of the given class loader. Recursion stops at the
     * platform's bootstrap class loader. That class loader is detected when either the current class loader has no
     * parent (a call to the {@code getParent()} method returns {@code null}) or when the class name of the
     * current class loader's parent class loader is equal to the name returned by method
     * {@code getBootstrapClassLoaderClassName()}. Configuration of the name of the platform's bootstrap class loader
     * class is needed when the platform's {@code getParent()} method of the {@code ClassLoader} class does not return
     * {@code null} to indicate the bootstrap class loader but instead returns an instance of {@code ClassLoader}.</p>
     *
     * @param classLoader The class loader whose parent class loader to return or {@code null} to return a
     * {@code ClassLoader} instance representing the platform's bootstrap class loader.
     *
     * @return The parent class loader of {@code classLoader}.
     *
     * @throws NullPointerException if {@code classLoader} is {@code null}.
     *
     * @see #getBootstrapClassLoaderClassName()
     * @see ClassLoader#getParent()
     */
    public static ClassLoader getClassLoader( final ClassLoader classLoader )
    {
        if ( classLoader == null )
        {
            return BOOTSTRAP_CLASSLOADER;
        }

        if ( classLoader.getParent() != null &&
             !classLoader.getParent().getClass().getName().equals( getBootstrapClassLoaderClassName() ) )
        {
            return getClassLoader( classLoader.getParent() );
        }

        return classLoader;
    }

    /**
     * Gets an object of a given instance from a given scope.
     *
     * @param scope The scope to get the object from or {@code null}.
     * @param instance The instance of the object to get.
     * @param classLoader The class loader to use for creating the object.
     *
     * @return An object of {@code instance} from {@code scope} or {@code null} if no such object is found.
     *
     * @throws NullPointerException if {@code instance} or {@code classLoader} is {@code null}.
     * @throws InstantiationException if creating an object fails.
     */
    public Object getObject( final Scope scope, final Instance instance, final ClassLoader classLoader )
        throws InstantiationException
    {
        if ( instance == null )
        {
            throw new NullPointerException( "instance" );
        }
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        Object object = null;
        final Modules model = this.getModules( classLoader );

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
                        object = model.createObject( instance, classLoader );
                    }
                    finally
                    {
                        if ( object != null )
                        {
                            object = this.createProxy( instance, object );
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
            try
            {
                object = model.createObject( instance, classLoader );
            }
            finally
            {
                if ( object != null )
                {
                    object = this.createProxy( instance, object );
                }
            }
        }

        return object;
    }

    /**
     * Gets an object for a given location URI.
     *
     * @param specification The specification class of the object to locate.
     * @param location The location URI of the object to locate.
     * @param classLoader The class loader to use for loading locator classes.
     * @param <T> The type of the object.
     *
     * @return An object located at {@code location} or {@code null} if no such object is found.
     *
     * @throws NullPointerException if {@code specification}, {@code location} or {@code classLoader} is {@code null}.
     * @throws InstantiationException if instantiating a locator fails.
     * @throws ClassNotFoundException if the class of {@code specification} is not found.
     * @throws IOException if locating the object fails.
     */
    public <T> T getObject( final Class<T> specification, final URI location, final ClassLoader classLoader )
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

        T object = null;
        final Locator locator = this.getLocator( classLoader, location );

        if ( locator != null )
        {
            object = locator.getObject( specification, location );
        }
        else if ( this.isLoggable( Level.WARNING ) )
        {
            this.log( Level.WARNING, this.getMissingLocatorMessage( location ), new Exception() );
        }

        return object;
    }

    /**
     * Gets the scope implementation for a given scope identifier.
     *
     * @param classLoader The class loader to use for loading scope implementations.
     * @param identifier The identifier of the scope to get an implementation of.
     *
     * @return The implementation of the scope identified by {@code identifier} or {@code null} if no such
     * scope implementation is found.
     *
     * @throws NullPointerException if {@code classLoader} or {@code identifier} is {@code null}.
     * @throws InstantiationException if instantiating a scope fails.
     *
     * @see #getDefaultScope(java.lang.String)
     */
    public Scope getScope( final ClassLoader classLoader, final String identifier ) throws InstantiationException
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }
        if ( identifier == null )
        {
            throw new NullPointerException( "identifier" );
        }

        final Modules model = this.getModules( classLoader );
        final ClassLoader scopesLoader = getClassLoader( classLoader );

        synchronized ( this.scopes )
        {
            Map<String, Scope> cachedScopes = this.scopes.get( scopesLoader );
            if ( cachedScopes == null )
            {
                cachedScopes = new HashMap();
                this.scopes.put( scopesLoader, cachedScopes );
            }

            Scope scope = cachedScopes.get( identifier );

            if ( scope == null )
            {
                // Bootstrap scope loading.
                final Specification scopeSpecification = model.getSpecification( Scope.class );

                if ( scopeSpecification != null )
                {
                    final Implementations implementations =
                        model.getImplementations( scopeSpecification.getIdentifier() );

                    if ( implementations != null )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            if ( identifier.equals( i.getName() ) )
                            {
                                final Instance instance = model.getInstance( i.getIdentifier() );

                                if ( instance != null )
                                {
                                    scope = (Scope) model.createObject( instance, classLoader );
                                    cachedScopes.put( identifier, scope );
                                    if ( this.isLoggable( Level.CONFIG ) )
                                    {
                                        this.log( Level.CONFIG, this.getMessage( "scopeInfo", new Object[]
                                            {
                                                i.getIdentifier(), identifier, scopesLoader.toString()
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
                scope = this.getDefaultScope( identifier );
                if ( scope != null )
                {
                    cachedScopes.put( identifier, scope );
                    if ( this.isLoggable( Level.FINE ) )
                    {
                        this.log( Level.FINE, this.getDefaultScopeInfoMessage( identifier, scopesLoader ), null );
                    }
                }
            }

            return scope;
        }
    }

    /**
     * Gets the default scope implementation for a given identifier.
     *
     * @param identifier The identifier of the scope to get a default implementation of.
     *
     * @return The default implementation of the scope identified by {@code identifier} or {@code null} if no such
     * default implementation is available.
     *
     * @throws NullPointerException if {@code identifier} is {@code null}.
     *
     * @see #getScope(java.lang.ClassLoader, java.lang.String)
     */
    public Scope getDefaultScope( final String identifier )
    {
        if ( identifier == null )
        {
            throw new NullPointerException( "identifier" );
        }

        DefaultScope defaultScope = null;

        if ( identifier.equals( SINGLETON_SCOPE_IDENTIFIER ) )
        {
            defaultScope = new DefaultScope( new HashMap<String, Object>() );
        }

        return defaultScope;
    }

    /**
     * Gets a locator to use with a given location URI.
     *
     * @param classLoader The class loader to use for loading locator implementations.
     * @param location The location URI to get a locator for.
     *
     * @return The locator to use for locating objects at {@code location} or {@code null} if no such locator is
     * available.
     *
     * @throws NullPointerException if {@code classLoader} or {@code location} is {@code null}.
     * @throws InstantiationException if instantiating a locator fails.
     *
     * @see #getDefaultLocator(java.net.URI)
     */
    public Locator getLocator( final ClassLoader classLoader, final URI location ) throws InstantiationException
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        final String scheme = location.getScheme();

        if ( scheme != null )
        {
            final Modules model = this.getModules( classLoader );
            final ClassLoader locatorsLoader = getClassLoader( classLoader );

            synchronized ( this.locators )
            {
                Map<String, Locator> cachedLocators = this.locators.get( locatorsLoader );
                if ( cachedLocators == null )
                {
                    cachedLocators = new HashMap();
                    this.locators.put( locatorsLoader, cachedLocators );
                }

                Locator locator = cachedLocators.get( scheme );

                if ( locator == null )
                {
                    // Bootstrap locator loading.
                    final Specification locatorSpecification = model.getSpecification( Locator.class );

                    if ( locatorSpecification != null )
                    {
                        final Implementations implementations =
                            model.getImplementations( locatorSpecification.getIdentifier() );

                        if ( implementations != null )
                        {
                            for ( Implementation i : implementations.getImplementation() )
                            {
                                if ( scheme.equals( i.getName() ) )
                                {
                                    final Instance instance = model.getInstance( i.getIdentifier() );

                                    if ( instance != null )
                                    {
                                        locator = (Locator) model.createObject( instance, classLoader );
                                        cachedLocators.put( scheme, locator );

                                        if ( this.isLoggable( Level.CONFIG ) )
                                        {
                                            this.log( Level.CONFIG, this.getMessage( "locatorInfo", new Object[]
                                                {
                                                    i.getIdentifier(), scheme, locatorsLoader.toString()
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
                        cachedLocators.put( scheme, locator );
                        if ( this.isLoggable( Level.FINE ) )
                        {
                            this.log( Level.FINE, this.getDefaultLocatorInfoMessage( scheme, locatorsLoader ), null );
                        }
                    }
                }

                return locator;
            }
        }

        return null;
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
     * @see #getLocator(java.lang.ClassLoader, java.net.URI)
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
     * Gets the invoker of the given class loader.
     *
     * @param classLoader The class loader to use for loading invoker implementations.
     *
     * @return The invoker of the given class loader.
     *
     * @throws NullPointerException if {@code classLoader} is {@code null}.
     * @throws InstantiationException if instantiating a new invoker fails.
     */
    public Invoker getInvoker( final ClassLoader classLoader ) throws InstantiationException
    {
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        final Modules model = this.getModules( classLoader );
        final ClassLoader invokersLoader = getClassLoader( classLoader );

        synchronized ( this.invokers )
        {
            Invoker invoker = this.invokers.get( invokersLoader );

            if ( invoker == null )
            {
                final Specification invokerSpecification = model.getSpecification( Invoker.class );

                if ( invokerSpecification != null )
                {
                    final Implementations implementations =
                        model.getImplementations( invokerSpecification.getIdentifier() );

                    if ( implementations != null && !implementations.getImplementation().isEmpty() )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            if ( invoker == null )
                            {
                                final Instance invokerInstance = model.getInstance( i.getIdentifier() );

                                if ( invokerInstance != null )
                                {
                                    invoker = (Invoker) model.createObject( invokerInstance, classLoader );
                                    this.invokers.put( invokersLoader, invoker );

                                    if ( this.isLoggable( Level.CONFIG ) )
                                    {
                                        this.log( Level.CONFIG, this.getMessage( "invokerInfo", new Object[]
                                            {
                                                i.getIdentifier(), invokersLoader.toString()
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

                if ( invoker == null )
                {
                    invoker = new DefaultInvoker();
                    this.invokers.put( invokersLoader, invoker );
                    if ( this.isLoggable( Level.FINE ) )
                    {
                        this.log( Level.FINE, this.getMessage( "defaultInvokerInfo", new Object[]
                            {
                                invokersLoader.toString()
                            } ), null );

                    }
                }
            }

            return invoker;
        }
    }

    /**
     * Gets an invocation for a given object, instance, method and arguments.
     *
     * @param object The object to invoke.
     * @param instance The instance of the object to invoke.
     * @param method The method to invoke on {@code object}.
     * @param arguments The arguments of the invocation or {@code null}.
     *
     * @return An invocation with {@code object}, {@code instance}, {@code method} and {@code arguments}.
     *
     * @throws NullPointerException if {@code object}, {@code instance} or {@code method} is {@code null}.
     * @throws InstantiationException if instantiating a new invocation fails.
     */
    public Invocation getInvocation( final Object object, final Instance instance, final Method method,
                                     final Object[] arguments ) throws InstantiationException
    {
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }
        if ( instance == null )
        {
            throw new NullPointerException( "instance" );
        }
        if ( method == null )
        {
            throw new NullPointerException( "method" );
        }

        Invocation invocation = null;
        final ClassLoader classLoader = getClassLoader( object.getClass() );
        final Modules model = this.getModules( classLoader );
        final Specification invocationSpecification = model.getSpecification( Invocation.class );

        if ( invocationSpecification != null )
        {
            final Implementations implementations =
                model.getImplementations( invocationSpecification.getIdentifier() );

            if ( implementations != null && !implementations.getImplementation().isEmpty() )
            {
                for ( Implementation i : implementations.getImplementation() )
                {
                    if ( invocation == null )
                    {
                        final Instance invocationInstance = model.getInstance( i.getIdentifier() );

                        if ( invocationInstance != null )
                        {
                            invocation = (Invocation) model.createObject( invocationInstance, classLoader );
                        }
                        else if ( this.isLoggable( Level.WARNING ) )
                        {
                            this.log( Level.WARNING, this.getMissingInstanceMessage(
                                i.getIdentifier(), i.getName() ), new Exception() );

                        }
                    }
                    else if ( this.isLoggable( Level.FINE ) )
                    {
                        this.log( Level.FINE, this.getMessage( "ignoredInvocation", new Object[]
                            {
                                i.getIdentifier()
                            } ), null );

                    }
                }
            }
        }
        else if ( this.isLoggable( Level.WARNING ) )
        {
            this.log( Level.WARNING, this.getMissingSpecificationMessage( Invocation.class.getName() ),
                      new Exception() );

        }

        if ( invocation == null )
        {
            invocation = new DefaultInvocation();
        }

        invocation.getContext().put( DefaultInvocation.OBJECT_KEY, object );
        invocation.getContext().put( DefaultInvocation.METHOD_KEY, method );
        invocation.getContext().put( DefaultInvocation.ARGUMENTS_KEY, arguments );
        invocation.getContext().put( DefaultInvocation.INSTANCE_KEY, instance );
        return invocation;
    }

    /**
     * Initializes the instance.
     * <p>This method is called once on first usage of a new instance.</p>
     *
     * @throws InstantiationException if initialization fails.
     */
    public synchronized void initialize() throws InstantiationException
    {
        if ( !this.initialized )
        {
            final List<LogRecord> bootstrapLogRecords = new LinkedList<LogRecord>();

            try
            {
                final long t0 = System.currentTimeMillis();
                this.initialized = true;

                this.listeners = null;
                this.modules.clear();
                this.invokers.clear();
                this.locators.clear();
                this.scopes.clear();

                Listener bootstrapListener = new Listener()
                {

                    public void onLog( final Level level, final String message, final Throwable throwable )
                    {
                        final LogRecord record = new LogRecord( level, message );
                        record.setThrown( throwable );
                        bootstrapLogRecords.add( record );
                    }

                };
                this.getListeners().add( bootstrapListener );

                final ClassLoader classLoader = getClassLoader( this.getClass() );
                final Modules model = this.getModules( classLoader );
                final Specification objectManager = model.getSpecification( ObjectManager.class );
                if ( objectManager == null )
                {
                    throw new InstantiationException( this.getMissingSpecificationMessage(
                        ObjectManager.class.getName() ) );

                }

                final Instance thisInstance = model.getInstance( this );
                if ( thisInstance == null )
                {
                    throw new InstantiationException( this.getMissingInstanceMessage(
                        this.getClass().getName(), this.getArtifactNameMessage() ) );

                }

                if ( objectManager.getScope() != null )
                {
                    final Scope scope = this.getScope( classLoader, objectManager.getScope() );
                    if ( scope == null )
                    {
                        throw new InstantiationException( this.getMissingScopeMessage( objectManager.getScope() ) );
                    }

                    scope.putObject( thisInstance.getIdentifier(), this );
                }

                // Bootstrap listener loading.
                final Specification listenerSpecification = model.getSpecification( Listener.class );

                if ( listenerSpecification != null )
                {
                    final Implementations implementations =
                        model.getImplementations( listenerSpecification.getIdentifier() );

                    if ( implementations != null && !implementations.getImplementation().isEmpty() )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            final Instance listenerInstance = model.getInstance( i.getIdentifier() );
                            if ( listenerInstance != null )
                            {
                                final Listener l = (Listener) model.createObject( listenerInstance, classLoader );
                                this.getListeners().add( l );
                                this.log( Level.CONFIG, this.getRegisteredListenerMessage(
                                    l.getClass().getName() ), null );

                            }
                            else if ( this.isLoggable( Level.WARNING ) )
                            {
                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                    i.getIdentifier(), i.getName() ), null );

                            }
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
                    this.log( Level.WARNING, this.getMissingSpecificationMessage(
                        Listener.class.getName() ), new Exception() );

                }

                this.getListeners().remove( bootstrapListener );
                bootstrapListener = null;

                if ( !this.getListeners().isEmpty() )
                {
                    for ( LogRecord logRecord : bootstrapLogRecords )
                    {
                        this.log( logRecord.getLevel(), logRecord.getMessage(), logRecord.getThrown() );
                    }
                }

                if ( this.isLoggable( Level.FINE ) )
                {
                    this.log( Level.FINE, this.getImplementationInfoMessage(
                        Long.valueOf( System.currentTimeMillis() - t0 ) ), null );

                }
            }
            catch ( final InstantiationException e )
            {
                Throwable cause = e;
                if ( !bootstrapLogRecords.isEmpty() )
                {
                    for ( LogRecord r : bootstrapLogRecords )
                    {
                        if ( r.getLevel().intValue() > Level.WARNING.intValue() )
                        {
                            if ( r.getMessage() != null )
                            {
                                cause = new IllegalStateException( r.getMessage() ).initCause( cause );
                            }
                            if ( r.getThrown() != null )
                            {
                                cause = new IllegalStateException( r.getThrown().toString() ).initCause( cause );
                            }
                        }
                    }
                }

                this.listeners = null;
                this.modules.clear();
                this.invokers.clear();
                this.locators.clear();
                this.scopes.clear();
                this.initialized = false;

                throw (InstantiationException) new InstantiationException( cause.getMessage() ).initCause( cause );
            }
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
     * @param instance The instance of {@code object}.
     * @param object The object to create a proxy for.
     *
     * @return A proxy for {@code object}.
     *
     * @throws InstantiationException if creating a proxy fails.
     */
    private Object createProxy( final Instance instance, final Object object ) throws InstantiationException
    {
        try
        {
            final ClassLoader classLoader = getClassLoader( object.getClass() );
            final Set<Class> interfaces = new HashSet<Class>();
            boolean canProxy = instance.getSpecifications() != null;

            if ( canProxy )
            {
                for ( Specification s : instance.getSpecifications().getSpecification() )
                {
                    if ( s.getClazz() != null )
                    {
                        final Class clazz = Class.forName( s.getClazz(), true, classLoader );

                        if ( !clazz.isInterface() )
                        {
                            canProxy = false;
                            break;
                        }

                        interfaces.add( clazz );
                    }
                }
            }

            if ( canProxy && !interfaces.isEmpty() )
            {
                return Proxy.newProxyInstance( classLoader, interfaces.toArray( new Class[ interfaces.size() ] ),
                                               new java.lang.reflect.InvocationHandler()
                {

                    public Object invoke( final Object proxy, final Method method, final Object[] args )
                        throws Throwable
                    {
                        return getInvoker( classLoader ).invoke( getInvocation( object, instance, method, args ) );
                    }

                } );

            }

            return object;
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

    private String getDefaultScopeInfoMessage( final String modelScope, final ClassLoader classLoader )
    {
        return this.getMessage( "defaultScopeInfo", new Object[]
            {
                modelScope, classLoader.toString()
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
        return this.getMessage( "listenerInfo", new Object[]
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

    private String getDefaultLocatorInfoMessage( final String scheme, final ClassLoader classLoader )
    {
        return this.getMessage( "defaultLocatorInfo", new Object[]
            {
                scheme, classLoader.toString()
            } );

    }

    private String getMissingLocatorMessage( final URI location )
    {
        return this.getMessage( "missingLocator", new Object[]
            {
                location.toString()
            } );

    }

    private String getMissingSpecificationClassMessage( final Specification specification )
    {
        return this.getMessage( "missingSpecificationClass", new Object[]
            {
                specification.getIdentifier()
            } );

    }

    private String getModulesReport( final Modules mods, final ClassLoader classLoader )
    {
        final StringBuilder modulesInfo = new StringBuilder();
        final String lineSeparator = System.getProperty( "line.separator" );

        modulesInfo.append( classLoader );

        if ( mods.getDocumentation() != null )
        {
            modulesInfo.append( " - " ).append( mods.getDocumentation().getText(
                Locale.getDefault().getLanguage() ).getValue() );

        }

        modulesInfo.append( lineSeparator );

        for ( Module m : mods.getModule() )
        {
            modulesInfo.append( "\tM:" ).append( m.getName() );

            if ( m.getVersion() != null )
            {
                modulesInfo.append( "|Version:" ).append( m.getVersion() );
            }
            if ( m.getVendor() != null )
            {
                modulesInfo.append( "|Vendor:" ).append( m.getVendor() );
            }

            modulesInfo.append( lineSeparator );

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
                            this.appendImplementationInfo( i, modulesInfo ).append( "|Module:" ).
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
                                mods.getImplementation( r.getIdentifier() ), modulesInfo ).append( "|Module:" ).
                                append( mods.getModuleOfImplementation( r.getIdentifier() ).getName() ).
                                append( lineSeparator );

                        }
                    }
                    if ( i.getSpecifications() != null )
                    {
                        for ( SpecificationReference s : i.getSpecifications().getReference() )
                        {
                            modulesInfo.append( "\t\t\tS:" ).append( s.getIdentifier() );

                            if ( s.getVersion() != null )
                            {
                                modulesInfo.append( "|Version:" ).append( s.getVersion() );
                            }

                            modulesInfo.append( "|Module:" ).append( mods.getModuleOfSpecification(
                                s.getIdentifier() ).getName() ).append( lineSeparator );

                        }
                    }

                    if ( i.getDependencies() != null )
                    {
                        for ( Dependency d : i.getDependencies().getDependency() )
                        {
                            modulesInfo.append( "\t\t\tD:" ).append( d.getName() ).append( "|Identifier:" ).
                                append( d.getIdentifier() );

                            if ( d.getImplementationName() != null )
                            {
                                modulesInfo.append( "|Name:" ).append( d.getImplementationName() );
                            }

                            modulesInfo.append( "|Module:" ).append( mods.getModuleOfSpecification(
                                d.getIdentifier() ).getName() ).append( lineSeparator );

                            final Implementations available = mods.getImplementations( d.getIdentifier() );

                            if ( available != null )
                            {
                                for ( Implementation di : available.getImplementation() )
                                {
                                    modulesInfo.append( "\t\t\t\t" );
                                    this.appendImplementationInfo( di, modulesInfo ).append( "|Module:" ).
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
                            modulesInfo.append( "\t\t\tM:" ).append( msg.getName() ).append( "|Text:" ).
                                append( msg.getTemplate().getText( Locale.getDefault().getLanguage() ).getValue() ).
                                append( lineSeparator );

                        }
                    }

                    if ( i.getProperties() != null )
                    {
                        for ( Property p : i.getProperties().getProperty() )
                        {
                            modulesInfo.append( "\t\t\tP:" ).append( p.getName() );

                            if ( p.getType() != null )
                            {
                                modulesInfo.append( "|Type:" ).append( p.getType() );
                            }

                            modulesInfo.append( "|Value:" );

                            try
                            {
                                modulesInfo.append( p.getJavaValue( getClassLoader( this.getClass() ) ) );
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
        b.append( "S:" ).append( s.getIdentifier() );
        if ( s.getVersion() != null )
        {
            b.append( "|Version:" ).append( s.getVersion() );
        }
        if ( s.getVendor() != null )
        {
            b.append( "|Vendor:" ).append( s.getVendor() );
        }

        b.append( "|Multiplicity:" ).append( s.getMultiplicity() ).append( "|Scope:" ).
            append( s.getScope() == null ? "Multiton" : s.getScope() );

        if ( s.getClazz() != null )
        {
            b.append( "|Class:" ).append( s.getClazz() );
        }

        return b;
    }

    private StringBuilder appendImplementationInfo( final Implementation i, final StringBuilder b )
    {
        b.append( "I:" ).append( i.getIdentifier() ).append( "|Name:" ).append( i.getName() ).append( "|Abstract:" ).
            append( i.isAbstract() ).append( "|Final:" ).append( i.isFinal() ).append( "|Stateless:" ).
            append( i.isStateless() );

        if ( i.getVersion() != null )
        {
            b.append( "|Version:" ).append( i.getVersion() );
        }
        if ( i.getVendor() != null )
        {
            b.append( "|Vendor:" ).append( i.getVendor() );
        }
        if ( i.getClazz() != null )
        {
            b.append( "|Class:" ).append( i.getClazz() );
        }
        if ( i.getLocation() != null )
        {
            b.append( "|Location:" ).append( i.getLocation() );
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
