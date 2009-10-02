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
package org.jomc.ri.test;

import java.util.Locale;

// SECTION-START[Documentation]
/**
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.ri.test.TestMultitonSpecification} {@code 1.0-alpha-4-SNAPSHOT} {@code Multiton}</li>
 * <li>{@code org.jomc.ri.test.TestSingletonSpecification} {@code 1.0-alpha-4-SNAPSHOT} {@code Singleton}</li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #getTestProperty testProperty}"
 * <blockquote>Property of type {@code java.lang.String}.
 * </blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getBoundMultitons BoundMultitons}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestMultitonSpecification} at specification level 1.0-alpha-4-SNAPSHOT bound to an instance.</blockquote></li>
 * <li>"{@link #getBoundSingletons BoundSingletons}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestSingletonSpecification} at specification level 1.0-alpha-4-SNAPSHOT bound to an instance.</blockquote></li>
 * <li>"{@link #getSelectedBoundMultiton SelectedBoundMultiton}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestMultitonSpecification} at specification level 1.0-alpha-4-SNAPSHOT bound to an instance.</blockquote></li>
 * <li>"{@link #getSelectedBoundSingleton SelectedBoundSingleton}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestSingletonSpecification} at specification level 1.0-alpha-4-SNAPSHOT bound to an instance.</blockquote></li>
 * <li>"{@link #getSelectedUnboundMultiton SelectedUnboundMultiton}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestMultitonSpecification} at specification level 1.0-alpha-4-SNAPSHOT.</blockquote></li>
 * <li>"{@link #getSelectedUnboundSingleton SelectedUnboundSingleton}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestSingletonSpecification} at specification level 1.0-alpha-4-SNAPSHOT.</blockquote></li>
 * <li>"{@link #getUnboundMultitons UnboundMultitons}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestMultitonSpecification} at specification level 1.0-alpha-4-SNAPSHOT.</blockquote></li>
 * <li>"{@link #getUnboundSingletons UnboundSingletons}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestSingletonSpecification} at specification level 1.0-alpha-4-SNAPSHOT.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getTestMessageMessage testMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Test message with {0} argument.</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
// SECTION-END
public class TestImplementation
    implements
    org.jomc.ri.test.TestSpecification
{
    // SECTION-START[TestMultitonSpecification]
    // SECTION-END
    // SECTION-START[TestSingletonSpecification]
    // SECTION-END
    // SECTION-START[TestImplementation]

    private static final int NUM_REQUESTS = 25000;

    private static final int NUM_RUNS = 5;

    public void testBoundMultitons() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getBoundMultitons();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'BoundMultitons': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    public void testUnboundMultitons() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getUnboundMultitons();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'UnboundMultitons': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    public void testSelectedBoundMultiton() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getSelectedBoundMultiton();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'SelectedBoundMultiton': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    public void testSelectedUnboundMultiton() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getSelectedUnboundMultiton();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'SelectedUnboundMultiton': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    public void testBoundSingletons() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getBoundSingletons();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'BoundSingletons': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    public void testUnboundSingletons() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getUnboundSingletons();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'UnboundSingletons': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    public void testSelectedBoundSingleton() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getSelectedBoundSingleton();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'SelectedBoundSingleton': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    public void testSelectedUnboundSingleton() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getSelectedUnboundSingleton();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'SelectedUnboundSingleton': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    public void testProperties() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getTestProperty();
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'TestProperty': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    public void testMessages() throws Exception
    {
        long t = 0L;

        for ( int i = NUM_RUNS - 1; i >= 0; i-- )
        {
            final long t0 = System.currentTimeMillis();
            final Locale locale = Locale.getDefault();

            for ( long l = NUM_REQUESTS - 1; l >= 0; l-- )
            {
                this.getTestMessageMessage( locale, "arg" );
            }

            t += ( System.currentTimeMillis() - t0 );
        }

        System.out.println( NUM_REQUESTS + " * 'TestMessage': ~" + ( t / NUM_RUNS ) + "ms." );
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Creates a new {@code TestImplementation} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    public TestImplementation()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code BoundMultitons} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.test.TestMultitonSpecification} specification at specification level 1.0-alpha-4-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code BoundMultitons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification[] getBoundMultitons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "BoundMultitons" );
        assert _d != null : "'BoundMultitons' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code BoundSingletons} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.test.TestSingletonSpecification} specification at specification level 1.0-alpha-4-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested and bound to this instance.</p>
     * @return The {@code BoundSingletons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification[] getBoundSingletons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "BoundSingletons" );
        assert _d != null : "'BoundSingletons' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedBoundMultiton} dependency.
     * <p>This method returns the "{@code JOMC RI}" object of the {@code org.jomc.ri.test.TestMultitonSpecification} specification at specification level 1.0-alpha-4-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code SelectedBoundMultiton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification getSelectedBoundMultiton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "SelectedBoundMultiton" );
        assert _d != null : "'SelectedBoundMultiton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedBoundSingleton} dependency.
     * <p>This method returns the "{@code JOMC RI}" object of the {@code org.jomc.ri.test.TestSingletonSpecification} specification at specification level 1.0-alpha-4-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested and bound to this instance.</p>
     * @return The {@code SelectedBoundSingleton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification getSelectedBoundSingleton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "SelectedBoundSingleton" );
        assert _d != null : "'SelectedBoundSingleton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedUnboundMultiton} dependency.
     * <p>This method returns the "{@code JOMC RI}" object of the {@code org.jomc.ri.test.TestMultitonSpecification} specification at specification level 1.0-alpha-4-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code SelectedUnboundMultiton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification getSelectedUnboundMultiton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "SelectedUnboundMultiton" );
        assert _d != null : "'SelectedUnboundMultiton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SelectedUnboundSingleton} dependency.
     * <p>This method returns the "{@code JOMC RI}" object of the {@code org.jomc.ri.test.TestSingletonSpecification} specification at specification level 1.0-alpha-4-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested.</p>
     * @return The {@code SelectedUnboundSingleton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification getSelectedUnboundSingleton()
    {
        final org.jomc.ri.test.TestSpecification _d = (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "SelectedUnboundSingleton" );
        assert _d != null : "'SelectedUnboundSingleton' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code UnboundMultitons} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.test.TestMultitonSpecification} specification at specification level 1.0-alpha-4-SNAPSHOT.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code UnboundMultitons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification[] getUnboundMultitons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "UnboundMultitons" );
        assert _d != null : "'UnboundMultitons' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code UnboundSingletons} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.test.TestSingletonSpecification} specification at specification level 1.0-alpha-4-SNAPSHOT.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested.</p>
     * @return The {@code UnboundSingletons} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification[] getUnboundSingletons()
    {
        final org.jomc.ri.test.TestSpecification[] _d = (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "UnboundSingletons" );
        assert _d != null : "'UnboundSingletons' dependency not found.";
        return _d;
    }
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code testProperty} property.
     * @return The value of the {@code testProperty} property.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private java.lang.String getTestProperty()
    {
        final java.lang.String _p = (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "testProperty" );
        assert _p != null : "'testProperty' property not found.";
        return _p;
    }
    // SECTION-END
    // SECTION-START[Messages]

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
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-4-SNAPSHOT/jomc-tools" )
    private String getTestMessageMessage( final java.util.Locale locale, final java.lang.String testArgument )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "testMessage", locale, new Object[] { testArgument, null } );
        assert _m != null : "'testMessage' message not found.";
        return _m;
    }
    // SECTION-END
}
