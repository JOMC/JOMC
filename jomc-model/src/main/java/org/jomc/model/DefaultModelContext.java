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
package org.jomc.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.logging.Level;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.jomc.model.modlet.Modlet;
import org.jomc.model.modlet.ModletContext;
import org.jomc.model.modlet.ModletException;
import org.jomc.model.modlet.Modlets;
import org.jomc.model.modlet.Schemas;
import org.jomc.model.modlet.Service;
import org.jomc.model.modlet.Services;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Default {@code ModelContext} implementation.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $Id$
 * @see ModelContext#createModelContext(java.lang.ClassLoader)
 */
public class DefaultModelContext extends ModelContext
{

    /** Supported schema name extensions. */
    private static final String[] SCHEMA_EXTENSIONS = new String[]
    {
        "xsd"
    };

    /** Constant for the identifier of the object management and configuration model. */
    private static final String MODEL_IDENTIFIER = "http://jomc.org/model";

    /** Cached {@code Modlets}. */
    private Reference<Modlets> cachedModlets = new SoftReference<Modlets>( null );

    /** Cached schema resources. */
    private Reference<Set<URI>> cachedSchemaResources = new SoftReference<Set<URI>>( null );

    /**
     * Creates a new {@code DefaultModelContext} instance taking a class loader.
     *
     * @param classLoader The class loader of the context.
     */
    public DefaultModelContext( final ClassLoader classLoader )
    {
        super( classLoader );
    }

    /**
     * Searches the context for modules.
     *
     * @return The modules found in the context.
     *
     * @throws ModelException if searching modules fails.
     *
     * @see ModletContext#findModlets()
     * @see Modlets#getServices(java.lang.String)
     * @see ModelProvider#findModules(org.jomc.model.ModelContext)
     */
    @Override
    public Modules findModules() throws ModelException
    {
        try
        {
            final Text text = new Text();
            text.setLanguage( "en" );
            text.setValue( getMessage( "contextModulesInfo" ) );

            final Modules modules = new Modules();
            modules.setDocumentation( new Texts() );
            modules.getDocumentation().setDefaultLanguage( "en" );
            modules.getDocumentation().getText().add( text );

            final Services services = this.getModlets().getServices( MODEL_IDENTIFIER );
            final List<Service> providers = services != null ? services.getServices( ModelProvider.class ) : null;

            if ( providers != null )
            {
                for ( Service provider : providers )
                {
                    final Class<?> clazz = this.findClass( provider.getClazz() );

                    if ( clazz == null )
                    {
                        throw new ModelException( getMessage( "serviceNotFound", provider.getOrdinal(),
                                                              provider.getIdentifier(), provider.getClazz() ) );

                    }

                    if ( !ModelProvider.class.isAssignableFrom( clazz ) )
                    {
                        throw new ModelException( getMessage( "illegalService", provider.getOrdinal(),
                                                              provider.getIdentifier(), provider.getClazz(),
                                                              ModelProvider.class.getName() ) );

                    }

                    final Class<? extends ModelProvider> modelProviderClass = clazz.asSubclass( ModelProvider.class );
                    final ModelProvider modelProvider = modelProviderClass.newInstance();
                    final Modules provided = modelProvider.findModules( this );
                    if ( provided != null )
                    {
                        modules.getModule().addAll( provided.getModule() );
                    }
                }
            }

            if ( this.isLoggable( Level.FINEST ) )
            {
                final StringWriter stringWriter = new StringWriter();
                final Marshaller m = this.createMarshaller();
                m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
                m.marshal( new ObjectFactory().createModules( modules ), stringWriter );
                stringWriter.close();

                this.log( Level.FINEST, getMessage( "foundModules" ), null );

                final BufferedReader reader = new BufferedReader( new StringReader( stringWriter.toString() ) );
                String line;

                while ( ( line = reader.readLine() ) != null )
                {
                    this.log( Level.FINEST, "\t" + line, null );
                }

                reader.close();
            }

            return modules;
        }
        catch ( final ModletException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final InstantiationException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final IllegalAccessException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final IOException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final JAXBException e )
        {
            if ( e.getLinkedException() != null )
            {
                throw new ModelException( e.getLinkedException().getMessage(), e.getLinkedException() );
            }
            else
            {
                throw new ModelException( e.getMessage(), e );
            }
        }
    }

