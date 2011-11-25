package org.tiling.misspelling.metric;

public class HammingDistanceMetric implements SequenceMetric {

	/**
	 * Calculates the Hamming distance between Strings x and y.
	 */
	public final int distance(String x, String y) {
		int m = x.length();
		int n = y.length();
		if (m != n) {
			throw new IllegalArgumentException(
					"Hamming distance is only defined for strings of the same length.");
		}
		int distance = 0;
		for (int i = 0; i < m; i++) {
			if (x.charAt(i) != y.charAt(i)) {
				distance++;
			}
		}
		return distance;
	}
}