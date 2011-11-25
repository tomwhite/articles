package org.tiling.xmlcatalog.parsers.dom;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This interface defines a contract for parsing an input source into a DOM tree.
 */
public interface DomParser {
	public Document parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException;
}
