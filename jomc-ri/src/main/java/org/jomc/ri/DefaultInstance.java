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

import java.util.HashMap;
import java.util.Map;
import org.jomc.model.Instance;

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
        this( classLoader, null );
    }

    /**
     * Creates a new {@code DefaultInstance} instance taking a classloader and an {@code Instance} to copy.
     *
     * @param classLoader The classloader of the instance.
     * @param instance The instance to copy or {@code null}.
     */
    public DefaultInstance( final ClassLoader classLoader, final Instance instance )
    {
        super( instance );
        this.classLoader = classLoader;
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
