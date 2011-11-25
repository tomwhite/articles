package org.tiling.misspelling.matcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.tiling.misspelling.phonetic.PhoneticEncoder;
public class PhoneticWordMatcher implements WordMatcher {
	private final Dictionary dictionary;
	private final PhoneticEncoder soundex;
	private final HashMap soundexCodesToDictionaryWords = new HashMap();

	public PhoneticWordMatcher(Dictionary dictionary, PhoneticEncoder soundex) {
		this.dictionary = dictionary;
		this.soundex = soundex;
		for (Iterator i = dictionary.getWords().iterator(); i.hasNext();) {
			String dictionaryWord = (String) i.next();
			String soundexCode = soundex.calculateCode(dictionaryWord);

			List dictionaryWords =
				(List) soundexCodesToDictionaryWords.get(soundexCode);
			if (dictionaryWords == null) {
				dictionaryWords = new ArrayList();
				soundexCodesToDictionaryWords.put(soundexCode, dictionaryWords);
			}
			dictionaryWords.add(dictionaryWord);
		}
	}

	public WordMatch match(String word) {
		WordMatch match = new WordMatch(word);
		String soundexCode = soundex.calculateCode(word);
		List dictionaryWords =
			(List) soundexCodesToDictionaryWords.get(soundexCode);
		if (dictionaryWords != null) {
			for (Iterator i = dictionaryWords.iterator(); i.hasNext();) {
				String dictionaryWord = (String) i.next();
				match.add(dictionaryWord, 0); // no score
			}
		}
		return match;
	}
}