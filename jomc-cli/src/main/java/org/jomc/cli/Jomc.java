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
package org.jomc.cli;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang.StringUtils;

// SECTION-START[Documentation]
/**
 * JOMC command line interface.
 * <p><b>Properties</b><ul>
 * <li>"{@link #getDescPad descPad}"<blockquote>
 * Property of type {@code int} with value "2".</blockquote></li>
 * <li>"{@link #getHelpCommandName helpCommandName}"<blockquote>
 * Property of type {@code java.lang.String} with value "help".</blockquote></li>
 * <li>"{@link #getLeftPad leftPad}"<blockquote>
 * Property of type {@code int} with value "2".</blockquote></li>
 * <li>"{@link #getWidth width}"<blockquote>
 * Property of type {@code int} with value "80".</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getCommands Commands}"<blockquote>
 * Dependency on {@code org.jomc.cli.Command} at specification level 1.0-alpha-2.</blockquote></li>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getIllegalArgumentsMessage illegalArguments}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal arguments. Type &raquo;jomc {0} {1}&laquo; for further information.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ung&uuml;ltige Argumente. Geben Sie &raquo;jomc {0} {1}&laquo; f&uuml;r weitere Informationen ein.</pre></td></tr>
 * </table>
 * <li>"{@link #getUsageMessage usage}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Type &raquo;jomc &lt;command&gt; {0}&laquo; for further information.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Geben Sie &raquo;jomc &lt;Befehl&gt; {0}&laquo; f&uuml;r weitere Informationen ein.</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
// SECTION-END
public class Jomc
{
    // SECTION-START[Jomc]

    /** Constant for a status code indicating success. */
    private static final int STATUS_SUCCESS = 0;

    /** Constant for a status code indicating failure. */
    private static final int STATUS_FAILURE = 1;

    /** Print stream of the instance. */
    private PrintStream printStream;

    /** Constant for the name of the system property controlling the bootstrap document location. */
    private static final String SYS_BOOTSTRAP_DOCUMENT_LOCATION =
        "org.jomc.model.DefaultModelManager.bootstrapDocumentLocation";

    /** Constant for the name of the system property controlling the default document location. */
    private static final String SYS_DEFAULT_DOCUMENT_LOCATION =
        "org.jomc.model.DefaultModelManager.defaultDocumentLocation";

    /** Constant for the name of the system property controlling the default stylesheet location. */
    private static final String SYS_DEFAULT_STYLESHEET_LOCATION =
        "org.jomc.model.DefaultModelManager.defaultStyelsheetLocation";

    /**
     * Gets the print stream of the instance.
     *
     * @return The print stream of the instance.
     */
    public PrintStream getPrintStream()
    {
        if ( this.printStream == null )
        {
            this.printStream = System.out;
        }

        return this.printStream;
    }

    /**
     * Sets the print stream of the instance.
     *
     * @param value The new print stream of the instance or {@code null}.
     */
    public void setPrintStream( final PrintStream value )
    {
        this.printStream = value;
    }

    /**
     * Processes the given arguments and executes the corresponding command.
     *
     * @param args Arguments to process.
     *
     * @return Status code.
     */
    public int jomc( final String[] args )
    {
        Command cmd = null;

        final String currentBootstrapDocumentLocation = System.getProperty( SYS_BOOTSTRAP_DOCUMENT_LOCATION );
        final String currentDefaultDocumentLocation = System.getProperty( SYS_DEFAULT_DOCUMENT_LOCATION );
        final String currentDefaultStylesheetLocation = System.getProperty( SYS_DEFAULT_STYLESHEET_LOCATION );

        try
        {
            System.setProperty( SYS_BOOTSTRAP_DOCUMENT_LOCATION, "META-INF/jomc-bootstrap.xml" );
            System.setProperty( SYS_DEFAULT_DOCUMENT_LOCATION, "META-INF/jomc-cli.xml" );
            System.setProperty( SYS_DEFAULT_STYLESHEET_LOCATION, "META-INF/jomc-cli.xslt" );

            final StringBuilder commandInfo = new StringBuilder();

            for ( Command c : this.getCommands() )
            {
                if ( cmd == null && args != null && args.length > 0 &&
                     ( args[0].equals( c.getName() ) || args[0].equals( c.getAbbreviatedName() ) ) )
                {
                    cmd = c;
                }

                commandInfo.append( StringUtils.rightPad( c.getName(), 25 ) ).append( " : " ).
                    append( c.getShortDescription( this.getLocale() ) ).append( " (" ).append( c.getAbbreviatedName() ).
                    append( ")" ).append( System.getProperty( "line.separator" ) );

            }

            if ( cmd == null )
            {
                this.getPrintStream().println( this.getUsageMessage( this.getLocale(), this.getHelpCommandName() ) );
                this.getPrintStream().println();
                this.getPrintStream().println( commandInfo.toString() );
                return STATUS_FAILURE;
            }

            final String[] commandArguments = new String[ args.length - 1 ];
            System.arraycopy( args, 1, commandArguments, 0, commandArguments.length );

            if ( commandArguments.length > 0 && this.getHelpCommandName().equals( commandArguments[0] ) )
            {
                final StringWriter usage = new StringWriter();
                final StringWriter opts = new StringWriter();
                final HelpFormatter formatter = new HelpFormatter();

                PrintWriter pw = new PrintWriter( usage );
                formatter.printUsage( pw, this.getWidth(), cmd.getName(), cmd.getOptions() );
                pw.close();

                pw = new PrintWriter( opts );
                formatter.printOptions( pw, this.getWidth(), cmd.getOptions(), this.getLeftPad(), this.getDescPad() );
                pw.close();

                this.getPrintStream().println( cmd.getShortDescription( this.getLocale() ) );
                this.getPrintStream().println();
                this.getPrintStream().println( usage.toString() );
                this.getPrintStream().println( opts.toString() );
                this.getPrintStream().println();
                this.getPrintStream().println( cmd.getLongDescription( this.getLocale() ) );
                this.getPrintStream().println();
                return STATUS_SUCCESS;
            }

            final CommandLineParser parser = new GnuParser();
            final CommandLine commandLine = parser.parse( cmd.getOptions(), commandArguments );

            System.clearProperty( SYS_BOOTSTRAP_DOCUMENT_LOCATION );
            System.clearProperty( SYS_DEFAULT_DOCUMENT_LOCATION );
            System.clearProperty( SYS_DEFAULT_STYLESHEET_LOCATION );

            return cmd.execute( commandLine, this.getPrintStream() );
        }
        catch ( final ParseException e )
        {
            this.getPrintStream().println( e.getMessage() );
            this.getPrintStream().println( this.getIllegalArgumentsMessage(
                this.getLocale(), cmd.getName(), this.getHelpCommandName() ) );

            return STATUS_FAILURE;
        }
        catch ( final Throwable t )
        {
            t.printStackTrace( this.getPrintStream() );
            return STATUS_FAILURE;
        }
        finally
        {
            if ( currentBootstrapDocumentLocation != null )
            {
                System.setProperty( SYS_BOOTSTRAP_DOCUMENT_LOCATION, currentBootstrapDocumentLocation );
            }
            if ( currentDefaultDocumentLocation != null )
            {
                System.setProperty( SYS_DEFAULT_DOCUMENT_LOCATION, currentDefaultDocumentLocation );
            }
            if ( currentDefaultStylesheetLocation != null )
            {
                System.setProperty( SYS_DEFAULT_STYLESHEET_LOCATION, currentDefaultStylesheetLocation );
            }
        }
    }

    /**
     * Main entry point.
     *
     * @param args The command arguments.
     */
    public static void main( final String[] args )
    {
        System.exit( new Jomc().jomc( args ) );
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Creates a new {@code Jomc} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
    public Jomc()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code Commands} dependency.
     * <p>This method returns any available object of the {@code org.jomc.cli.Command} specification at specification level 1.0-alpha-2.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code Commands} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
    private org.jomc.cli.Command[] getCommands()
    {
        final org.jomc.cli.Command[] _d = (org.jomc.cli.Command[]) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "Commands" );
        assert _d != null : "'Commands' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code Locale} dependency.
     * <p>This method returns the "{@code default}" object of the {@code java.util.Locale} specification at specification level 1.1.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code Locale} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
    private java.util.Locale getLocale()
    {
        final java.util.Locale _d = (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "Locale" );
        assert _d != null : "'Locale' dependency not found.";
        return _d;
    }
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code descPad} property.
     * @return The number of characters of padding to be prefixed to each description line.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
    private int getDescPad()
    {
        final java.lang.Integer _p = (java.lang.Integer) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "descPad" );
        assert _p != null : "'descPad' property not found.";
        return _p.intValue();
    }

    /**
     * Gets the value of the {@code helpCommandName} property.
     * @return The name of the command used to request help.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
    private java.lang.String getHelpCommandName()
    {
        final java.lang.String _p = (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "helpCommandName" );
        assert _p != null : "'helpCommandName' property not found.";
        return _p;
    }

    /**
     * Gets the value of the {@code leftPad} property.
     * @return The number of characters of padding to be prefixed to each line.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
    private int getLeftPad()
    {
        final java.lang.Integer _p = (java.lang.Integer) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "leftPad" );
        assert _p != null : "'leftPad' property not found.";
        return _p.intValue();
    }

    /**
     * Gets the value of the {@code width} property.
     * @return The number of characters per line for the usage statement.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
    private int getWidth()
    {
        final java.lang.Integer _p = (java.lang.Integer) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "width" );
        assert _p != null : "'width' property not found.";
        return _p.intValue();
    }
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code illegalArguments} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal arguments. Type &raquo;jomc {0} {1}&laquo; for further information.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ung&uuml;ltige Argumente. Geben Sie &raquo;jomc {0} {1}&laquo; f&uuml;r weitere Informationen ein.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param command Format argument.
     * @param helpCommandName Format argument.
     * @return The text of the {@code illegalArguments} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
    private String getIllegalArgumentsMessage( final java.util.Locale locale, final java.lang.String command, final java.lang.String helpCommandName )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "illegalArguments", locale, new Object[] { command, helpCommandName, null } );
        assert _m != null : "'illegalArguments' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code usage} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Type &raquo;jomc &lt;command&gt; {0}&laquo; for further information.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Geben Sie &raquo;jomc &lt;Befehl&gt; {0}&laquo; f&uuml;r weitere Informationen ein.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param helpCommandName Format argument.
     * @return The text of the {@code usage} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-2/jomc-tools" )
    private String getUsageMessage( final java.util.Locale locale, final java.lang.String helpCommandName )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "usage", locale, new Object[] { helpCommandName, null } );
        assert _m != null : "'usage' message not found.";
        return _m;
    }
    // SECTION-END
}
