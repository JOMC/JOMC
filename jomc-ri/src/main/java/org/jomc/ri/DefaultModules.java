// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) 2011 The JOMC Project
 *   Copyright (C) 2005 - 2011 Christian Schulte <schulte2005@users.sourceforge.net>
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

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.jomc.model.Dependencies;
import org.jomc.model.Implementation;
import org.jomc.model.Implementations;
import org.jomc.model.Instance;
import org.jomc.model.Messages;
import org.jomc.model.Module;
import org.jomc.model.Modules;
import org.jomc.model.Properties;
import org.jomc.model.Specification;
import org.jomc.model.Specifications;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Default {@code Modules} implementation.
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="2" nowrap><font size="+2">Implementation</font></th>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Identifier:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code org.jomc.ri.DefaultModules}</td>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Name:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code JOMC RI}</td>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Flags:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code none}</td>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Version:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code 1.2}</td>
 *     </tr>
 *   </table>
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.2
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class DefaultModules extends Modules
{
    // SECTION-START[DefaultModules]

    /** Cache reference. */
    private Reference<Map<String, Module>> modulesByNameCache;

    /** Cache reference. */
    private Reference<Specifications> specificationsCache;

    /** Cache reference. */
    private Reference<Implementations> implementationsCache;

    /** Cache reference. */
    private Reference<Map<String, Module>> moduleBySpecificationIdentifierCache;

    /** Cache reference. */
    private Reference<Map<String, Module>> moduleByImplementationIdentifierCache;

    /** Cache reference. */
    private Reference<Map<String, Specification>> specificationByIdentifierCache;

    /** Cache reference. */
    private Reference<Map<String, Specification>> specificationByClassNameCache;

    /** Cache reference. */
    private Reference<Map<String, Specifications>> specificationsByImplemenationIdentifierCache;

    /** Cache reference. */
    private Reference<Map<String, Implementation>> implementationByIdentifierCache;

    /** Cache reference. */
    private Reference<Map<String, Implementation>> implementationByClassNameCache;

    /** Cache reference. */
    private Reference<Map<String, Implementation>> implementationByObjectClassNameCache;

    /** Cache reference. */
    private Reference<Map<String, Dependencies>> dependenciesByImplementationIdentifierCache;

    /** Cache reference. */
    private Reference<Map<String, Properties>> propertiesByImplementationIdentifierCache;

    /** Cache reference. */
    private Reference<Map<String, Properties>> specifiedPropertiesByImplementationIdentifierCache;

    /** Cache reference. */
    private Reference<Map<String, Messages>> messagesByImplementationIdentifierCache;

    /** Cache reference. */
    private Reference<Map<String, Implementations>> implementationsBySpecificationIdentifierCache;

    /**
     * Creates a new {@code DefaultModules} instance by deeply copying a given {@code DefaultModules} instance taking a
     * map backing the instance.
     *
     * @param o The instance to copy.
     * @param objects The map backing the instance.
     *
     * @throws NullPointerException if {@code o} or {@code objects} is {@code null}.
     */
    public DefaultModules( final DefaultModules modules )
    {
        super( modules );
    }

    /**
     * Creates a new {@code DefaultModules} instance by deeply copying a given {@code Modules} instance taking a map
     * backing the instance.
     *
     * @param o The instance to copy.
     * @param objects The map backing the instance.
     *
     * @throws NullPointerException if {@code o} or {@code objects} is {@code null}.
     */
    public DefaultModules( final Modules modules, final Map<Object, Instance> objects )
    {
        super( modules, objects );
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Module getModule( final String name )
    {
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }

        Map<String, Module> map = fromReference( this.modulesByNameCache );

        if ( map == null )
        {
            map = createMap();
            this.modulesByNameCache = toReference( map );
        }

        Module m = map.get( name );

        if ( m == null )
        {
            m = super.getModule( name );
            map.put( name, m );
        }

        return m;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Specifications getSpecifications()
    {
        Specifications s = fromReference( this.specificationsCache );

        if ( s == null )
        {
            s = super.getSpecifications();
            this.specificationsCache = toReference( s );
        }

        return s;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Implementations getImplementations()
    {
        Implementations i = fromReference( this.implementationsCache );

        if ( i == null )
        {
            i = super.getImplementations();
            this.implementationsCache = toReference( i );
        }

        return i;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Module getModuleOfSpecification( final String specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        Map<String, Module> map = fromReference( this.moduleBySpecificationIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.moduleBySpecificationIdentifierCache = toReference( map );
        }

        Module m = map.get( specification );

        if ( m == null )
        {
            m = super.getModuleOfSpecification( specification );
            map.put( specification, m );
        }

        return m;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Module getModuleOfImplementation( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        Map<String, Module> map = fromReference( this.moduleByImplementationIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.moduleByImplementationIdentifierCache = toReference( map );
        }

        Module m = map.get( implementation );

        if ( m == null )
        {
            m = super.getModuleOfImplementation( implementation );
            map.put( implementation, m );
        }

        return m;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Specification getSpecification( final String specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        Map<String, Specification> map = fromReference( this.specificationByIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.specificationByIdentifierCache = toReference( map );
        }

        Specification s = map.get( specification );

        if ( s == null )
        {
            s = super.getSpecification( specification );
            map.put( specification, s );
        }

        return s;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Specification getSpecification( final Class<?> specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        Map<String, Specification> map = fromReference( this.specificationByClassNameCache );

        if ( map == null )
        {
            map = createMap();
            this.specificationByClassNameCache = toReference( map );
        }

        Specification s = map.get( specification.getName() );

        if ( s == null )
        {
            s = super.getSpecification( specification );
            map.put( specification.getName(), s );
        }

        return s;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Specifications getSpecifications( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        Map<String, Specifications> map = fromReference( this.specificationsByImplemenationIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.specificationsByImplemenationIdentifierCache = toReference( map );
        }

        Specifications s = map.get( implementation );

        if ( s == null )
        {
            s = super.getSpecifications( implementation );
            map.put( implementation, s );
        }

        return s;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Implementation getImplementation( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        Map<String, Implementation> map = fromReference( this.implementationByIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.implementationByIdentifierCache = toReference( map );
        }

        Implementation i = map.get( implementation );

        if ( i == null )
        {
            i = super.getImplementation( implementation );
            map.put( implementation, i );
        }

        return i;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Implementation getImplementation( final Class<?> implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        Map<String, Implementation> map = fromReference( this.implementationByClassNameCache );

        if ( map == null )
        {
            map = createMap();
            this.implementationByClassNameCache = toReference( map );
        }

        Implementation i = map.get( implementation.getName() );

        if ( i == null )
        {
            i = super.getImplementation( implementation );
            map.put( implementation.getName(), i );
        }

        return i;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Implementation getImplementation( final Object object )
    {
        if ( object == null )
        {
            throw new NullPointerException( "object" );
        }

        Map<String, Implementation> map = fromReference( this.implementationByObjectClassNameCache );

        if ( map == null )
        {
            map = createMap();
            this.implementationByObjectClassNameCache = toReference( map );
        }

        Implementation i = map.get( object.getClass().getName() );

        if ( i == null )
        {
            i = super.getImplementation( object );
            map.put( object.getClass().getName(), i );
        }

        return i;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Dependencies getDependencies( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        Map<String, Dependencies> map = fromReference( this.dependenciesByImplementationIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.dependenciesByImplementationIdentifierCache = toReference( map );
        }

        Dependencies d = map.get( implementation );

        if ( d == null )
        {
            d = super.getDependencies( implementation );
            map.put( implementation, d );
        }

        return d;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Properties getProperties( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        Map<String, Properties> map = fromReference( this.propertiesByImplementationIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.propertiesByImplementationIdentifierCache = toReference( map );
        }

        Properties p = map.get( implementation );

        if ( p == null )
        {
            p = super.getProperties( implementation );
            map.put( implementation, p );
        }

        return p;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Properties getSpecifiedProperties( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        Map<String, Properties> map = fromReference( this.specifiedPropertiesByImplementationIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.specifiedPropertiesByImplementationIdentifierCache = toReference( map );
        }

        Properties p = map.get( implementation );

        if ( p == null )
        {
            p = super.getSpecifiedProperties( implementation );
            map.put( implementation, p );
        }

        return p;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Messages getMessages( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        Map<String, Messages> map = fromReference( this.messagesByImplementationIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.messagesByImplementationIdentifierCache = toReference( map );
        }

        Messages m = map.get( implementation );

        if ( m == null )
        {
            m = super.getMessages( implementation );
            map.put( implementation, m );
        }

        return m;
    }

    /**
     * {@inheritDoc}
     * @see #clearCache()
     */
    @Override
    public Implementations getImplementations( final String specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        Map<String, Implementations> map = fromReference( this.implementationsBySpecificationIdentifierCache );

        if ( map == null )
        {
            map = createMap();
            this.implementationsBySpecificationIdentifierCache = toReference( map );
        }

        Implementations i = map.get( specification );

        if ( i == null )
        {
            i = super.getImplementations( specification );
            map.put( specification, i );
        }

        return i;
    }

    /**
     * Clears the cache.
     * <p><b>Note:</b> This method should be called after modifying the list of modules of the instance.</p>
     */
    public void clearCache()
    {
        this.dependenciesByImplementationIdentifierCache = null;
        this.implementationByClassNameCache = null;
        this.implementationByIdentifierCache = null;
        this.implementationByObjectClassNameCache = null;
        this.implementationsCache = null;
        this.implementationsBySpecificationIdentifierCache = null;
        this.messagesByImplementationIdentifierCache = null;
        this.moduleByImplementationIdentifierCache = null;
        this.moduleBySpecificationIdentifierCache = null;
        this.modulesByNameCache = null;
        this.propertiesByImplementationIdentifierCache = null;
        this.specificationByClassNameCache = null;
        this.specificationByIdentifierCache = null;
        this.specificationsCache = null;
        this.specificationsByImplemenationIdentifierCache = null;
        this.specifiedPropertiesByImplementationIdentifierCache = null;
    }

    /**
     * Creates and returns a deep copy of this object.
     *
     * @return A deep copy of this object.
     */
    @Override
    public DefaultModules clone()
    {
        return new DefaultModules( this );
    }

    /**
     * Creates a new {@code Map} instance.
     *
     * @param <K> The type of the keys to maintain by the new map.
     * @param <V> The type of the values to map.
     *
     * @return A new {@code Map} instance.
     */
    private static <K, V> Map<K, V> createMap()
    {
        return new HashMap<K, V>( 128 );
    }

    /**
     * Gets the referent object of a reference.
     * @param <T> The type of the referent object to get.
     * @param reference The reference to query or {@code null}.
     *
     * @return The referent object of {@code reference} or {@code null}.
     */
    private static <T> T fromReference( final Reference<T> reference )
    {
        return reference != null ? reference.get() : null;
    }

    /**
     * Creates a new {@code Reference} instance referring to a given object.
     *
     * @param <T> The type of the referent object.
     * @param referent The referent object.
     *
     * @return A new {@code Reference} referring to {@code referent}.
     */
    private static <T> Reference<T> toReference( final T referent )
    {
        return new WeakReference<T>( referent );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultModules} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public DefaultModules()
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
