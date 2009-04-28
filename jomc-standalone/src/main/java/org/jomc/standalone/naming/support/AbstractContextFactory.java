// SECTION-START[License Header]
/*
 *  JOMC Standalone
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
package org.jomc.standalone.naming.support;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jomc.standalone.Environment;

// SECTION-START[Implementation Comment]
/**
 * Base context factory.
 * <p><b>Specifications</b><ul>
 * <li>{@code javax.naming.spi.InitialContextFactory}<blockquote>
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
public abstract class AbstractContextFactory
    implements
    javax.naming.spi.InitialContextFactory
{
    // SECTION-START[AbstractContextFactory]

    private Environment environment;

    protected Environment getEnvironment()
    {
        if ( this.environment == null )
        {
            this.environment = new Environment();
        }

        return this.environment;
    }

    protected Context getContext() throws NamingException
    {
        return new InitialContext();
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public AbstractContextFactory()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
