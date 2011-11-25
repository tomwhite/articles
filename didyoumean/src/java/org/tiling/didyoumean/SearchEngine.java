package org.tiling.didyoumean;

import java.io.IOException;

import org.apache.lucene.queryParser.ParseException;

public interface SearchEngine {
	/**
	 * Search for documents that match the user query using an underlying Lucene engine.
	 * @param queryString The user query.
	 * @return A collection of document hits.
	 * @throws IOException if there is a communications problem while searching the index.
	 * @throws ParseException if the query string cannot be interpreted.
	 */
	public SearchResult search(String queryString) throws IOException, ParseException;
}