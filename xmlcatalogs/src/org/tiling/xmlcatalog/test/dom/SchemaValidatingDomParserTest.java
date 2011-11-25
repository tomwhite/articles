package org.tiling.xmlcatalog.test.dom;

import junit.framework.TestCase;

import org.tiling.xmlcatalog.parsers.dom.DomParser;
import org.tiling.xmlcatalog.parsers.dom.SchemaValidatingDomParser;
import org.tiling.xmlcatalog.test.InputSourceUtil;

public class SchemaValidatingDomParserTest extends TestCase {
	public void test() throws Exception {
		try {
			DomParser parser = new NetworkCheckDomParser(new SchemaValidatingDomParser());
			parser.parse(InputSourceUtil.getInputSource("recipe.xml"));
		} catch (IllegalArgumentException e) {
			fail("JAXP 1.2 is required to run this test.");
		}
	}
}
