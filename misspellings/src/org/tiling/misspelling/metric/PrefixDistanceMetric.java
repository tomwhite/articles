package org.tiling.misspelling.metric;

public class PrefixDistanceMetric implements SequenceMetric {

	/**
	 * Calculates the prefix distance between Strings x and y.
	 */
	public final int distance(String x, String y) {

		int m = x.length();
		int n = y.length();
		int min = Math.min(m, n);

		int lcp = 0;
		for (int i = 0; i < min; i++) {
			if (x.charAt(i) != y.charAt(i)) {
				break;
			}
			lcp++;
		}

		return m + n - 2 * lcp;
	}
}