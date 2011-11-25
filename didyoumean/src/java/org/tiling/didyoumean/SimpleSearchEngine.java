package org.tiling.didyoumean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;

public class SimpleSearchEngine implements SearchEngine {
	
	private String defaultField;
	private String nameField;
	private Directory originalIndexDirectory;
	private int maxHits;
	
	public SimpleSearchEngine(String defaultField, String nameField, 
			Directory originalIndexDirectory, int maxHits) {
		this.defaultField = defaultField;
		this.nameField = nameField;
		this.originalIndexDirectory = originalIndexDirectory;
		this.maxHits = maxHits; 
	}

	public SearchResult search(String queryString) throws IOException, ParseException {
	    long startTime = System.currentTimeMillis();
	    IndexSearcher is = null;
		try {
			is = new IndexSearcher(originalIndexDirectory);
			QueryParser queryParser = new QueryParser(defaultField, new StandardAnalyzer());
			queryParser.setOperator(QueryParser.DEFAULT_OPERATOR_AND);		
			Query query = queryParser.parse(queryString);			
		    Hits hits = is.search(query);
		    long endTime = System.currentTimeMillis();
			return new SearchResult(extractHits(hits), hits.length(), endTime - startTime, queryString);
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
	
	private List extractHits(Hits hits) throws IOException {
	    List hitList = new ArrayList();
	    for (int i = 0, count = 0; i < hits.length() && count++ < maxHits; i++) {
            hitList.add(hits.doc(i).getField(nameField).stringValue());
        }
	    return hitList;
	}
}