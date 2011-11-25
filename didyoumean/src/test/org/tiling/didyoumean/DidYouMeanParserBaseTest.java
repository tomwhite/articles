package org.tiling.didyoumean;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import junit.framework.TestCase;

public abstract class DidYouMeanParserBaseTest extends TestCase {

	protected static final String FIELD = "field";
	
	protected Directory originalIndex;
	protected Directory suggestionsIndex;
	protected DidYouMeanParser parser;
	
	public void setUp() throws IOException {
		originalIndex = new RAMDirectory();
		IndexWriter writer = new IndexWriter(originalIndex, new StandardAnalyzer(),	true);
		writer.addDocument(createDocument("bream")); // doesn't get put in spell index! (First alphabetically!)
		writer.addDocument(createDocument("haddock"));
		writer.addDocument(createDocument("mackerel"));
		writer.addDocument(createDocument("plaice"));
		writer.addDocument(createDocument("sole"));
		writer.optimize();
		writer.close();
		
		suggestionsIndex = new RAMDirectory();
		new DidYouMeanIndexer().createSpellIndex(FIELD, originalIndex, suggestionsIndex);
		
		parser = createDidYouMeanParser();
		
	}
	
	private Document createDocument(String text) {
		Document document = new Document();
		document.add(Field.Text(FIELD, text));
		return document;
	}
	
	protected abstract DidYouMeanParser createDidYouMeanParser();

	public void testSingleTermWithNoMisspelling() throws ParseException {
		String queryString = "plaice";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Whole query string is a term", FIELD + ":plaice", parsedQuery.toString());
		
		Query suggestedQuery = parser.suggest(queryString);
		assertNull("Exact match should prompt no suggestions.", suggestedQuery);
	}

	public void testSingleTermWithMisspellingWithMissingLetter() throws ParseException {
		String queryString = "place";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Whole query string is a term", FIELD + ":place", parsedQuery.toString());

		Query suggestedQuery = parser.suggest(queryString);
		assertEquals("Should suggest 'plaice'", FIELD + ":plaice", suggestedQuery.toString());
	}	

	public void testSingleTermWithMisspellingWithSubstitutedLetter() throws ParseException {
		String queryString = "mackerul";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Whole query string is a term", FIELD + ":mackerul", parsedQuery.toString());

		Query suggestedQuery = parser.suggest(queryString);
		assertEquals("Should suggest 'mackerel'", FIELD + ":mackerel", suggestedQuery.toString());
	}	

	public void testSingleTermWithMisspellingWithExtraLetter() throws ParseException {
		String queryString = "hadddock";
		
		Query parsedQuery = parser.parse(queryString);
		assertEquals("Whole query string is a term", FIELD + ":hadddock", parsedQuery.toString());

		Query suggestedQuery = parser.suggest(queryString);
		assertEquals("Should suggest 'haddock'", FIELD + ":haddock", suggestedQuery.toString());
	}	

}
