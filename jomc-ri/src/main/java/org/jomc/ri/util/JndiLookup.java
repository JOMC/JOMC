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
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "objectName" );
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
        return ( (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "remoteObject" ) ).booleanValue();
    }
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