    /**
     * Processes modules.
     *
     * @param modules The modules to process.
     *
     * @return The processed modules.
     *
     * @throws NullPointerException if {@code modules} is {@code null}.
     * @throws ModelException if processing modules fails.
     *
     * @see ModletContext#findModlets()
     * @see Modlets#getServices(java.lang.String)
     * @see ModelProcessor#processModules(org.jomc.model.ModelContext, org.jomc.model.Modules)
     */
    @Override
    public Modules processModules( final Modules modules ) throws ModelException
    {
        if ( modules == null )
        {
            throw new NullPointerException( "modules" );
        }

        try
        {
            Modules processed = modules;
            final Services services = this.getModlets().getServices( MODEL_IDENTIFIER );
            final List<Service> processors = services != null ? services.getServices( ModelProcessor.class ) : null;

            if ( processors != null )
            {
                for ( Service processor : processors )
                {
                    final Class<?> clazz = this.findClass( processor.getClazz() );

                    if ( clazz == null )
                    {
                        throw new ModelException( getMessage( "serviceNotFound", processor.getOrdinal(),
                                                              processor.getIdentifier(), processor.getClazz() ) );

                    }

                    if ( !ModelProcessor.class.isAssignableFrom( clazz ) )
                    {
                        throw new ModelException( getMessage( "illegalService", processor.getOrdinal(),
                                                              processor.getIdentifier(), processor.getClazz(),
                                                              ModelProcessor.class.getName() ) );

                    }

                    final Class<? extends ModelProcessor> modelProcessorClass =
                        clazz.asSubclass( ModelProcessor.class );

                    final ModelProcessor modelProcessor = modelProcessorClass.newInstance();
                    final Modules current = modelProcessor.processModules( this, processed );
                    if ( current != null )
                    {
                        processed = current;
                    }
                }
            }

            if ( this.isLoggable( Level.FINEST ) )
            {
                final StringWriter stringWriter = new StringWriter();
                final Marshaller m = this.createMarshaller();
                m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
                m.marshal( new ObjectFactory().createModules( processed ), stringWriter );
                stringWriter.close();

                this.log( Level.FINEST, getMessage( "processedModules" ), null );

                final BufferedReader reader = new BufferedReader( new StringReader( stringWriter.toString() ) );
                String line;

                while ( ( line = reader.readLine() ) != null )
                {
                    this.log( Level.FINEST, "\t" + line, null );
                }

                reader.close();
            }

            return processed;
        }
        catch ( final ModletException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final InstantiationException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final IllegalAccessException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final IOException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final JAXBException e )
        {
            if ( e.getLinkedException() != null )
            {
                throw new ModelException( e.getLinkedException().getMessage(), e.getLinkedException() );
            }
            else
            {
                throw new ModelException( e.getMessage(), e );
            }
        }
    }

    /**
     * Validates a given model.
     *
     * @param model A source providing the model to validate.
     *
     * @return Validation report.
     *
     * @throws NullPointerException if {@code model} is {@code null}.
     * @throws ModelException if validating the model fails.
     *
     * @see #createSchema()
     */
    @Override
    public ModelValidationReport validateModel( final Source model ) throws ModelException
    {
        if ( model == null )
        {
            throw new NullPointerException( "model" );
        }

        final Schema schema = this.createSchema();
        final Validator validator = schema.newValidator();
        final ModelErrorHandler modelErrorHandler = new ModelErrorHandler( this );
        validator.setErrorHandler( modelErrorHandler );

        try
        {
            validator.validate( model );
        }
        catch ( final SAXException e )
        {
            if ( this.isLoggable( Level.FINE ) )
            {
                this.log( Level.FINE, e.getMessage(), e );
            }

            if ( modelErrorHandler.getReport().isModelValid() )
            {
                throw new ModelException( e.getMessage(), e );
            }
        }
        catch ( final IOException e )
        {
            throw new ModelException( e.getMessage(), e );
        }

        return modelErrorHandler.getReport();
    }

