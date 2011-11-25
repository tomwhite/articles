package org.tiling.didyoumean;

import java.io.IOException;
import java.util.Arrays;

import org.apache.lucene.queryParser.ParseException;

public class SimpleSearchEngineTest extends SearchEngineBaseTest {

	public void testUnrestrictiveMaxHits() throws IOException, ParseException {
		SearchEngine engine = new SimpleSearchEngine(DEFAULT_FIELD, NAME_FIELD, originalIndex, 3);
		SearchResult result = engine.search("chips");
		assertEquals("Total hit count", 2, result.getTotalHitCount());
		assertEquals("Top hit count", 2, result.getTopHitCount());
		assertEquals("Top hits", Arrays.asList(new String[] { "doc1", "doc2" }), result.getTopHits());
		assertEquals("Original query", "chips", result.getOriginalQuery());
		assertNull("Suggested query", result.getSuggestedQuery());
		assertTrue("Duration", result.getSearchDuration() >= 0);
	}
	
	public void testRestrictiveMaxHits() throws IOException, ParseException {
		SearchEngine engine = new SimpleSearchEngine(DEFAULT_FIELD, NAME_FIELD, originalIndex, 1);
		SearchResult result = engine.search("chips");
		assertEquals("Total hit count", 2, result.getTotalHitCount());
		assertEquals("Top hit count", 1, result.getTopHitCount());
		assertEquals("Top hits", Arrays.asList(new String[] { "doc1" }), result.getTopHits());
		assertEquals("Original query", "chips", result.getOriginalQuery());
		assertNull("Suggested query", result.getSuggestedQuery());
		assertTrue("Duration", result.getSearchDuration() >= 0);
	}

}
