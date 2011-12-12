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

import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;
import org.jomc.model.Property;
import org.jomc.model.PropertyException;
import org.jomc.util.WeakIdentityHashMap;
import static org.jomc.ri.model.RuntimeModelObjects.BOOTSTRAP_CLASSLOADER_KEY;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime {@code Property}.
 *
 * <p>
 *   This implementation is identified by {@code <org.jomc.ri.model.RuntimeProperty>}.
 * </p>
 * <p>
 *   It provides objects named {@code <JOMC RI RuntimeProperty>} of the following specifications:
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
public class RuntimeProperty extends Property implements RuntimeModelObject
{
    // SECTION-START[RuntimeProperty]

    /** Java values by class loader cache.*/
    @XmlTransient
    private final Map<ClassLoader, Object> javaValuesByClassLoaderCache =
        new WeakIdentityHashMap<ClassLoader, Object>();

    /**
     * Creates a new {@code RuntimeProperty} instance by deeply copying a given {@code Property} instance.
     *
     * @param property The instance to copy.
     *
     * @throws NullPointerException if {@code property} is {@code null}.
     */
    public RuntimeProperty( final Property property )
    {
        super( property );

        if ( this.getAuthors() != null )
        {
            this.setAuthors( RuntimeModelObjects.getInstance().copyOf( this.getAuthors() ) );
        }
        if ( this.getDocumentation() != null )
        {
            this.setDocumentation( RuntimeModelObjects.getInstance().copyOf( this.getDocumentation() ) );
        }
    }

    /**
     * Gets the Java value of the property.
     * <p>This method queries an internal cache for a result object to return for the given argument values. If no
     * cached result object is available, this method queries the super-class for a result object to return and caches
     * the outcome of that query for use on successive calls.</p>
     * <p><b>Note:</b><br/>Method {@code clear()} must be used to synchronize the state of the internal cache with the
     * state of the instance, should the state of the instance change.</p>
     *
     * @param classLoader The class loader to use for getting the Java value; {@code null} to use the platform's
     * bootstrap class loader.
     *
     * @return The Java value of the property or {@code null}.
     *
     * @throws PropertyException if getting the Java value of the property fails unexpectedly.
     *
     * @see #clear()
     */
    @Override
    public Object getJavaValue( final ClassLoader classLoader ) throws PropertyException
    {
        ClassLoader classLoaderKey = classLoader;
        if ( classLoaderKey == null )
        {
            classLoaderKey = BOOTSTRAP_CLASSLOADER_KEY;
        }

        synchronized ( this.javaValuesByClassLoaderCache )
        {
            Object javaValue = this.javaValuesByClassLoaderCache.get( classLoaderKey );

            if ( javaValue == null )
            {
                javaValue = super.getJavaValue( classLoader );

                if ( javaValue != null )
                {
                    this.javaValuesByClassLoaderCache.put( classLoaderKey, javaValue );
                }
            }

            return javaValue;
        }
    }

    // SECTION-END
    // SECTION-START[RuntimeModelObject]
    public void gc()
    {
        synchronized ( this.javaValuesByClassLoaderCache )
        {
            this.javaValuesByClassLoaderCache.size();
        }

        this.gcOrClear( true, false );
    }

    public void clear()
    {
        synchronized ( this.javaValuesByClassLoaderCache )
        {
            this.javaValuesByClassLoaderCache.clear();
        }

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
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code RuntimeProperty} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public RuntimeProperty()
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
