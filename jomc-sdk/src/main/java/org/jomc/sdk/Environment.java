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
package org.jomc.sdk;

// SECTION-START[Implementation Comment]
/**
 * SDK environment.
 * <p><b>Properties</b><ul>
 * <li>"{@link #getDefaultEntityManagerFactoryJndiName defaultEntityManagerFactoryJndiName}"<blockquote>
 * Property of type {@code java.lang.String} with value "java:comp/env/persistence/EntityManagerFactory".</blockquote></li>
 * <li>"{@link #getDefaultEntityManagerJndiName defaultEntityManagerJndiName}"<blockquote>
 * Property of type {@code java.lang.String} with value "java:comp/env/persistence/EntityManager".</blockquote></li>
 * <li>"{@link #getDefaultTransactionManagerJndiName defaultTransactionManagerJndiName}"<blockquote>
 * Property of type {@code java.lang.String} with value "java:comp/TransactionManager".</blockquote></li>
 * <li>"{@link #getDefaultUserTransactionJndiName defaultUserTransactionJndiName}"<blockquote>
 * Property of type {@code java.lang.String} with value "java:comp/UserTransaction".</blockquote></li>
 * <li>"{@link #getDefaultTransactionSynchronizationRegistryJndiName defaultTransactionSynchronizationRegistryJndiName}"<blockquote>
 * Property of type {@code java.lang.String} with value "java:comp/TransactionSynchronizationRegistry".</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class Environment
{
    // SECTION-START[Environment]

    /**
     * Constant for the name of the system property holding the JNDI name of the {@code EntityManager} backing the SDK.
     */
    public static final String ENTITY_MANAGER_JNDI_NAME = "sdk.entityManagerJndiName";

    /**
     * Constant for the name of the system property holding the JNDI name of the {@code EntityManagerFactory} backing
     * the SDK.
     */
    public static final String ENTITY_MANAGER_FACTORY_JNDI_NAME = "sdk.entityManagerFactoryJndiName";

    /**
     * Constant for the name of the system property holding the JNDI name of the {@code TransactionManager} backing the
     * SDK.
     */
    public static final String TRANSACTION_MANAGER_JNDI_NAME = "sdk.transactionManagerJndiName";

    /**
     * Constant for the name of the system property holding the JNDI name of the {@code UserTransaction} backing the
     * SDK.
     */
    public static final String USER_TRANSACTION_JNDI_NAME = "sdk.userTransactionJndiName";

    /**
     * Constant for the name of the system property holding the JNDI name of the
     * {@code TransactionSynchronizationRegistry} backing the SDK.
     */
    public static final String TRANSACTION_SYNCHRONIZATION_REGISTRY_JNDI_NAME =
        "sdk.transactionSynchronizationRegistryJndiName";

    /**
     * Gets the JNDI name of the {@code EntityManager} backing the SDK.
     *
     * @return The JNDI name of the {@code EntityManager} backing the SDK.
     */
    public String getEntityManagerJndiName()
    {
        return System.getProperty( ENTITY_MANAGER_JNDI_NAME, this.getDefaultEntityManagerJndiName() );
    }

    /**
     * Gets the JNDI name of the {@code EntityManagerFactory} backing the SDK.
     *
     * @return The JNDI name of the {@code EntityManagerFactory} backing the SDK.
     */
    public String getEntityManagerFactoryJndiName()
    {
        return System.getProperty( ENTITY_MANAGER_FACTORY_JNDI_NAME, this.getDefaultEntityManagerFactoryJndiName() );
    }

    /**
     * Gets the JNDI name of the {@code TransactionManager} backing the SDK.
     *
     * @return The JNDI name of the {@code TransactionManager} backing the SDK.
     */
    public String getTransactionManagerJndiName()
    {
        return System.getProperty( TRANSACTION_MANAGER_JNDI_NAME, this.getDefaultTransactionManagerJndiName() );
    }

    /**
     * Gets the JNDI name of the {@code UserTransaction} backing the SDK.
     *
     * @return The JNDI name of the {@code UserTransaction} backing the SDK.
     */
    public String getUserTransactionJndiName()
    {
        return System.getProperty( USER_TRANSACTION_JNDI_NAME, this.getDefaultUserTransactionJndiName() );
    }

    /**
     * Gets the JNDI name of the {@code TransactionSynchronizationRegistry} backing the SDK.
     *
     * @return The JNDI name of the {@code TransactionSynchronizationRegistry} backing the SDK.
     */
    public String getTransactionSynchronizationRegistryJndiName()
    {
        return System.getProperty( TRANSACTION_SYNCHRONIZATION_REGISTRY_JNDI_NAME,
                                   this.getDefaultTransactionSynchronizationRegistryJndiName() );

    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public Environment()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code defaultEntityManagerFactoryJndiName} property.
     * @return The default JNDI name of the {@code EntityManagerFactory} backing the SDK.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.lang.String getDefaultEntityManagerFactoryJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "defaultEntityManagerFactoryJndiName" );
    }

    /**
     * Gets the value of the {@code defaultEntityManagerJndiName} property.
     * @return The default JNDI name of the {@code EntityManager} backing the SDK.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.lang.String getDefaultEntityManagerJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "defaultEntityManagerJndiName" );
    }

    /**
     * Gets the value of the {@code defaultTransactionManagerJndiName} property.
     * @return The default JNDI name of the {@code TransactionManager} backing the SDK.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.lang.String getDefaultTransactionManagerJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "defaultTransactionManagerJndiName" );
    }

    /**
     * Gets the value of the {@code defaultTransactionSynchronizationRegistryJndiName} property.
     * @return The default JNDI name of the {@code TransactionSynchronizationRegistry} backing the SDK.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.lang.String getDefaultTransactionSynchronizationRegistryJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "defaultTransactionSynchronizationRegistryJndiName" );
    }

    /**
     * Gets the value of the {@code defaultUserTransactionJndiName} property.
     * @return The default JNDI name of the {@code UserTransaction} backing the SDK.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.lang.String getDefaultUserTransactionJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "defaultUserTransactionJndiName" );
    }
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
