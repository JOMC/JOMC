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
package org.jomc.tools.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jomc.model.Implementation;
import org.jomc.model.Module;
import org.jomc.model.Specification;
import org.jomc.tools.JavaSources;
import org.jomc.tools.ToolException;
import org.jomc.util.SectionEditor;

/**
 * Tests {@code JavaSources} implementations.
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a>
 * @version $Id$
 */
public class JavaSourcesTest extends JomcToolTest
{

    /** Serial number of the test sources directory. */
    private int testSourcesId;

    /** The {@code JavaSources} instance tests are performed with. */
    private JavaSources testTool;

    @Override
    public JavaSources getTestTool() throws ToolException
    {
        if ( this.testTool == null )
        {
            this.testTool = new JavaSources();
            this.testTool.setModules( this.getTestModules() );
        }

        return this.testTool;
    }

    /**
     * Gets the directory to write sources to.
     *
     * @return The directory to write sources to.
     *
     * @throws IOException if getting the directory fails.
     */
    public File getTestSourcesDirectory() throws IOException
    {
        final File testSourcesDirectory =
            new File( this.getTestProperty( "testSourcesDirectory" ), Integer.toString( this.testSourcesId++ ) );

        if ( testSourcesDirectory.exists() )
        {
            FileUtils.cleanDirectory( testSourcesDirectory );
        }

        return testSourcesDirectory;
    }

    private String getTestProperty( final String key ) throws IOException
    {
        final java.util.Properties p = new java.util.Properties();
        final InputStream in = this.getClass().getResourceAsStream( "JavaSourcesTest.properties" );
        p.load( in );
        in.close();

        return p.getProperty( key );
    }

