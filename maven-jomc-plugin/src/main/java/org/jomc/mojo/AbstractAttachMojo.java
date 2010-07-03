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
package org.jomc.mojo;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

/**
 * Base class for attaching artifacts to a project.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $Id$
 */
public abstract class AbstractAttachMojo extends AbstractMojo
{

    /**
     * The Maven project of the instance.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject mavenProject;

    /**
     * Maven ProjectHelper.
     *
     * @component
     * @required
     * @readonly
     */
    private MavenProjectHelper projectHelper;

    protected abstract File getArtifactFile();

    protected abstract String getArtifactClassifier();

    protected abstract String getArtifactType();

    protected abstract long getArtifactTimeoutMillis();

    public void execute() throws MojoExecutionException, MojoFailureException
    {
        File outputDirectory = new File( this.mavenProject.getBuild().getDirectory() );
        if ( !outputDirectory.isAbsolute() )
        {
            outputDirectory = new File( this.mavenProject.getBasedir(), this.mavenProject.getBuild().getDirectory() );
        }

        final File attachment = new File( outputDirectory, this.getArtifactClassifier() + "-"
                                                           + ManagementFactory.getRuntimeMXBean().getStartTime() + "."
                                                           + this.getArtifactType() );

        try
        {
            if ( this.getArtifactFile().exists() )
            {
                if ( !attachment.exists()
                     || attachment.lastModified() + this.getArtifactTimeoutMillis() < System.currentTimeMillis() )
                {
                    if ( attachment.exists() && !attachment.delete() )
                    {
                        this.getLog().warn( getMessage( "failedDeletingFile", attachment.getAbsolutePath() ) );
                    }

                    FileUtils.copyFile( this.getArtifactFile(), attachment, false );
                    this.projectHelper.attachArtifact( this.mavenProject, this.getArtifactType(),
                                                       this.getArtifactClassifier(), attachment );

                }
                else if ( this.getLog().isInfoEnabled() )
                {
                    this.getLog().info( getMessage( "alreadyAttached", getArtifactFile().getAbsolutePath() ) );
                }
            }
            else if ( this.getLog().isWarnEnabled() )
            {
                this.getLog().warn( getMessage( "artifactFileNotFound", this.getArtifactFile().getAbsolutePath() ) );
            }
        }
        catch ( final IOException e )
        {
            throw new MojoExecutionException( getMessage( "failedCopying", this.getArtifactFile().getAbsolutePath(),
                                                          attachment.getAbsolutePath(),
                                                          e.getMessage() != null ? e.getMessage() : "" ), e );

        }
    }

    private static String getMessage( final String key, final Object... args )
    {
        return MessageFormat.format( ResourceBundle.getBundle(
            AbstractAttachMojo.class.getName().replace( '.', '/' ) ).getString( key ), args );

    }

}
