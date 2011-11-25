package org.tiling.misspelling.test;

import junit.framework.TestCase;

import org.tiling.misspelling.phonetic.OReillyPythonSoundex;
import org.tiling.misspelling.phonetic.OdellRussellSoundex;
import org.tiling.misspelling.phonetic.PhoneticEncoder;

public class SoundexVariantsTest extends TestCase {

	private PhoneticEncoder soundex;
	private void checkSoundex(String word, String expectedSoundex) {
		assertEquals(word, expectedSoundex, soundex.calculateCode(word));
	}
	public void testOdellRussell() {
		soundex = new OdellRussellSoundex();
		checkSoundex("White", "W14");
		checkSoundex("Wood", "W14");
		checkSoundex("Woods", "W14");
		checkSoundex("Lee", "L1");
		checkSoundex("Leigh", "L1");
		checkSoundex("Thomson", "T1637");
		checkSoundex("Thompson", "T16237");
		checkSoundex("Tchaikovsky", "T31323");
		checkSoundex("Chaikovsky", "C1323");
		checkSoundex("Lloyd", "L14");
		checkSoundex("Ashcroft", "A3824");
	}

	public void testOReillyPython() {
		soundex = new OReillyPythonSoundex();
		checkSoundex("White", "W300");
		checkSoundex("Wood", "W300");
		checkSoundex("Woods", "W320");
		checkSoundex("Lee", "L000");
		checkSoundex("Leigh", "L200");
		checkSoundex("Thomson", "T525");
		checkSoundex("Thompson", "T512");
		checkSoundex("Tchaikovsky", "T221");
		checkSoundex("Chaikovsky", "C212");
		checkSoundex("Lloyd", "L300");
		checkSoundex("Ashcroft", "A226");
	}

}