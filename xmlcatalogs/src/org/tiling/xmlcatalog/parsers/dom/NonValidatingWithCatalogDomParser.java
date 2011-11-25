package org.tiling.xmlcatalog.parsers.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.resolver.tools.CatalogResolver;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This class is a wrapper around a JAXP DOM parser that doesn't validate the XML document
 * and uses an XML catalog.
 */
public class NonValidatingWithCatalogDomParser implements DomParser {
	
	public Document parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setEntityResolver(new CatalogResolver());
		Document document = builder.parse(inputSource);
		return document;
	}
}
