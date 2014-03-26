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
import org.jomc.model.Message;
import org.jomc.model.MessageReference;
import org.jomc.model.Messages;
import static org.jomc.ri.model.RuntimeModelObjects.createMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Messages}.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.model.RuntimeMessages</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ RI ⁑ RuntimeMessages</dd>
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
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.8-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.8/jomc-tools-1.8-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class RuntimeMessages extends Messages implements RuntimeModelObject
{
    // SECTION-START[RuntimeMessages]

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Message> messagesByNameCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, MessageReference> referencesByNameCache = createMap();

    /**
     * Creates a new {@code RuntimeMessages} instance by deeply copying a given {@code Messages} instance.
     *
     * @param messages The instance to copy.
     *
     * @throws NullPointerException if {@code messages} is {@code null}.
     */
    public RuntimeMessages( final Messages messages )
    {
        super( messages );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }

        this.copyMessages();
        this.copyMessageReferences();
    }

    /**
     * Gets a message for a given name from the list of messages.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param name The name of the message to return.
     *
     * @return The first matching message or {@code null}, if no such message is found.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     *
     * @see #getMessage()
     * @see Message#getName()
     * @see #clear()
     */
    @Override
    public Message getMessage( final String name )
    {
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }

        synchronized ( this.messagesByNameCache )
        {
            Message m = this.messagesByNameCache.get( name );

            if ( m == null && !this.messagesByNameCache.containsKey( name ) )
            {
                m = super.getMessage( name );
                this.messagesByNameCache.put( name, m );
            }

            return m;
        }
    }

    /**
     * Gets a message reference for a given name from the list of references.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param name The name of the message reference to return.
     *
     * @return The first matching message reference or {@code null}, if no such reference is found.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     *
     * @see #getReference()
     * @see MessageReference#getName()
     * @see #clear()
     */
    @Override
    public MessageReference getReference( final String name )
    {
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }

        synchronized ( this.referencesByNameCache )
        {
            MessageReference r = this.referencesByNameCache.get( name );

            if ( r == null && !this.referencesByNameCache.containsKey( name ) )
            {
                r = super.getReference( name );
                this.referencesByNameCache.put( name, r );
            }

            return r;
        }
    }

    private void copyMessages()
    {
        for ( int i = 0, s0 = this.getMessage().size(); i < s0; i++ )
        {
            final Message m = this.getMessage().get( i );
            this.getMessage().set( i, RuntimeModelObjects.getInstance().copyOf( m ) );
        }
    }

    private void copyMessageReferences()
    {
        for ( int i = 0, s0 = this.getReference().size(); i < s0; i++ )
        {
            final MessageReference r = this.getReference().get( i );
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
        synchronized ( this.messagesByNameCache )
        {
            this.messagesByNameCache.clear();
        }
        synchronized ( this.referencesByNameCache )
        {
            this.referencesByNameCache.clear();
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

        this.gcOrClearMessageReferences( gc, clear );
        this.gcOrClearMessages( gc, clear );
    }

    private void gcOrClearMessages( final boolean gc, final boolean clear )
    {
        for ( int i = 0, s0 = this.getMessage().size(); i < s0; i++ )
        {
            final Message m = this.getMessage().get( i );
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

    private void gcOrClearMessageReferences( final boolean gc, final boolean clear )
    {
        for ( int i = 0, s0 = this.getReference().size(); i < s0; i++ )
        {
            final MessageReference r = this.getReference().get( i );
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
    /** Creates a new {@code RuntimeMessages} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.8-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.8/jomc-tools-1.8-SNAPSHOT" )
    public RuntimeMessages()
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
