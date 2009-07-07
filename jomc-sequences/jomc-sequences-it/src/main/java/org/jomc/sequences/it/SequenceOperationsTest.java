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

import junit.framework.Assert;
import junit.framework.TestCase;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceLimitException;
import org.jomc.sequences.SequenceNotFoundException;
import org.jomc.sequences.SequenceOperations;
import org.jomc.sequences.SequencesSystemException;

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
public class SequenceOperationsTest extends TestCase
{
    // SECTION-START[SequenceOperationsTest]

    /** Increment value of the test sequence. */
    private static final int TEST_INCREMENT = 10;

    /**
     * Tetst the {@link SequenceOperations#getNextSequenceValue(String)} and
     * {@link SequenceOperations#getNextSequenceValues(String,int)} methods to handle illegal arguments correctly
     * by throwing a {@code SequencesSystemException} with non-null message.
     */
    public void testIllegalArguments() throws Exception
    {
        assert this.getSequenceOperations() != null;

        try
        {
            this.getSequenceOperations().getNextSequenceValue( null );
            throw new AssertionError();
        }
        catch ( SequencesSystemException e )
        {
            System.out.println( e.toString() );
            Assert.assertNotNull( e.getMessage() );
        }

        try
        {
            this.getSequenceOperations().getNextSequenceValues( null, 0 );
            throw new AssertionError();
        }
        catch ( SequencesSystemException e )
        {
            System.out.println( e.toString() );
            Assert.assertNotNull( e.getMessage() );
        }

        try
        {
            this.getSequenceOperations().getNextSequenceValues( "TEST", -1 );
            throw new AssertionError();
        }
        catch ( SequencesSystemException e )
        {
            System.out.println( e.toString() );
            Assert.assertNotNull( e.getMessage() );
        }

    }

    /**
     * Tests that requesting sequence values for unknown sequences is prevented by throwing a corresponding
     * {@code SequenceNotFoundException}.
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
            this.getSequenceOperations().getNextSequenceValues( "UNKNOWN", 100 );
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
     * and {@link SequenceOperations#getNextSequenceValues(java.lang.String, int) } methods to throw a
     * {@code SequenceLimitException} when a maximum value is reached.
     */
    public void testSequenceLimitException() throws Exception
    {
        this.setupTestSequence();

        final long nextValue = this.getSequenceOperations().getNextSequenceValue( this.getClass().getName() );

        Assert.assertEquals( 10, nextValue );

        try
        {
            this.getSequenceOperations().getNextSequenceValue( this.getClass().getName() );
            throw new AssertionError();
        }
        catch ( SequenceLimitException e )
        {
            Assert.assertNotNull( e.getMessage() );
            System.out.println( e.toString() );
        }

        this.setupTestSequence();

        final long[] nextValues = this.getSequenceOperations().getNextSequenceValues( this.getClass().getName(), 1 );
        Assert.assertEquals( 1, nextValues.length );
        Assert.assertEquals( 10, nextValues[0] );

        try
        {
            this.getSequenceOperations().getNextSequenceValues( this.getClass().getName(), 1 );
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

        Sequence s = this.getSequenceDirectory().getSequence( this.getClass().getName() );

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
            this.getSequenceDirectory().editSequence( s.getName(), s.getRevision(), s );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
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

    /**
     * Gets the {@code SequenceOperations} dependency.
     * <p>This method returns any available object of the {@code org.jomc.sequences.SequenceOperations} specification at specification level 1.0.</p>
     * @return The {@code SequenceOperations} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.sequences.SequenceOperations getSequenceOperations() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.sequences.SequenceOperations) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "SequenceOperations" );
    }
    // SECTION-END
}
