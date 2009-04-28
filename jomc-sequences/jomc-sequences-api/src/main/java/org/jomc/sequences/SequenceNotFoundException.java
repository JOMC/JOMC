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
 * Gets thrown when a sequence is not found for a given name.
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getSequenceNotFoundMessage sequenceNotFound}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>No sequence found matching name {0}.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Keine Sequenz mit Namen {0} vorhanden.</pre></td></tr>
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
    comments = "See http://jomc.sourceforge.net/jomc-tools"
)
// SECTION-END
public class SequenceNotFoundException extends IllegalArgumentException
{
    // SECTION-START[SequenceNotFoundException]

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = 7534237573212724983L;

    /**
     * Name of the sequence which does not exist.
     * @serial
     */
    private String sequenceName;

    /**
     * Creates a new {@code SequenceNotFoundException} instance taking the
     * name of the sequence which does not exist.
     *
     * @param sequenceName Name of the sequence which does not exist.
     *
     * @throws NullPointerException if {@code sequenceName} is {@code null}.
     */
    public SequenceNotFoundException( final String sequenceName )
    {
        super();
        this.sequenceName = sequenceName;
    }

    /**
     * Gets the name of the sequence which does not exist.
     *
     * @return The name of the sequence which does not exist.
     */
    public String getSequenceName()
    {
        return this.sequenceName;
    }

    /**
     * Gets the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage()
    {
        return this.getSequenceNotFoundMessage( this.getLocale(), this.getSequenceName() );
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
        return (java.util.Locale) org.jomc.ObjectManager.getInstance().getDependency( this, "Locale" );
    }
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code sequenceNotFound} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>No sequence found matching name {0}.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Keine Sequenz mit Namen {0} vorhanden.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param sequenceName Format argument.
     * @return The text of the {@code sequenceNotFound} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getSequenceNotFoundMessage( final java.util.Locale locale, final java.lang.String sequenceName ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "sequenceNotFound", locale, new Object[] { sequenceName, null } );
    }
    // SECTION-END
}
