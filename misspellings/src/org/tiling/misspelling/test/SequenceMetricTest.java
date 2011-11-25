package org.tiling.misspelling.test;

import org.tiling.misspelling.metric.ApacheLevenshteinDistanceMetric;
import org.tiling.misspelling.metric.HammingDistanceMetric;
import org.tiling.misspelling.metric.LevenshteinDistanceMetric;
import org.tiling.misspelling.metric.PrefixDistanceMetric;
import org.tiling.misspelling.metric.SequenceMetric;

import junit.framework.TestCase;

public class SequenceMetricTest extends TestCase {

	private void checkDistance(
		SequenceMetric metric,
		String x,
		String y,
		int expectedDistance) {
		int distance1 = metric.distance(x, y);
		int distance2 = metric.distance(y, x);
		String message = " (between \"" + x + "\" and \"" + y + "\")";
		assertEquals(
			"Distance must be non-negative" + message,
			true,
			expectedDistance >= 0);
		assertEquals(
			"Distance must be symmetric" + message,
			distance1,
			distance2);
		assertEquals(
			"Incorrect distance" + message,
			expectedDistance,
			distance1);
	}
	
	private void checkDistanceIsUndefined(SequenceMetric metric, String x, String y) {
		try {
			metric.distance(x, y);
			fail("Should throw an exception for words of unequal lengths" +
					" (between \"" + x + "\" and \"" + y + "\")");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			metric.distance(y, x);
			fail("Should throw an exception for words of unequal lengths" +
					" (between \"" + y + "\" and \"" + x + "\")");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}				
	}

	public void testApacheLevenshteinDistance() {
		SequenceMetric metric = new ApacheLevenshteinDistanceMetric();
		checkDistance(metric, "", "", 0);
		checkDistance(metric, "", "a", 1);
		checkDistance(metric, "a", "a", 0);
		checkDistance(metric, "a", "b", 1);
		checkDistance(metric, "a", "A", 1);
		checkDistance(metric, "Levenshtein", "Levunschten", 3);
	}
	public void testHammingDistance() {
		SequenceMetric metric = new HammingDistanceMetric();
		checkDistance(metric, "", "", 0);
		checkDistanceIsUndefined(metric, "", "a");
		checkDistance(metric, "a", "a", 0);
		checkDistance(metric, "a", "b", 1);
		checkDistance(metric, "a", "A", 1);
		checkDistance(metric, "Hamming", "humming", 2);
		checkDistanceIsUndefined(metric, "Hamming", "ham");
	}
	public void testLevenshteinDistance() {
		SequenceMetric metric = new LevenshteinDistanceMetric();
		checkDistance(metric, "", "", 0);
		checkDistance(metric, "", "a", 1);
		checkDistance(metric, "a", "a", 0);
		checkDistance(metric, "a", "b", 1);
		checkDistance(metric, "a", "A", 1);
		checkDistance(metric, "Levenshtein", "Levunschten", 3);
	}
	public void testPrefixDistance() {
		SequenceMetric metric = new PrefixDistanceMetric();
		checkDistance(metric, "", "", 0);
		checkDistance(metric, "", "a", 1);
		checkDistance(metric, "a", "a", 0);
		checkDistance(metric, "a", "b", 2);
		checkDistance(metric, "a", "A", 2);
		checkDistance(metric, "prefix", "pr", 4);
		checkDistance(metric, "prefix", "pro", 5);
	}
}