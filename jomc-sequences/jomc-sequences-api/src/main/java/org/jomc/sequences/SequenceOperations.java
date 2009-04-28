// SECTION-START[License Header]
/*
 *  JOMC Sequences API
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
package org.jomc.sequences;

// SECTION-START[Specification Comment]
/**
 * Provides operations performed with sequences.
 * <p>This specification applies to Singleton scope.
 * An application assembler is required to provide exactly one implementation of this specification. Use of class
 * {@link org.jomc.ObjectManager ObjectManager} is supported for getting that implementation.<pre>
 * SequenceOperations object = (SequenceOperations) ObjectManager.getInstance().getObject( SequenceOperations.class );
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
public interface SequenceOperations
{
    // SECTION-START[SequenceOperations]

    /**
     * Gets the next value for a named sequence.
     *
     * @param sequenceName The name of the sequence to get the next value of.
     *
     * @return The next value of the sequence with name {@code name}.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws SequenceNotFoundException if no sequence exists for {@code sequenceName}.
     * @throws SequenceLimitException if the sequence with name {@code sequenceName} reached its maximum value.
     * @throws SequencesSystemException if getting the value fails.
     */
    long getNextSequenceValue( String sequenceName )
        throws NullPointerException, SequenceNotFoundException, SequenceLimitException, SequencesSystemException;

    /**
     * Gets multiple next values for a named sequence.
     *
     * @param sequenceName the name of the sequence to get values of.
     * @param numValues the number of values to get from the sequence with name {@code sequenceName}.
     *
     * @return An array of next values of the sequence with name {@code name} with a length equal to {@code numValues}.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code numValues} is negative.
     * @throws SequenceNotFoundException if no sequence exists for {@code sequenceName}.
     * @throws SequenceLimitException if the sequence with name {@code sequenceName} reached its maximum value.
     * @throws SequencesSystemException if getting values fails.
     */
    long[] getNextSequenceValues( String sequenceName, int numValues )
        throws NullPointerException, IllegalArgumentException, SequenceNotFoundException, SequenceLimitException,
               SequencesSystemException;

    // SECTION-END
}
