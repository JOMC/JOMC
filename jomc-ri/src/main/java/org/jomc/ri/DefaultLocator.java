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
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.DefaultLocator</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ RI ⁑ DefaultLocator</dd>
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
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class DefaultLocator implements Locator
{
    // SECTION-START[DefaultLocator]

    /**
     * Constant for the {@code 'jndi'} URI scheme.
     */
    private static final String JNDI_URI_SCHEME = "jndi";

    /**
     * Constant for the {@code 'jndi+rmi'} URI scheme.
     */
    private static final String JNDI_RMI_URI_SCHEME = "jndi+rmi";

    /**
     * URI schemes supported by this {@code Locator} implementation.
     */
    private static final String[] SUPPORTED_URI_SCHEMES =
    {
        JNDI_URI_SCHEME, JNDI_RMI_URI_SCHEME
    };

    /**
     * The JNDI context of the instance.
     */
    private Context jndiContext;

    /**
     * Gets a flag indicating support for a given location URI.
     *
     * @param location The location URI to test support for.
     *
     * @return {@code true}, if {@code location} is supported by this implementation; {@code false}, else.
     *
     * @throws NullPointerException if {@code location} is {@code null}.
     */
    public boolean isLocationSupported( final URI location )
    {
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        for ( int i = SUPPORTED_URI_SCHEMES.length - 1; i >= 0; i-- )
        {
            if ( SUPPORTED_URI_SCHEMES[i].equals( location.getScheme() ) )
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

        T object = null;

        try
        {
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
            // JDK: As of JDK 6, "new IOException( message, cause )".
            throw (IOException) new IOException( getMessage( e ) ).initCause( e );
        }
        catch ( final ClassCastException e )
        {
            // JDK: As of JDK 6, "new IOException( message, cause )".
            throw (IOException) new IOException( getIllegalObjectMessage(
                Locale.getDefault(), object != null ? object.toString() : null,
                specification.getName() ) ).initCause( e );

        }
    }

    private static String getMessage( final Throwable t )
    {
        return t != null
                   ? t.getMessage() != null && t.getMessage().trim().length() > 0
                         ? t.getMessage()
                         : getMessage( t.getCause() )
                   : null;

    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code DefaultLocator} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
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
     * Gets the text of the {@code <Illegal Object Message>} message.
     * <p><dl>
     *   <dt><b>Languages:</b></dt>
     *     <dd>English (default)</dd>
     *     <dd>Deutsch</dd>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl></p>
     * @param locale The locale of the message to return.
     * @param objectInfo Format argument.
     * @param classInfo Format argument.
     * @return The text of the {@code <Illegal Object Message>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @SuppressWarnings({"unchecked", "unused", "PMD.UnnecessaryFullyQualifiedName"})
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
    private static String getIllegalObjectMessage( final java.util.Locale locale, final java.lang.String objectInfo, final java.lang.String classInfo )
    {
        java.io.BufferedReader reader = null;
        boolean suppressExceptionOnClose = true;

        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org.jomc.ri.DefaultLocator", locale ).getString( "Illegal Object Message" ), objectInfo, classInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            suppressExceptionOnClose = false;
            return builder.length() > 0 ? builder.substring( lineSeparator.length() ) : "";
        }
        catch( final java.lang.ClassCastException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.lang.IllegalArgumentException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        finally
        {
            try
            {
                if( reader != null )
                {
                    reader.close();
                }
            }
            catch( final java.io.IOException e )
            {
                if( !suppressExceptionOnClose )
                {
                    throw new org.jomc.ObjectManagementException( e.getMessage(), e );
                }
            }
        }
    }
    /**
     * Gets the text of the {@code <Unsupported URI Scheme Message>} message.
     * <p><dl>
     *   <dt><b>Languages:</b></dt>
     *     <dd>English (default)</dd>
     *     <dd>Deutsch</dd>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl></p>
     * @param locale The locale of the message to return.
     * @param schemeInfo Format argument.
     * @return The text of the {@code <Unsupported URI Scheme Message>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @SuppressWarnings({"unchecked", "unused", "PMD.UnnecessaryFullyQualifiedName"})
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
    private static String getUnsupportedUriSchemeMessage( final java.util.Locale locale, final java.lang.String schemeInfo )
    {
        java.io.BufferedReader reader = null;
        boolean suppressExceptionOnClose = true;

        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org.jomc.ri.DefaultLocator", locale ).getString( "Unsupported URI Scheme Message" ), schemeInfo, (Object) null );
            final java.lang.StringBuilder builder = new java.lang.StringBuilder( message.length() );
            reader = new java.io.BufferedReader( new java.io.StringReader( message ) );
            final String lineSeparator = System.getProperty( "line.separator", "\n" );

            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                builder.append( lineSeparator ).append( line );
            }

            suppressExceptionOnClose = false;
            return builder.length() > 0 ? builder.substring( lineSeparator.length() ) : "";
        }
        catch( final java.lang.ClassCastException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.lang.IllegalArgumentException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.util.MissingResourceException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        catch( final java.io.IOException e )
        {
            throw new org.jomc.ObjectManagementException( e.getMessage(), e );
        }
        finally
        {
            try
            {
                if( reader != null )
                {
                    reader.close();
                }
            }
            catch( final java.io.IOException e )
            {
                if( !suppressExceptionOnClose )
                {
                    throw new org.jomc.ObjectManagementException( e.getMessage(), e );
                }
            }
        }
    }
    // </editor-fold>
    // SECTION-END

}
