// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2009 - 2011 The JOMC Project
 *   Copyright (c) 2005 - 2011 Christian Schulte <schulte2005@users.sourceforge.net>
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

import org.junit.Test;
import java.util.Locale;
import static org.junit.Assert.assertNull;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="4" nowrap><font size="+2">Specifications</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Identifier</b></td>
 *       <td align="left" scope="col" nowrap><b>Class</b></td>
 *       <td align="left" scope="col" nowrap><b>Scope</b></td>
 *       <td align="left" scope="col" nowrap><b>Version</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestMultitonSpecification}</td>
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestSpecification}</td>
 *       <td align="left" nowrap>{@code Multiton}</td>
 *       <td align="left" nowrap>{@code 1.2-SNAPSHOT}</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestScopeSpecification}</td>
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestScopeSpecification}</td>
 *       <td align="left" nowrap>{@code Test}</td>
 *       <td align="left" nowrap>{@code 1.2-SNAPSHOT}</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestSingletonSpecification}</td>
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestSpecification}</td>
 *       <td align="left" nowrap>{@code Singleton}</td>
 *       <td align="left" nowrap>{@code 1.2-SNAPSHOT}</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestSpecificationMany}</td>
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestSpecificationMany}</td>
 *       <td align="left" nowrap>{@code Multiton}</td>
 *       <td align="left" nowrap>{@code 1.2-SNAPSHOT}</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestSpecificationOne}</td>
 *       <td align="left" nowrap>{@code org.jomc.ri.test.TestSpecificationOne}</td>
 *       <td align="left" nowrap>{@code Multiton}</td>
 *       <td align="left" nowrap>{@code 1.2-SNAPSHOT}</td>
 *     </tr>
 *   </table>
 * </p>
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="3" nowrap><font size="+2">Properties</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Name</b></td>
 *       <td align="left" scope="col" nowrap><b>Type</b></td>
 *       <td align="left" scope="col" nowrap><b>Documentation</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getTestProperty testProperty}</td>
 *       <td align="left" valign="top" nowrap>{@code java.lang.String}</td>
 *       <td align="left" valign="top"></td>
 *     </tr>
 *   </table>
 * </p>
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="2" nowrap><font size="+2">Dependencies</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Name</b></td>
 *       <td align="left" scope="col" nowrap><b>Description</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getBoundMultitons BoundMultitons}</td>
 *       <td align="left" valign="top">Dependency on any object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT bound to an instance.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getBoundSingletons BoundSingletons}</td>
 *       <td align="left" valign="top">Dependency on any object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT applying to {@code Singleton} scope bound to an instance.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getInvokerTestSpecification InvokerTestSpecification}</td>
 *       <td align="left" valign="top">Dependency on the {@code 'InvokerTestImplementation'} object of the {@code 'org.jomc.ri.test.InvokerTestSpecification'} {@code (org.jomc.ri.test.InvokerTestSpecification)} specification at specification level 1.2-SNAPSHOT applying to {@code Singleton} scope bound to an instance.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getOptionalLocale OptionalLocale}</td>
 *       <td align="left" valign="top">Dependency on the {@code 'DOES NOT EXIST'} object of the {@code 'java.util.Locale'} {@code (java.util.Locale)} specification at specification level 1.2-SNAPSHOT.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getSelectedBoundMultiton SelectedBoundMultiton}</td>
 *       <td align="left" valign="top">Dependency on the {@code 'ImplementationTest'} object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT bound to an instance.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getSelectedBoundSingleton SelectedBoundSingleton}</td>
 *       <td align="left" valign="top">Dependency on the {@code 'ImplementationTest'} object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT applying to {@code Singleton} scope bound to an instance.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getSelectedUnboundMultiton SelectedUnboundMultiton}</td>
 *       <td align="left" valign="top">Dependency on the {@code 'ImplementationTest'} object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getSelectedUnboundSingleton SelectedUnboundSingleton}</td>
 *       <td align="left" valign="top">Dependency on the {@code 'ImplementationTest'} object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT applying to {@code Singleton} scope.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getUnboundMultitons UnboundMultitons}</td>
 *       <td align="left" valign="top">Dependency on any object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getUnboundSingletons UnboundSingletons}</td>
 *       <td align="left" valign="top">Dependency on any object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT applying to {@code Singleton} scope.</td>
 *     </tr>
 *   </table>
 * </p>
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="3" nowrap><font size="+2">Messages</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Name</b></td>
 *       <td align="left" scope="col" nowrap><b>Languages</b></td>
 *       <td align="left" scope="col" nowrap><b>Default Templates</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" valign="top" nowrap>{@link #getTestMessage testMessage}</td>
 *       <td align="left" valign="top" nowrap>English (default)</td>
 *       <td align="left" valign="top" nowrap><pre><code>Test message with {0} argument.</code></pre></td>
 *     </tr>
 *   </table>
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
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
    org.jomc.ri.test.TestSpecification,
    org.jomc.ri.test.TestSpecificationOne,
    org.jomc.ri.test.TestSpecificationMany,
    org.jomc.ri.test.TestScopeSpecification
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
     * Gets the {@code BoundMultitons} dependency.
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code BoundMultitons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.TestSpecification[] getBoundMultitons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "BoundMultitons" );
        assert _d != null : "'BoundMultitons' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code BoundSingletons} dependency.
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested and bound to this instance.</p>
     * @return The {@code BoundSingletons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.TestSpecification[] getBoundSingletons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "BoundSingletons" );
        assert _d != null : "'BoundSingletons' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code InvokerTestSpecification} dependency.
     * <p>This method returns the {@code 'InvokerTestImplementation'} object of the {@code 'org.jomc.ri.test.InvokerTestSpecification'} {@code (org.jomc.ri.test.InvokerTestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested and bound to this instance.</p>
     * @return The {@code InvokerTestSpecification} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.InvokerTestSpecification getInvokerTestSpecification()
    {
        final org.jomc.ri.test.InvokerTestSpecification _d = (org.jomc.ri.test.InvokerTestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "InvokerTestSpecification" );
        assert _d != null : "'InvokerTestSpecification' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code OptionalLocale} dependency.
     * <p>This method returns the {@code 'DOES NOT EXIST'} object of the {@code 'java.util.Locale'} {@code (java.util.Locale)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code OptionalLocale} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.util.Locale getOptionalLocale()
    {
        return (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "OptionalLocale" );
    }

    /**
     * Gets the {@code SelectedBoundMultiton} dependency.
     * <p>This method returns the {@code 'ImplementationTest'} object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code SelectedBoundMultiton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.TestSpecification getSelectedBoundMultiton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedBoundMultiton" );
        assert _d != null : "'SelectedBoundMultiton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedBoundSingleton} dependency.
     * <p>This method returns the {@code 'ImplementationTest'} object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested and bound to this instance.</p>
     * @return The {@code SelectedBoundSingleton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.TestSpecification getSelectedBoundSingleton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedBoundSingleton" );
        assert _d != null : "'SelectedBoundSingleton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedUnboundMultiton} dependency.
     * <p>This method returns the {@code 'ImplementationTest'} object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code SelectedUnboundMultiton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.TestSpecification getSelectedUnboundMultiton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedUnboundMultiton" );
        assert _d != null : "'SelectedUnboundMultiton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedUnboundSingleton} dependency.
     * <p>This method returns the {@code 'ImplementationTest'} object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested.</p>
     * @return The {@code SelectedUnboundSingleton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.TestSpecification getSelectedUnboundSingleton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedUnboundSingleton" );
        assert _d != null : "'SelectedUnboundSingleton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code UnboundMultitons} dependency.
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code UnboundMultitons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.TestSpecification[] getUnboundMultitons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "UnboundMultitons" );
        assert _d != null : "'UnboundMultitons' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code UnboundSingletons} dependency.
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested.</p>
     * @return The {@code UnboundSingletons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.TestSpecification[] getUnboundSingletons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "UnboundSingletons" );
        assert _d != null : "'UnboundSingletons' dependency not found.";
        return _d;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // <editor-fold defaultstate="collapsed" desc=" Generated Properties ">

    /**
     * Gets the value of the {@code testProperty} property.
     * @return The value of the {@code testProperty} property.
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
     * Gets the text of the {@code testMessage} message.
     * <p><strong>Templates:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Language</b></th>
     *       <th align="left" scope="col" nowrap><b>Template</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>English (default)</td>
     *       <td align="left" valign="top" nowrap><pre><code>Test message with {0} argument.</code></pre></td>
     *     </tr>
     *   </table>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param testArgument Format argument.
     * @return The text of the {@code testMessage} message for {@code locale}.
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
