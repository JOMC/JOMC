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
package org.jomc.sequences.ri;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceChangeEvent;

// SECTION-START[Implementation Comment]
/**
 * {@code VetoableChangeListener} implementation validating sequence instances for use with the reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code java.beans.VetoableChangeListener}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
 * </ul></p>
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
public class DefaultSequenceValidator implements VetoableChangeListener
{
    // SECTION-START[VetoableChangeListener]

    public void vetoableChange( final PropertyChangeEvent evt ) throws PropertyVetoException
    {
        if ( evt instanceof SequenceChangeEvent )
        {
            final SequenceChangeEvent sequenceChange = (SequenceChangeEvent) evt;
            boolean valid = true;

            if ( sequenceChange.getNewSequence() != null )
            {
                if ( sequenceChange.getNewSequence().getName() == null )
                {
                    valid = false;
                    sequenceChange.getStatus( Sequence.PROP_NAME ).add( SequenceChangeEvent.MANDATORY_VALUE );
                }
                if ( sequenceChange.getNewSequence().getMaximum() < sequenceChange.getNewSequence().getMinimum() ||
                     sequenceChange.getNewSequence().getMinimum() > sequenceChange.getNewSequence().getMaximum() )
                {
                    valid = false;
                    sequenceChange.getStatus( Sequence.PROP_MINIMUM ).add( SequenceChangeEvent.ILLEGAL_VALUE );
                    sequenceChange.getStatus( Sequence.PROP_MAXIMUM ).add( SequenceChangeEvent.ILLEGAL_VALUE );
                }
                if ( sequenceChange.getNewSequence().getValue() > sequenceChange.getNewSequence().getMaximum() ||
                     sequenceChange.getNewSequence().getValue() < sequenceChange.getNewSequence().getMinimum() )
                {
                    valid = false;
                    sequenceChange.getStatus( Sequence.PROP_VALUE ).add( SequenceChangeEvent.ILLEGAL_VALUE );
                }
                if ( sequenceChange.getNewSequence().getIncrement() <= 0L )
                {
                    valid = false;
                    sequenceChange.getStatus( Sequence.PROP_INCREMENT ).add( SequenceChangeEvent.ILLEGAL_VALUE );
                }
            }

            if ( !valid )
            {
                throw new PropertyVetoException( this.getIllegalSequenceMessage( this.getLocale() ), sequenceChange );
            }
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public DefaultSequenceValidator()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
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
}
