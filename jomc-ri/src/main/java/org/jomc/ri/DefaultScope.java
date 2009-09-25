// SECTION-START[License Header]
/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@jomc.org>
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
package org.jomc.ri;

import java.util.Map;
import org.jomc.spi.Scope;

// SECTION-START[Documentation]
/**
 * Default {@code Scope} implementation.
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-3-SNAPSHOT/jomc-tools" )
// SECTION-END
public class DefaultScope implements Scope
{
    // SECTION-START[DefaultScope]

    /** Objects of the scope. */
    private Map<String, Object> objects;

    /**
     * Creates a new {@code DefaultScope} instance taking a map backing the scope.
     *
     * @param map The map backing the scope or {@code null}.
     */
    public DefaultScope( final Map<String, Object> map )
    {
        this.objects = map;
    }

    public Map<String, Object> getObjects()
    {
        return this.objects;
    }

    public Object getObject( final String instance )
    {
        if ( this.getObjects() != null )
        {
            return this.getObjects().get( instance );
        }

        return null;
    }

    public Object putObject( final String instance, final Object object )
    {
        if ( this.getObjects() != null )
        {
            return this.getObjects().put( instance, object );
        }

        return null;
    }

    public Object removeObject( final String instance )
    {
        if ( this.getObjects() != null )
        {
            return this.getObjects().remove( instance );
        }

        return null;
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Creates a new {@code DefaultScope} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-3-SNAPSHOT/jomc-tools" )
    public DefaultScope()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
