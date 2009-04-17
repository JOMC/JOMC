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
 * Gets thrown when a sequence is about to be added to a directory although a sequence with the same name already exists.
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getDuplicateSequenceMessage duplicateSequence}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>A sequence with the name {0} already exists.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Eine Sequenz mit Namen {0} existiert bereits.</pre></td></tr>
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
public class DuplicateSequenceException extends IllegalArgumentException
{
    // SECTION-START[DuplicateSequenceException]

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = 4317727029710509724L;

    /**
     * The sequence already known.
     * @serial
     */
    private final Sequence sequence;

    /**
     * Creates a new {@code DuplicateSequenceException} taking the sequence
     * already known.
     *
     * @param sequence The sequence already known.
     */
    public DuplicateSequenceException( final Sequence sequence )
    {
        super();
        this.sequence = sequence;
    }

    /**
     * Gets the sequence already known.
     *
     * @return The sequence already known.
     */
    public Sequence getSequence()
    {
        return this.sequence;
    }

    /**
     * Gets the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage()
    {
        return this.getDuplicateSequenceMessage(
            this.getLocale(), this.getSequence() != null ? this.getSequence().getName() : null );

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
     * Gets the text of the {@code duplicateSequence} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>A sequence with the name {0} already exists.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Eine Sequenz mit Namen {0} existiert bereits.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param sequenceName Format argument.
     * @return Message stating that a sequence already exists in the directory.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getDuplicateSequenceMessage( final java.util.Locale locale, final java.lang.String sequenceName ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "duplicateSequence", locale, new Object[] { sequenceName, null } );
    }
    // SECTION-END
}
