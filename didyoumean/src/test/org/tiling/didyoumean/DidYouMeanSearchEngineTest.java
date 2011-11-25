package org.tiling.didyoumean;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

public class DidYouMeanSearchEngineTest extends SearchEngineBaseTest {
	
	static class MockDidYouMeanParser implements DidYouMeanParser {

		public Query parse(String queryString) throws ParseException {
			return new TermQuery(new Term(DEFAULT_FIELD, queryString));
		}

		public Query suggest(String queryString) throws ParseException {
			return queryString.equals("chops") ? new TermQuery(new Term(DEFAULT_FIELD, "chips")) : null;
		}
		
	}

	public void testUnrestrictiveMaxHits() throws IOException, ParseException {
		SearchEngine engine = new DidYouMeanSearchEngine(DEFAULT_FIELD, NAME_FIELD, originalIndex, 3,
				3, 1.0f, new MockDidYouMeanParser());
		SearchResult result = engine.search("chips");
		assertEquals("Total hit count", 2, result.getTotalHitCount());
		assertEquals("Top hit count", 2, result.getTopHitCount());
		assertEquals("Top hits", Arrays.asList(new String[] { "doc1", "doc2" }), result.getTopHits());
		assertEquals("Original query", "chips", result.getOriginalQuery());
		assertNull("Suggested query", result.getSuggestedQuery());
		assertTrue("Duration", result.getSearchDuration() >= 0);
	}
	
	public void testRestrictiveMaxHits() throws IOException, ParseException {
		SearchEngine engine = new DidYouMeanSearchEngine(DEFAULT_FIELD, NAME_FIELD, originalIndex, 1,
				3, 1.0f, new MockDidYouMeanParser());
		SearchResult result = engine.search("chips");
		assertEquals("Total hit count", 2, result.getTotalHitCount());
		assertEquals("Top hit count", 1, result.getTopHitCount());
		assertEquals("Top hits", Arrays.asList(new String[] { "doc1" }), result.getTopHits());
		assertEquals("Original query", "chips", result.getOriginalQuery());
		assertNull("Suggested query", result.getSuggestedQuery());
		assertTrue("Duration", result.getSearchDuration() >= 0);
	}
	
	
	public void testNoHits() throws IOException, ParseException {
		SearchEngine engine = new DidYouMeanSearchEngine(DEFAULT_FIELD, NAME_FIELD, originalIndex, 3,
				3, 1.0f, new MockDidYouMeanParser());
		SearchResult result = engine.search("chops");
		assertEquals("Total hit count", 0, result.getTotalHitCount());
		assertEquals("Top hit count", 0, result.getTopHitCount());
		assertEquals("Top hits", Collections.EMPTY_LIST, result.getTopHits());
		assertEquals("Original query", "chops", result.getOriginalQuery());
		assertEquals("Suggested query", "chips", result.getSuggestedQuery());
		assertTrue("Duration", result.getSearchDuration() >= 0);
	}	
	
}
