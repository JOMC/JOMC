// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2010 The JOMC Project
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
// </editor-fold>
// SECTION-END
package org.jomc.ri.test;

import java.net.URI;
import java.util.Locale;
import junit.framework.Assert;
import org.jomc.ObjectManager;
import org.jomc.model.Instance;
import org.jomc.ri.DefaultObjectManager;
import org.jomc.spi.Scope;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test cases for the {@code DefaultObjectManager} class.
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-13-SNAPSHOT/jomc-tools" )
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
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getDefaultLocator( null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getDefaultScope( null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getDependency( null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getDependency( this, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getLocator( null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getLocator( this.getClass().getClassLoader(), null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getMessage( null, null, null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getMessage( this, null, null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getMessage( this, "", null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( (Scope) null, (Instance) null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( (Scope) null, new Instance(), null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( (Class) null, null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( this.getClass(), null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getObject( this.getClass(), new URI( "/" ), null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getProperty( null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getProperty( this, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getScope( null, null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getObjectManager().getScope( this.getClass().getClassLoader(), null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( NullPointerException e )
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
        Assert.assertNotNull( this.getObjectManager().getListeners() );
        Assert.assertNotNull( this.getObjectManager().getModules( this.getClass().getClassLoader() ) );
        Assert.assertNotNull( this.getObjectManager().getObject( TestSpecificationOne.class ) );
        Assert.assertNotNull( this.getObjectManager().getObject( TestSpecificationOne.class, "TestImplementation" ) );
        Assert.assertNotNull( this.getObjectManager().getObject( TestSpecificationMany.class ) );
        Assert.assertNotNull( this.getObjectManager().getObject( TestSpecificationMany.class, "TestImplementation" ) );
        Assert.assertNotNull( this.getObjectManager().getObject( TestScopeSpecification.class ) );
        Assert.assertNotNull( this.getObjectManager().getObject( TestScopeSpecification.class, "TestImplementation" ) );
    }

    /**
     * Tests that methods declared to return {@code null} for any missing model objects do not throw an exception.
     *
     * @throws Exception if testing fails.
     */
    public void testNull() throws Exception
    {
        Assert.assertNull( this.getObjectManager().getObject( Object.class ) );
        Assert.assertNull( this.getObjectManager().getObject( TestSpecification.class ) );
        Assert.assertNull( this.getObjectManager().getObject( TestSpecificationOneMore.class ) );
        Assert.assertNull( this.getObjectManager().getObject( ObjectManager.class, "DOES NOT EXIST" ) );
        Assert.assertNull( this.getObjectManager().getObject( TestSpecification.class, "DOES NOT EXIST" ) );
        Assert.assertNull( this.getObjectManager().getDependency( this, "DOES NOT EXIST" ) );
        Assert.assertNull( this.getObjectManager().getProperty( this, "DOES NOT EXIST" ) );
        Assert.assertNull( this.getObjectManager().getMessage( this, "DOES NOT EXIST", Locale.getDefault(), null ) );
        Assert.assertNull( this.getObjectManager().getObject( TestSpecificationOneMore.class,
                                                              "IllegalLocationImplementation" ) );

        Assert.assertNull( this.getObjectManager().getObject( TestScopeSpecification.class,
                                                              "TestLocatorImplementation" ) );

    }

    public static void assertNullPointerException( final NullPointerException e )
    {
        Assert.assertNotNull( e.getMessage() );
        System.out.println( e.toString() );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultObjectManagerTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-13-SNAPSHOT/jomc-tools" )
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
