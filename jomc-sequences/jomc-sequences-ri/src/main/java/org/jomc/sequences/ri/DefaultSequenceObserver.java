// SECTION-START[License Header]
/*
 *  JOMC Sequences RI
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
package org.jomc.sequences.ri;

import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceObserver;

// SECTION-START[Implementation Comment]
/**
 * {@code SequenceObserver} reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.sequences.SequenceObserver} {@code 1.0}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLogger Logger}"<blockquote>
 * Dependency on {@code org.jomc.logging.Logger} at specification level 1.0 applying to Multiton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getOperationInfoMessage operationInfo}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Directory update
     *     --''{0}''
     *     ++''{1}''</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnisänderung
     *     --''{0}''
     *     ++''{1}''</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class DefaultSequenceObserver implements SequenceObserver
{
    // SECTION-START[SequenceObserver]

    public void onSequenceChange( final Sequence oldValue, final Sequence newValue )
    {
        if ( this.getLogger().isDebugEnabled() )
        {
            this.getLogger().debug( this.getOperationInfoMessage(
                this.getLocale(), oldValue == null ? null : oldValue.toString(),
                newValue == null ? null : newValue.toString() ) );

        }
    }

    // SECTION-END
    // SECTION-START[DefaultSequenceObserver]
    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public DefaultSequenceObserver()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
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

    /**
     * Gets the {@code Logger} dependency.
     * </p>
     * <p><b>Properties</b><dl>
     * <dt>"{@code name}"</dt>
     * <dd>Property of type {@code java.lang.String} with value "org.jomc.sequences.ri.DefaultSequenceObserver".
     * </dd>
     * </dl>
     * @return The {@code Logger} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private org.jomc.logging.Logger getLogger() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.logging.Logger) org.jomc.ObjectManager.getInstance().getDependency( this, "Logger" );
    }
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code operationInfo} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Directory update
     *     --''{0}''
     *     ++''{1}''</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnisänderung
     *     --''{0}''
     *     ++''{1}''</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param oldSequenceInfo Format argument.
     * @param newSequenceInfo Format argument.
     * @return The text of the {@code operationInfo} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getOperationInfoMessage( final java.util.Locale locale, final java.lang.String oldSequenceInfo, final java.lang.String newSequenceInfo ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "operationInfo", locale, new Object[] { oldSequenceInfo, newSequenceInfo, null } );
    }
    // SECTION-END
}
