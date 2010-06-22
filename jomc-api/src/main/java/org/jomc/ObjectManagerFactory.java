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
package org.jomc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Factory for the {@code ObjectManager} singleton.
 * <p><b>Messages</b><ul>
 * <li>"{@link #getFactoryClassNotFoundMessage factoryClassNotFoundMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Factory class ''{0}'' not found.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Fabrik-Klasse ''{0}'' nicht gefunden.</pre></td></tr>
 * </table>
 * <li>"{@link #getFactoryMethodAccessDeniedMessage factoryMethodAccessDeniedMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Access to method ''public static {0}.getObjecManager(ClassLoader)'' denied.{1}</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Zugriff auf Methode ''public static {0}.getObjecManager(ClassLoader)'' verweigert.{1}</pre></td></tr>
 * </table>
 * <li>"{@link #getFactoryMethodInvocationExceptionMessage factoryMethodInvocationExceptionMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Factory method ''public static {0}.getObjecManager(ClassLoader)'' invocation exception.{1}</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ausnahme bei der Ausf&uuml;hrung der Fabrik-Methode ''public static {0}.getObjecManager(ClassLoader)''.{1}</pre></td></tr>
 * </table>
 * <li>"{@link #getFactoryMethodNotFoundMessage factoryMethodNotFoundMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Factory method ''public static {0}.getObjecManager(ClassLoader)'' not found.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Fabrik-Methode ''public static {0}.getObjecManager(ClassLoader)'' nicht gefunden.</pre></td></tr>
 * </table>
 * <li>"{@link #getImplementationAccessDeniedMessage implementationAccessDeniedMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Access to implementation ''{0}'' denied.{1}</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Zugriff auf Implementierung ''{0}'' verweigert.{1}</pre></td></tr>
 * </table>
 * <li>"{@link #getImplementationClassNotFoundMessage implementationClassNotFoundMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Implementation class ''{0}'' not found.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Implementierungs-Klasse ''{0}'' nicht gefunden.</pre></td></tr>
 * </table>
 * <li>"{@link #getImplementationExceptionMessage implementationExceptionMessage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Implementation ''{0}'' invocation exception.{1}</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ausnahme bei der Erstellung der Implementierung ''{0}''.{1}</pre></td></tr>
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
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
// </editor-fold>
// SECTION-END
public abstract class ObjectManagerFactory
{
    // SECTION-START[ObjectManagerFactory]

    /** Constant for the name of the class providing the default {@code getObjectManager()} method. */
    private static final String DEFAULT_FACTORY_CLASSNAME = "org.jomc.ri.DefaultObjectManager";

    /** Constant for the name of the class providing the default {@code ObjectManager} implementation. */
    private static final String DEFAULT_IMPLEMENTATION_CLASSNAME = "org.jomc.ri.DefaultObjectManager";

    /** Constant for the name of the system property holding the {@code getObjectManager()} method's class name. */
    private static final String SYS_FACTORY_CLASSNAME = "org.jomc.ObjectManagerFactory";

    /** Constant for the name of the system property holding the {@code ObjectManager} implementation class name. */
    private static final String SYS_IMPLEMENTATION_CLASSNAME = "org.jomc.ObjectManager";

    /**
     * Gets the {@code ObjectManager} singleton instance.
     * <p>This method is controlled by system property {@code org.jomc.ObjectManagerFactory} providing the name of a
     * class declaring a <blockquote>{@code public static ObjectManager getObjectManager( ClassLoader )}</blockquote>
     * method called by this method to get the instance to return.</p>
     * <p><b>Note</b><br/>
     * The {@code newObjectManager} method should be used by {@code getObjectManager} implementors to retrieve a new
     * {@code ObjectManager} implementation.</p>
     *
     * @param classLoader The class loader to use for getting the singleton instance; {@code null} to use the platform's
     * bootstrap class loader.
     *
     * @return The {@code ObjectManager} singleton instance.
     *
     * @see #newObjectManager(java.lang.ClassLoader)
     *
     * @throws ObjectManagementException if getting the singleton instance fails.
     */
    public static ObjectManager getObjectManager( final ClassLoader classLoader )
    {
        final String factory = System.getProperty( SYS_FACTORY_CLASSNAME, DEFAULT_FACTORY_CLASSNAME );

        try
        {
            final Class<?> factoryClass = Class.forName( factory, true, classLoader );
            final Method factoryMethod = factoryClass.getMethod( "getObjectManager", ClassLoader.class );
            return (ObjectManager) factoryMethod.invoke( null, classLoader );
        }
        catch ( final ClassNotFoundException e )
        {
            throw new ObjectManagementException( getFactoryClassNotFoundMessage( Locale.getDefault(), factory ), e );
        }
        catch ( final NoSuchMethodException e )
        {
            throw new ObjectManagementException( getFactoryMethodNotFoundMessage( Locale.getDefault(), factory ), e );
        }
        catch ( final IllegalAccessException e )
        {
            throw new ObjectManagementException( getFactoryMethodAccessDeniedMessage(
                Locale.getDefault(), factory, e.getMessage() != null ? e.getMessage() : "" ), e );

        }
        catch ( final InvocationTargetException e )
        {
            String message = e.getMessage();
            if ( message == null && e.getTargetException() != null )
            {
                message = e.getTargetException().getMessage();
            }

            throw new ObjectManagementException( getFactoryMethodInvocationExceptionMessage(
                Locale.getDefault(), factory, message != null ? message : "" ), e );

        }
    }

