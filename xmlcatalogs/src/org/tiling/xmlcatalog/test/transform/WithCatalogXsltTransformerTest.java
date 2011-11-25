package org.tiling.xmlcatalog.test.transform;

import javax.xml.transform.stream.StreamResult;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.tiling.xmlcatalog.parsers.dom.DomParser;
import org.tiling.xmlcatalog.parsers.dom.DtdValidatingWithCatalogDomParser;
import org.tiling.xmlcatalog.test.InputSourceUtil;
import org.tiling.xmlcatalog.transform.WithCatalogXsltTransformer;
import org.tiling.xmlcatalog.transform.XsltTransformer;
import org.xml.sax.InputSource;

public class WithCatalogXsltTransformerTest extends XMLTestCase {
	
	public void testInclude() throws Exception {
		XsltTransformer transformer = new WithCatalogXsltTransformer();
		StreamResult result = transformer.transform(
			InputSourceUtil.getInputSource("recipe.xml"),
			InputSourceUtil.getInputSource("recipe_with_include.xslt")
		);
		checkAgainstControlXhtml(result.getWriter().toString());
	}

	public void testImport() throws Exception {
		XsltTransformer transformer = new WithCatalogXsltTransformer();
		StreamResult result = transformer.transform(
			InputSourceUtil.getInputSource("recipe.xml"),
			InputSourceUtil.getInputSource("recipe_with_import.xslt")
		);
		checkAgainstControlXhtml(result.getWriter().toString());
	}

	private void checkAgainstControlXhtml(String result)
		throws Exception {
		
		XMLUnit.setIgnoreWhitespace(true); 
		InputSource controlDoc = InputSourceUtil.getInputSource("recipe.html");
		InputSource testDoc = InputSourceUtil.asInputSource(result);
		DomParser domParser = new DtdValidatingWithCatalogDomParser();
		Diff diff = new Diff(
			domParser.parse(controlDoc),
			domParser.parse(testDoc)
		);
		assertTrue("Identical: " + diff, diff.identical());
	}


}
