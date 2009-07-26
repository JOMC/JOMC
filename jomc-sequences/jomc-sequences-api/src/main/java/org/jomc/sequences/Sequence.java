// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
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
// SECTION-END
package org.jomc.sequences;

import java.io.Serializable;
import java.util.Date;

// SECTION-START[Implementation Comment]
/**
 * Sequence of numbers.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://jomc.sourceforge.net/jomc-tools"
)
// SECTION-END
public class Sequence implements Comparable<Sequence>, Cloneable, Serializable
{
    // SECTION-START[Sequence]

    /** Constant for the name of property {@code name}. */
    public static final String PROP_NAME = "org.jomc.sequences.Sequence.PROP_NAME";

    /** Constant for the name of property {@code minimum}. */
    public static final String PROP_MINIMUM = "org.jomc.sequences.Sequence.PROP_MINIMUM";

    /** Constant for the name of property {@code maximum}. */
    public static final String PROP_MAXIMUM = "org.jomc.sequences.Sequence.PROP_MAXIMUM";

    /** Constant for the name of property {@code increment}. */
    public static final String PROP_INCREMENT = "org.jomc.sequences.Sequence.PROP_INCREMENT";

    /** Constant for the name of property {@code value}. */
    public static final String PROP_VALUE = "org.jomc.sequences.Sequence.PROP_VALUE";

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = -5986954258127229601L;

    /**
     * Revision of the sequence.
     * @serial
     */
    private long revision;

    /**
     * Date of the revision.
     * @serial
     */
    private long date;

    /**
     * Logical name of the sequence.
     * @serial
     */
    private String name;

    /**
     * Minimum value of property {@code value}.
     * @serial
     */
    private long minimum;

    /**
     * Maximum value of property {@code value}.
     * @serial
     */
    private long maximum;

    /**
     * Delta to add to the value of property {@code value} for the next value in the sequence.
     * @serial
     */
    private long increment;

    /**
     * Current value of the sequence.
     * @serial
     */
    private long value;

    /**
     * Gets the revision of the entity.
     *
     * @return The revision of the entity.
     */
    public long getRevision()
    {
        return this.revision;
    }

    /**
     * Gets the date of the revision of the entity.
     *
     * @return The date of the revision of the entity.
     */
    public long getDate()
    {
        return this.date;
    }

    /**
     * Gets the logical name of the sequence.
     *
     * @return The logical name of the sequence.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the logical name of the sequence.
     *
     * @param value The new logical name of the sequence.
     */
    public void setName( final String value )
    {
        this.name = value;
    }

    /**
     * Gets the minimum value of property {@code value}.
     *
     * @return The minimum value of property {@code value}.
     */
    public long getMinimum()
    {
        return this.minimum;
    }

    /**
     * Sets the minimum value of property {@code value}.
     *
     * @param value The new minimum value of property {@code value}.
     */
    public void setMinimum( final long value )
    {
        this.minimum = value;
    }

    /**
     * Gets the maximum value of property {@code value}.
     *
     * @return The maximum value of property {@code value}.
     */
    public long getMaximum()
    {
        return this.maximum;
    }

    /**
     * Sets the maximum value of property {@code value}.
     *
     * @param value The new maximum value of property {@code value}.
     */
    public void setMaximum( final long value )
    {
        this.maximum = value;
    }

    /**
     * Gets the delta to add to the value of property {@code value} for the next value in the sequence.
     *
     * @return The the delta to add to the value of property {@code value} for the next value in the sequence.
     */
    public long getIncrement()
    {
        return this.increment;
    }

    /**
     * Sets the delta to add to the value of property {@code value} for the next value in the sequence.
     *
     * @param value The new delta to add to the value of property {@code value} for the next value in the sequence.
     */
    public void setIncrement( final long value )
    {
        this.increment = value;
    }

    /**
     * Gets the current value of the sequence.
     *
     * @return The current value of the sequence.
     */
    public long getValue()
    {
        return this.value;
    }

    /**
     * Sets the current value of the sequence.
     *
     * @param value The current value of the sequence.
     */
    public void setValue( final long value )
    {
        this.value = value;
    }

    /**
     * Creates a string representing the properties of the instance.
     *
     * @return A string representing the properties of the instance.
     */
    private String internalString()
    {
        return new StringBuffer( 150 ).append( '{' ).
            append( "revision=" ).append( this.revision ).
            append( ", date=" ).append( new Date( this.date ) ).
            append( ", name=" ).append( this.name ).
            append( ", minimum=" ).append( this.minimum ).
            append( ", maximum=" ).append( this.maximum ).
            append( ", increment=" ).append( this.increment ).
            append( ", value=" ).append( this.value ).
            append( '}' ).toString();

    }

    // SECTION-END
    // SECTION-START[Comparable]

    /**
     * Compares this sequence with the specified sequence for order.
     * <p>Returns a negative integer, zero, or a positive integer as this sequence is less than, equal to, or greater
     * than the specified sequence.</p>
     * <p><b>Note:</b><br/>This class has a natural ordering that is inconsistent with equals.</p>
     *
     * @param s The sequence to be compared.
     *
     * @return A negative integer, zero, or a positive integer as this sequence is less than, equal to, or greater than
     * the specified sequence.
     */
    public int compareTo( final Sequence s )
    {
        int result = s == null ? 1 : 0;

        if ( result == 0 )
        {
            if ( this.getName() == null )
            {
                result = s.getName() == null ? 0 : -1;
            }
            else
            {
                result = s.getName() == null ? 1 : this.getName().compareTo( s.getName() );
            }
        }

        return result;
    }

    // SECTION-END
    // SECTION-START[Cloneable]

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Sequence clone()
    {
        try
        {
            return (Sequence) super.clone();
        }
        catch ( CloneNotSupportedException e )
        {
            throw new AssertionError( e );
        }
    }

    // SECTION-END
    // SECTION-START[Object]

    /**
     * Indicates whether some other object is equal to this one by comparing the values of properties {@code name} and
     * {@code revision}.
     *
     * @param o The reference object with which to compare.
     *
     * @return {@code true} if this object is the same as {@code o}; {@code false} otherwise.
     */
    @Override
    public boolean equals( final Object o )
    {
        boolean ret = o == this;

        if ( !ret && o instanceof Sequence )
        {
            final Sequence that = (Sequence) o;
            ret = ( this.getName() == null ? that.getName() == null : this.getName().equals( that.getName() ) ) &&
                  ( this.getRevision() == that.getRevision() );

        }

        return ret;
    }

    /**
     * Returns a hash code value for this object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode()
    {
        int hc = 23;
        hc = 37 * hc + ( this.getName() == null ? 0 : this.getName().hashCode() );
        hc = 37 * hc + (int) ( this.getRevision() ^ ( this.getRevision() >>> 32 ) );
        return hc;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString() + this.internalString();
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public Sequence()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
}
