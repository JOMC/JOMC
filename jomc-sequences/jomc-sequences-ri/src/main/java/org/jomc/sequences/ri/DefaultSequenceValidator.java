// SECTION-START[License Header]
/*
 *  JOMC Sequences RI
 *  Copyright (C) 2005 Christian Schulte <cs@schulte.it>
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 *
 *  $Id$
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
 * State does not need to be retained across operations to operate as
 * specified.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class DefaultSequenceValidator implements SequenceValidator
{
    // SECTION-START[SequenceValidator]

    public IllegalSequenceException assertOperationValid(
        final Sequence oldValue, final Sequence newValue )
    {
        boolean valid = true;
        IllegalSequenceException result = null;

        if ( newValue != null )
        {
            result = new IllegalSequenceException();

            if ( newValue.getName() == null )
            {
                valid = false;
                result.getDetails( Sequence.PROP_NAME ).
                    add( IllegalSequenceException.MANDATORY_PROPERTY_MISSING );

            }
            if ( newValue.getMaximum() < newValue.getMinimum() ||
                 newValue.getMinimum() > newValue.getMaximum() )
            {
                valid = false;
                result.getDetails( Sequence.PROP_MINIMUM ).
                    add( IllegalSequenceException.ILLEGAL_PROPERTY_VALUE );

                result.getDetails( Sequence.PROP_MAXIMUM ).
                    add( IllegalSequenceException.ILLEGAL_PROPERTY_VALUE );

            }
            if ( newValue.getValue() > newValue.getMaximum() ||
                 newValue.getValue() < newValue.getMinimum() )
            {
                valid = false;
                result.getDetails( Sequence.PROP_VALUE ).
                    add( IllegalSequenceException.ILLEGAL_PROPERTY_VALUE );

            }
            if ( newValue.getIncrement() <= 0L )
            {
                valid = false;
                result.getDetails( Sequence.PROP_INCREMENT ).
                    add( IllegalSequenceException.ILLEGAL_PROPERTY_VALUE );

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
