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
package org.jomc.spi;

import java.util.Map;

// SECTION-START[Specification Comment]
/**
 * Scope a specification applies to.
 * <p>This specification applies to Multiton scope.
 * An application assembler may provide multiple implementations of this specification (including none).
 * Use of class {@link org.jomc.ObjectManager ObjectManager} is supported for getting these implementations or for
 * selecting a single implementation.<pre>
 * Scope[] objects = (Scope[]) ObjectManagerFactory.getObjectManager().getObject( Scope.class );
 * Scope object = ObjectManagerFactory.getObjectManager().getObject( Scope.class, "<i>implementation name</i>" );
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
    comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
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
