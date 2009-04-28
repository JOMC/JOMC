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

import java.math.BigInteger;

// SECTION-START[Implementation Comment]
/**
 * Gets thrown whenever the directories capacity limit has been reached.
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getCapacityLimitMessage capacityLimit}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>The capacity limit {0,number} of the sequence directory has been reached.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Kapazitätsgrenze {0,number} des Sequenz-Verzeichnisses erreicht.</pre></td></tr>
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
public class CapacityLimitException extends RuntimeException
{
    // SECTION-START[CapacityLimitException]

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = -6709782550864964813L;

    /**
     * Capacity limit value.
     * @serial
     */
    private BigInteger capacityLimit;

    /**
     * Creates a new {@code CapacityLimitException} taking the capacity limit
     * value.
     *
     * @param capacityLimit The capacity limit to report.
     */
    public CapacityLimitException( final BigInteger capacityLimit )
    {
        super();
        this.capacityLimit = capacityLimit;
    }

    /**
     * Gets the reported capacity limit.
     *
     * @return The reported capacity limit.
     */
    public BigInteger getCapacityLimit()
    {
        return this.capacityLimit;
    }

    /**
     * Gets the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage()
    {
        return this.getCapacityLimitMessage( this.getLocale(), this.getCapacityLimit() );
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
     * Gets the text of the {@code capacityLimit} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>The capacity limit {0,number} of the sequence directory has been reached.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Kapazitätsgrenze {0,number} des Sequenz-Verzeichnisses erreicht.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param capacityLimit Format argument.
     * @return The text of the {@code capacityLimit} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getCapacityLimitMessage( final java.util.Locale locale, final java.lang.Number capacityLimit ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "capacityLimit", locale, new Object[] { capacityLimit, null } );
    }
    // SECTION-END
}
