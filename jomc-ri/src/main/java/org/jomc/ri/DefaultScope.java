/*
 *   Copyright (c) 2009 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@schulte.it>
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
package org.jomc.ri;

import java.util.Map;
import org.jomc.spi.Scope;

/**
 * Default {@code Scope} implementation.
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a>
 * @version $Id$
 */
public class DefaultScope implements Scope
{

    /** The name of the scope. */
    private String name;

    /** Objects of the scope. */
    private Map<String, Object> objects;

    /**
     * Creates a new {@code DefaultScope} instance taking the name of the scope.
     *
     * @param name The name of the scope.
     * @param map The map backing the scope or {@code null}.
     */
    public DefaultScope( final String name, final Map<String, Object> map )
    {
        this.name = name;
        this.objects = map;
    }

    /**
     * Gets the {@code Map} backing the scope.
     *
     * @return The {@code Map} backing the scope.
     */
    public Map<String, Object> getObjects()
    {
        return this.objects;
    }

    public String getName()
    {
        return this.name;
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

}
