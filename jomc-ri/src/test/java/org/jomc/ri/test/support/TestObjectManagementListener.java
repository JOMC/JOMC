// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) 2009 - 2011 The JOMC Project
 *   Copyright (C) 2005 - 2011 Christian Schulte <schulte2005@users.sourceforge.net>
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
 * <p>
 *   This implementation is identified by identifier {@code <JOMC :: RI :: Tests :: Test Object Management Listener>}.
 *   It provides objects named {@code <JOMC RI>} of the following specifications:
 *
 *   <ul>
 *     <li>{@code <org.jomc.spi.Listener>} at specification level 1.0.</li>
 *   </ul>
 *
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 1.2-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
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
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
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