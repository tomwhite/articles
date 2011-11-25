package org.tiling.xmlcatalog.parsers.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This interface defines a contract for parsing an input source using a SAX event-based parser.
 */
public interface SaxParser {
	public void parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException;
}
