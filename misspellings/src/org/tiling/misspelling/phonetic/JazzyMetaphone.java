package org.tiling.misspelling.phonetic;

import com.swabunga.spell.engine.DoubleMeta;

/**
 * This is a wrapper around the Jazzy implementation
 * of the Metaphone algorithm.
 */
public class JazzyMetaphone implements PhoneticEncoder {
	private DoubleMeta metaphone = new DoubleMeta();
	public String calculateCode(String string) {
		return metaphone.transform(string);
	}
}
