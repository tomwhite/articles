@snip_start@package org.tiling.misspelling.metric;

@snip_end@public class LevenshteinDistanceMetric implements SequenceMetric {
	/**
	 * Calculates the distance between Strings x and y using the
	 * <b>Dynamic Programming</b> algorithm.
	 */
	public final int distance(String x, String y) {

		int m = x.length();
		int n = y.length();

		int[][] T = new int[m + 1][n + 1];

		T[0][0] = 0;
		for (int j = 0; j < n; j++) {
			T[0][j + 1] = T[0][j] + ins(y, j);
		}
		for (int i = 0; i < m; i++) {
			T[i + 1][0] = T[i][0] + del(x, i);
			for (int j = 0; j < n; j++) {
				T[i + 1][j + 1] =	min(
						T[i][j] + sub(x, i, y, j),
						T[i][j + 1] + del(x, i),
						T[i + 1][j] + ins(y, j)
				);
			}
		}

		return T[m][n];
	}
	private int sub(String x, int xi, String y, int yi) {
		return x.charAt(xi) == y.charAt(yi) ? 0 : 1;
	}
	private int ins(String x, int xi) {
		return 1;
	}
	private int del(String x, int xi) {
		return 1;
	}
	private int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
}