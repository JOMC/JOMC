// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 * Java Object Management and Configuration
 * Copyright (C) Christian Schulte <cs@schulte.it>, 2011-313
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   o Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   o Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $JOMC$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.ri.model;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;
import org.jomc.model.Argument;
import org.jomc.model.Arguments;
import org.jomc.model.Author;
import org.jomc.model.Authors;
import org.jomc.model.Dependencies;
import org.jomc.model.Dependency;
import org.jomc.model.Implementation;
import org.jomc.model.ImplementationReference;
import org.jomc.model.Implementations;
import org.jomc.model.Instance;
import org.jomc.model.Instances;
import org.jomc.model.Message;
import org.jomc.model.MessageReference;
import org.jomc.model.Messages;
import org.jomc.model.Module;
import org.jomc.model.Person;
import org.jomc.model.Persons;
import org.jomc.model.Properties;
import org.jomc.model.Property;
import org.jomc.model.PropertyReference;
import org.jomc.model.Specification;
import org.jomc.model.SpecificationReference;
import org.jomc.model.Specifications;
import org.jomc.model.Text;
import org.jomc.model.Texts;
import org.jomc.util.WeakIdentityHashMap;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Runtime model objects.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.ri.model.RuntimeModelObjects</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC ⁑ RI ⁑ RuntimeModelObjects</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.2
 * @version 1.2
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class RuntimeModelObjects
{
    // SECTION-START[RuntimeModelObjects]

    /**
     * Classes by class loader any name cache.
     */
    static final Map<ClassLoader, Map<String, Reference<Class<?>>>> classesByClassLoaderAndNameCache =
        new WeakIdentityHashMap<ClassLoader, Map<String, Reference<Class<?>>>>();

    /**
     * Singleton instance.
     */
    private static final RuntimeModelObjects instance = new RuntimeModelObjects();

    /**
     * Cache key for the bootstrap class loader.
     */
    static final ClassLoader BOOTSTRAP_CLASSLOADER_KEY = new ClassLoader()
    {
    };

    /**
     * Gets the {@code RuntimeModelObjects} singleton instance.
     *
     * @return The {@code RuntimeModelObjects} singleton instance.
     */
    public static RuntimeModelObjects getInstance()
    {
        return instance;
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimePropertyReference( o )}
     */
    public PropertyReference copyOf( final PropertyReference o )
    {
        return new RuntimePropertyReference( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeImplementationReference( o )}
     */
    public ImplementationReference copyOf( final ImplementationReference o )
    {
        return new RuntimeImplementationReference( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeDependencies( o )}
     */
    public Dependencies copyOf( final Dependencies o )
    {
        return new RuntimeDependencies( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeInstances( o )}
     */
    public Instances copyOf( final Instances o )
    {
        return new RuntimeInstances( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeAuthors( o )}
     */
    public Authors copyOf( final Authors o )
    {
        return new RuntimeAuthors( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeSpecification( o )}
     */
    public Specification copyOf( final Specification o )
    {
        return new RuntimeSpecification( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeMessage( o )}
     */
    public Message copyOf( final Message o )
    {
        return new RuntimeMessage( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeInstance( o )}
     */
    public Instance copyOf( final Instance o )
    {
        return new RuntimeInstance( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeImplementations( o )}
     */
    public Implementations copyOf( final Implementations o )
    {
        return new RuntimeImplementations( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeTexts( o )}
     */
    public Texts copyOf( final Texts o )
    {
        return new RuntimeTexts( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeImplementation( o )}
     */
    public Implementation copyOf( final Implementation o )
    {
        return new RuntimeImplementation( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeModule( o )}
     */
    public Module copyOf( final Module o )
    {
        return new RuntimeModule( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeArgument( o )}
     */
    public Argument copyOf( final Argument o )
    {
        return new RuntimeArgument( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeText( o )}
     */
    public Text copyOf( final Text o )
    {
        return new RuntimeText( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeProperties( o )}
     */
    public Properties copyOf( final Properties o )
    {
        return new RuntimeProperties( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimePerson( o )}
     */
    public Person copyOf( final Person o )
    {
        return new RuntimePerson( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeSpecificationReference( o )}
     */
    public SpecificationReference copyOf( final SpecificationReference o )
    {
        return new RuntimeSpecificationReference( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeAuthor( o )}
     */
    public Author copyOf( final Author o )
    {
        return new RuntimeAuthor( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeProperty( o )}
     */
    public Property copyOf( final Property o )
    {
        return new RuntimeProperty( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeArguments( o )}
     */
    public Arguments copyOf( final Arguments o )
    {
        return new RuntimeArguments( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeDependency( o )}
     */
    public Dependency copyOf( final Dependency o )
    {
        return new RuntimeDependency( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeSpecifications( o )}
     */
    public Specifications copyOf( final Specifications o )
    {
        return new RuntimeSpecifications( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeMessages( o )}
     */
    public Messages copyOf( final Messages o )
    {
        return new RuntimeMessages( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimePersons( o )}
     */
    public Persons copyOf( final Persons o )
    {
        return new RuntimePersons( o );
    }

    /**
     * Creates a copy of a given object.
     *
     * @param o The object to copy.
     *
     * @return {@code new RuntimeMessageReference( o )}
     */
    public MessageReference copyOf( final MessageReference o )
    {
        return new RuntimeMessageReference( o );
    }

    /**
     * Handles shared runtime state.
     */
    public void gc()
    {
        synchronized ( classesByClassLoaderAndNameCache )
        {
            classesByClassLoaderAndNameCache.size();
        }
        synchronized ( RuntimeInstance.assignableFlagsByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.assignableFlagsByClassLoaderAndInstanceCache.size();
        }
        synchronized ( RuntimeInstance.classesByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.classesByClassLoaderAndInstanceCache.size();
        }
        synchronized ( RuntimeInstance.constructorsByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.constructorsByClassLoaderAndInstanceCache.size();
        }
        synchronized ( RuntimeInstance.methodsByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.methodsByClassLoaderAndInstanceCache.size();
        }
        synchronized ( RuntimeInstance.proxyClassesByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.proxyClassesByClassLoaderAndInstanceCache.size();
        }
    }

    /**
     * Clears shared runtime state.
     */
    public void clear()
    {
        synchronized ( classesByClassLoaderAndNameCache )
        {
            classesByClassLoaderAndNameCache.clear();
        }
        synchronized ( RuntimeInstance.assignableFlagsByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.assignableFlagsByClassLoaderAndInstanceCache.clear();
        }
        synchronized ( RuntimeInstance.classesByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.classesByClassLoaderAndInstanceCache.clear();
        }
        synchronized ( RuntimeInstance.constructorsByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.constructorsByClassLoaderAndInstanceCache.clear();
        }
        synchronized ( RuntimeInstance.methodsByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.methodsByClassLoaderAndInstanceCache.clear();
        }
        synchronized ( RuntimeInstance.proxyClassesByClassLoaderAndInstanceCache )
        {
            RuntimeInstance.proxyClassesByClassLoaderAndInstanceCache.clear();
        }
    }

    /**
     * Creates a new {@code Map} instance.
     *
     * @param <K> The type of the keys to maintain by the new map.
     * @param <V> The type of the values to map.
     *
     * @return A new {@code Map} instance.
     */
    static <K, V> Map<K, V> createMap()
    {
        return new HashMap<K, V>( 128 );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code RuntimeModelObjects} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.9-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.9/jomc-tools-1.9-SNAPSHOT" )
    public RuntimeModelObjects()
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
