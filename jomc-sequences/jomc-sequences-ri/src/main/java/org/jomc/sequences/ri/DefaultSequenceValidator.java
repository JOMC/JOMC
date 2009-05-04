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
package org.jomc.sequences.ri;

import org.jomc.sequences.IllegalSequenceException;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.spi.SequenceValidator;

// SECTION-START[Implementation Comment]
/**
 * SequenceValidator implementation validating sequence instances for use with the reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.sequences.spi.SequenceValidator} {@code 1.0}<blockquote>
 * Object applies to Multiton scope.
 * State does not need to be retained across operations to operate as specified.</blockquote></li>
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
    comments = "See http://www.jomc.org/jomc-tools"
)
// SECTION-END
public class DefaultSequenceValidator implements SequenceValidator
{
    // SECTION-START[SequenceValidator]

    public IllegalSequenceException assertOperationValid( final Sequence oldValue, final Sequence newValue )
    {
        boolean valid = true;
        IllegalSequenceException result = null;

        if ( newValue != null )
        {
            result = new IllegalSequenceException();

            if ( newValue.getName() == null )
            {
                valid = false;
                result.getDetails( Sequence.PROP_NAME ).add( IllegalSequenceException.MANDATORY_PROPERTY_MISSING );
            }
            if ( newValue.getMaximum() < newValue.getMinimum() || newValue.getMinimum() > newValue.getMaximum() )
            {
                valid = false;
                result.getDetails( Sequence.PROP_MINIMUM ).add( IllegalSequenceException.ILLEGAL_PROPERTY_VALUE );
                result.getDetails( Sequence.PROP_MAXIMUM ).add( IllegalSequenceException.ILLEGAL_PROPERTY_VALUE );
            }
            if ( newValue.getValue() > newValue.getMaximum() || newValue.getValue() < newValue.getMinimum() )
            {
                valid = false;
                result.getDetails( Sequence.PROP_VALUE ).add( IllegalSequenceException.ILLEGAL_PROPERTY_VALUE );
            }
            if ( newValue.getIncrement() <= 0L )
            {
                valid = false;
                result.getDetails( Sequence.PROP_INCREMENT ).add( IllegalSequenceException.ILLEGAL_PROPERTY_VALUE );
            }
        }

        return valid ? null : result;
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public DefaultSequenceValidator()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
}
