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

// SECTION-START[Implementation Comment]
/**
 * Gets thrown whenever an unexpected error condition is detected.
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
public class SequencesSystemException extends RuntimeException
{
    // SECTION-START[SequencesSystemException]

    /**
     * Creates a new {@code SequencesSystemException} taking a message describing the exception.
     *
     * @param msg The message describing the exception.
     */
    public SequencesSystemException( final String msg )
    {
        super( msg );
    }

    /**
     * Creates a new {@code SequencesSystemException} taking a causing exception.
     *
     * @param e The cause of the exception.
     */
    public SequencesSystemException( final Exception e )
    {
        super( e );
    }

    /**
     * Creates a new {@code SequencesSystemException} taking a message describing the exception a causing exception.
     *
     * @param msg The message describing the exception.
     * @param e The cause of the exception.
     */
    public SequencesSystemException( final String msg, final Exception e )
    {
        super( msg, e );
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public SequencesSystemException()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
