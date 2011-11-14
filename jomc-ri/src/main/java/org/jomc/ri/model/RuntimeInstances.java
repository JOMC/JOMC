// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
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
import org.jomc.model.Instance;
import org.jomc.model.Instances;
import static org.jomc.ri.model.RuntimeModelObjects.createMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Instances}.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.ri.model.RuntimeInstances>}.
 *   It provides objects named {@code <JOMC RI RuntimeInstances>} of the following specifications:
 *
 *   <ul>
 *     <li>{@code <org.jomc.ri.model.RuntimeModelObject>} at specification level 1.2.</li>
 *   </ul>
 *
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.2
 * @version 1.2
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class RuntimeInstances extends Instances implements RuntimeModelObject
{
    // SECTION-START[RuntimeInstances]

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Instance> instancesByIdentifierCache = createMap();

    /**
     * Creates a new {@code RuntimeInstances} instance by deeply copying a given {@code Instances} instance.
     *
     * @param instances The instance to copy.
     *
     * @throws NullPointerException if {@code instances} is {@code null}.
     */
    public RuntimeInstances( final Instances instances )
    {
        super( instances );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }

        this.copyInstances();
    }

    /**
     * Gets an instance for a given identifier from the list of instances.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param identifier The identifier of the instance to return.
     *
     * @return The first matching instance or {@code null}, if no such instance is found.
     *
     * @throws NullPointerException if {@code identifier} is {@code null}.
     *
     * @see #getInstance()
     * @see Instance#getIdentifier()
     * @see #clear()
     */
    @Override
    public Instance getInstance( final String identifier )
    {
        if ( identifier == null )
        {
            throw new NullPointerException( "identifier" );
        }

        synchronized ( this.instancesByIdentifierCache )
        {
            Instance i = this.instancesByIdentifierCache.get( identifier );

            if ( i == null && !this.instancesByIdentifierCache.containsKey( identifier ) )
            {
                i = super.getInstance( identifier );
                this.instancesByIdentifierCache.put( identifier, i );
            }

            return i;
        }
    }

    private void copyInstances()
    {
        for ( int i = 0, s0 = this.getInstance().size(); i < s0; i++ )
        {
            final Instance inst = this.getInstance().get( i );
            this.getInstance().set( i, RuntimeModelObjects.getInstance().copyOf( inst ) );
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
        synchronized ( this.instancesByIdentifierCache )
        {
            this.instancesByIdentifierCache.clear();
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

        this.gcOrClearInstances( gc, clear );
    }

    private void gcOrClearInstances( final boolean handle, final boolean prepare )
    {
        for ( int i = 0, s0 = this.getInstance().size(); i < s0; i++ )
        {
            final Instance inst = this.getInstance().get( i );
            if ( inst instanceof RuntimeModelObject )
            {
                if ( handle )
                {
                    ( (RuntimeModelObject) inst ).gc();
                }
                if ( prepare )
                {
                    ( (RuntimeModelObject) inst ).clear();
                }
            }
        }
    }
    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code RuntimeInstances} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public RuntimeInstances()
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
