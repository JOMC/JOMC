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
package org.jomc.sdk.transaction;

import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;
import org.jomc.sdk.Environment;

// SECTION-START[Implementation Comment]
/**
 * Transaction framework.
 * <p><b>Specifications</b><ul>
 * <li>{@code javax.transaction.TransactionManager}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * <li>{@code javax.transaction.UserTransaction}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * <li>{@code javax.transaction.TransactionSynchronizationRegistry}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getInitialContext InitialContext}"<blockquote>
 * Dependency on {@code javax.naming.Context} applying to Multiton scope.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class TransactionFramework
{
    // SECTION-START[TransactionFramework]

    public TransactionManager getTransactionManager() throws NamingException
    {
        return (TransactionManager) this.getInitialContext().lookup(
            this.getSdkEnvironment().getTransactionManagerJndiName() );

    }

    public UserTransaction getUserTransaction() throws NamingException
    {
        return (UserTransaction) this.getInitialContext().lookup(
            this.getSdkEnvironment().getUserTransactionJndiName() );

    }

    public TransactionSynchronizationRegistry getTransactionSynchronizationRegistry() throws NamingException
    {
        return (TransactionSynchronizationRegistry) this.getInitialContext().lookup(
            this.getSdkEnvironment().getTransactionSynchronizationRegistryJndiName() );

    }

    // SECTION-END
    // SECTION-START[TransactionFramework]

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
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public TransactionFramework()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code InitialContext} dependency.
     * </p>
     * @return The {@code InitialContext} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private javax.naming.Context getInitialContext() throws org.jomc.ObjectManagementException
    {
        return (javax.naming.Context) org.jomc.ObjectManager.getInstance().getDependency( this, "InitialContext" );
    }
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
