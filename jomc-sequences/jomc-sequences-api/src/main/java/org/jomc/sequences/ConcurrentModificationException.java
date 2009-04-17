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
 * Gets thrown whenever a sequence is edited or removed using outdated data.
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getConcurrentlyModifiedMessage concurrentlyModified}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>The sequence got concurrently modified.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Die Sequenz wurde zwischenzeitlich geändert.</pre></td></tr>
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
public class ConcurrentModificationException extends RuntimeException
{
    // SECTION-START[ConcurrentModificationException]

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = -1376564723120982029L;

    /**
     * Most recent revision of the correspondent reported.
     * @serial
     */
    private Sequence mostRecentRevision;

    /**
     * Creates a new {@code ConcurrentModificationException} instance taking the
     * most recent revision of the sequence to report.
     *
     * @param sequence The most recent revision of the sequence to report.
     */
    public ConcurrentModificationException( final Sequence sequence )
    {
        this.mostRecentRevision = sequence;
    }

    /**
     * Gets the most recent revision of the sequence.
     *
     * @return The most recent revision of the sequence.
     */
    public Sequence getMostRecentRevision()
    {
        return this.mostRecentRevision;
    }

    /**
     * Gets the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage()
    {
        return this.getConcurrentlyModifiedMessage( this.getLocale() );
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
     * Gets the text of the {@code concurrentlyModified} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>The sequence got concurrently modified.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Die Sequenz wurde zwischenzeitlich geändert.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code concurrentlyModified} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getConcurrentlyModifiedMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "concurrentlyModified", locale,  null );
    }
    // SECTION-END
}
