/*
 *  JOMC :: RI
 *  Copyright (c) 2005 Christian Schulte <cs@schulte.it>
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
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
