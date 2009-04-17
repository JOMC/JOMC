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

import junit.framework.Assert;
import junit.framework.TestCase;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceLimitException;
import org.jomc.sequences.SequenceNotFoundException;
import org.jomc.sequences.SequenceOperations;

// SECTION-START[Implementation Comment]
/**
 * Testcase for {@code SequenceOperations} implementations.
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getSequenceDirectory SequenceDirectory}"<blockquote>
 * Dependency on {@code org.jomc.sequences.SequenceDirectory} at specification level 1.0 applying to Singleton scope bound to an instance.</blockquote></li>
 * <li>"{@link #getSequenceOperations SequenceOperations}"<blockquote>
 * Dependency on {@code org.jomc.sequences.SequenceOperations} at specification level 1.0 applying to Singleton scope bound to an instance.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]

// SECTION-END
public class SequenceOperationsTest extends TestCase
{
    // SECTION-START[SequenceOperationsTest]

    /** Increment value of the test sequence. */
    private static final int TEST_INCREMENT = 10;

    /**
     * Tetst the {@link SequenceOperations#getNextSequenceValue(String)} and
     * {@link SequenceOperations#getNextSequenceValues(String,int)} methods to
     * handle {@code null} arguments correctly by throwing a corresponding
     * {@code NullPointerException}.
     */
    public void testNullArguments() throws Exception
    {
        assert this.getSequenceOperations() != null;

        try
        {
            this.getSequenceOperations().getNextSequenceValue( null );
            throw new AssertionError();
        }
        catch ( NullPointerException e )
        {
            System.out.println( e.toString() );
            Assert.assertNotNull( e.getMessage() );
        }

        try
        {
            this.getSequenceOperations().getNextSequenceValues( null, 0 );
            throw new AssertionError();
        }
        catch ( NullPointerException e )
        {
            System.out.println( e.toString() );
            Assert.assertNotNull( e.getMessage() );
        }

        try
        {
            this.getSequenceOperations().getNextSequenceValues( "TEST", -1 );
            throw new AssertionError();
        }
        catch ( IllegalArgumentException e )
        {
            System.out.println( e.toString() );
            Assert.assertNotNull( e.getMessage() );
        }

    }

    /**
     * Tests that requesting sequence values for unknown sequences is prevented
     * by throwing a corresponding {@code SequenceNotFoundException}.
     */
    public void testSequenceNotFoundException() throws Exception
    {
        assert this.getSequenceOperations() != null;

        try
        {
            this.getSequenceOperations().getNextSequenceValue( "UNKNOWN" );
            throw new AssertionError();
        }
        catch ( SequenceNotFoundException e )
        {
            System.out.println( e.toString() );
            Assert.assertNotNull( e.getMessage() );
        }

        try
        {
            this.getSequenceOperations().
                getNextSequenceValues( "UNKNOWN", 100 );

            throw new AssertionError();
        }
        catch ( SequenceNotFoundException e )
        {
            System.out.println( e.toString() );
            Assert.assertNotNull( e.getMessage() );
        }
    }

    /**
     * Tests the
     * {@link SequenceOperations#getNextSequenceValue(java.lang.String) }
     * and {@link SequenceOperations#getNextSequenceValues(java.lang.String, int) }
     * methods to throw a {@code SequenceLimitException} when a maximum value is
     * reached.
     */
    public void testSequenceLimitException() throws Exception
    {
        this.setupTestSequence();

        final long nextValue = this.getSequenceOperations().
            getNextSequenceValue( this.getClass().getName() );

        Assert.assertEquals( 10, nextValue );

        try
        {
            this.getSequenceOperations().
                getNextSequenceValue( this.getClass().getName() );

            throw new AssertionError();
        }
        catch ( SequenceLimitException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        this.setupTestSequence();

        final long[] nextValues = this.getSequenceOperations().
            getNextSequenceValues( this.getClass().getName(), 1 );

        Assert.assertEquals( 1, nextValues.length );
        Assert.assertEquals( 10, nextValues[0] );

        try
        {
            this.getSequenceOperations().
                getNextSequenceValues( this.getClass().getName(), 1 );

        }
        catch ( SequenceLimitException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }
    }

    private void setupTestSequence() throws Exception
    {
        assert this.getSequenceDirectory() != null;

        Sequence s = this.getSequenceDirectory().getSequence(
            this.getClass().getName() );

        if ( s == null )
        {
            s = new Sequence();
            s.setIncrement( TEST_INCREMENT );
            s.setMaximum( TEST_INCREMENT );
            s.setMinimum( 0 );
            s.setName( this.getClass().getName() );
            s.setValue( 0 );

            this.getSequenceDirectory().addSequence( s );
        }
        else
        {
            s.setValue( 0 );
            this.getSequenceDirectory().editSequence(
                s.getName(), s.getRevision(), s );

        }
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public SequenceOperationsTest()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code SequenceDirectory} dependency.
     * </p>
     * @return The {@code SequenceDirectory} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private org.jomc.sequences.SequenceDirectory getSequenceDirectory() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.sequences.SequenceDirectory) org.jomc.ObjectManager.getInstance().getDependency( this, "SequenceDirectory" );
    }

    /**
     * Gets the {@code SequenceOperations} dependency.
     * </p>
     * @return The {@code SequenceOperations} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    private org.jomc.sequences.SequenceOperations getSequenceOperations() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.sequences.SequenceOperations) org.jomc.ObjectManager.getInstance().getDependency( this, "SequenceOperations" );
    }
    // SECTION-END
}
