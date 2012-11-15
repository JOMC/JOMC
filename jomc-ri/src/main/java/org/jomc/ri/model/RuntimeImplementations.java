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

import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;
import org.jomc.model.Implementation;
import org.jomc.model.ImplementationReference;
import org.jomc.model.Implementations;
import static org.jomc.ri.model.RuntimeModelObjects.createMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Implementations}.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.model.RuntimeImplementations</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC &#8273; RI &#8273; RuntimeImplementations</dd>
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
public class RuntimeImplementations extends Implementations implements RuntimeModelObject
{
    // SECTION-START[RuntimeImplementations]

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Implementation> implementationsByIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Implementation> implementationsByClassCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Implementation> implementationsByNameCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, ImplementationReference> referencesByIdentifierCache = createMap();

    /**
     * Creates a new {@code RuntimeImplementations} instance by deeply copying a given {@code Implementations} instance.
     *
     * @param implementations The instance to copy.
     *
     * @throws NullPointerException if {@code implementations} is {@code null}.
     */
    public RuntimeImplementations( final Implementations implementations )
    {
        super( implementations );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }

        this.copyImplementations();
        this.copyImplementationReferences();
    }

    /**
     * Gets an implementation for a given identifier from the list of implementations.
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
     * @see #getImplementation()
     * @see Implementation#getIdentifier()
     * @see #clear()
     */
    @Override
    public Implementation getImplementation( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.implementationsByIdentifierCache )
        {
            Implementation i = this.implementationsByIdentifierCache.get( implementation );

            if ( i == null && !this.implementationsByIdentifierCache.containsKey( implementation ) )
            {
                i = super.getImplementation( implementation );
                this.implementationsByIdentifierCache.put( implementation, i );
            }

            return i;
        }
    }

    /**
     * Gets an implementation for a given class from the list of implementations.
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
     *
     * @see #getImplementation()
     * @see Implementation#isClassDeclaration()
     * @see Implementation#getClazz()
     * @see #clear()
     */
    @Override
    public Implementation getImplementation( final Class<?> implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.implementationsByClassCache )
        {
            Implementation i = this.implementationsByClassCache.get( implementation.getName() );

            if ( i == null && !this.implementationsByClassCache.containsKey( implementation.getName() ) )
            {
                i = super.getImplementation( implementation );
                this.implementationsByClassCache.put( implementation.getName(), i );
            }

            return i;
        }
    }

    /**
     * Gets an implementation for a given name from the list of implementations.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param name The name of the implementation to return.
     *
     * @return The first matching implementation or {@code null}, if no such implementation is found.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     *
     * @see #getImplementation()
     * @see Implementation#getName()
     * @see #clear()
     */
    @Override
    public Implementation getImplementationByName( final String name )
    {
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }

        synchronized ( this.implementationsByNameCache )
        {
            Implementation i = this.implementationsByNameCache.get( name );

            if ( i == null && !this.implementationsByNameCache.containsKey( name ) )
            {
                i = super.getImplementationByName( name );
                this.implementationsByNameCache.put( name, i );
            }

            return i;
        }
    }

    /**
     * Gets an implementation reference for a given identifier from the list of references.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param implementation The identifier of the reference to return.
     *
     * @return The first matching implementation reference or {@code null}, if no such reference is found.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     *
     * @see #getReference()
     * @see ImplementationReference#getIdentifier()
     * @see #clear()
     */
    @Override
    public ImplementationReference getReference( final String implementation )
    {
        if ( implementation == null )
        {
            throw new NullPointerException( "implementation" );
        }

        synchronized ( this.referencesByIdentifierCache )
        {
            ImplementationReference r = this.referencesByIdentifierCache.get( implementation );

            if ( r == null && !this.referencesByIdentifierCache.containsKey( implementation ) )
            {
                r = super.getReference( implementation );
                this.referencesByIdentifierCache.put( implementation, r );
            }

            return r;
        }
    }

    private void copyImplementations()
    {
        for ( int i = 0, s0 = this.getImplementation().size(); i < s0; i++ )
        {
            final Implementation impl = this.getImplementation().get( i );
            this.getImplementation().set( i, RuntimeModelObjects.getInstance().copyOf( impl ) );
        }
    }

    private void copyImplementationReferences()
    {
        for ( int i = 0, s0 = this.getReference().size(); i < s0; i++ )
        {
            final ImplementationReference r = this.getReference().get( i );
            this.getReference().set( i, RuntimeModelObjects.getInstance().copyOf( r ) );
        }
    }

    // SECTION-END
    // SECTION-START[RuntimeModelObject]
    public void gc()
    {
        this.gcOrClear( true, false );
    }

    public void clear()
    {
        synchronized ( this.implementationsByClassCache )
        {
            this.implementationsByClassCache.clear();
        }
        synchronized ( this.implementationsByIdentifierCache )
        {
            this.implementationsByIdentifierCache.clear();
        }
        synchronized ( this.implementationsByNameCache )
        {
            this.implementationsByNameCache.clear();
        }
        synchronized ( this.referencesByIdentifierCache )
        {
            this.referencesByIdentifierCache.clear();
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

        this.gcOrClearImplementationReferences( gc, clear );
        this.gcOrClearImplementations( gc, clear );
    }

    private void gcOrClearImplementations( final boolean gc, final boolean clear )
    {
        for ( int i = 0, s0 = this.getImplementation().size(); i < s0; i++ )
        {
            final Implementation impl = this.getImplementation().get( i );
            if ( impl instanceof RuntimeModelObject )
            {
                if ( gc )
                {
                    ( (RuntimeModelObject) impl ).gc();
                }
                if ( clear )
                {
                    ( (RuntimeModelObject) impl ).clear();
                }
            }
        }
    }

    private void gcOrClearImplementationReferences( final boolean gc, final boolean clear )
    {
        for ( int i = 0, s0 = this.getReference().size(); i < s0; i++ )
        {
            final ImplementationReference r = this.getReference().get( i );
            if ( r instanceof RuntimeModelObject )
            {
                if ( gc )
                {
                    ( (RuntimeModelObject) r ).gc();
                }
                if ( clear )
                {
                    ( (RuntimeModelObject) r ).clear();
                }
            }
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code RuntimeImplementations} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
    public RuntimeImplementations()
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
