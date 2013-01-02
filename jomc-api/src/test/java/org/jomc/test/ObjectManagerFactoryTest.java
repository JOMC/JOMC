// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Java Object Management and Configuration
 *   Copyright (C) Christian Schulte, 2005-206
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
 *   THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *   INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 *   AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 *   THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *   NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *   DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *   THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *   THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $JOMC$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.test;

import org.jomc.ObjectManagerFactory;
import org.jomc.test.support.UnsupportedOperationExceptionObjectManager;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test cases for class {@code org.jomc.ObjectManagerFactory}.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.test.ObjectManagerFactoryTest</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ API</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>Yes</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 2.0-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://www.jomc.org/jomc/2.0/jomc-tools-2.0-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class ObjectManagerFactoryTest
{
    // SECTION-START[ObjectManagerFactoryTest]

    private static final String SYS_FACTORY_CLASSNAME = "org.jomc.ObjectManagerFactory";

    private static final String SYS_IMPL_CLASSNAME = "org.jomc.ObjectManager";

    @Test
    public final void testObjectManagerFactory() throws Exception
    {
        final String objectManagerFactory = System.getProperty( SYS_FACTORY_CLASSNAME );
        final String objectManager = System.getProperty( SYS_IMPL_CLASSNAME );
        try
        {
            System.setProperty( SYS_FACTORY_CLASSNAME, UnsupportedOperationExceptionObjectManager.class.getName() );
            System.setProperty( SYS_IMPL_CLASSNAME, UnsupportedOperationExceptionObjectManager.class.getName() );
            assertNotNull( ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ) );
            assertTrue( ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() )
                        == ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ) );

            assertNotNull( ObjectManagerFactory.newObjectManager( this.getClass().getClassLoader() ) );
            assertFalse( ObjectManagerFactory.newObjectManager( this.getClass().getClassLoader() )
                         == ObjectManagerFactory.newObjectManager( this.getClass().getClassLoader() ) );

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
                System.setProperty( SYS_IMPL_CLASSNAME, objectManager );
            }
            else
            {
                System.clearProperty( SYS_IMPL_CLASSNAME );
            }
        }
    }
    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code ObjectManagerFactoryTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://www.jomc.org/jomc/2.0/jomc-tools-2.0-SNAPSHOT" )
    public ObjectManagerFactoryTest()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
