package org.tiling.xmlcatalog.test.transform;

import java.net.SocketPermission;
import java.security.AccessControlException;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

import junit.framework.Assert;

import org.tiling.xmlcatalog.transform.XsltTransformer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 * This class decorates (wraps) an {@link XsltTransformer} and asserts that the transformer
 * attempts to access a network resource. If no attempt is made to use the
 * network then a JUnit assertion failure occurs.
 * <p>
 * Note that I need to run with {@link org.tiling.xmlcatalog.test.NoNetworkSecurityManager}
 * installed.
 */
public class NetworkCheckXsltTransformer implements XsltTransformer {
	
	private XsltTransformer transformer;
	
	public NetworkCheckXsltTransformer(XsltTransformer transformer) {
		this.transformer = transformer;
	}

	public StreamResult transform(InputSource inputSource, InputSource transformSource)	throws TransformerException {
		try {
			transformer.transform(inputSource, transformSource);
			Assert.fail("Should fail since the code does not have permission to connect to remote machines.");
		} catch (TransformerConfigurationException e) {
			Throwable cause1 = e.getException();
			Assert.assertTrue("TransformerConfigurationException",
				cause1 instanceof TransformerConfigurationException);
			
			Throwable cause2 = ((TransformerConfigurationException) cause1).getException();
			Assert.assertTrue("TransformerException",
				cause2 instanceof TransformerException);
			
			Throwable cause3 = ((TransformerException) cause2).getException();
			if (cause3 instanceof SAXParseException) { // for Crimson
				SAXParseException saxException = (SAXParseException) cause3;
				Assert.assertTrue("AccessControlException",
					saxException.getException() instanceof AccessControlException);
				Assert.assertTrue("SocketPermission", 
					((AccessControlException) saxException.getException()).getPermission() instanceof SocketPermission);
			} else {
				Assert.assertTrue("AccessControlException",
					cause3 instanceof AccessControlException);
			
				Assert.assertTrue("SocketPermission", 
					((AccessControlException) cause3).getPermission() instanceof SocketPermission);
			}
		}
		return null;		
	}

}
