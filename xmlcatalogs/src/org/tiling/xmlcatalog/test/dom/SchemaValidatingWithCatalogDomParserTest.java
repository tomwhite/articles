package org.tiling.xmlcatalog.test.dom;

import junit.framework.TestCase;

import org.tiling.xmlcatalog.parsers.dom.DomParser;
import org.tiling.xmlcatalog.parsers.dom.SchemaValidatingWithCatalogDomParser;
import org.tiling.xmlcatalog.test.InputSourceUtil;
import org.w3c.dom.Document;

public class SchemaValidatingWithCatalogDomParserTest extends TestCase {
	public void test() throws Exception {
		try {
			DomParser parser = new SchemaValidatingWithCatalogDomParser();
			Document doc = parser.parse(InputSourceUtil.getInputSource("recipe.xml"));
			assertEquals("recipe", doc.getDocumentElement().getNodeName());
		} catch (IllegalArgumentException e) {
			fail("JAXP 1.2 is required to run this test.");
		}		
	}
}
