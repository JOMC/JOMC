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

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jomc.ObjectManagementException;
import org.jomc.ObjectManager;
import org.jomc.model.DefaultModelManager;
import org.jomc.model.Dependency;
import org.jomc.model.Implementation;
import org.jomc.model.Implementations;
import org.jomc.model.Instance;
import org.jomc.model.Message;
import org.jomc.model.ModelManager;
import org.jomc.model.Multiplicity;
import org.jomc.model.Property;
import org.jomc.model.Scope;
import org.jomc.model.Specification;
import org.jomc.ri.util.WeakIdentityHashMap;

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

// SECTION-END
public class DefaultObjectManager extends ObjectManager
{
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public DefaultObjectManager()
    {
        // SECTION-START[Default Constructor]
        super();
        if ( Logger.getLogger( this.getClass().getName() ).isLoggable( Level.FINE ) )
        {
            Logger.getLogger( this.getClass().getName() ).log( Level.FINE, this.getImplementationInfoMessage() );
        }

        try
        {
            synchronized ( ObjectManager.class )
            {
                final Instance instance = this.getInstance( this );
                instance.setScope( Scope.SINGLETON );

                final org.jomc.spi.Scope singletons = this.getScope( Scope.SINGLETON );
                if ( singletons != null )
                {
                    singletons.putObject( instance.getIdentifier(), this );
                }
            }
        }
        catch ( ObjectManagementException e )
        {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, e.getMessage(), e );
        }
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[ObjectManager]

    @Override
    public Object getObject( final Class specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        synchronized ( specification )
        {
            try
            {
                Object object = null;
                final Specification s = this.getModelManager().getSpecification( specification.getName() );

                if ( s == null )
                {
                    throw new IllegalArgumentException(
                        this.getMissingSpecificationMessage( specification.getName() ) );

                }

                final Implementations available = this.getModelManager().getImplementations( specification.getName() );

                if ( s.getMultiplicity() == Multiplicity.ONE )
                {
                    if ( available == null )
                    {
                        throw new ObjectManagementException(
                            this.getMissingImplementationsMessage( specification.getName() ) );

                    }
                    if ( available.getImplementation().size() != 1 )
                    {
                        throw new ObjectManagementException( this.getMultiplicityConstraintMessage(
                            Long.valueOf( available.getImplementation().size() ), specification.getName(),
                            BigInteger.ONE, s.getMultiplicity().value() ) );

                    }

                    final Implementation i = available.getImplementation().get( 0 );

                    object = this.getObject(
                        s.getIdentifier(), i.getName(),
                        this.getInstance( this.getClassLoader( specification ), s.getIdentifier(), i.getName() ) );

                }
                else if ( available != null )
                {
                    final List<Object> list = new ArrayList<Object>( available.getImplementation().size() );

                    for ( Implementation i : available.getImplementation() )
                    {
                        list.add( this.getObject(
                            s.getIdentifier(), i.getName(),
                            this.getInstance( this.getClassLoader( specification ), s.getIdentifier(), i.getName() ) ) );

                    }

                    object = list.toArray( (Object[]) Array.newInstance( specification, list.size() ) );
                }

                return object;
            }
            catch ( IllegalArgumentException e )
            {
                throw e;
            }
            catch ( ObjectManagementException e )
            {
                throw e;
            }
            catch ( Throwable t )
            {
                throw new ObjectManagementException( t.getMessage(), t );
            }
        }
    }

    @Override
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

