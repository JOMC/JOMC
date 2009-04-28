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

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;

// SECTION-START[Implementation Comment]
/**
 * {@code ObjectManagementLister} logging to the console.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.spi.Listener} {@code 1.0}<blockquote>
 * Object applies to Singleton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
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
public class TestObjectManagementListener
    implements
    org.jomc.spi.Listener
{
    // SECTION-START[TestObjectManagementListener]

    /** The {@code OutputStream} to stream to. */
    private OutputStream outputStream;

    /** The {@code PrintWriter} events are printed with. */
    private PrintWriter printWriter;

    /**
     * Gets the output stream events are streamed to.
     *
     * @return The output stream events are streamed to.
     */
    public OutputStream getOutputStream()
    {
        if ( this.outputStream == null )
        {
            this.outputStream = System.out;
        }

        return this.outputStream;
    }

    /**
     * Gets the print writer events are printed with,
     *
     * @return The print writer events are printed with.
     */
    public PrintWriter getPrintWriter()
    {
        if ( this.printWriter == null )
        {
            this.printWriter = new PrintWriter( this.getOutputStream() );
        }

        return this.printWriter;
    }

    /**
     * Sets the print writer to print events with,
     *
     * @param value The new print writer to print events with,
     */
    public void setPrintWriter( final PrintWriter value )
    {
        this.printWriter = value;
    }

    /**
     * Sets the output stream to stream events to.
     *
     * @param value The new output stream to stream events to.
     */
    public void setOutputStream( final OutputStream value )
    {
        this.outputStream = value;
    }

    public void onLog( final Level level, final String message, final Throwable throwable )
    {
        this.getPrintWriter().print( "[JOMC] " );
        this.getPrintWriter().print( "[" + level.toString() + "] " );

        if ( message != null )
        {
            this.getPrintWriter().println( message );
        }

        if ( throwable != null )
        {
            throwable.printStackTrace( this.getPrintWriter() );
        }

        this.getPrintWriter().flush();
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public TestObjectManagementListener()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
