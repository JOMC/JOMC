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

import java.math.BigInteger;
import java.util.Set;

// SECTION-START[Specification Comment]
/**
 * Directory of sequences.
 * <p>This specification applies to Singleton scope.
 * An application assembler is required to provide exactly one implementation of this specification. Use of class
 * {@link org.jomc.ObjectManager ObjectManager} is supported for getting that implementation.<pre>
 * SequenceDirectory object = (SequenceDirectory) ObjectManager.getInstance().getObject( SequenceDirectory.class );
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
public interface SequenceDirectory
{
    // SECTION-START[SequenceDirectory]

    /**
     * Gets the total number of sequences stored in the directory.
     *
     * @return The total number of sequences stored in the directory.
     *
     * @throws SequencesSystemException if getting the total number of sequences fails.
     */
    BigInteger getSequenceCount() throws SequencesSystemException;

    /**
     * Gets the capacity limit of the directory.
     *
     * @return The capacity limit of the directory.
     *
     * @throws SequencesSystemException if getting the capacity limit fails.
     */
    BigInteger getCapacityLimit() throws SequencesSystemException;

    /**
     * Gets a sequence for a given name.
     *
     * @param name The name of the sequence to return.
     *
     * @return The sequence with name {@code name} or {@code null} if no sequence matching {@code name} exists in the
     * directory.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws SequencesSystemException if getting the sequence fails.
     */
    Sequence getSequence( String name ) throws NullPointerException, SequencesSystemException;

    /**
     * Adds a sequence to the directory.
     *
     * @param sequence The sequence to add to the directory.
     *
     * @return The data of the sequence from the directory.
     *
     * @throws NullPointerException if {@code sequence} is {@code null}.
     * @throws IllegalSequenceException if {@code sequence} holds illegal values.
     * @throws DuplicateSequenceException if a sequence with the same name already exists.
     * @throws CapacityLimitException if the directory's capacity limit has been reached.
     * @throws SequencesSystemException if adding the sequence fails.
     */
    Sequence addSequence( Sequence sequence )
        throws NullPointerException, IllegalSequenceException, DuplicateSequenceException, CapacityLimitException,
               SequencesSystemException;

    /**
     * Updates a sequence in the directory.
     *
     * @param name The name of the sequence to update.
     * @param revision The revision of the sequence to update.
     * @param sequence The data to update the directory with.
     *
     * @return The data of the sequence from the directory.
     *
     * @throws NullPointerException if {@code name} or {@code sequence} is {@code null}.
     * @throws SequenceNotFoundException if no sequence matching {@code name} exists in the directory.
     * @throws IllegalSequenceException if {@code sequence} holds illegal values.
     * @throws ConcurrentModificationException if the same sequence got concurrently modified in the directory, that is,
     * {@code revision} denotes outdated data.
     * @throws SequencesSystemException if editing the sequence fails.
     */
    Sequence editSequence( String name, long revision, Sequence sequence )
        throws NullPointerException, SequenceNotFoundException, IllegalSequenceException,
               ConcurrentModificationException, SequencesSystemException;

    /**
     * Removes a sequence from the directory.
     *
     * @param name The name of the sequence to remove.
     * @param revision The revision of the sequence to remove.
     *
     * @return The data of the removed sequence from the directory.
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws SequenceNotFoundException if no sequence matching {@code name} exists in the directory.
     * @throws ConcurrentModificationException if the same sequence got concurrently modified in the directory, that is,
     * {@code revision} denotes outdated data.
     * @throws SequencesSystemException if deleting the sequence fails.
     */
    Sequence deleteSequence( String name, long revision )
        throws NullPointerException, SequenceNotFoundException, ConcurrentModificationException,
               SequencesSystemException;

    /**
     * Searches the directory for sequences matching the given arguments.
     *
     * @param name Text to select sequences whose property {@code name} match the given text; {@code null} to ignore
     * property {@code name} in the search.
     *
     * @return All sequences matching the given criteria.
     *
     * @throws IllegalArgumentException if {@code name} cannot be used for searching the directory.
     * @throws SequencesSystemException if seqrching the directory fails.
     */
    Set<Sequence> searchSequences( String name ) throws IllegalArgumentException, SequencesSystemException;

    // SECTION-END
}
