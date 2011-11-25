@snip_start@package org.tiling.xmlcatalog.parsers.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.resolver.tools.CatalogResolver;
import org.tiling.xmlcatalog.parsers.DefaultErrorHandler;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This class is a wrapper around a JAXP DOM parser that validates against a 
 * W3C XML Schema (if specified in the XML document) and uses an XML catalog.
 */
public class SchemaValidatingWithCatalogDomParser implements DomParser {
	
	public Document parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {@snip_end@
@snip_start@		@snip_end@DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
@snip_start@		@snip_end@factory.setNamespaceAware(true);
@snip_start@		@snip_end@factory.setValidating(true);
@snip_start@		@snip_end@@open_bold@factory.setAttribute(
@snip_start@		@snip_end@	"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
@snip_start@		@snip_end@	"http://www.w3.org/2001/XMLSchema"
@snip_start@		@snip_end@);@close_bold@
@snip_start@		@snip_end@DocumentBuilder builder = factory.newDocumentBuilder();
@snip_start@		@snip_end@builder.setEntityResolver(new CatalogResolver());
@snip_start@		@snip_end@builder.setErrorHandler(new DefaultErrorHandler());
@snip_start@		@snip_end@Document document = builder.parse(inputSource);@snip_start@
		return document;
	}
}@snip_end@