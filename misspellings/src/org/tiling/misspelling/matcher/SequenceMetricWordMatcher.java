package org.tiling.misspelling.matcher;

import java.util.Iterator;

import org.tiling.misspelling.metric.SequenceMetric;

public class SequenceMetricWordMatcher implements WordMatcher {
	private final Dictionary dictionary;
	private final SequenceMetric sequenceMetric;
	private final int maximumScore;
	
	public SequenceMetricWordMatcher(Dictionary dictionary,
			SequenceMetric sequenceMetric, int maximumScore) {
		
		this.dictionary = dictionary;
		this.sequenceMetric = sequenceMetric;
		this.maximumScore = maximumScore;
	}
	
	public WordMatch match(String word) {
		WordMatch match = new WordMatch(word);
		for (Iterator i = dictionary.getWords().iterator(); i.hasNext();) {
			String dictionaryWord = (String) i.next();
			int score = sequenceMetric.distance(word, dictionaryWord);
			if (score <= maximumScore) {
				match.add(dictionaryWord, score);
			}
		}
		return match;
	}
}