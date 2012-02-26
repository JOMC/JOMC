// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Java Object Management and Configuration
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

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.util.Map;
import org.jomc.model.Implementation;
import static org.jomc.ri.model.RuntimeModelObjects.BOOTSTRAP_CLASSLOADER_KEY;
import static org.jomc.ri.model.RuntimeModelObjects.classesByClassLoaderAndNameCache;
import static org.jomc.ri.model.RuntimeModelObjects.createMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Implementation}.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.model.RuntimeImplementation</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC RI RuntimeImplementation</dd>
 *   <dt><b>Specifications:</b></dt>
 *     <dd>org.jomc.ri.model.RuntimeModelObject @ 1.2</dd>
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
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/2.0/jomc-tools-2.0-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class RuntimeImplementation extends Implementation implements RuntimeModelObject
{
    // SECTION-START[RuntimeImplementation]

    /** Cached location URI. */
    private volatile URI locationUri;

    /**
     * Creates a new {@code RuntimeImplementation} instance by deeply copying a given {@code Implementation} instance.
     *
     * @param implementation The instance to copy.
     *
     * @throws NullPointerException if {@code implementation} is {@code null}.
     */
    public RuntimeImplementation( final Implementation implementation )
    {
        super( implementation );

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
        if ( this.getImplementations() != null )
        {
            this.setImplementations( RuntimeModelObjects.getInstance().copyOf( this.getImplementations() ) );
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
     * Gets the location URI used for locating instances of this implementation.
     * <p>This method queries an internal cache for a result object to return. If no cached result object is available,
     * this method queries the super-class for a result object to return and caches the outcome of that query for use on
     * successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @return The location URI used for locating instances of this implementation or {@code null}, if instances of this
     * implementation do not need to be located.
     *
     * @see #getLocation()
     * @see #clear()
     */
    @Override
    public URI getLocationUri()
    {
        if ( this.locationUri == null )
        {
            this.locationUri = super.getLocationUri();
        }

        return this.locationUri;
    }

    /**
     * Gets the Java class of the implementation for a given class loader.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code RuntimeModelObjects.clear()} must be used to synchronize the state of the
     * internal cache with the state of the class loader, should the state of the class loader change.</p>
     *
     * @param classLoader The class loader to get the Java class from or {@code null}, to get the Java class from the
     * platform's bootstrap class loader.
     *
     * @return The Java class of the implementation or {@code null}, if the implementation does not declare a class.
     *
     * @throws ClassNotFoundException if the Java class is not found.
     *
     * @see #getClazz()
     * @see RuntimeModelObjects#clear()
     */
    @Override
    public Class<?> getJavaClass( final ClassLoader classLoader ) throws ClassNotFoundException
    {
        Class<?> javaClass = null;

        if ( this.getClazz() != null )
        {
            ClassLoader classLoaderKey = classLoader;
            if ( classLoaderKey == null )
            {
                classLoaderKey = BOOTSTRAP_CLASSLOADER_KEY;
            }

            synchronized ( classesByClassLoaderAndNameCache )
            {
                Map<String, Reference<Class<?>>> map = classesByClassLoaderAndNameCache.get( classLoaderKey );

                if ( map == null )
                {
                    map = createMap();
                    classesByClassLoaderAndNameCache.put( classLoaderKey, map );
                }

                final Reference<Class<?>> reference = map.get( this.getClazz() );

                if ( reference != null )
                {
                    javaClass = reference.get();
                }

                if ( javaClass == null )
                {
                    javaClass = super.getJavaClass( classLoader );
                    map.put( this.getClazz(), new WeakReference<Class<?>>( javaClass ) );
                }
            }
        }

        return javaClass;
    }

    // SECTION-END
    // SECTION-START[RuntimeModelObject]
    public void gc()
    {
        this.gcOrClear( true, false );
    }

    public void clear()
    {
        this.locationUri = null;
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
        if ( this.getImplementations() instanceof RuntimeModelObject )
        {
            if ( gc )
            {
                ( (RuntimeModelObject) this.getImplementations() ).gc();
            }
            if ( clear )
            {
                ( (RuntimeModelObject) this.getImplementations() ).clear();
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
    /** Creates a new {@code RuntimeImplementation} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 2.0-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/2.0/jomc-tools-2.0-SNAPSHOT" )
    public RuntimeImplementation()
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
