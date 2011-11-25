package org.tiling.didyoumean;

import java.io.IOException;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;

public class SimpleDidYouMeanParser implements DidYouMeanParser {
	
	private String defaultField;
	private Directory spellIndexDirectory;
	
	public SimpleDidYouMeanParser(String defaultField, Directory spellIndexDirectory) {
		this.defaultField = defaultField;
		this.spellIndexDirectory = spellIndexDirectory;
	}

	public Query parse(String queryString) {
		return new TermQuery(new Term(defaultField, queryString));
	}

	public Query suggest(String queryString) throws ParseException {
		try {
			SpellChecker spellChecker = new SpellChecker(spellIndexDirectory);		
			if (spellChecker.exist(queryString)) {
				return null;
			}
			String[] similarWords = spellChecker.suggestSimilar(queryString, 1);
			if (similarWords.length == 0) {
				return null;
			}
			return new TermQuery(new Term(defaultField, similarWords[0]));
		} catch (IOException e) {
			throw new ParseException(e.getMessage());
		}
	}

}
