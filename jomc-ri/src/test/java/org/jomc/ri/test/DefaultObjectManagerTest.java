// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2010 The JOMC Project
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
// </editor-fold>
// SECTION-END
package org.jomc.ri.test;

import java.net.URI;
import java.util.Locale;
import org.jomc.ObjectManager;
import org.jomc.model.Instance;
import org.jomc.ri.DefaultObjectManager;
import org.jomc.spi.Scope;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test cases for the {@code DefaultObjectManager} class.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0.10.188", comments = "See http://jomc.sourceforge.net/jomc/1.1/jomc-tools" )
// </editor-fold>
// SECTION-END
public class DefaultObjectManagerTest
{
    // SECTION-START[DefaultObjectManagerTest]

    /** The instance tests are performed with. */
    private DefaultObjectManager objectManager;

    /**
     * Gets the instance tests are performed with.
     *
     * @return The instance tests are performed with.
     */
    protected DefaultObjectManager getObjectManager()
    {
        if ( this.objectManager == null )
        {
            this.objectManager = new DefaultObjectManager();
        }

        return this.objectManager;
    }

    /**
     * Tests that the instance will throw a {@code NullPointerException} with non-null message for any
     * {@code null} arguments.
     *
     * @throws Exception if testing fails.
     */
    public void testNullPointerException() throws Exception
    {
        try
        {
            DefaultObjectManager.getClassLoader( (Class) null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getDefaultLocator( null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getDefaultScope( null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getDependency( null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getDependency( this, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getLocator( null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getLocator( new URI( "TEST" ), null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getLocator( null, this.getClass().getClassLoader() );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getMessage( null, null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getMessage( this, null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getMessage( this, "", null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( (Scope) null, (Instance) null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( (Scope) null, new Instance(), null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( (Class<?>) null, null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( this.getClass(), null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( this.getClass(), new URI( "/" ), null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getProperty( null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getProperty( this, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getScope( null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getScope( "TEST", null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getScope( null, this.getClass().getClassLoader() );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }
    }

    /**
     * Tests that methods declared to not return {@code null} do not return {@code null}.
     *
     * @throws Exception if testing fails.
     */
    public void testNotNull() throws Exception
    {
        assertNotNull( this.getObjectManager().getListeners() );
        assertNotNull( this.getObjectManager().getModules( this.getClass().getClassLoader() ) );
        assertNotNull( this.getObjectManager().getObject( TestSpecificationOne.class ) );
        assertNotNull( this.getObjectManager().getObject( TestSpecificationOne.class, "TestImplementation" ) );
        assertNotNull( this.getObjectManager().getObject( TestSpecificationMany[].class ) );
        assertNotNull( this.getObjectManager().getObject( TestSpecificationMany.class, "TestImplementation" ) );
        assertNotNull( this.getObjectManager().getObject( TestScopeSpecification[].class ) );
        assertNotNull( this.getObjectManager().getObject( TestScopeSpecification.class, "TestImplementation" ) );
    }

    /**
     * Tests that methods declared to return {@code null} for any missing model objects do not throw an exception.
     *
     * @throws Exception if testing fails.
     */
    public void testNull() throws Exception
    {
        assertNull( this.getObjectManager().getObject( Object.class ) );
        assertNull( this.getObjectManager().getObject( TestSpecification.class ) );
        assertNull( this.getObjectManager().getObject( TestSpecificationOne[].class ) );
        assertNull( this.getObjectManager().getObject( TestSpecificationOneMore.class ) );
        assertNull( this.getObjectManager().getObject( TestSpecificationMany.class ) );
        assertNull( this.getObjectManager().getObject( ObjectManager.class, "DOES NOT EXIST" ) );
        assertNull( this.getObjectManager().getObject( TestSpecification.class, "DOES NOT EXIST" ) );
        assertNull( this.getObjectManager().getDependency( this, "DOES NOT EXIST" ) );
        assertNull( this.getObjectManager().getProperty( this, "DOES NOT EXIST" ) );
        assertNull( this.getObjectManager().getMessage( this, "DOES NOT EXIST", Locale.getDefault() ) );
        assertNull( this.getObjectManager().getObject( TestSpecificationOneMore.class,
                                                       "IllegalLocationImplementation" ) );

        assertNull( this.getObjectManager().getObject( TestScopeSpecification.class,
                                                       "TestLocatorImplementation" ) );

    }

    /**
     * Tests that the {@link DefaultObjectManager#getObjectManager(java.lang.ClassLoader)} method returns the same
     * object on successive calls.
     *
     * @throws Exception if testing fails.
     */
    public void testGetObjectManager() throws Exception
    {
        final ObjectManager first = DefaultObjectManager.getObjectManager( this.getClass().getClassLoader() );
        final ObjectManager second = DefaultObjectManager.getObjectManager( this.getClass().getClassLoader() );
        final ObjectManager third = DefaultObjectManager.getObjectManager( this.getClass().getClassLoader() );

        assertTrue( first == second );
        assertTrue( first == third );
        assertTrue( second == third );
    }

    public static void assertNullPointerException( final NullPointerException e )
    {
        assertNotNull( e.getMessage() );
        System.out.println( e.toString() );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultObjectManagerTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0.10.188", comments = "See http://jomc.sourceforge.net/jomc/1.1/jomc-tools" )
    public DefaultObjectManagerTest()
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
