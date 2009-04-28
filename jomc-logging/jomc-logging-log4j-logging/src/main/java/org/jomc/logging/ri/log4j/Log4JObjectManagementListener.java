// SECTION-START[License Header]
/*
 *  JOMC Logging Log4J Logging
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
package org.jomc.logging.ri.log4j;

import java.util.logging.Level;

// SECTION-START[Implementation Comment]
/**
 * {@code ObjectManagementLister} backed by Log4J logging.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.spi.Listener} {@code 1.0}<blockquote>
 * Object applies to Singleton scope.
 * State must be retained across operations to operate as specified.</blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLogger Logger}"<blockquote>
 * Dependency on {@code org.jomc.logging.Logger} at specification level 1.0 applying to Multiton scope bound to an instance.</blockquote></li>
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
public class Log4JObjectManagementListener
implements
    org.jomc.spi.Listener
{
    // SECTION-START[Log4JObjectManagementListener]

    public void onLog( final Level level, final String message, final Throwable throwable )
    {
        if ( level != null )
        {
            if ( level.equals( Level.CONFIG ) || level.equals( Level.FINE ) )
            {
                this.getLogger().debug( message, throwable );
            }
            else if ( level.equals( Level.FINER ) || level.equals( Level.FINEST ) )
            {
                this.getLogger().trace( message, throwable );
            }
            else if ( level.equals( Level.INFO ) )
            {
                this.getLogger().info( message, throwable );
            }
            else if ( level.equals( Level.SEVERE ) )
            {
                this.getLogger().error( message, throwable );
            }
            else if ( level.equals( Level.WARNING ) )
            {
                this.getLogger().warn( message, throwable );
            }
            else
            {
                this.getLogger().trace( message, throwable );
            }
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public Log4JObjectManagementListener()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]

    /**
     * Gets the {@code Logger} dependency.
     * <p>This method returns the "{@code JOMC Logging Log4J Logging}" object of the {@code org.jomc.logging.Logger} specification at specification level 1.0.</p>
     * <p><b>Properties</b><dl>
     * <dt>"{@code name}"</dt>
     * <dd>Property of type {@code java.lang.String} with value "org.jomc.ObjectManager".
     * </dd>
     * </dl>
     * @return The {@code Logger} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private org.jomc.logging.Logger getLogger() throws org.jomc.ObjectManagementException
    {
        return (org.jomc.logging.Logger) org.jomc.ObjectManager.getInstance().getDependency( this, "Logger" );
    }
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
