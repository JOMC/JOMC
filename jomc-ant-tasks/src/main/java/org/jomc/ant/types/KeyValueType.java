/*
 *   Copyright (c) 2009 The JOMC Project
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
package org.jomc.ant.types;

/**
 * Datatype holding a {@code key} and a {@code value} property.
 *
 * @param <K> The type of the {@code key} property.
 * @param <V> The type of the {@code value} property.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $Id$
 */
public class KeyValueType<K, V> implements Cloneable
{

    /** The key of the type. */
    private K key;

    /** The value of the type. */
    private V value;

    /**
     * Gets the value of the {@code key} property.
     *
     * @return The value of the {@code key} property.
     */
    public final K getKey()
    {
        return this.key;
    }

    /**
     * Sets the value of the {@code key} property.
     *
     * @param value The new value of the {@code key} property.
     */
    public final void setKey( final K value )
    {
        this.key = value;
    }

    /**
     * Gets the value of the {@code value} property.
     *
     * @return The value of the {@code value} property.
     */
    public final V getValue()
    {
        return this.value;
    }

    /**
     * Sets the value of the {@code key} property.
     *
     * @param value The new value of the {@code value} property.
     */
    public final void setValue( final V value )
    {
        this.value = value;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A copy of this object.
     */
    @Override
    @SuppressWarnings( "unchecked" )
    public KeyValueType<K, V> clone()
    {
        try
        {
            return (KeyValueType<K, V>) super.clone();
        }
        catch ( final CloneNotSupportedException e )
        {
            throw new AssertionError( e );
        }
    }

}