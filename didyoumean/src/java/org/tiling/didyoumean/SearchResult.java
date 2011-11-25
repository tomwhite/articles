package org.tiling.didyoumean;

import java.util.List;

public class SearchResult {
	
	private List topHits;
	private int totalHitCount;
	private long searchDuration;
	private String originalQuery;
	private String suggestedQuery;

	public SearchResult(List topHits, int totalHitCount, long searchDuration, String originalQuery) {
		this(topHits, totalHitCount, searchDuration, originalQuery, null);
	}

	public SearchResult(List topHits, int totalHitCount, long searchDuration, String originalQuery, String suggestedQuery) {
		this.topHits = topHits;
		this.totalHitCount = totalHitCount;
		this.searchDuration = searchDuration;
		this.originalQuery = originalQuery;
		this.suggestedQuery = suggestedQuery;
	}

	public List getTopHits() {
		return topHits;
	}
	
	public int getTopHitCount() {
	    return topHits.size();
	}
	
	public int getTotalHitCount() {
	    return totalHitCount;
	}
	
	public long getSearchDuration() {
		return searchDuration;
	}

	public String getOriginalQuery() {
		return originalQuery;
	}
	public String getSuggestedQuery() {
		return suggestedQuery;
	}
}