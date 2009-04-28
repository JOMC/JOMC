// SECTION-START[License Header]
/*
 *  JOMC Logging Commons Logging
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
package org.jomc.logging.ri.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// SECTION-START[Implementation Comment]
/**
 * Commons logging system implementation.
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
public class CommonsLogger
    implements
    org.jomc.logging.Logger
{
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public CommonsLogger()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[CommonsLogger]

    public boolean isDebugEnabled()
    {
        return this.getLogger().isDebugEnabled();
    }

    public void debug( String message )
    {
        this.getLogger().debug( message );
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

    public void error( String message )
    {
        this.getLogger().error( message );
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
        return this.getLogger().isFatalEnabled();
    }

    public void fatal( String message )
    {
        this.getLogger().fatal( message );
    }

    public void fatal( Throwable throwable )
    {
        this.getLogger().fatal( throwable.getMessage(), throwable );
    }

    public void fatal( String message, Throwable throwable )
    {
        this.getLogger().fatal( message, throwable );
    }

    public boolean isInfoEnabled()
    {
        return this.getLogger().isInfoEnabled();
    }

    public void info( String message )
    {
        this.getLogger().info( message );
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

    public void trace( String message )
    {
        this.getLogger().trace( message );
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

    public void warn( String message )
    {
        this.getLogger().warn( message );
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
     * Requests a commons logging logger for the name given by property
     * {@code name}.
     *
     * @return the commons logging logger for the name given by property
     * {@code name}.
     */
    public Log getLogger()
    {
        return LogFactory.getLog( this.getName() );
    }

    // SECTION-END
    // SECTION-START[Dependencies]
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
    // SECTION-START[Messages]
    // SECTION-END
}
