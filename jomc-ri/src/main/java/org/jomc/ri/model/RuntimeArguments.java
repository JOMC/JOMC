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
import org.jomc.model.Argument;
import org.jomc.model.Arguments;
import static org.jomc.ri.model.RuntimeModelObjects.createMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Arguments}.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.model.RuntimeArguments</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC RI RuntimeArguments</dd>
 *   <dt><b>Specifications:</b></dt>
 *     <dd>org.jomc.ri.model.RuntimeModelObject @ 1.2</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.2
 * @version 1.2
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-2.0-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class RuntimeArguments extends Arguments implements RuntimeModelObject
{
    // SECTION-START[RuntimeArguments]

    /** Cache map. */
    @XmlTransient
    private transient final Map<String, Argument> argumentsByNameCache = createMap();

    /** Cache map. */
    @XmlTransient
    private transient final Map<Number, Argument> argumentsByIndexCache = createMap();

    /**
     * Creates a new {@code RuntimeArguments} instance by deeply copying a given {@code Arguments} instance.
     *
     * @param arguments The instance to copy.
     *
     * @throws NullPointerException if {@code arguments} is {@code null}.
     */
    public RuntimeArguments( final Arguments arguments )
    {
        super( arguments );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }

        this.copyArguments();
    }

    /**
     * Gets an argument for a given name from the list of arguments.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param name The name of the argument to return.
     *
     * @return The first matching argument or {@code null}, if no such argument is found.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     *
     * @see #getArgument()
     * @see Argument#getName()
     * @see #clear()
     */
    @Override
    public Argument getArgument( final String name )
    {
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }

        synchronized ( this.argumentsByNameCache )
        {
            Argument a = this.argumentsByNameCache.get( name );

            if ( a == null && !this.argumentsByNameCache.containsKey( name ) )
            {
                a = super.getArgument( name );
                this.argumentsByNameCache.put( name, a );
            }

            return a;
        }
    }

    /**
     * Gets an argument for a given index from the list of arguments.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param index The index of the argument to return.
     *
     * @return The first matching argument or {@code null}, if no such argument is found.
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative, greater or equal to the size of the list of
     * arguments.
     *
     * @see #getArgument()
     * @see Argument#getIndex()
     * @see #clear()
     */
    @Override
    public Argument getArgument( final int index )
    {
        if ( index < 0 || index >= this.getArgument().size() )
        {
            throw new IndexOutOfBoundsException( Integer.toString( index ) );
        }

        synchronized ( this.argumentsByIndexCache )
        {
            Argument a = this.argumentsByIndexCache.get( index );

            if ( a == null && !this.argumentsByIndexCache.containsKey( index ) )
            {
                a = super.getArgument( index );
                this.argumentsByIndexCache.put( index, a );
            }

            return a;
        }
    }

    private void copyArguments()
    {
        for ( int i = 0, s0 = this.getArgument().size(); i < s0; i++ )
        {
            final Argument a = this.getArgument().get( i );
            this.getArgument().set( i, RuntimeModelObjects.getInstance().copyOf( a ) );
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
        synchronized ( this.argumentsByIndexCache )
        {
            this.argumentsByIndexCache.clear();
        }
        synchronized ( this.argumentsByNameCache )
        {
            this.argumentsByNameCache.clear();
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

        this.gcOrClearArguments( gc, clear );
    }

    private void gcOrClearArguments( final boolean gc, final boolean clear )
    {
        for ( int i = 0, s0 = this.getArgument().size(); i < s0; i++ )
        {
            final Argument a = this.getArgument().get( i );
            if ( a instanceof RuntimeModelObject )
            {
                if ( gc )
                {
                    ( (RuntimeModelObject) a ).gc();
                }
                if ( clear )
                {
                    ( (RuntimeModelObject) a ).clear();
                }
            }
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code RuntimeArguments} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-2.0-SNAPSHOT" )
    public RuntimeArguments()
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
