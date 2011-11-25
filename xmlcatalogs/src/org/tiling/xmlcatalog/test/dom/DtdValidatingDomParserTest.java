package org.tiling.xmlcatalog.test.dom;

import junit.framework.TestCase;

import org.tiling.xmlcatalog.parsers.dom.DomParser;
import org.tiling.xmlcatalog.parsers.dom.DtdValidatingDomParser;
import org.tiling.xmlcatalog.test.InputSourceUtil;

public class DtdValidatingDomParserTest
	extends TestCase {

	public void testSystem() throws Exception {
		DomParser parser = new NetworkCheckDomParser(new DtdValidatingDomParser());
		parser.parse(InputSourceUtil.getInputSource("rss091.xml"));
	}

	public void testPublic() throws Exception {
		DomParser parser = new NetworkCheckDomParser(new DtdValidatingDomParser());
		parser.parse(InputSourceUtil.getInputSource("recipe.html"));
	}
}
