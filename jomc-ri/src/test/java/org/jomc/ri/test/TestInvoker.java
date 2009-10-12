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
package org.jomc.ri.test;

import java.util.Arrays;
import java.util.Iterator;
import org.jomc.ri.DefaultInvocation;
import org.jomc.ri.DefaultInvoker;

// SECTION-START[Documentation]
/**
 * Test {@code Invoker} implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.spi.Invoker} {@code Multiton}</li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-8-SNAPSHOT/jomc-tools" )
// SECTION-END
public class TestInvoker extends DefaultInvoker
{
    // SECTION-START[DefaultInvoker]

    @Override
    public DefaultInvocation postInvoke( final DefaultInvocation invocation )
    {
        final StringBuilder b = new StringBuilder();
        b.append( invocation.getObject().toString() );

        if ( invocation.getInstance() != null )
        {
            b.append( "[" ).append( invocation.getInstance().getIdentifier() ).append( "]: " );
        }

        b.append( invocation.getMethod().getName() ).append( "( " );

        for ( Iterator it = Arrays.asList( invocation.getArguments() ).iterator(); it.hasNext(); )
        {
            b.append( it.next() );
            if ( it.hasNext() )
            {
                b.append( ", " );
            }
        }

        b.append( " ): " ).append( invocation.getResult() );
        System.out.println( b.toString() );
        return invocation;
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Creates a new {@code TestInvoker} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-8-SNAPSHOT/jomc-tools" )
    public TestInvoker()
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
