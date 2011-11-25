package org.tiling.misspelling.metric;

public interface SequenceMetric {

	/**
	 * Calculates the distance between Strings x and y.
	 */
	public int distance(String x, String y);
}