package org.tiling.xmlcatalog.test.sax;

import junit.framework.TestCase;

import org.tiling.xmlcatalog.parsers.sax.SaxParser;
import org.tiling.xmlcatalog.parsers.sax.SchemaValidatingSaxParser;
import org.tiling.xmlcatalog.test.InputSourceUtil;
import org.xml.sax.SAXNotRecognizedException;

public class SchemaValidatingSaxParserTest extends TestCase {
	public void test() throws Exception {
		try {
			SaxParser parser = new NetworkCheckSaxParser(new SchemaValidatingSaxParser());
			parser.parse(InputSourceUtil.getInputSource("recipe.xml"));
		} catch (IllegalArgumentException e) {
			fail("JAXP 1.2 is required to run this test.");
		} catch (SAXNotRecognizedException e) {
			fail("JAXP 1.2 is required to run this test.");
		}
	}
}
