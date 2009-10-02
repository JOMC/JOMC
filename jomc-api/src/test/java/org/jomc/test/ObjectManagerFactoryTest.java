// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@jomc.org>
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
package org.jomc.test;

import junit.framework.Assert;
import org.jomc.ObjectManagerFactory;

// SECTION-START[Documentation]
/**
 * Testcases for class {@code org.jomc.ObjectManagerFactory}.
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-3/jomc-tools" )
// SECTION-END
public class ObjectManagerFactoryTest
{
    // SECTION-START[ObjectManagerFactoryTest]

    private static final String SYS_FACTORY_CLASSNAME = "org.jomc.ObjectManagerFactory";

    private static final String SYS_IMPLEMENTATION_CLASSNAME = "org.jomc.ObjectManager";

    public void testObjectManagerFactory() throws Exception
    {
        final String objectManagerFactory = System.getProperty( SYS_FACTORY_CLASSNAME );
        final String objectManager = System.getProperty( SYS_IMPLEMENTATION_CLASSNAME );
        try
        {
            System.setProperty( SYS_FACTORY_CLASSNAME, ObjectManagerDummy.class.getName() );
            System.setProperty( SYS_IMPLEMENTATION_CLASSNAME, ObjectManagerDummy.class.getName() );
            Assert.assertNotNull( ObjectManagerFactory.getObjectManager() );
            Assert.assertTrue( ObjectManagerFactory.getObjectManager() == ObjectManagerFactory.getObjectManager() );
            Assert.assertNotNull( ObjectManagerFactory.newObjectManager() );
            Assert.assertFalse( ObjectManagerFactory.newObjectManager() == ObjectManagerFactory.newObjectManager() );
        }
        finally
        {
            if ( objectManagerFactory != null )
            {
                System.setProperty( SYS_FACTORY_CLASSNAME, objectManagerFactory );
            }
            else
            {
                System.clearProperty( SYS_FACTORY_CLASSNAME );
            }
            if ( objectManager != null )
            {
                System.setProperty( SYS_IMPLEMENTATION_CLASSNAME, objectManager );
            }
            else
            {
                System.clearProperty( SYS_IMPLEMENTATION_CLASSNAME );
            }
        }
    }
    // SECTION-END
    // SECTION-START[Constructors]

    /** Creates a new {@code ObjectManagerFactoryTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-3/jomc-tools" )
    public ObjectManagerFactoryTest()
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
