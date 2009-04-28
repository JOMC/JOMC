// SECTION-START[License Header]
/*
 *  JOMC Standalone
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
package org.jomc.standalone.naming.support;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import org.objectweb.jotm.Jotm;

// SECTION-START[Implementation Comment]
/**
 * Standalone Jonas JTA context factory.
 * <p><b>Specifications</b><ul>
 * <li>{@code javax.naming.spi.InitialContextFactory}<blockquote>
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
 * <li>"{@link #getImplementationInfoMessage implementationInfo}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>JotmContextFactory 1.0-alpha-1-SNAPSHOT</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>JotmContextFactory 1.0-alpha-1-SNAPSHOT</pre></td></tr>
 * </table>
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
public class JotmContextFactory extends AbstractContextFactory implements InitialContextFactory
{
    // SECTION-START[JonasJTAContextFactory]

    public Context getInitialContext( final Hashtable<?, ?> environment ) throws NamingException
    {
        final Jotm jotm = new Jotm( true, false );
        this.getContext().bind( this.getEnvironment().getTransactionManagerJndiName(), jotm.getTransactionManager() );
        this.getContext().bind( this.getEnvironment().getUserTransactionJndiName(), jotm.getUserTransaction() );
        this.getContext().bind( this.getEnvironment().getTransactionSynchronizationRegistryJndiName(),
                                jotm.getTransactionSynchronizationRegistry() );

        return null;
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public JotmContextFactory()
    {
        // SECTION-START[Default Constructor]
        super();
        this.getLogger().info( this.getImplementationInfoMessage( this.getLocale() ) );
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
        return (java.util.Locale) org.jomc.ObjectManager.getInstance().getDependency( this, "Locale" );
    }

    /**
     * Gets the {@code Logger} dependency.
     * <p>This method returns any available object of the {@code org.jomc.logging.Logger} specification at specification level 1.0.</p>
     * <p><b>Properties</b><dl>
     * <dt>"{@code name}"</dt>
     * <dd>Property of type {@code java.lang.String} with value "org.jomc.standalone.naming.support.JotmContextFactory".
     * </dd>
     * </dl>
     * @return The {@code Logger} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
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
     * Gets the text of the {@code implementationInfo} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>JotmContextFactory 1.0-alpha-1-SNAPSHOT</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>JotmContextFactory 1.0-alpha-1-SNAPSHOT</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code implementationInfo} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getImplementationInfoMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "implementationInfo", locale,  null );
    }
    // SECTION-END
}
