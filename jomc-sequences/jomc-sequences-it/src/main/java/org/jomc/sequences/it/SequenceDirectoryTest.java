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
package org.jomc.sequences.it;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.jomc.sequences.ConcurrentModificationException;
import org.jomc.sequences.DuplicateSequenceException;
import org.jomc.sequences.SequenceVetoException;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceDirectory;
import org.jomc.sequences.SequenceNotFoundException;
import org.jomc.sequences.SequencesSystemException;

// SECTION-START[Implementation Comment]
/**
 * Testcase for {@code SequenceDirectory} implementations.
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getSequenceDirectory SequenceDirectory}"<blockquote>
 * Dependency on {@code org.jomc.sequences.SequenceDirectory} at specification level 1.0 applying to Singleton scope bound to an instance.</blockquote></li>
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
public class SequenceDirectoryTest extends TestCase
{
    // SECTION-START[SequenceDirectoryTest]

    /**
     * Gets a sequence with valid data.
     *
     * @return A sequence with valid data.
     */
    public static Sequence getTestSequence()
    {
        final Sequence legal = new Sequence();
        legal.setIncrement( 1L );
        legal.setMaximum( 10L );
        legal.setMinimum( 0L );
        legal.setName( "TEST" );
        legal.setValue( 0L );

        return legal;
    }

    /**
     * Tests all {@link SequenceDirectory} methods to handle illegal arguments correctly by throwing a
     * {@code SequencesSystemException} with non-null message.
     */
    public void testIllegalArguments() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        try
        {
            this.getSequenceDirectory().addSequence( null );
            throw new AssertionError();
        }
        catch ( SequencesSystemException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().deleteSequence( null, 0L );
            throw new AssertionError();
        }
        catch ( SequencesSystemException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().editSequence( null, 0L, null );
            throw new AssertionError();
        }
        catch ( SequencesSystemException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().editSequence( "TEST", 0L, null );
            throw new AssertionError();
        }
        catch ( SequencesSystemException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().editSequence( null, 0L, new Sequence() );
            throw new AssertionError();
        }
        catch ( SequencesSystemException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().getSequence( null );
            throw new AssertionError();
        }
        catch ( SequencesSystemException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

    }

    /**
     * Tests that a valid sequence can get added, edited, searched and removed.
     */
    public void testAddEditSearchDeleteLegalSequence() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        Sequence legal = getTestSequence();

        legal = this.getSequenceDirectory().addSequence( legal );

        System.out.println( legal );

        legal.setName( "TEST2" );

        legal = this.getSequenceDirectory().editSequence( "TEST", legal.getRevision(), legal );

        System.out.println( legal );

        Assert.assertEquals( legal, this.getSequenceDirectory().getSequence( "TEST2" ) );

        final Set<Sequence> result = this.getSequenceDirectory().searchSequences( "TEST" );

        Assert.assertEquals( 1, result.size() );
        Assert.assertEquals( legal, result.toArray()[0] );

        this.getSequenceDirectory().deleteSequence( "TEST2", legal.getRevision() );

        Assert.assertEquals( 0, this.getSequenceDirectory().searchSequences( null ).size() );
        Assert.assertEquals( BigInteger.ZERO, this.getSequenceDirectory().getSequenceCount() );
    }

    /**
     * Tests that a sequence cannot get edited or removed when it got changed concurrently.
     */
    public void testConcurrentModificationException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        final Sequence legal = getTestSequence();

        Sequence sequence = this.getSequenceDirectory().addSequence( legal );

        try
        {
            this.getSequenceDirectory().editSequence( "TEST", sequence.getRevision() + 1L, sequence );
            throw new AssertionError();
        }
        catch ( ConcurrentModificationException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().deleteSequence( "TEST", sequence.getRevision() + 1L );
            throw new AssertionError();
        }
        catch ( ConcurrentModificationException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }
    }

    /**
     * Tests that adding an illegal sequence or updating an existing sequence with illegal data is prevented by throwing
     * a corresponding {@code SequenceVetoException}.
     */
    public void testSequenceVetoException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        Sequence legal = getTestSequence();

        final Sequence illegal = new Sequence();
        illegal.setName( "TEST" );
        illegal.setMinimum( 100L );
        illegal.setMaximum( 1L );
        illegal.setValue( 0L );
        illegal.setIncrement( -1L );

        try
        {
            this.getSequenceDirectory().addSequence( illegal );
            throw new AssertionError();
        }
        catch ( SequenceVetoException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        legal = this.getSequenceDirectory().addSequence( legal );

        try
        {
            this.getSequenceDirectory().editSequence( legal.getName(), legal.getRevision(), illegal );
            throw new AssertionError();
        }
        catch ( SequenceVetoException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        this.getSequenceDirectory().deleteSequence( legal.getName(), legal.getRevision() );
    }

    /**
     * Tests that adding a sequence twice is prevented by throwing a corresponding {@code DuplicateSequenceException}.
     */
    public void testDuplicateSequenceException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        Sequence legal = getTestSequence();

        legal = this.getSequenceDirectory().addSequence( legal );

        try
        {
            this.getSequenceDirectory().addSequence( legal );
            throw new AssertionError();
        }
        catch ( DuplicateSequenceException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }
    }

    /**
     * Tests that updating or deleting an unknown sequence is prevented by throwing a corresponding
     * {@code SequenceNotFoundException}.
     */
    public void testSequenceNotFoundException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        final Sequence legal = getTestSequence();

        try
        {
            this.getSequenceDirectory().editSequence( "UNKNOWN", 0L, legal );
            throw new AssertionError();
        }
        catch ( SequenceNotFoundException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().deleteSequence( "UNKNOWN", 0L );
            throw new AssertionError();
        }
        catch ( SequenceNotFoundException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        this.clearDirectory();
    }

    /**
     * Tests that adding, editing and then removing multiple sequences leaves an empty directory.
     */
    public void testAddEditDeleteMany() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        final int count = 15;
        final List<Sequence> added = new ArrayList<Sequence>( count );
        final List<Sequence> updated = new ArrayList<Sequence>( count );
        for ( int i = 0; i < count; i++ )
        {
            final Sequence legal = getTestSequence();
            legal.setName( legal.getName() + ' ' + i );
            final Sequence a = this.getSequenceDirectory().addSequence( legal );
            added.add( a );
            System.out.println( "ADD: " + a );
        }

        for ( Sequence s : added )
        {
            final String oldName = s.getName();
            s.setName( oldName + "_UPDATED" );
            final Sequence u = this.getSequenceDirectory().editSequence(
                oldName, s.getRevision(), s );

            updated.add( u );
            System.out.println( "EDIT: " + u );
        }

        for ( Sequence s : updated )
        {
            final Sequence d = this.getSequenceDirectory().deleteSequence(
                s.getName(), s.getRevision() );

            System.out.println( "DELETE: " + d );
        }

        Assert.assertEquals( BigInteger.ZERO, this.getSequenceDirectory().getSequenceCount() );
    }

    /** Removes all sequences from the directory. */
    protected void clearDirectory() throws Exception
    {
        // Remove any test entries.
        for ( Sequence sequence : this.getSequenceDirectory().searchSequences( null ) )
        {
            System.out.println( this.getSequenceDirectory().deleteSequence(
                sequence.getName(), sequence.getRevision() ) );

        }

        Assert.assertEquals( BigInteger.ZERO, this.getSequenceDirectory().getSequenceCount() );
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public SequenceDirectoryTest()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code SequenceDirectory} dependency.
     * <p>This method returns any available object of the {@code org.jomc.sequences.SequenceDirectory} specification at specification level 1.0.</p>
     * @return The {@code SequenceDirectory} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.sequences.SequenceDirectory getSequenceDirectory() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.sequences.SequenceDirectory) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "SequenceDirectory" );
    }
    // SECTION-END
}
