// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
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
package org.jomc;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Factory for the {@code ObjectManager} singleton.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.ObjectManagerFactory>}.
 *   It does not provide any specified objects due to flag {@code <abstract>}.
 *   This implementation is flagged the {@code <final>} node in an inheritance hierarchy.
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 1.0
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
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
        return AccessController.doPrivileged( new PrivilegedAction<ObjectManager>()
        {

            public ObjectManager run()
            {
                final String factory = System.getProperty( SYS_FACTORY_CLASSNAME, DEFAULT_FACTORY_CLASSNAME );

                try
                {
                    final Class<?> factoryClass = Class.forName( factory, true, classLoader );
                    final Method factoryMethod = factoryClass.getMethod( "getObjectManager", ClassLoader.class );
                    return (ObjectManager) factoryMethod.invoke( null, classLoader );
                }
                catch ( final Exception e )
                {
                    throw new ObjectManagementException( getMessage( e ), e );
                }
            }

        } );
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
        return AccessController.doPrivileged( new PrivilegedAction<ObjectManager>()
        {

            public ObjectManager run()
            {
                final String implementation =
                    System.getProperty( SYS_IMPLEMENTATION_CLASSNAME, DEFAULT_IMPLEMENTATION_CLASSNAME );

                try
                {
                    return Class.forName( implementation, true, classLoader ).asSubclass( ObjectManager.class ).
                        newInstance();

                }
                catch ( final Exception e )
                {
                    throw new ObjectManagementException( getMessage( e ), e );
                }
            }

        } );
    }

    private static String getMessage( final Throwable t )
    {
        return t != null ? t.getMessage() != null ? t.getMessage() : getMessage( t.getCause() ) : null;
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code ObjectManagerFactory} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
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
    // SECTION-END
}
