/*
 *   Copyright (C) 2009 The JOMC Project
 *   Copyright (C) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
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
package org.jomc.modlet.test.support;

import org.jomc.modlet.ModelProcessor;
import org.jomc.modlet.ModelProvider;
import org.jomc.modlet.ModelValidator;
import org.jomc.modlet.Modlet;
import org.jomc.modlet.ModelContext;
import org.jomc.modlet.ModelException;
import org.jomc.modlet.ModletProvider;
import org.jomc.modlet.Modlets;
import org.jomc.modlet.Service;
import org.jomc.modlet.Services;

/**
 * {@code ModletProvider} test implementation.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
public final class TestModletProvider implements ModletProvider
{

    public TestModletProvider()
    {
        super();
    }

    public Modlets findModlets( final ModelContext context ) throws ModelException
    {
        final Modlets modlets = new Modlets();
        final Modlet modlet = new Modlet();
        modlets.getModlet().add( modlet );
        modlet.setName( TestModletProvider.class.getName() );
        modlet.setModel( TestModletProvider.class.getName() );
        modlet.setServices( new Services() );

        Service s = new Service();
        s.setClazz( TestModelProvider.class.getName() );
        s.setIdentifier( ModelProvider.class.getName() );
        modlet.getServices().getService().add( s );

        s = new Service();
        s.setClazz( TestModelProcessor.class.getName() );
        s.setIdentifier( ModelProcessor.class.getName() );
        modlet.getServices().getService().add( s );

        s = new Service();
        s.setClazz( TestModelValidator.class.getName() );
        s.setIdentifier( ModelValidator.class.getName() );
        modlet.getServices().getService().add( s );

        context.setAttribute( TestModletProvider.class.getName(), this );
        return modlets;
    }

}