// SECTION-START[License Header]
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
// SECTION-END
package org.jomc.cli.commands;

import java.io.File;
import java.io.PrintStream;
import java.util.Locale;
import java.util.logging.Level;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.jomc.model.ModelException;
import org.jomc.tools.JavaBundles;

// SECTION-START[Implementation Comment]
/**
 * Command line interface for the {@code JavaBundles} tool.
 *
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.cli.Command} {@code 1.0}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #getAbbreviatedCommandName abbreviatedCommandName}"<blockquote>
 * Property of type {@code java.lang.String} with value "gjb".</blockquote></li>
 * <li>"{@link #getBuildDirectoryOptionLongName buildDirectoryOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "build-dir".</blockquote></li>
 * <li>"{@link #getBuildDirectoryOptionShortName buildDirectoryOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "bd".</blockquote></li>
 * <li>"{@link #getClasspathOptionLongName classpathOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "classpath".</blockquote></li>
 * <li>"{@link #getClasspathOptionShortName classpathOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "cp".</blockquote></li>
 * <li>"{@link #getCommandName commandName}"<blockquote>
 * Property of type {@code java.lang.String} with value "generate-java-bundles".</blockquote></li>
 * <li>"{@link #getDebugOptionLongName debugOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "debug".</blockquote></li>
 * <li>"{@link #getDebugOptionShortName debugOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "D".</blockquote></li>
 * <li>"{@link #getDocumentLocationOptionLongName documentLocationOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "document-location".</blockquote></li>
 * <li>"{@link #getDocumentLocationOptionShortName documentLocationOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "dl".</blockquote></li>
 * <li>"{@link #getDocumentsOptionLongName documentsOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "documents".</blockquote></li>
 * <li>"{@link #getDocumentsOptionShortName documentsOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "df".</blockquote></li>
 * <li>"{@link #getLanguageOptionLongName languageOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "language".</blockquote></li>
 * <li>"{@link #getLanguageOptionShortName languageOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "l".</blockquote></li>
 * <li>"{@link #getModuleNameOptionLongName moduleNameOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "module".</blockquote></li>
 * <li>"{@link #getModuleNameOptionShortName moduleNameOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "mn".</blockquote></li>
 * <li>"{@link #getOutputEncodingOptionLongName outputEncodingOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "output-encoding".</blockquote></li>
 * <li>"{@link #getOutputEncodingOptionShortName outputEncodingOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "oe".</blockquote></li>
 * <li>"{@link #getProfileOptionLongName profileOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "profile".</blockquote></li>
 * <li>"{@link #getProfileOptionShortName profileOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "p".</blockquote></li>
 * <li>"{@link #getResourceDirectoryOptionLongName resourceDirectoryOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "resource-dir".</blockquote></li>
 * <li>"{@link #getResourceDirectoryOptionShortName resourceDirectoryOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "rd".</blockquote></li>
 * <li>"{@link #getSourceDirectoryOptionLongName sourceDirectoryOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "source-dir".</blockquote></li>
 * <li>"{@link #getSourceDirectoryOptionShortName sourceDirectoryOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "sd".</blockquote></li>
 * <li>"{@link #getTemplateEncodingOptionLongName templateEncodingOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "template-encoding".</blockquote></li>
 * <li>"{@link #getTemplateEncodingOptionShortName templateEncodingOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "te".</blockquote></li>
 * <li>"{@link #getVerboseOptionLongName verboseOptionLongName}"<blockquote>
 * Property of type {@code java.lang.String} with value "verbose".</blockquote></li>
 * <li>"{@link #getVerboseOptionShortName verboseOptionShortName}"<blockquote>
 * Property of type {@code java.lang.String} with value "v".</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 applying to Multiton scope bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getApplicationTitleMessage applicationTitle}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>JOMC Version 1.0-alpha-1-SNAPSHOT Build 2009-08-01T07:33:42+0000</pre></td></tr>
 * </table>
 * <li>"{@link #getBuildDirectoryOptionMessage buildDirectoryOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Work directory of the process.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Arbeitsverzeichnis des Vorgangs.</pre></td></tr>
 * </table>
 * <li>"{@link #getBuildDirectoryOptionArgNameMessage buildDirectoryOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>directory</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis</pre></td></tr>
 * </table>
 * <li>"{@link #getClasspathElementMessage classpathElement}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Classpath element: ''{0}''</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Klassenpfad-Element: ''{0}''</pre></td></tr>
 * </table>
 * <li>"{@link #getClasspathOptionMessage classpathOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Classpath elements separated by '':''. If starting with a ''@'' character, a file name of a file holding classpath elements.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Klassenpfad-Elemente mit '':'' getrennt. Wenn mit ''@'' beginnend, Dateiname einer Textdatei mit Klassenpfad-Elementen.</pre></td></tr>
 * </table>
 * <li>"{@link #getClasspathOptionArgNameMessage classpathOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>elements</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Elemente</pre></td></tr>
 * </table>
 * <li>"{@link #getDebugOptionMessage debugOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Enables debug output.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktiviert Diagnose-Ausgaben.</pre></td></tr>
 * </table>
 * <li>"{@link #getDescriptionMessage description}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Generates Java resource bundles.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Generiert Java Ressource-Bündel.</pre></td></tr>
 * </table>
 * <li>"{@link #getDocumentFileMessage documentFile}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Document file: ''{0}''</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Dokument-Datei: ''{0}''</pre></td></tr>
 * </table>
 * <li>"{@link #getDocumentLocationOptionMessage documentLocationOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Location of classpath documents.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ort der Klassenpfad-Dokumente.</pre></td></tr>
 * </table>
 * <li>"{@link #getDocumentLocationOptionArgNameMessage documentLocationOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>location</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ort</pre></td></tr>
 * </table>
 * <li>"{@link #getDocumentsOptionMessage documentsOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Document filenames separated by '':''. If starting with a ''@'' character, a file name of a file holding document filenames.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Dokument-Dateinamen mit '':'' getrennt. Wenn mit ''@'' beginnend, Dateiname einer Textdatei mit Dokument-Dateinamen.</pre></td></tr>
 * </table>
 * <li>"{@link #getDocumentsOptionArgNameMessage documentsOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>files</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Dateien</pre></td></tr>
 * </table>
 * <li>"{@link #getLanguageOptionMessage languageOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Default language (defaults to ''{0}'').</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Standard-Sprache (Standard ''{0}'').</pre></td></tr>
 * </table>
 * <li>"{@link #getLanguageOptionArgNameMessage languageOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>language</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Sprache</pre></td></tr>
 * </table>
 * <li>"{@link #getModuleNameOptionMessage moduleNameOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Name of the module to process.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Name des zu verarbeitenden Moduls.</pre></td></tr>
 * </table>
 * <li>"{@link #getModuleNameOptionArgNameMessage moduleNameOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>name</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Name</pre></td></tr>
 * </table>
 * <li>"{@link #getOutputEncodingOptionMessage outputEncodingOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Output encoding.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ausgabekodierung.</pre></td></tr>
 * </table>
 * <li>"{@link #getOutputEncodingOptionArgNameMessage outputEncodingOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>encoding.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Kodierung.</pre></td></tr>
 * </table>
 * <li>"{@link #getProfileOptionMessage profileOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Templates profile to use.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Zu verwendendes Vorlagen-Profil.</pre></td></tr>
 * </table>
 * <li>"{@link #getProfileOptionArgNameMessage profileOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>profile</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Profil</pre></td></tr>
 * </table>
 * <li>"{@link #getResourceDirectoryOptionMessage resourceDirectoryOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Directory to write resource files to.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis in das Ressource-Dateien geschrieben werden sollen.</pre></td></tr>
 * </table>
 * <li>"{@link #getResourceDirectoryOptionArgNameMessage resourceDirectoryOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>directory</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis</pre></td></tr>
 * </table>
 * <li>"{@link #getSeparatorMessage separator}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>--------------------------------------------------------------------------------</pre></td></tr>
 * </table>
 * <li>"{@link #getSourceDirectoryOptionMessage sourceDirectoryOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Directory to write source files to.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis in das Quelltext-Dateien geschrieben werden sollen.</pre></td></tr>
 * </table>
 * <li>"{@link #getSourceDirectoryOptionArgNameMessage sourceDirectoryOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>directory</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis</pre></td></tr>
 * </table>
 * <li>"{@link #getTemplateEncodingOptionMessage templateEncodingOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Template encoding.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Vorlagenkodierung.</pre></td></tr>
 * </table>
 * <li>"{@link #getTemplateEncodingOptionArgNameMessage templateEncodingOptionArgName}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>encoding.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Kodierung.</pre></td></tr>
 * </table>
 * <li>"{@link #getVerboseOptionMessage verboseOption}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Enables verbose output.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktiviert ausführliche Ausgaben.</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
)
// SECTION-END
public class GenerateJavaBundlesCommand
    extends AbstractJomcCommand
    implements org.jomc.cli.Command
{
    // SECTION-START[Command]

    private Options options;

    @Override
    public Options getOptions()
    {
        if ( this.options == null )
        {
            this.options = super.getOptions();
            this.options.addOption( this.getSourceDirectoryOption() );
            this.options.addOption( this.getResourceDirectoryOption() );
            this.options.addOption( this.getLanguageOption() );
            this.options.addOption( this.getProfileOption() );
            this.options.addOption( this.getTemplateEncodingOption() );
            this.options.addOption( this.getOutputEncodingOption() );
        }

        return this.options;
    }

    public int execute( final PrintStream printStream, final CommandLine commandLine )
    {
        int status = super.execute( commandLine, printStream );

        final boolean verbose = commandLine.hasOption( this.getVerboseOption().getOpt() );
        final boolean debug = commandLine.hasOption( this.getDebugOption().getOpt() );

        try
        {
            final JavaBundles tool = new JavaBundles();
            this.configureTool( tool, commandLine, printStream, true );
            tool.setModuleName( commandLine.getOptionValue( this.getModuleNameOption().getOpt() ) );
            if ( commandLine.hasOption( this.getLanguageOption().getOpt() ) )
            {
                tool.setDefaultLocale( new Locale( commandLine.getOptionValue( this.getLanguageOption().getOpt() ) ) );
            }
            if ( commandLine.hasOption( this.getProfileOption().getOpt() ) )
            {
                tool.setProfile( commandLine.getOptionValue( this.getProfileOption().getOpt() ) );
            }
            if ( commandLine.hasOption( this.getTemplateEncodingOption().getOpt() ) )
            {
                tool.setTemplateEncoding( commandLine.getOptionValue( this.getTemplateEncodingOption().getOpt() ) );
            }
            if ( commandLine.hasOption( this.getOutputEncodingOption().getOpt() ) )
            {
                tool.setOutputEncoding( commandLine.getOptionValue( this.getOutputEncodingOption().getOpt() ) );
            }

            tool.writeModuleBundles( new File( commandLine.getOptionValue( this.getSourceDirectoryOption().getOpt() ) ),
                                     new File( commandLine.getOptionValue( this.getResourceDirectoryOption().getOpt() ) ) );

        }
        catch ( ModelException e )
        {
            for ( ModelException.Detail d : e.getDetails() )
            {
                this.log( d.getLevel(), d.getMessage(), null, printStream, verbose, debug );
            }

            this.log( Level.SEVERE, e.getMessage(), e, printStream, verbose, debug );
            status = STATUS_FAILURE;
        }
        catch ( Throwable t )
        {
            this.log( Level.SEVERE, t.getMessage(), t, printStream, verbose, debug );
            status = STATUS_FAILURE;
        }

        this.log( Level.INFO, this.getSeparatorMessage( this.getLocale() ), null, printStream, verbose, debug );

        return status;
    }

    // SECTION-END
    // SECTION-START[GenerateJavaBundlesCommand]
    private Option sourceDirectoryOption;

    private Option resourceDirectoryOption;

    private Option languageOption;

    private Option profileOption;

    private Option templateEncodingOption;

    private Option outputEncodingOption;

    public Option getSourceDirectoryOption()
    {
        if ( this.sourceDirectoryOption == null )
        {
            this.sourceDirectoryOption = new Option( this.getSourceDirectoryOptionShortName(),
                                                     this.getSourceDirectoryOptionLongName(), true,
                                                     this.getSourceDirectoryOptionMessage( this.getLocale() ) );

            this.sourceDirectoryOption.setRequired( true );
            this.sourceDirectoryOption.setArgName( this.getSourceDirectoryOptionArgNameMessage( this.getLocale() ) );
        }

        return this.sourceDirectoryOption;
    }

    public Option getResourceDirectoryOption()
    {
        if ( this.resourceDirectoryOption == null )
        {
            this.resourceDirectoryOption = new Option( this.getResourceDirectoryOptionShortName(),
                                                       this.getResourceDirectoryOptionLongName(), true,
                                                       this.getResourceDirectoryOptionMessage( this.getLocale() ) );

            this.resourceDirectoryOption.setRequired( true );
            this.resourceDirectoryOption.setArgName( this.getResourceDirectoryOptionArgNameMessage( this.getLocale() ) );
        }

        return this.resourceDirectoryOption;
    }

    public Option getLanguageOption()
    {
        if ( this.languageOption == null )
        {
            this.languageOption = new Option( this.getLanguageOptionShortName(), this.getLanguageOptionLongName(),
                                              true, this.getLanguageOptionMessage( this.getLocale(),
                                                                                   this.getLocale().getLanguage() ) );

            this.languageOption.setArgName( this.getLanguageOptionArgNameMessage( this.getLocale(), null ) );
        }

        return this.languageOption;
    }

    public Option getProfileOption()
    {
        if ( this.profileOption == null )
        {
            this.profileOption = new Option( this.getProfileOptionShortName(), this.getProfileOptionLongName(),
                                             true, this.getProfileOptionMessage( this.getLocale() ) );

            this.profileOption.setArgName( this.getProfileOptionArgNameMessage( this.getLocale() ) );
        }

        return this.profileOption;
    }

    public Option getTemplateEncodingOption()
    {
        if ( this.templateEncodingOption == null )
        {
            this.templateEncodingOption = new Option( this.getTemplateEncodingOptionShortName(),
                                                      this.getTemplateEncodingOptionLongName(), true,
                                                      this.getTemplateEncodingOptionMessage( this.getLocale() ) );

            this.templateEncodingOption.setArgName( this.getTemplateEncodingOptionArgNameMessage( this.getLocale() ) );
        }

        return this.templateEncodingOption;
    }

    public Option getOutputEncodingOption()
    {
        if ( this.outputEncodingOption == null )
        {
            this.outputEncodingOption = new Option( this.getOutputEncodingOptionShortName(),
                                                    this.getOutputEncodingOptionLongName(), true,
                                                    this.getOutputEncodingOptionMessage( this.getLocale() ) );

            this.outputEncodingOption.setArgName( this.getOutputEncodingOptionArgNameMessage( this.getLocale() ) );
        }

        return this.outputEncodingOption;
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    public GenerateJavaBundlesCommand()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code Locale} dependency.
     * <p>This method returns the "{@code default}" object of the {@code java.util.Locale} specification at specification level 1.1.</p>
     * @return The {@code Locale} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.util.Locale getLocale() throws org.jomc.ObjectManagementException
    {
        return (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager().getDependency( this, "Locale" );
    }
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code abbreviatedCommandName} property.
     * @return Abbreviated name of the command.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getAbbreviatedCommandName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "abbreviatedCommandName" );
    }

    /**
     * Gets the value of the {@code buildDirectoryOptionLongName} property.
     * @return Long name of the 'build-dir' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getBuildDirectoryOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "buildDirectoryOptionLongName" );
    }

    /**
     * Gets the value of the {@code buildDirectoryOptionShortName} property.
     * @return Name of the 'build-dir' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getBuildDirectoryOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "buildDirectoryOptionShortName" );
    }

    /**
     * Gets the value of the {@code classpathOptionLongName} property.
     * @return Long name of the 'classpath' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getClasspathOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "classpathOptionLongName" );
    }

    /**
     * Gets the value of the {@code classpathOptionShortName} property.
     * @return Name of the 'classpath' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getClasspathOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "classpathOptionShortName" );
    }

    /**
     * Gets the value of the {@code commandName} property.
     * @return Name of the command.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getCommandName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "commandName" );
    }

    /**
     * Gets the value of the {@code debugOptionLongName} property.
     * @return Long name of the 'debug' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getDebugOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "debugOptionLongName" );
    }

    /**
     * Gets the value of the {@code debugOptionShortName} property.
     * @return Name of the 'debug' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getDebugOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "debugOptionShortName" );
    }

    /**
     * Gets the value of the {@code documentLocationOptionLongName} property.
     * @return Long name of the 'document-location' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getDocumentLocationOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "documentLocationOptionLongName" );
    }

    /**
     * Gets the value of the {@code documentLocationOptionShortName} property.
     * @return Name of the 'document-location' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getDocumentLocationOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "documentLocationOptionShortName" );
    }

    /**
     * Gets the value of the {@code documentsOptionLongName} property.
     * @return Long name of the 'documents' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getDocumentsOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "documentsOptionLongName" );
    }

    /**
     * Gets the value of the {@code documentsOptionShortName} property.
     * @return Name of the 'documents' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getDocumentsOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "documentsOptionShortName" );
    }

    /**
     * Gets the value of the {@code languageOptionLongName} property.
     * @return Long name of the 'language' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getLanguageOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "languageOptionLongName" );
    }

    /**
     * Gets the value of the {@code languageOptionShortName} property.
     * @return Name of the 'language' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getLanguageOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "languageOptionShortName" );
    }

    /**
     * Gets the value of the {@code moduleNameOptionLongName} property.
     * @return Long name of the 'module' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getModuleNameOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "moduleNameOptionLongName" );
    }

    /**
     * Gets the value of the {@code moduleNameOptionShortName} property.
     * @return Name of the 'module' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getModuleNameOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "moduleNameOptionShortName" );
    }

    /**
     * Gets the value of the {@code outputEncodingOptionLongName} property.
     * @return Long name of the 'output-encoding' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getOutputEncodingOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "outputEncodingOptionLongName" );
    }

    /**
     * Gets the value of the {@code outputEncodingOptionShortName} property.
     * @return Name of the 'output-encoding' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getOutputEncodingOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "outputEncodingOptionShortName" );
    }

    /**
     * Gets the value of the {@code profileOptionLongName} property.
     * @return Long name of the 'profile' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getProfileOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "profileOptionLongName" );
    }

    /**
     * Gets the value of the {@code profileOptionShortName} property.
     * @return Name of the 'profile' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getProfileOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "profileOptionShortName" );
    }

    /**
     * Gets the value of the {@code resourceDirectoryOptionLongName} property.
     * @return Long name of the 'resource-dir' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getResourceDirectoryOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "resourceDirectoryOptionLongName" );
    }

    /**
     * Gets the value of the {@code resourceDirectoryOptionShortName} property.
     * @return Name of the 'resource-dir' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getResourceDirectoryOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "resourceDirectoryOptionShortName" );
    }

    /**
     * Gets the value of the {@code sourceDirectoryOptionLongName} property.
     * @return Long name of the 'source-dir' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getSourceDirectoryOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "sourceDirectoryOptionLongName" );
    }

    /**
     * Gets the value of the {@code sourceDirectoryOptionShortName} property.
     * @return Name of the 'source-dir' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getSourceDirectoryOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "sourceDirectoryOptionShortName" );
    }

    /**
     * Gets the value of the {@code templateEncodingOptionLongName} property.
     * @return Long name of the 'template-encoding' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getTemplateEncodingOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "templateEncodingOptionLongName" );
    }

    /**
     * Gets the value of the {@code templateEncodingOptionShortName} property.
     * @return Name of the 'template-encoding' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getTemplateEncodingOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "templateEncodingOptionShortName" );
    }

    /**
     * Gets the value of the {@code verboseOptionLongName} property.
     * @return Long name of the 'verbose' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getVerboseOptionLongName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "verboseOptionLongName" );
    }

    /**
     * Gets the value of the {@code verboseOptionShortName} property.
     * @return Name of the 'verbose' option.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private java.lang.String getVerboseOptionShortName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "verboseOptionShortName" );
    }
    // SECTION-END
    // SECTION-START[Messages]

    /**
     * Gets the text of the {@code applicationTitle} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>JOMC Version 1.0-alpha-1-SNAPSHOT Build 2009-08-01T07:33:42+0000</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code applicationTitle} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getApplicationTitleMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "applicationTitle", locale,  null );
    }

    /**
     * Gets the text of the {@code buildDirectoryOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Work directory of the process.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Arbeitsverzeichnis des Vorgangs.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code buildDirectoryOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getBuildDirectoryOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "buildDirectoryOption", locale,  null );
    }

    /**
     * Gets the text of the {@code buildDirectoryOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>directory</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code buildDirectoryOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getBuildDirectoryOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "buildDirectoryOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code classpathElement} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Classpath element: ''{0}''</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Klassenpfad-Element: ''{0}''</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param classpathElement Format argument.
     * @return The text of the {@code classpathElement} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getClasspathElementMessage( final java.util.Locale locale, final java.lang.String classpathElement ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "classpathElement", locale, new Object[] { classpathElement, null } );
    }

    /**
     * Gets the text of the {@code classpathOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Classpath elements separated by '':''. If starting with a ''@'' character, a file name of a file holding classpath elements.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Klassenpfad-Elemente mit '':'' getrennt. Wenn mit ''@'' beginnend, Dateiname einer Textdatei mit Klassenpfad-Elementen.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code classpathOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getClasspathOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "classpathOption", locale,  null );
    }

    /**
     * Gets the text of the {@code classpathOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>elements</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Elemente</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code classpathOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getClasspathOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "classpathOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code debugOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Enables debug output.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktiviert Diagnose-Ausgaben.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code debugOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getDebugOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "debugOption", locale,  null );
    }

    /**
     * Gets the text of the {@code description} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Generates Java resource bundles.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Generiert Java Ressource-Bündel.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code description} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getDescriptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "description", locale,  null );
    }

    /**
     * Gets the text of the {@code documentFile} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Document file: ''{0}''</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Dokument-Datei: ''{0}''</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param documentFile Format argument.
     * @return The text of the {@code documentFile} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getDocumentFileMessage( final java.util.Locale locale, final java.lang.String documentFile ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "documentFile", locale, new Object[] { documentFile, null } );
    }

    /**
     * Gets the text of the {@code documentLocationOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Location of classpath documents.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ort der Klassenpfad-Dokumente.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code documentLocationOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getDocumentLocationOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "documentLocationOption", locale,  null );
    }

    /**
     * Gets the text of the {@code documentLocationOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>location</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ort</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code documentLocationOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getDocumentLocationOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "documentLocationOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code documentsOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Document filenames separated by '':''. If starting with a ''@'' character, a file name of a file holding document filenames.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Dokument-Dateinamen mit '':'' getrennt. Wenn mit ''@'' beginnend, Dateiname einer Textdatei mit Dokument-Dateinamen.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code documentsOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getDocumentsOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "documentsOption", locale,  null );
    }

    /**
     * Gets the text of the {@code documentsOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>files</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Dateien</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code documentsOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getDocumentsOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "documentsOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code languageOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Default language (defaults to ''{0}'').</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Standard-Sprache (Standard ''{0}'').</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param defaultLanguage Format argument.
     * @return The text of the {@code languageOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getLanguageOptionMessage( final java.util.Locale locale, final java.lang.String defaultLanguage ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "languageOption", locale, new Object[] { defaultLanguage, null } );
    }

    /**
     * Gets the text of the {@code languageOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>language</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Sprache</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param defaultLanguage Format argument.
     * @return The text of the {@code languageOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getLanguageOptionArgNameMessage( final java.util.Locale locale, final java.lang.String defaultLanguage ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "languageOptionArgName", locale, new Object[] { defaultLanguage, null } );
    }

    /**
     * Gets the text of the {@code moduleNameOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Name of the module to process.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Name des zu verarbeitenden Moduls.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code moduleNameOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getModuleNameOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "moduleNameOption", locale,  null );
    }

    /**
     * Gets the text of the {@code moduleNameOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>name</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Name</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code moduleNameOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getModuleNameOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "moduleNameOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code outputEncodingOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Output encoding.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ausgabekodierung.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code outputEncodingOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getOutputEncodingOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "outputEncodingOption", locale,  null );
    }

    /**
     * Gets the text of the {@code outputEncodingOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>encoding.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Kodierung.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code outputEncodingOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getOutputEncodingOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "outputEncodingOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code profileOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Templates profile to use.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Zu verwendendes Vorlagen-Profil.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code profileOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getProfileOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "profileOption", locale,  null );
    }

    /**
     * Gets the text of the {@code profileOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>profile</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Profil</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code profileOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getProfileOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "profileOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code resourceDirectoryOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Directory to write resource files to.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis in das Ressource-Dateien geschrieben werden sollen.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code resourceDirectoryOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getResourceDirectoryOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "resourceDirectoryOption", locale,  null );
    }

    /**
     * Gets the text of the {@code resourceDirectoryOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>directory</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code resourceDirectoryOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getResourceDirectoryOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "resourceDirectoryOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code separator} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>--------------------------------------------------------------------------------</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code separator} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getSeparatorMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "separator", locale,  null );
    }

    /**
     * Gets the text of the {@code sourceDirectoryOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Directory to write source files to.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis in das Quelltext-Dateien geschrieben werden sollen.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code sourceDirectoryOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getSourceDirectoryOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "sourceDirectoryOption", locale,  null );
    }

    /**
     * Gets the text of the {@code sourceDirectoryOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>directory</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Verzeichnis</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code sourceDirectoryOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getSourceDirectoryOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "sourceDirectoryOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code templateEncodingOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Template encoding.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Vorlagenkodierung.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code templateEncodingOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getTemplateEncodingOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "templateEncodingOption", locale,  null );
    }

    /**
     * Gets the text of the {@code templateEncodingOptionArgName} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>encoding.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Kodierung.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code templateEncodingOptionArgName} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getTemplateEncodingOptionArgNameMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "templateEncodingOptionArgName", locale,  null );
    }

    /**
     * Gets the text of the {@code verboseOption} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Enables verbose output.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Aktiviert ausführliche Ausgaben.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code verboseOption} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-1-SNAPSHOT/jomc-tools"
    )
    private String getVerboseOptionMessage( final java.util.Locale locale ) throws org.jomc.ObjectManagementException
    {
        return org.jomc.ObjectManagerFactory.getObjectManager().getMessage( this, "verboseOption", locale,  null );
    }
    // SECTION-END
}
