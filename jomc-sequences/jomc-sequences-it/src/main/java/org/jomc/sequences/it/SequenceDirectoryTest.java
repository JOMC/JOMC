// SECTION-START[License Header]
/*
 *  JOMC Sequences Test Suite
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
package org.jomc.sequences.it;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.jomc.sequences.ConcurrentModificationException;
import org.jomc.sequences.DuplicateSequenceException;
import org.jomc.sequences.IllegalSequenceException;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceDirectory;
import org.jomc.sequences.SequenceNotFoundException;

// SECTION-START[Implementation Comment]
/**
 * Testcase for {@code SequenceDirectory} implementations.
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getSequenceDirectory SequenceDirectory}"<blockquote>
 * Dependency on {@code org.jomc.sequences.SequenceDirectory} at specification level 1.0 applying to Singleton scope bound to an instance.</blockquote></li>
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
    static Sequence getLegalSequence()
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
     * Tests all {@link SequenceDirectory} methods to handle null arguments
     * correctly by throwing a corresponding {@code NullPointerException}.
     */
    public void testNullArguments() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        try
        {
            this.getSequenceDirectory().addSequence( null );
            throw new AssertionError();
        }
        catch ( NullPointerException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().deleteSequence( null, 0L );
            throw new AssertionError();
        }
        catch ( NullPointerException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().editSequence( null, 0L, null );
            throw new AssertionError();
        }
        catch ( NullPointerException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().editSequence( "TEST", 0L, null );
            throw new AssertionError();
        }
        catch ( NullPointerException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().editSequence( null, 0L, new Sequence() );
            throw new AssertionError();
        }
        catch ( NullPointerException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().getSequence( null );
            throw new AssertionError();
        }
        catch ( NullPointerException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

    }

    /**
     * Tests that a valid sequence can be added, edited, searched and removed.
     */
    public void testAddEditSearchDeleteLegalSequence() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        Sequence legal = getLegalSequence();

        legal = this.getSequenceDirectory().addSequence( legal );

        System.out.println( legal );

        legal.setName( "TEST2" );

        legal = this.getSequenceDirectory().editSequence(
            "TEST", legal.getRevision(), legal );

        System.out.println( legal );

        Assert.assertEquals( legal, this.getSequenceDirectory().
            getSequence( "TEST2" ) );

        final Set<Sequence> result = this.getSequenceDirectory().
            searchSequences( "TEST" );

        Assert.assertEquals( 1, result.size() );
        Assert.assertEquals( legal, result.toArray()[0] );

        this.getSequenceDirectory().deleteSequence( "TEST2",
                                                    legal.getRevision() );

        Assert.assertEquals( 0, this.getSequenceDirectory().
            searchSequences( null ).size() );

        Assert.assertEquals( BigInteger.ZERO, this.getSequenceDirectory().
            getSequenceCount() );

    }

    /**
     * Tests that a sequence cannot be edited or removed when it got
     * changed concurrently.
     */
    public void testConcurrentModificationException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        final Sequence legal = getLegalSequence();

        Sequence sequence = this.getSequenceDirectory().addSequence( legal );

        try
        {
            this.getSequenceDirectory().editSequence(
                "TEST", sequence.getRevision() + 1L, sequence );

            throw new AssertionError();
        }
        catch ( ConcurrentModificationException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        try
        {
            this.getSequenceDirectory().deleteSequence(
                "TEST", sequence.getRevision() + 1L );

            throw new AssertionError();
        }
        catch ( ConcurrentModificationException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }
    }

    /**
     * Tests that adding an illegal sequence or updating an existing sequence
     * with illegal data is prevented by throwing a corresponding
     * {@code IllegalSequenceException}.
     */
    public void testIllegalSequenceException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        Sequence legal = getLegalSequence();

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
        catch ( IllegalSequenceException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        legal = this.getSequenceDirectory().addSequence( legal );

        try
        {
            this.getSequenceDirectory().editSequence(
                legal.getName(), legal.getRevision(), illegal );

            throw new AssertionError();
        }
        catch ( IllegalSequenceException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        this.getSequenceDirectory().deleteSequence(
            legal.getName(), legal.getRevision() );

    }

    /**
     * Tests that adding a sequence twice is prevented by throwing a
     * corresponding {@code DuplicateSequenceException}.
     */
    public void testDuplicateSequenceException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        Sequence legal = getLegalSequence();

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
     * Tests that updating or deleting an unknown sequence is prevented by
     * throwing a corresponding {@code SequenceNotFoundException}.
     */
    public void testSequenceNotFoundException() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        this.clearDirectory();

        final Sequence legal = getLegalSequence();

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
     * Tests that adding, editing and removing multiple sequences leaves
     * an empty directory.
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
            final Sequence legal = getLegalSequence();
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

        Assert.assertEquals( BigInteger.ZERO,
                             this.getSequenceDirectory().getSequenceCount() );

    }

    /** Removes all sequences from the directory. */
    protected void clearDirectory() throws Exception
    {
        // Remove any test entries.
        for ( Sequence sequence : this.getSequenceDirectory().
            searchSequences( null ) )
        {
            System.out.println( this.getSequenceDirectory().deleteSequence(
                sequence.getName(), sequence.getRevision() ) );

        }

        Assert.assertEquals( BigInteger.ZERO, this.getSequenceDirectory().
            getSequenceCount() );

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
        return (org.jomc.sequences.SequenceDirectory) org.jomc.ObjectManager.getInstance().getDependency( this, "SequenceDirectory" );
    }
    // SECTION-END
}
