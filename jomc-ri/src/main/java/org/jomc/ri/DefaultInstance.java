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

import java.util.HashMap;
import java.util.Map;

/**
 * Extended instance data.
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a>
 * @version $Id$
 */
public class DefaultInstance extends org.jomc.model.Instance
{

    /** Map of dependency objects of this instance. */
    private Map<String, Object> dependencyObjects;

    /** Map of property values. */
    private Map<String, Object> propertyObjects;

    /** Classloader of the instance. */
    private ClassLoader classLoader;

    /**
     * Creates a new {@code DefaultInstance} instance taking a classloder.
     *
     * @param classLoader The classloader of the instance.
     */
    public DefaultInstance( final ClassLoader classLoader )
    {
        super();
        this.classLoader = classLoader;
    }

    /**
     * Creates a new {@code DefaultInstance} instance taking an
     * {@code Instance} to initialize the new instance with and a classloader.
     *
     * @param classLoader The classloader of the instance.
     * @param instance The {@code Instance} to initialize the new instance with.
     */
    public DefaultInstance( final ClassLoader classLoader, final org.jomc.model.Instance instance )
    {
        this( classLoader );
        this.setIdentifier( instance.getIdentifier() );
        this.setClazz( instance.getClazz() );
        this.setDependencies( instance.getDependencies() );
        this.setDocumentation( instance.getDocumentation() );
        this.setMessages( instance.getMessages() );
        this.setModelVersion( instance.getModelVersion() );
        this.setProperties( instance.getProperties() );
        this.setScope( instance.getScope() );
    }

    /**
     * Gets the classloader of the instance.
     *
     * @return The classloader of the instance.
     */
    public ClassLoader getClassLoader()
    {
        return this.classLoader;
    }

    /**
     * Gets a mapping of dependency names to objects bound to the instance.
     *
     * @return A mapping of dependency names to objects bound to the instance.
     */
    public Map<String, Object> getDependencyObjects()
    {
        if ( this.dependencyObjects == null )
        {
            this.dependencyObjects = new HashMap<String, Object>();
        }

        return this.dependencyObjects;
    }

    /**
     * Gets a mapping of property names to objects bound to the instance.
     *
     * @return A mapping of property names to objects bound to the instance.
     */
    public Map<String, Object> getPropertyObjects()
    {
        if ( this.propertyObjects == null )
        {
            this.propertyObjects = new HashMap<String, Object>();
        }

        return this.propertyObjects;
    }

}
