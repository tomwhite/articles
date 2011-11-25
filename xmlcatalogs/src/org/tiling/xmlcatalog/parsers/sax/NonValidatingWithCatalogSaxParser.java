package org.tiling.xmlcatalog.parsers.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.xml.resolver.tools.CatalogResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * This class is a wrapper around a JAXP SAX parser that doesn't validate the XML document
 * and uses an XML catalog.
 */
public class NonValidatingWithCatalogSaxParser implements SaxParser {
	
	public void parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		reader.setEntityResolver(new CatalogResolver());		
		reader.parse(inputSource);
	}
}
