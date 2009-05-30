// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@schulte.it>
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// SECTION-START[Implementation Comment]
/**
 *
 * Gets thrown whenever illegal sequence information is passed to a method
 * expecting legal sequence data.
 * <p>Throwing an {@code IllegalSequenceException}<blockquote><pre>
 * IllegalSequenceException e = new IllegalSequenceException();
 * e.getDetails(Sequence.PROP_<i>XYZ</i>).add(IllegalSequenceException.MANDATORY_PROPERTY_MISSING);
 * e.getDetails().add(IllegalSequenceException.<i>XYZ</i>);
 * throw e;</pre></blockquote></p>
 * <p>Catching an {@code IllegalSequenceException}<blockquote><pre>
 * catch(IllegalSequenceException e)
 * {
 *         <i>Fetch details bound to properties.</i>
 *         e.getDetails(Sequence.PROP_<i>XYZ</i>);
 *         ...
 *         <i>Fetch details not bound to any property.</i>
 *         e.getDetails();
 *         ...
 * }</blockquote></pre></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getIllegalSequenceMessage illegalSequence}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal sequence data.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ungültige Sequenzdaten.</pre></td></tr>
 * </table>
 * </li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://www.jomc.org/jomc-tools"
)
// SECTION-END
public class IllegalSequenceException extends IllegalArgumentException
{
    // SECTION-START[IllegalSequenceException]

    /** Detail of an {@code IllegalSequenceException}. */
    public static class Detail implements Serializable
    {

        /**
         * Identifier of the detail.
         * @serial
         */
        private String identifier;

        /**
         * Creates a new {@code Detail} instance taking the identifier of the instance.
         *
         * @param identifier The identifier of the instance.
         */
        public Detail( final String identifier )
        {
            this.identifier = identifier;
        }

        /**
         * Gets the identifier of the detail.
         *
         * @return The identifier of the detail.
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
            return super.toString() + new StringBuffer().append( "{identifier=" ).append( this.getIdentifier() ).
                append( '}' ).toString();

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
            if ( !equal && o instanceof Detail )
            {
                final Detail that = (Detail) o;
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
    public static final Detail MANDATORY_VALUE =
        new Detail( IllegalSequenceException.class.getName() + ".MANDATORY_VALUE" );

    /** A property value is illegal. */
    public static final Detail ILLEGAL_VALUE =
        new Detail( IllegalSequenceException.class.getName() + ".ILLEGAL_VALUE" );

    /** Key to the list of messages not bound to any particular property. */
    private static final String PROP_UNSPECIFIED = Sequence.class.getName();

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = -8021815827932105890L;

    /**
     * Flag indicating that the instance is locked for changes.
     * @serial
     */
    private boolean locked;

    /**
     * The details of the exception.
     * @serial
     */
    private final Map<String, List<Detail>> details;

    /**
     * Gets a list containing details describing the exception.
     *
     * @return A list containing details describing the exception.
     */
    public List<Detail> getDetails()
    {
        return this.locked ? Collections.unmodifiableList( this.details.get( PROP_UNSPECIFIED ) )
               : this.details.get( PROP_UNSPECIFIED );

    }

    /**
     * Gets a list containing details bound to a property.
     *
     * @param propertyName The name of a property to return any details for.
     *
     * @return A list containing details bound to a property with name
     * {@code propertyName} or an empty list if the instance does not hold
     * details for a property with name {@code propertyName}.
     *
     * @throws NullPointerException if {@code propertyName} is {@code null}.
     */
    public List<Detail> getDetails( final String propertyName )
    {
        List<Detail> list = this.details.get( propertyName );
        if ( list == null )
        {
            list = new LinkedList<Detail>();
            this.details.put( propertyName, list );
        }

        return this.locked ? Collections.unmodifiableList( list ) : list;
    }

    /**
     * Gets the names of all properties for which the exception holds messages.
     *
     * @return A list of the names of all properties for which the exception
     * holds messages or an empty list if the exception does not hold any
     * message bound to a property.
     */
    public List<String> getPropertyNames()
    {
        List<String> propertyNames = new LinkedList<String>();
        for ( String key : this.details.keySet() )
        {
            if ( !PROP_UNSPECIFIED.equals( key ) )
            {
                propertyNames.add( key );
            }
        }
        return Collections.unmodifiableList( propertyNames );
    }

    /**
     * Creates a string representing the messages of the instance.
     *
     * @return a string representing the messages of the instance.
     */
    private String internalString()
    {
        final StringBuffer buf = new StringBuffer( 200 ).append( '{' );
        final List<String> propertyNames = this.getPropertyNames();
        final List<Detail> instanceDetails = this.getDetails();

        for ( Iterator<String> it = propertyNames.iterator(); it.hasNext(); )
        {
            final String p = it.next();
            buf.append( p ).append( "={" );

            int j = 0;
            for ( Iterator<Detail> d = this.getDetails( p ).iterator(); d.hasNext(); j++ )
            {
                buf.append( '[' ).append( j ).append( "]=" ).append( d.next() );

                if ( d.hasNext() )
                {
                    buf.append( ", " );
                }
            }

            buf.append( '}' );

            if ( it.hasNext() )
            {
                buf.append( ", " );
            }
        }

        if ( !instanceDetails.isEmpty() )
        {
            if ( propertyNames.size() > 0 )
            {
                buf.append( ", " );
            }

            buf.append( PROP_UNSPECIFIED ).append( "={" );

            int i = 0;
            for ( Iterator<Detail> it = instanceDetails.iterator(); it.hasNext(); i++ )
            {
                buf.append( "[" ).append( i ).append( "]=" ).append( it.next() );

                if ( it.hasNext() )
                {
                    buf.append( ", " );
                }
            }

            buf.append( '}' );
        }

        buf.append( '}' );
        return buf.toString();
    }

    // SECTION-END
    // SECTION-START[Throwable]

    /**
     * Gets the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage()
    {
        return this.getIllegalSequenceMessage( this.getLocale() );
    }

    // SECTION-END
    // SECTION-START[Object]
    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString()
    {
        return super.toString() + '\n' + this.internalString();
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public IllegalSequenceException()
    {
        // SECTION-START[Default Constructor]
        super();
        this.details = new HashMap<String, List<Detail>>( 100 );
        this.details.put( PROP_UNSPECIFIED, new LinkedList<Detail>() );
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code Locale} dependency.
     * <p>This method returns the "{@code default}" object of the {@code java.util.Locale} specification at specification level 1.1.</p>
     * @return The {@code Locale} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.util.Locale getLocale() throws org.jomc.ObjectManagementException
    {
        return (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "Locale" );
    }
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code illegalSequence} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal sequence data.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ungültige Sequenzdaten.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code illegalSequence} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getIllegalSequenceMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "illegalSequence", locale,  null );
    }
    // SECTION-END
}
