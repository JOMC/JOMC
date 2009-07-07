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

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.jomc.model.bootstrap.Schema;
import org.jomc.model.bootstrap.Schemas;
import org.jomc.model.util.ParseException;
import org.jomc.model.util.TokenMgrError;
import org.jomc.model.util.VersionParser;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Default {@code ModelManager} implementation.
 *
 * <p><b>Classpath support</b><ul>
 * <li>{@link #getClassLoader() }</li>
 * <li>{@link #setClassLoader(java.lang.ClassLoader) }</li>
 * <li>{@link #getClasspathModule(org.jomc.model.Modules) }</li>
 * <li>{@link #getClasspathModules(java.lang.String) }</li>
 * </ul></p>
 *
 * <p><b>Logging</b><ul>
 * <li>{@link #getListeners() }</li>
 * <li>{@link #log(java.util.logging.Level, java.lang.String, java.lang.Throwable) }</li>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a>
 * @version $Id$
 */
public class DefaultModelManager implements ModelManager
{
    // SECTION-START[ModelManager]

    public ObjectFactory getObjectFactory()
    {
        if ( this.objectFactory == null )
        {
            this.objectFactory = new ObjectFactory();
        }

        return this.objectFactory;
    }

    public EntityResolver getEntityResolver()
    {
        if ( this.entityResolver == null )
        {
            this.entityResolver = new EntityResolver()
            {

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
                        final Schema s = getSchemas().getSchema( publicId );
                        if ( s != null )
                        {
                            schemaSource = new InputSource();
                            schemaSource.setPublicId( publicId );

                            if ( s.getClasspathId() != null )
                            {
                                schemaSource.setSystemId( getClassLoader().getResource( s.getClasspathId() ).
                                    toExternalForm() );

                            }
                            else
                            {
                                schemaSource.setSystemId( s.getSystemId() );
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

                                for ( URL url : getSchemaResources() )
                                {
                                    if ( url.getPath().endsWith( schemaName ) )
                                    {
                                        schemaSource = new InputSource();
                                        schemaSource.setPublicId( publicId );
                                        schemaSource.setSystemId( url.toExternalForm() );

                                        log( Level.FINE, getMessage( "resolvedSystemIdUri", new Object[]
                                            {
                                                systemUri.toASCIIString(),
                                                schemaSource.getSystemId()
                                            } ), null );

                                        break;
                                    }
                                }
                            }
                            else
                            {
                                log( Level.WARNING, getMessage( "unsupportedSystemIdUri", new Object[]
                                    {
                                        systemId, systemUri.toASCIIString()
                                    } ), null );

                                schemaSource = null;
                            }
                        }
                    }
                    catch ( URISyntaxException e )
                    {
                        log( Level.WARNING, getMessage( "unsupportedSystemIdUri", new Object[]
                            {
                                systemId, e.getMessage()
                            } ), null );

                        schemaSource = null;
                    }
                    catch ( JAXBException e )
                    {
                        throw (IOException) new IOException( e.getMessage() ).initCause( e );
                    }

                    return schemaSource;
                }

            };
        }

        return this.entityResolver;
    }

    public LSResourceResolver getLSResourceResolver()
    {
        if ( this.resourceResolver == null )
        {
            this.resourceResolver = new LSResourceResolver()
            {

                public LSInput resolveResource( final String type, final String namespaceURI, final String publicId,
                                                final String systemId, final String baseURI )
                {
                    LSInput input = null;
                    try
                    {
                        final InputSource schemaSource = getEntityResolver().resolveEntity( publicId, systemId );

                        if ( schemaSource != null )
                        {
                            input = new LSInput()
                            {

                                public Reader getCharacterStream()
                                {
                                    return schemaSource.getCharacterStream();
                                }

                                public void setCharacterStream( final Reader characterStream )
                                {
                                    throw new UnsupportedOperationException();
                                }

                                public InputStream getByteStream()
                                {
                                    return schemaSource.getByteStream();
                                }

                                public void setByteStream( final InputStream byteStream )
                                {
                                    throw new UnsupportedOperationException();
                                }

                                public String getStringData()
                                {
                                    return null;
                                }

                                public void setStringData( final String stringData )
                                {
                                    throw new UnsupportedOperationException();
                                }

                                public String getSystemId()
                                {
                                    return schemaSource.getSystemId();
                                }

                                public void setSystemId( final String systemId )
                                {
                                    throw new UnsupportedOperationException();
                                }

                                public String getPublicId()
                                {
                                    return schemaSource.getPublicId();
                                }

                                public void setPublicId( final String publicId )
                                {
                                    throw new UnsupportedOperationException();
                                }

                                public String getBaseURI()
                                {
                                    return null;
                                }

                                public void setBaseURI( final String baseURI )
                                {
                                    throw new UnsupportedOperationException();
                                }

                                public String getEncoding()
                                {
                                    return schemaSource.getEncoding();
                                }

                                public void setEncoding( final String encoding )
                                {
                                    throw new UnsupportedOperationException();
                                }

                                public boolean getCertifiedText()
                                {
                                    return false;
                                }

                                public void setCertifiedText( final boolean certifiedText )
                                {
                                    throw new UnsupportedOperationException();
                                }

                            };
                        }

                    }
                    catch ( SAXException e )
                    {
                        log( Level.WARNING, getMessage( "unsupportedSystemIdUri", new Object[]
                            {
                                systemId, e.getMessage()
                            } ), null );

                        input = null;
                    }
                    catch ( IOException e )
                    {
                        log( Level.WARNING, getMessage( "unsupportedSystemIdUri", new Object[]
                            {
                                systemId, e.getMessage()
                            } ), null );

                        input = null;
                    }

                    return input;
                }

            };
        }

        return this.resourceResolver;
    }

    public javax.xml.validation.Schema getSchema() throws IOException, SAXException, JAXBException
    {
        final SchemaFactory f = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
        final List<Source> sources = new ArrayList<Source>( this.getSchemas().getSchema().size() );

        for ( Schema s : this.getSchemas().getSchema() )
        {
            sources.add( new StreamSource( this.getClassLoader().getResourceAsStream( s.getClasspathId() ),
                                           s.getSystemId() ) );

        }

        return f.newSchema( sources.toArray( new Source[ sources.size() ] ) );
    }

    public JAXBContext getContext() throws IOException, SAXException, JAXBException
    {
        final StringBuffer context = new StringBuffer();

        for ( Iterator<Schema> s = this.getSchemas().getSchema().iterator(); s.hasNext(); )
        {
            final Schema schema = s.next();
            context.append( schema.getContextId() );
            if ( s.hasNext() )
            {
                context.append( ':' );
            }
        }

        return JAXBContext.newInstance( context.toString(), this.getClassLoader() );
    }

    public Marshaller getMarshaller( final boolean validating, final boolean formattedOutput )
        throws IOException, SAXException, JAXBException
    {
        final Marshaller m = this.getContext().createMarshaller();
        final StringBuffer schemaLocation = new StringBuffer();

        for ( Iterator<Schema> s = this.getSchemas().getSchema().iterator(); s.hasNext(); )
        {
            final Schema schema = s.next();
            schemaLocation.append( schema.getPublicId() ).append( ' ' ).append( schema.getSystemId() );
            if ( s.hasNext() )
            {
                schemaLocation.append( ' ' );
            }
        }

        m.setProperty( Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation.toString() );
        m.setProperty( Marshaller.JAXB_ENCODING, "UTF-8" );
        m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.valueOf( formattedOutput ) );

        if ( validating )
        {
            m.setSchema( this.getSchema() );
        }

        return m;
    }

    public Unmarshaller getUnmarshaller( final boolean validating ) throws IOException, SAXException, JAXBException
    {
        final Unmarshaller u = this.getContext().createUnmarshaller();

        if ( validating )
        {
            u.setSchema( this.getSchema() );
        }

        return u;
    }

    public void validateModelObject( final JAXBElement<? extends ModelObject> modelObject )
        throws ModelException, IOException, SAXException, JAXBException
    {
        if ( modelObject == null )
        {
            throw new NullPointerException( "modelObject" );
        }

        final StringWriter stringWriter = new StringWriter();
        final List<ModelException.Detail> details = new LinkedList<ModelException.Detail>();
        final Validator validator = this.getSchema().newValidator();
        validator.setErrorHandler( new ErrorHandler()
        {

            public void warning( final SAXParseException exception ) throws SAXException
            {
                if ( exception.getMessage() != null )
                {
                    details.add( new ModelException.Detail( Level.WARNING, exception.getMessage() ) );
                }
            }

            public void error( final SAXParseException exception ) throws SAXException
            {
                if ( exception.getMessage() != null )
                {
                    details.add( new ModelException.Detail( Level.SEVERE, exception.getMessage() ) );
                }

                throw exception;
            }

            public void fatalError( final SAXParseException exception ) throws SAXException
            {
                if ( exception.getMessage() != null )
                {
                    details.add( new ModelException.Detail( Level.SEVERE, exception.getMessage() ) );
                }

                throw exception;
            }

        } );

        this.getMarshaller( false, false ).marshal( modelObject, stringWriter );

        try
        {
            validator.validate( new StreamSource( new StringReader( stringWriter.toString() ) ) );
        }
        catch ( SAXException e )
        {
            final ModelException modelException = new ModelException( e.getMessage(), e );
            modelException.getDetails().addAll( details );
            throw modelException;
        }
    }

    public void validateModules( final Modules modules )
        throws ModelException, IOException, SAXException, JAXBException
    {
        if ( modules == null )
        {
            throw new NullPointerException( "modules" );
        }

        this.validateModelObject( this.getObjectFactory().createModules( modules ) );
        final List<ModelException.Detail> details = new LinkedList<ModelException.Detail>();

        try
        {
            for ( Module m : modules.getModule() )
            {
                if ( m.getImplementations() != null )
                {
                    for ( Implementation i : m.getImplementations().getImplementation() )
                    {
                        final List<SpecificationReference> specs =
                            modules.getSpecifications( i.getIdentifier() );

                        final Dependencies deps = modules.getDependencies( i.getIdentifier() );

                        if ( specs != null )
                        {
                            for ( SpecificationReference r : specs )
                            {
                                final Specification s = modules.getSpecification( r.getIdentifier() );

                                if ( s != null && r.getVersion() != null && s.getVersion() != null &&
                                     VersionParser.compare( r.getVersion(), s.getVersion() ) != 0 )
                                {
                                    details.add( this.newIncompatibleImplementationDetail(
                                        this.getObjectFactory().createImplementation( i ), i.getIdentifier(),
                                        r.getIdentifier(), r.getVersion(), s.getVersion() ) );

                                }
                            }
                        }

                        if ( deps != null )
                        {
                            for ( Dependency d : deps.getDependency() )
                            {
                                final Specification s = modules.getSpecification( d.getIdentifier() );

                                if ( s != null && s.getVersion() != null && d.getVersion() != null &&
                                     VersionParser.compare( d.getVersion(), s.getVersion() ) > 0 )
                                {
                                    details.add( this.newIncompatibleDependencyDetail(
                                        this.getObjectFactory().createDependency( d ), i.getIdentifier(),
                                        d.getIdentifier(), d.getVersion(), s.getVersion() ) );

                                }

                                if ( s.getScope() != Scope.MULTITON && d.getProperties() != null &&
                                     !d.getProperties().getProperty().isEmpty() )
                                {
                                    details.add( this.newPropertyOverwriteConstraintDetail(
                                        this.getObjectFactory().createDependency( d ), i.getIdentifier(), d.getName(),
                                        s.getIdentifier() ) );

                                }

                                final Implementations available =
                                    modules.getImplementations( i.getIdentifier(), d.getName() );

                                if ( !d.isOptional() &&
                                     ( available == null || available.getImplementation().isEmpty() ) )
                                {
                                    details.add( this.newMandatoryDependencyConstraintDetail(
                                        this.getObjectFactory().createDependency( d ), i.getIdentifier(),
                                        d.getName() ) );

                                }
                            }
                        }
                    }
                }

                if ( m.getSpecifications() != null )
                {
                    for ( Specification s : m.getSpecifications().getSpecification() )
                    {
                        final Implementations impls = modules.getImplementations( s.getIdentifier() );

                        if ( impls != null )
                        {
                            final Map<String, Implementation> map = new HashMap<String, Implementation>();

                            for ( Implementation i : impls.getImplementation() )
                            {
                                if ( map.containsKey( i.getName() ) )
                                {
                                    details.add( this.newImplementationNameConstraintDetail(
                                        this.getObjectFactory().createSpecification( s ), s.getIdentifier(),
                                        i.getIdentifier() + ", " + map.get( i.getName() ).getIdentifier() ) );

                                }
                            }

                            if ( s.getMultiplicity() == Multiplicity.ONE && impls.getImplementation().size() > 1 )
                            {
                                details.add( this.newMultiplicityConstraintDetail(
                                    this.getObjectFactory().createSpecification( s ), impls.getImplementation().size(),
                                    s.getIdentifier(), 1, s.getMultiplicity() ) );

                            }
                        }
                    }
                }
            }

            if ( !details.isEmpty() )
            {
                final ModelException modelException = new ModelException();
                modelException.getDetails().addAll( details );
                throw modelException;
            }
        }
        catch ( TokenMgrError e )
        {
            throw new ModelException( e.getMessage(), e );
        }
        catch ( ParseException e )
        {
            throw new ModelException( e.getMessage(), e );
        }
    }

    public Object createObject( final Modules modules, final String specification, final String name,
                                final ClassLoader classLoader ) throws InstantiationException
    {
        if ( modules == null )
        {
            throw new NullPointerException( "modules" );
        }
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }
        if ( name == null )
        {
            throw new NullPointerException( "name" );
        }
        if ( classLoader == null )
        {
            throw new NullPointerException( "classLoader" );
        }

        Object instance = null;

        try
        {
            final Specification s = modules.getSpecification( specification );
            final Implementation i = modules.getImplementation( specification, name );

            if ( s != null && i != null && i.getClazz() != null )
            {
                final Class specClass = Class.forName( s.getIdentifier(), true, classLoader );
                final Class implClass = Class.forName( i.getClazz(), true, classLoader );

                if ( Modifier.isPublic( implClass.getModifiers() ) )
                {
                    Constructor ctor = null;
                    try
                    {
                        ctor = implClass.getConstructor( NO_CLASSES );
                    }
                    catch ( NoSuchMethodException e )
                    {
                        this.log( Level.FINE, this.getMessage( "noSuchMethod", new Object[]
                            {
                                e.getMessage()
                            } ), null );

                        ctor = null;
                    }

                    if ( ctor != null && specClass.isAssignableFrom( implClass ) )
                    {
                        instance = implClass.newInstance();
                    }
                    else
                    {
                        final StringBuffer methodNames = new StringBuffer().append( '[' );
                        Method factoryMethod = null;
                        String methodName = null;

                        char[] c = i.getName().toCharArray();
                        c[0] = Character.toUpperCase( c[0] );
                        methodName = "get" + String.valueOf( c );

                        boolean javaIdentifier = Character.isJavaIdentifierStart( c[0] );
                        if ( javaIdentifier )
                        {
                            for ( int idx = c.length - 1; idx > 0; idx-- )
                            {
                                if ( !Character.isJavaIdentifierPart( c[idx] ) )
                                {
                                    javaIdentifier = false;
                                    break;
                                }
                            }
                        }

                        if ( javaIdentifier )
                        {
                            methodNames.append( methodName );
                            factoryMethod = this.getFactoryMethod( implClass, specClass, methodName );
                        }

                        if ( factoryMethod == null )
                        {
                            c = s.getIdentifier().substring( s.getIdentifier().lastIndexOf( '.' ) + 1 ).toCharArray();
                            c[0] = Character.toUpperCase( c[0] );
                            methodName = "get" + String.valueOf( c );
                            methodNames.append( ", " ).append( methodName );
                            factoryMethod = this.getFactoryMethod( implClass, specClass, "get" + String.valueOf( c ) );
                        }

                        if ( factoryMethod == null )
                        {
                            methodName = "getObject";
                            methodNames.append( ", " ).append( methodName );
                            factoryMethod = this.getFactoryMethod( implClass, null, methodName );
                        }

                        if ( factoryMethod == null )
                        {
                            throw new InstantiationException( this.getMessage( "missingFactoryMethod", new Object[]
                                {
                                    implClass.getName(), specClass.getName(), methodNames.append( ']' ).toString()
                                } ) );

                        }

                        if ( Modifier.isStatic( factoryMethod.getModifiers() ) )
                        {
                            instance = factoryMethod.invoke( null, NO_OBJECTS );
                        }
                        else if ( ctor != null )
                        {
                            instance = factoryMethod.invoke( ctor.newInstance(), NO_OBJECTS );
                        }
                        else
                        {
                            throw new InstantiationException( this.getMessage( "missingFactoryMethod", new Object[]
                                {
                                    implClass.getName(), specClass.getName(), methodNames.append( ']' ).toString()
                                } ) );

                        }
                    }
                }
            }

            return instance;
        }
        catch ( InvocationTargetException e )
        {
            throw (InstantiationException) new InstantiationException().initCause( e.getTargetException() != null
                                                                                   ? e.getTargetException() : e );

        }
        catch ( IllegalAccessException e )
        {
            throw (InstantiationException) new InstantiationException().initCause( e );
        }
        catch ( ClassNotFoundException e )
        {
            throw (InstantiationException) new InstantiationException().initCause( e );
        }
    }

    // SECTION-END
    // SECTION-START[DefaultModelManager]
    
    /** Listener interface. */
    public static abstract class Listener
    {

        /**
         * Get called on logging.
         *
         * @param level The level of the event.
         * @param message The message of the event or {@code null}.
         * @param t The throwable of the event or {@code null}.
         */
        public abstract void onLog( Level level, String message, Throwable t );

    }

    /** Constant for the classpath module name. */
    public static final String CLASSPATH_MODULE_NAME = "Java Classpath";

    /** Classpath location searched for documents by default. */
    public static final String DEFAULT_DOCUMENT_LOCATION = "META-INF/jomc.xml";

    /** Classpath location of the bootstrap schema. */
    private static final String BOOTSTRAP_SCHEMA_LOCATION = "org/jomc/model/bootstrap/jomc-bootstrap-1.0.xsd";

    /** Classpath location searched for bootstrap resources. */
    private static final String BOOTSTRAP_DOCUMENT_LOCATION = "META-INF/jomc-bootstrap.xml";

    /** JAXB context of the bootstrap schema. */
    private static final String BOOTSTRAP_CONTEXT = "org.jomc.model.bootstrap";

    /** Supported schema name extensions. */
    private static final String[] SCHEMA_EXTENSIONS = new String[]
    {
        "xsd"
    };

    /** Empty {@code Class} array. */
    private static final Class[] NO_CLASSES =
    {
    };

    /** Empty {@code Object} array. */
    private static final Object[] NO_OBJECTS =
    {
    };

    /** Classloader of the instance. */
    private ClassLoader classLoader;

    /** The entity resolver of the instance. */
    private EntityResolver entityResolver;

    /** The L/S resolver of the instance. */
    private LSResourceResolver resourceResolver;

    /** The bootstrap schema. */
    private javax.xml.validation.Schema bootstrapSchema;

    /** URLs of all available classpath schema resources. */
    private Set<URL> schemaResources;

    /** Schemas of the instance. */
    private Schemas schemas;

    /** Object factory of the instance. */
    private ObjectFactory objectFactory;

    /** The listeners of the instance. */
    private List<Listener> listeners;

    /** Creates a new {@code DefaultModelManager} instance. */
    public DefaultModelManager()
    {
        super();
    }

    /**
     * Sets the object factory of the instance.
     *
     * @param value The new object factory of the instance.
     */
    public void setObjectFactory( final ObjectFactory value )
    {
        this.objectFactory = value;
    }

    /**
     * Sets the entity resolver of the instance.
     *
     * @param value The new entity resolver of the instance.
     */
    public void setEntityResolver( final EntityResolver value )
    {
        this.entityResolver = value;
    }

    /**
     * Sets the L/S resolver of the instance.
     *
     * @param value The new L/S resolver of the instance.
     */
    public void setLSResourceResolver( final LSResourceResolver value )
    {
        this.resourceResolver = value;
    }

    /**
     * Gets the list of registered listeners.
     *
     * @return The list of registered listeners.
     */
    public List<Listener> getListeners()
    {
        if ( this.listeners == null )
        {
            this.listeners = new LinkedList<Listener>();
        }

        return this.listeners;
    }

    /**
     * Gets the schemas backing the instance.
     *
     * @return The schemas backing the instance.
     *
     * @throws IOException if reading schema resources fails.
     * @throws SAXException if parsing schema resources fails.
     * @throws JAXBException if unmarshalling schema resources fails.
     *
     * @see #BOOTSTRAP_DOCUMENT_LOCATION
     */
    public Schemas getSchemas() throws IOException, JAXBException, SAXException
    {
        if ( this.schemas == null )
        {
            this.schemas = new Schemas();

            final JAXBContext ctx = JAXBContext.newInstance( BOOTSTRAP_CONTEXT, this.getClassLoader() );
            final Unmarshaller u = ctx.createUnmarshaller();
            final Enumeration<URL> e = this.getClassLoader().getResources( BOOTSTRAP_DOCUMENT_LOCATION );
            u.setSchema( this.getBootstrapSchema() );

            while ( e.hasMoreElements() )
            {
                final URL url = e.nextElement();
                this.log( Level.FINE, this.getMessage( "processing", new Object[]
                    {
                        url.toExternalForm()
                    } ), null );

                final Object content = u.unmarshal( url );
                if ( content instanceof JAXBElement )
                {
                    final JAXBElement element = (JAXBElement) content;
                    if ( element.getValue() instanceof Schema )
                    {
                        final Schema schema = (Schema) element.getValue();
                        this.log( Level.FINE, this.getMessage( "addingSchema", new Object[]
                            {
                                schema.getPublicId(), schema.getSystemId(), schema.getContextId(),
                                schema.getClasspathId()
                            } ), null );

                        this.schemas.getSchema().add( (Schema) element.getValue() );
                    }
                    else if ( element.getValue() instanceof Schemas )
                    {
                        for ( Schema schema : ( (Schemas) element.getValue() ).getSchema() )
                        {
                            this.log( Level.FINE, this.getMessage( "addingSchema", new Object[]
                                {
                                    schema.getPublicId(), schema.getSystemId(), schema.getContextId(),
                                    schema.getClasspathId()
                                } ), null );

                            this.schemas.getSchema().add( schema );
                        }
                    }
                }
            }
        }

        return this.schemas;
    }

    /**
     * Gets modules by searching the classloader of the instance for resources.
     *
     * @param location The location to search at.
     *
     * @return All resources from the classloader of the instance matching {@code location}.
     *
     * @throws NullPointerException if {@code location} is {@code null}.
     * @throws IOException if reading resources fails.
     * @throws SAXException if parsing schema resources fails.
     * @throws JAXBException if unmarshalling schema resources fails.
     *
     * @see #DEFAULT_DOCUMENT_LOCATION
     */
    public Modules getClasspathModules( final String location ) throws IOException, SAXException, JAXBException
    {
        if ( location == null )
        {
            throw new NullPointerException( "location" );
        }

        final Text text = new Text();
        text.setLanguage( "en" );
        text.setValue( this.getMessage( "classpathModulesInfo", new Object[]
            {
                location
            } ) );

        Modules mods = new Modules();
        mods.setDocumentation( new Texts() );
        mods.getDocumentation().setDefaultLanguage( "en" );
        mods.getDocumentation().getText().add( text );

        final Unmarshaller u = this.getUnmarshaller( true );
        final Enumeration<URL> resources = this.getClassLoader().getResources( location );

        while ( resources.hasMoreElements() )
        {
            final URL url = resources.nextElement();

            this.log( Level.FINE, this.getMessage( "processing", new Object[]
                {
                    url.toExternalForm()
                } ), null );

            final Object content = ( (JAXBElement) u.unmarshal( url ) ).getValue();

            if ( content instanceof Module )
            {
                mods.getModule().add( (Module) content );
            }
            else if ( content instanceof Modules )
            {
                this.log( Level.FINE, this.getMessage( "usingModules", new Object[]
                    {
                        ( mods.getDocumentation() != null
                          ? mods.getDocumentation().getText( Locale.getDefault().getLanguage() ).getValue()
                          : "<>" ),
                        url.toExternalForm()
                    } ), null );

                mods = (Modules) content;
                break;
            }
        }

        return mods;
    }

    /**
     * Gets a module holding model objects resolved by inspecting the classloader of the instance.
     * <p>This method searches the given modules for unresolved references and tries to resolve each unresolved
     * reference by inspecting the classloader of the instance.</p>
     *
     * @param modules The modules to resolve by inspecting the classloader of the instance.
     *
     * @return A module holding model objects resolved by inspecting the classloader of the instance or {@code null} if
     * nothing could be resolved.
     *
     * @see #CLASSPATH_MODULE_NAME
     */
    public Module getClasspathModule( final Modules modules )
    {
        final Module module = new Module();
        module.setVersion( System.getProperty( "java.specification.version" ) );
        module.setName( CLASSPATH_MODULE_NAME );

        this.resolveClasspath( modules, module );

        final boolean resolved = ( module.getSpecifications() != null &&
                                   !module.getSpecifications().getSpecification().isEmpty() ) ||
                                 ( module.getImplementations() != null &&
                                   !module.getImplementations().getImplementation().isEmpty() );

        return resolved ? module : null;
    }

    /**
     * Gets the classloader of the instance.
     *
     * @return The classloader of the instance.
     */
    public ClassLoader getClassLoader()
    {
        if ( this.classLoader == null )
        {
            this.classLoader = this.getClass().getClassLoader();
            if ( this.classLoader == null )
            {
                this.classLoader = ClassLoader.getSystemClassLoader();
            }

        }

        return this.classLoader;
    }

    /**
     * Sets the classloader of the instance.
     *
     * @param value The new classloader of the instance.
     */
    public void setClassLoader( final ClassLoader value )
    {
        this.classLoader = value;
        this.bootstrapSchema = null;
        this.schemaResources = null;
        this.schemas = null;
        this.entityResolver = null;
        this.resourceResolver = null;
    }

    /**
     * Notifies registered listeners.
     *
     * @param level The level of the event.
     * @param message The message of the event.
     * @param throwable The throwable of the event.
     */
    protected void log( final Level level, final String message, final Throwable throwable )
    {
        for ( Listener l : this.getListeners() )
        {
            l.onLog( level, message, throwable );
        }
    }

    /**
     * Resolves references by inspecting the classloader of the instance.
     *
     * @param modules The modules to resolve.
     * @param cpModule The module for resolved references.
     *
     * @throws NullPointerException if {@code cpModule} is {@code null}.
     */
    private void resolveClasspath( final Modules modules, final Module cpModule )
    {
        for ( Module m : modules.getModule() )
        {
            if ( m.getSpecifications() != null )
            {
                this.resolveClasspath( modules, m.getSpecifications(), cpModule );
            }

            if ( m.getImplementations() != null )
            {
                this.resolveClasspath( modules, m.getImplementations(), cpModule );
            }

        }
    }

    private void resolveClasspath( final Modules modules, final SpecificationReference ref, final Module cpModule )
    {
        if ( modules.getSpecification( ref.getIdentifier() ) == null )
        {
            this.resolveClasspath( ref.getIdentifier(), cpModule );
        }
    }

    private void resolveClasspath( final Modules modules, final Specifications references, final Module cpModule )
    {
        for ( SpecificationReference ref : references.getReference() )
        {
            this.resolveClasspath( modules, ref, cpModule );
        }

    }

    private void resolveClasspath( final Modules modules, final Implementations implementations, final Module cpModule )
    {
        for ( Implementation implementation : implementations.getImplementation() )
        {
            if ( implementation.getSpecifications() != null )
            {
                this.resolveClasspath( modules, implementation.getSpecifications(), cpModule );
            }

            if ( implementation.getDependencies() != null )
            {
                this.resolveClasspath( modules, implementation.getDependencies(), cpModule );
            }
        }
    }

    private void resolveClasspath( final Modules modules, final Dependencies dependencies, final Module cpModule )
    {
        for ( Dependency dependency : dependencies.getDependency() )
        {
            this.resolveClasspath( modules, dependency, cpModule );
        }
    }

    private void resolveClasspath( final String identifier, final Module cpModule )
    {
        Specification specification =
            cpModule.getSpecifications() == null ? null
            : cpModule.getSpecifications().getSpecification( identifier );

        if ( specification == null )
        {
            try
            {
                final Class classpathSpec = Class.forName( identifier, true, this.getClassLoader() );
                if ( Modifier.isPublic( classpathSpec.getModifiers() ) )
                {
                    String vendor = null;
                    String version = null;

                    if ( classpathSpec.getPackage() != null )
                    {
                        vendor = classpathSpec.getPackage().getSpecificationVendor();
                        version = classpathSpec.getPackage().getSpecificationVersion();
                    }

                    specification = new Specification();
                    specification.setIdentifier( identifier );
                    specification.setMultiplicity( Multiplicity.MANY );
                    specification.setScope( Scope.MULTITON );
                    specification.setVendor( vendor );
                    specification.setVersion( version );

                    this.log( Level.FINE, this.getMessage( "classpathSpecification", new Object[]
                        {
                            specification.getIdentifier(),
                            specification.getScope().value(),
                            specification.getMultiplicity().value()
                        } ), null );


                    if ( cpModule.getSpecifications() == null )
                    {
                        cpModule.setSpecifications( new Specifications() );
                    }

                    cpModule.getSpecifications().getSpecification().add( specification );

                    this.resolveClasspath( specification, cpModule );
                }

            }
            catch ( ClassNotFoundException e )
            {
                this.log( Level.FINE, this.getMessage( "noSuchClass", new Object[]
                    {
                        e.getMessage()
                    } ), null );

            }
        }
    }

    private void resolveClasspath( final Specification specification, final Module cpModule )
    {
        if ( specification == null )
        {
            throw new NullPointerException( "specification" );
        }

        Implementation implementation =
            cpModule.getImplementations() == null ? null
            : cpModule.getImplementations().getImplementation( specification.getIdentifier() );

        if ( implementation == null )
        {
            String name = null;

            try
            {
                final Class classpathImpl = Class.forName( specification.getIdentifier(), true, this.getClassLoader() );
                boolean classpathImplementation = false;

                if ( Modifier.isPublic( classpathImpl.getModifiers() ) )
                {
                    if ( !Modifier.isAbstract( classpathImpl.getModifiers() ) )
                    {
                        try
                        {
                            classpathImpl.getConstructor( new Class[ 0 ] );
                            name = "init";
                            classpathImplementation = true;
                        }
                        catch ( NoSuchMethodException e )
                        {
                            this.log( Level.FINE, this.getMessage( "noSuchMethod", new Object[]
                                {
                                    e.getMessage()
                                } ), null );

                        }

                    }

                    if ( !classpathImplementation )
                    {
                        final char[] c = specification.getIdentifier().substring(
                            specification.getIdentifier().lastIndexOf( '.' ) + 1 ).toCharArray();

                        name = String.valueOf( c );
                        c[0] = Character.toUpperCase( c[0] );

                        if ( this.checkFactoryMethod( classpathImpl, classpathImpl, "getDefault" ) )
                        {
                            name = "default";
                            classpathImplementation = true;
                        }
                        else if ( this.checkFactoryMethod( classpathImpl, classpathImpl, "getInstance" ) )
                        {
                            name = "instance";
                            classpathImplementation = true;
                        }
                        else if ( this.checkFactoryMethod( classpathImpl, classpathImpl, "get" + String.valueOf( c ) ) )
                        {
                            classpathImplementation = true;
                        }

                    }

                    if ( classpathImplementation )
                    {
                        String vendor = null;
                        String version = null;
                        if ( classpathImpl.getPackage() != null )
                        {
                            vendor = classpathImpl.getPackage().getImplementationVendor();
                            version = classpathImpl.getPackage().getImplementationVersion();
                        }

                        implementation = new Implementation();
                        implementation.setVendor( vendor );
                        implementation.setFinal( true );
                        implementation.setName( name );
                        implementation.setIdentifier( specification.getIdentifier() );
                        implementation.setClazz( specification.getIdentifier() );
                        implementation.setVersion( version );

                        final Specifications implemented = new Specifications();
                        final SpecificationReference ref = new SpecificationReference();
                        ref.setIdentifier( specification.getIdentifier() );
                        ref.setVersion( specification.getVersion() );
                        implemented.getReference().add( ref );
                        implementation.setSpecifications( implemented );

                        this.log( Level.FINE, this.getMessage( "classpathImplementation", new Object[]
                            {
                                implementation.getIdentifier(),
                                specification.getIdentifier(),
                                implementation.getName()
                            } ), null );

                        if ( cpModule.getImplementations() == null )
                        {
                            cpModule.setImplementations( new Implementations() );
                        }

                        cpModule.getImplementations().getImplementation().add( implementation );
                    }
                    else
                    {
                        this.log( Level.FINE, this.getMessage( "noClasspathImplementation", new Object[]
                            {
                                specification.getIdentifier()
                            } ), null );

                    }
                }
            }
            catch ( ClassNotFoundException e )
            {
                this.log( Level.FINE, this.getMessage( "noSuchClass", new Object[]
                    {
                        e.getMessage()
                    } ), null );

            }
        }
    }

    private boolean checkFactoryMethod( final Class clazz, final Class type, final String methodName )
    {
        boolean factoryMethod = false;

        try
        {
            final Method m = clazz.getMethod( methodName, new Class[ 0 ] );
            factoryMethod = Modifier.isStatic( m.getModifiers() ) && type.isAssignableFrom( m.getReturnType() );
        }
        catch ( NoSuchMethodException e )
        {
            this.log( Level.FINE, this.getMessage( "noSuchMethod", new Object[]
                {
                    e.getMessage()
                } ), null );

            factoryMethod = false;
        }

        return factoryMethod;
    }

    private Method getFactoryMethod( final Class clazz, final Class type, final String methodName )
    {
        Method m = null;

        try
        {
            m = clazz.getMethod( methodName, new Class[ 0 ] );
            if ( type != null && !type.isAssignableFrom( m.getReturnType() ) )
            {
                m = null;
            }

        }
        catch ( NoSuchMethodException e )
        {
            this.log( Level.FINE, this.getMessage( "noSuchMethod", new Object[]
                {
                    e.getMessage()
                } ), null );

            m = null;
        }

        return m;
    }

    /**
     * Searches all available {@code META-INF/MANIFEST.MF} resources and gets a set containing URLs of entries whose
     * name end with a known schema extension.
     *
     * @return URLs of any matching entries.
     *
     * @throws IOException if reading or parsing fails.
     */
    private Set<URL> getSchemaResources() throws IOException
    {
        if ( this.schemaResources == null )
        {
            this.schemaResources = new HashSet<URL>();

            for ( Enumeration<URL> e = this.getClassLoader().getResources( "META-INF/MANIFEST.MF" );
                  e.hasMoreElements(); )
            {
                final URL manifestUrl = e.nextElement();
                final String externalForm = manifestUrl.toExternalForm();
                final String baseUrl = externalForm.substring( 0, externalForm.indexOf( "META-INF" ) );
                final InputStream manifestStream = manifestUrl.openStream();
                final Manifest mf = new Manifest( manifestStream );
                manifestStream.close();

                for ( Map.Entry<String, Attributes> entry : mf.getEntries().entrySet() )
                {
                    for ( int i = SCHEMA_EXTENSIONS.length - 1; i >= 0; i-- )
                    {
                        if ( entry.getKey().toLowerCase().endsWith( '.' + SCHEMA_EXTENSIONS[i].toLowerCase() ) )
                        {
                            final URL schemaUrl = new URL( baseUrl + entry.getKey() );
                            this.schemaResources.add( schemaUrl );
                            this.log( Level.FINE, this.getMessage( "processing", new Object[]
                                {
                                    schemaUrl.toExternalForm()
                                } ), null );

                        }
                    }
                }
            }
        }

        return this.schemaResources;
    }

    /**
     * Gets the bootstrap schema.
     *
     * @return The bootstrap schema.
     *
     * @throws SAXException if parsing the bootstrap schema fails.
     */
    private javax.xml.validation.Schema getBootstrapSchema() throws SAXException
    {
        if ( this.bootstrapSchema == null )
        {
            final URL url = this.getClassLoader().getResource( BOOTSTRAP_SCHEMA_LOCATION );
            this.log( Level.FINE, this.getMessage( "processing", new Object[]
                {
                    url.toExternalForm()
                } ), null );

            this.bootstrapSchema = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI ).newSchema( url );
        }

        return this.bootstrapSchema;
    }

    private String getMessage( final String key, final Object args )
    {
        return new MessageFormat( ResourceBundle.getBundle( "org/jomc/model/DefaultModelManager", Locale.getDefault() ).
            getString( key ) ).format( args );

    }

    private ModelException.Detail newIncompatibleImplementationDetail( final JAXBElement element,
                                                                       final String implementation,
                                                                       final String specification,
                                                                       final String implementedVersion,
                                                                       final String specifiedVersion )
    {
        final ModelException.Detail detail =
            new ModelException.Detail( Level.SEVERE, this.getMessage( "incompatibleImplementation", new Object[]
            {
                implementation, specification, implementedVersion, specifiedVersion
            } ) );

        detail.setElement( element );
        return detail;
    }

    private ModelException.Detail newIncompatibleDependencyDetail( final JAXBElement element,
                                                                   final String implementation,
                                                                   final String specification,
                                                                   final String requiredVersion,
                                                                   final String availableVersion )
    {
        final ModelException.Detail detail =
            new ModelException.Detail( Level.SEVERE, this.getMessage( "incompatibleDependency", new Object[]
            {
                implementation, specification, requiredVersion, availableVersion
            } ) );

        detail.setElement( element );
        return detail;
    }

    private ModelException.Detail newPropertyOverwriteConstraintDetail( final JAXBElement element,
                                                                        final String implementation,
                                                                        final String dependency,
                                                                        final String specification )
    {
        final ModelException.Detail detail =
            new ModelException.Detail( Level.SEVERE, this.getMessage( "propertyOverwriteConstraint", new Object[]
            {
                implementation, dependency, specification
            } ) );

        detail.setElement( element );
        return detail;
    }

    private ModelException.Detail newImplementationNameConstraintDetail( final JAXBElement element,
                                                                         final String specification,
                                                                         final String implementations )
    {
        final ModelException.Detail detail =
            new ModelException.Detail( Level.SEVERE, this.getMessage( "implementationNameConstraint", new Object[]
            {
                specification, implementations
            } ) );

        detail.setElement( element );
        return detail;
    }

    private ModelException.Detail newMandatoryDependencyConstraintDetail( final JAXBElement element,
                                                                          final String implementation,
                                                                          final String dependencyName )
    {
        final ModelException.Detail detail =
            new ModelException.Detail( Level.SEVERE, this.getMessage( "mandatoryDependencyConstraint", new Object[]
            {
                implementation, dependencyName
            } ) );

        detail.setElement( element );
        return detail;
    }

    private ModelException.Detail newMultiplicityConstraintDetail( final JAXBElement element,
                                                                   final Number implementations,
                                                                   final String specification,
                                                                   final Number expected,
                                                                   final Multiplicity multiplicity )
    {
        final ModelException.Detail detail =
            new ModelException.Detail( Level.SEVERE, this.getMessage( "multiplicityConstraint", new Object[]
            {
                implementations, specification, expected, multiplicity.value()
            } ) );

        detail.setElement( element );
        return detail;
    }

    // SECTION-END
}
