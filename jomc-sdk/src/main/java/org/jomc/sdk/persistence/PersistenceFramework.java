// SECTION-START[License Header]
/*
 *  JOMC SDK
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
package org.jomc.sdk.persistence;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.jomc.sdk.Environment;

// SECTION-START[Implementation Comment]
/**
 * Persistence framework.
 * <p><b>Specifications</b><ul>
 * <li>{@code javax.persistence.EntityManagerFactory}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * <li>{@code javax.persistence.EntityManager}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #isContainerManaged containerManaged}"<blockquote>
 * Property of type {@code boolean} with value "false".</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getInitialContext InitialContext}"<blockquote>
 * Dependency on {@code javax.naming.Context} applying to Multiton scope.</blockquote></li>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getLogger Logger}"<blockquote>
 * Dependency on {@code org.jomc.logging.Logger} at specification level 1.0 applying to Multiton scope bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getMissingEntityManagerFactoryMessage missingEntityManagerFactory}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Entity manager factory ''{0}'' not found.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Entity manager factory ''{0}'' nicht gefunden.</pre></td></tr>
 * </table>
 * <li>"{@link #getMissingEntityManagerMessage missingEntityManager}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Entity manager ''{0}'' not found.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Entity manager ''{0}'' nicht gefunden.</pre></td></tr>
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
public class PersistenceFramework
{
    // SECTION-START[PersistenceFramework]

    public EntityManagerFactory getEntityManagerFactory() throws NamingException
    {
        final EntityManagerFactory entityManagerFactory = (EntityManagerFactory) this.getInitialContext().lookup(
            this.getSdkEnvironment().getEntityManagerFactoryJndiName() );

        if ( entityManagerFactory == null )
        {
            this.getLogger().warn( this.getMissingEntityManagerFactoryMessage(
                this.getLocale(), this.getSdkEnvironment().getEntityManagerFactoryJndiName() ) );

        }

        return entityManagerFactory;
    }

    public EntityManager getEntityManager() throws NamingException
    {
        final EntityManager entityManager =
            (EntityManager) this.getInitialContext().lookup( this.getSdkEnvironment().getEntityManagerJndiName() );

        if ( entityManager != null )
        {
            if ( !this.isContainerManaged() )
            {
                entityManager.joinTransaction();
            }
        }
        else
        {
            this.getLogger().warn( this.getMissingEntityManagerMessage(
                this.getLocale(), this.getSdkEnvironment().getEntityManagerJndiName() ) );

        }

        return entityManager;
    }

    // SECTION-END
    // SECTION-START[PersistenceFramework]

    private Environment sdkEnvironment;

    public Environment getSdkEnvironment()
    {
        if ( this.sdkEnvironment == null )
        {
            this.sdkEnvironment = new Environment();
        }

        return this.sdkEnvironment;
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public PersistenceFramework()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code InitialContext} dependency.
     * <p>This method returns the "{@code JOMC SDK}" object of the {@code javax.naming.Context} specification.</p>
     * @return The {@code InitialContext} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private javax.naming.Context getInitialContext() throws org.jomc.ObjectManagementException
    {
        return (javax.naming.Context) org.jomc.ObjectManager.getInstance().getDependency( this, "InitialContext" );
    }

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
     * <dd>Property of type {@code java.lang.String} with value "org.jomc.sdk.persistence.PersistenceFramework".
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

    /**
     * Gets the value of the {@code containerManaged} property.
     * @return {@code true} if the {@code EntityManager} looked up from the JNDI context is provided by an EJB container; {@code false} to perform a call to the {@code EntityManager.joinTransaction} method on each JNDI lookup.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private boolean isContainerManaged() throws org.jomc.ObjectManagementException
    {
        return ( (java.lang.Boolean) org.jomc.ObjectManager.getInstance().getProperty( this, "containerManaged" ) ).booleanValue();
    }
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code missingEntityManager} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Entity manager ''{0}'' not found.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Entity manager ''{0}'' nicht gefunden.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param jndiName Format argument.
     * @return The text of the {@code missingEntityManager} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getMissingEntityManagerMessage( final java.util.Locale locale, final java.lang.String jndiName ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "missingEntityManager", locale, new Object[] { jndiName, null } );
    }

    /**
     * Gets the text of the {@code missingEntityManagerFactory} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Entity manager factory ''{0}'' not found.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Entity manager factory ''{0}'' nicht gefunden.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param jndiName Format argument.
     * @return The text of the {@code missingEntityManagerFactory} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getMissingEntityManagerFactoryMessage( final java.util.Locale locale, final java.lang.String jndiName ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "missingEntityManagerFactory", locale, new Object[] { jndiName, null } );
    }
    // SECTION-END
}
