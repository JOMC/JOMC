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

import org.jomc.ant.ResourceFileProcessorTask;

/**
 * Test cases for class {@code org.jomc.ant.ResourceFileProcessorTask}.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $Id$
 */
public class ResourceFileProcessorTaskTest extends JomcToolTaskTest
{

    /** Creates a new {@code ResourceFileProcessorTaskTest} instance. */
    public ResourceFileProcessorTaskTest()
    {
        super();
    }

    /**
     * Creates a new {@code ResourceFileProcessorTaskTest} instance taking a name.
     *
     * @param name The name of the instance.
     */
    public ResourceFileProcessorTaskTest( final String name )
    {
        super( name );
    }

    /**
     * Gets the {@code ResourceFileProcessorTask} tests are performed with.
     *
     * @return The {@code ResourceFileProcessorTask} tests are performed with.
     *
     * @see #createJomcTask()
     */
    @Override
    public ResourceFileProcessorTask getJomcTask()
    {
        return (ResourceFileProcessorTask) super.getJomcTask();
    }

    /**
     * Creates a new {@code ResourceFileProcessorTask} instance tests are performed with.
     *
     * @return A new {@code ResourceFileProcessorTask} instance tests are performed with.
     *
     * @see #getJomcTask()
     */
    @Override
    protected ResourceFileProcessorTask createJomcTask()
    {
        return new ResourceFileProcessorTask();
    }

    /** {@inheritDoc} */
    @Override
    protected String getBuildFileName()
    {
        return "resource-file-processor-task-test.xml";
    }

}
