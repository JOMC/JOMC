// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@schulte.it>
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
package org.jomc;

import java.lang.reflect.Method;

// SECTION-START[Implementation Comment]
/**
 * Factory for the {@code ObjectManager} singleton.
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://www.jomc.org/jomc-tools"
)
// SECTION-END
public class ObjectManagerFactory
{
    // SECTION-START[ObjectManagerFactory]

    /** Constant for the default classname providing the {@code getInstance()} and {@code newInstance()} methods. */
    private static final String DEFAULT_FACTORY_CLASS = "org.jomc.ri.DefaultObjectManager";

    /** Constant for the classname of the default {@code ObjectManager} implementation. */
    private static final String DEFAULT_IMPLEMENTATION = "org.jomc.ri.DefaultObjectManager";

    /** Constant for the name of the system property holding the {@code getInstance()} factory classname. */
    private static final String SYS_FACTORY_CLASSNAME = "jomc.ObjectManagerFactory";

    /** Constant for the name of the system property holding the {@code newInstance()} implementation classname. */
    private static final String SYS_IMPLEMENTATION_CLASSNAME = "jomc.ObjectManager";

    /** Empty {@code Class} array. */
    private static final Class[] NO_CLASSES =
    {
    };

    /** Empty {@code Object} array. */
    private static final Object[] NO_OBJECTS =
    {
    };

    /**
     * Gets the {@code ObjectManager} singleton instance.
     * <p>This method is controlled by system property {@code org.jomc.ObjectManagerFactory} providing the name of a
     * class declaring a <blockquote>{@code public static ObjectManager getObjectManager()}</blockquote> method called
     * by this method to get the instance to return.</p>
     * <p>The {@code newObjectManager} method should be used by {@code getObjectManager} implementors to retrieve a new
     * {@code ObjectManager} implementation.</p>
     *
     * @return The {@code ObjectManager} singleton instance.
     *
     * @see ObjectManagerFactory#newObjectManager()
     *
     * @throws ObjectManagementException if getting the singleton instance fails.
     */
    public static ObjectManager getObjectManager() throws ObjectManagementException
    {
        final String factory = System.getProperty( SYS_FACTORY_CLASSNAME, DEFAULT_FACTORY_CLASS );

        try
        {
            final Class factoryClass = Class.forName( factory );
            final Method factoryMethod = factoryClass.getMethod( "getObjectManager", NO_CLASSES );
            return (ObjectManager) factoryMethod.invoke( null, NO_OBJECTS );
        }
        catch ( Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Creates a new {@code ObjectManager} instance.
     * <p>The object manager implementation returned by this method is controlled by system property
     * {@code org.jomc.ObjectManager} providing the name of the {@code ObjectManager} implementation to return.</p>
     *
     * @return A new {@code ObjectManager} instance.
     *
     * @throws ObjectManagementException if creating a new {@code ObjectManager} instance fails.
     */
    public static ObjectManager newObjectManager() throws ObjectManagementException
    {
        final String impl = System.getProperty( SYS_IMPLEMENTATION_CLASSNAME, DEFAULT_IMPLEMENTATION );

        try
        {
            return (ObjectManager) Class.forName( impl ).newInstance();
        }
        catch ( Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public ObjectManagerFactory()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
