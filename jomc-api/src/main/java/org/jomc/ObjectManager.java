// SECTION-START[License Header]
/*
 *  JOMC API
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
package org.jomc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Locale;

// SECTION-START[Specification Comment]
/**
 * Manages objects.
 * <p>This specification applies to Singleton scope.
 * An application assembler is required to provide exactly one implementation of this specification. Use of class
 * {@link org.jomc.ObjectManager ObjectManager} is supported for getting that implementation.<pre>
 * ObjectManager object = (ObjectManager) ObjectManager.getInstance().getObject( ObjectManager.class );
 * </pre></p>
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
public abstract class ObjectManager
{
    // SECTION-START[Object Manager]

    /** Constant for the default classname providing the {@code getInstance()} and {@code newInstance()} methods. */
    private static final String DEFAULT_FACTORY_CLASS = "org.jomc.ri.DefaultObjectManager";

    /** Constant for the classname of the default {@code ObjectManager} implementation. */
    private static final String DEFAULT_IMPLEMENTATION = "org.jomc.ri.DefaultObjectManager";

    private static final Class[] EMPTY = new Class[]
    {
    };

    /**
     * Gets an object of an implementation of a specification.
     * <p>When creating objects, use of the classloader associated with the given class, as returned by method
     * {@link Class#getClassLoader()}, is recommended. Only if that method returns {@code null}, indicating the
     * class has been loaded by the bootstrap classloader, use of the bootstrap classloader is recommended.</p>
     *
     * @param specification The specification class to return an implementation object of.
     *
     * @return An object of an implementation of the specification class {@code specification} or {@code null} if
     * nothing could be resolved.
     *
     * @throws NullPointerException if {@code specification} is {@code null}.
     * @throws ObjectManagementException if getting the object fails.
     */
    public abstract Object getObject( Class specification ) throws ObjectManagementException;

    /**
     * Gets an object of an implementation of a specification.
     * <p>When creating objects, use of the classloader associated with the given class, as returned by method
     * {@link Class#getClassLoader()}, is recommended. Only if that method returns {@code null}, indicating the
     * class has been loaded by the bootstrap classloader, use of the bootstrap classloader is recommended.</p>
     *
     * @param specification The specification class to return an implementation object of.
     * @param implementationName The name of the implementation to return an object of.
     *
     * @return An object of the implementation named {@code implementationName} of the specification class
     * {@code specification} or {@code null} if nothing could be resolved.
     *
     * @throws NullPointerException if {@code specification} or {@code implementationName} is {@code null}.
     * @throws ObjectManagementException if getting the object fails.
     */
    public abstract Object getObject( Class specification, String implementationName ) throws ObjectManagementException;

    /**
     * Gets an instance of a dependency of an object.
     * <p>When creating objects, use of the classloader associated with the class of the given object, as returned by
     * method {@link Class#getClassLoader()}, is recommended. Only if that method returns {@code null}, indicating the
     * class has been loaded by the bootstrap classloader, use of the bootstrap classloader is recommended.</p>
     *
     * @param object The object to return a dependency instance of.
     * @param dependencyName The name of the dependency of {@code object} to return an instance of.
     *
     * @return An instance of the dependency named {@code dependencyName} of {@code object} or {@code null} if nothing
     * could be resolved.
     *
     * @throws NullPointerException if {@code object} or {@code dependencyName} is {@code null}.
     * @throws ObjectManagementException if getting the dependency instance fails.
     */
    public abstract Object getDependency( Object object, String dependencyName ) throws ObjectManagementException;

    /**
     * Gets an instance of a property of an object.
     * <p>When creating objects, use of the classloader associated with the class of the given object, as returned by
     * method {@link Class#getClassLoader()}, is recommended. Only if that method returns {@code null}, indicating the
     * class has been loaded by the bootstrap classloader, use of the bootstrap classloader is recommended.</p>
     *
     * @param object The object to return a property instance of.
     * @param propertyName The name of the property of {@code object} to return an instance of.
     *
     * @return An instance of the property named {@code propertyName} of {@code object} or {@code null} if nothing
     * could be resolved.
     *
     * @throws NullPointerException if {@code object} or {@code propertyName} is {@code null}.
     * @throws ObjectManagementException if getting the property instance fails.
     */
    public abstract Object getProperty( Object object, String propertyName ) throws ObjectManagementException;

    /**
     * Gets an instance of a message of an object for a given locale.
     * <p>When creating objects, use of the classloader associated with the class of the given object, as returned by
     * method {@link Class#getClassLoader()}, is recommended. Only if that method returns {@code null}, indicating the
     * class has been loaded by the bootstrap classloader, use of the bootstrap classloader is recommended.</p>
     *
     * @param object The object to return a message instance of.
     * @param messageName The name of the message of {@code object} to return an instance of.
     * @param locale The locale of the message instance to return.
     * @param arguments Arguments to format the message instance with or {@code null}.
     *
     * @return An instance of the message named {@code messageName} of {@code object} formatted with {@code arguments}
     * for {@code locale} or {@code null} if nothing could be resolved.
     *
     * @throws NullPointerException if {@code object}, {@code messageName} or {@code locale} is {@code null}.
     * @throws ObjectManagementException if getting the message instance fails.
     */
    public abstract String getMessage( Object object, String messageName, Locale locale, Object arguments )
        throws ObjectManagementException;

    /**
     * Gets the singleton instance of this class.
     * <p>This method is controlled by system property {@code org.jomc.ObjectManager.factoryClass} providing
     * the name of a class declaring a {@code public static ObjectManager getInstance()} method used to get
     * the instance.</p>
     * <p>The {@code newInstance} method should be used by {@code getInstance} implementors to retrieve a new
     * {@code ObjectManager} implementation.</p>
     *
     * @return The singleton instance of this class.
     *
     * @see ObjectManager#newInstance()
     *
     * @throws ObjectManagementException if getting the singleton instance fails.
     */
    public static ObjectManager getInstance() throws ObjectManagementException
    {
        final String factory =
            System.getProperty( ObjectManager.class.getName() + ".factoryClass", DEFAULT_FACTORY_CLASS );

        try
        {
            final Class factoryClass = Class.forName( factory );
            final Method factoryMethod = factoryClass.getMethod( "getInstance", EMPTY );
            return (ObjectManager) factoryMethod.invoke( null, (Object[]) EMPTY );
        }
        catch ( Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
    }

    /**
     * Creates a new {@code ObjectManager} instance.
     * <p>The object manager implementation returned by this method is controlled by system property
     * {@code org.jomc.ObjectManager.implementationClass} set to a class name to be loaded as the object
     * manager implementation to return.</p>
     *
     * @return A new {@code ObjectManager} instance.
     *
     * @throws ObjectManagementException if creating a new {@code ObjectManager} instance fails.
     */
    public static ObjectManager newInstance() throws ObjectManagementException
    {
        final String impl =
            System.getProperty( ObjectManager.class.getName() + ".implementationClass", DEFAULT_IMPLEMENTATION );

        try
        {
            return (ObjectManager) Class.forName( impl ).newInstance();
        }
        catch ( Exception e )
        {
            throw new ObjectManagementException( e.getMessage(), e );
        }
    }

    // SECTION-END
}
