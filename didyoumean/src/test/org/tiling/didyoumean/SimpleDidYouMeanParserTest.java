package org.tiling.didyoumean;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;

public class SimpleDidYouMeanParserTest extends DidYouMeanParserBaseTest {

	protected DidYouMeanParser createDidYouMeanParser() {
		return new SimpleDidYouMeanParser(FIELD, suggestionsIndex);
	}
	
	public void testMultipleTermsWithNoMisspellings() throws ParseException {
		String queryString = "plaice haddock";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Whole query string is a term", FIELD + ":plaice haddock", parsedQuery.toString());
	}	
}