    /**
     * Creates a new {@code ObjectManager} instance.
     * <p>The object manager implementation returned by this method is controlled by system property
     * {@code org.jomc.ObjectManager} providing the name of the {@code ObjectManager} implementation class to return
     * a new instance of.</p>
     *
     * @param classLoader The class loader to use for creating the instance; {@code null} to use the platform's
     * bootstrap class loader.
     *
     * @return A new {@code ObjectManager} instance.
     *
     * @throws ObjectManagementException if creating a new {@code ObjectManager} instance fails.
     */
    public static ObjectManager newObjectManager( final ClassLoader classLoader )
    {
        final String implementation =
            System.getProperty( SYS_IMPLEMENTATION_CLASSNAME, DEFAULT_IMPLEMENTATION_CLASSNAME );

        try
        {
            return Class.forName( implementation, true, classLoader ).asSubclass( ObjectManager.class ).newInstance();
        }
        catch ( final ClassNotFoundException e )
        {
            throw new ObjectManagementException( getImplementationClassNotFoundMessage(
                Locale.getDefault(), implementation ), e );

        }
        catch ( final InstantiationException e )
        {
            throw new ObjectManagementException( getImplementationExceptionMessage(
                Locale.getDefault(), implementation, e.getMessage() != null ? e.getMessage() : "" ), e );

        }
        catch ( final IllegalAccessException e )
        {
            throw new ObjectManagementException( getImplementationAccessDeniedMessage(
                Locale.getDefault(), implementation, e.getMessage() != null ? e.getMessage() : "" ), e );

        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code ObjectManagerFactory} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    public ObjectManagerFactory()
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
     * Gets the text of the {@code factoryClassNotFoundMessage} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Factory class ''{0}'' not found.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Fabrik-Klasse ''{0}'' nicht gefunden.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param factoryName Format argument.
     * @return The text of the {@code factoryClassNotFoundMessage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private static String getFactoryClassNotFoundMessage( final java.util.Locale locale, final java.lang.String factoryName )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ObjectManagerFactory", locale ).getString( "factoryClassNotFoundMessage" ), factoryName, (Object) null );
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

    /**
     * Gets the text of the {@code factoryMethodAccessDeniedMessage} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Access to method ''public static {0}.getObjecManager(ClassLoader)'' denied.{1}</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Zugriff auf Methode ''public static {0}.getObjecManager(ClassLoader)'' verweigert.{1}</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param factoryName Format argument.
     * @param detail Format argument.
     * @return The text of the {@code factoryMethodAccessDeniedMessage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private static String getFactoryMethodAccessDeniedMessage( final java.util.Locale locale, final java.lang.String factoryName, final java.lang.String detail )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ObjectManagerFactory", locale ).getString( "factoryMethodAccessDeniedMessage" ), factoryName, detail, (Object) null );
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

    /**
     * Gets the text of the {@code factoryMethodInvocationExceptionMessage} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Factory method ''public static {0}.getObjecManager(ClassLoader)'' invocation exception.{1}</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ausnahme bei der Ausf&uuml;hrung der Fabrik-Methode ''public static {0}.getObjecManager(ClassLoader)''.{1}</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param factoryName Format argument.
     * @param detail Format argument.
     * @return The text of the {@code factoryMethodInvocationExceptionMessage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private static String getFactoryMethodInvocationExceptionMessage( final java.util.Locale locale, final java.lang.String factoryName, final java.lang.String detail )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ObjectManagerFactory", locale ).getString( "factoryMethodInvocationExceptionMessage" ), factoryName, detail, (Object) null );
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

    /**
     * Gets the text of the {@code factoryMethodNotFoundMessage} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Factory method ''public static {0}.getObjecManager(ClassLoader)'' not found.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Fabrik-Methode ''public static {0}.getObjecManager(ClassLoader)'' nicht gefunden.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param factoryName Format argument.
     * @return The text of the {@code factoryMethodNotFoundMessage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private static String getFactoryMethodNotFoundMessage( final java.util.Locale locale, final java.lang.String factoryName )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ObjectManagerFactory", locale ).getString( "factoryMethodNotFoundMessage" ), factoryName, (Object) null );
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

    /**
     * Gets the text of the {@code implementationAccessDeniedMessage} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Access to implementation ''{0}'' denied.{1}</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Zugriff auf Implementierung ''{0}'' verweigert.{1}</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param factoryName Format argument.
     * @param detail Format argument.
     * @return The text of the {@code implementationAccessDeniedMessage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private static String getImplementationAccessDeniedMessage( final java.util.Locale locale, final java.lang.String factoryName, final java.lang.String detail )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ObjectManagerFactory", locale ).getString( "implementationAccessDeniedMessage" ), factoryName, detail, (Object) null );
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

    /**
     * Gets the text of the {@code implementationClassNotFoundMessage} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Implementation class ''{0}'' not found.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Implementierungs-Klasse ''{0}'' nicht gefunden.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param factoryName Format argument.
     * @return The text of the {@code implementationClassNotFoundMessage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private static String getImplementationClassNotFoundMessage( final java.util.Locale locale, final java.lang.String factoryName )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ObjectManagerFactory", locale ).getString( "implementationClassNotFoundMessage" ), factoryName, (Object) null );
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

    /**
     * Gets the text of the {@code implementationExceptionMessage} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Implementation ''{0}'' invocation exception.{1}</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ausnahme bei der Erstellung der Implementierung ''{0}''.{1}</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param factoryName Format argument.
     * @param detail Format argument.
     * @return The text of the {@code implementationExceptionMessage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.0-beta-5-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.0-beta-5-SNAPSHOT/jomc-tools" )
    private static String getImplementationExceptionMessage( final java.util.Locale locale, final java.lang.String factoryName, final java.lang.String detail )
    {
        try
        {
            final String message = java.text.MessageFormat.format( java.util.ResourceBundle.getBundle( "org/jomc/ObjectManagerFactory", locale ).getString( "implementationExceptionMessage" ), factoryName, detail, (Object) null );
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
