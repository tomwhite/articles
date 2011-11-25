package org.tiling.didyoumean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;

public class DidYouMeanSearchEngine implements SearchEngine {
	
	private String defaultField;
	private String nameField;
	private Directory originalIndexDirectory;
	private int maxHits;
	private int minimumHits;
	private float minimumScore;
	private DidYouMeanParser didYouMeanParser;
	
	public DidYouMeanSearchEngine(String defaultField, String nameField,
			Directory originalIndexDirectory,
			int maxHits, int minimumHits, float minimumScore,
			DidYouMeanParser didYouMeanParser) {
		
		this.defaultField = defaultField;
		this.nameField = nameField;
		this.originalIndexDirectory = originalIndexDirectory;
		this.maxHits = maxHits;
		this.minimumHits = minimumHits;
		this.minimumScore = minimumScore;
		this.didYouMeanParser = didYouMeanParser;
	}

	public SearchResult search(String queryString) throws IOException, ParseException {
	    long startTime = System.currentTimeMillis();
	    IndexSearcher is = null;
		try {
			is = new IndexSearcher(originalIndexDirectory);
			Query query = didYouMeanParser.parse(queryString);
		    Hits hits = is.search(query);
			
		    String suggestedQueryString = null;
			if (hits.length() < minimumHits || hits.score(0) < minimumScore) {
				Query didYouMean = didYouMeanParser.suggest(queryString);
				if (didYouMean != null) {
					suggestedQueryString = didYouMean.toString(defaultField);
				}
			}
			
		    long endTime = System.currentTimeMillis();
			return new SearchResult(extractHits(hits), hits.length(),
					endTime - startTime, queryString, suggestedQueryString);
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
