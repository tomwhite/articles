package org.tiling.xmlcatalog.parsers.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.tiling.xmlcatalog.parsers.DefaultErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * This class is a wrapper around a JAXP SAX parser that validates against a 
 * W3C XML Schema (if specified in the XML document) and doesn't use an XML catalog.
 */
public class SchemaValidatingSaxParser implements SaxParser {
	
	public void parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		SAXParser parser = factory.newSAXParser();
		parser.setProperty(
			"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
			"http://www.w3.org/2001/XMLSchema"
		);
		XMLReader reader = parser.getXMLReader();
		reader.setErrorHandler(new DefaultErrorHandler());		
		reader.parse(inputSource);
	}
}
