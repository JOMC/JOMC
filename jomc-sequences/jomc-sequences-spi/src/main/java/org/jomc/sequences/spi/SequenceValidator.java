// SECTION-START[License Header]
/*
 *  JOMC Sequences SPI
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
    comments = "See http://jomc.sourceforge.net/jomc-tools"
)
// SECTION-END
public interface SequenceValidator
{
    // SECTION-START[SequenceValidator]

    /**
     * Gets called whenever the state of a sequence is about to change in a sequence directory.
     *
     * @param oldValue The entity getting changed or {@code null} if {@code newValue} is about to be added to the
     * directory.
     * @param newValue The value {@code oldValue} will be changed to or {@code null} if {@code oldValue} is about to be
     * removed from the directory.
     *
     * @return The result describing the reason for preventing the requested operation or {@code null} if the
     * implementation does not choose to prevent the operation from being performed.
     */
    IllegalSequenceException assertOperationValid( Sequence oldValue, Sequence newValue );

    // SECTION-END
}
