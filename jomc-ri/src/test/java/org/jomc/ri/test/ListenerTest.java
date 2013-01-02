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
package org.jomc.ri.test;

import java.util.logging.Level;
import org.jomc.ri.DefaultListener;
import org.jomc.spi.Listener;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * {@code Listener} test cases.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>JOMC ⁑ RI ⁑ Tests ⁑ Listener Test</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ RI ⁑ Tests ⁑ Listener Test</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 2.0-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://www.jomc.org/jomc/2.0/jomc-tools-2.0-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class ListenerTest
{
    // SECTION-START[ListenerTest]

    /** The {@code Listener} instance tests are performed with. */
    private Listener listener;

    /**
     * Gets the {@code Listener} instance tests are performed with.
     *
     * @return The {@code Listener} instance tests are performed with.
     *
     * @see #newListener()
     */
    public Listener getListener()
    {
        if ( this.listener == null )
        {
            this.listener = this.newListener();
        }

        return this.listener;
    }

    /**
     * Creates a new {@code Listener} instance to test.
     *
     * @return A new {@code Listener} instance to test.
     *
     * @see #getListener()
     */
    protected Listener newListener()
    {
        return new DefaultListener();
    }

    @Test
    public final void testLog() throws Exception
    {
        try
        {
            this.getListener().onLog( null, null, null );
            fail( "Expected NullPointerException not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e );
        }

        this.getListener().onLog( Level.INFO, "TEST", new Exception() );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code ListenerTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://www.jomc.org/jomc/2.0/jomc-tools-2.0-SNAPSHOT" )
    public ListenerTest()
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
