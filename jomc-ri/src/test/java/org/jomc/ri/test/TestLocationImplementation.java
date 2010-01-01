// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2010 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@jomc.org>
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
 *   THIS SOFTWARE IS PROVIDED BY THE JOMC PROJECT AND CONTRIBUTORS "AS IS"
 *   AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *   THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *   PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE JOMC PROJECT OR
 *   CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *   EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *   PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 *   OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *   WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *   OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *   ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $Id$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.ri.test;

import junit.framework.Assert;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test implementation with unsupported location.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.ri.test.TestScopeSpecificationMany} {@code 1.0-alpha-13-SNAPSHOT} {@code DOES_NOT_EXIST}</li>
 * <li>{@code org.jomc.ri.test.TestScopeSpecificationOne2} {@code 1.0-alpha-13-SNAPSHOT} {@code DOES_NOT_EXIST}</li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getTestScopeSpecificationMany TestScopeSpecificationMany}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestScopeSpecificationMany} at specification level 1.0-alpha-13-SNAPSHOT.</blockquote></li>
 * <li>"{@link #getTestScopeSpecificationOne TestScopeSpecificationOne}"<blockquote>
 * Dependency on {@code org.jomc.ri.test.TestScopeSpecificationOne2} at specification level 1.0-alpha-13-SNAPSHOT.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-13-SNAPSHOT/jomc-tools" )
// </editor-fold>
// SECTION-END
public class TestLocationImplementation
    implements
    org.jomc.ri.test.TestSpecification
{
    // SECTION-START[TestLocationImplementation]

    public void testUnsupportedLocation() throws Exception
    {
        Assert.assertNull( this.getTestScopeSpecificationOne() );
        Assert.assertNull( this.getTestScopeSpecificationMany() );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code TestLocationImplementation} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-13-SNAPSHOT/jomc-tools" )
    public TestLocationImplementation()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // <editor-fold defaultstate="collapsed" desc=" Generated Dependencies ">

    /**
     * Gets the {@code TestScopeSpecificationMany} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.test.TestScopeSpecificationMany} specification at specification level 1.0-alpha-13-SNAPSHOT.</p>
     * <p>That specification applies to {@code DOES_NOT_EXIST} scope. The does_not_exist object is returned whenever requested.</p>
     * @return The {@code TestScopeSpecificationMany} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-13-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification[] getTestScopeSpecificationMany()
    {
        return (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "TestScopeSpecificationMany" );
    }

    /**
     * Gets the {@code TestScopeSpecificationOne} dependency.
     * <p>This method returns any available object of the {@code org.jomc.ri.test.TestScopeSpecificationOne2} specification at specification level 1.0-alpha-13-SNAPSHOT.</p>
     * <p>That specification applies to {@code DOES_NOT_EXIST} scope. The does_not_exist object is returned whenever requested.</p>
     * @return The {@code TestScopeSpecificationOne} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-13-SNAPSHOT/jomc-tools" )
    private org.jomc.ri.test.TestSpecification getTestScopeSpecificationOne()
    {
        return (org.jomc.ri.test.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "TestScopeSpecificationOne" );
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
