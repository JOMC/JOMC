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
import org.jomc.model.Dependencies;
import org.jomc.model.Dependency;
import static org.jomc.ri.model.RuntimeModelObjects.createMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Dependencies}.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.model.RuntimeDependencies</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ RI ⁑ RuntimeDependencies</dd>
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
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.5", comments = "See http://www.jomc.org/jomc/1.5/jomc-tools-1.5" )
// </editor-fold>
// SECTION-END
public class RuntimeDependencies extends Dependencies implements RuntimeModelObject
{
    // SECTION-START[RuntimeDependencies]

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Dependency> dependenciesByNameCache = createMap();

    /**
     * Creates a new {@code RuntimeDependencies} instance by deeply copying a given {@code Dependencies} instance.
     *
     * @param dependencies The instance to copy.
     *
     * @throws NullPointerException if {@code dependencies} is {@code null}.
     */
    public RuntimeDependencies( final Dependencies dependencies )
    {
        super( dependencies );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }

        this.copyDependencies();
    }

    /**
     * Gets a dependency for a given name from the list of dependencies.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param name The name of the dependency to return.
     *
     * @return The first matching dependency or {@code null}, if no such dependency is found.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     *
     * @see #getDependency()
     * @see Dependency#getName()
     * @see #clear()
     */
    @Override
    public Dependency getDependency( final String name )
    {
        if ( name == null )
        {
            throw new NullPointerException( "identifier" );
        }

        synchronized ( this.dependenciesByNameCache )
        {
            Dependency d = this.dependenciesByNameCache.get( name );

            if ( d == null && !this.dependenciesByNameCache.containsKey( name ) )
            {
                d = super.getDependency( name );
                this.dependenciesByNameCache.put( name, d );
            }

            return d;
        }
    }

    private void copyDependencies()
    {
        for ( int i = 0, s0 = this.getDependency().size(); i < s0; i++ )
        {
            final Dependency d = this.getDependency().get( i );
            this.getDependency().set( i, RuntimeModelObjects.getInstance().copyOf( d ) );
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
        synchronized ( this.dependenciesByNameCache )
        {
            this.dependenciesByNameCache.clear();
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

        this.gcOrClearDependencies( gc, clear );
    }

    private void gcOrClearDependencies( final boolean gc, final boolean clear )
    {

        for ( int i = 0, s0 = this.getDependency().size(); i < s0; i++ )
        {
            final Dependency d = this.getDependency().get( i );
            if ( d instanceof RuntimeModelObject )
            {
                if ( gc )
                {
                    ( (RuntimeModelObject) d ).gc();
                }
                if ( clear )
                {
                    ( (RuntimeModelObject) d ).clear();
                }
            }
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code RuntimeDependencies} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.5", comments = "See http://www.jomc.org/jomc/1.5/jomc-tools-1.5" )
    public RuntimeDependencies()
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
