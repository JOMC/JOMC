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
 * Interface to observe operations performed by a system's sequence directory.
 * <p>This specification applies to Multiton scope.
 * An application assembler may provide multiple implementations of this specification (including none). Use of class
 * {@link org.jomc.ObjectManager ObjectManager} is supported for getting these implementations or for selecting a
 * single implementation.<pre>
 * SequenceObserver[] objects = (SequenceObserver[]) ObjectManager.getInstance().getObject( SequenceObserver.class );
 * SequenceObserver object = (SequenceObserver) ObjectManager.getInstance().getObject( SequenceObserver.class, "<i>implementation name</i>" );
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
public interface SequenceObserver
{
    // SECTION-START[SequenceObserver]

    /**
     * Gets called whenever the state of a sequence changed in the sequence
     * directory.
     *
     * @param oldValue The entity having been changed or {@code null} if
     * {@code newValue} got added to the directory.
     * @param newValue The value {@code oldValue} got changed to or {@code null}
     * if {@code oldValue} got removed from the directory.
     */
    void onSequenceChange( Sequence oldValue, Sequence newValue );

    // SECTION-END
}
