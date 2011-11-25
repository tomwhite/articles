package org.tiling.xmlcatalog.transform;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;

public interface XsltTransformer {
	/**
	 * This interface defines a contract for transforming an input source to an output result using a XSLT transform.
	 */
	public StreamResult transform(InputSource inputSource, InputSource transformSource) throws TransformerException;
}
