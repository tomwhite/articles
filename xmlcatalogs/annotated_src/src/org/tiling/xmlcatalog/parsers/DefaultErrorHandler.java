@snip_start@package org.tiling.xmlcatalog.parsers;

@snip_end@import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DefaultErrorHandler implements ErrorHandler {
	public void warning(SAXParseException exception) throws SAXException {
		System.err.println(exception.getMessage());
	}
	public void error(SAXParseException exception) throws SAXException {
		throw exception;
	}
	public void fatalError(SAXParseException exception) throws SAXException {
		throw exception;
	}
}