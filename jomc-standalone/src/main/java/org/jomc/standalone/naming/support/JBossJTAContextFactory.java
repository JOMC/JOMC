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

import com.arjuna.ats.arjuna.coordinator.TransactionReaper;
import com.arjuna.ats.arjuna.recovery.RecoveryManager;
import com.arjuna.ats.jdbc.TransactionalDriver;
import com.arjuna.ats.jdbc.common.jdbcPropertyManager;
import com.arjuna.ats.jta.common.jtaPropertyManager;
import com.arjuna.ats.jta.utils.JNDIManager;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

// SECTION-START[Implementation Comment]
/**
 * Standalone JBoss JTA context factory.
 * <p><b>Specifications</b><ul>
 * <li>{@code javax.naming.spi.InitialContextFactory}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #isTransactionalDriverEnabled transactionalDriverEnabled}"<blockquote>
 * Property of type {@code boolean} with value "true".</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLogger Logger}"<blockquote>
 * Dependency on {@code org.jomc.logging.Logger} at specification level 1.0 applying to Multiton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getImplementationInfoMessage implementationInfo}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>JBossJTAContextFactory 1.0-alpha-1-SNAPSHOT</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>JBossJTAContextFactory 1.0-alpha-1-SNAPSHOT</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class JBossJTAContextFactory extends AbstractContextFactory
{
    // SECTION-START[InitialContextFactory]

    public Context getInitialContext( final Hashtable<?, ?> environment ) throws NamingException
    {
        jdbcPropertyManager.getPropertyManager().setProperty(
            "Context.INITIAL_CONTEXT_FACTORY", (String) environment.get( Context.INITIAL_CONTEXT_FACTORY ) );

        jdbcPropertyManager.getPropertyManager().setProperty(
            "Context.URL_PKG_PREFIXES", (String) environment.get( Context.URL_PKG_PREFIXES ) );

        jtaPropertyManager.getPropertyManager().setProperty( com.arjuna.ats.jta.common.Environment.TM_JNDI_CONTEXT,
                                                             this.getEnvironment().getTransactionManagerJndiName() );

        jtaPropertyManager.getPropertyManager().setProperty( com.arjuna.ats.jta.common.Environment.UT_JNDI_CONTEXT,
                                                             this.getEnvironment().getUserTransactionJndiName() );

        jtaPropertyManager.getPropertyManager().setProperty(
            com.arjuna.ats.jta.common.Environment.TSR_JNDI_CONTEXT,
            this.getEnvironment().getTransactionSynchronizationRegistryJndiName() );

        JNDIManager.bindJTAImplementation();
        TransactionReaper.create();
        RecoveryManager.manager().startRecoveryManagerThread();

        if ( this.isTransactionalDriverEnabled() )
        {
            this.getContext().rebind( this.getEnvironment().getJtaDataSourceJndiName(),
                                      this.getTransactionalDataSource() );

        }

        return null;
    }

    public DataSource getTransactionalDataSource()
    {
        return new DataSource()
        {

            private final TransactionalDriver driver = new TransactionalDriver();

            private PrintWriter logWriter;

            private boolean logWriterSet;

            private int loginTimeout;

            public Connection getConnection() throws SQLException
            {
                return this.getConnection( getEnvironment().getDataSourceUser(),
                                           getEnvironment().getDataSourcePassword() );

            }

            public Connection getConnection( final String username, final String password ) throws SQLException
            {
                final String url = TransactionalDriver.arjunaDriver + getEnvironment().getDataSourceJndiName();
                final Properties properties = new Properties();
                properties.setProperty( TransactionalDriver.userName, username );
                properties.setProperty( TransactionalDriver.password, password );
                return this.driver.connect( url, properties );
            }

            public PrintWriter getLogWriter() throws SQLException
            {
                if ( this.logWriter == null && !this.logWriterSet )
                {
                    this.logWriter = new PrintWriter( System.out );
                }

                return this.logWriter;
            }

            public void setLogWriter( final PrintWriter out ) throws SQLException
            {
                this.logWriter = out;
                this.logWriterSet = true;
            }

            public void setLoginTimeout( final int seconds ) throws SQLException
            {
                this.loginTimeout = seconds;
            }

            public int getLoginTimeout() throws SQLException
            {
                return this.loginTimeout;
            }

            public <T> T unwrap( final Class<T> iface ) throws SQLException
            {
                return (T) ( this.isWrapperFor( iface ) ? this : null );
            }

            public boolean isWrapperFor( final Class<?> iface ) throws SQLException
            {
                return iface.isAssignableFrom( DataSource.class );
            }

        };
    }

    // SECTION-END
    // SECTION-START[JBossJTAContextFactory]
    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public JBossJTAContextFactory()
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
     * <dd>Property of type {@code java.lang.String} with value "org.jomc.standalone.naming.support.JBossJTAContextFactory".
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

    /**
     * Gets the value of the {@code transactionalDriverEnabled} property.
     * @return {@code true} to enable ArjunaJTA's transactional JDBC driver (no JDBC3 support); {@code false} to disable ArjunaJTA's transactional JDBC driver.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private boolean isTransactionalDriverEnabled() throws org.jomc.ObjectManagementException
    {
        return ( (java.lang.Boolean) org.jomc.ObjectManager.getInstance().getProperty( this, "transactionalDriverEnabled" ) ).booleanValue();
    }
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code implementationInfo} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>JBossJTAContextFactory 1.0-alpha-1-SNAPSHOT</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>JBossJTAContextFactory 1.0-alpha-1-SNAPSHOT</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code implementationInfo} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private String getImplementationInfoMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "implementationInfo", locale,  null );
    }
    // SECTION-END
}
