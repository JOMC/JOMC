// SECTION-START[License Header]
/*
 *  JOMC Logging JDK Logging
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
package org.jomc.logging.ri.jdk;

import java.util.logging.Level;

// SECTION-START[Implementation Comment]
/**
 * JDK logging system implementation.
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

// SECTION-END
public class JdkLogger
    implements
    org.jomc.logging.Logger
{
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public JdkLogger()
    {
        // SECTION-START[Default Constructor]
        super();
    // SECTION-END
    }
    // SECTION-END
    // SECTION-START[JdkLogger]

    public boolean isDebugEnabled()
    {
        return this.getLogger().isLoggable( Level.FINE );
    }

    public void debug( String message )
    {
        this.log( Level.FINE, message, null );
    }

    public void debug( Throwable throwable )
    {
        this.log( Level.FINE, throwable.getMessage(), throwable );
    }

    public void debug( String message, Throwable throwable )
    {
        this.log( Level.FINE, message, throwable );
    }

    public boolean isErrorEnabled()
    {
        return this.getLogger().isLoggable( Level.SEVERE );
    }

    public void error( String message )
    {
        this.log( Level.SEVERE, message, null );
    }

    public void error( Throwable throwable )
    {
        this.log( Level.SEVERE, throwable.getMessage(), throwable );
    }

    public void error( String message, Throwable throwable )
    {
        this.log( Level.SEVERE, message, throwable );
    }

    public boolean isFatalEnabled()
    {
        return this.getLogger().isLoggable( Level.SEVERE );
    }

    public void fatal( String message )
    {
        this.log( Level.SEVERE, message, null );
    }

    public void fatal( Throwable throwable )
    {
        this.log( Level.SEVERE, throwable.getMessage(), throwable );
    }

    public void fatal( String message, Throwable throwable )
    {
        this.log( Level.SEVERE, message, throwable );
    }

    public boolean isInfoEnabled()
    {
        return this.getLogger().isLoggable( Level.INFO );
    }

    public void info( String message )
    {
        this.log( Level.INFO, message, null );
    }

    public void info( Throwable throwable )
    {
        this.log( Level.INFO, throwable.getMessage(), throwable );
    }

    public void info( String message, Throwable throwable )
    {
        this.log( Level.INFO, message, throwable );
    }

    public boolean isTraceEnabled()
    {
        return this.getLogger().isLoggable( Level.FINEST );
    }

    public void trace( String message )
    {
        this.log( Level.FINEST, message, null );
    }

    public void trace( Throwable throwable )
    {
        this.log( Level.FINEST, throwable.getMessage(), throwable );
    }

    public void trace( String message, Throwable throwable )
    {
        this.log( Level.FINEST, message, throwable );
    }

    public boolean isWarnEnabled()
    {
        return this.getLogger().isLoggable( Level.WARNING );
    }

    public void warn( String message )
    {
        this.log( Level.WARNING, message, null );
    }

    public void warn( Throwable throwable )
    {
        this.log( Level.WARNING, throwable.getMessage(), throwable );
    }

    public void warn( String message, Throwable throwable )
    {
        this.log( Level.WARNING, message, throwable );
    }

    /**
     * Requests the JDK logger for the name given by property {@code name}.
     *
     * @return the JDK logger for the name given by property {@code name}.
     */
    public java.util.logging.Logger getLogger()
    {
        return java.util.logging.Logger.getLogger( this.getName() );
    }

    private void log( final Level level, final String msg, final Throwable t )
    {
        if ( this.getLogger().isLoggable( level ) )
        {
            StackTraceElement caller;
            final Throwable x = new Throwable();
            final StackTraceElement[] elements = x.getStackTrace();

            String cname = "unknown";
            String method = "unknown";

            if ( elements != null && elements.length > 2 )
            {
                caller = elements[2];
                cname = caller.getClassName();
                method = caller.getMethodName();
            }

            if ( t == null )
            {
                this.getLogger().logp( level, cname, method, msg );
            }
            else
            {
                this.getLogger().logp( level, cname, method, msg, t );
            }
        }
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
        comments = "See http://www.jomc.org/jomc-tools"
    )
    public java.lang.String getName() throws org.jomc.ObjectManagementException
    {
        return (java.lang.String) org.jomc.ObjectManager.getInstance().getProperty( this, "name" );
    }
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
