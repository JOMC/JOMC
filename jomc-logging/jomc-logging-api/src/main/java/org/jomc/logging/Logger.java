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
package org.jomc.logging;

// SECTION-START[Specification Comment]
/**
 * Logs events for a specific component.
 * <p>This specification applies to Multiton scope.
 * An application assembler is required to provide no more than one implementation of this specification (including none).
 * Use of class {@link org.jomc.ObjectManager ObjectManager} is supported for getting that implementation.<pre>
 * Logger object = (Logger) ObjectManagerFactory.getObjectManager().getObject( Logger.class );
 * </pre></p>
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
public interface Logger
{

    /**
     * Getter for property {@code debugEnabled}.
     *
     * @return {@code true} if logging debug messages is enabled; {@code false}
     * if logging debug messages is disabled.
     */
    boolean isDebugEnabled();

    /**
     * Logs a message at log level {@code debug}.
     *
     * @param message The message to log.
     */
    void debug( String message );

    /**
     * Logs an exception at log level {@code debug}.
     *
     * @param t The exception to log.
     */
    void debug( Throwable t );

    /**
     * Logs a message and an exception at log level {@code debug}.
     *
     * @param message The message to log.
     * @param t The exception to log.
     */
    void debug( String message, Throwable t );

    /**
     * Getter for property {@code errorEnabled}.
     *
     * @return {@code true} if logging error messages is enabled; {@code false}
     * if logging error messages is disabled.
     */
    boolean isErrorEnabled();

    /**
     * Logs a message at log level {@code error}.
     *
     * @param message The message to log.
     */
    void error( String message );

    /**
     * Logs an exception at log level {@code error}.
     *
     * @param t The exception to log.
     */
    void error( Throwable t );

    /**
     * Logs a message and an exception at log level {@code error}.
     *
     * @param message The message to log.
     * @param t The exception to log.
     */
    void error( String message, Throwable t );

    /**
     * Getter for property {@code fatalEnabled}.
     *
     * @return {@code true} if logging fatal messages is enabled; {@code false}
     * if logging fatal messages is disabled.
     */
    boolean isFatalEnabled();

    /**
     * Logs a message at log level {@code fatal}.
     *
     * @param message The message to log.
     */
    void fatal( String message );

    /**
     * Logs an exception at log level {@code fatal}.
     *
     * @param t The exception to log.
     */
    void fatal( Throwable t );

    /**
     * Logs a message and an exception at log level {@code fatal}.
     *
     * @param message The message to log.
     * @param t The exception to log.
     */
    void fatal( String message, Throwable t );

    /**
     * Getter for property {@code infoEnabled}.
     *
     * @return {@code true} if logging info messages is enabled; {@code false}
     * if logging info messages is disabled.
     */
    boolean isInfoEnabled();

    /**
     * Logs a message at log level {@code info}.
     *
     * @param message The message to log.
     */
    void info( String message );

    /**
     * Logs an exception at log level {@code info}.
     *
     * @param t The exception to log.
     */
    void info( Throwable t );

    /**
     * Logs a message and an exception at log level {@code info}.
     *
     * @param message The message to log.
     * @param t The exception to log.
     */
    void info( String message, Throwable t );

    /**
     * Getter for property {@code traceEnabled}.
     *
     * @return {@code true} if logging trace messages is enabled; {@code false}
     * if logging trace messages is disabled.
     */
    boolean isTraceEnabled();

    /**
     * Logs a message at log level {@code trace}.
     *
     * @param message The message to log.
     */
    void trace( String message );

    /**
     * Logs an exception at log level {@code trace}.
     *
     * @param t The exception to log.
     */
    void trace( Throwable t );

    /**
     * Logs a message and an exception at log level {@code trace}.
     *
     * @param message The message to log.
     * @param t The exception to log.
     */
    void trace( String message, Throwable t );

    /**
     * Getter for property {@code warnEnabled}.
     *
     * @return {@code true} if logging warning messages is enabled;
     * {@code false} if logging warning messages is disabled.
     */
    boolean isWarnEnabled();

    /**
     * Logs a message at log level {@code warn}.
     *
     * @param message The message to log.
     */
    void warn( String message );

    /**
     * Logs an exception at log level {@code warn}.
     *
     * @param t The exception to log.
     */
    void warn( Throwable t );

    /**
     * Logs a message and an exception at log level {@code warn}.
     *
     * @param message The message to log.
     * @param t The exception to log.
     */
    void warn( String message, Throwable t );

}
