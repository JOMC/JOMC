// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2010 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
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

import static junit.framework.Assert.assertNull;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test implementation of the {@code ScopeTestSpecification}.
 * <p><b>Specifications</b><ul>
 * <li>{@code 'org.jomc.ri.test.TestScopeSpecificationMany'} {@code (org.jomc.ri.test.TestSpecification)} {@code 1.2-SNAPSHOT} {@code DOES_NOT_EXIST}</li>
 * <li>{@code 'org.jomc.ri.test.TestScopeSpecificationOne1'} {@code (org.jomc.ri.test.TestSpecification)} {@code 1.2-SNAPSHOT} {@code DOES_NOT_EXIST}</li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getTestScopeSpecificationMany TestScopeSpecificationMany}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestScopeSpecificationMany'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.2-SNAPSHOT.</blockquote></li>
 * <li>"{@link #getTestScopeSpecificationOne TestScopeSpecificationOne}"<blockquote>
 * Dependency on {@code 'org.jomc.ri.test.TestScopeSpecificationOne1'} {@code (org.jomc.ri.test.TestSpecification)} at specification level 1.2-SNAPSHOT.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class TestScopeImplementation
    implements
    org.jomc.ri.test.TestSpecification
{
    // SECTION-START[TestSpecification]
    // SECTION-END
    // SECTION-START[TestScopeImplementation]

    public void testUnsupportedScope() throws Exception
    {
        assertNull( this.getTestScopeSpecificationOne() );
        assertNull( this.getTestScopeSpecificationMany() );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code TestScopeImplementation} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public TestScopeImplementation()
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
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestScopeSpecificationMany'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification applies to {@code DOES_NOT_EXIST} scope. The does_not_exist object is returned whenever requested.</p>
     * @return The {@code TestScopeSpecificationMany} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.ri.test.TestSpecification[] getTestScopeSpecificationMany()
    {
        return (org.jomc.ri.test.TestSpecification[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "TestScopeSpecificationMany" );
    }

    /**
     * Gets the {@code TestScopeSpecificationOne} dependency.
     * <p>This method returns any available object of the {@code 'org.jomc.ri.test.TestScopeSpecificationOne1'} {@code (org.jomc.ri.test.TestSpecification)} specification at specification level 1.2-SNAPSHOT.</p>
     * <p>That specification applies to {@code DOES_NOT_EXIST} scope. The does_not_exist object is returned whenever requested.</p>
     * @return The {@code TestScopeSpecificationOne} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
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
