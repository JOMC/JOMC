// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
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
package org.jomc.ri.test;

import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.assertNull;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Implementation {@code <JOMC :: RI :: Tests :: Implementation Test>}.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>JOMC :: RI :: Tests :: Implementation Test</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC :: RI :: Tests :: Implementation Test</dd>
 *   <dt><b>Specifications:</b></dt>
 *     <dd>JOMC :: RI :: Tests :: Test Multiton Specification @ 1.2-SNAPSHOT</dd>
 *     <dd>JOMC :: RI :: Tests :: Test Scope Specification @ 1.2-SNAPSHOT</dd>
 *     <dd>JOMC :: RI :: Tests :: Test Singleton Specification @ 1.2-SNAPSHOT</dd>
 *     <dd>JOMC :: RI :: Tests :: Test Specification Many @ 1.2-SNAPSHOT</dd>
 *     <dd>JOMC :: RI :: Tests :: Test Specification One @ 1.2-SNAPSHOT</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 1.2-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class ImplementationTest
    implements
    org.jomc.ri.test.support.TestSpecification,
    org.jomc.ri.test.support.TestSpecificationOne,
    org.jomc.ri.test.support.TestSpecificationMany,
    org.jomc.ri.test.support.TestScopeSpecification
{
    // SECTION-START[TestSpecification]
    // SECTION-END
    // SECTION-START[TestScopeSpecification]
    // SECTION-END
    // SECTION-START[TestSpecificationMany]
    // SECTION-END
    // SECTION-START[TestSpecificationOne]
    // SECTION-END
    // SECTION-START[ImplementationTest]

    private static final int NUM_REQUESTS = 25000;

    private static final int NUM_RUNS = 2;

    @Test
    public final void testBoundMultitons() throws Exception
    {
        this.printEstimatedExecutionTime( "BoundMultitons", new Runnable()
        {

            public void run()
            {
                getBoundMultitons();
            }

        } );

    }

    @Test
    public final void testUnboundMultitons() throws Exception
    {
        this.printEstimatedExecutionTime( "UnboundMultitons", new Runnable()
        {

            public void run()
            {
                getUnboundMultitons();
            }

        } );

    }

    @Test
    public final void testSelectedBoundMultiton() throws Exception
    {
        this.printEstimatedExecutionTime( "SelectedBoundMultiton", new Runnable()
        {

            public void run()
            {
                getSelectedBoundMultiton();
            }

        } );

    }

    @Test
    public final void testSelectedUnboundMultiton() throws Exception
    {
        this.printEstimatedExecutionTime( "SelectedUnboundMultiton", new Runnable()
        {

            public void run()
            {
                getSelectedUnboundMultiton();
            }

        } );

    }

    @Test
    public final void testBoundSingletons() throws Exception
    {
        this.printEstimatedExecutionTime( "BoundSingletons", new Runnable()
        {

            public void run()
            {
                getBoundSingletons();
            }

        } );

    }

    @Test
    public final void testUnboundSingletons() throws Exception
    {
        this.printEstimatedExecutionTime( "UnboundSingletons", new Runnable()
        {

            public void run()
            {
                getUnboundSingletons();
            }

        } );

    }

    @Test
    public final void testSelectedBoundSingleton() throws Exception
    {
        this.printEstimatedExecutionTime( "SelectedBoundSingleton", new Runnable()
        {

            public void run()
            {
                getSelectedBoundSingleton();
            }

        } );

    }

    @Test
    public final void testSelectedUnboundSingleton() throws Exception
    {
        this.printEstimatedExecutionTime( "SelectedUnboundSingleton", new Runnable()
        {

            public void run()
            {
                getSelectedUnboundSingleton();
            }

        } );

    }

    @Test
    public final void testProperties() throws Exception
    {
        this.printEstimatedExecutionTime( "TestProperty", new Runnable()
        {

            public void run()
            {
                getTestProperty();
            }

        } );

    }

    @Test
    public final void testMessages() throws Exception
    {
        this.printEstimatedExecutionTime( "TestMessage", new Runnable()
        {

            public void run()
            {
                getTestMessage( Locale.getDefault(), "arg" );
            }

        } );

    }

    @Test
    public final void testOptionalDependency() throws Exception
    {
        assertNull( this.getOptionalLocale() );
    }

    @Test
    public final void testInvoker() throws Exception
    {
        this.getInvokerTestSpecification().invoke( "TEST" );
    }

    protected void printEstimatedExecutionTime( final String identifier, final Runnable runnable )
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                runnable.run();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * '" + identifier + "': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code ImplementationTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public ImplementationTest()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // <editor-fold defaultstate="collapsed" desc=" Generated Dependencies ">
    /**
     * Gets the {@code <BoundMultitons>} dependency.
     * <p>
     *   This method returns any available object of the {@code <JOMC :: RI :: Tests :: Test Multiton Specification>} specification at specification level 1.2-SNAPSHOT.
     *   That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <BoundMultitons>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification[] getBoundMultitons()
    {
        final org.jomc.ri.test.support.TestSpecification[] _d = (org.jomc.ri.test.support.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "BoundMultitons" );
        assert _d != null : "'BoundMultitons' dependency not found.";
        return _d;
    }
    /**
     * Gets the {@code <BoundSingletons>} dependency.
     * <p>
     *   This method returns any available object of the {@code <JOMC :: RI :: Tests :: Test Singleton Specification>} specification at specification level 1.2-SNAPSHOT.
     *   That specification applies to {@code <Singleton>} scope. The singleton object is returned whenever requested and bound to this instance.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <BoundSingletons>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification[] getBoundSingletons()
    {
        final org.jomc.ri.test.support.TestSpecification[] _d = (org.jomc.ri.test.support.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "BoundSingletons" );
        assert _d != null : "'BoundSingletons' dependency not found.";
        return _d;
    }
    /**
     * Gets the {@code <InvokerTestSpecification>} dependency.
     * <p>
     *   This method returns the {@code <JOMC :: RI :: Tests :: Invoker Test Implementation>} object of the {@code <JOMC :: RI :: Tests :: Invoker Test Specification>} specification at specification level 1.2-SNAPSHOT.
     *   That specification applies to {@code <Singleton>} scope. The singleton object is returned whenever requested and bound to this instance.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <InvokerTestSpecification>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.support.InvokerTestSpecification getInvokerTestSpecification()
    {
        final org.jomc.ri.test.support.InvokerTestSpecification _d = (org.jomc.ri.test.support.InvokerTestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "InvokerTestSpecification" );
        assert _d != null : "'InvokerTestSpecification' dependency not found.";
        return _d;
    }
    /**
     * Gets the {@code <OptionalLocale>} dependency.
     * <p>
     *   This method returns the {@code <DOES NOT EXIST>} object of the {@code <java.util.Locale>} specification at specification level 1.2-SNAPSHOT.
     *   That specification does not apply to any scope. A new object is returned whenever requested.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <OptionalLocale>} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.util.Locale getOptionalLocale()
    {
        return (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "OptionalLocale" );
    }
    /**
     * Gets the {@code <SelectedBoundMultiton>} dependency.
     * <p>
     *   This method returns the {@code <JOMC :: RI :: Tests :: Implementation Test>} object of the {@code <JOMC :: RI :: Tests :: Test Multiton Specification>} specification at specification level 1.2-SNAPSHOT.
     *   That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <SelectedBoundMultiton>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification getSelectedBoundMultiton()
    {
        final org.jomc.ri.test.support.TestSpecification _d = (org.jomc.ri.test.support.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedBoundMultiton" );
        assert _d != null : "'SelectedBoundMultiton' dependency not found.";
        return _d;
    }
    /**
     * Gets the {@code <SelectedBoundSingleton>} dependency.
     * <p>
     *   This method returns the {@code <JOMC :: RI :: Tests :: Implementation Test>} object of the {@code <JOMC :: RI :: Tests :: Test Singleton Specification>} specification at specification level 1.2-SNAPSHOT.
     *   That specification applies to {@code <Singleton>} scope. The singleton object is returned whenever requested and bound to this instance.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <SelectedBoundSingleton>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification getSelectedBoundSingleton()
    {
        final org.jomc.ri.test.support.TestSpecification _d = (org.jomc.ri.test.support.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedBoundSingleton" );
        assert _d != null : "'SelectedBoundSingleton' dependency not found.";
        return _d;
    }
    /**
     * Gets the {@code <SelectedUnboundMultiton>} dependency.
     * <p>
     *   This method returns the {@code <JOMC :: RI :: Tests :: Implementation Test>} object of the {@code <JOMC :: RI :: Tests :: Test Multiton Specification>} specification at specification level 1.2-SNAPSHOT.
     *   That specification does not apply to any scope. A new object is returned whenever requested.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <SelectedUnboundMultiton>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification getSelectedUnboundMultiton()
    {
        final org.jomc.ri.test.support.TestSpecification _d = (org.jomc.ri.test.support.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedUnboundMultiton" );
        assert _d != null : "'SelectedUnboundMultiton' dependency not found.";
        return _d;
    }
    /**
     * Gets the {@code <SelectedUnboundSingleton>} dependency.
     * <p>
     *   This method returns the {@code <JOMC :: RI :: Tests :: Implementation Test>} object of the {@code <JOMC :: RI :: Tests :: Test Singleton Specification>} specification at specification level 1.2-SNAPSHOT.
     *   That specification applies to {@code <Singleton>} scope. The singleton object is returned whenever requested.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <SelectedUnboundSingleton>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification getSelectedUnboundSingleton()
    {
        final org.jomc.ri.test.support.TestSpecification _d = (org.jomc.ri.test.support.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedUnboundSingleton" );
        assert _d != null : "'SelectedUnboundSingleton' dependency not found.";
        return _d;
    }
    /**
     * Gets the {@code <UnboundMultitons>} dependency.
     * <p>
     *   This method returns any available object of the {@code <JOMC :: RI :: Tests :: Test Multiton Specification>} specification at specification level 1.2-SNAPSHOT.
     *   That specification does not apply to any scope. A new object is returned whenever requested.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <UnboundMultitons>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification[] getUnboundMultitons()
    {
        final org.jomc.ri.test.support.TestSpecification[] _d = (org.jomc.ri.test.support.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "UnboundMultitons" );
        assert _d != null : "'UnboundMultitons' dependency not found.";
        return _d;
    }
    /**
     * Gets the {@code <UnboundSingletons>} dependency.
     * <p>
     *   This method returns any available object of the {@code <JOMC :: RI :: Tests :: Test Singleton Specification>} specification at specification level 1.2-SNAPSHOT.
     *   That specification applies to {@code <Singleton>} scope. The singleton object is returned whenever requested.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <UnboundSingletons>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification[] getUnboundSingletons()
    {
        final org.jomc.ri.test.support.TestSpecification[] _d = (org.jomc.ri.test.support.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "UnboundSingletons" );
        assert _d != null : "'UnboundSingletons' dependency not found.";
        return _d;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // <editor-fold defaultstate="collapsed" desc=" Generated Properties ">
    /**
     * Gets the value of the {@code <testProperty>} property.
     * <p><dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl></p>
     * @return The value of the {@code <testProperty>} property.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.lang.String getTestProperty()
    {
        final java.lang.String _p = (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "testProperty" );
        assert _p != null : "'testProperty' property not found.";
        return _p;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Messages]
    // <editor-fold defaultstate="collapsed" desc=" Generated Messages ">
    /**
     * Gets the text of the {@code <testMessage>} message.
     * <p><dl>
     *   <dt><b>Languages:</b></dt>
     *     <dd>English (default)</dd>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl></p>
     * @param locale The locale of the message to return.
     * @param testArgument Format argument.
     * @return The text of the {@code <testMessage>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getTestMessage( final java.util.Locale locale, final java.lang.String testArgument )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "testMessage", locale, testArgument );
        assert _m != null : "'testMessage' message not found.";
        return _m;
    }
    // </editor-fold>
    // SECTION-END
}
