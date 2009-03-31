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
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class PersistenceFramework
{
    // SECTION-START[PersistenceFramework]

    public EntityManagerFactory getEntityManagerFactory() throws NamingException
    {
        return (EntityManagerFactory) this.getInitialContext().lookup(
            this.getSdkEnvironment().getEntityManagerFactoryJndiName() );

    }

    public EntityManager getEntityManager() throws NamingException
    {
        final EntityManager entityManager =
            (EntityManager) this.getInitialContext().lookup( this.getSdkEnvironment().getEntityManagerJndiName() );

        if ( !this.isContainerManaged() )
        {
            entityManager.joinTransaction();
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
        comments = "See http://www.jomc.org/jomc-tools"
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

    /**
     * Gets the value of the {@code containerManaged} property.
     * @return {@code true} if the {@code EntityManager} looked up from the JNDI context is provided by an EJB container; {@code false} to perform a call to the {@code EntityManager.joinTransaction} method on each JNDI lookup.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private boolean isContainerManaged() throws org.jomc.ObjectManagementException
    {
        return ( (java.lang.Boolean) org.jomc.ObjectManager.getInstance().getProperty( this, "containerManaged" ) ).booleanValue();
    }
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
