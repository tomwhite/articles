@snip_start@package org.tiling.xmlcatalog.transform;

import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.xml.resolver.tools.CatalogResolver;
import org.xml.sax.InputSource;

public class WithCatalogXsltTransformer implements XsltTransformer {
	public StreamResult transform(InputSource inputSource, InputSource transformSource) throws TransformerException {
		Source stylesheetSource = new StreamSource(transformSource.getCharacterStream(), transformSource.getSystemId());
		Source inputStreamSource = new StreamSource(inputSource.getCharacterStream(), inputSource.getSystemId());@snip_end@
@snip_start@		@snip_end@TransformerFactory factory = TransformerFactory.newInstance();
@snip_start@		@snip_end@factory.setURIResolver(new CatalogResolver());
@snip_start@		@snip_end@Transformer transformer = factory.newTransformer(stylesheetSource);
@snip_start@		@snip_end@StringWriter writer = new StringWriter();
@snip_start@		@snip_end@StreamResult result = new StreamResult(writer);
@snip_start@		@snip_end@transformer.transform(inputStreamSource, result);@snip_start@
		return result;
	}

}@snip_end@