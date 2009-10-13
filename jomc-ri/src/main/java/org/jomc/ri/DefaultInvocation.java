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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.jomc.model.Instance;
import org.jomc.spi.Invocation;

// SECTION-START[Documentation]
/**
 * Default invocation.
 * @see DefaultInvoker
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-8-SNAPSHOT/jomc-tools" )
// SECTION-END
public class DefaultInvocation implements Invocation
{
    // SECTION-START[DefaultInvocation]

    /** Constant for the context key of the {@code Instance} corresponding to the object of this invocation. */
    public static final String INSTANCE_KEY = Instance.class.getName();

    /** The context of this invocation. */
    private Map context;

    /** The object of this invocation. */
    private Object object;

    /** The method of this invocation. */
    private Method method;

    /** The arguments of this invocation. */
    private Object[] arguments;

    /** The result of this invocation. */
    private Object result;

    /**
     * Creates a new {@code DefaultInvocation} instance taking an invocation to initialize the instance with.
     *
     * @param invocation The invocation to initialize the instance with.
     */
    public DefaultInvocation( final DefaultInvocation invocation )
    {
        this( invocation.getObject(), invocation.getMethod(), invocation.getArguments() );
        this.context = new HashMap( invocation.getContext() );
        this.result = invocation.getResult();
    }

    /**
     * Creates a new {@code DefaultInvocation} instance taking an object of the invocation, a method and the
     * arguments of the invocation.
     *
     * @param object The object of the invocation.
     * @param method The method of the invocation.
     * @param arguments The arguments of the invocation or {@code null},
     */
    public DefaultInvocation( final Object object, final Method method, final Object[] arguments )
    {
        super();
        this.object = object;
        this.method = method;
        this.arguments = arguments;
    }

    public Map getContext()
    {
        if ( this.context == null )
        {
            this.context = new HashMap();
        }

        return this.context;
    }

    public Object getObject()
    {
        return this.object;
    }

    public Method getMethod()
    {
        return this.method;
    }

    public Object[] getArguments()
    {
        return this.arguments;
    }

    public Object getResult()
    {
        return this.result;
    }

    public void setResult( final Object value )
    {
        this.result = value;
    }

    /**
     * Gets the instance of the object of this invocation from the context of this invocation.
     *
     * @return The instance of the object of this invocation from the context of this invocation or {@code null}.
     *
     * @see #setInstance(org.jomc.model.Instance)
     * @see #INSTANCE_KEY
     */
    public Instance getInstance()
    {
        return (Instance) this.getContext().get( INSTANCE_KEY );
    }

    /**
     * Sets the instance of the object of this invocation into the context of this invocation.
     *
     * @param value The new instance of the object of this invocation to set into the context or {@code null}.
     *
     * @return The previous context {@code Instance} or {@code null}, if the context did not contain an
     * {@code Instance}.
     *
     * @see #getInstance()
     * @see #INSTANCE_KEY
     */
    public Instance setInstance( final Instance value )
    {
        if ( value == null )
        {
            return (Instance) this.getContext().remove( INSTANCE_KEY );
        }
        else
        {
            return (Instance) this.getContext().put( INSTANCE_KEY, value );
        }
    }

    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
