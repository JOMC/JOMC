// SECTION-START[License Header]
/*
 *  JOMC Logging API
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
package org.jomc.logging;

// SECTION-START[Specification Comment]
/**
 * Logs events for a specific component.
 * <p>This specification applies to Multiton scope.
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

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
