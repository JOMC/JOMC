// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Java Object Management and Configuration
 *   Copyright (C) Christian Schulte, 2005-206
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
 *   THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *   INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 *   AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 *   THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *   NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *   DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *   THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *   THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $JOMC$
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
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.spi.Invocation</dd>
 *   <dt><b>Multiplicity:</b></dt><dd>One</dd>
 *   <dt><b>Scope:</b></dt><dd>None</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 1.0
 * @see org.jomc.ObjectManager#getObject(java.lang.Class) getObject(Invocation.class)
 * @see org.jomc.ObjectManager#getObject(java.lang.Class,java.lang.String) getObject(Invocation.class, "<i>implementation name</i>")
 * @see org.jomc.ObjectManagerFactory
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.8", comments = "See http://www.jomc.org/jomc/1.8/jomc-tools-1.8" )
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
