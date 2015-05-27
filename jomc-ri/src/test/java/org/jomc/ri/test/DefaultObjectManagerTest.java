// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 * Java Object Management and Configuration
 * Copyright (C) Christian Schulte <cs@schulte.it>, 2005-206
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   o Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   o Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $JOMC$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.ri.test;

import java.net.URI;
import java.util.Locale;
import org.jomc.ObjectManager;
import org.jomc.model.Instance;
import org.jomc.model.Modules;
import org.jomc.ri.DefaultObjectManager;
import org.jomc.ri.test.support.TestScopeSpecification;
import org.jomc.ri.test.support.TestSpecification;
import org.jomc.ri.test.support.TestSpecificationMany;
import org.jomc.ri.test.support.TestSpecificationOne;
import org.jomc.ri.test.support.TestSpecificationOneMore;
import org.jomc.spi.Scope;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test cases for the {@code DefaultObjectManager} class.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>JOMC ⁑ RI ⁑ Tests ⁑ Default Object Manager Test</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ RI ⁑ Tests ⁑ Default Object Manager Test</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 1.9-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class DefaultObjectManagerTest
{
    // SECTION-START[DefaultObjectManagerTest]

    /**
     * The {@code DefaultObjectManager} tests are performed with.
     */
    private DefaultObjectManager objectManager;

    /**
     * Gets the instance tests are performed with.
     *
     * @return The instance tests are performed with.
     *
     * @see #newObjectManager()
     */
    public DefaultObjectManager getObjectManager()
    {
        if ( this.objectManager == null )
        {
            this.objectManager = this.newObjectManager();
        }

        return this.objectManager;
    }

    /**
     * Creates a new {@code DefaultObjectManager} instance to test.
     *
     * @return A new {@code DefaultObjectManager} instance to test.
     *
     * @see #getObjectManager()
     */
    protected DefaultObjectManager newObjectManager()
    {
        return new DefaultObjectManager();
    }

    @Test
    @SuppressWarnings( "deprecation" )
    public final void testNullPointerException() throws Exception
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
            this.getObjectManager().getDefaultClassLoader( (Class<?>) null );
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
            this.getObjectManager().getDefaultLocator( null, new URI( "file:///tmp" ) );
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
            this.getObjectManager().getDefaultScope( new Modules(), null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }
        try
        {
            this.getObjectManager().getDefaultScope( null, "Singleton" );
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

        try
        {
            this.getObjectManager().log( null, null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().log( null, null, null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().log( this.getClass().getClassLoader(), null, null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }
    }

    @Test
    public final void testNotNull() throws Exception
    {
        assertNotNull( this.getObjectManager().getListeners() );
        assertNotNull( this.getObjectManager().getModules( this.getClass().getClassLoader() ) );
        assertNotNull( this.getObjectManager().getObject( TestSpecificationOne.class ) );
        assertNotNull( this.getObjectManager().getObject( TestSpecificationOne.class,
                                                          "JOMC ⁑ RI ⁑ Tests ⁑ Implementation Test" ) );

        assertNotNull( this.getObjectManager().getObject( TestSpecificationMany[].class ) );
        assertNotNull( this.getObjectManager().getObject( TestSpecificationMany.class,
                                                          "JOMC ⁑ RI ⁑ Tests ⁑ Implementation Test" ) );

        assertNotNull( this.getObjectManager().getObject( TestScopeSpecification[].class ) );
        assertNotNull( this.getObjectManager().getObject( TestScopeSpecification.class,
                                                          "JOMC ⁑ RI ⁑ Tests ⁑ Implementation Test" ) );

    }

    @Test
    public final void testNull() throws Exception
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
                                                       "JOMC ⁑ RI ⁑ Tests ⁑ Illegal Location Implementation" ) );

        assertNull( this.getObjectManager().getObject( TestScopeSpecification.class,
                                                       "JOMC ⁑ RI ⁑ Tests ⁑ Illegal Location Implementation" ) );

    }

    @Test
    public final void testGetObjectManager() throws Exception
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
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
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
