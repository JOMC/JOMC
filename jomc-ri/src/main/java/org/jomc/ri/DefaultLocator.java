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

import java.io.IOException;
import java.net.URI;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import org.jomc.spi.Locator;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Default {@code Locator} implementation.
 * <p><b>Messages</b><ul>
 * <li>"{@link #getUnsupportedUriSchemeMessage unsupportedUriSchemeMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>URI scheme ''{0}'' is not supported.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Keine Unterst&uuml;tzung f&uuml;r URI Schema ''{0}''.</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.1-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.1-SNAPSHOT/jomc-tools" )
// </editor-fold>
// SECTION-END
public class DefaultLocator implements Locator
{
    // SECTION-START[DefaultLocator]

    /** Constant for the {@code 'jndi'} URI scheme. */
    private static final String JNDI_URI_SCHEME = "jndi";

    /** Constant for the {@code 'jndi+rmi'} URI scheme. */
    private static final String JNDI_RMI_URI_SCHEME = "jndi+rmi";

    /** URI schemes supported by this {@code Locator} implementation. */
    private static final String[] SUPPORTED_URI_SCHEMES =
    {
        JNDI_URI_SCHEME, JNDI_RMI_URI_SCHEME
    };

    /** The JNDI context of the instance. */
    private Context jndiContext;

    /**
     * Gets a flag indicating support for a given location URI.
     *
     * @param location The location URI to test support for.
     *
     * @return {@code true} if {@code location} is supported by this implementation; {@code false} else.
     *
     * @throws NullPointerException if {@code location} is {@code null}.
     */
    public boolean isLocationSupported( final URI location )
    {
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        final String scheme = location.getScheme();
        for ( String s : SUPPORTED_URI_SCHEMES )
        {
            if ( s.equals( scheme ) )
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the JNDI context of the instance.
     *
     * @return The JNDI context of the instance.
     *
     * @throws NamingException if getting the context fails.
     */
    public Context getJndiContext() throws NamingException
    {
        if ( this.jndiContext == null )
        {
            this.jndiContext = new InitialContext();
        }

        return this.jndiContext;
    }

    /**
     * Gets the JNDI name for a given location.
     *
     * @param location The location to get a JNDI name for.
     *
     * @return The JNDI name for {@code location}.
     *
     * @throws NullPointerException if {@code location} is {@code null}.
     */
    public String getJndiName( final URI location )
    {
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        String name = location.getSchemeSpecificPart();
        if ( name == null || name.replace( '/', ' ' ).trim().length() == 0 )
        {
            name = "";
        }
        if ( location.getFragment() != null )
        {
            name += '#' + location.getFragment();
        }

        return name;
    }

    public <T> T getObject( final Class<T> specification, final URI location ) throws IOException
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        try
        {
            T object = null;

            final String scheme = location.getScheme();
            if ( !this.isLocationSupported( location ) )
            {
                throw new IOException( getUnsupportedUriSchemeMessage( Locale.getDefault(), location.getScheme() ) );
            }

            final Object jndiObject = this.getJndiContext().lookup( this.getJndiName( location ) );

            if ( JNDI_URI_SCHEME.equals( scheme ) )
            {
                object = (T) jndiObject;
            }
            else if ( JNDI_RMI_URI_SCHEME.equals( scheme ) )
            {
                object = (T) PortableRemoteObject.narrow( jndiObject, specification );
            }

            return object;
        }
        catch ( final NamingException e )
        {
            throw (IOException) new IOException( e.getMessage() ).initCause( e );
        }
        catch ( final ClassCastException e )
        {
            throw (IOException) new IOException( e.getMessage() ).initCause( e );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultLocator} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.1-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.1-SNAPSHOT/jomc-tools" )
    public DefaultLocator()
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
    // <editor-fold defaultstate="collapsed" desc=" Generated Messages ">

    /**
     * Gets the text of the {@code unsupportedUriSchemeMessage} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>URI scheme ''{0}'' is not supported.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Keine Unterst&uuml;tzung f&uuml;r URI Schema ''{0}''.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param schemeInfo Format argument.
     * @return The text of the {@code unsupportedUriSchemeMessage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.1-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.1-SNAPSHOT/jomc-tools" )
    private static String getUnsupportedUriSchemeMessage( final java.util.Locale locale, final java.lang.String schemeInfo )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ri/DefaultLocator", locale ).getString( "unsupportedUriSchemeMessage" ), schemeInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            final java.io.BufferedReader reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            return builder.substring( lineSeparator.length() );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
    }
    // </editor-fold>
    // SECTION-END
}
