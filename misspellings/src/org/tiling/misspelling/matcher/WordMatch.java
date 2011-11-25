package org.tiling.misspelling.matcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class WordMatch {
	private final String word;
	private final TreeMap matches = new TreeMap();
	
	public WordMatch(String word) {
		this.word = word;
	}
	
	public void add(String dictionaryWord, int score) {
		Integer key = new Integer(score);
		List dictionaryWords = (List) matches.get(key);
		if (dictionaryWords == null) {
			dictionaryWords = new ArrayList();
			matches.put(key, dictionaryWords);
		}
		dictionaryWords.add(dictionaryWord);
	}
	public int getNumberOfMatches() {
		return matches.size();
	}
	public Iterator matches() {
		return matches.values().iterator();
	}
	public Iterator scores() {
		return matches.keySet().iterator();
	}
}