    /**
     * Validates a given list of modules.
     *
     * @param modules The list of modules to validate.
     *
     * @return Validation report.
     *
     * @throws NullPointerException if {@code modules} is {@code null}.
     * @throws ModelException if validating the modules fails.
     *
     * @see ModletContext#findModlets()
     * @see Modlets#getServices(java.lang.String)
     * @see ModelValidator#validateModel(org.jomc.model.ModelContext, org.jomc.model.Modules)
     */
    @Override
    public ModelValidationReport validateModel( final Modules modules ) throws ModelException
    {
        if ( modules == null )
        {
            throw new NullPointerException( "modules" );
        }

        try
        {
            final Services services = this.getModlets().getServices( MODEL_IDENTIFIER );
            final List<Service> validators = services != null ? services.getServices( ModelValidator.class ) : null;
            final ModelValidationReport report = new ModelValidationReport();

            if ( validators != null )
            {
                for ( Service validator : validators )
                {
                    final Class<?> clazz = this.findClass( validator.getClazz() );

                    if ( clazz == null )
                    {
                        throw new ModelException( getMessage( "serviceNotFound", validator.getOrdinal(),
                                                              validator.getIdentifier(), validator.getClazz() ) );

                    }

                    if ( !ModelValidator.class.isAssignableFrom( clazz ) )
                    {
                        throw new ModelException( getMessage( "illegalService", validator.getOrdinal(),
                                                              validator.getIdentifier(), validator.getClazz(),
                                                              ModelValidator.class.getName() ) );

                    }

                    final Class<? extends ModelValidator> modelValidatorClass =
                        clazz.asSubclass( ModelValidator.class );

                    final ModelValidator modelValidator = modelValidatorClass.newInstance();
                    final ModelValidationReport current = modelValidator.validateModel( this, modules );
                    if ( current != null )
                    {
                        report.getDetails().addAll( current.getDetails() );
                    }
                }
            }

            return report;
        }
        catch ( final ModletException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final InstantiationException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final IllegalAccessException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see ModletContext#findModlets()
     * @see Modlets#getSchemas(java.lang.String)
     */
    @Override
    public EntityResolver createEntityResolver() throws ModelException
    {
        return new DefaultHandler()
        {

            @Override
            public InputSource resolveEntity( final String publicId, final String systemId )
                throws SAXException, IOException
            {
                if ( systemId == null )
                {
                    throw new NullPointerException( "systemId" );
                }

                InputSource schemaSource = null;

                try
                {
                    org.jomc.model.modlet.Schema s = null;
                    final Schemas classpathSchemas = getModlets().getSchemas( MODEL_IDENTIFIER );

                    if ( classpathSchemas != null )
                    {
                        if ( publicId != null )
                        {
                            s = classpathSchemas.getSchemaByPublicId( publicId );
                        }
                        if ( s == null )
                        {
                            s = classpathSchemas.getSchemaBySystemId( systemId );
                        }
                    }

                    if ( s != null )
                    {
                        schemaSource = new InputSource();
                        schemaSource.setPublicId( s.getPublicId() != null ? s.getPublicId() : publicId );
                        schemaSource.setSystemId( s.getSystemId() );

                        if ( s.getClasspathId() != null )
                        {
                            final URL resource = findResource( s.getClasspathId() );

                            if ( resource != null )
                            {
                                schemaSource.setSystemId( resource.toExternalForm() );
                            }
                            else
                            {
                                if ( isLoggable( Level.WARNING ) )
                                {
                                    log( Level.WARNING, getMessage( "resourceNotFound", s.getClasspathId() ), null );
                                }
                            }
                        }

                        if ( isLoggable( Level.FINE ) )
                        {
                            log( Level.FINE, getMessage(
                                "resolutionInfo", publicId + ", " + systemId,
                                schemaSource.getPublicId() + ", " + schemaSource.getSystemId() ), null );

                        }
                    }

                    if ( schemaSource == null )
                    {
                        final URI systemUri = new URI( systemId );
                        String schemaName = systemUri.getPath();
                        if ( schemaName != null )
                        {
                            final int lastIndexOfSlash = schemaName.lastIndexOf( '/' );
                            if ( lastIndexOfSlash != -1 && lastIndexOfSlash < schemaName.length() )
                            {
                                schemaName = schemaName.substring( lastIndexOfSlash + 1 );
                            }

                            for ( URI uri : getSchemaResources() )
                            {
                                if ( uri.getPath().endsWith( schemaName ) )
                                {
                                    schemaSource = new InputSource();
                                    schemaSource.setPublicId( publicId );
                                    schemaSource.setSystemId( uri.toASCIIString() );

                                    if ( isLoggable( Level.FINE ) )
                                    {
                                        log( Level.FINE, getMessage( "resolutionInfo", systemUri.toASCIIString(),
                                                                     schemaSource.getSystemId() ), null );

                                    }

                                    break;
                                }
                            }
                        }
                        else
                        {
                            if ( isLoggable( Level.WARNING ) )
                            {
                                log( Level.WARNING, getMessage( "unsupportedSystemIdUri", systemId,
                                                                systemUri.toASCIIString() ), null );

                            }

                            schemaSource = null;
                        }
                    }
                }
                catch ( final URISyntaxException e )
                {
                    if ( isLoggable( Level.WARNING ) )
                    {
                        log( Level.WARNING, getMessage( "unsupportedSystemIdUri", systemId, e.getMessage() ), null );
                    }

                    schemaSource = null;
                }
                catch ( final ModletException e )
                {
                    throw (IOException) new IOException( getMessage( "failedResolvingSchemas" ) ).initCause( e );
                }
                catch ( final ModelException e )
                {
                    throw (IOException) new IOException( getMessage(
                        "failedResolving", publicId, systemId, e.getMessage() ) ).initCause( e );

                }

                return schemaSource;
            }

        };
    }

    /**
     * {@inheritDoc}
     *
     * @see ModletContext#findModlets()
     * @see Modlets#getSchemas(java.lang.String)
     */
    @Override
    public LSResourceResolver createResourceResolver() throws ModelException
    {
        return new LSResourceResolver()
        {

            public LSInput resolveResource( final String type, final String namespaceURI, final String publicId,
                                            final String systemId, final String baseURI )
            {
                final String resolvePublicId = namespaceURI == null ? publicId : namespaceURI;
                final String resolveSystemId = systemId == null ? "" : systemId;

                try
                {
                    if ( XMLConstants.W3C_XML_SCHEMA_NS_URI.equals( type ) )
                    {
                        final InputSource schemaSource =
                            createEntityResolver().resolveEntity( resolvePublicId, resolveSystemId );

                        if ( schemaSource != null )
                        {
                            return new LSInput()
                            {

                                public Reader getCharacterStream()
                                {
                                    return schemaSource.getCharacterStream();
                                }

                                public void setCharacterStream( final Reader characterStream )
                                {
                                    log( Level.WARNING, getMessage(
                                        "unsupportedOperation", "setCharacterStream",
                                        DefaultModelContext.class.getName() + ".LSResourceResolver" ), null );

                                }

                                public InputStream getByteStream()
                                {
                                    return schemaSource.getByteStream();
                                }

                                public void setByteStream( final InputStream byteStream )
                                {
                                    log( Level.WARNING, getMessage(
                                        "unsupportedOperation", "setByteStream",
                                        DefaultModelContext.class.getName() + ".LSResourceResolver" ), null );

                                }

                                public String getStringData()
                                {
                                    return null;
                                }

                                public void setStringData( final String stringData )
                                {
                                    log( Level.WARNING, getMessage(
                                        "unsupportedOperation", "setStringData",
                                        DefaultModelContext.class.getName() + ".LSResourceResolver" ), null );

                                }

                                public String getSystemId()
                                {
                                    return schemaSource.getSystemId();
                                }

                                public void setSystemId( final String systemId )
                                {
                                    log( Level.WARNING, getMessage(
                                        "unsupportedOperation", "setSystemId",
                                        DefaultModelContext.class.getName() + ".LSResourceResolver" ), null );

                                }

                                public String getPublicId()
                                {
                                    return schemaSource.getPublicId();
                                }

                                public void setPublicId( final String publicId )
                                {
                                    log( Level.WARNING, getMessage(
                                        "unsupportedOperation", "setPublicId",
                                        DefaultModelContext.class.getName() + ".LSResourceResolver" ), null );

                                }

                                public String getBaseURI()
                                {
                                    return baseURI;
                                }

                                public void setBaseURI( final String baseURI )
                                {
                                    log( Level.WARNING, getMessage(
                                        "unsupportedOperation", "setBaseURI",
                                        DefaultModelContext.class.getName() + ".LSResourceResolver" ), null );

                                }

                                public String getEncoding()
                                {
                                    return schemaSource.getEncoding();
                                }

                                public void setEncoding( final String encoding )
                                {
                                    log( Level.WARNING, getMessage(
                                        "unsupportedOperation", "setEncoding",
                                        DefaultModelContext.class.getName() + ".LSResourceResolver" ), null );

                                }

                                public boolean getCertifiedText()
                                {
                                    return false;
                                }

                                public void setCertifiedText( final boolean certifiedText )
                                {
                                    log( Level.WARNING, getMessage(
                                        "unsupportedOperation", "setCertifiedText",
                                        DefaultModelContext.class.getName() + ".LSResourceResolver" ), null );

                                }

                            };
                        }

                    }
                    else if ( isLoggable( Level.WARNING ) )
                    {
                        log( Level.WARNING, getMessage( "unsupportedResourceType", type ), null );
                    }
                }
                catch ( final SAXException e )
                {
                    if ( isLoggable( Level.SEVERE ) )
                    {
                        log( Level.SEVERE, getMessage( "failedResolving", resolvePublicId, resolveSystemId,
                                                       e.getMessage() ), e );

                    }
                }
                catch ( final IOException e )
                {
                    if ( isLoggable( Level.SEVERE ) )
                    {
                        log( Level.SEVERE, getMessage( "failedResolving", resolvePublicId, resolveSystemId,
                                                       e.getMessage() ), e );

                    }
                }
                catch ( final ModelException e )
                {
                    if ( isLoggable( Level.SEVERE ) )
                    {
                        log( Level.SEVERE, getMessage( "failedResolving", resolvePublicId, resolveSystemId,
                                                       e.getMessage() ), e );

                    }
                }

                return null;
            }

        };

    }

    /**
     * {@inheritDoc}
     *
     * @see ModletContext#findModlets()
     * @see Modlets#getSchemas(java.lang.String)
     */
    @Override
    public Schema createSchema() throws ModelException
    {
        try
        {
            final SchemaFactory f = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            final Schemas schemas = this.getModlets().getSchemas( MODEL_IDENTIFIER );
            final EntityResolver entityResolver = this.createEntityResolver();
            final List<Source> sources = new ArrayList<Source>( schemas != null ? schemas.getSchema().size() : 0 );

            if ( schemas != null )
            {
                for ( org.jomc.model.modlet.Schema s : schemas.getSchema() )
                {
                    final InputSource inputSource = entityResolver.resolveEntity( s.getPublicId(), s.getSystemId() );

                    if ( inputSource != null )
                    {
                        sources.add( new SAXSource( inputSource ) );
                    }
                }
            }

            if ( sources.isEmpty() )
            {
                throw new ModelException( getMessage( "missingSchemas" ) );
            }

            f.setResourceResolver( this.createResourceResolver() );
            return f.newSchema( sources.toArray( new Source[ sources.size() ] ) );
        }
        catch ( final ModletException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final IOException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final SAXException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see ModletContext#findModlets()
     * @see Modlets#getSchemas(java.lang.String)
     */
    @Override
    public JAXBContext createContext() throws ModelException
    {
        try
        {
            final StringBuilder packageNames = new StringBuilder();
            final Schemas schemas = this.getModlets().getSchemas( MODEL_IDENTIFIER );

            if ( schemas != null )
            {
                for ( final Iterator<org.jomc.model.modlet.Schema> s = schemas.getSchema().iterator(); s.hasNext(); )
                {
                    final org.jomc.model.modlet.Schema schema = s.next();
                    if ( schema.getContextId() != null )
                    {
                        packageNames.append( ':' ).append( schema.getContextId() );
                        if ( this.isLoggable( Level.CONFIG ) )
                        {
                            this.log( Level.CONFIG, getMessage( "foundContext", schema.getContextId() ), null );
                        }
                    }
                }
            }

            if ( packageNames.length() == 0 )
            {
                throw new ModelException( getMessage( "missingSchemas" ) );
            }

            return JAXBContext.newInstance( packageNames.toString().substring( 1 ), this.getClassLoader() );
        }
        catch ( final ModletException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final JAXBException e )
        {
            if ( e.getLinkedException() != null )
            {
                throw new ModelException( e.getLinkedException().getMessage(), e.getLinkedException() );
            }
            else
            {
                throw new ModelException( e.getMessage(), e );
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see ModletContext#findModlets()
     * @see Modlets#getSchemas(java.lang.String)
     */
    @Override
    public Marshaller createMarshaller() throws ModelException
    {
        try
        {
            final StringBuilder packageNames = new StringBuilder();
            final StringBuilder schemaLocation = new StringBuilder();
            final Schemas schemas = this.getModlets().getSchemas( MODEL_IDENTIFIER );

            if ( schemas != null )
            {
                for ( final Iterator<org.jomc.model.modlet.Schema> s = schemas.getSchema().iterator(); s.hasNext(); )
                {
                    final org.jomc.model.modlet.Schema schema = s.next();

                    if ( schema.getContextId() != null )
                    {
                        packageNames.append( ':' ).append( schema.getContextId() );
                    }
                    if ( schema.getPublicId() != null && schema.getSystemId() != null )
                    {
                        schemaLocation.append( ' ' ).append( schema.getPublicId() ).append( ' ' ).
                            append( schema.getSystemId() );

                    }
                }
            }

            if ( packageNames.length() == 0 )
            {
                throw new ModelException( getMessage( "missingSchemas" ) );
            }

            final Marshaller m =
                JAXBContext.newInstance( packageNames.toString().substring( 1 ), this.getClassLoader() ).
                createMarshaller();

            if ( schemaLocation.length() != 0 )
            {
                m.setProperty( Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation.toString().substring( 1 ) );
            }

            return m;
        }
        catch ( final ModletException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( final JAXBException e )
        {
            if ( e.getLinkedException() != null )
            {
                throw new ModelException( e.getLinkedException().getMessage(), e.getLinkedException() );
            }
            else
            {
                throw new ModelException( e.getMessage(), e );
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see ModletContext#findModlets()
     * @see Modlets#getSchemas(java.lang.String)
     */
    @Override
    public Unmarshaller createUnmarshaller() throws ModelException
    {
        try
        {
            return this.createContext().createUnmarshaller();
        }
        catch ( final JAXBException e )
        {
            if ( e.getLinkedException() != null )
            {
                throw new ModelException( e.getLinkedException().getMessage(), e.getLinkedException() );
            }
            else
            {
                throw new ModelException( e.getMessage(), e );
            }
        }
    }

    /**
     * Gets the modlets of the instance.
     *
     * @return The modlets of the instance.
     *
     * @throws ModletException if getting the modlets fails.
     *
     * @see ModletContext#findModlets()
     */
    private Modlets getModlets() throws ModletException
    {
        Modlets modlets = this.cachedModlets.get();

        if ( modlets == null || System.getProperty( this.getClass().getName() + ".disableCaching" ) != null )
        {
            modlets = ModletContext.createModletContext( this.getClassLoader() ).findModlets();

            if ( modlets != null && this.isLoggable( Level.CONFIG ) )
            {
                for ( Modlet m : modlets.getModlet() )
                {
                    this.log( Level.CONFIG, getMessage( "foundModlet", m.getIdentifier(), m.getModel(), m.getName(),
                                                        m.getVendor(), m.getVersion() ), null );

                    if ( this.isLoggable( Level.FINE ) )
                    {
                        if ( m.getSchemas() != null )
                        {
                            for ( org.jomc.model.modlet.Schema s : m.getSchemas().getSchema() )
                            {
                                this.log( Level.FINE, getMessage(
                                    "foundSchema", m.getIdentifier(), s.getPublicId(), s.getSystemId(),
                                    s.getContextId(), s.getClasspathId() ), null );

                            }
                        }

                        if ( m.getServices() != null )
                        {
                            for ( Service s : m.getServices().getService() )
                            {
                                this.log( Level.FINE, getMessage(
                                    "foundService", m.getIdentifier(), s.getOrdinal(), s.getIdentifier(),
                                    s.getClazz() ), null );

                            }
                        }
                    }
                }
            }

            this.cachedModlets = new SoftReference<Modlets>( modlets );
        }

        return modlets;
    }

    /**
     * Searches the context for {@code META-INF/MANIFEST.MF} resources and returns a set of URIs of entries whose names
     * end with a known schema extension.
     *
     * @return Set of URIs of any matching entries.
     *
     * @throws IOException if reading fails.
     * @throws URISyntaxException if parsing fails.
     */
    private Set<URI> getSchemaResources() throws IOException, URISyntaxException
    {
        Set<URI> resources = this.cachedSchemaResources.get();

        if ( resources == null || System.getProperty( this.getClass().getName() + ".disableCaching" ) != null )
        {
            resources = new HashSet<URI>();
            final long t0 = System.currentTimeMillis();
            int count = 0;

            for ( final Enumeration<URL> e = this.getClassLoader().getResources( "META-INF/MANIFEST.MF" );
                  e.hasMoreElements(); )
            {
                count++;
                final URL manifestUrl = e.nextElement();
                final String externalForm = manifestUrl.toExternalForm();
                final String baseUrl = externalForm.substring( 0, externalForm.indexOf( "META-INF" ) );
                final InputStream manifestStream = manifestUrl.openStream();
                final Manifest mf = new Manifest( manifestStream );
                manifestStream.close();

                if ( this.isLoggable( Level.FINE ) )
                {
                    this.log( Level.FINE, getMessage( "processing", externalForm ), null );
                }

                for ( Map.Entry<String, Attributes> entry : mf.getEntries().entrySet() )
                {
                    for ( int i = SCHEMA_EXTENSIONS.length - 1; i >= 0; i-- )
                    {
                        if ( entry.getKey().toLowerCase().endsWith( '.' + SCHEMA_EXTENSIONS[i].toLowerCase() ) )
                        {
                            final URL schemaUrl = new URL( baseUrl + entry.getKey() );
                            resources.add( schemaUrl.toURI() );

                            if ( this.isLoggable( Level.FINE ) )
                            {
                                this.log( Level.FINE, getMessage( "foundSchemaCandidate", schemaUrl.toExternalForm() ),
                                          null );

                            }
                        }
                    }
                }
            }

            if ( this.isLoggable( Level.FINE ) )
            {
                this.log( Level.FINE, getMessage( "contextReport", count, "META-INF/MANIFEST.MF",
                                                  Long.valueOf( System.currentTimeMillis() - t0 ) ), null );

            }

            this.cachedSchemaResources = new SoftReference<Set<URI>>( resources );
        }

        return resources;
    }

    private static String getMessage( final String key, final Object... args )
    {
        return MessageFormat.format( ResourceBundle.getBundle(
            DefaultModelContext.class.getName().replace( '.', '/' ), Locale.getDefault() ).getString( key ), args );

    }

}

/**
 * {@code ErrorHandler} collecting {@code ModelValidationReport} details.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $Id$
 */
class ModelErrorHandler extends DefaultHandler
{

    /** The context of the instance. */
    private ModelContext context;

    /** The report of the instance. */
    private ModelValidationReport report;

    /**
     * Creates a new {@code ModelErrorHandler} instance taking a context.
     *
     * @param context The context of the instance.
     */
    public ModelErrorHandler( final ModelContext context )
    {
        this( context, null );
    }

    /**
     * Creates a new {@code ModelErrorHandler} instance taking a report to use for collecting validation events.
     *
     * @param context The context of the instance.
     * @param report A report to use for collecting validation events.
     */
    public ModelErrorHandler( final ModelContext context, final ModelValidationReport report )
    {
        super();
        this.context = context;
        this.report = report;
    }

    /**
     * Gets the report of the instance.
     *
     * @return The report of the instance.
     */
    public ModelValidationReport getReport()
    {
        if ( this.report == null )
        {
            this.report = new ModelValidationReport();
        }

        return this.report;
    }

    @Override
    public void warning( final SAXParseException exception ) throws SAXException
    {
        if ( this.context != null && this.context.isLoggable( Level.FINE ) )
        {
            this.context.log( Level.FINE, exception.getMessage(), exception );
        }

        this.getReport().getDetails().add( new ModelValidationReport.Detail(
            "W3C XML 1.0 Recommendation - Warning condition", Level.WARNING, exception.getMessage(), null ) );

    }

    @Override
    public void error( final SAXParseException exception ) throws SAXException
    {
        if ( this.context != null && this.context.isLoggable( Level.FINE ) )
        {
            this.context.log( Level.FINE, exception.getMessage(), exception );
        }

        this.getReport().getDetails().add( new ModelValidationReport.Detail(
            "W3C XML 1.0 Recommendation - Section 1.2 - Error", Level.SEVERE, exception.getMessage(), null ) );

    }

    @Override
    public void fatalError( final SAXParseException exception ) throws SAXException
    {
        if ( this.context != null && this.context.isLoggable( Level.FINE ) )
        {
            this.context.log( Level.FINE, exception.getMessage(), exception );
        }

        this.getReport().getDetails().add( new ModelValidationReport.Detail(
            "W3C XML 1.0 Recommendation - Section 1.2 - Fatal Error", Level.SEVERE, exception.getMessage(), null ) );

    }

}
