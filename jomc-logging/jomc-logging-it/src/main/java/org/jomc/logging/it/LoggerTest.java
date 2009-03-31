// SECTION-START[License Header]
/*
 *  JOMC Logging Test Suite
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
package org.jomc.logging.it;

import org.jomc.logging.Logger;

// SECTION-START[Implementation Comment]
/**
 * Logging system test suite.
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLogger Logger}"<blockquote>
 * Dependency on {@code org.jomc.logging.Logger} at specification level 1.0 applying to Multiton scope bound to an instance.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class LoggerTest
{
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public LoggerTest()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[LoggerTest]
    /**
     * Tests the {@link Logger#isInfoEnabled() isXxxEnabled()} methods to not
     * throw any exceptions.
     */
    public void testIsEnabled() throws Exception
    {
        assert this.getLogger() != null;

        this.getLogger().isDebugEnabled();
        this.getLogger().isErrorEnabled();
        this.getLogger().isFatalEnabled();
        this.getLogger().isInfoEnabled();
        this.getLogger().isTraceEnabled();
        this.getLogger().isWarnEnabled();
    }

    /** Test the various logger methods to not throw any exceptions. */
    public void testLog() throws Exception
    {
        assert this.getLogger() != null;

        this.getLogger().debug( "TEST" );
        this.getLogger().debug( new Exception() );
        this.getLogger().debug( "TEST", new Exception() );

        this.getLogger().error( "TEST" );
        this.getLogger().error( new Exception() );
        this.getLogger().error( "TEST", new Exception() );

        this.getLogger().fatal( "TEST" );
        this.getLogger().fatal( new Exception() );
        this.getLogger().fatal( "TEST", new Exception() );

        this.getLogger().info( "TEST" );
        this.getLogger().info( new Exception() );
        this.getLogger().info( "TEST", new Exception() );

        this.getLogger().trace( "TEST" );
        this.getLogger().trace( new Exception() );
        this.getLogger().trace( "TEST", new Exception() );

        this.getLogger().warn( "TEST" );
        this.getLogger().warn( new Exception() );
        this.getLogger().warn( "TEST", new Exception() );
    }

    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code Logger} dependency.
     * </p>
     * <p><b>Properties</b><dl>
     * <dt>"{@code name}"</dt>
     * <dd>Property of type {@code java.lang.String} with value "org.jomc.logging.it.LoggerTest".
     * </dd>
     * </dl>
     * @return Implementation tests are performed with.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private org.jomc.logging.Logger getLogger() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.logging.Logger) org.jomc.ObjectManager.getInstance().getDependency( this, "Logger" );
    }
    // SECTION-END
}
