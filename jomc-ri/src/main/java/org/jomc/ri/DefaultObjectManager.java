// SECTION-START[License Header]
/*
 *  JOMC RI
 *  Copyright (C) 2005 Christian Schulte <cs@schulte.it>
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 *
 *  $Id$
 */
// SECTION-END
package org.jomc.ri;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import org.jomc.ObjectManagementException;
import org.jomc.ObjectManager;
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
import org.jomc.model.Scope;
import org.jomc.model.Specification;
import org.jomc.ri.util.WeakIdentityHashMap;
import org.jomc.spi.Listener;

// SECTION-START[Implementation Comment]
/**
 * Object management and configuration reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.ObjectManager} {@code 1.0}<blockquote>
 * Object applies to Singleton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://jomc.sourceforge.net/jomc-tools"
)
// SECTION-END
public class DefaultObjectManager extends ObjectManager
{
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public DefaultObjectManager()
    {
        // SECTION-START[Default Constructor]
        super();
    // SECTION-END
    }
    // SECTION-END
    // SECTION-START[ObjectManager]

    @Override
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

            synchronized ( specification )
            {
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
                            final DefaultInstance instance = this.getInstance(
                                this.getClassLoader( specification ), s.getIdentifier(), i.getName() );

                            if ( instance != null )
                            {
                                object = this.getObject( s.getIdentifier(), i.getName(), instance );
                            }
                            else
                            {
                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                    i.getIdentifier(), i.getName() ), null );

                            }
                        }
                        else if ( s.getMultiplicity() == Multiplicity.MANY )
                        {
                            final List<Object> list = new ArrayList<Object>( available.getImplementation().size() );

                            for ( Implementation i : available.getImplementation() )
                            {
                                final DefaultInstance instance = this.getInstance(
                                    this.getClassLoader( specification ), s.getIdentifier(), i.getName() );

                                if ( instance != null )
                                {
                                    list.add( this.getObject( s.getIdentifier(), i.getName(), instance ) );
                                }
                                else
                                {
                                    this.log( Level.WARNING, this.getMissingInstanceMessage(
                                        i.getIdentifier(), i.getName() ), null );

                                }
                            }

                            object = list.isEmpty() ? null
                                     : list.toArray( (Object[]) Array.newInstance( specification, list.size() ) );

                        }
                        else
                        {
                            this.log( Level.WARNING, this.getUnsupportedMultiplicityMessage( s.getMultiplicity() ),
                                      null );

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
        }
        catch ( InstantiationException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return object;
    }

    @Override
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

            synchronized ( specification )
            {
                final Specification s = this.getModules().getSpecification( specification.getName() );

                if ( s != null )
                {
                    final Implementations available =
                        this.getModules().getImplementations( specification.getName() );

                    if ( available != null && !available.getImplementation().isEmpty() )
                    {
                        final Implementation i = available.getImplementationByName( implementationName );

                        if ( i != null )
                        {
                            final DefaultInstance instance = this.getInstance(
                                this.getClassLoader( specification ), s.getIdentifier(), i.getName() );

                            if ( instance != null )
                            {
                                object = this.getObject( s.getIdentifier(), i.getName(), instance );
                            }
                            else
                            {
                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                    i.getIdentifier(), i.getName() ), null );

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
        }
        catch ( InstantiationException e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }

        return object;
    }

    @Override
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

            synchronized ( object )
            {
                final DefaultInstance instance = this.getInstance( object );

                if ( instance != null )
                {
                    o = instance.getDependencyObjects().get( dependencyName );

                    if ( o == null )
                    {
                        final Dependency dependency = instance.getDependencies().getDependency( dependencyName );

                        if ( dependency != null )
                        {
                            final Specification ds =
                                this.getModules().getSpecification( dependency.getIdentifier() );

                            if ( ds != null )
                            {
                                final Class specClass = Class.forName(
                                    ds.getIdentifier(), true, this.getClassLoader( object.getClass() ) );

                                final Implementations available = this.getModules().getImplementations(
                                    instance.getIdentifier(), dependency.getName() );

                                if ( available != null && !available.getImplementation().isEmpty() )
                                {
                                    if ( dependency.getImplementationName() != null )
                                    {
                                        final Implementation implementation =
                                            available.getImplementationByName( dependency.getImplementationName() );

                                        if ( implementation != null )
                                        {
                                            final DefaultInstance di = this.getInstance(
                                                this.getClassLoader( object.getClass() ), ds.getIdentifier(),
                                                implementation.getName(), dependency );

                                            if ( di != null )
                                            {
                                                o = this.getObject( ds.getIdentifier(), implementation.getName(), di );
                                            }
                                            else
                                            {
                                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                                    implementation.getIdentifier(), implementation.getName() ), null );

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

                                        final DefaultInstance di = this.getInstance(
                                            this.getClassLoader( object.getClass() ), ds.getIdentifier(),
                                            ref.getName(), dependency );

                                        if ( di != null )
                                        {
                                            o = this.getObject( ds.getIdentifier(), ref.getName(), di );
                                        }
                                        else
                                        {
                                            this.log( Level.WARNING, this.getMissingInstanceMessage(
                                                ref.getIdentifier(), ref.getName() ), null );

                                        }
                                    }
                                    else
                                    {
                                        final List<Object> list =
                                            new ArrayList<Object>( available.getImplementation().size() );

                                        for ( Implementation a : available.getImplementation() )
                                        {
                                            final DefaultInstance di = this.getInstance(
                                                this.getClassLoader( object.getClass() ), ds.getIdentifier(),
                                                a.getName(), dependency );

                                            if ( di != null )
                                            {
                                                list.add( this.getObject( ds.getIdentifier(), a.getName(), di ) );
                                            }
                                            else
                                            {
                                                this.log( Level.WARNING, this.getMissingInstanceMessage(
                                                    a.getIdentifier(), a.getName() ), null );

                                            }
                                        }

                                        o = list.isEmpty() ? null : list.toArray( (Object[]) Array.newInstance(
                                            specClass, list.size() ) );

                                    }

                                    if ( o != null && dependency.isBound() )
                                    {
                                        instance.getDependencyObjects().put( dependencyName, o );
                                    }
                                }
                                else
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
                else
                {
                    this.log( Level.WARNING, this.getMissingObjectImplementationMessage( object ), null );
                }
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

        return o;
    }

    @Override
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

        this.initialize();

        synchronized ( object )
        {
            final DefaultInstance instance = this.getInstance( object );

            if ( instance != null )
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
            else
            {
                this.log( Level.WARNING, this.getMissingObjectImplementationMessage( object ), null );
            }
        }

        return value;
    }

    @Override
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

        this.initialize();

        synchronized ( object )
        {
            final Instance instance = this.getInstance( object );

            if ( instance != null )
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
            else
            {
                this.log( Level.WARNING, this.getMissingObjectImplementationMessage( object ), null );
            }
        }

        return text;
    }

    // SECTION-END
    // SECTION-START[DefaultObjectManager]
    /** Singleton instance. */
    private static final ObjectManager singleton = ObjectManager.newInstance();

    /** The modules of the instance. */
    private Modules modules;

    /** The model manager of the instance. */
    private ModelManager modelManager;

    /** Maps objects to {@code DefaultInstance}s. */
    private final Map objects = new WeakIdentityHashMap( 1024 );

    /** Scopes of the instance. */
    private final Map<Scope, org.jomc.spi.Scope> scopes = new HashMap<Scope, org.jomc.spi.Scope>();

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
     * Default {@link ObjectManager#getInstance()} implementation backed by static field.
     *
     * @return The default {@code ObjectManager} singleton instance.
     */
    public static ObjectManager getInstance()
    {
        return (ObjectManager) singleton.getObject( ObjectManager.class );
    }

    /**
     * Gets the flag indicating that classpath resolution is performed.
     * <p>Classpath resolution is performed by default. It can be disabled by setting the system property
     * {@code org.jomc.ri.DefaultObjectManager.classpathAware} to {@code false}.</p>
     *
     * @return {@code true} if the classloader of the instance is searched for resources; {@code false} if no
     * classpath resolution is performed.
     */
    public boolean isClasspathAware()
    {
        if ( this.classpathAware == null )
        {
            this.classpathAware = Boolean.valueOf( System.getProperty(
                "org.jomc.ri.DefaultObjectManager.classpathAware", Boolean.TRUE.toString() ) );

        }

        return this.classpathAware;
    }

    /**
     * Sets the flag indicating that classpath resolution should be performed.
     *
     * @param value {@code true} if the classloader of the instance is searched for resources; {@code false} if no
     * classpath resolution is performed.
     */
    public void setClasspathAware( final boolean value )
    {
        this.classpathAware = value;
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

                    this.modules = defaultModelManager.getClasspathModules(
                        DefaultModelManager.DEFAULT_DOCUMENT_LOCATION );

                    final Module classpathModule = defaultModelManager.getClasspathModule( this.modules );
                    if ( classpathModule != null )
                    {
                        this.modules.getModule().add( classpathModule );
                    }

                    defaultModelManager.getListeners().remove( this.defaultModelManagerListener );
                }
                else
                {
                    this.modules = new Modules();
                }

                this.getModelManager().validateModules( this.getModules() );
            }
            catch ( ModelException e )
            {
                this.log( Level.SEVERE, e.getMessage(), e );
                this.modules = null;
            }
            catch ( IOException e )
            {
                this.log( Level.SEVERE, e.getMessage(), e );
                this.modules = null;
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
     *
     * @throws ContainerSystemException if getting the model manager fails.
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
     * Gets the classloader of a given class.
     *
     * @param clazz The class whose classloader to return.
     *
     * @return The classloader of {@code clazz}.
     *
     * @throws NullPointerException if {@code clazz} is {@code null}.
     * @throws ContainerSystemException if no classloader is available.
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
            throw new ObjectManagementException( this.getMissingClassLoaderMessage() );
        }

        return cl;
    }

    /**
     * Gets an instance of an implementation of a given specification.
     *
     * @param classLoader The classloader of the instance.
     * @param specification The identifier of the specification to return an instance of.
     * @param name The name of the implementation implementing {@code specification} to return an instance of.
     *
     * @return An instance of the implementation with name {@code name} implementing the specification identified by
     * {@code specification} or {@code null}, if no such instance is available.
     */
    public DefaultInstance getInstance( final ClassLoader classLoader, final String specification, final String name )
    {
        final Instance i = this.getModules().getInstance( specification, name );
        return i == null ? null : new DefaultInstance( classLoader, i );
    }

    /**
     * Gets an instance of an implementation of a given specification as required by a given dependency.
     *
     * @param classLoader The classloader of the instance.
     * @param specification The identifier of the specification to return an instance of.
     * @param name The name of the implementation implementing {@code specification} to return an instance of.
     * @param dependency The dependency requiring the instance.
     *
     * @return An instance of the implementation with name {@code name} implementing the specification identified by
     * {@code specification} as required by {@code dependency} or {@code null}, if no such instance is available.
     */
    public DefaultInstance getInstance( final ClassLoader classLoader, final String specification,
                                        final String name, final Dependency dependency )
    {
        final Instance i = this.getModules().getInstance( specification, name, dependency );
        return i == null ? null : new DefaultInstance( classLoader, i );
    }

    /**
     * Gets the instance of an object.
     *
     * @param object The object to get the instance of.
     *
     * @return The instance of {@code object} or {@code null} if no instance is available for {@code object}.
     */
    public DefaultInstance getInstance( final Object object )
    {
        DefaultInstance instance;

        synchronized ( this.objects )
        {
            instance = (DefaultInstance) this.objects.get( object );
            if ( instance == null )
            {
                final Implementation i = this.getImplementation( object );

                if ( i != null )
                {
                    final Instance model = this.getModules().getInstance( i.getIdentifier() );

                    if ( model != null )
                    {
                        instance = new DefaultInstance( this.getClassLoader( object.getClass() ), model );
                        this.objects.put( object, instance );
                    }
                }

            }
        }

        return instance;
    }

    /**
     * Gets an object of a given instance.
     *
     * @param specification The identifier of the specification specifying the object to return.
     * @param name The name of the implementation implementing the object to return.
     * @param instance The instance to get an object of.
     *
     * @return An object of {@code instance} or {@code null} if nothing could be resolved.
     *
     * @throws InstantiationException if getting an object fails.
     */
    public Object getObject( final String specification, final String name, final DefaultInstance instance )
        throws InstantiationException
    {
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
                        object = this.getModelManager().createObject(
                            this.getModules(), specification, name, instance.getClassLoader() );

                    }
                    finally
                    {
                        scope.putObject( instance.getIdentifier(), object );
                        if ( object != null )
                        {
                            synchronized ( this.objects )
                            {
                                this.objects.put( object, instance );
                            }
                        }
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
     * Gets the implementation of an object.
     *
     * @param object The object to get the implementation for.
     *
     * @return The implementation for {@code object} or {@code null}, if nothing is known about {@code object}.
     */
    public Implementation getImplementation( final Object object )
    {
        return this.collectImplementation( object.getClass() );
    }

    private Implementation collectImplementation( final Class clazz )
    {
        Implementation i = this.getModules().getImplementation( clazz );
        if ( i == null && clazz.getSuperclass() != null )
        {
            i = this.collectImplementation( clazz.getSuperclass() );
        }

        return i;
    }

    /**
     * Gets the scope implementation for a given model scope.
     *
     * @param modelScope The scope to get an implementation of.
     *
     * @return The implementation of {@code modelScope} or {@code null} if no implementation is available implementing
     * {@code modelScope}.
     *
     * @throws InstantiationException if getting the scope fails.
     */
    public org.jomc.spi.Scope getScope( final Scope modelScope ) throws InstantiationException
    {
        synchronized ( this.scopes )
        {
            org.jomc.spi.Scope scope = this.scopes.get( modelScope );

            if ( scope == null )
            {
                // Bootstrap scope loading.
                final Implementations implementations =
                    this.getModules().getImplementations( org.jomc.spi.Scope.class.getName() );

                if ( implementations != null )
                {
                    for ( Implementation i : implementations.getImplementation() )
                    {
                        final org.jomc.spi.Scope s =
                            (org.jomc.spi.Scope) this.getModelManager().createObject(
                            this.getModules(), org.jomc.spi.Scope.class.getName(), i.getIdentifier(),
                            this.getClassLoader( org.jomc.spi.Scope.class ) );

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
                if ( modelScope == Scope.SINGLETON || modelScope == Scope.CONTEXT )
                {
                    scope = new DefaultScope( modelScope.value(), new HashMap<String, Object>() );
                    this.scopes.put( modelScope, scope );
                    this.log( Level.FINE, this.getDefaultScopeInfoMessage( modelScope, scope.getObjects() ), null );
                }
                else if ( modelScope == Scope.MULTITON )
                {
                    scope = new DefaultScope( modelScope.value(), null );
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

    /** Initializes the instance lazily. */
    public void initialize()
    {
        try
        {
            if ( !this.initialized )
            {
                final long start = System.currentTimeMillis();

                synchronized ( ObjectManager.class )
                {
                    this.initialized = true;

                    this.objects.clear();
                    this.scopes.clear();
                    this.bootstrapLogRecords.clear();

                    if ( this.modelManager instanceof DefaultModelManager )
                    {
                        ( (DefaultModelManager) this.modelManager ).getListeners().
                            remove( this.defaultModelManagerListener );

                    }

                    this.modelManager = null;
                    this.listeners = null;

                    this.getListeners().add( this.bootstrapObjectManagementListener );

                    final Instance instance = this.getInstance( this );
                    instance.setScope( Scope.SINGLETON );

                    final org.jomc.spi.Scope singletons = this.getScope( Scope.SINGLETON );
                    if ( singletons != null )
                    {
                        singletons.putObject( instance.getIdentifier(), this );
                    }

                    // Bootstrap listener loading.
                    final Implementations implementations = this.getModules().getImplementations(
                        org.jomc.spi.Listener.class.getName() );

                    if ( implementations != null && !implementations.getImplementation().isEmpty() )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            final org.jomc.spi.Listener l =
                                (org.jomc.spi.Listener) this.getModelManager().createObject(
                                this.getModules(), org.jomc.spi.Listener.class.getName(), i.getName(),
                                this.getClassLoader( org.jomc.spi.Listener.class ) );

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
                }

                this.log( Level.FINE, this.getImplementationInfoMessage(
                    new Date( System.currentTimeMillis() - start ) ), null );

            }
        }
        catch ( InstantiationException e )
        {
            this.objects.clear();
            this.scopes.clear();
            this.bootstrapLogRecords.clear();

            if ( this.modelManager instanceof DefaultModelManager )
            {
                ( (DefaultModelManager) this.modelManager ).getListeners().remove( this.defaultModelManagerListener );
            }

            this.modelManager = null;
            this.listeners = null;
            this.initialized = false;
            this.log( Level.SEVERE, e.getMessage(), e );
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

    private String getMessage( final String key, final Object arguments )
    {
        final MessageFormat fmt = new MessageFormat( ResourceBundle.getBundle( "org/jomc/ri/DefaultObjectManager" ).
            getString( key ) );

        return fmt.format( arguments );
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

    private String getMissingObjectImplementationMessage( final Object object )
    {
        return this.getMessage( "missingObjectImplementation", new Object[]
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

    private String getDefaultScopeInfoMessage( final Scope scope,
                                               final Map objects )
    {
        return this.getMessage( "defaultScopeInfo", new Object[]
            {
                scope.value(), objects == null ? "" : objects.toString()
            } );

    }

    private String getMissingScopeMessage( final Scope scope )
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

    // SECTION-END
}
