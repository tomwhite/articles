package org.tiling.xmlcatalog.test.sax;

import junit.framework.TestCase;

import org.tiling.xmlcatalog.parsers.sax.DtdValidatingSaxParser;
import org.tiling.xmlcatalog.parsers.sax.SaxParser;
import org.tiling.xmlcatalog.test.InputSourceUtil;

public class DtdValidatingSaxParserTest
	extends TestCase {

	public void testSystem() throws Exception {
		SaxParser parser = new NetworkCheckSaxParser(new DtdValidatingSaxParser());
		parser.parse(InputSourceUtil.getInputSource("rss091.xml"));
	}

	public void testPublic() throws Exception {
		// This also shows entities (referenced from DTD).
		SaxParser parser = new NetworkCheckSaxParser(new DtdValidatingSaxParser());
		parser.parse(InputSourceUtil.getInputSource("recipe.html"));
	}
}
