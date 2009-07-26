// SECTION-START[License Header]
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
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
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
public class LoggerTest
{
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
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

    /** Tests the various logger methods to not throw any exceptions. */
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
     * <p>This method returns any available object of the {@code org.jomc.logging.Logger} specification at specification level 1.0.</p>
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
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.logging.Logger getLogger() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.logging.Logger) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "Logger" );
    }
    // SECTION-END
}
