// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2010 The JOMC Project
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
// </editor-fold>
// SECTION-END
package org.jomc.spi;

import java.lang.reflect.Method;
import java.util.Map;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Invocation of an object.
 * <p>This specification declares a multiplicity of {@code One}.
 * An application assembler is required to provide no more than one implementation of this specification (including none).
 * Use of class {@link org.jomc.ObjectManager ObjectManager} is supported for getting that implementation.<pre>
 * Invocation object = (Invocation) ObjectManagerFactory.getObjectManager( getClass().getClassLoader() ).getObject( Invocation.class );
 * </pre>
 * </p>
 *
 * <p>This specification does not apply to any scope. A new object is returned whenever requested.</p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-19-SNAPSHOT/jomc-tools" )
// </editor-fold>
// SECTION-END
public interface Invocation
{
    // SECTION-START[Invocation]

    /**
     * Gets the context of this invocation.
     *
     * @return The context of this invocation.
     */
    Map getContext();

    /**
     * Gets the object of this invocation.
     *
     * @return The object of this invocation.
     */
    Object getObject();

    /**
     * Gets the method of this invocation.
     *
     * @return The method of this invocation.
     */
    Method getMethod();

    /**
     * Gets the arguments of this invocation.
     *
     * @return The arguments of this invocation or {@code null}.
     */
    Object[] getArguments();

    /**
     * Gets the result of this invocation.
     *
     * @return The result of this invocation or {@code null}.
     *
     * @see #setResult(java.lang.Object)
     */
    Object getResult();

    /**
     * Sets the result of this invocation.
     *
     * @param value The new result of this invocation or {@code null}.
     *
     * @see #getResult()
     */
    void setResult( Object value );

    // SECTION-END
}
