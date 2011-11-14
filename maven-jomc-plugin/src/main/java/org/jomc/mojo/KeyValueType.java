/*
 *   Copyright (C) Christian Schulte, 2011-293
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
package org.jomc.mojo;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Datatype holding a {@code key}, {@code value} and {@code type} property.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $JOMC$
 * @since 1.2
 */
public class KeyValueType implements Cloneable
{

    /** The key of the type. */
    private String key;

    /** The value of the type. */
    private String value;

    /** The name of the class of the type of {@code value}. */
    private String type;

    /** Creates a new {@code KeyValueType} instance. */
    public KeyValueType()
    {
        super();
    }

    /**
     * Gets the value of the {@code key} property.
     *
     * @return The value of the {@code key} property.
     *
     * @see #setKey(java.lang.String)
     */
    public final String getKey()
    {
        return this.key;
    }

    /**
     * Sets the value of the {@code key} property.
     *
     * @param k The new value of the {@code key} property.
     *
     * @see #getKey()
     */
    public final void setKey( final String k )
    {
        this.key = k;
    }

    /**
     * Gets the value of the {@code value} property.
     *
     * @return The value of the {@code value} property or {@code null}.
     *
     * @see #setValue(java.lang.String)
     */
    public final String getValue()
    {
        return this.value;
    }

    /**
     * Sets the value of the {@code value} property.
     *
     * @param v The new value of the {@code value} property or {@code null}.
     *
     * @see #getValue()
     */
    public final void setValue( final String v )
    {
        this.value = v;
    }

    /**
     * Gets the value of the {@code type} property.
     *
     * @return The value of the {@code type} property or {@code null}.
     *
     * @see #setType(java.lang.String)
     */
    public final String getType()
    {
        return this.type;
    }

    /**
     * Sets the value of the {@code type} property.
     *
     * @param t The new value of the {@code type} property or {@code null}.
     *
     * @see #getType()
     */
    public final void setType( final String t )
    {
        this.type = t;
    }

    /**
     * Gets the object of the instance.
     *
     * @return The object of the instance or {@code null}.
     *
     * @throws InstantiationException if getting the object of the instance fails.
     *
     * @see #getType()
     * @see #getValue()
     */
    public Object getObject() throws InstantiationException // JDK: As of JDK 7, "throws ReflectiveOperationException".
    {
        try
        {
            Object o = this.getValue();

            if ( o != null )
            {
                if ( this.getType() != null && !String.class.getName().equals( this.getType() ) )
                {
                    o = Class.forName( this.getType() ).getConstructor( String.class ).newInstance( o );
                }
            }
            else if ( this.getType() != null )
            {
                o = Class.forName( this.getType() ).newInstance();
            }

            return o;
        }
        catch ( final ClassNotFoundException e )
        {
            throw (InstantiationException) new InstantiationException(
                Messages.getMessage( "failedCreatingObject", this.getType() ) ).initCause( e );

        }
        catch ( final NoSuchMethodException e )
        {
            throw (InstantiationException) new InstantiationException(
                Messages.getMessage( "failedCreatingObject", this.getType() ) ).initCause( e );

        }
        catch ( final IllegalAccessException e )
        {
            throw (InstantiationException) new InstantiationException(
                Messages.getMessage( "failedCreatingObject", this.getType() ) ).initCause( e );

        }
        catch ( final InvocationTargetException e )
        {
            throw (InstantiationException) new InstantiationException(
                Messages.getMessage( "failedCreatingObject", this.getType() ) ).initCause( e );

        }
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A copy of this object.
     */
    @Override
    public KeyValueType clone()
    {
        try
        {
            return (KeyValueType) super.clone();
        }
        catch ( final CloneNotSupportedException e )
        {
            throw new AssertionError( e );
        }
    }

    /**
     * Creates and returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

}