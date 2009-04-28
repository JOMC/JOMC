// SECTION-START[License Header]
/*
 *  JOMC SPI
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
package org.jomc.spi;

import java.util.Map;

// SECTION-START[Specification Comment]
/**
 * Scope a specification applies to.
 * <p>This specification applies to Multiton scope.
 * An application assembler may provide multiple implementations of this specification (including none). Use of class
 * {@link org.jomc.ObjectManager ObjectManager} is supported for getting these implementations or for selecting a
 * single implementation.<pre>
 * Scope[] objects = (Scope[]) ObjectManager.getInstance().getObject( Scope.class );
 * Scope object = (Scope) ObjectManager.getInstance().getObject( Scope.class, "<i>implementation name</i>" );
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
public interface Scope
{
    // SECTION-START[Scope]

    /**
     * Gets the name of the scope.
     *
     * @return The name of the scope.
     */
    String getName();

    /**
     * Gets the objects of the scope.
     *
     * @return The object of the scope.
     */
    Map<String, Object> getObjects();

    /**
     * Gets an object from the scope.
     *
     * @param instance The identifier of the object to get from the scope.
     *
     * @return The object identified by {@code instance} or {@code null} if no object exists in the scope.
     */
    Object getObject( String instance );

    /**
     * Puts an object into the scope.
     *
     * @param instance The identifier of the object to put into the scope.
     * @param object The object to put into the scope.
     *
     * @return The previous object from the scope or {@code null} if there was no object in the scope.
     */
    Object putObject( String instance, Object object );

    /**
     * Removes an object from the scope.
     *
     * @param instance The identifier of the object to remove from the scope.
     *
     * @return The removed object or {@code null} if there was no object in the scope.
     */
    Object removeObject( String instance );

    // SECTION-END
}
