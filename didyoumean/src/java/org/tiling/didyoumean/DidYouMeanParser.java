package org.tiling.didyoumean;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;

public interface DidYouMeanParser {
	public Query parse(String queryString) throws ParseException;
	public Query suggest(String queryString) throws ParseException;
}
