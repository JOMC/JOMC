// SECTION-START[License Header]
/*
 *  JOMC Sequences API
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
package org.jomc.sequences;

// SECTION-START[Implementation Comment]
/**
 *
 * Gets thrown whenever the next value of a sequence is requested although its
 * maximum value has already been reached.
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getSequenceLimitMessage sequenceLimit}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>The maximum sequence value {0,number} has been reached. No more sequence values available.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Der Maximalwert {0,number} der Sequenz wurde erreicht. Keine weiteren Sequenz-Werte verfügbar.</pre></td></tr>
 * </table>
 * </li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class SequenceLimitException extends RuntimeException
{
    // SECTION-START[SequenceLimitException]

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = -1325024909891370491L;

    /**
     * Current value of the sequence.
     * @serial
     */
    private long currentValue;

    /**
     * Creates a new {@code SequenceLimitException} taking the current
     * value of the sequence.
     *
     * @param currentValue The current value of the sequence reaching its limit.
     */
    public SequenceLimitException( final long currentValue )
    {
        super();
        this.currentValue = currentValue;
    }

    /**
     * Gets the current value of the sequence reaching its limit.
     *
     * @return The current value of the sequence reaching its limit.
     */
    public long getCurrentValue()
    {
        return this.currentValue;
    }

    /**
     * Gets the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage()
    {
        return this.getSequenceLimitMessage( this.getLocale(), Long.valueOf( this.getCurrentValue() ) );
    }

    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code Locale} dependency.
     * </p>
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
        return (java.util.Locale) org.jomc.ObjectManager.getInstance().getDependency( this, "Locale" );
    }
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code sequenceLimit} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>The maximum sequence value {0,number} has been reached. No more sequence values available.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Der Maximalwert {0,number} der Sequenz wurde erreicht. Keine weiteren Sequenz-Werte verfügbar.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param sequenceLimit Format argument.
     * @return The text of the {@code sequenceLimit} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getSequenceLimitMessage( final java.util.Locale locale, final java.lang.Number sequenceLimit ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "sequenceLimit", locale, new Object[] { sequenceLimit, null } );
    }
    // SECTION-END
}
