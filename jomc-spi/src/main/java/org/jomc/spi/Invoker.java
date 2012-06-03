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

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Invokes objects.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.spi.Invoker</dd>
 *   <dt><b>Multiplicity:</b></dt><dd>One</dd>
 *   <dt><b>Scope:</b></dt><dd>None</dd>
 * </dl>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 1.0
 * @see org.jomc.ObjectManager#getObject(java.lang.Class) getObject(Invoker.class)
 * @see org.jomc.ObjectManager#getObject(java.lang.Class,java.lang.String) getObject(Invoker.class, "<i>implementation name</i>")
 * @see org.jomc.ObjectManagerFactory
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.3", comments = "See http://jomc.sourceforge.net/jomc/1.3/jomc-tools-1.3" )
// </editor-fold>
// SECTION-END
public interface Invoker
{
    // SECTION-START[Invoker]

    /**
     * Performs a method invocation on an object.
     *
     * @param invocation The invocation to perform.
     *
     * @return The return value of the invocation. If the declared return type of the method of the invocation is a
     * primitive type, then the value returned by this method must be an instance of the corresponding primitive wrapper
     * class; otherwise, it must be a type assignable to the declared return type of the method of the invocation.
     * If the value returned by this method is {@code null} and the declared return type of the method of the invocation
     * is primitive, then a {@code NullPointerException} will be thrown. If the value returned by this method is
     * otherwise not compatible to the declared return type of the method of the invocation, a
     * {@code ClassCastException} will be thrown.
     *
     * @throws Throwable The exception thrown from the method invocation. The exception's type must be assignable
     * either to any of the exception types declared in the {@code throws} clause of the method of the invocation or to
     * the unchecked exception types {@code java.lang.RuntimeException} or {@code java.lang.Error}.
     * If a checked exception is thrown by this method that is not assignable to any of the exception types declared in
     * the {@code throws} clause of the method of the invocation, then an {@code UndeclaredThrowableException}
     * containing the exception that was thrown by this method will be thrown.
     *
     * @see java.lang.reflect.UndeclaredThrowableException
     */
    Object invoke( Invocation invocation ) throws Throwable;

    // SECTION-END
}
