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
package org.jomc.ant.test;

import org.jomc.ant.JomcToolTask;
import static junit.framework.Assert.assertNotNull;

/**
 * Test cases for class {@code org.jomc.ant.JomcToolTask}.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $Id$
 */
public class JomcToolTaskTest extends JomcModelTaskTest
{

    /** Creates a new {@code JomcToolTaskTest} instance. */
    public JomcToolTaskTest()
    {
        super();
    }

    /**
     * Creates a new {@code JomcToolTaskTest} instance taking a name.
     *
     * @param name The name of the instance.
     */
    public JomcToolTaskTest( final String name )
    {
        super( name );
    }

    /**
     * Gets the {@code JomcToolTask} tests are performed with.
     *
     * @return The {@code JomcToolTask} tests are performed with.
     *
     * @see #createJomcTask()
     */
    @Override
    public JomcToolTask getJomcTask()
    {
        return (JomcToolTask) super.getJomcTask();
    }

    public void testConfigureJomcTool() throws Exception
    {
        try
        {
            this.getJomcTask().configureJomcTool( null );
            fail( "Expected 'NullPointerException' not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e );
        }
    }

    public void testGetSpecification() throws Exception
    {
        try
        {
            this.getJomcTask().getSpecification( null );
            fail( "Expected 'NullPointerException' not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e );
        }
    }

    public void testGetImplementation() throws Exception
    {
        try
        {
            this.getJomcTask().getImplementation( null );
            fail( "Expected 'NullPointerException' not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e );
        }
    }

    public void testGetModule() throws Exception
    {
        try
        {
            this.getJomcTask().getModule( null );
            fail( "Expected 'NullPointerException' not thrown." );
        }
        catch ( final NullPointerException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e );
        }
    }

    /**
     * Creates a new {@code JomcToolTask} instance tests are performed with.
     *
     * @return A new {@code JomcToolTask} instance tests are performed with.
     *
     * @see #getJomcTask()
     */
    @Override
    protected JomcToolTask createJomcTask()
    {
        return new JomcToolTask();
    }

    /** {@inheritDoc} */
    @Override
    protected String getBuildFileName()
    {
        return "jomc-tool-task-test.xml";
    }

}