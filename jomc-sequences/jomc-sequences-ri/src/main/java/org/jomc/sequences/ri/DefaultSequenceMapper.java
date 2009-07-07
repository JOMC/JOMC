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
 * Object applies to Singleton scope.</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getUnhandledExceptionMessage unhandledException}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Unhandled exception.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Unbehandelte Ausnahme.</pre></td></tr>
 * </table>
 * </li>
 * <li>"{@link #getIllegalArgumentMessage illegalArgument}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal value ''{1}'' for argument ''{0}''.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ungültiger Wert ''{1}'' für Parameter ''{0}''.</pre></td></tr>
 * </table>
 * </li>
 * </ul></p>
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
public class DefaultSequenceMapper implements SequenceMapper
{
    // SECTION-START[SequenceMapper]

    public Sequence map( final SequenceType source, final Sequence target ) throws SequencesSystemException
    {
        if ( source == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "source", null ) );
        }
        if ( target == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "target", null ) );
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

    public SequenceType map( final Sequence source, final SequenceType target ) throws SequencesSystemException
    {
        if ( source == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "source", null ) );
        }
        if ( target == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "target", null ) );
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
                    throw new SequencesSystemException( getUnhandledExceptionMessage( getLocale() ), e );
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
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public DefaultSequenceMapper()
    {
        // SECTION-START[Default Constructor]
        super();
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
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.util.Locale getLocale() throws org.jomc.ObjectManagementException
    {
        return (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "Locale" );
    }
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code illegalArgument} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal value ''{1}'' for argument ''{0}''.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ungültiger Wert ''{1}'' für Parameter ''{0}''.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param argumentName Format argument.
     * @param argumentValue Format argument.
     * @return The text of the {@code illegalArgument} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getIllegalArgumentMessage( final java.util.Locale locale, final java.lang.String argumentName, final java.lang.String argumentValue ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "illegalArgument", locale, new Object[] { argumentName, argumentValue, null } );
    }

    /**
     * Gets the text of the {@code unhandledException} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Unhandled exception.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Unbehandelte Ausnahme.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code unhandledException} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getUnhandledExceptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "unhandledException", locale,  null );
    }
    // SECTION-END
}
