// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2010 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
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
// </editor-fold>
// SECTION-END
package org.jomc.ri;

import java.lang.reflect.InvocationTargetException;
import org.jomc.model.Instance;
import org.jomc.spi.Invocation;
import org.jomc.spi.Invoker;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Default {@code Invoker} implementation.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-21-SNAPSHOT/jomc-tools" )
// </editor-fold>
// SECTION-END
public class DefaultInvoker implements Invoker
{
    // SECTION-START[DefaultInvoker]

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
    public Object invoke( final Invocation invocation ) throws Throwable
    {
        Invocation current = invocation;
        final Instance instance = (Instance) current.getContext().get( DefaultInvocation.INSTANCE_KEY );

        try
        {
            if ( instance != null && instance.isStateless() )
            {
                try
                {
                    current = this.preInvoke( current );
                }
                catch ( final Throwable t )
                {
                    this.handleException( current, t );
                }

                if ( !( current.getResult() instanceof Throwable ) )
                {
                    try
                    {
                        current.setResult( current.getMethod().invoke( current.getObject(), current.getArguments() ) );
                    }
                    catch ( final Throwable t )
                    {
                        this.handleException( current, t );
                    }
                }

                try
                {
                    current = this.postInvoke( current );
                }
                catch ( final Throwable t )
                {
                    this.handleException( current, t );
                }

                if ( current.getResult() instanceof Throwable )
                {
                    throw (Throwable) current.getResult();
                }

                return current.getResult();
            }
            else
            {
                synchronized ( invocation.getObject() )
                {
                    try
                    {
                        current = this.preInvoke( current );
                    }
                    catch ( final Throwable t )
                    {
                        this.handleException( current, t );
                    }

                    if ( !( current.getResult() instanceof Throwable ) )
                    {
                        try
                        {
                            current.setResult( current.getMethod().invoke( current.getObject(),
                                                                           current.getArguments() ) );

                        }
                        catch ( final Throwable t )
                        {
                            this.handleException( current, t );
                        }
                    }

                    try
                    {
                        current = this.postInvoke( current );
                    }
                    catch ( final Throwable t )
                    {
                        this.handleException( current, t );
                    }

                    if ( current.getResult() instanceof Throwable )
                    {
                        throw (Throwable) current.getResult();
                    }

                    return current.getResult();
                }
            }
        }
        finally
        {
            invocation.getContext().clear();
        }
    }

    /**
     * Called before an invocation is performed.
     * <p>Overriding classes may use this method to perform any kind of operation prior to an invocation and to create
     * custom invocation instances. If an overriding class wishes to throw an exception, it may do so by setting the
     * result property of the returned invocation to an instance of {@code Throwable} thrown as the result of the
     * invocation. If an overriding class wishes to provide a custom {@code Invocation} class, it may do so by returning
     * a different instance from this method. By default, this method does nothing and returns the given invocation
     * unchanged.</p>
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

    /**
     * Called whenever an exception has been caught.
     * <p>Overriding classes may use this method for handling exceptions. By default, this method updates the result of
     * the given invocation with the given throwable. If that throwable is an instance of
     * {@code InvocationTargetException}, this method updates the result with the value of that exception's target
     * exception. If the result of the given invocation already is an instance of {@code Throwable}, this method does
     * not update the result.</p>
     *
     * @param invocation The invocation to update.
     * @param t The throwable to update {@code invocation} with.
     */
    public void handleException( final Invocation invocation, final Throwable t )
    {
        if ( invocation != null && !( invocation.getResult() instanceof Throwable ) )
        {
            if ( t instanceof InvocationTargetException &&
                 ( (InvocationTargetException) t ).getTargetException() != null )
            {
                invocation.setResult( ( (InvocationTargetException) t ).getTargetException() );
                return;
            }

            invocation.setResult( t );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultInvoker} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-21-SNAPSHOT/jomc-tools" )
    public DefaultInvoker()
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
