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
package org.jomc.ri.test;

import org.junit.Test;
import static org.junit.Assert.assertNull;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test implementation with unsupported location.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>JOMC &#8273; RI &#8273; Tests &#8273; Location Implementation Test</dd>
 *   <dt><b>Name:</b></dt><dd>JOMC &#8273; RI &#8273; Tests &#8273; Location Implementation Test</dd>
 *   <dt><b>Specifications:</b></dt>
 *     <dd>JOMC &#8273; RI &#8273; Tests &#8273; Test Scope Specification Many @ 1.4-SNAPSHOT</dd>
 *     <dd>JOMC &#8273; RI &#8273; Tests &#8273; Test Scope Specification One &#8273; 2 @ 1.4-SNAPSHOT</dd>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>No</dd>
 * </dl>
 *
 * @author <a href="mailto:cs@schulte.it">Christian Schulte</a> 1.0
 * @version 1.4-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class LocationImplementationTest
    implements
    org.jomc.ri.test.support.TestSpecification
{
    // SECTION-START[TestSpecification]
    // SECTION-END
    // SECTION-START[LocationImplementationTest]

    @Test
    public final void testUnsupportedLocation() throws Exception
    {
        assertNull( this.getTestScopeSpecificationOne() );
        assertNull( this.getTestScopeSpecificationMany() );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code LocationImplementationTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
    public LocationImplementationTest()
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
     * Gets the {@code <TestScopeSpecificationMany>} dependency.
     * <p>
     *   This method returns any available object of the {@code <JOMC ⁑ RI ⁑ Tests ⁑ Test Scope Specification Many>} specification at specification level 1.4-SNAPSHOT.
     *   That specification applies to {@code <DOES_NOT_EXIST>} scope. The does_not_exist object is returned whenever requested.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <TestScopeSpecificationMany>} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @SuppressWarnings("unused")
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification[] getTestScopeSpecificationMany()
    {
        return (org.jomc.ri.test.support.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "TestScopeSpecificationMany" );
    }
    /**
     * Gets the {@code <TestScopeSpecificationOne>} dependency.
     * <p>
     *   This method returns any available object of the {@code <JOMC ⁑ RI ⁑ Tests ⁑ Test Scope Specification One ⁑ 2>} specification at specification level 1.4-SNAPSHOT.
     *   That specification applies to {@code <DOES_NOT_EXIST>} scope. The does_not_exist object is returned whenever requested.
     * </p>
     * <dl>
     *   <dt><b>Final:</b></dt><dd>No</dd>
     * </dl>
     * @return The {@code <TestScopeSpecificationOne>} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @SuppressWarnings("unused")
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.4-SNAPSHOT", comments = "See http://www.jomc.org/jomc/1.4/jomc-tools-1.4-SNAPSHOT" )
    private org.jomc.ri.test.support.TestSpecification getTestScopeSpecificationOne()
    {
        return (org.jomc.ri.test.support.TestSpecification) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "TestScopeSpecificationOne" );
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
