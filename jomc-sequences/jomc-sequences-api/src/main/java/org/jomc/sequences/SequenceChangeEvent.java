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
import java.util.EventObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// SECTION-START[Implementation Comment]
/**
 * A "SequenceChange" event gets delivered whenever the state of a sequence changes.
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
public class SequenceChangeEvent extends EventObject
{
    // SECTION-START[SequenceChangeEvent]

    /** Status of a {@code SequenceChangeEvent}. */
    public static class Status implements Serializable
    {

        /** Constant for an information. */
        public static final int INFORMATION = 1;

        /** Constant for a notification. */
        public static final int NOTIFICATION = 2;

        /** Constant for a warning. */
        public static final int WARNING = 3;

        /** Constant for an error. */
        public static final int ERROR = 3;

        /**
         * Type of the status.
         * @serial
         */
        private int type;

        /**
         * Identifier of the status.
         * @serial
         */
        private String identifier;

        /**
         * Creates a new {@code Status} instance taking a type constant and an identifier of the instance.
         *
         * @param type The type of the new status.
         * @param identifier The identifier of the status.
         */
        public Status( final int type, final String identifier )
        {
            this.type = type;
            this.identifier = identifier;
        }

        /**
         * Gets the type of the status.
         *
         * @return The type of the status.
         */
        public int getType()
        {
            return this.type;
        }

        /**
         * Gets the identifier of status.
         *
         * @return The identifier of the status.
         */
        public String getIdentifier()
        {
            return this.identifier;
        }

        /**
         * Returns a string representation of the object.
         *
         * @return A string representation of the object.
         */
        @Override
        public String toString()
        {
            final StringBuffer str = new StringBuffer().append( "{" );
            str.append( "type=" ).append( this.type ).
                append( ", identifier=" ).append( this.getIdentifier() ).
                append( '}' );

            return super.toString() + str.toString();
        }

        /**
         * Indicates whether some other object is "equal to" this one by comparing the value of property
         * {@code identifier}.
         *
         * @param o The reference object with which to compare.
         * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
         */
        @Override
        public boolean equals( final Object o )
        {
            boolean equal = this == o;
            if ( !equal && o instanceof Status )
            {
                final Status that = (Status) o;
                equal = this.getIdentifier().equals( that.getIdentifier() );
            }
            return equal;
        }

        /**
         * Returns a hash code value for the object.
         *
         * @return A hash code value for this object.
         */
        @Override
        public int hashCode()
        {
            int hash = 5;
            hash = 47 * hash + ( this.identifier != null ? this.identifier.hashCode() : 0 );
            return hash;
        }

    }

    /** A mandatory property is missing a value. */
    public static final Status MANDATORY_VALUE =
        new Status( Status.ERROR, Sequence.class.getName() + ".MANDATORY_VALUE" );

    /** A property value is illegal. */
    public static final Status ILLEGAL_VALUE =
        new Status( Status.ERROR, Sequence.class.getName() + ".ILLEGAL_VALUE" );

    /**
     * The entity getting changed.
     * @serial
     */
    private Sequence oldSequence;

    /**
     * The the entity the old sequence is changed to.
     * @serial
     */
    private Sequence newSequence;

    /**
     * The status of the event.
     * @serial
     */
    private Map<String, List<Status>> status;

    /**
     * Creates a new {@code SequenceChangeEvent} instance.
     *
     * @param source The source of the event.
     * @param oldSequence The entity getting changed or {@code null} if {@code newValue} is about to be added to
     * {@code source}.
     * @param newSequence The value {@code oldValue} will be changed to or {@code null} if {@code oldValue} is about to
     * be removed from {@code source}.
     */
    public SequenceChangeEvent( final Object source, final Sequence oldSequence, final Sequence newSequence )
    {
        super( source );
        this.oldSequence = oldSequence;
        this.newSequence = newSequence;
    }

    /**
     * Gets the entity getting changed.
     *
     * @return The entity getting changed or {@code null} if a new sequence is added to the source of.the event.
     */
    public Sequence getOldSequence()
    {
        return this.oldSequence;
    }

    /**
     * Gets the entity the old sequence is changed to.
     *
     * @return The entity the old sequence is changed to or {@code null} if the old sequence is removed from the source
     * of the event.
     */
    public Sequence getNewSequence()
    {
        return this.newSequence;
    }

    /**
     * Gets status for a given key.
     *
     * @param key The key of the status to return.
     *
     * @return The status for {@code key}.
     */
    public List<Status> getStatus( final String key )
    {
        if ( this.status == null )
        {
            this.status = new HashMap<String, List<Status>>();
        }

        List<Status> list = this.status.get( key );
        if ( list == null )
        {
            list = new LinkedList<Status>();
            this.status.put( key, list );
        }

        return list;
    }

    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
