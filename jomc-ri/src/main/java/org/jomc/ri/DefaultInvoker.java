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

import java.lang.reflect.InvocationTargetException;
import org.jomc.model.Instance;
import org.jomc.spi.Invocation;
import org.jomc.spi.Invoker;

// SECTION-START[Documentation]
/**
 * Default {@code Invoker} implementation.
 * @see DefaultInvocation
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-8-SNAPSHOT/jomc-tools" )
// SECTION-END
public class DefaultInvoker implements Invoker
{
    // SECTION-START[Invoker]

    /**
     * Performs a method invocation on an object.
     * <p>This method first passes the given invocation to the {@code preInvoke} method. If the result property of the
     * invocation returned by the {@code preInvoke} method is an instance of {@code Throwable}, that instance will be
     * thrown; otherwise the invocation returned by the {@code preInvoke} method is performed and then passed to the
     * {@code postInvoke} method. If the result property of the invocation returned from the {@code postInvoke} method
     * is an instance of {@code Throwable}, that instance will be thrown; otherwise the value of the result property is
     * returned by this method.</p>
     *
     * @param invocation The invocation to perform.
     *
     * @return The return value of the invocation. If the declared return type of the method of the invocation is a
     * primitive type, then the value returned by this method must be an instance of the corresponding primitive wrapper
     * class; otherwise, it must be a type assignable to the declared return type of the method of the invocation.
     * If the value returned by this method is {@code null} and the declared return type of the method of the invocation
     * is primitive, then a {@code NullPointerException} will be thrown. If the value returned by this method is
     * otherwise not compatible to the declared return type of the method of the invocation, a
     * {@code ClassCastException} will be thrown.
     *
     * @throws Throwable The exception thrown from the method invocation. The exception's type must be assignable
     * either to any of the exception types declared in the {@code throws} clause of the method of the invocation or to
     * the unchecked exception types {@code java.lang.RuntimeException} or {@code java.lang.Error}.
     * If a checked exception is thrown by this method that is not assignable to any of the exception types declared in
     * the {@code throws} clause of the method of the invocation, then an {@code UndeclaredThrowableException}
     * containing the exception that was thrown by this method will be thrown.
     *
     * @see #preInvoke(org.jomc.spi.Invocation)
     * @see #postInvoke(org.jomc.spi.Invocation)
     */
    public Object invoke( Invocation invocation ) throws Throwable
    {
        final Instance instance = (Instance) invocation.getContext().get( DefaultInvocation.INSTANCE_KEY );

        try
        {
            if ( instance != null && instance.isStateless() )
            {
                invocation = this.preInvoke( invocation );
                if ( invocation.getResult() instanceof Throwable )
                {
                    throw (Throwable) invocation.getResult();
                }

                try
                {
                    invocation.setResult(
                        invocation.getMethod().invoke( invocation.getObject(), invocation.getArguments() ) );

                }
                catch ( final InvocationTargetException e )
                {
                    invocation.setResult( e.getTargetException() != null ? e.getTargetException() : e );
                }
                catch ( final Throwable t )
                {
                    invocation.setResult( t );
                }

                invocation = this.postInvoke( invocation );
                if ( invocation.getResult() instanceof Throwable )
                {
                    throw (Throwable) invocation.getResult();
                }
            }
            else
            {
                synchronized ( invocation.getObject() )
                {
                    invocation = this.preInvoke( invocation );
                    if ( invocation.getResult() instanceof Throwable )
                    {
                        throw (Throwable) invocation.getResult();
                    }

                    try
                    {
                        invocation.setResult(
                            invocation.getMethod().invoke( invocation.getObject(), invocation.getArguments() ) );

                    }
                    catch ( final InvocationTargetException e )
                    {
                        invocation.setResult( e.getTargetException() != null ? e.getTargetException() : e );
                    }
                    catch ( final Throwable t )
                    {
                        invocation.setResult( t );
                    }

                    invocation = this.postInvoke( invocation );
                    if ( invocation.getResult() instanceof Throwable )
                    {
                        throw (Throwable) invocation.getResult();
                    }
                }
            }

            return invocation.getResult();
        }
        finally
        {
            invocation.getContext().clear();
        }
    }

    // SECTION-END
    // SECTION-START[DefaultInvoker]
    /**
     * Called before an invocation is performed.
     * <p>Overriding classes may use this method to perform any kind of operation prior to an invocation and to create
     * custom invocation instances. If an overriding class wishes to throw an exception, it may do so by setting the
     * result property of the returned invocation to an instance of {@code Throwable} thrown as the result of the
     * invocation. If an overriding class wishes to extend the {@code DefaultInvocation} class, it may do so by
     * returning a different instance from this method. By default, this method does nothing and returns the given
     * invocation unchanged.</p>
     *
     * @param invocation The invocation about to be performed.
     *
     * @return The processed invocation.
     *
     * @throws NullPointerException if {@code invocation} is {@code null}.
     */
    public Invocation preInvoke( final Invocation invocation )
    {
        if ( invocation == null )
        {
            throw new NullPointerException( "invocation" );
        }

        return invocation;
    }

    /**
     * Called after an invocation has been performed.
     * <p>Overriding classes may use this method to perform any kind of operation after an invocation has been
     * performed and to maintain custom invocation instances. If an overriding class wishes to throw an exception, it
     * may do so by setting the result property of the returned invocation to an instance of {@code Throwable} thrown as
     * the result of the invocation. Since the result property of the given invocation already holds the result of the
     * invocation (which may already be an instance of {@code Throwable}), care must be taken when updating that result.
     * By default, this method does nothing and returns the given invocation unchanged.</p>
     *
     * @param invocation The performed invocation.
     *
     * @return The processed invocation.
     *
     * @throws NullPointerException if {@code invocation} is {@code null}.
     */
    public Invocation postInvoke( final Invocation invocation )
    {
        if ( invocation == null )
        {
            throw new NullPointerException( "invocation" );
        }

        return invocation;
    }

    // SECTION-END
    // SECTION-START[Constructors]
    /** Creates a new {@code DefaultInvoker} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-8-SNAPSHOT/jomc-tools" )
    public DefaultInvoker()
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
