// SECTION-START[License Header]
/*
 *  JOMC Logging Slf4J Logging
 *  Copyright (C) 2005 Christian Schulte <cs@schulte.it>
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 *
 *  $Id$
 */
// SECTION-END
package org.jomc.logging.ri.slf4j;

import org.jomc.logging.Logger;

// SECTION-START[Implementation Comment]
/**
 * Slf4J logging system implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.logging.Logger} {@code 1.0}<blockquote>
 * Object applies to Multiton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://jomc.sourceforge.net/jomc-tools"
)
// SECTION-END
public class Slf4JLogger implements Logger
{
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public Slf4JLogger()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code name} property.
     * @return Name of the component events are logged for.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public java.lang.String getName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "name" );
    }
    // SECTION-END
    // SECTION-START[Slf4JLogger]

    public boolean isDebugEnabled()
    {
        return this.getLogger().isDebugEnabled();
    }

    public void debug( String string )
    {
        this.getLogger().debug( string );
    }

    public void debug( Throwable throwable )
    {
        this.getLogger().debug( throwable.getMessage(), throwable );
    }

    public void debug( String message, Throwable throwable )
    {
        this.getLogger().debug( message, throwable );
    }

    public boolean isErrorEnabled()
    {
        return this.getLogger().isErrorEnabled();
    }

    public void error( String string )
    {
        this.getLogger().error( string );
    }

    public void error( Throwable throwable )
    {
        this.getLogger().error( throwable.getMessage(), throwable );
    }

    public void error( String message, Throwable throwable )
    {
        this.getLogger().error( message, throwable );
    }

    public boolean isFatalEnabled()
    {
        return this.getLogger().isErrorEnabled();
    }

    public void fatal( String string )
    {
        this.getLogger().error( string );
    }

    public void fatal( Throwable throwable )
    {
        this.getLogger().error( throwable.getMessage(), throwable );
    }

    public void fatal( String message, Throwable throwable )
    {
        this.getLogger().error( message, throwable );
    }

    public boolean isInfoEnabled()
    {
        return this.getLogger().isInfoEnabled();
    }

    public void info( String string )
    {
        this.getLogger().info( string );
    }

    public void info( Throwable throwable )
    {
        this.getLogger().info( throwable.getMessage(), throwable );
    }

    public void info( String message, Throwable throwable )
    {
        this.getLogger().info( message, throwable );
    }

    public boolean isTraceEnabled()
    {
        return this.getLogger().isTraceEnabled();
    }

    public void trace( String string )
    {
        this.getLogger().trace( string );
    }

    public void trace( Throwable throwable )
    {
        this.getLogger().trace( throwable.getMessage(), throwable );
    }

    public void trace( String message, Throwable throwable )
    {
        this.getLogger().trace( message, throwable );
    }

    public boolean isWarnEnabled()
    {
        return this.getLogger().isWarnEnabled();
    }

    public void warn( String string )
    {
        this.getLogger().warn( string );
    }

    public void warn( Throwable throwable )
    {
        this.getLogger().warn( throwable.getMessage(), throwable );
    }

    public void warn( String message, Throwable throwable )
    {
        this.getLogger().warn( message, throwable );
    }

    /**
     * Requests a Slf4J logger for the name given by property {@code name}.
     *
     * @return The Slf4J logger for the name given by property {@code name}.
     */
    public org.slf4j.Logger getLogger()
    {
        return org.slf4j.LoggerFactory.getLogger( this.getName() );
    }

    // SECTION-END
}
