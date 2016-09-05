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
package org.jomc.test.support;

import java.util.Locale;
import org.jomc.ObjectManager;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test {@code ObjectManager} implementation throwing {@code UnsupportedOperationException}s.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.test.support.UnsupportedOperationExceptionObjectManager</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ API</dd>
 *   <dt><b>Specifications:</b></dt>
 *     <dd>org.jomc.ObjectManager @ 1.0</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>Yes</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 1.10
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.10", comments = "See http://www.jomc.org/jomc-tools/1.10-SNAPSHOT" )
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
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.10", comments = "See http://www.jomc.org/jomc-tools/1.10-SNAPSHOT" )
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
