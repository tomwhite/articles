package org.tiling.didyoumean;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;

public class CompositeDidYouMeanParserTest extends DidYouMeanParserBaseTest {

	protected DidYouMeanParser createDidYouMeanParser() {
		return new CompositeDidYouMeanParser(FIELD, suggestionsIndex);
	}	

	public void testMultipleTermsWithNoMisspellings() throws ParseException {
		String queryString = "plaice haddock";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Query string terms are parsed correctly", "+" + FIELD + ":plaice " + "+" + FIELD + ":haddock",
				parsedQuery.toString());
		
		Query suggestedQuery = parser.suggest(queryString);
		assertNull("Exact match should prompt no suggestions.", suggestedQuery);
	}

	public void testMultipleTermsWithOneMisspelling() throws ParseException {
		String queryString = "place haddock";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Query string terms are parsed correctly", "+" + FIELD + ":place " + "+" + FIELD + ":haddock",
				parsedQuery.toString());
		
		Query suggestedQuery = parser.suggest(queryString);
		assertEquals("Should suggest 'plaice'", "+" + FIELD + ":plaice " + "+" + FIELD + ":haddock",
				suggestedQuery.toString());		
	}

	public void testMultipleTermsWithMultipleMisspellings() throws ParseException {
		String queryString = "place hadddock";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Query string terms are parsed correctly", "+" + FIELD + ":place " + "+" + FIELD + ":hadddock",
				parsedQuery.toString());
		
		Query suggestedQuery = parser.suggest(queryString);
		assertEquals("Should suggest 'plaice' and 'haddock'", "+" + FIELD + ":plaice " + "+" + FIELD + ":haddock",
				suggestedQuery.toString());		
	}

	public void testPhraseWithNoMisspellings() throws ParseException {
		String queryString = "\"plaice haddock\"";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Query string terms are parsed correctly", FIELD + ":\"plaice haddock\"", parsedQuery.toString());
		
		Query suggestedQuery = parser.suggest(queryString);
		assertNull("Exact match should prompt no suggestions.", suggestedQuery);
	}

	public void testPhraseWithOneMisspelling() throws ParseException {
		String queryString = "\"place haddock\"";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Query string terms are parsed correctly", FIELD + ":\"place haddock\"", parsedQuery.toString());
		
		Query suggestedQuery = parser.suggest(queryString);
		assertEquals("Should suggest 'plaice haddock'", FIELD + ":\"plaice haddock\"", suggestedQuery.toString());		

	}
	
	public void testPhraseWithMultipleMisspellings() throws ParseException {
		String queryString = "\"place hadddock\"";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Query string terms are parsed correctly", FIELD + ":\"place hadddock\"", parsedQuery.toString());
		
		Query suggestedQuery = parser.suggest(queryString);
		assertEquals("Should suggest 'plaice haddock'", FIELD + ":\"plaice haddock\"", suggestedQuery.toString());		
	}

	

}
