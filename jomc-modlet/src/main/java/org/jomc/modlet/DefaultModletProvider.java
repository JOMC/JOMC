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
package org.jomc.modlet;

import java.net.URL;
import java.util.Enumeration;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Default {@code ModletProvider} implementation.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $Id$
 * @see ModelContext#findModlets()
 */
public class DefaultModletProvider implements ModletProvider
{

    /**
     * Classpath location searched for {@code Modlets} by default.
     * @see #getDefaultModletLocation()
     */
    private static final String DEFAULT_MODLET_LOCATION = "META-INF/jomc-modlet.xml";

    /** Default {@code Modlet} location. */
    private static volatile String defaultModletLocation;

    /** Flag indicating the provider is enabled by default. */
    private static volatile Boolean defaultEnabled;

    /** Flag indicating the provider is enabled. */
    private Boolean enabled;

    /** Modlet location of the instance. */
    private String modletLocation;

    /** Creates a new {@code DefaultModletProvider} instance. */
    public DefaultModletProvider()
    {
        super();
    }

    /**
     * Gets a flag indicating the provider is enabled by default.
     * <p>The default enabled flag is controlled by system property
     * {@code org.jomc.modlet.DefaultModletProvider.defaultEnabled} holding a value indicating the provider is
     * enabled by default. If that property is not set, the {@code true} default is returned.</p>
     *
     * @return {@code true} if the provider is enabled by default; {@code false} if the provider is disabled by default.
     *
     * @see #setDefaultEnabled(java.lang.Boolean)
     */
    public static boolean isDefaultEnabled()
    {
        if ( defaultEnabled == null )
        {
            defaultEnabled = Boolean.valueOf( System.getProperty(
                "org.jomc.modlet.DefaultModletProvider.defaultEnabled", Boolean.toString( true ) ) );

        }

        return defaultEnabled;
    }

    /**
     * Sets the flag indicating the provider is enabled by default.
     *
     * @param value The new value of the flag indicating the provider is enabled by default or {@code null}.
     *
     * @see #isDefaultEnabled()
     */
    public static void setDefaultEnabled( final Boolean value )
    {
        defaultEnabled = value;
    }

    /**
     * Gets a flag indicating the provider is enabled.
     *
     * @return {@code true} if the provider is enabled; {@code false} if the provider is disabled.
     *
     * @see #isDefaultEnabled()
     * @see #setEnabled(java.lang.Boolean)
     */
    public boolean isEnabled()
    {
        if ( this.enabled == null )
        {
            this.enabled = isDefaultEnabled();
        }

        return this.enabled;
    }

    /**
     * Sets the flag indicating the provider is enabled.
     *
     * @param value The new value of the flag indicating the provider is enabled or {@code null}.
     *
     * @see #isEnabled()
     */
    public void setEnabled( final Boolean value )
    {
        this.enabled = value;
    }

    /**
     * Gets the default location searched for {@code Modlet} resources.
     * <p>The default {@code Modlet} location is controlled by system property
     * {@code org.jomc.modlet.DefaultModletProvider.defaultModletLocation} holding the location to search for
     * {@code Modlet} resources by default. If that property is not set, the {@code META-INF/jomc-modlet.xml} default is
     * returned.</p>
     *
     * @return The location searched for {@code Modlet} resources by default.
     *
     * @see #setDefaultModletLocation(java.lang.String)
     */
    public static String getDefaultModletLocation()
    {
        if ( defaultModletLocation == null )
        {
            defaultModletLocation = System.getProperty(
                "org.jomc.modlet.DefaultModletProvider.defaultModletLocation", DEFAULT_MODLET_LOCATION );

        }

        return defaultModletLocation;
    }

    /**
     * Sets the default location searched for {@code Modlet} resources.
     *
     * @param value The new default location to search for {@code Modlet} resources or {@code null}.
     *
     * @see #getDefaultModletLocation()
     */
    public static void setDefaultModletLocation( final String value )
    {
        defaultModletLocation = value;
    }

    /**
     * Gets the location searched for {@code Modlet} resources.
     *
     * @return The location searched for {@code Modlet} resources.
     *
     * @see #getDefaultModletLocation()
     * @see #setModletLocation(java.lang.String)
     */
    public String getModletLocation()
    {
        if ( this.modletLocation == null )
        {
            this.modletLocation = getDefaultModletLocation();
        }

        return this.modletLocation;
    }

    /**
     * Sets the location searched for {@code Modlet} resources.
     *
     * @param value The new location to search for {@code Modlet} resources or {@code null}.
     *
     * @see #getModletLocation()
     */
    public void setModletLocation( final String value )
    {
        this.modletLocation = value;
    }

    /**
     * Searches a given context for {@code Modlets}.
     *
     * @param context The context to search for {@code Modlets}.
     * @param location The location to search at.
     *
     * @return The {@code Modlets} found at {@code location} in {@code context} or {@code null} if no {@code Modlets}
     * are found.
     *
     * @throws NullPointerException if {@code context} or {@code location} is {@code null}.
     * @throws ModelException if searching the context fails.
     */
    public Modlets findModlets( final ModelContext context, final String location ) throws ModelException
    {
        if ( context == null )
        {
            throw new NullPointerException( "context" );
        }
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        try
        {
            Modlets modlets = null;
            final JAXBContext ctx = context.createContext( ModletObject.MODEL_PUBLIC_ID );
            final Unmarshaller u = ctx.createUnmarshaller();
            final Enumeration<URL> e = context.findResources( location );
            u.setSchema( context.createSchema( ModletObject.MODEL_PUBLIC_ID ) );

            while ( e.hasMoreElements() )
            {
                final URL url = e.nextElement();
                Object content = u.unmarshal( url );
                if ( content instanceof JAXBElement )
                {
                    content = ( (JAXBElement) content ).getValue();
                }

                if ( content instanceof Modlet )
                {
                    if ( modlets == null )
                    {
                        modlets = new Modlets();
                    }

                    modlets.getModlet().add( (Modlet) content );
                }
                else if ( content instanceof Modlets )
                {
                    if ( modlets == null )
                    {
                        modlets = new Modlets();
                    }

                    for ( Modlet m : ( (Modlets) content ).getModlet() )
                    {
                        modlets.getModlet().add( m );
                    }
                }
            }

            return modlets == null || modlets.getModlet().isEmpty() ? null : modlets;
        }
        catch ( final JAXBException e )
        {
            if ( e.getLinkedException() != null )
            {
                throw new ModelException( e.getLinkedException().getMessage(), e.getLinkedException() );
            }
            else
            {
                throw new ModelException( e.getMessage(), e );
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see #isEnabled()
     * @see #getModletLocation()
     * @see #findModlets(org.jomc.modlet.ModelContext, java.lang.String)
     */
    public Modlets findModlets( final ModelContext context ) throws ModelException
    {
        if ( context == null )
        {
            throw new NullPointerException( "context" );
        }

        return this.isEnabled() ? this.findModlets( context, this.getModletLocation() ) : null;
    }

}
