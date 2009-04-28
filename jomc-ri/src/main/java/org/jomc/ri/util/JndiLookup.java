// SECTION-START[License Header]
/*
 *  JOMC RI
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
package org.jomc.ri.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

// SECTION-START[Implementation Comment]
/**
 * Naming system lookup support class.
 * <p><b>Properties</b><ul>
 * <li>"{@link #getObjectName objectName}"<blockquote>
 * Property of type {@code java.lang.String}.</blockquote></li>
 * <li>"{@link #isRemoteObject remoteObject}"<blockquote>
 * Property of type {@code boolean} with value "false".</blockquote></li>
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
public class JndiLookup
{
    // SECTION-START[ObjectFactory]

    /**
     * Gets an object from the naming system.
     *
     * @return The object for the name as returned by method {@code getObjectName}.
     *
     * @throws NamingException if the lookup fails.
     */
    public Object getObject() throws NamingException
    {
        Object object = new InitialContext().lookup( this.getObjectName() );

        if ( object != null && this.isRemoteObject() )
        {
            object = PortableRemoteObject.narrow( object, Object.class );
        }

        return object;
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public JndiLookup()
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
     * Gets the value of the {@code objectName} property.
     * @return The JNDI name of the object to lookup.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.String getObjectName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "objectName" );
    }

    /**
     * Gets the value of the {@code remoteObject} property.
     * @return Flag indicating the remote status of the JNDI object. True to narrow any JNDI objects. False to return JNDI objects unchanged.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private boolean isRemoteObject() throws org.jomc.ObjectManagementException
    {
        return ( (java.lang.Boolean) org.jomc.ObjectManager.getInstance().getProperty( this, "remoteObject" ) ).booleanValue();
    }
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