    @Override
    public void testNullPointerException() throws Exception
    {
        super.testNullPointerException();

        try
        {
            this.getTestTool().getSourceFileType( (Specification) null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getTestTool().getSourceFileType( (Implementation) null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getTestTool().getSourceCodeEditor( (Specification) null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getTestTool().getSourceCodeEditor( (Implementation) null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getTestTool().manageSources( null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getTestTool().manageSources( (Implementation) null, new File( "/" ) );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getTestTool().manageSources( new Implementation(), null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getTestTool().manageSources( (Module) null, new File( "/" ) );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }


        try
        {
            this.getTestTool().manageSources( new Module(), null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getTestTool().manageSources( (Specification) null, new File( "/" ) );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }

        try
        {
            this.getTestTool().manageSources( new Specification(), null );
            Assert.fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNullPointerException( e );
        }
    }

    @Override
    public void testNotNull() throws Exception
    {
        super.testNotNull();

        final Implementation i = new Implementation();
        i.setIdentifier( "TEST" );
        i.setClazz( "TEST" );

        final Specification s = new Specification();
        s.setIdentifier( "TEST" );
        s.setClazz( "TEST" );

        Assert.assertNotNull( this.getTestTool().getSourceFileType( i ) );
        Assert.assertNotNull( this.getTestTool().getSourceFileType( s ) );
        Assert.assertNotNull( this.getTestTool().getSourceCodeEditor( i ) );
        Assert.assertNotNull( this.getTestTool().getSourceCodeEditor( s ) );
    }

    public void testManageSources() throws Exception
    {
        this.getTestTool().manageSources( this.getTestSourcesDirectory() );
        this.getTestTool().manageSources( this.getTestTool().getModules().getModule( "Module" ),
                                          this.getTestSourcesDirectory() );

        final File implementationDirectory = this.getTestSourcesDirectory();
        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        final File specificationDirectory = this.getTestSourcesDirectory();
        this.getTestTool().manageSources( this.getTestTool().getModules().getSpecification( "Specification" ),
                                          specificationDirectory );

        this.getTestTool().manageSources( this.getTestTool().getModules().getSpecification( "Specification" ),
                                          specificationDirectory );

        IOUtils.copy( this.getClass().getResourceAsStream( "IllegalImplementationSource.java.txt" ),
                      new FileOutputStream( new File( implementationDirectory, "Implementation.java" ) ) );

        IOUtils.copy( this.getClass().getResourceAsStream( "IllegalSpecificationSource.java.txt" ),
                      new FileOutputStream( new File( specificationDirectory, "Specification.java" ) ) );

        try
        {
            this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                              implementationDirectory );

            Assert.fail( "Expected ToolException not thrown." );
        }
        catch ( final ToolException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getTestTool().manageSources( this.getTestTool().getModules().getSpecification( "Specification" ),
                                              specificationDirectory );

            Assert.fail( "Expected ToolException not thrown." );
        }
        catch ( final ToolException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        this.getTestTool().setProfile( "DOES_NOT_EXIST" );

        this.getTestTool().manageSources( this.getTestSourcesDirectory() );
        this.getTestTool().manageSources( this.getTestTool().getModules().getModule( "Module" ),
                                          this.getTestSourcesDirectory() );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          this.getTestSourcesDirectory() );

        this.getTestTool().manageSources( this.getTestTool().getModules().getSpecification( "Specification" ),
                                          this.getTestSourcesDirectory() );

    }

    public void testMandatorySections() throws Exception
    {
        final SectionEditor editor = new SectionEditor();
        final File specificationDirectory = this.getTestSourcesDirectory();
        final File implementationDirectory = this.getTestSourcesDirectory();

        File f = new File( implementationDirectory, "Implementation.java" );
        IOUtils.copy( this.getClass().getResourceAsStream( "ImplementationWithoutAnnotationsSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        String edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertTrue( editor.isSectionPresent( "Annotations" ) );

        f = new File( implementationDirectory, "Implementation.java" );
        IOUtils.copy( this.getClass().getResourceAsStream( "ImplementationWithoutDependenciesSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertTrue( editor.isSectionPresent( "Dependencies" ) );

        f = new File( implementationDirectory, "Implementation.java" );
        IOUtils.copy( this.getClass().getResourceAsStream( "ImplementationWithoutMessagesSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertTrue( editor.isSectionPresent( "Messages" ) );

        f = new File( implementationDirectory, "Implementation.java" );
        IOUtils.copy( this.getClass().getResourceAsStream( "ImplementationWithoutPropertiesSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertTrue( editor.isSectionPresent( "Properties" ) );

        f = new File( implementationDirectory, "ImplementationOfSpecification.java" );
        IOUtils.copy(
            this.getClass().getResourceAsStream( "ImplementationOfSpecificationWithoutConstructorsSection.java.txt" ),
            new FileOutputStream( f ) );

        this.getTestTool().manageSources(
            this.getTestTool().getModules().getImplementation( "ImplementationOfSpecification" ),
            implementationDirectory );

        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertTrue( editor.isSectionPresent( "Constructors" ) );

        f = new File( specificationDirectory, "Specification.java" );
        IOUtils.copy( this.getClass().getResourceAsStream( "SpecificationWithoutAnnotationsSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getSpecification( "Specification" ),
                                          specificationDirectory );

        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertTrue( editor.isSectionPresent( "Annotations" ) );
    }

    public void testOptionalSections() throws Exception
    {
        final SectionEditor editor = new SectionEditor();
        final File implementationDirectory = this.getTestSourcesDirectory();
        final File specificationDirectory = this.getTestSourcesDirectory();

        File f = new File( implementationDirectory, "Implementation.java" );
        IOUtils.copy( this.getClass().getResourceAsStream( "ImplementationWithoutConstructorsSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        String edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertFalse( editor.isSectionPresent( "Constructors" ) );

        IOUtils.copy( this.getClass().getResourceAsStream( "ImplementationWithoutDefaultConstructorSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertTrue( editor.isSectionPresent( "Constructors" ) );
        Assert.assertTrue( editor.isSectionPresent( "Default Constructor" ) );

        IOUtils.copy( this.getClass().getResourceAsStream( "ImplementationWithoutDocumentationSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertFalse( editor.isSectionPresent( "Documentation" ) );

        IOUtils.copy( this.getClass().getResourceAsStream( "ImplementationWithoutLicenseSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getImplementation( "Implementation" ),
                                          implementationDirectory );

        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertFalse( editor.isSectionPresent( "License Header" ) );

        f = new File( specificationDirectory, "Specification.java" );
        IOUtils.copy( this.getClass().getResourceAsStream( "SpecificationWithoutDocumentationSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getSpecification( "Specification" ),
                                          specificationDirectory );


        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertFalse( editor.isSectionPresent( "Documentation" ) );

        IOUtils.copy( this.getClass().getResourceAsStream( "SpecificationWithoutLicenseSection.java.txt" ),
                      new FileOutputStream( f ) );

        this.getTestTool().manageSources( this.getTestTool().getModules().getSpecification( "Specification" ),
                                          specificationDirectory );

        edited = IOUtils.toString( new FileInputStream( f ) );
        editor.edit( edited );
        Assert.assertFalse( editor.isSectionPresent( "License Header" ) );
    }

    public void testCopyConstructor() throws Exception
    {
        new JavaSources( this.getTestTool() );
    }

}
