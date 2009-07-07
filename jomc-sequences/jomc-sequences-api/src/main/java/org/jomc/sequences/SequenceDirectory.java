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
package org.jomc.sequences;

import java.math.BigInteger;
import java.util.Set;

// SECTION-START[Specification Comment]
/**
 * Directory of sequences.
 * <p>This specification applies to Singleton scope.
 * An application assembler is required to provide no more than one implementation of this specification (including none).
 * Use of class {@link org.jomc.ObjectManager ObjectManager} is supported for getting that implementation.<pre>
 * SequenceDirectory object = (SequenceDirectory) ObjectManagerFactory.getObjectManager().getObject( SequenceDirectory.class );
 * </pre></p>
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
     * @throws SequencesSystemException if getting the sequence fails.
     */
    Sequence getSequence( String name ) throws SequencesSystemException;

    /**
     * Adds a sequence to the directory.
     *
     * @param sequence The sequence to add to the directory.
     *
     * @return The data of the sequence from the directory.
     *
     * @throws SequenceVetoException if {@code sequence} holds illegal values.
     * @throws DuplicateSequenceException if a sequence with the same name already exists.
     * @throws CapacityLimitException if the directory's capacity limit has been reached.
     * @throws SequencesSystemException if adding the sequence fails.
     */
    Sequence addSequence( Sequence sequence )
        throws SequenceVetoException, DuplicateSequenceException, CapacityLimitException, SequencesSystemException;

    /**
     * Updates a sequence in the directory.
     *
     * @param name The name of the sequence to update.
     * @param revision The revision of the sequence to update.
     * @param sequence The data to update the directory with.
     *
     * @return The data of the sequence from the directory.
     *
     * @throws SequenceVetoException if {@code sequence} holds illegal values.
     * @throws SequenceNotFoundException if no sequence matching {@code name} exists in the directory.
     * @throws ConcurrentModificationException if the same sequence got concurrently modified in the directory, that is,
     * {@code revision} denotes outdated data.
     * @throws SequencesSystemException if editing the sequence fails.
     */
    Sequence editSequence( String name, long revision, Sequence sequence )
        throws SequenceVetoException, SequenceNotFoundException, ConcurrentModificationException,
               SequencesSystemException;

    /**
     * Removes a sequence from the directory.
     *
     * @param name The name of the sequence to remove.
     * @param revision The revision of the sequence to remove.
     *
     * @return The data of the removed sequence from the directory.
     *
     * @throws SequenceNotFoundException if no sequence matching {@code name} exists in the directory.
     * @throws ConcurrentModificationException if the same sequence got concurrently modified in the directory, that is,
     * {@code revision} denotes outdated data.
     * @throws SequencesSystemException if deleting the sequence fails.
     */
    Sequence deleteSequence( String name, long revision )
        throws SequenceNotFoundException, ConcurrentModificationException, SequencesSystemException;

    /**
     * Searches the directory for sequences matching the given arguments.
     *
     * @param name Text to select sequences whose property {@code name} match the given text; {@code null} to ignore
     * property {@code name} in the search.
     *
     * @return All sequences matching the given criteria.
     *
     * @throws SequencesSystemException if seqrching the directory fails.
     */
    Set<Sequence> searchSequences( String name ) throws SequencesSystemException;

    // SECTION-END
}
