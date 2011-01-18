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
package org.jomc.util.test;

import org.jomc.util.TrailingWhitespaceEditor;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test cases for class {@code org.jomc.util.TrailingWhitespaceEditor}.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $Id$
 */
public class TrailingWhitespaceEditorTest extends LineEditorTest
{

    /** Creates a new {@code TrailingWhitespaceEditorTest} instance. */
    public TrailingWhitespaceEditorTest()
    {
        super();
    }

    /** {@code inheritDoc} */
    @Override
    public TrailingWhitespaceEditor getLineEditor()
    {
        return (TrailingWhitespaceEditor) super.getLineEditor();
    }

    /** {@code inheritDoc} */
    @Override
    protected TrailingWhitespaceEditor newLineEditor()
    {
        return new TrailingWhitespaceEditor();
    }

    @Test
    public final void testTrailingWhitespace() throws Exception
    {
        assertEquals( this.getLineEditor().getLineSeparator(), this.getLineEditor().edit( "\t     " ) );
        assertEquals( this.getLineEditor().getLineSeparator(), this.getLineEditor().edit( "\t     \n" ) );
        assertEquals( "   X" + this.getLineEditor().getLineSeparator(), this.getLineEditor().edit( "   X " ) );
    }

}
