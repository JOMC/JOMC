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

import java.util.Locale;
import static junit.framework.Assert.assertNull;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * <p><b>Specifications</b><ul>
 * <li>{@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} {@code 1.0-beta-5-SNAPSHOT} {@code Multiton}</li>
 * <li>{@code 'org.jomc.ri.test.TestScopeSpecification'} {@code (org.jomc.ri.test.TestScopeSpecification)} {@code 1.0-beta-5-SNAPSHOT} {@code Test}</li>
 * <li>{@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} {@code 1.0-beta-5-SNAPSHOT} {@code Singleton}</li>
 * <li>{@code 'org.jomc.ri.test.TestSpecificationMany'} {@code (org.jomc.ri.test.TestSpecificationMany)} {@code 1.0-beta-5-SNAPSHOT} {@code Multiton}</li>
 * <li>{@code 'org.jomc.ri.test.TestSpecificationOne'} {@code (org.jomc.ri.test.TestSpecificationOne)} {@code 1.0-beta-5-SNAPSHOT} {@code Multiton}</li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #getTestProperty testProperty}"
 * <blockquote>Property of type {@code java.lang.String}.
 * </blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getBoundMultitons BoundMultitons}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.0-beta-5-SNAPSHOT bound to an instance.</blockquote></li>
 * <li>"{@link #getBoundSingletons BoundSingletons}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.0-beta-5-SNAPSHOT bound to an instance.</blockquote></li>
 * <li>"{@link #getInvokerTestSpecification InvokerTestSpecification}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.InvokerTestSpecification'} {@code (org.jomc.ri.test.InvokerTestSpecification)} at specification level 1.0-beta-5-SNAPSHOT bound to an instance.</blockquote></li>
 * <li>"{@link #getOptionalLocale OptionalLocale}"<blockquote>
 * Dependency on {@code 'java.util.Locale'} {@code (java.util.Locale)} at specification level 1.0-beta-5-SNAPSHOT.</blockquote></li>
 * <li>"{@link #getSelectedBoundMultiton SelectedBoundMultiton}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.0-beta-5-SNAPSHOT bound to an instance.</blockquote></li>
 * <li>"{@link #getSelectedBoundSingleton SelectedBoundSingleton}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.0-beta-5-SNAPSHOT bound to an instance.</blockquote></li>
 * <li>"{@link #getSelectedUnboundMultiton SelectedUnboundMultiton}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.0-beta-5-SNAPSHOT.</blockquote></li>
 * <li>"{@link #getSelectedUnboundSingleton SelectedUnboundSingleton}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.0-beta-5-SNAPSHOT.</blockquote></li>
 * <li>"{@link #getUnboundMultitons UnboundMultitons}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.0-beta-5-SNAPSHOT.</blockquote></li>
 * <li>"{@link #getUnboundSingletons UnboundSingletons}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.0-beta-5-SNAPSHOT.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getTestMessage testMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Test message with {0} argument.</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
