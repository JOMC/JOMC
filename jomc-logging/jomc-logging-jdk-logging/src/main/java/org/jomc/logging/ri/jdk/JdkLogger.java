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
package org.jomc.logging.ri.jdk;

import java.util.logging.Level;

// SECTION-START[Implementation Comment]
/**
 * JDK logging system implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.logging.Logger} {@code 1.0}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
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
public class JdkLogger
    implements
    org.jomc.logging.Logger
{
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public JdkLogger()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[JdkLogger]

    public boolean isDebugEnabled()
    {
        return this.getLogger().isLoggable( Level.FINE );
    }

    public void debug( String message )
    {
        this.log( Level.FINE, message, null );
    }

    public void debug( Throwable throwable )
    {
        this.log( Level.FINE, throwable.getMessage(), throwable );
    }

    public void debug( String message, Throwable throwable )
    {
        this.log( Level.FINE, message, throwable );
    }

    public boolean isErrorEnabled()
    {
        return this.getLogger().isLoggable( Level.SEVERE );
    }

    public void error( String message )
    {
        this.log( Level.SEVERE, message, null );
    }

    public void error( Throwable throwable )
    {
        this.log( Level.SEVERE, throwable.getMessage(), throwable );
    }

    public void error( String message, Throwable throwable )
    {
        this.log( Level.SEVERE, message, throwable );
    }

    public boolean isFatalEnabled()
    {
        return this.getLogger().isLoggable( Level.SEVERE );
    }

    public void fatal( String message )
    {
        this.log( Level.SEVERE, message, null );
    }

    public void fatal( Throwable throwable )
    {
        this.log( Level.SEVERE, throwable.getMessage(), throwable );
    }

    public void fatal( String message, Throwable throwable )
    {
        this.log( Level.SEVERE, message, throwable );
    }

    public boolean isInfoEnabled()
    {
        return this.getLogger().isLoggable( Level.INFO );
    }

    public void info( String message )
    {
        this.log( Level.INFO, message, null );
    }

    public void info( Throwable throwable )
    {
        this.log( Level.INFO, throwable.getMessage(), throwable );
    }

    public void info( String message, Throwable throwable )
    {
        this.log( Level.INFO, message, throwable );
    }

    public boolean isTraceEnabled()
    {
        return this.getLogger().isLoggable( Level.FINEST );
    }

    public void trace( String message )
    {
        this.log( Level.FINEST, message, null );
    }

    public void trace( Throwable throwable )
    {
        this.log( Level.FINEST, throwable.getMessage(), throwable );
    }

    public void trace( String message, Throwable throwable )
    {
        this.log( Level.FINEST, message, throwable );
    }

    public boolean isWarnEnabled()
    {
        return this.getLogger().isLoggable( Level.WARNING );
    }

    public void warn( String message )
    {
        this.log( Level.WARNING, message, null );
    }

    public void warn( Throwable throwable )
    {
        this.log( Level.WARNING, throwable.getMessage(), throwable );
    }

    public void warn( String message, Throwable throwable )
    {
        this.log( Level.WARNING, message, throwable );
    }

    /**
     * Requests the JDK logger for the name given by property {@code name}.
     *
     * @return the JDK logger for the name given by property {@code name}.
     */
    public java.util.logging.Logger getLogger()
    {
        return java.util.logging.Logger.getLogger( this.getName() );
    }

    private void log( final Level level, final String msg, final Throwable t )
    {
        if ( this.getLogger().isLoggable( level ) )
        {
            StackTraceElement caller;
            final Throwable x = new Throwable();
            final StackTraceElement[] elements = x.getStackTrace();

            String cname = "unknown";
            String method = "unknown";

            if ( elements != null && elements.length > 2 )
            {
                caller = elements[2];
                cname = caller.getClassName();
                method = caller.getMethodName();
            }

            if ( t == null )
            {
                this.getLogger().logp( level, cname, method, msg );
            }
            else
            {
                this.getLogger().logp( level, cname, method, msg, t );
            }
        }
    }

    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code name} property.
     * @return Name of the component events are logged for.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public java.lang.String getName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "name" );
    }
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
