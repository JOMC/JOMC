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
package org.jomc.ri;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.jomc.model.Instance;
import org.jomc.model.Modules;
import org.jomc.spi.Invocation;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Default {@code Invocation} implementation.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.DefaultInvocation</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC &#8273; RI &#8273; DefaultInvocation</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 1.0
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class DefaultInvocation implements Invocation
{
    // SECTION-START[DefaultInvocation]

    /** Constant for the context key of the {@code Object} of this invocation. */
    public static final String OBJECT_KEY = "org.jomc.spi.Invocation.object";

    /** Constant for the context key of the {@code Method} of this invocation. */
    public static final String METHOD_KEY = "org.jomc.spi.Invocation.method";

    /** Constant for the context key of the {@code Object[]} arguments of this invocation. */
    public static final String ARGUMENTS_KEY = "org.jomc.spi.Invocation.arguments";

    /** Constant for the context key of the result {@code Object} of this invocation. */
    public static final String RESULT_KEY = "org.jomc.spi.Invocation.result";

    /** Constant for the context key of the {@code Instance} corresponding to the object of this invocation. */
    public static final String INSTANCE_KEY = "org.jomc.spi.Invocation.instance";

    /** Constant for the context key of the {@code Modules} corresponding to the object of this invocation. */
    public static final String MODULES_KEY = "org.jomc.spi.Invocation.modules";

    /** Constant for the context key of the {@code ClassLoader} corresponding to the modules of this invocation. */
    public static final String CLASSLOADER_KEY = "org.jomc.spi.Invocation.classLoader";

    /** The context of this invocation. */
    private Map context;

    /**
     * Creates a new {@code DefaultInvocation} instance taking an invocation to initialize the instance with.
     *
     * @param invocation The invocation to initialize the instance with.
     */
    public DefaultInvocation( final Invocation invocation )
    {
        this.context = new HashMap( invocation.getContext() );
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
        return this.getContext().get( OBJECT_KEY );
    }

    public Method getMethod()
    {
        return (Method) this.getContext().get( METHOD_KEY );
    }

    public Object[] getArguments()
    {
        return (Object[]) this.getContext().get( ARGUMENTS_KEY );
    }

    public Object getResult()
    {
        return this.getContext().get( RESULT_KEY );
    }

    public void setResult( final Object value )
    {
        if ( value == null )
        {
            this.getContext().remove( RESULT_KEY );
        }
        else
        {
            this.getContext().put( RESULT_KEY, value );
        }
    }

    /**
     * Gets the instance of the object of this invocation from the context of this invocation.
     *
     * @return The instance of the object of this invocation from the context of this invocation or {@code null}.
     *
     * @see #INSTANCE_KEY
     */
    public Instance getInstance()
    {
        return (Instance) this.getContext().get( INSTANCE_KEY );
    }

    /**
     * Gets the modules corresponding to the object of this invocation from the context of this invocation.
     *
     * @return The modules corresponding to the object of this invocation from the context of this invocation or
     * {@code null}.
     *
     * @see #MODULES_KEY
     */
    public Modules getModules()
    {
        return (Modules) this.getContext().get( MODULES_KEY );
    }

    /**
     * Gets the class loader corresponding to the modules of this invocation from the context of this invocation.
     *
     * @return The class loader corresponding to the modules of this invocation from the context of this invocation or
     * {@code null}.
     *
     * @see #CLASSLOADER_KEY
     */
    public ClassLoader getClassLoader()
    {
        return (ClassLoader) this.getContext().get( CLASSLOADER_KEY );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code DefaultInvocation} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
    public DefaultInvocation()
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
