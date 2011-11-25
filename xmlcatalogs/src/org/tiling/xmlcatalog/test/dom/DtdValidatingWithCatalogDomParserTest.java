package org.tiling.xmlcatalog.test.dom;

import junit.framework.TestCase;

import org.tiling.xmlcatalog.parsers.dom.DomParser;
import org.tiling.xmlcatalog.parsers.dom.DtdValidatingWithCatalogDomParser;
import org.tiling.xmlcatalog.test.InputSourceUtil;
import org.w3c.dom.Document;

public class DtdValidatingWithCatalogDomParserTest
	extends TestCase {

	public void testSystem() throws Exception {
		DomParser parser = new DtdValidatingWithCatalogDomParser();
		Document doc = parser.parse(InputSourceUtil.getInputSource("rss091.xml"));
		assertEquals("rss", doc.getDocumentElement().getNodeName());
	}

	public void testPublic() throws Exception {
		// This also shows entities (referenced from DTD).
		DomParser parser = new DtdValidatingWithCatalogDomParser();
		Document doc = parser.parse(InputSourceUtil.getInputSource("recipe.html"));
		assertEquals("html", doc.getDocumentElement().getNodeName());
	}
}
