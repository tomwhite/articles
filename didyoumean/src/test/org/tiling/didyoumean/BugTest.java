package org.tiling.didyoumean;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class BugTest extends TestCase {
	protected static final String FIELD = "field";	
	protected Directory originalIndex;
	protected Directory suggestionsIndex;
	protected DidYouMeanParser parser;
	
	public void setUp() throws IOException {
		originalIndex = FSDirectory.getDirectory("/opt/lucene/didyoumean/indexes/original", false);
		suggestionsIndex = FSDirectory.getDirectory("/opt/lucene/didyoumean/indexes/spell", false);
		parser = new CompositeDidYouMeanParser(FIELD, suggestionsIndex);
		
	}

	public void testLettuceLeaf() throws ParseException {
		String queryString = "lettice leef";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Query string terms are parsed correctly", "+" + FIELD + ":lettice " + "+" + FIELD + ":leef",
				parsedQuery.toString());
		
		Query suggestedQuery = parser.suggest(queryString);
		assertEquals("Should suggest 'lettuce leaf'", "+" + FIELD + ":lettuce " + "+" + FIELD + ":leaf",
				suggestedQuery.toString());
	}
	
	public void testLeafLettuce() throws ParseException {
		String queryString = "leef lettice";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Query string terms are parsed correctly", "+" + FIELD + ":leef " + "+" + FIELD + ":lettice",
				parsedQuery.toString());
		
		Query suggestedQuery = parser.suggest(queryString);
		assertEquals("Should suggest 'leaf lettuce'", "+" + FIELD + ":leaf " + "+" + FIELD + ":lettuce",
				suggestedQuery.toString());
	}
	
}
