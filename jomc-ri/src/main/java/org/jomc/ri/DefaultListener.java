// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Java Object Management and Configuration
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
package org.jomc.ri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import org.jomc.spi.Listener;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Default {@code Listener} implementation.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.DefaultListener</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ RI ⁑ DefaultListener</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.1
 * @version 1.1
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class DefaultListener implements Listener
{
    // SECTION-START[DefaultListener]

    /**
     * Constant holding the platforms line separator.
     */
    private static final String LINE_SEPARATOR = System.getProperty( "line.separator", "\n" );

    /**
     * Flag indicating the listener is enabled by default.
     */
    private static volatile Boolean defaultEnabled;

    /**
     * Flag indicating the listener is enabled.
     */
    private Boolean enabled;

    /**
     * Gets a flag indicating the listener is enabled by default.
     * <p>
     * The default enabled flag is controlled by system property
     * {@code org.jomc.ri.DefaultListener.defaultEnabled} holding a value indicating the listener is enabled by default.
     * If that property is not set, the {@code true} default is returned.
     * </p>
     *
     * @return {@code true}, if the listener is enabled by default; {@code false}, if the listener is disabled by
     * default.
     *
     * @see #setDefaultEnabled(java.lang.Boolean)
     */
    public static boolean isDefaultEnabled()
    {
        if ( defaultEnabled == null )
        {
            defaultEnabled = Boolean.valueOf( System.getProperty(
                "org.jomc.ri.DefaultListener.defaultEnabled", Boolean.toString( true ) ) );

        }

        return defaultEnabled;
    }

    /**
     * Sets the flag indicating the listener is enabled by default.
     *
     * @param value The new value of the flag indicating the listener is enabled by default or {@code null}.
     *
     * @see #isDefaultEnabled()
     */
    public static void setDefaultEnabled( final Boolean value )
    {
        defaultEnabled = value;
    }

    /**
     * Gets a flag indicating the listener is enabled.
     *
     * @return {@code true}, if the listener is enabled; {@code false}, if the listener is disabled.
     *
     * @see #isDefaultEnabled()
     * @see #setEnabled(java.lang.Boolean)
     */
    public final boolean isEnabled()
    {
        if ( this.enabled == null )
        {
            this.enabled = isDefaultEnabled();
        }

        return this.enabled;
    }

    /**
     * Sets the flag indicating the listener is enabled.
     *
     * @param value The new value of the flag indicating the listener is enabled or {@code null}.
     *
     * @see #isEnabled()
     */
    public final void setEnabled( final Boolean value )
    {
        this.enabled = value;
    }

    /**
     * Gets called on logging.
     * <p>
     * This method prints messages to the "standard" system streams. Messages with a level greater than
     * {@code WARNING} are printed to the system error stream. All other messages are printed to the system output
     * stream.
     * </p>
     *
     * @param level The level of the event.
     * @param message The message of the event or {@code null}.
     * @param throwable The throwable of the event or {@code null}.
     *
     * @throws NullPointerException if {@code level} is {@code null}.
     *
     * @see #isEnabled()
     */
    public void onLog( final Level level, final String message, final Throwable throwable )
    {
        if ( level == null )
        {
            throw new NullPointerException( "level" );
        }

        if ( this.isEnabled() )
        {
            // JDK: As of JDK 6, "System.console().writer()".
            final PrintStream out = level.intValue() > Level.WARNING.intValue() ? System.err : System.out;

            try
            {
                if ( message != null )
                {
                    out.print( this.splitLines( level, message ) );
                    out.flush();
                }

                if ( throwable != null )
                {
                    final StringWriter stringWriter = new StringWriter();
                    final PrintWriter printWriter = new PrintWriter( stringWriter );
                    throwable.printStackTrace( printWriter );
                    printWriter.close();
                    out.print( this.splitLines( level, stringWriter.toString() ) );
                    out.flush();
                }
            }
            catch ( final IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    private StringBuilder splitLines( final Level level, final String message ) throws IOException
    {
        BufferedReader reader = null;
        boolean suppressExceptionOnClose = true;

        try
        {
            final String linePrefix = "[" + level.getLocalizedName() + "] ";
            final StringBuilder b = new StringBuilder( message.length() );
            reader = new BufferedReader( new StringReader( message ) );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                b.append( linePrefix ).append( line ).append( LINE_SEPARATOR );
            }

            suppressExceptionOnClose = false;
            return b;
        }
        finally
        {
            try
            {
                if ( reader != null )
                {
                    reader.close();
                }
            }
            catch ( final IOException e )
            {
                if ( !suppressExceptionOnClose )
                {
                    throw e;
                }
            }
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code DefaultListener} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
    public DefaultListener()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END

}
