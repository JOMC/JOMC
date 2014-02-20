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
package org.jomc.ri.test.support;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * {@code ObjectManagementLister} logging to the console.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>JOMC ⁑ RI ⁑ Tests ⁑ Test Object Management Listener</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ RI ⁑ Tests ⁑ Test Object Management Listener</dd>
 *   <dt><b>Specifications:</b></dt>
 *     <dd>org.jomc.spi.Listener @ 1.0</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 1.7-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.7-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.7/jomc-tools-1.7-SNAPSHOT" )
// </editor-fold>
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
            this.printWriter = new PrintWriter( this.getOutputStream(), true );
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

    // SECTION-END
    // SECTION-START[Listener]
    public void onLog( final Level level, final String message, final Throwable throwable )
    {
        if ( message != null )
        {
            this.getPrintWriter().print( "[JOMC] " );
            this.getPrintWriter().print( "[" + level.getLocalizedName() + "] " );
            this.getPrintWriter().println( message );
        }
        if ( throwable != null )
        {
            this.getPrintWriter().print( "[JOMC] " );
            this.getPrintWriter().print( "[" + level.getLocalizedName() + "] " );
            this.getPrintWriter().println( throwable );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code TestObjectManagementListener} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.7-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.7/jomc-tools-1.7-SNAPSHOT" )
    public TestObjectManagementListener()
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
