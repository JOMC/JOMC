// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
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
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
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
        comments = "See http://jomc.sourceforge.net/jomc-tools"
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
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.String getDefaultEntityManagerFactoryJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "defaultEntityManagerFactoryJndiName" );
    }

    /**
     * Gets the value of the {@code defaultEntityManagerJndiName} property.
     * @return The default JNDI name of the {@code EntityManager} backing the SDK.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.String getDefaultEntityManagerJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "defaultEntityManagerJndiName" );
    }

    /**
     * Gets the value of the {@code defaultTransactionManagerJndiName} property.
     * @return The default JNDI name of the {@code TransactionManager} backing the SDK.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.String getDefaultTransactionManagerJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "defaultTransactionManagerJndiName" );
    }

    /**
     * Gets the value of the {@code defaultTransactionSynchronizationRegistryJndiName} property.
     * @return The default JNDI name of the {@code TransactionSynchronizationRegistry} backing the SDK.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.String getDefaultTransactionSynchronizationRegistryJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "defaultTransactionSynchronizationRegistryJndiName" );
    }

    /**
     * Gets the value of the {@code defaultUserTransactionJndiName} property.
     * @return The default JNDI name of the {@code UserTransaction} backing the SDK.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.String getDefaultUserTransactionJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "defaultUserTransactionJndiName" );
    }
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
