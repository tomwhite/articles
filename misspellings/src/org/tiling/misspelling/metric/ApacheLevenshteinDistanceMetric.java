package org.tiling.misspelling.metric;

import org.apache.commons.lang.StringUtils;

/**
 * This is a wrapper around the Apache Jakarta Commons Lang implementation
 * of the Levenshtein algorithm.
 */
public class ApacheLevenshteinDistanceMetric implements SequenceMetric {

	public int distance(String x, String y) {
		return StringUtils.getLevenshteinDistance(x, y);
	}

}
