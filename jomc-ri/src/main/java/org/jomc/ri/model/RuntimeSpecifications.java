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

import java.text.ParseException;
import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;
import org.jomc.model.ModelObjectException;
import org.jomc.model.Specification;
import org.jomc.model.SpecificationReference;
import org.jomc.model.Specifications;
import static org.jomc.ri.model.RuntimeModelObjects.createMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Specifications}.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.model.RuntimeSpecifications</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC &#8273; RI &#8273; RuntimeSpecifications</dd>
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
public class RuntimeSpecifications extends Specifications implements RuntimeModelObject
{
    // SECTION-START[RuntimeSpecifications]

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Specification> specificationsByIdentifierCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Specification> specificationsByClassCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, SpecificationReference> referencesByIdentifierCache = createMap();

    /**
     * Creates a new {@code RuntimeSpecifications} instance by deeply copying a given {@code Specifications} instance.
     *
     * @param specifications The instance to copy.
     *
     * @throws NullPointerException if {@code specifications} is {@code null}.
     */
    public RuntimeSpecifications( final Specifications specifications )
    {
        super( specifications );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }

        this.copySpecifications();
        this.copyReferences();
    }

    /**
     * Gets a specification for a given identifier from the list of specifications.
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
     * @see #getSpecification()
     * @see Specification#getIdentifier()
     * @see #clear()
     */
    @Override
    public Specification getSpecification( final String specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        synchronized ( this.specificationsByIdentifierCache )
        {
            Specification s = this.specificationsByIdentifierCache.get( specification );

            if ( s == null && !this.specificationsByIdentifierCache.containsKey( specification ) )
            {
                s = super.getSpecification( specification );
                this.specificationsByIdentifierCache.put( specification, s );
            }

            return s;
        }
    }

    /**
     * Gets a specification for a given class from the list of specifications.
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
     * @see #getSpecification()
     * @see Specification#isClassDeclaration()
     * @see Specification#getClazz()
     * @see #clear()
     */
    @Override
    public Specification getSpecification( final Class<?> specification ) throws ModelObjectException
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        synchronized ( this.specificationsByClassCache )
        {
            Specification s = this.specificationsByClassCache.get( specification.getName() );

            if ( s == null && !this.specificationsByClassCache.containsKey( specification.getName() ) )
            {
                s = super.getSpecification( specification );
                this.specificationsByClassCache.put( specification.getName(), s );
            }

            return s;
        }
    }

    /**
     * Gets a specification reference for a given identifier from the list of references.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param specification The identifier of the reference to return.
     *
     * @return The first matching specification reference or {@code null}, if no such specification reference is found.
     *
     * @throws NullPointerException if {@code specification} is {@code null}.
     *
     * @see #getReference()
     * @see SpecificationReference#getIdentifier()
     * @see #clear()
     */
    @Override
    public SpecificationReference getReference( final String specification )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        synchronized ( this.referencesByIdentifierCache )
        {
            SpecificationReference r = this.referencesByIdentifierCache.get( specification );

            if ( r == null && !this.referencesByIdentifierCache.containsKey( specification ) )
            {
                r = super.getReference( specification );
                this.referencesByIdentifierCache.put( specification, r );
            }

            return r;
        }
    }

    private void copySpecifications()
    {
        for ( int i = 0, s0 = this.getSpecification().size(); i < s0; i++ )
        {
            final Specification s = this.getSpecification().get( i );
            this.getSpecification().set( i, RuntimeModelObjects.getInstance().copyOf( s ) );
        }
    }

    private void copyReferences()
    {
        for ( int i = 0, s0 = this.getReference().size(); i < s0; i++ )
        {
            final SpecificationReference r = this.getReference().get( i );
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
        synchronized ( this.specificationsByClassCache )
        {
            this.specificationsByClassCache.clear();
        }
        synchronized ( this.specificationsByIdentifierCache )
        {
            this.specificationsByIdentifierCache.clear();
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

        this.gcOrClearReferences( gc, clear );
        this.gcOrClearSpecifications( gc, clear );
    }

    private void gcOrClearSpecifications( final boolean gc, final boolean clear )
    {
        for ( int i = 0, s0 = this.getSpecification().size(); i < s0; i++ )
        {
            final Specification s = this.getSpecification().get( i );
            if ( s instanceof RuntimeModelObject )
            {
                if ( gc )
                {
                    ( (RuntimeModelObject) s ).gc();
                }
                if ( clear )
                {
                    ( (RuntimeModelObject) s ).clear();
                }
            }
        }
    }

    private void gcOrClearReferences( final boolean gc, final boolean clear )
    {
        for ( int i = 0, s0 = this.getReference().size(); i < s0; i++ )
        {
            final SpecificationReference r = this.getReference().get( i );
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
    /** Creates a new {@code RuntimeSpecifications} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
    public RuntimeSpecifications()
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
