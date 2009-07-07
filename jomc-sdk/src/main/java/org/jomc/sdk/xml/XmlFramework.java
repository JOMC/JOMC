// SECTION-START[License Header]
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
// SECTION-END
package org.jomc.sdk.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.jomc.sdk.model.SchemaType;
import org.jomc.sdk.model.XmlFrameworkConfigurationType;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// SECTION-START[Implementation Comment]
/**
 * XML framework.
 * <p><b>Specifications</b><ul>
 * <li>{@code javax.xml.bind.JAXBContext}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
 * <li>{@code javax.xml.bind.Marshaller}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
 * <li>{@code javax.xml.bind.Unmarshaller}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
 * <li>{@code javax.xml.validation.Schema}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
 * <li>{@code org.xml.sax.EntityResolver}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
 * <li>{@code org.w3c.dom.ls.LSResourceResolver}<blockquote>
 * Object applies to Multiton scope.</blockquote></li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #getConfiguration configuration}"<blockquote>
 * Property of type {@code java.lang.Object}.</blockquote></li>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// SECTION-END
// SECTION-START[Annotations]
@javax.annotation.Generated
(
    value = "org.jomc.tools.JavaSources",
    comments = "See http://jomc.sourceforge.net/jomc-tools"
)
// SECTION-END
public class XmlFramework
{
    // SECTION-START[BindingFramework]

    public EntityResolver getEntityResolver()
    {
        return new EntityResolver()
        {

            public InputSource resolveEntity( final String publicId, final String systemId )
                throws SAXException, IOException
            {
                InputSource source = null;
                final SchemaType s = getXmlFrameworkConfiguration().getSchemas().getSchema( publicId );

                if ( s != null )
                {
                    source = new InputSource();
                    source.setPublicId( s.getPublicId() );
                    source.setSystemId( getClassLoader().getResource( s.getClasspathId() ).toExternalForm() );
                }

                return source;
            }

        };
    }

    public LSResourceResolver getLSResourceResolver()
    {
        return new LSResourceResolver()
        {

            public LSInput resolveResource( final String type, final String namespaceURI, final String publicId,
                                            final String systemId, final String baseURI )
            {
                LSInput input = null;
                final SchemaType s = getXmlFrameworkConfiguration().getSchemas().getSchema( publicId );

                if ( s != null )
                {
                    input = new LSInput()
                    {

                        public Reader getCharacterStream()
                        {
                            return null;
                        }

                        public void setCharacterStream( final Reader characterStream )
                        {
                            throw new UnsupportedOperationException();
                        }

                        public InputStream getByteStream()
                        {
                            return getClassLoader().getResourceAsStream( s.getClasspathId() );
                        }

                        public void setByteStream( final InputStream byteStream )
                        {
                            throw new UnsupportedOperationException();
                        }

                        public String getStringData()
                        {
                            throw new UnsupportedOperationException();
                        }

                        public void setStringData( final String stringData )
                        {
                            throw new UnsupportedOperationException();
                        }

                        public String getSystemId()
                        {
                            return getClassLoader().getResource( s.getClasspathId() ).toExternalForm();
                        }

                        public void setSystemId( final String systemId )
                        {
                            throw new UnsupportedOperationException();
                        }

                        public String getPublicId()
                        {
                            return s.getPublicId();
                        }

                        public void setPublicId( final String publicId )
                        {
                            throw new UnsupportedOperationException();
                        }

                        public String getBaseURI()
                        {
                            throw new UnsupportedOperationException();
                        }

                        public void setBaseURI( final String baseURI )
                        {
                            throw new UnsupportedOperationException();
                        }

                        public String getEncoding()
                        {
                            throw new UnsupportedOperationException();
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

                return input;
            }

        };
    }

    public JAXBContext getJAXBContext() throws JAXBException
    {
        JAXBContext jaxbContext = null;

        if ( this.getXmlFrameworkConfiguration().getSchemas() != null )
        {
            final StringBuffer ctx = new StringBuffer();
            for ( Iterator<SchemaType> s = this.getXmlFrameworkConfiguration().getSchemas().getSchema().iterator();
                  s.hasNext(); )
            {
                final SchemaType source = s.next();
                ctx.append( source.getContextId() );
                if ( s.hasNext() )
                {
                    ctx.append( ':' );
                }
            }

            jaxbContext = JAXBContext.newInstance( ctx.toString(), this.getClassLoader() );
        }

        return jaxbContext;
    }

    public Marshaller getMarshaller() throws JAXBException, SAXException
    {
        final Marshaller m = this.getJAXBContext().createMarshaller();
        m.setProperty( Marshaller.JAXB_ENCODING, this.getXmlFrameworkConfiguration().getEncoding() );
        m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, this.getXmlFrameworkConfiguration().isFormattedOutput() );

        if ( this.getXmlFrameworkConfiguration().getSchemas() != null )
        {
            final StringBuffer schemaLocation = new StringBuffer();
            for ( SchemaType s : this.getXmlFrameworkConfiguration().getSchemas().getSchema() )
            {
                schemaLocation.append( s.getPublicId() ).append( ' ' ).append( s.getSystemId() );
            }

            m.setProperty( Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation.toString() );
        }

        if ( this.getXmlFrameworkConfiguration().isValidating() )
        {
            m.setSchema( this.getSchema() );
        }

        return m;
    }

    public Unmarshaller getUnmarshaller() throws JAXBException, SAXException
    {
        final Unmarshaller u = this.getJAXBContext().createUnmarshaller();

        if ( this.getXmlFrameworkConfiguration().isValidating() )
        {
            u.setSchema( this.getSchema() );
        }

        return u;
    }

    public Schema getSchema() throws SAXException
    {
        Schema schema = null;

        final SchemaFactory f = SchemaFactory.newInstance( this.getXmlFrameworkConfiguration().getSchemaLanguage() );
        if ( this.getXmlFrameworkConfiguration().getSchemas() != null )
        {
            final List<Source> sources =
                new ArrayList( this.getXmlFrameworkConfiguration().getSchemas().getSchema().size() );

            for ( SchemaType s : this.getXmlFrameworkConfiguration().getSchemas().getSchema() )
            {
                sources.add( new StreamSource( this.getClassLoader().getResourceAsStream(
                    s.getClasspathId() ), s.getSystemId() ) );

            }

            schema = f.newSchema( sources.toArray( new Source[ sources.size() ] ) );
        }

        return schema;
    }

    private XmlFrameworkConfigurationType getXmlFrameworkConfiguration()
    {
        return (XmlFrameworkConfigurationType) this.getConfiguration();
    }

    private ClassLoader getClassLoader()
    {
        ClassLoader cl = this.getClass().getClassLoader();
        if ( cl == null )
        {
            cl = ClassLoader.getSystemClassLoader();
        }

        return cl;
    }

    // SECTION-END
    // SECTION-START[Constructors]

    /** Default implementation constructor. */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    public XmlFramework()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // SECTION-END
    // SECTION-START[Properties]

    /**
     * Gets the value of the {@code configuration} property.
     * @return The configuration of an XML framework implementation. Object from XML namespace http://jomc.eu/sdk/model.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated
    (
        value = "org.jomc.tools.JavaSources",
        comments = "See http://jomc.sourceforge.net/jomc-tools"
    )
    private java.lang.Object getConfiguration() throws org.jomc.ObjectManagementException
    {
        return (java.lang.Object) org.jomc.ObjectManagerFactory.getObjectManager().getProperty( this, "configuration" );
    }
    // SECTION-END
}
