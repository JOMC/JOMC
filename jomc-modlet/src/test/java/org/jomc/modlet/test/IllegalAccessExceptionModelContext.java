/*
 *   Copyright (c) 2009 The JOMC Project
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
package org.jomc.modlet.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import org.jomc.modlet.Model;
import org.jomc.modlet.ModelContext;
import org.jomc.modlet.ModelException;
import org.jomc.modlet.ModelValidationReport;
import org.jomc.modlet.Modlets;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.EntityResolver;

/**
 * Test {@code ModelContext} implementation with inaccessible constructor.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
public class IllegalAccessExceptionModelContext extends ModelContext
{

    private IllegalAccessExceptionModelContext( final ClassLoader classLoader )
    {
        super( null );
    }

    @Override
    public Modlets findModlets() throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Model findModel( final String model ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityResolver createEntityResolver( final String model ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public LSResourceResolver createResourceResolver( final String model ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Schema createSchema( final String model ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public JAXBContext createContext( final String model ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Marshaller createMarshaller( final String model ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Unmarshaller createUnmarshaller( final String model ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Model processModel( final Model model ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModelValidationReport validateModel( final Model model ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModelValidationReport validateModel( final String model, final Source source ) throws ModelException
    {
        throw new UnsupportedOperationException();
    }

}