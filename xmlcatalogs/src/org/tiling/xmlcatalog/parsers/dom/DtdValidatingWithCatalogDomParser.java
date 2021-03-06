package org.tiling.xmlcatalog.parsers.dom;

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
 * DTD (if specified in the XML document) and uses an XML catalog.
 */
public class DtdValidatingWithCatalogDomParser implements DomParser {
	
	public Document parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setEntityResolver(new CatalogResolver());
		builder.setErrorHandler(new DefaultErrorHandler());			
		Document document = builder.parse(inputSource);
		return document;
	}
}
