// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 * Java Object Management and Configuration
 * Copyright (C) Christian Schulte <cs@schulte.it>, 2005-206
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   o Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   o Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $JOMC$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.spi;

import java.util.Map;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Scope a specification applies to.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.spi.Scope</dd>
 *   <dt><b>Multiplicity:</b></dt><dd>Many</dd>
 *   <dt><b>Scope:</b></dt><dd>None</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 1.0
 * @see org.jomc.ObjectManager#getObject(java.lang.Class) getObject(Scope[].class)
 * @see org.jomc.ObjectManager#getObject(java.lang.Class,java.lang.String) getObject(Scope.class, "<i>implementation name</i>")
 * @see org.jomc.ObjectManagerFactory
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.10-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.10/jomc-tools-1.10-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public interface Scope
{
    // SECTION-START[Scope]

    /**
     * Gets the objects of the scope.
     *
     * @return The objects of the scope or {@code null}.
     */
    Map<String, Object> getObjects();

    /**
     * Gets an object from the scope.
     *
     * @param identifier The identifier of the object to get from the scope.
     *
     * @return The object identified by {@code identifier} or {@code null}, if no such object exists in the scope.
     *
     * @throws NullPointerException if {@code identifier} is {@code null}.
     */
    Object getObject( String identifier ) throws NullPointerException;

    /**
     * Puts an object into the scope.
     *
     * @param identifier The identifier of the object to put into the scope.
     * @param object The object to put into the scope.
     *
     * @return The previous object from the scope or {@code null}, if there was no object in the scope.
     *
     * @throws NullPointerException if {@code identifier} or {@code object} is {@code null}.
     */
    Object putObject( String identifier, Object object ) throws NullPointerException;

    /**
     * Removes an object from the scope.
     *
     * @param identifier The identifier of the object to remove from the scope.
     *
     * @return The removed object or {@code null}, if there was no object in the scope.
     *
     * @throws NullPointerException if {@code identifier} is {@code null}.
     */
    Object removeObject( String identifier ) throws NullPointerException;

    // SECTION-END
}