// </editor-fold>
// SECTION-END
public class TestImplementation
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
    // SECTION-START[TestImplementation]

    private static final int NUM_REQUESTS = 25000;

    private static final int NUM_RUNS = 5;

    public void testBoundMultitons() throws Exception
    {
        this.printEstimatedExecutionTime( "BoundMultitons", new Runnable()
        {

            public void run()
            {
                getBoundMultitons();
            }

        } );

    }

    public void testUnboundMultitons() throws Exception
    {
        this.printEstimatedExecutionTime( "UnboundMultitons", new Runnable()
        {

            public void run()
            {
                getUnboundMultitons();
            }

        } );

    }

    public void testSelectedBoundMultiton() throws Exception
    {
        this.printEstimatedExecutionTime( "SelectedBoundMultiton", new Runnable()
        {

            public void run()
            {
                getSelectedBoundMultiton();
            }

        } );

    }

    public void testSelectedUnboundMultiton() throws Exception
    {
        this.printEstimatedExecutionTime( "SelectedUnboundMultiton", new Runnable()
        {

            public void run()
            {
                getSelectedUnboundMultiton();
            }

        } );

    }

    public void testBoundSingletons() throws Exception
    {
        this.printEstimatedExecutionTime( "BoundSingletons", new Runnable()
        {

            public void run()
            {
                getBoundSingletons();
            }

        } );

    }

    public void testUnboundSingletons() throws Exception
    {
        this.printEstimatedExecutionTime( "UnboundSingletons", new Runnable()
        {

            public void run()
            {
                getUnboundSingletons();
            }

        } );

    }

    public void testSelectedBoundSingleton() throws Exception
    {
        this.printEstimatedExecutionTime( "SelectedBoundSingleton", new Runnable()
        {

            public void run()
            {
                getSelectedBoundSingleton();
            }

        } );

    }

    public void testSelectedUnboundSingleton() throws Exception
    {
        this.printEstimatedExecutionTime( "SelectedUnboundSingleton", new Runnable()
        {

            public void run()
            {
                getSelectedUnboundSingleton();
            }

        } );

    }

    public void testProperties() throws Exception
    {
        this.printEstimatedExecutionTime( "TestProperty", new Runnable()
        {

            public void run()
            {
                getTestProperty();
            }

        } );

    }

    public void testMessages() throws Exception
    {
        this.printEstimatedExecutionTime( "TestMessage", new Runnable()
        {

            public void run()
            {
                getTestMessage( Locale.getDefault(), "arg" );
            }

        } );

    }

    public void testOptionalDependency() throws Exception
    {
        assertNull( this.getOptionalLocale() );
    }

    public void testInvoker() throws Exception
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

    /** Creates a new {@code TestImplementation} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    public TestImplementation()
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
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code BoundMultitons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification[] getBoundMultitons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "BoundMultitons" );
        assert _d != null : "'BoundMultitons' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code BoundSingletons} dependency.
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested and bound to this instance.</p>
     * @return The {@code BoundSingletons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification[] getBoundSingletons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "BoundSingletons" );
        assert _d != null : "'BoundSingletons' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code InvokerTestSpecification} dependency.
     * <p>This method returns the {@code 'InvokerTest'} object of the {@code 'org.jomc.ri.test.InvokerTestSpecification'} {@code (org.jomc.ri.test.InvokerTestSpecification)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested and bound to this instance.</p>
     * @return The {@code InvokerTestSpecification} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.InvokerTestSpecification getInvokerTestSpecification()
    {
        final org.jomc.ri.test.InvokerTestSpecification _d = (org.jomc.ri.test.InvokerTestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "InvokerTestSpecification" );
        assert _d != null : "'InvokerTestSpecification' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code OptionalLocale} dependency.
     * <p>This method returns the {@code 'DOES NOT EXIST'} object of the {@code 'java.util.Locale'} {@code (java.util.Locale)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code OptionalLocale} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private java.util.Locale getOptionalLocale()
    {
        return (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "OptionalLocale" );
    }

    /**
     * Gets the {@code SelectedBoundMultiton} dependency.
     * <p>This method returns the {@code 'TestImplementation'} object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code SelectedBoundMultiton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification getSelectedBoundMultiton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedBoundMultiton" );
        assert _d != null : "'SelectedBoundMultiton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedBoundSingleton} dependency.
     * <p>This method returns the {@code 'TestImplementation'} object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested and bound to this instance.</p>
     * @return The {@code SelectedBoundSingleton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification getSelectedBoundSingleton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedBoundSingleton" );
        assert _d != null : "'SelectedBoundSingleton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedUnboundMultiton} dependency.
     * <p>This method returns the {@code 'TestImplementation'} object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code SelectedUnboundMultiton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification getSelectedUnboundMultiton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedUnboundMultiton" );
        assert _d != null : "'SelectedUnboundMultiton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedUnboundSingleton} dependency.
     * <p>This method returns the {@code 'TestImplementation'} object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested.</p>
     * @return The {@code SelectedUnboundSingleton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification getSelectedUnboundSingleton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectedUnboundSingleton" );
        assert _d != null : "'SelectedUnboundSingleton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code UnboundMultitons} dependency.
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestMultitonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code UnboundMultitons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification[] getUnboundMultitons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "UnboundMultitons" );
        assert _d != null : "'UnboundMultitons' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code UnboundSingletons} dependency.
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestSingletonSpecification'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.0-beta-5-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested.</p>
     * @return The {@code UnboundSingletons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
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
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
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
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Test message with {0} argument.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param testArgument Format argument.
     * @return The text of the {@code testMessage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private String getTestMessage( final java.util.Locale locale, final java.lang.String testArgument )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "testMessage", locale, testArgument );
        assert _m != null : "'testMessage' message not found.";
        return _m;
    }
    // </editor-fold>
    // SECTION-END
}
