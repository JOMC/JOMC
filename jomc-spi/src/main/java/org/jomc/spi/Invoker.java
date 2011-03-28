// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) 2009 - 2011 The JOMC Project
 *   Copyright (C) 2005 - 2011 Christian Schulte <schulte2005@users.sourceforge.net>
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

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Invokes objects.
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="2" nowrap><font size="+2">Specification</font></th>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Identifier:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code org.jomc.spi.Invoker}</td>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Multiplicity:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code One}</td>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Scope:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code Multiton}</td>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Version:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code 1.0}</td>
 *     </tr>
 *   </table>
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
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
