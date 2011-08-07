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
package org.jomc.test;

import java.util.Locale;
import org.jomc.ObjectManager;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test {@code ObjectManager} implementation.
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="2" nowrap><font size="+2">Implementation</font></th>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Identifier:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code org.jomc.test.UnsupportedOperationExceptionObjectManager}</td>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Name:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code JOMC API}</td>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Flags:</b></td>
 *       <td class="TableRowColor" align="left" nowrap> {@code final}</td>
 *     </tr>
 *     <tr>
 *       <td class="TableSubHeadingColor" align="left" nowrap><b>Version:</b></td>
 *       <td class="TableRowColor" align="left" nowrap>{@code 1.2-SNAPSHOT}</td>
 *     </tr>
 *   </table>
 * </p>
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="4" nowrap><font size="+2">Specifications</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Identifier</b></td>
 *       <td align="left" scope="col" nowrap><b>Class</b></td>
 *       <td align="left" scope="col" nowrap><b>Scope</b></td>
 *       <td align="left" scope="col" nowrap><b>Version</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@code org.jomc.ObjectManager}</td>
 *       <td align="left" nowrap>{@code org.jomc.ObjectManager}</td>
 *       <td align="left" nowrap>{@code Singleton}</td>
 *       <td align="left" nowrap>{@code 1.0}</td>
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
public class UnsupportedOperationExceptionObjectManager
    implements
    org.jomc.ObjectManager
{
    // SECTION-START[ObjectManager]

    public <T> T getObject( final Class<T> specification )
    {
        throw new UnsupportedOperationException();
    }

    public <T> T getObject( final Class<T> specification, final String implementationName )
    {
        throw new UnsupportedOperationException();
    }

    public Object getDependency( final Object object, final String dependencyName )
    {
        throw new UnsupportedOperationException();
    }

    public Object getProperty( final Object object, final String propertyName )
    {
        throw new UnsupportedOperationException();
    }

    public String getMessage( final Object object, final String messageName, final Locale locale,
                              final Object... arguments )
    {
        throw new UnsupportedOperationException();
    }

    // SECTION-END
    // SECTION-START[UnsupportedOperationExceptionObjectManager]
    private static final ObjectManager objectManager = new UnsupportedOperationExceptionObjectManager();

    public static ObjectManager getObjectManager( final ClassLoader classLoader )
    {
        return objectManager;
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code UnsupportedOperationExceptionObjectManager} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public UnsupportedOperationExceptionObjectManager()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}