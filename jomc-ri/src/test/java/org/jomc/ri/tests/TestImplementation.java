// SECTION-START[License Header]
/*
 *  JOMC RI Tests
 *  Copyright (C) 2005 Christian Schulte <cs@schulte.it>
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 *
 *  $Id$
 */
// SECTION-END
package org.jomc.ri.tests;

import java.util.Locale;

// SECTION-START[Implementation Comment]
/**
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.ri.tests.SingletonSpecification} {@code 1.0-alpha-1-SNAPSHOT}<blockquote>
 * Object applies to Singleton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * <li>{@code org.jomc.ri.tests.MultitonSpecification} {@code 1.0-alpha-1-SNAPSHOT}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * <li>{@code org.jomc.ri.tests.TestSpecification} {@code 1.0-alpha-1-SNAPSHOT}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #getTestProperty testProperty}"<blockquote>
 * Property of type {@code java.lang.String} with value "TEST".</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getTestBoundSingleton TestBoundSingleton}"<blockquote>
 * Dependency on {@code org.jomc.ri.tests.SingletonSpecification} at specification level 1.0-alpha-1-SNAPSHOT applying to Singleton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getTestSingleton TestSingleton}"<blockquote>
 * Dependency on {@code org.jomc.ri.tests.SingletonSpecification} at specification level 1.0-alpha-1-SNAPSHOT applying to Singleton scope.</blockquote></li>
 * <li>"{@link #getTestMultiton TestMultiton}"<blockquote>
 * Dependency on {@code org.jomc.ri.tests.MultitonSpecification} at specification level 1.0-alpha-1-SNAPSHOT applying to Multiton scope.</blockquote></li>
 * <li>"{@link #getTestSpecification TestSpecification}"<blockquote>
 * Dependency on {@code org.jomc.ri.tests.TestSpecification} at specification level 1.0-alpha-1-SNAPSHOT applying to Multiton scope.</blockquote></li>
 * <li>"{@link #getSelectedTestSpecification SelectedTestSpecification}"<blockquote>
 * Dependency on {@code org.jomc.ri.tests.TestSpecification} at specification level 1.0-alpha-1-SNAPSHOT applying to Multiton scope.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getTestMessageMessage testMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Test message with {0} argument.</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://jomc.sourceforge.net/jomc-tools"
)
// SECTION-END
public class TestImplementation
implements
    org.jomc.ri.tests.SingletonSpecification,
    org.jomc.ri.tests.MultitonSpecification,
    org.jomc.ri.tests.TestSpecification
{
    // SECTION-START[TestImplementation]

    public void testDependencies() throws Exception
    {
        final long numRequests = 1000L;
        final long time = System.currentTimeMillis();
        for ( long l = numRequests - 1; l >= 0; l-- )
        {
            this.getTestSingleton();
        }
        final long singleton = System.currentTimeMillis();
        for ( long l = numRequests - 1; l >= 0; l-- )
        {
            this.getTestBoundSingleton();
        }
        final long bound = System.currentTimeMillis();
        for ( long l = numRequests - 1; l >= 0; l-- )
        {
            this.getTestMultiton();
        }
        final long multiton = System.currentTimeMillis();
        for ( long l = numRequests - 1; l >= 0; l-- )
        {
            this.getTestSpecification();
        }
        final long many = System.currentTimeMillis();
        for ( long l = numRequests - 1; l >= 0; l-- )
        {
            this.getSelectedTestSpecification();
        }
        final long selected = System.currentTimeMillis();

        System.out.println( numRequests + " singleton dependency requests: " +
                            ( singleton - time ) + "ms." );

        System.out.println( numRequests + " bound dependency requests: " +
                            ( bound - singleton ) + "ms." );

        System.out.println( numRequests + " multiton dependency requests: " +
                            ( multiton - bound ) + "ms." );

        System.out.println( numRequests +
                            " multiton dependency requests (many): " +
                            ( many - multiton ) + "ms." );

        System.out.println( numRequests +
                            " multiton dependency requests (select): " +
                            ( selected - many ) + "ms." );

    }

    public void testProperties() throws Exception
    {
        final long numRequests = 1000L;
        final long time = System.currentTimeMillis();
        for ( long l = numRequests - 1; l >= 0; l-- )
        {
            this.getTestProperty();
        }
        final long properties = System.currentTimeMillis();
        System.out.println( numRequests + " property requests: " +
                            ( properties - time ) + "ms." );

    }

    public void testMessages() throws Exception
    {
        final long numRequests = 1000L;
        final long time = System.currentTimeMillis();
        for ( long l = numRequests - 1; l >= 0; l-- )
        {
            this.getTestMessageMessage( Locale.getDefault(), "arg" );
        }

        final long properties = System.currentTimeMillis();
        System.out.println( numRequests + " message requests: " +
                            ( properties - time ) + "ms." );

    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public TestImplementation()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code SelectedTestSpecification} dependency.
     * <p>This method returns the "{@code JOMC RI}" object of the {@code org.jomc.ri.tests.TestSpecification} specification at specification level 1.0-alpha-1-SNAPSHOT.</p>
     * @return The {@code SelectedTestSpecification} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.ri.tests.TestSpecification getSelectedTestSpecification() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.ri.tests.TestSpecification) org.jomc.ObjectManager.getInstance().getDependency( this, "SelectedTestSpecification" );
    }

    /**
     * Gets the {@code TestBoundSingleton} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.tests.SingletonSpecification} specification at specification level 1.0-alpha-1-SNAPSHOT.</p>
     * @return The {@code TestBoundSingleton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.ri.tests.SingletonSpecification getTestBoundSingleton() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.ri.tests.SingletonSpecification) org.jomc.ObjectManager.getInstance().getDependency( this, "TestBoundSingleton" );
    }

    /**
     * Gets the {@code TestMultiton} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.tests.MultitonSpecification} specification at specification level 1.0-alpha-1-SNAPSHOT.</p>
     * @return The {@code TestMultiton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.ri.tests.MultitonSpecification getTestMultiton() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.ri.tests.MultitonSpecification) org.jomc.ObjectManager.getInstance().getDependency( this, "TestMultiton" );
    }

    /**
     * Gets the {@code TestSingleton} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.tests.SingletonSpecification} specification at specification level 1.0-alpha-1-SNAPSHOT.</p>
     * @return The {@code TestSingleton} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.ri.tests.SingletonSpecification getTestSingleton() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.ri.tests.SingletonSpecification) org.jomc.ObjectManager.getInstance().getDependency( this, "TestSingleton" );
    }

    /**
     * Gets the {@code TestSpecification} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.tests.TestSpecification} specification at specification level 1.0-alpha-1-SNAPSHOT.</p>
     * @return The {@code TestSpecification} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.ri.tests.TestSpecification[] getTestSpecification() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.ri.tests.TestSpecification[]) org.jomc.ObjectManager.getInstance().getDependency( this, "TestSpecification" );
    }
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code testProperty} property.
     * @return The value of the {@code testProperty} property.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.String getTestProperty() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "testProperty" );
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
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private String getTestMessageMessage( final java.util.Locale locale, final java.lang.String testArgument ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManager.getInstance().getMessage( this, "testMessage", locale, new Object[] { testArgument, null } );
    }
    // SECTION-END
}