        synchronized ( specification )
        {
            try
            {
                Object object = null;
                final Specification s = this.getModelManager().getSpecification( specification.getName() );

                if ( s == null )
                {
                    throw new IllegalArgumentException(
                        this.getMissingSpecificationMessage( specification.getName() ) );

                }

                final Implementations available = this.getModelManager().getImplementations( specification.getName() );
                Implementation i = null;
                if ( available != null )
                {
                    i = available.getImplementationByName( implementationName );
                }

                if ( s.getMultiplicity() == Multiplicity.ONE )
                {
                    if ( available == null )
                    {
                        throw new ObjectManagementException(
                            this.getMissingImplementationsMessage( specification.getName() ) );

                    }
                    if ( i == null )
                    {
                        throw new IllegalArgumentException(
                            this.getMissingImplementationMessage( implementationName, specification.getName() ) );

                    }
                }

                if ( i != null )
                {
                    object = this.getObject(
                        s.getIdentifier(), i.getName(),
                        this.getInstance( this.getClassLoader( specification ), s.getIdentifier(), i.getName() ) );

                }

                return object;
            }
            catch ( IllegalArgumentException e )
            {
                throw e;
            }
            catch ( ObjectManagementException e )
            {
                throw e;
            }
            catch ( Throwable t )
            {
                throw new ObjectManagementException( t.getMessage(), t );
            }
        }
    }

    @Override
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
        synchronized ( object )
        {
            try
            {
                final DefaultInstance instance = this.getInstance( object );

                if ( instance == null )
                {
                    throw new IllegalArgumentException( this.getMissingObjectImplementationMessage( object ) );
                }

                o = instance.getDependencyObjects().get( dependencyName );

                if ( o == null )
                {
                    final Implementation i = this.getImplementation( object );
                    if ( i == null )
                    {
                        throw new ObjectManagementException( this.getMissingObjectImplementationMessage( object ) );
                    }

                    final Dependency dependency = instance.getDependencies().getDependency( dependencyName );

                    if ( dependency == null )
                    {
                        throw new IllegalArgumentException(
                            this.getMissingDependencyMessage( dependencyName, object.getClass().getName() ) );

                    }

                    final Specification ds = this.getModelManager().getSpecification( dependency.getIdentifier() );

                    if ( ds == null )
                    {
                        throw new ObjectManagementException(
                            this.getMissingSpecificationMessage( dependency.getIdentifier() ) );

                    }

                    final Class specClass =
                        Class.forName( ds.getIdentifier(), true, this.getClassLoader( object.getClass() ) );

                    final Implementations available =
                        this.getModelManager().getImplementations( i.getIdentifier(), dependency.getName() );

                    if ( ds.getMultiplicity() == Multiplicity.ONE )
                    {
                        if ( available == null )
                        {
                            throw new ObjectManagementException(
                                this.getMissingImplementationsMessage( ds.getIdentifier() ) );

                        }
                        if ( available.getImplementation().size() != 1 )
                        {
                            throw new ObjectManagementException(
                                this.getMultiplicityConstraintMessage( available.getImplementation().size(),
                                                                       ds.getIdentifier(), BigInteger.ONE,
                                                                       ds.getMultiplicity().value() ) );

                        }

                        if ( dependency.getImplementationName() != null &&
                             available.getImplementationByName( dependency.getImplementationName() ) == null &&
                             !dependency.isOptional() )
                        {
                            throw new ObjectManagementException(
                                this.getMissingImplementationMessage( dependency.getImplementationName(),
                                                                      dependency.getIdentifier() ) );

                        }

                        final Implementation ref = available.getImplementation().get( 0 );

                        final DefaultInstance di =
                            this.getInstance( this.getClassLoader( object.getClass() ),
                                              ds.getIdentifier(), ref.getName(), dependency );

                        o = this.getObject( ds.getIdentifier(), ref.getName(), di );
                    }
                    else if ( available != null )
                    {
                        if ( dependency.getImplementationName() != null )
                        {
                            final Implementation ref =
                                available.getImplementationByName( dependency.getImplementationName() );

                            if ( !dependency.isOptional() && ref == null )
                            {
                                throw new ObjectManagementException(
                                    this.getMissingImplementationMessage( dependency.getImplementationName(),
                                                                          dependency.getIdentifier() ) );

                            }

                            if ( ref != null )
                            {
                                final DefaultInstance di =
                                    this.getInstance( this.getClassLoader( object.getClass() ),
                                                      ds.getIdentifier(), ref.getName(), dependency );

                                o = this.getObject( ds.getIdentifier(), ref.getName(), di );
                            }
                        }
                        else
                        {
                            final List<Object> list = new ArrayList<Object>( available.getImplementation().size() );

                            for ( Implementation a : available.getImplementation() )
                            {
                                final DefaultInstance di =
                                    this.getInstance( this.getClassLoader( object.getClass() ),
                                                      ds.getIdentifier(), a.getName(), dependency );

                                list.add( this.getObject( ds.getIdentifier(), a.getName(), di ) );
                            }

                            if ( !dependency.isOptional() && list.isEmpty() )
                            {
                                throw new ObjectManagementException( this.getMissingImplementationMessage(
                                    dependency.getName(), dependency.getIdentifier() ) );

                            }

                            o = list.toArray( (Object[]) Array.newInstance( specClass, list.size() ) );
                        }
                    }

                    if ( dependency.isBound() )
                    {
                        instance.getDependencyObjects().put( dependencyName, o );
                    }
                }
            }
            catch ( IllegalArgumentException e )
            {
                throw e;
            }
            catch ( ObjectManagementException e )
            {
                throw e;
            }
            catch ( Throwable t )
            {
                throw new ObjectManagementException( t.getMessage(), t );
            }
        }

        return o;
    }

    @Override
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
        synchronized ( object )
        {
            try
            {
                final DefaultInstance instance = this.getInstance( object );

                if ( instance == null )
                {
                    throw new IllegalArgumentException( this.getMissingObjectImplementationMessage( object ) );
                }

                value = instance.getPropertyObjects().get( propertyName );

                if ( value == null )
                {
                    final Property property = instance.getProperties().getProperty( propertyName );

                    if ( property == null )
                    {
                        throw new IllegalArgumentException(
                            this.getMissingPropertyMessage( propertyName, object.getClass().getName() ) );

                    }

                    value = property.getPropertyValue();

                    if ( value != null )
                    {
                        instance.getPropertyObjects().put( propertyName, value );
                    }
                }
            }
            catch ( IllegalArgumentException e )
            {
                throw e;
            }
            catch ( ObjectManagementException e )
            {
                throw e;
            }
            catch ( Throwable t )
            {
                throw new ObjectManagementException( t.getMessage(), t );
            }
        }

        return value;
    }

    @Override
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

        synchronized ( object )
        {
            try
            {
                final Instance instance = this.getInstance( object );

                if ( instance == null )
                {
                    throw new IllegalArgumentException(
                        this.getMissingObjectImplementationMessage( object ) );

                }

                final Message message = instance.getMessages().
                    getMessage( messageName );

                if ( message == null )
                {
                    throw new IllegalArgumentException(
                        this.getMissingMessageMessage( messageName, object.getClass().getName() ) );

                }

                final MessageFormat fmt = new MessageFormat( message.getTemplate().
                    getText( locale.getLanguage().toLowerCase( Locale.ENGLISH ) ).getValue(), locale );

                return fmt.format( arguments );
            }
            catch ( IllegalArgumentException e )
            {
                throw e;
            }
            catch ( ObjectManagementException e )
            {
                throw e;
            }
            catch ( Throwable t )
            {
                throw new ObjectManagementException( t.getMessage(), t );
            }
        }
    }

    // SECTION-END
    // SECTION-START[DefaultObjectManager]
    /** Thread local stack. */
    private static final ThreadLocal<Stack<Instance>> INSTANTIATION_STACK =
        new ThreadLocal<Stack<Instance>>()
        {

            @Override
            protected Stack<Instance> initialValue()
            {
                return new Stack<Instance>();
            }

        };

    /** Singleton instance. */
    private static final ObjectManager singleton = ObjectManager.newInstance();

    /** The model manager of the instance. */
    private ModelManager modelManager;

    /** Maps objects to {@code DefaultInstance}s. */
    private final Map objects = new WeakIdentityHashMap( 1024 );

    /** Scopes of the instance. */
    private final Map<Scope, org.jomc.spi.Scope> scopes = new HashMap<Scope, org.jomc.spi.Scope>();

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
     * Gets the model manager backing the instance.
     *
     * @return The model manager backing the instance.
     *
     * @throws ContainerSystemException if getting the model manager fails.
     */
    public ModelManager getModelManager()
    {
        try
        {
            if ( this.modelManager == null )
            {
                final DefaultModelManager defaultManager =
                    new DefaultModelManager( this.getClassLoader( this.getClass() ) );

                defaultManager.setClasspathAware( true );
                defaultManager.setValidating( true );
                this.modelManager = defaultManager;
            }

            return this.modelManager;
        }
        catch ( Throwable t )
        {
            throw new ObjectManagementException( t.getMessage(), t );
        }
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
     *
     * @throws NullPointerException if {@code classLoader}, {@code specification} or {@code name} is {@code null}.
     * @throws ContainerSystemException if no instance is available.
     */
    public DefaultInstance getInstance( final ClassLoader classLoader, final String specification, final String name )
    {
        try
        {
            final Instance i = this.getModelManager().getInstance( specification, name );

            if ( i == null )
            {
                throw new ObjectManagementException( this.getMissingInstanceMessage( specification, name ) );
            }

            return new DefaultInstance( classLoader, i );
        }
        catch ( ObjectManagementException e )
        {
            throw e;
        }
        catch ( Throwable t )
        {
            throw new ObjectManagementException( t.getMessage(), t );
        }
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
     *
     * @throws NullPointerException if {@code classLoader}, {@code specification}, {@code name} or {@code dependency} is
     * {@code null}.
     * @throws ContainerSystemException if no instance is available.
     */
    public DefaultInstance getInstance( final ClassLoader classLoader, final String specification, final String name,
                                        final Dependency dependency )
    {
        try
        {
            final Instance i = this.getModelManager().getInstance( specification, name, dependency );

            if ( i == null )
            {
                throw new ObjectManagementException( this.getMissingInstanceMessage( specification, name ) );
            }

            return new DefaultInstance( classLoader, i );
        }
        catch ( ObjectManagementException e )
        {
            throw e;
        }
        catch ( Throwable t )
        {
            throw new ObjectManagementException( t.getMessage(), t );
        }
    }

    /**
     * Gets the instance of an object.
     *
     * @param object The object to get the instance of.
     *
     * @return The instance of {@code object} or {@code null} if no instance is available for {@code object}.
     *
     * @throws ContainerSystemException if no instance is available.
     */
    public DefaultInstance getInstance( final Object object )
    {
        DefaultInstance instance;

        synchronized ( this.objects )
        {
            try
            {
                instance = (DefaultInstance) this.objects.get( object );
                if ( instance == null )
                {
                    final Implementation i = this.getImplementation( object );

                    if ( i != null )
                    {
                        final Instance model = this.getModelManager().getInstance( i.getIdentifier() );

                        if ( model == null )
                        {
                            throw new ObjectManagementException(
                                this.getMissingInstanceMessage( null, i.getIdentifier() ) );

                        }

                        instance = new DefaultInstance( this.getClassLoader( object.getClass() ), model );
                        this.objects.put( object, instance );
                    }
                }
            }
            catch ( ObjectManagementException e )
            {
                throw e;
            }
            catch ( Throwable t )
            {
                throw new ObjectManagementException( t.getMessage(), t );
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
     * @return An object of {@code instance}.
     *
     * @throws ContainerSystemException if getting the object fails.
     */
    public Object getObject( final String specification, final String name,
                             final DefaultInstance instance ) throws InstantiationException
    {
        final org.jomc.spi.Scope scope = this.getScope( instance.getScope() );

        if ( scope == null )
        {
            throw new ObjectManagementException( this.getMissingImplementationMessage(
                instance.getScope().value(), org.jomc.spi.Scope.class.getName() ) );

        }

        Object object;
        synchronized ( scope )
        {
            try
            {
                object = scope.getObject( instance.getIdentifier() );

                if ( object == null )
                {
                    scope.putObject( instance.getIdentifier(), instance );
                    INSTANTIATION_STACK.get().push( instance );

                    try
                    {
                        object = this.getModelManager().createObject( specification, name, instance.getClassLoader() );
                    }
                    catch ( Throwable t )
                    {
                        throw new ObjectManagementException( t.getMessage(), t );
                    }
                    finally
                    {
                        INSTANTIATION_STACK.get().pop();
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
            catch ( ObjectManagementException e )
            {
                throw e;
            }
            catch ( Throwable t )
            {
                throw new ObjectManagementException( t.getMessage(), t );
            }
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
        try
        {
            Implementation i = this.collectImplementation( object.getClass() );

            if ( i != null && !INSTANTIATION_STACK.get().empty() &&
                 INSTANTIATION_STACK.get().peek().getClazz().equals( i.getClazz() ) )
            {
                i = this.getModelManager().getImplementation( INSTANTIATION_STACK.get().peek().getIdentifier() );
            }

            return i;
        }
        catch ( ObjectManagementException e )
        {
            throw e;
        }
        catch ( Throwable t )
        {
            throw new ObjectManagementException( t.getMessage(), t );
        }
    }

    private Implementation collectImplementation( final Class clazz )
    {
        Implementation i = getModelManager().getImplementation( clazz );
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
     * @throws ContainerSystemException if getting the scope fails.
     */
    public org.jomc.spi.Scope getScope( final Scope modelScope )
    {
        synchronized ( this.scopes )
        {
            try
            {
                org.jomc.spi.Scope scope = this.scopes.get( modelScope );

                if ( scope == null )
                {
                    // Bootstrap scope loading.
                    final Implementations implementations =
                        this.getModelManager().getImplementations( org.jomc.spi.Scope.class.getName() );

                    if ( implementations != null )
                    {
                        for ( Implementation i : implementations.getImplementation() )
                        {
                            final org.jomc.spi.Scope s =
                                (org.jomc.spi.Scope) this.getModelManager().createObject(
                                org.jomc.spi.Scope.class.getName(), i.getIdentifier(),
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

                        if ( Logger.getLogger( this.getClass().getName() ).isLoggable( Level.FINE ) )
                        {
                            Logger.getLogger( this.getClass().getName() ).log(
                                Level.FINE, this.getDefaultScopeInfoMessage( modelScope, scope.getObjects() ) );

                        }
                    }
                    else if ( modelScope == Scope.MULTITON )
                    {
                        scope = new DefaultScope( modelScope.value(), null );
                        this.scopes.put( modelScope, scope );

                        if ( Logger.getLogger( this.getClass().getName() ).isLoggable( Level.FINE ) )
                        {
                            Logger.getLogger( this.getClass().getName() ).log(
                                Level.FINE, this.getDefaultScopeInfoMessage( modelScope, scope.getObjects() ) );

                        }
                    }
                }

                return scope;
            }
            catch ( ObjectManagementException e )
            {
                throw e;
            }
            catch ( Throwable t )
            {
                throw new ObjectManagementException( t.getMessage(), t );
            }
        }
    }

    private String getMessage( final String key, final Object arguments )
    {
        final MessageFormat fmt = new MessageFormat( ResourceBundle.getBundle(
            DefaultObjectManager.class.getName().replace( '.', '/' ) ).getString( key ) );

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

    private String getMultiplicityConstraintMessage( final Number implementations, final String specification,
                                                     final Number expected, final String multiplicity )
    {
        return this.getMessage( "multiplicityConstraint", new Object[]
            {
                implementations, specification, expected, multiplicity
            } );

    }

    private String getMissingImplementationMessage( final String implementationName, final String specification )
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

    private String getMissingInstanceMessage( final String specification, final String name )
    {
        return this.getMessage( "missingInstance", new Object[]
            {
                specification, name
            } );

    }

    private String getMissingClassLoaderMessage()
    {
        return this.getMessage( "missingClassloader", null );
    }

    private String getDependencyCycleMessage( final String implementation )
    {
        return this.getMessage( "dependencyCycle", new Object[]
            {
                implementation
            } );

    }

    private String getImplementationInfoMessage()
    {
        return this.getMessage( "implementationInfo", null );
    }

    private String getDefaultScopeInfoMessage( final Scope scope, final Map objects )
    {
        return this.getMessage( "defaultScopeInfo", new Object[]
            {
                scope.value(), objects == null ? "" : objects.toString()
            } );

    }

    // SECTION-END
}
