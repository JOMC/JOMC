// SECTION-START[License Header]
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
// SECTION-END
package org.jomc.sequences.ri;

import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceChangeEvent;
import org.jomc.sequences.SequenceVetoException;

// SECTION-START[Implementation Comment]
/**
 * {@code VetoableSequenceChangeListener} reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.sequences.VetoableSequenceChangeListener} {@code 1.0}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
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
public class DefaultVetoableSequenceChangeListener
    implements
    org.jomc.sequences.VetoableSequenceChangeListener
{
    // SECTION-START[VetoableSequenceChangeListener]

    public void vetoableSequenceChange( final SequenceChangeEvent evt ) throws SequenceVetoException
    {
        boolean valid = true;

        if ( evt.getNewSequence() != null )
        {
            if ( evt.getNewSequence().getName() == null )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_NAME ).add( SequenceChangeEvent.MANDATORY_VALUE );
            }
            if ( evt.getNewSequence().getMaximum() < evt.getNewSequence().getMinimum() ||
                 evt.getNewSequence().getMinimum() > evt.getNewSequence().getMaximum() )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_MINIMUM ).add( SequenceChangeEvent.ILLEGAL_VALUE );
                evt.getStatus( Sequence.PROP_MAXIMUM ).add( SequenceChangeEvent.ILLEGAL_VALUE );
            }
            if ( evt.getNewSequence().getValue() > evt.getNewSequence().getMaximum() ||
                 evt.getNewSequence().getValue() < evt.getNewSequence().getMinimum() )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_VALUE ).add( SequenceChangeEvent.ILLEGAL_VALUE );
            }
            if ( evt.getNewSequence().getIncrement() <= 0L )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_INCREMENT ).add( SequenceChangeEvent.ILLEGAL_VALUE );
            }
        }

        if ( !valid )
        {
            throw new SequenceVetoException( evt );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public DefaultVetoableSequenceChangeListener()
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
