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

import org.jomc.ri.DefaultListener;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * {@code DefaultListener} test cases.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>JOMC :: RI :: Tests :: Default Listener Test</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC :: RI :: Tests :: Default Listener Test</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 2.0-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/2.0/jomc-tools-2.0-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class DefaultListenerTest extends ListenerTest
{
    // SECTION-START[DefaultListenerTest]

    /** {@inheritDoc} */
    @Override
    public DefaultListener getListener()
    {
        return (DefaultListener) super.getListener();
    }

    /** {@inheritDoc} */
    @Override
    protected DefaultListener newListener()
    {
        return new DefaultListener();
    }

    @Test
    public final void testDefaultEnabled() throws Exception
    {
        assertTrue( DefaultListener.isDefaultEnabled() );
        System.setProperty( "org.jomc.ri.DefaultListener.defaultEnabled", Boolean.toString( false ) );
        DefaultListener.setDefaultEnabled( null );
        assertFalse( DefaultListener.isDefaultEnabled() );
        System.clearProperty( "org.jomc.ri.DefaultListener.defaultEnabled" );
        DefaultListener.setDefaultEnabled( null );
    }

    @Test
    public final void testEnabled() throws Exception
    {
        DefaultListener.setDefaultEnabled( null );
        this.getListener().setEnabled( null );
        assertTrue( this.getListener().isEnabled() );

        DefaultListener.setDefaultEnabled( false );
        this.getListener().setEnabled( null );
        assertFalse( this.getListener().isEnabled() );

        DefaultListener.setDefaultEnabled( null );
        this.getListener().setEnabled( null );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code DefaultListenerTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/2.0/jomc-tools-2.0-SNAPSHOT" )
    public DefaultListenerTest()
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
