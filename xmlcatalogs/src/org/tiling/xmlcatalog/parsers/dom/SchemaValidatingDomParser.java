package org.tiling.xmlcatalog.parsers.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.tiling.xmlcatalog.parsers.DefaultErrorHandler;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This class is a wrapper around a JAXP DOM parser that validates against a 
 * W3C XML Schema (if specified in the XML document) and doesn't use an XML catalog.
 */
public class SchemaValidatingDomParser implements DomParser {
	
	public Document parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		factory.setAttribute(
			"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
			"http://www.w3.org/2001/XMLSchema"
		);
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(new DefaultErrorHandler());
		Document document = builder.parse(inputSource);
		return document;
	}
}
