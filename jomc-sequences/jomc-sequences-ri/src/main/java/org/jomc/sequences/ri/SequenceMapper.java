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

import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequencesSystemException;
import org.jomc.sequences.model.SequenceType;

// SECTION-START[Specification Comment]
/**
 * Maps model classes.
 * <p>This specification applies to Singleton scope.
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public interface SequenceMapper
{
    // SECTION-START[SequenceMapper]

    /**
     * Maps an implementation model instance to a {@code Sequence} instance.
     *
     * @param source The instance to map.
     * @param target The target instance to map {@code source} to.
     *
     * @return {@code source} mapped to {@code target}.
     *
     * @throws NullPointerException if {@code source} or {@code target} is {@code null}.
     * @throws SequencesSystemException if mapping fails unexpectedly.
     */
    Sequence map( SequenceType source, Sequence target ) throws NullPointerException, SequencesSystemException;

    /**
     * Maps a {@code Sequence} instance to an implementation model instance.
     *
     * @param source The instance to map.
     * @param target The target instance to map {@code source} to.
     *
     * @return {@code source} mapped to {@code target}.
     *
     * @throws NullPointerException if {@code source} or {@code target} is {@code null}.
     * @throws SequencesSystemException if mapping fails unexpectedly.
     */
    SequenceType map( Sequence source, SequenceType target ) throws NullPointerException, SequencesSystemException;

    // SECTION-END
}
