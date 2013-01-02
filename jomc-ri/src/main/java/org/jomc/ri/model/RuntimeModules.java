// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Java Object Management and Configuration
 *   Copyright (C) Christian Schulte, 2011-313
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
 *   THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *   INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 *   AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 *   THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *   NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *   DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *   THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *   THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $JOMC$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.ri.model;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;
import org.jomc.model.Dependencies;
import org.jomc.model.Dependency;
import org.jomc.model.Implementation;
import org.jomc.model.Implementations;
import org.jomc.model.Instance;
import org.jomc.model.Message;
import org.jomc.model.Messages;
import org.jomc.model.ModelObjectException;
import org.jomc.model.Module;
import org.jomc.model.Modules;
import org.jomc.model.Properties;
import org.jomc.model.Property;
import org.jomc.model.Specification;
import org.jomc.model.Specifications;
import static org.jomc.ri.model.RuntimeModelObjects.createMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Modules}.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.model.RuntimeModules</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ RI ⁑ RuntimeModules</dd>
 *   <dt><b>Specifications:</b></dt>
 *     <dd>org.jomc.ri.model.RuntimeModelObject @ 1.2</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.2
 * @version 1.2
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class RuntimeModules extends Modules implements RuntimeModelObject
{
    // SECTION-START[RuntimeModules]

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Module> modulesByNameCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Specifications> specificationsCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Implementations> implementationsCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Module> moduleBySpecificationIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Module> moduleByImplementationIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Specification> specificationByIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Specification> specificationByClassNameCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Specifications> specificationsByImplemenationIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Implementation> implementationByIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Implementation> implementationByClassNameCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Implementation> implementationByObjectClassNameCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Implementation> implementationBySpecificationAndNameCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Dependencies> dependenciesByImplementationIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Properties> propertiesByImplementationIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Properties> specifiedPropertiesByImplementationIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Messages> messagesByImplementationIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Implementations> implementationsBySpecificationIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, List<Object>> anyObjectsByImplemenationIdentifierCache = createMap();

    /**
     * Creates a new {@code RuntimeModules} instance by deeply copying a given {@code Modules} instance.
     *
     * @param modules The instance to copy.
     *
     * @throws NullPointerException if {@code modules} is {@code null}.
     */
    public RuntimeModules( final Modules modules )
    {
        super( modules );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }

        this.copyModules();
    }

    /**
     * Creates a new {@code DefaultModules} instance by deeply copying a given {@code Modules} instance taking a map
     * backing the instance.
     *
     * @param modules The instance to copy.
     * @param objects The map backing the instance.
     *
     * @throws NullPointerException if {@code modules} or {@code objects} is {@code null}.
     */
    public RuntimeModules( final Modules modules, final Map<Object, Instance> objects )
    {
        super( modules, objects );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }

        this.copyModules();
    }

    /**
     * Gets a module for a given name from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param name The name of the module to return.
     *
     * @return The first matching module or {@code null}, if no such module is found.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     *
     * @see #getModule()
     * @see Module#getName()
     * @see #clear()
     */
    @Override
    public Module getModule( final String name )
    {
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }

        synchronized ( this.modulesByNameCache )
        {
            Module m = this.modulesByNameCache.get( name );

            if ( m == null && !this.modulesByNameCache.containsKey( name ) )
            {
                m = super.getModule( name );
                this.modulesByNameCache.put( name, m );
            }

            return m;
        }
    }

    /**
     * Gets all specifications of the list of modules.
     * <p>This method queries an internal cache for a result object to return. If no cached result object is available,
     * this method queries the super-class for a result object to return and caches the outcome of that query for use on
     * successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @return All specifications or {@code null}, if no specifications are found.
     *
     * @see #getModule()
     * @see #clear()
     */
    @Override
    public Specifications getSpecifications()
    {
        synchronized ( this.specificationsCache )
        {
            Specifications s = this.specificationsCache.get( RuntimeModules.class.getName() );

            if ( s == null && !this.specificationsCache.containsKey( RuntimeModules.class.getName() ) )
            {
                s = super.getSpecifications();

                if ( s != null )
                {
                    s = RuntimeModelObjects.getInstance().copyOf( s );
                }

                this.specificationsCache.put( RuntimeModules.class.getName(), s );
            }

            return s;
        }
    }

    /**
     * Gets all specifications of the list of modules.
     * <p>This method queries an internal cache for a result object to return. If no cached result object is available,
     * this method queries the super-class for a result object to return and caches the outcome of that query for use on
     * successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @return All specifications or {@code null}, if no specifications are found.
     *
     * @see #getModule()
     * @see #clear()
     */
    @Override
    public Implementations getImplementations()
    {
        synchronized ( this.implementationsCache )
        {
            Implementations i = this.implementationsCache.get( RuntimeModules.class.getName() );

            if ( i == null && !this.implementationsCache.containsKey( RuntimeModules.class.getName() ) )
            {
                i = super.getImplementations();

                if ( i != null )
                {
                    i = RuntimeModelObjects.getInstance().copyOf( i );
                }

                this.implementationsCache.put( RuntimeModules.class.getName(), i );
            }

            return i;
        }
    }

    /**
     * Gets the module declaring a given specification from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param specification The identifier of the specification whose declaring module to return.
     *
     * @return The first matching module or {@code null}, if no such module is found.
     *
     * @throws NullPointerException if {@code specification} is {@code null}.
     *
     * @see #getModule()
     * @see Module#getSpecifications()
     * @see Specifications#getSpecification( java.lang.String )
     * @see #clear()
     */
    @Override
    public Module getModuleOfSpecification( final String specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        synchronized ( this.moduleBySpecificationIdentifierCache )
        {
            Module m = this.moduleBySpecificationIdentifierCache.get( specification );

            if ( m == null && !this.moduleBySpecificationIdentifierCache.containsKey( specification ) )
            {
                m = super.getModuleOfSpecification( specification );
                this.moduleBySpecificationIdentifierCache.put( specification, m );
            }

            return m;
        }
    }

    /**
     * Gets the module declaring a given implementation from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The identifier of the implementation whose declaring module to return.
     *
     * @return The first matching module or {@code null}, if no such module is found.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getModule()
     * @see Module#getImplementations()
     * @see Implementations#getImplementation( java.lang.String )
     * @see #clear()
     */
    @Override
    public Module getModuleOfImplementation( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.moduleByImplementationIdentifierCache )
        {
            Module m = this.moduleByImplementationIdentifierCache.get( implementation );

            if ( m == null && !this.moduleByImplementationIdentifierCache.containsKey( implementation ) )
            {
                m = super.getModuleOfImplementation( implementation );
                this.moduleByImplementationIdentifierCache.put( implementation, m );
            }

            return m;
        }
    }

    /**
     * Gets a specification for a given identifier from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param specification The identifier of the specification to return.
     *
     * @return The first matching specification or {@code null}, if no such specification is found.
     *
     * @throws NullPointerException if {@code specification} is {@code null}.
     *
     * @see #getModule()
     * @see Module#getSpecifications()
     * @see Specifications#getSpecification( java.lang.String )
     * @see #clear()
     */
    @Override
    public Specification getSpecification( final String specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        synchronized ( this.specificationByIdentifierCache )
        {
            Specification s = this.specificationByIdentifierCache.get( specification );

            if ( s == null && !this.specificationByIdentifierCache.containsKey( specification ) )
            {
                s = super.getSpecification( specification );
                this.specificationByIdentifierCache.put( specification, s );
            }

            return s;
        }
    }

    /**
     * Gets a specification for a given class from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param specification The class of the specification to return.
     *
     * @return The first matching specification or {@code null}, if no such specification is found.
     *
     * @throws NullPointerException if {@code specification} is {@code null}.
     * @throws ModelObjectException if parsing a name of a referenced type fails.
     *
     * @see #getModule()
     * @see Module#getSpecifications()
     * @see Specifications#getSpecification( java.lang.Class )
     * @see #clear()
     */
    @Override
    public Specification getSpecification( final Class<?> specification ) throws ModelObjectException
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        synchronized ( this.specificationByClassNameCache )
        {
            Specification s = this.specificationByClassNameCache.get( specification.getName() );

            if ( s == null && !this.specificationByClassNameCache.containsKey( specification.getName() ) )
            {
                s = super.getSpecification( specification );
                this.specificationByClassNameCache.put( specification.getName(), s );
            }

            return s;
        }
    }

    /**
     * Gets all specifications an implementation implements from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The identifier of the implementation to get all implemented specifications of.
     *
     * @return All specifications implemented by the first matching implementation or {@code null}, if no such
     * implementation is found or if the first matching implementation does not implement any specification.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getModule()
     * @see #getImplementation( java.lang.String )
     * @see Implementation#getImplementations()
     * @see Implementations#getReference()
     * @see #clear()
     */
    @Override
    public Specifications getSpecifications( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.specificationsByImplemenationIdentifierCache )
        {
            Specifications s = this.specificationsByImplemenationIdentifierCache.get( implementation );

            if ( s == null && !this.specificationsByImplemenationIdentifierCache.containsKey( implementation ) )
            {
                s = super.getSpecifications( implementation );

                if ( s != null )
                {
                    s = RuntimeModelObjects.getInstance().copyOf( s );
                }

                this.specificationsByImplemenationIdentifierCache.put( implementation, s );
            }

            return s;
        }
    }

    /**
     * Gets an implementation for a given identifier from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The identifier of the implementation to return.
     *
     * @return The first matching implementation or {@code null}, if no such implementation is found.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getModule()
     * @see Module#getImplementations()
     * @see Implementations#getImplementation( java.lang.String )
     * @see #clear()
     */
    @Override
    public Implementation getImplementation( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.implementationByIdentifierCache )
        {
            Implementation i = this.implementationByIdentifierCache.get( implementation );

            if ( i == null && !this.implementationByIdentifierCache.containsKey( implementation ) )
            {
                i = super.getImplementation( implementation );
                this.implementationByIdentifierCache.put( implementation, i );
            }

            return i;
        }
    }

    /**
     * Gets an implementation for a given class from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The class of the implementation to return.
     *
     * @return The first matching implementation or {@code null}, if no such implementation is found.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     * @throws ModelObjectException if parsing a name of a referenced type fails.
     *
     * @see #getModule()
     * @see Module#getImplementations()
     * @see Implementations#getImplementation( java.lang.Class )
     * @see #clear()
     */
    @Override
    public Implementation getImplementation( final Class<?> implementation ) throws ModelObjectException
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.implementationByClassNameCache )
        {
            Implementation i = this.implementationByClassNameCache.get( implementation.getName() );

            if ( i == null && !this.implementationByClassNameCache.containsKey( implementation.getName() ) )
            {
                i = super.getImplementation( implementation );
                this.implementationByClassNameCache.put( implementation.getName(), i );
            }

            return i;
        }
    }

    /**
     * Gets an implementation for a given object from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param object The object of the implementation to return.
     *
     * @return The first matching implementation or {@code null}, if no such implementation is found.
     *
     * @throws NullPointerException if {@code object} is {@code null}.
     * @throws ModelObjectException if parsing a name of a referenced type fails.
     *
     * @see #getModule()
     * @see #getImplementation( java.lang.Class )
     * @see #clear()
     */
    @Override
    public Implementation getImplementation( final Object object ) throws ModelObjectException
    {
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }

        synchronized ( this.implementationByObjectClassNameCache )
        {
            Implementation i = this.implementationByObjectClassNameCache.get( object.getClass().getName() );

            if ( i == null && !this.implementationByObjectClassNameCache.containsKey( object.getClass().getName() ) )
            {
                i = super.getImplementation( object );
                this.implementationByObjectClassNameCache.put( object.getClass().getName(), i );
            }

            return i;
        }
    }

    /**
     * Gets an implementation for a given name implementing a given specification from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param specification The identifier of the specification to return an implementation of.
     * @param name The name of the implementation to return.
     *
     * @return The first matching implementation or {@code null}, if no such implementation is found.
     *
     * @throws NullPointerException if {@code specification} or {@code name} is {@code null}.
     *
     * @see #getModule()
     * @see #getImplementations( java.lang.String )
     * @see Implementations#getImplementationByName( java.lang.String )
     * @see #clear()
     */
    @Override
    public Implementation getImplementation( final String specification, final String name )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }

        synchronized ( this.implementationBySpecificationAndNameCache )
        {
            final String key = specification + "|" + name;
            Implementation i = this.implementationBySpecificationAndNameCache.get( key );

            if ( i == null && !this.implementationBySpecificationAndNameCache.containsKey( key ) )
            {
                i = super.getImplementation( specification, name );
                this.implementationBySpecificationAndNameCache.put( key, i );
            }

            return i;
        }
    }

    /**
     * Gets all dependencies of an implementation from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The identifier of the implementation to get all dependencies of.
     *
     * @return All dependencies of the first matching implementation or {@code null}, if no such implementation is
     * found or if the first matching implementation does not have any dependencies.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getModule()
     * @see #getImplementation( java.lang.String )
     * @see Implementation#getImplementations()
     * @see Implementations#getReference()
     * @see #clear()
     */
    @Override
    public Dependencies getDependencies( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.dependenciesByImplementationIdentifierCache )
        {
            Dependencies d = this.dependenciesByImplementationIdentifierCache.get( implementation );

            if ( d == null && !this.dependenciesByImplementationIdentifierCache.containsKey( implementation ) )
            {
                d = super.getDependencies( implementation );

                if ( d != null )
                {
                    d = RuntimeModelObjects.getInstance().copyOf( d );
                }

                this.dependenciesByImplementationIdentifierCache.put( implementation, d );
            }

            return d;
        }
    }

    /**
     * Gets all properties of an implementation from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The identifier of the implementation to get all properties of.
     *
     * @return All properties of the first matching implementation or {@code null}, if no such implementation is found
     * or if the first matching implementation does not have any properties.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getModule()
     * @see #getImplementation( java.lang.String )
     * @see Implementation#getImplementations()
     * @see Implementations#getReference()
     * @see #clear()
     */
    @Override
    public Properties getProperties( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.propertiesByImplementationIdentifierCache )
        {
            Properties p = this.propertiesByImplementationIdentifierCache.get( implementation );

            if ( p == null && !this.propertiesByImplementationIdentifierCache.containsKey( implementation ) )
            {
                p = super.getProperties( implementation );

                if ( p != null )
                {
                    p = RuntimeModelObjects.getInstance().copyOf( p );
                }

                this.propertiesByImplementationIdentifierCache.put( implementation, p );
            }

            return p;
        }
    }

    /**
     * Gets all properties specified for an implementation from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The identifier of the implementation to return specified properties of.
     *
     * @return All properties specified for the first matching implementation or {@code null}, if no such implementation
     * is found or if the first matching implementation does not have any specified properties.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getModule()
     * @see #getSpecifications( java.lang.String )
     * @see Specification#getProperties()
     * @see #clear()
     */
    @Override
    public Properties getSpecifiedProperties( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.specifiedPropertiesByImplementationIdentifierCache )
        {
            Properties p = this.specifiedPropertiesByImplementationIdentifierCache.get( implementation );

            if ( p == null && !this.specifiedPropertiesByImplementationIdentifierCache.containsKey( implementation ) )
            {
                p = super.getSpecifiedProperties( implementation );

                if ( p != null )
                {
                    p = RuntimeModelObjects.getInstance().copyOf( p );
                }

                this.specifiedPropertiesByImplementationIdentifierCache.put( implementation, p );
            }

            return p;
        }
    }

    /**
     * Gets all messages of an implementation from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The identifier of the implementation to get all messages of.
     *
     * @return All messages of the first matching implementation or {@code null}, if no such implementation is found
     * or if the first matching implementation does not have any messages.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getModule()
     * @see #getImplementation( java.lang.String )
     * @see Implementation#getImplementations()
     * @see Implementations#getReference()
     * @see #clear()
     */
    @Override
    public Messages getMessages( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.messagesByImplementationIdentifierCache )
        {
            Messages m = this.messagesByImplementationIdentifierCache.get( implementation );

            if ( m == null && !this.messagesByImplementationIdentifierCache.containsKey( implementation ) )
            {
                m = super.getMessages( implementation );

                if ( m != null )
                {
                    m = RuntimeModelObjects.getInstance().copyOf( m );
                }

                this.messagesByImplementationIdentifierCache.put( implementation, m );
            }

            return m;
        }
    }

    /**
     * Gets all implementations implementing a given specification from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param specification The identifier of the specification to return all implementations of.
     *
     * @return All implementations implementing the first matching specification or {@code null}, if no such
     * specification is found or if the first matching specification does not have any implementations.
     *
     * @throws NullPointerException if {@code specification} is {@code null}.
     *
     * @see #getModule()
     * @see #getSpecifications( java.lang.String )
     * @see #clear()
     */
    @Override
    public Implementations getImplementations( final String specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        synchronized ( this.implementationsBySpecificationIdentifierCache )
        {
            Implementations i = this.implementationsBySpecificationIdentifierCache.get( specification );

            if ( i == null && !this.implementationsBySpecificationIdentifierCache.containsKey( specification ) )
            {
                i = super.getImplementations( specification );

                if ( i != null )
                {
                    i = RuntimeModelObjects.getInstance().copyOf( i );
                }

                this.implementationsBySpecificationIdentifierCache.put( specification, i );
            }

            return i;
        }
    }

    /**
     * Gets any objects of an implementation from the list of modules.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The identifier of the implementation to get any objects of.
     *
     * @return Any objects of the first matching implementation or {@code null}, if no such implementation is found.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getModule()
     * @see #getImplementation( java.lang.String )
     * @see Implementation#getImplementations()
     * @see Implementations#getReference()
     * @see #clear()
     */
    @Override
    public List<Object> getAnyObjects( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.anyObjectsByImplemenationIdentifierCache )
        {
            List<Object> any = this.anyObjectsByImplemenationIdentifierCache.get( implementation );

            if ( any == null && !this.anyObjectsByImplemenationIdentifierCache.containsKey( implementation ) )
            {
                any = super.getAnyObjects( implementation );
                this.anyObjectsByImplemenationIdentifierCache.put( implementation, any );
            }

            return any;
        }
    }

    /**
     * Gets an instance for an implementation from the list of modules.
     *
     * @param implementation The identifier of the implementation to get an instance for.
     *
     * @return A new instance for the first matching implementation or {@code null}, if no such implementation is found.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getModule()
     * @see #getImplementation( java.lang.String )
     * @see #getDependencies(java.lang.String)
     * @see #getProperties(java.lang.String)
     * @see #getMessages(java.lang.String)
     * @see #getSpecifications(java.lang.String)
     * @see #getAnyObjects(java.lang.String)
     */
    @Override
    public Instance getInstance( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        final Implementation i = this.getImplementation( implementation );

        if ( i != null && i.getClazz() != null )
        {
            final Instance instance = new RuntimeInstance();
            instance.setIdentifier( i.getIdentifier() );
            instance.setName( i.getName() );
            instance.setClazz( i.getClazz() );
            instance.setStateless( i.isStateless() );
            instance.setDependencies( this.getDependencies( implementation ) );
            instance.setProperties( this.getProperties( implementation ) );
            instance.setMessages( this.getMessages( implementation ) );
            instance.setSpecifications( this.getSpecifications( implementation ) );
            instance.getAny().addAll( this.getAnyObjects( implementation ) );
            return instance;
        }

        return null;
    }

    /**
     * Gets an instance for an implementation from the list of modules overridden with a given dependency.
     *
     * @param implementation The identifier of the implementation to get an instance for.
     * @param dependency The dependency to use for overriding model objects of the instance.
     *
     * @return An instance for the first matching implementation with any model objects overridden using
     * {@code dependency} or {@code null}, if no such implementation is found.
     *
     * @throws NullPointerException if {@code implementation} or {@code dependency} is {@code null}.
     *
     * @see #getModule()
     * @see #getInstance( java.lang.String )
     */
    @Override
    public Instance getInstance( final String implementation, final Dependency dependency )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }
        if ( dependency == null )
        {
            throw new NullPointerException( "dependency" );
        }

        Instance instance = this.getInstance( implementation );

        if ( instance != null )
        {
            final Specification dependencySpecification = this.getSpecification( dependency.getIdentifier() );

            if ( dependencySpecification != null && dependencySpecification.getScope() == null )
            {
                if ( dependency.getDependencies() != null && !dependency.getDependencies().getDependency().isEmpty() )
                {
                    final Dependencies dependencies =
                        RuntimeModelObjects.getInstance().copyOf( dependency.getDependencies() );

                    if ( instance.getDependencies() != null )
                    {
                        for ( int i = 0, s0 = instance.getDependencies().getDependency().size(); i < s0; i++ )
                        {
                            final Dependency d = instance.getDependencies().getDependency().get( i );
                            final Dependency td = dependencies.getDependency( d.getName() );

                            if ( td == null )
                            {
                                dependencies.getDependency().add( d );

                                if ( dependencies instanceof RuntimeModelObject )
                                {
                                    ( (RuntimeModelObject) dependencies ).clear();
                                }
                            }
                            else
                            {
                                this.collectDependencies( d, td );
                            }
                        }
                    }

                    instance.setDependencies( dependencies );
                }

                if ( dependency.getMessages() != null && !dependency.getMessages().getMessage().isEmpty() )
                {
                    final Messages messages =
                        RuntimeModelObjects.getInstance().copyOf( dependency.getMessages() );

                    if ( instance.getMessages() != null )
                    {
                        for ( int i = 0, s0 = instance.getMessages().getMessage().size(); i < s0; i++ )
                        {
                            final Message m = instance.getMessages().getMessage().get( i );

                            if ( messages.getMessage( m.getName() ) == null )
                            {
                                messages.getMessage().add( m );

                                if ( messages instanceof RuntimeModelObject )
                                {
                                    ( (RuntimeModelObject) messages ).clear();
                                }
                            }
                        }
                    }

                    instance.setMessages( messages );
                }

                if ( dependency.getProperties() != null && !dependency.getProperties().getProperty().isEmpty() )
                {
                    final Properties properties =
                        RuntimeModelObjects.getInstance().copyOf( dependency.getProperties() );

                    if ( instance.getProperties() != null )
                    {
                        for ( int i = 0, s0 = instance.getProperties().getProperty().size(); i < s0; i++ )
                        {
                            final Property p = instance.getProperties().getProperty().get( i );

                            if ( properties.getProperty( p.getName() ) == null )
                            {
                                properties.getProperty().add( p );

                                if ( properties instanceof RuntimeModelObject )
                                {
                                    ( (RuntimeModelObject) properties ).clear();
                                }
                            }
                        }
                    }

                    instance.setProperties( properties );
                }
            }
        }

        return instance;
    }

    private void collectDependencies( final Dependency source, final Dependency target )
    {
        if ( source.getMessages() != null )
        {
            if ( target.getMessages() == null )
            {
                target.setMessages( new RuntimeMessages() );
            }

            for ( int i = 0, s0 = source.getMessages().getMessage().size(); i < s0; i++ )
            {
                final Message m = source.getMessages().getMessage().get( i );

                if ( target.getMessages().getMessage( m.getName() ) == null )
                {
                    target.getMessages().getMessage().add( m );

                    if ( target.getMessages() instanceof RuntimeModelObject )
                    {
                        ( (RuntimeModelObject) target.getMessages() ).clear();
                    }
                }
            }
        }

        if ( source.getProperties() != null )
        {
            if ( target.getProperties() == null )
            {
                target.setProperties( new RuntimeProperties() );
            }

            for ( int i = 0, s0 = source.getProperties().getProperty().size(); i < s0; i++ )
            {
                final Property p = source.getProperties().getProperty().get( i );

                if ( target.getProperties().getProperty( p.getName() ) == null )
                {
                    target.getProperties().getProperty().add( p );

                    if ( target.getProperties() instanceof RuntimeModelObject )
                    {
                        ( (RuntimeModelObject) target.getProperties() ).clear();
                    }
                }
            }
        }

        if ( source.getDependencies() != null )
        {
            if ( target.getDependencies() == null )
            {
                target.setDependencies( new RuntimeDependencies() );
            }

            for ( int i = 0, s0 = source.getDependencies().getDependency().size(); i < s0; i++ )
            {
                final Dependency sd = source.getDependencies().getDependency().get( i );
                final Dependency td = target.getDependencies().getDependency( sd.getName() );

                if ( td == null )
                {
                    target.getDependencies().getDependency().add( sd );

                    if ( target.getDependencies() instanceof RuntimeModelObject )
                    {
                        ( (RuntimeModelObject) target.getDependencies() ).clear();
                    }
                }
                else
                {
                    this.collectDependencies( sd, td );
                }
            }
        }
    }

    private void copyModules()
    {
        for ( int i = 0, s0 = this.getModule().size(); i < s0; i++ )
        {
            final Module m = this.getModule().get( i );
            this.getModule().set( i, RuntimeModelObjects.getInstance().copyOf( m ) );
        }
    }

    // SECTION-END
    // SECTION-START[RuntimeModelObject]
    public void gc()
    {
        gcMap( this.specificationsCache );
        gcMap( this.implementationsCache );
        gcMap( this.specificationsByImplemenationIdentifierCache );
        gcMap( this.dependenciesByImplementationIdentifierCache );
        gcMap( this.propertiesByImplementationIdentifierCache );
        gcMap( this.specifiedPropertiesByImplementationIdentifierCache );
        gcMap( this.messagesByImplementationIdentifierCache );
        gcMap( this.implementationsBySpecificationIdentifierCache );
        this.gcOrClear( true, false );
    }

    public void clear()
    {
        synchronized ( this.anyObjectsByImplemenationIdentifierCache )
        {
            this.anyObjectsByImplemenationIdentifierCache.clear();
        }
        synchronized ( this.dependenciesByImplementationIdentifierCache )
        {
            this.dependenciesByImplementationIdentifierCache.clear();
        }
        synchronized ( this.implementationByClassNameCache )
        {
            this.implementationByClassNameCache.clear();
        }
        synchronized ( this.implementationByIdentifierCache )
        {
            this.implementationByIdentifierCache.clear();
        }
        synchronized ( this.implementationByObjectClassNameCache )
        {
            this.implementationByObjectClassNameCache.clear();
        }
        synchronized ( this.implementationBySpecificationAndNameCache )
        {
            this.implementationBySpecificationAndNameCache.clear();
        }
        synchronized ( this.implementationsBySpecificationIdentifierCache )
        {
            this.implementationsBySpecificationIdentifierCache.clear();
        }
        synchronized ( this.implementationsCache )
        {
            this.implementationsCache.clear();
        }
        synchronized ( this.messagesByImplementationIdentifierCache )
        {
            this.messagesByImplementationIdentifierCache.clear();
        }
        synchronized ( this.moduleByImplementationIdentifierCache )
        {
            this.moduleByImplementationIdentifierCache.clear();
        }
        synchronized ( this.moduleBySpecificationIdentifierCache )
        {
            this.moduleBySpecificationIdentifierCache.clear();
        }
        synchronized ( this.modulesByNameCache )
        {
            this.modulesByNameCache.clear();
        }
        synchronized ( this.propertiesByImplementationIdentifierCache )
        {
            this.propertiesByImplementationIdentifierCache.clear();
        }
        synchronized ( this.specificationByClassNameCache )
        {
            this.specificationByClassNameCache.clear();
        }
        synchronized ( this.specificationByIdentifierCache )
        {
            this.specificationByIdentifierCache.clear();
        }
        synchronized ( this.specificationsByImplemenationIdentifierCache )
        {
            this.specificationsByImplemenationIdentifierCache.clear();
        }
        synchronized ( this.specificationsCache )
        {
            this.specificationsCache.clear();
        }
        synchronized ( this.specifiedPropertiesByImplementationIdentifierCache )
        {
            this.specifiedPropertiesByImplementationIdentifierCache.clear();
        }

        this.gcOrClear( false, true );
    }

    private void gcOrClear( final boolean gc, final boolean clear )
    {
        if ( this.getAuthors() instanceof RuntimeModelObject )
        {
            if ( gc )
            {
                ( (RuntimeModelObject) this.getAuthors() ).gc();
            }
            if ( clear )
            {
                ( (RuntimeModelObject) this.getAuthors() ).clear();
            }
        }
        if ( this.getDocumentation() instanceof RuntimeModelObject )
        {
            if ( gc )
            {
                ( (RuntimeModelObject) this.getDocumentation() ).gc();
            }
            if ( clear )
            {
                ( (RuntimeModelObject) this.getDocumentation() ).clear();
            }
        }

        this.gcOrClearModules( gc, clear );
    }

    private void gcOrClearModules( final boolean gc, final boolean clear )
    {

        for ( int i = 0, s0 = this.getModule().size(); i < s0; i++ )
        {
            final Module m = this.getModule().get( i );
            if ( m instanceof RuntimeModelObject )
            {
                if ( gc )
                {
                    ( (RuntimeModelObject) m ).gc();
                }
                if ( clear )
                {
                    ( (RuntimeModelObject) m ).clear();
                }
            }
        }
    }

    private static void gcMap( final Map<?, ?> map )
    {
        synchronized ( map )
        {
            for ( Map.Entry<?, ?> e : map.entrySet() )
            {
                if ( e.getValue() instanceof RuntimeModelObject )
                {
                    ( (RuntimeModelObject) e.getValue() ).gc();
                }
            }
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code RuntimeModules} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
    public RuntimeModules()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
