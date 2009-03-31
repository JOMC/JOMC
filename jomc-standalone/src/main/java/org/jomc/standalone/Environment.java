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
package org.jomc.standalone;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

// SECTION-START[Implementation Comment]
/**
 * Standalone environment.
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
 * <li>"{@link #getDefaultDataSourceContextFactoryName defaultDataSourceContextFactoryName}"<blockquote>
 * Property of type {@code java.lang.String} with value "org.jomc.standalone.naming.support.DataSourceContextFactory".</blockquote></li>
 * <li>"{@link #getDefaultDataSourceJndiName defaultDataSourceJndiName}"<blockquote>
 * Property of type {@code java.lang.String} with value "java:jdbc/jomc-standalone".</blockquote></li>
 * <li>"{@link #getDefaultJtaDataSourceJndiName defaultJtaDataSourceJndiName}"<blockquote>
 * Property of type {@code java.lang.String} with value "java:jta/jomc-standalone".</blockquote></li>
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
     * Constant for the name of the system property holding the JNDI name the standalone {@code EntityManager}
     * implementation is bound to.
     */
    public static final String ENTITY_MANAGER_JNDI_NAME = "standalone.entityManagerJndiName";

    /**
     * Constant for the name of the system property holding the JNDI name the standalone {@code EntityManagerFactory}
     * implementation is bound to.
     */
    public static final String ENTITY_MANAGER_FACTORY_JNDI_NAME = "standalone.entityManagerFactoryJndiName";

    /**
     * Constant for the name of the system property holding the JNDI name the standalone {@code TransactionManager}
     * implementation is bound to.
     */
    public static final String TRANSACTION_MANAGER_JNDI_NAME = "standalone.transactionManagerJndiName";

    /**
     * Constant for the name of the system property holding the JNDI name the standalone {@code UserTransaction}
     * implementation is bound to.
     */
    public static final String USER_TRANSACTION_JNDI_NAME = "standalone.userTransactionJndiName";

    /**
     * Constant for the name of the system property holding the JNDI name the standalone
     * {@code TransactionSynchronizationRegistry} implementation is bound to.
     */
    public static final String TRANSACTION_SYNCHRONIZATION_REGISTRY_JNDI_NAME =
        "standalone.transactionSynchronizationRegistryJndiName";

    /**
     * Constant for the name of the system property holding the class name of the {@code InitialContextFactory}
     * providing the JPA implementation backing the standalone environment.
     */
    public static final String JPA_CONTEXT_FACTORY_NAME = "standalone.jpaContextFactoryName";

    /**
     * Constant for the name of the system property holding the root URL of the persistence unit backing the
     * standalone environment.
     */
    public static final String JPA_ROOT_URL = "standalone.jpaRootUrl";

    /**
     * Constant for the name of the system property holding the class name of the {@code InitialContextFactory}
     * providing the JTA implementation backing the standalone environment.
     */
    public static final String JTA_CONTEXT_FACTORY_NAME = "standalone.jtaContextFactoryName";

    /**
     * Constant for the name of the system property holding the JNDI name of the JTA aware {@code DataSource} backing
     * the standalone environment.
     */
    public static final String JTA_DATA_SOURCE_JNDI_NAME = "standalone.jtaDataSourceJndiName";

    /** Constant for the prefix of data source properties of the standalone environment. */
    public static final String DATA_SOURCE_PREFIX = "standalone.dataSource.";

    /**
     * Constant for the name of the system property holding the class name of the {@code InitialContextFactory}
     * providing the {@code DataSource} implementation backing the standalone environment.
     */
    public static final String DATA_SOURCE_CONTEXT_FACTORY_NAME = DATA_SOURCE_PREFIX + "contextFactoryName";

    /**
     * Constant for the name of the system property holding the JNDI name of the {@code DataSource} backing the
     * standalone environment.
     */
    public static final String DATA_SOURCE_JNDI_NAME = DATA_SOURCE_PREFIX + "jndiName";

    /**
     * Constant for the name of the system property holding the class name of the {@code DataSource} backing the
     * standalone environment.
     */
    public static final String DATA_SOURCE_CLASS_NAME = DATA_SOURCE_PREFIX + "className";

    /**
     * Constant for the name of the system property holding the user of the {@code DataSource} backing the
     * standalone environment.
     */
    public static final String DATA_SOURCE_USER = DATA_SOURCE_PREFIX + "user";

    /**
     * Constant for the name of the system property holding the password of the {@code DataSource} backing the
     * standalone environment.
     */
    public static final String DATA_SOURCE_PASSWORD = DATA_SOURCE_PREFIX + "password";

    /**
     * Gets the JNDI name the standalone {@code EntityManager} implementation is bound to.
     *
     * @return The the JNDI name the standalone {@code EntityManager} implementation is bound to.
     */
    public String getEntityManagerJndiName()
    {
        return System.getProperty( ENTITY_MANAGER_JNDI_NAME, this.getDefaultEntityManagerJndiName() );
    }

    /**
     * Gets the JNDI name the standalone {@code EntityManagerFactory} implementation is bound to.
     *
     * @return The JNDI name the standalone {@code EntityManagerFactory} implementation is bound to.
     */
    public String getEntityManagerFactoryJndiName()
    {
        return System.getProperty( ENTITY_MANAGER_FACTORY_JNDI_NAME, this.getDefaultEntityManagerFactoryJndiName() );
    }

    /**
     * Gets the JNDI name the standalone {@code TransactionManager} implementation is bound to.
     *
     * @return The JNDI name the standalone {@code TransactionManager} implementation is bound to.
     */
    public String getTransactionManagerJndiName()
    {
        return System.getProperty( TRANSACTION_MANAGER_JNDI_NAME, this.getDefaultTransactionManagerJndiName() );
    }

    /**
     * Gets the JNDI name the standalone {@code UserTransaction} implementation is bound to.
     *
     * @return The JNDI name the standalone {@code UserTransaction} implementation is bound to.
     */
    public String getUserTransactionJndiName()
    {
        return System.getProperty( USER_TRANSACTION_JNDI_NAME, this.getDefaultUserTransactionJndiName() );
    }

    /**
     * Gets the JNDI name the standalone {@code TransactionSynchronizationRegistry} is bound to.
     *
     * @return The JNDI name the standalone {@code TransactionSynchronizationRegistry} is bound to.
     */
    public String getTransactionSynchronizationRegistryJndiName()
    {
        return System.getProperty( TRANSACTION_SYNCHRONIZATION_REGISTRY_JNDI_NAME,
                                   this.getDefaultTransactionSynchronizationRegistryJndiName() );

    }

    /**
     * Gets the class name of the {@code InitialContextFactory} providing the JPA implementation backing the standalone
     * environment.
     *
     * @return The class name of the {@code InitialContextFactory} providing the JPA implementation backing the
     * standalone environment or {@code null}.
     */
    public String getJpaContextFactoryName()
    {
        return System.getProperty( JPA_CONTEXT_FACTORY_NAME );
    }

    /**
     * Gets the root URL of the persistence unit backing the standalone environment.
     *
     * @return The root URL of the persistence unit backing the standalone environment.
     */
    public URL getJpaRootUrl()
    {
        try
        {
            String sysRootUrl = System.getProperty( JPA_ROOT_URL );

            if ( sysRootUrl != null )
            {
                final File f = new File( sysRootUrl );
                if ( !f.exists() )
                {
                    f.mkdirs();
                }

                return f.toURI().toURL();
            }
            else
            {
                final File defaultRootUrl =
                    new File( System.getProperty( "java.io.tmpdir" ), "jomc-standalone" );

                if ( !defaultRootUrl.exists() )
                {
                    defaultRootUrl.mkdirs();
                }

                return defaultRootUrl.toURI().toURL();
            }
        }
        catch ( MalformedURLException e )
        {
            throw new AssertionError( e );
        }
    }

    /**
     * Gets the class name of the {@code InitialContextFactory} providing the JTA implementation backing the standalone
     * environment.
     *
     * @return The class name of the {@code InitialContextFactory} providing the JTA implementation backing the
     * standalone environment or {@code null}.
     */
    public String getJtaContextFactoryName()
    {
        return System.getProperty( JTA_CONTEXT_FACTORY_NAME );
    }

    /**
     * Gets the JNDI name of the JTA aware {@code DataSource} backing the standalone environment.
     *
     * @return The JNDI name of the JTA aware {@code DataSource} backing the standalone environment or {@code null}.
     */
    public String getJtaDataSourceJndiName()
    {
        return System.getProperty( JTA_DATA_SOURCE_JNDI_NAME, this.getDefaultJtaDataSourceJndiName() );
    }

    /**
     * Gets the class name of the {@code InitialContextFactory} providing the {@code DataSource} backing the standalone
     * environment.
     *
     * @return The class name of the {@code InitialContextFactory} providing the {@code DataSource} backing the
     * standalone environment.
     */
    public String getDataSourceContextFactoryName()
    {
        return System.getProperty( DATA_SOURCE_CONTEXT_FACTORY_NAME, this.getDefaultDataSourceContextFactoryName() );
    }

    /**
     * Gets the class name of the {@code DataSource} backing the standalone environment.
     *
     * @return The class name of the {@code DataSource} backing the standalone environment or {@code null}.
     */
    public String getDataSourceClassName()
    {
        return System.getProperty( DATA_SOURCE_CLASS_NAME );
    }

    /**
     * Gets the JNDI name of the {@code DataSource} backing the standalone environment.
     *
     * @return The JNDI name of the {@code DataSource} backing the standalone environment.
     */
    public String getDataSourceJndiName()
    {
        return System.getProperty( DATA_SOURCE_JNDI_NAME, this.getDefaultDataSourceJndiName() );
    }

    /**
     * Gets the user of the {@code DataSource} backing the standalone environment.
     *
     * @return The user of the {@code DataSource} backing the standalone environment.
     */
    public String getDataSourceUser()
    {
        return System.getProperty( DATA_SOURCE_USER );
    }

    /**
     * Gets the password of the {@code DataSource} backing the standalone environment.
     *
     * @return The password of the {@code DataSource} backing the standalone environment.
     */
    public String getDataSourcePassword()
    {
        return System.getProperty( DATA_SOURCE_PASSWORD );
    }

    /**
     * Gets the properties of the instance.
     *
     * @return The properties of the instance.
     */
    public Properties getProperties()
    {
        return System.getProperties();
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
     * Gets the value of the {@code defaultDataSourceContextFactoryName} property.
     * @return The default class name of the {@code InitialContextFactory} providing the standalone {@code DataSource}.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.lang.String getDefaultDataSourceContextFactoryName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "defaultDataSourceContextFactoryName" );
    }

    /**
     * Gets the value of the {@code defaultDataSourceJndiName} property.
     * @return The default JNDI name of the data source backing the standalone JPA implementation.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.lang.String getDefaultDataSourceJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "defaultDataSourceJndiName" );
    }

    /**
     * Gets the value of the {@code defaultEntityManagerFactoryJndiName} property.
     * @return The default JNDI name the standalone {@code EntityManagerFactory} is bound to.
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
     * @return The default JNDI name the standalone {@code EntityManager} is bound to.
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
     * Gets the value of the {@code defaultJtaDataSourceJndiName} property.
     * @return The default JNDI name of the data source backing the standalone JTA implementation.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private java.lang.String getDefaultJtaDataSourceJndiName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "defaultJtaDataSourceJndiName" );
    }

    /**
     * Gets the value of the {@code defaultTransactionManagerJndiName} property.
     * @return The default JNDI name the standalone {@code TransactionManager} is bound to.
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
     * @return The default JNDI name the standalone {@code TransactionSynchronizationRegistry} is bound to.
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
     * @return The default JNDI name the standalone {@code UserTransaction} is bound to.
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
