package org.tiling.xmlcatalog.transform;

import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;

public class VanillaXsltTransformer implements XsltTransformer {
	public StreamResult transform(InputSource inputSource, InputSource transformSource) throws TransformerException {
		Source stylesheetSource = new StreamSource(transformSource.getCharacterStream(), transformSource.getSystemId());
		Source inputStreamSource = new StreamSource(inputSource.getCharacterStream(), inputSource.getSystemId());
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(stylesheetSource);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.transform(inputStreamSource, result);
		return result;
	}

}
