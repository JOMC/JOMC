// SECTION-START[License Header]
/*
 *  JOMC Sequences RI
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
package org.jomc.sequences.ri;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Calendar;
import java.util.TimeZone;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequencesSystemException;
import org.jomc.sequences.model.SequenceType;

// SECTION-START[Implementation Comment]
/**
 * {@code SequenceMapper} reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.sequences.ri.SequenceMapper} {@code 1.0}<blockquote>
 * Object applies to Singleton scope.
 * State does not need to be retained across operations to operate as
 * specified.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class DefaultSequenceMapper implements SequenceMapper
{
    // SECTION-START[SequenceMapper]

    public Sequence map( final SequenceType source, final Sequence target )
        throws NullPointerException, SequencesSystemException
    {
        if ( source == null )
        {
            throw new NullPointerException( "source" );
        }
        if ( target == null )
        {
            throw new NullPointerException( "target" );
        }

        target.setIncrement( source.getIncrement() );
        target.setMaximum( source.getMaximum() );
        target.setMinimum( source.getMinimum() );
        target.setName( source.getName() );
        target.setValue( source.getValue() );
        this.injectRevision( target, source.getRevision() );
        this.injectDate( target, source.getJpaDate().getTimeInMillis() );

        return target;
    }

    public SequenceType map( final Sequence source, final SequenceType target )
        throws NullPointerException, SequencesSystemException
    {
        if ( source == null )
        {
            throw new NullPointerException( "source" );
        }
        if ( target == null )
        {
            throw new NullPointerException( "target" );
        }

        target.setIncrement( source.getIncrement() );
        target.setMaximum( source.getMaximum() );
        target.setMinimum( source.getMinimum() );
        target.setName( source.getName() );
        target.setValue( source.getValue() );
        target.setRevision( source.getRevision() );

        final Calendar c = Calendar.getInstance();
        c.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
        c.setTimeInMillis( source.getDate() );

        target.setJpaDate( c );

        return target;
    }

    // SECTION-END
    // SECTION-START[DefaultSequenceMapper]

    /**
     * Sets the value of property {@code revision} of a given sequence using reflection.
     *
     * @param s The sequence to update.
     * @param value The new value for property {@code revision}.
     *
     * @throws SequencesSystemException if injecting {@code value} fails unexpectedly.
     */
    protected void injectRevision( final Sequence s, final long value ) throws SequencesSystemException
    {
        this.injectFieldValue( s, "revision", Long.valueOf( value ) );
    }

    /**
     * Sets the value of property {@code date} of a given sequence using reflection.
     *
     * @param s The sequence to update.
     * @param value The new value for property {@code date}.
     *
     * @throws SequencesSystemException if injecting {@code value} fails unexpectedly.
     */
    protected void injectDate( final Sequence s, final long value ) throws SequencesSystemException
    {
        this.injectFieldValue( s, "date", Long.valueOf( value ) );
    }

    /**
     * Sets the value of a field of a given object using reflection.
     *
     * @param object The object to update.
     * @param fieldName The name of the field to update.
     * @param value The new value for field {@code fieldName}.
     *
     * @throws SequencesSystemException if setting {@code value} fails unexpectedly.
     */
    protected void injectFieldValue( final Object object, final String fieldName, final Object value )
        throws SequencesSystemException
    {
        AccessController.doPrivileged( new PrivilegedAction<Object>()
        {

            public Object run()
            {
                Field field = null;

                try
                {
                    field = object.getClass().getDeclaredField( fieldName );
                    field.setAccessible( true );
                    field.set( object, value );
                    field.setAccessible( false );
                    return null;
                }
                catch ( Exception e )
                {
                    throw new SequencesSystemException( e );
                }
            }

        } );
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public DefaultSequenceMapper()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
