package org.tiling.xmlcatalog.test.sax;

import java.io.IOException;
import java.net.SocketPermission;
import java.security.AccessControlException;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.tiling.xmlcatalog.parsers.sax.SaxParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This class decorates (wraps) a {@link SaxParser} and asserts that the parser
 * attempts to access a network resource. If no attempt is made to use the
 * network then a JUnit assertion failure occurs.
 * <p>
 * Note that I need to run with {@link org.tiling.xmlcatalog.test.NoNetworkSecurityManager}
 * installed.
 */
public class NetworkCheckSaxParser implements SaxParser {

	private SaxParser parser;
	
	public NetworkCheckSaxParser(SaxParser parser) {
		this.parser = parser;
	}

	public void parse(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException {
		try {
			parser.parse(inputSource);
			Assert.fail("Should fail since the code does not have permission to connect to remote machines.");
		} catch (AccessControlException e) {
			Assert.assertTrue(e.getPermission() instanceof SocketPermission);
		} catch (SAXParseException e) { // for Crimson
			Assert.assertTrue(e.getException() instanceof AccessControlException);
			Assert.assertTrue(((AccessControlException) e.getException()).getPermission() instanceof SocketPermission);
		}		
	}
}
