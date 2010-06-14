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
package org.jomc.model.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import junit.framework.Assert;
import org.jomc.model.Dependencies;
import org.jomc.model.Dependency;
import org.jomc.model.Implementation;
import org.jomc.model.Implementations;
import org.jomc.model.Instance;
import org.jomc.model.Message;
import org.jomc.model.Messages;
import org.jomc.model.ModelContext;
import org.jomc.model.ModelException;
import org.jomc.model.ModelObject;
import org.jomc.model.ModelValidationReport;
import org.jomc.model.Modules;
import org.jomc.model.Properties;
import org.jomc.model.Property;
import org.jomc.model.Specification;
import org.jomc.model.SpecificationReference;
import org.jomc.model.Specifications;
import org.jomc.model.Text;
import org.jomc.model.Texts;

/**
 * Test cases for class {@code org.jomc.model.Modules}.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
public class ModulesTest
{

    private TestSuite testSuite;

    public ModulesTest()
    {
        this( null );
    }

    public ModulesTest( final TestSuite testSuite )
    {
        super();
        this.testSuite = testSuite;
    }

    public TestSuite getTestSuite() throws ModelException
    {
        try
        {
            if ( this.testSuite == null )
            {
                final ModelContext context = ModelContext.createModelContext( this.getClass().getClassLoader() );
                final JAXBElement<TestSuite> e = (JAXBElement<TestSuite>) context.createUnmarshaller().unmarshal(
                    this.getClass().getResource( "testsuite.xml" ) );

                this.testSuite = e.getValue();
            }

            return this.testSuite;
        }
        catch ( final JAXBException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
    }

    public void testImplementations() throws Exception
    {
        final ModelContext context = ModelContext.createModelContext( this.getClass().getClassLoader() );
        final JAXBContext jaxbContext = context.createContext();

        for ( ImplementationTest test : this.getTestSuite().getImplementationTest() )
        {
            System.out.println( "ImplementationTest: " + test.getIdentifier() );

            final JAXBElement<Modules> modules = (JAXBElement<Modules>) test.getModules().getAny();
            final ModelValidationReport modulesReport = context.validateModel( modules.getValue() );

            if ( !modulesReport.isModelValid() )
            {
                log( modulesReport );
            }

            Assert.assertTrue( "[" + test.getIdentifier() + "] Unexpected invalid modules.",
                               modulesReport.isModelValid() );

            final JAXBElement<Implementation> expected =
                (JAXBElement<Implementation>) test.getImplementation().getAny();

            final ModelValidationReport implementationReport =
                context.validateModel( new JAXBSource( jaxbContext, expected ) );

            if ( !implementationReport.isModelValid() )
            {
                log( implementationReport );
            }

            Assert.assertTrue( "[" + test.getIdentifier() + "] Unexpected invalid implementation.",
                               implementationReport.isModelValid() );

            final Implementation i = modules.getValue().getImplementation( expected.getValue().getIdentifier() );

            Assert.assertNotNull( i );
            assertEquals( expected.getValue(), i );
            assertEquals( expected.getValue().getDependencies(),
                          modules.getValue().getDependencies( expected.getValue().getIdentifier() ) );

            assertEquals( expected.getValue().getMessages(),
                          modules.getValue().getMessages( expected.getValue().getIdentifier() ) );

            assertEquals( expected.getValue().getProperties(),
                          modules.getValue().getProperties( expected.getValue().getIdentifier() ) );

            assertEquals( expected.getValue().getSpecifications(),
                          modules.getValue().getSpecifications( expected.getValue().getIdentifier() ) );

        }
    }

    public void testInstances() throws Exception
    {
        final ModelContext context = ModelContext.createModelContext( this.getClass().getClassLoader() );

        for ( InstanceTest test : this.getTestSuite().getInstanceTest() )
        {
            System.out.println( "InstanceTest: " + test.getIdentifier() );

            final JAXBElement<Modules> modules = (JAXBElement<Modules>) test.getModules().getAny();
            ModelValidationReport validationReport = context.validateModel( modules.getValue() );

            if ( !validationReport.isModelValid() )
            {
                log( validationReport );
            }

            Assert.assertTrue( "[" + test.getIdentifier() + "] Unexpected invalid modules.",
                               validationReport.isModelValid() );

            final JAXBElement<Instance> expected = (JAXBElement<Instance>) test.getInstance().getAny();
            validationReport = context.validateModel( new JAXBSource( context.createContext(), expected ) );

            if ( !validationReport.isModelValid() )
            {
                log( validationReport );
            }

            Assert.assertTrue( "[" + test.getIdentifier() + "] Unexpected invalid instance.",
                               validationReport.isModelValid() );

            Instance instance = null;

            if ( test.getDependencyName() != null )
            {
                final Dependencies dependencies =
                    modules.getValue().getDependencies( test.getImplementationIdentifier() );

                Assert.assertNotNull( "[" + test.getIdentifier() + "] No dependencies for implementation '"
                                      + test.getImplementationIdentifier() + "' not found.", dependencies );

                final Dependency d = dependencies.getDependency( test.getDependencyName() );
                Assert.assertNotNull( "[" + test.getIdentifier() + "] Dependency '" + test.getDependencyName()
                                      + "' not found.", d );

                Assert.assertNotNull( "[" + test.getIdentifier() + "] Expected implementation name of dependency '"
                                      + test.getDependencyName() + "' not set.", d.getImplementationName() );

                final Implementations implementations = modules.getValue().getImplementations( d.getIdentifier() );
                Assert.assertNotNull( "[" + test.getIdentifier() + "] Expected implementations of dependency '"
                                      + test.getDependencyName() + "' not found.", implementations );

                final Implementation i = implementations.getImplementationByName( d.getImplementationName() );
                Assert.assertNotNull( "[" + test.getIdentifier() + "] Expected '" + d.getImplementationName()
                                      + "' implementation not found.", i );

                instance = modules.getValue().getInstance( i.getIdentifier(), d );
            }
            else
            {
                instance = modules.getValue().getInstance( test.getImplementationIdentifier() );
            }

            Assert.assertNotNull( "[" + test.getIdentifier() + "] Expected instance not found.", instance );
            assertEquals( expected.getValue(), instance );
        }
    }

    public static void assertEquals( final ModelObject expected, final ModelObject computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            Assert.assertEquals( expected.getCreateDate(), computed.getCreateDate() );
            Assert.assertEquals( expected.getModelVersion(), computed.getModelVersion() );
            Assert.assertEquals( expected.isDeprecated(), computed.isDeprecated() );
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Instance expected, final Instance computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            assertEquals( (ModelObject) expected, (ModelObject) computed );
            Assert.assertEquals( expected.getClazz(), computed.getClazz() );
            assertEquals( expected.getDependencies(), computed.getDependencies() );
            Assert.assertEquals( expected.getIdentifier(), computed.getIdentifier() );
            assertEquals( expected.getMessages(), computed.getMessages() );
            Assert.assertEquals( expected.getName(), computed.getName() );
            assertEquals( expected.getProperties(), computed.getProperties() );
            assertEquals( expected.getSpecifications(), computed.getSpecifications() );
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Implementations expected, final Implementations computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            assertEquals( (ModelObject) expected, (ModelObject) computed );

            for ( Implementation i : expected.getImplementation() )
            {
                assertEquals( i, computed.getImplementation( i.getIdentifier() ) );
            }
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Implementation expected, final Implementation computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            assertEquals( (ModelObject) expected, (ModelObject) computed );
            Assert.assertEquals( expected.getClazz(), computed.getClazz() );
            Assert.assertEquals( expected.getIdentifier(), computed.getIdentifier() );
            Assert.assertEquals( expected.getLocation(), computed.getLocation() );
            Assert.assertEquals( expected.getName(), computed.getName() );
            Assert.assertEquals( expected.getVendor(), computed.getVendor() );
            Assert.assertEquals( expected.getVersion(), computed.getVersion() );
            Assert.assertEquals( expected.isAbstract(), computed.isAbstract() );
            Assert.assertEquals( expected.isFinal(), computed.isFinal() );
            Assert.assertEquals( expected.isStateless(), computed.isStateless() );
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Specifications expected, final Specifications computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            assertEquals( (ModelObject) expected, (ModelObject) computed );

            for ( Specification s : expected.getSpecification() )
            {
                assertEquals( s, computed.getSpecification( s.getIdentifier() ) );
            }

            for ( SpecificationReference r : expected.getReference() )
            {
                assertEquals( r, computed.getReference( r.getIdentifier() ) );
            }
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final SpecificationReference expected, final SpecificationReference computed )
        throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            assertEquals( (ModelObject) expected, (ModelObject) computed );

            Assert.assertEquals( expected.getIdentifier(), computed.getIdentifier() );
            Assert.assertEquals( expected.getVersion(), computed.getVersion() );
            Assert.assertEquals( expected.isDeprecated(), computed.isDeprecated() );
            Assert.assertEquals( expected.isFinal(), computed.isFinal() );
            Assert.assertEquals( expected.isOverride(), computed.isOverride() );
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Specification expected, final Specification computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            assertEquals( (ModelObject) expected, (ModelObject) computed );

            Assert.assertEquals( expected.getClazz(), computed.getClazz() );
            Assert.assertEquals( expected.getIdentifier(), computed.getIdentifier() );
            Assert.assertEquals( expected.getMultiplicity(), computed.getMultiplicity() );
            assertEquals( expected.getProperties(), computed.getProperties() );
            Assert.assertEquals( expected.getScope(), computed.getScope() );
            Assert.assertEquals( expected.getVendor(), computed.getVendor() );
            Assert.assertEquals( expected.getVersion(), computed.getVersion() );
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Dependencies expected, final Dependencies computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );

            for ( Dependency d : expected.getDependency() )
            {
                assertEquals( d, computed.getDependency( d.getName() ) );
            }
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Dependency expected, final Dependency computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            Assert.assertEquals( expected.getIdentifier(), computed.getIdentifier() );
            Assert.assertEquals( expected.getImplementationName(), computed.getImplementationName() );
            Assert.assertEquals( expected.getName(), computed.getName() );
            Assert.assertEquals( expected.isDeprecated(), computed.isDeprecated() );
            Assert.assertEquals( expected.isFinal(), computed.isFinal() );
            Assert.assertEquals( expected.isOverride(), computed.isOverride() );
            Assert.assertEquals( expected.isBound(), computed.isBound() );
            Assert.assertEquals( expected.isOptional(), computed.isOptional() );
            Assert.assertEquals( expected.getVersion(), computed.getVersion() );
            assertEquals( expected.getDependencies(), computed.getDependencies() );
            assertEquals( expected.getMessages(), computed.getMessages() );
            assertEquals( expected.getProperties(), computed.getProperties() );
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Messages expected, final Messages computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );

            for ( Message m : expected.getMessage() )
            {
                assertEquals( m, computed.getMessage( m.getName() ) );
            }
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Message expected, final Message computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            Assert.assertEquals( expected.getName(), computed.getName() );
            Assert.assertEquals( expected.isDeprecated(), computed.isDeprecated() );
            Assert.assertEquals( expected.isFinal(), computed.isFinal() );
            Assert.assertEquals( expected.isOverride(), computed.isOverride() );
            assertEquals( expected.getTemplate(), computed.getTemplate() );
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Texts expected, final Texts computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            Assert.assertEquals( expected.getDefaultLanguage(), computed.getDefaultLanguage() );

            for ( Text t : expected.getText() )
            {
                Assert.assertNotNull( computed.getText( t.getLanguage() ) );
                Assert.assertEquals( t.getValue(), computed.getText( t.getLanguage() ).getValue() );
            }

            for ( Text t : computed.getText() )
            {
                Assert.assertNotNull( expected.getText( t.getLanguage() ) );
            }
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Properties expected, final Properties computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );

            for ( Property p : expected.getProperty() )
            {
                assertEquals( p, computed.getProperty( p.getName() ) );
            }
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void assertEquals( final Property expected, final Property computed ) throws Exception
    {
        if ( expected != null )
        {
            Assert.assertNotNull( computed );
            Assert.assertEquals( expected.getJavaValue( ModelValidatorTest.class.getClassLoader() ),
                                 computed.getJavaValue( ModelValidatorTest.class.getClassLoader() ) );

            Assert.assertEquals( expected.getName(), computed.getName() );
            Assert.assertEquals( expected.getType(), computed.getType() );
            Assert.assertEquals( expected.getValue(), computed.getValue() );
            Assert.assertEquals( expected.isDeprecated(), computed.isDeprecated() );
            Assert.assertEquals( expected.isFinal(), computed.isFinal() );
            Assert.assertEquals( expected.isOverride(), computed.isOverride() );
        }
        else
        {
            Assert.assertNull( computed );
        }
    }

    public static void log( final ModelValidationReport report )
    {
        for ( ModelValidationReport.Detail d : report.getDetails() )
        {
            System.out.println( "\t" + d.toString() );
        }
    }

}