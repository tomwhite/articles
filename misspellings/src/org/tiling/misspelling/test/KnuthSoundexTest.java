package org.tiling.misspelling.test;

import org.tiling.misspelling.phonetic.KnuthSoundex;

public class KnuthSoundexTest extends AbstractSoundexTest {
	public void setUp() {
		soundex = new KnuthSoundex();
	}
}
