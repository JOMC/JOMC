// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@schulte.it>
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
// SECTION-END
package org.jomc.sequences.spi;

import org.jomc.sequences.IllegalSequenceException;
import org.jomc.sequences.Sequence;

// SECTION-START[Specification Comment]
/**
 * Validates sequence instances.
 * <p>This specification applies to Multiton scope.
 * An application assembler may provide multiple implementations of this specification (including none). Use of class
 * {@link org.jomc.ObjectManager ObjectManager} is supported for getting these implementations or for selecting a
 * single implementation.<pre>
 * SequenceValidator[] objects = (SequenceValidator[]) ObjectManager.getInstance().getObject( SequenceValidator.class );
 * SequenceValidator object = (SequenceValidator) ObjectManager.getInstance().getObject( SequenceValidator.class, "<i>implementation name</i>" );
 * </pre></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://www.jomc.org/jomc-tools"
)
// SECTION-END
public interface SequenceValidator
{
    // SECTION-START[SequenceValidator]

    /**
     * Gets called whenever the state of a sequence is about to change in a sequence directory.
     *
     * @param oldValue The entity getting changed or {@code null} if {@code newValue} is about to be added to a
     * directory.
     * @param newValue The value {@code oldValue} will be changed to or {@code null} if {@code oldValue} is about to be
     * removed from a directory.
     *
     * @return The result describing the reason for preventing the requested operation or {@code null} if the
     * implementation does not choose to prevent the operation from being performed.
     */
    IllegalSequenceException assertOperationValid( Sequence oldValue, Sequence newValue );

    // SECTION-END
}
