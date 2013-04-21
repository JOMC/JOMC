// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Java Object Management and Configuration
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

import java.util.Locale;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Manages objects.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ObjectManager</dd>
 *   <dt><b>Multiplicity:</b></dt><dd>One</dd>
 *   <dt><b>Scope:</b></dt><dd>Singleton</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 1.0
 * @see org.jomc.ObjectManager#getObject(java.lang.Class) getObject(ObjectManager.class)
 * @see org.jomc.ObjectManager#getObject(java.lang.Class,java.lang.String) getObject(ObjectManager.class, "<i>implementation name</i>")
 * @see org.jomc.ObjectManagerFactory
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.6-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.6/jomc-tools-1.6-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public interface ObjectManager
{
    // SECTION-START[ObjectManager]

    /**
     * Gets an instance of an implementation of a specification.
     * <p><b>Note:</b><br/>
     * Implementations must use the class loader associated with the given class as returned by method
     * {@link Class#getClassLoader() specification.getClassLoader()} for loading classes. Only if that method returns
     * {@code null}, indicating the class has been loaded by the bootstrap class loader, use of the bootstrap class
     * loader is recommended.</p>
     *
     * @param <T> The type of the instance.
     * @param specification The specification class to return an implementation instance of.
     *
     * @return An instance of an implementation of the specification class {@code specification} or {@code null}, if no
     * such instance is available.
     *
     * @throws NullPointerException if {@code specification} is {@code null}.
     * @throws ObjectManagementException if getting the object fails.
     */
    <T> T getObject( Class<T> specification )
        throws NullPointerException, ObjectManagementException;

    /**
     * Gets an instance of an implementation of a specification.
     * <p><b>Note:</b><br/>
     * Implementations must use the class loader associated with the given class as returned by method
     * {@link Class#getClassLoader() specification.getClassLoader()} for loading classes. Only if that method returns
     * {@code null}, indicating the class has been loaded by the bootstrap class loader, use of the bootstrap class
     * loader is recommended.</p>
     *
     * @param <T> The type of the instance.
     * @param specification The specification class to return an implementation instance of.
     * @param implementationName The name of the implementation to return an instance of.
     *
     * @return An instance of the implementation named {@code implementationName} of the specification class
     * {@code specification} or {@code null}, if no such instance is available.
     *
     * @throws NullPointerException if {@code specification} or {@code implementationName} is {@code null}.
     * @throws ObjectManagementException if getting the object fails.
     */
    <T> T getObject( Class<T> specification, String implementationName )
        throws NullPointerException, ObjectManagementException;

    /**
     * Gets an instance of a dependency of an object.
     * <p><b>Note:</b><br/>
     * Implementations must use the class loader associated with the class of the given object as returned by method
     * {@link Class#getClassLoader() object.getClass().getClassLoader()} for loading classes. Only if that method
     * returns {@code null}, indicating the class has been loaded by the bootstrap class loader, use of the bootstrap
     * class loader is recommended.</p>
     *
     * @param object The object to return a dependency instance of.
     * @param dependencyName The name of the dependency of {@code object} to return an instance of.
     *
     * @return An instance of the dependency named {@code dependencyName} of {@code object} or {@code null}, if no such
     * instance is available.
     *
     * @throws NullPointerException if {@code object} or {@code dependencyName} is {@code null}.
     * @throws ObjectManagementException if getting the dependency instance fails.
     */
    Object getDependency( Object object, String dependencyName )
        throws NullPointerException, ObjectManagementException;

    /**
     * Gets an instance of a property of an object.
     * <p><b>Note:</b><br/>
     * Implementations must use the class loader associated with the class of the given object as returned by method
     * {@link Class#getClassLoader() object.getClass().getClassLoader()} for loading classes. Only if that method
     * returns {@code null}, indicating the class has been loaded by the bootstrap class loader, use of the bootstrap
     * class loader is recommended.</p>
     *
     * @param object The object to return a property instance of.
     * @param propertyName The name of the property of {@code object} to return an instance of.
     *
     * @return An instance of the property named {@code propertyName} of {@code object} or {@code null}, if no such
     * instance is available.
     *
     * @throws NullPointerException if {@code object} or {@code propertyName} is {@code null}.
     * @throws ObjectManagementException if getting the property instance fails.
     */
    Object getProperty( Object object, String propertyName )
        throws NullPointerException, ObjectManagementException;

    /**
     * Gets an instance of a message of an object.
     * <p><b>Note:</b><br/>
     * Implementations must use the class loader associated with the class of the given object as returned by method
     * {@link Class#getClassLoader() object.getClass().getClassLoader()} for loading classes. Only if that method
     * returns {@code null}, indicating the class has been loaded by the bootstrap class loader, use of the bootstrap
     * class loader is recommended.</p>
     *
     * @param object The object to return a message instance of.
     * @param messageName The name of the message of {@code object} to return an instance of.
     * @param locale The locale of the message instance to return.
     * @param arguments Arguments to format the message instance with.
     *
     * @return An instance of the message named {@code messageName} of {@code object} formatted with {@code arguments}
     * for {@code locale} or {@code null}, if no such instance is available.
     *
     * @throws NullPointerException if {@code object}, {@code messageName} or {@code locale} is {@code null}.
     * @throws ObjectManagementException if getting the message instance fails.
     */
    String getMessage( Object object, String messageName, Locale locale, Object... arguments )
        throws NullPointerException, ObjectManagementException;

    // SECTION-END
}
