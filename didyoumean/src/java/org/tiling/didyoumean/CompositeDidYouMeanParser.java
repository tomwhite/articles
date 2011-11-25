package org.tiling.didyoumean;

import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;

public class CompositeDidYouMeanParser implements DidYouMeanParser {
	
	private class QuerySuggester extends QueryParser {
		private boolean suggestedQuery = false;
		public QuerySuggester(String field, Analyzer analyzer) {
			super(field, analyzer);
		}
		protected Query getFieldQuery(String field, String queryText) throws ParseException {
			// Copied from org.apache.lucene.queryParser.QueryParser
			// replacing construction of TermQuery with call to getTermQuery()
			// which finds close matches.
		    TokenStream source = getAnalyzer().tokenStream(field, new StringReader(queryText));
			Vector v = new Vector();
			Token t;

			while (true) {
				try {
					t = source.next();
				} catch (IOException e) {
					t = null;
				}
				if (t == null)
					break;
				v.addElement(t.termText());
			}
			try {
				source.close();
			} catch (IOException e) {
				// ignore
			}

			if (v.size() == 0)
				return null;
			else if (v.size() == 1)
				return new TermQuery(getTerm(field, (String) v.elementAt(0)));
			else {
				PhraseQuery q = new PhraseQuery();
				q.setSlop(getPhraseSlop());
				for (int i = 0; i < v.size(); i++) {
					q.add(getTerm(field, (String) v.elementAt(i)));
				}
				return q;
			}
		}
		private Term getTerm(String field, String queryText) throws ParseException {
			SpellChecker spellChecker = new SpellChecker(spellIndexDirectory);
			try {
				if (spellChecker.exist(queryText)) {
					return new Term(field, queryText);
				}
				String[] similarWords = spellChecker.suggestSimilar(queryText, 1);
				if (similarWords.length == 0) {
					return new Term(field, queryText);
				}			
				suggestedQuery = true;
				return new Term(field, similarWords[0]);
			} catch (IOException e) {
				throw new ParseException(e.getMessage());
			}
		}		
		public boolean hasSuggestedQuery() {
			return suggestedQuery;
		}
		
	}
	
	private String defaultField;
	private Directory spellIndexDirectory;
	
	public CompositeDidYouMeanParser(String defaultField, Directory spellIndexDirectory) {
		this.defaultField = defaultField;
		this.spellIndexDirectory = spellIndexDirectory;
	}

	public Query parse(String queryString) throws ParseException {
		QueryParser queryParser = new QueryParser(defaultField, new StandardAnalyzer());
		queryParser.setOperator(QueryParser.DEFAULT_OPERATOR_AND);		
		return queryParser.parse(queryString);
	}

	public Query suggest(String queryString) throws ParseException {
		QuerySuggester querySuggester = new QuerySuggester(defaultField, new StandardAnalyzer());
		querySuggester.setOperator(QueryParser.DEFAULT_OPERATOR_AND);
		Query query = querySuggester.parse(queryString);
		return querySuggester.hasSuggestedQuery() ? query : null;
	}

}
