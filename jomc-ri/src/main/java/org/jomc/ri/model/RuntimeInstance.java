// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) Christian Schulte, 2011-313
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
package org.jomc.ri.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;
import org.jomc.model.Instance;
import org.jomc.model.Specification;
import org.jomc.util.WeakIdentityHashMap;
import static org.jomc.ri.model.RuntimeModelObjects.BOOTSTRAP_CLASSLOADER_KEY;
import static org.jomc.ri.model.RuntimeModelObjects.classesByClassLoaderAndNameCache;
import static org.jomc.ri.model.RuntimeModelObjects.createMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Instance}.
 *
 * <p>
 *   This implementation is identified by {@code <org.jomc.ri.model.RuntimeInstance>}.
 * </p>
 * <p>
 *   It provides objects named {@code <JOMC RI RuntimeInstance>} of the following specifications:
 *
 *   <ul>
 *     <li>{@code <org.jomc.ri.model.RuntimeModelObject>} at specification level 1.2.</li>
 *   </ul>
 * </p>
 * <dl>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.2
 * @version 1.2
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class RuntimeInstance extends Instance implements RuntimeModelObject
{
    // SECTION-START[RuntimeInstance]

    /** Classes by class loader any instance cache. */
    @XmlTransient
    static final Map<ClassLoader, Map<String, Class<?>[]>> classesByClassLoaderAndInstanceCache =
        new WeakIdentityHashMap<ClassLoader, Map<String, Class<?>[]>>();

    /** Constructors by class loader any instance cache. */
    @XmlTransient
    static final Map<ClassLoader, Map<String, Constructor<?>>> constructorsByClassLoaderAndInstanceCache =
        new WeakIdentityHashMap<ClassLoader, Map<String, Constructor<?>>>();

    /** Methods by class loader any instance cache. */
    @XmlTransient
    static final Map<ClassLoader, Map<String, Method>> methodsByClassLoaderAndInstanceCache =
        new WeakIdentityHashMap<ClassLoader, Map<String, Method>>();

    /** Assignable flags by class loader any instance cache. */
    @XmlTransient
    static final Map<ClassLoader, Map<String, Boolean>> assignableFlagsByClassLoaderAndInstanceCache =
        new WeakIdentityHashMap<ClassLoader, Map<String, Boolean>>();

    /** Proxy classes by class loader any instance cache. */
    @XmlTransient
    static final Map<ClassLoader, Map<String, Class<?>>> proxyClassesByClassLoaderAndInstanceCache =
        new WeakIdentityHashMap<ClassLoader, Map<String, Class<?>>>();

    /** Method name. */
    @XmlTransient
    private volatile String javaClassFactoryMethodName;

    /**
     * Creates a new {@code RuntimeInstance} instance by deeply copying a given {@code Instance} instance.
     *
     * @param instance The instance to copy.
     *
     * @throws NullPointerException if {@code instance} is {@code null}.
     */
    public RuntimeInstance( final Instance instance )
    {
        super( instance );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDependencies() != null )
        {
            this.setDependencies( RuntimeModelObjects.getInstance().copyOf( this.getDependencies() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }
        if ( this.getMessages() != null )
        {
            this.setMessages( RuntimeModelObjects.getInstance().copyOf( this.getMessages() ) );
        }
        if ( this.getProperties() != null )
        {
            this.setProperties( RuntimeModelObjects.getInstance().copyOf( this.getProperties() ) );
        }
        if ( this.getSpecifications() != null )
        {
            this.setSpecifications( RuntimeModelObjects.getInstance().copyOf( this.getSpecifications() ) );
        }
    }

    /**
     * Gets the Java class of the instance for a given class loader.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code RuntimeModelObjects.clear()} must be used to synchronize the state of the
     * internal cache with the state of the class loader, should the state of the class loader change.</p>
     *
     * @param classLoader The class loader to get the Java class from or {@code null}, to get the Java class from the
     * platform's bootstrap class loader.
     *
     * @return The Java class of the instance.
     *
     * @throws ClassNotFoundException if the Java class is not found.
     *
     * @see #getClazz()
     * @see RuntimeModelObjects#clear()
     */
    @Override
    public Class<?> getJavaClass( final ClassLoader classLoader ) throws ClassNotFoundException
    {
        ClassLoader classLoaderKey = classLoader;
        if ( classLoaderKey == null )
        {
            classLoaderKey = BOOTSTRAP_CLASSLOADER_KEY;
        }

        synchronized ( classesByClassLoaderAndNameCache )
        {
            Map<String, Class<?>> map = classesByClassLoaderAndNameCache.get( classLoaderKey );

            if ( map == null )
            {
                map = createMap();
                classesByClassLoaderAndNameCache.put( classLoaderKey, map );
            }

            Class<?> javaClass = map.get( this.getClazz() );

            if ( javaClass == null )
            {
                javaClass = super.getJavaClass( classLoader );
                map.put( this.getClazz(), javaClass );
            }

            return javaClass;
        }
    }

    /**
     * Gets the Java classes of all specifications of the instance for a given class loader.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code RuntimeModelObjects.clear()} must be used to synchronize the state of the
     * internal cache with the state of the instance and class loader, should the state of the instance or class loader
     * change.</p>
     *
     * @param classLoader The class loader to get the Java classes from or {@code null}, to get the Java classes from
     * the platform's bootstrap class loader.
     *
     * @return The Java classes of all specifications of the instance.
     *
     * @throws ClassNotFoundException if a Java class is not found.
     *
     * @see #getSpecifications()
     * @see Specification#getClazz()
     * @see RuntimeModelObjects#clear()
     */
    @Override
    public Class<?>[] getJavaClasses( final ClassLoader classLoader ) throws ClassNotFoundException
    {
        ClassLoader classLoaderKey = classLoader;
        if ( classLoaderKey == null )
        {
            classLoaderKey = BOOTSTRAP_CLASSLOADER_KEY;
        }

        synchronized ( classesByClassLoaderAndInstanceCache )
        {
            Map<String, Class<?>[]> map = classesByClassLoaderAndInstanceCache.get( classLoaderKey );

            if ( map == null )
            {
                map = createMap();
                classesByClassLoaderAndInstanceCache.put( classLoaderKey, map );
            }

            Class<?>[] javaClasses = map.get( this.getIdentifier() );

            if ( javaClasses == null && !map.containsKey( this.getIdentifier() ) )
            {
                javaClasses = super.getJavaClasses( classLoader );
                map.put( this.getIdentifier(), javaClasses );
            }

            return javaClasses;
        }
    }

    /**
     * Gets the Java constructor to use for creating objects of the instance.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code RuntimeModelObjects.clear()} must be used to synchronize the state of the
     * internal cache with the state of the instance and class loader, should the state of the instance or class loader
     * change.</p>
     *
     * @param classLoader The class loader to get the Java class from or {@code null}, to get the Java class from the
     * platform's bootstrap class loader.
     *
     * @return The public default Java constructor of the Java class of the instance or {@code null}, if that class
     * does not declare such a constructor, is abstract or is not public.
     *
     * @throws ClassNotFoundException if the Java class is not found.
     *
     * @see #getJavaClass(java.lang.ClassLoader)
     * @see RuntimeModelObjects#clear()
     */
    @Override
    public Constructor<?> getJavaClassConstructor( final ClassLoader classLoader ) throws ClassNotFoundException
    {
        ClassLoader classLoaderKey = classLoader;
        if ( classLoaderKey == null )
        {
            classLoaderKey = BOOTSTRAP_CLASSLOADER_KEY;
        }

        synchronized ( constructorsByClassLoaderAndInstanceCache )
        {
            Map<String, Constructor<?>> map = constructorsByClassLoaderAndInstanceCache.get( classLoaderKey );

            if ( map == null )
            {
                map = createMap();
                constructorsByClassLoaderAndInstanceCache.put( classLoaderKey, map );
            }

            Constructor<?> javaClassConstructor = map.get( this.getIdentifier() );

            if ( javaClassConstructor == null && !map.containsKey( this.getIdentifier() ) )
            {
                javaClassConstructor = super.getJavaClassConstructor( classLoader );
                map.put( this.getIdentifier(), javaClassConstructor );
            }

            return javaClassConstructor;
        }
    }

    /**
     * Gets the name of the Java method to use for creating objects of the instance.
     * <p>This method queries an internal cache for a result object to return. If no cached result object is available,
     * this method queries the super-class for a result object to return and caches the outcome of that query for use on
     * successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @return The name of the Java method to use for creating objects of the instance or {@code null}, if no such
     * method name is supported.
     *
     * @see #getName()
     * @see #clear()
     */
    @Override
    public String getJavaClassFactoryMethodName()
    {
        if ( this.javaClassFactoryMethodName == null )
        {
            this.javaClassFactoryMethodName = super.getJavaClassFactoryMethodName();
        }

        return this.javaClassFactoryMethodName;
    }

    /**
     * Gets the Java method to use for creating objects of the instance.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code RuntimeModelObjects.clear()} must be used to synchronize the state of the
     * internal cache with the state of the instance and class loader, should the state of the instance or class loader
     * change.</p>
     *
     * @param classLoader The class loader to get the Java class from or {@code null}, to get the Java class from the
     * platform's bootstrap class loader.
     *
     * @return The public Java method of the Java class of the instance to use for creating objects of the instance or
     * {@code null}, if that class does not declare such a method.
     *
     * @throws ClassNotFoundException if the Java class is not found.
     *
     * @see #getJavaClass(java.lang.ClassLoader)
     * @see #getJavaClassFactoryMethodName()
     * @see RuntimeModelObjects#clear()
     */
    @Override
    public Method getJavaClassFactoryMethod( final ClassLoader classLoader ) throws ClassNotFoundException
    {
        ClassLoader classLoaderKey = classLoader;
        if ( classLoaderKey == null )
        {
            classLoaderKey = BOOTSTRAP_CLASSLOADER_KEY;
        }

        synchronized ( methodsByClassLoaderAndInstanceCache )
        {
            Map<String, Method> map = methodsByClassLoaderAndInstanceCache.get( classLoaderKey );

            if ( map == null )
            {
                map = createMap();
                methodsByClassLoaderAndInstanceCache.put( classLoaderKey, map );
            }

            Method javaClassFactoryMethod = map.get( this.getIdentifier() );

            if ( javaClassFactoryMethod == null && !map.containsKey( this.getIdentifier() ) )
            {
                javaClassFactoryMethod = super.getJavaClassFactoryMethod( classLoader );
                map.put( this.getIdentifier(), javaClassFactoryMethod );
            }

            return javaClassFactoryMethod;
        }
    }

    /**
     * Gets a flag indicating the Java class of the instance is assignable to all Java classes of all specifications of
     * the instance.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code RuntimeModelObjects.clear()} must be used to synchronize the state of the
     * internal cache with the state of the instance and class loader, should the state of the instance or class loader
     * change.</p>
     *
     * @param classLoader The class loader to get the Java classes from or {@code null}, to get the Java classes from
     * the platform's bootstrap class loader.
     *
     * @return {@code true}, if the Java class of the instance is assignable to all Java classes of all specifications
     * of the instance; {@code false}, if the Java class of the instance is not assignable to all Java classes of all
     * specifications of the instance.
     *
     * @throws ClassNotFoundException if a Java class is not found.
     *
     * @see #getJavaClass(java.lang.ClassLoader)
     * @see #getJavaClasses(java.lang.ClassLoader)
     * @see RuntimeModelObjects#clear()
     */
    @Override
    public boolean isJavaClassAssignable( final ClassLoader classLoader ) throws ClassNotFoundException
    {
        ClassLoader classLoaderKey = classLoader;
        if ( classLoaderKey == null )
        {
            classLoaderKey = BOOTSTRAP_CLASSLOADER_KEY;
        }

        synchronized ( assignableFlagsByClassLoaderAndInstanceCache )
        {
            Map<String, Boolean> map = assignableFlagsByClassLoaderAndInstanceCache.get( classLoaderKey );

            if ( map == null )
            {
                map = createMap();
                assignableFlagsByClassLoaderAndInstanceCache.put( classLoaderKey, map );
            }

            Boolean javaClassAssignable = map.get( this.getIdentifier() );

            if ( javaClassAssignable == null && !map.containsKey( this.getIdentifier() ) )
            {
                javaClassAssignable = super.isJavaClassAssignable( classLoader );
                map.put( this.getIdentifier(), javaClassAssignable );
            }

            return javaClassAssignable == null ? false : javaClassAssignable;
        }
    }

    /**
     * Gets the Java proxy class for a given class loader.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code RuntimeModelObjects.clear()} must be used to synchronize the state of the
     * internal cache with the state of the instance and class loader, should the state of the instance or class loader
     * change.</p>
     *
     * @param classLoader The class loader to get the Java proxy class for.
     *
     * @return The Java proxy class for {@code classLoader} or {@code null}, if the instance does not support a Java
     * proxy class.
     *
     * @throws ClassNotFoundException if a Java class is not found.
     *
     * @see #getJavaClasses(java.lang.ClassLoader)
     * @see RuntimeModelObjects#clear()
     */
    @Override
    public Class<?> getJavaProxyClass( final ClassLoader classLoader ) throws ClassNotFoundException
    {
        ClassLoader classLoaderKey = classLoader;
        if ( classLoaderKey == null )
        {
            classLoaderKey = BOOTSTRAP_CLASSLOADER_KEY;
        }

        synchronized ( proxyClassesByClassLoaderAndInstanceCache )
        {
            Map<String, Class<?>> map = proxyClassesByClassLoaderAndInstanceCache.get( classLoaderKey );

            if ( map == null )
            {
                map = createMap();
                proxyClassesByClassLoaderAndInstanceCache.put( classLoaderKey, map );
            }

            Class<?> javaProxyClass = map.get( this.getIdentifier() );

            if ( javaProxyClass == null && !map.containsKey( this.getIdentifier() ) )
            {
                javaProxyClass = super.getJavaProxyClass( classLoader );
                map.put( this.getIdentifier(), javaProxyClass );
            }

            return javaProxyClass;
        }
    }

    // SECTION-END
    // SECTION-START[RuntimeModelObject]
    public void gc()
    {
        this.gcOrClear( true, false );
    }

    public void clear()
    {
        this.javaClassFactoryMethodName = null;
        this.gcOrClear( false, true );
    }

    private void gcOrClear( final boolean gc, final boolean clear )
    {
        if ( this.getAuthors() instanceof RuntimeModelObject )
        {
            if ( gc )
            {
                ( (RuntimeModelObject) this.getAuthors() ).gc();
            }
            if ( clear )
            {
                ( (RuntimeModelObject) this.getAuthors() ).clear();
            }
        }
        if ( this.getDependencies() instanceof RuntimeModelObject )
        {
            if ( gc )
            {
                ( (RuntimeModelObject) this.getDependencies() ).gc();
            }
            if ( clear )
            {
                ( (RuntimeModelObject) this.getDependencies() ).clear();
            }
        }
        if ( this.getDocumentation() instanceof RuntimeModelObject )
        {
            if ( gc )
            {
                ( (RuntimeModelObject) this.getDocumentation() ).gc();
            }
            if ( clear )
            {
                ( (RuntimeModelObject) this.getDocumentation() ).clear();
            }
        }
        if ( this.getMessages() instanceof RuntimeModelObject )
        {
            if ( gc )
            {
                ( (RuntimeModelObject) this.getMessages() ).gc();
            }
            if ( clear )
            {
                ( (RuntimeModelObject) this.getMessages() ).clear();
            }
        }
        if ( this.getProperties() instanceof RuntimeModelObject )
        {
            if ( gc )
            {
                ( (RuntimeModelObject) this.getProperties() ).gc();
            }
            if ( clear )
            {
                ( (RuntimeModelObject) this.getProperties() ).clear();
            }
        }
        if ( this.getSpecifications() instanceof RuntimeModelObject )
        {
            if ( gc )
            {
                ( (RuntimeModelObject) this.getSpecifications() ).gc();
            }
            if ( clear )
            {
                ( (RuntimeModelObject) this.getSpecifications() ).clear();
            }
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code RuntimeInstance} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public RuntimeInstance()
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
