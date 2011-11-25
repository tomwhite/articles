package org.tiling.misspelling.test;

import junit.framework.TestCase;

import org.tiling.misspelling.phonetic.PhoneticEncoder;

public abstract class AbstractSoundexTest extends TestCase {

	protected PhoneticEncoder soundex;
	
	private void checkSoundex(String word, String expectedSoundex) {
		assertEquals(word, expectedSoundex, soundex.calculateCode(word));
	}
	
	public void testOther() {

		// From Knuth's book
		checkSoundex("Euler", "E460");
		checkSoundex("Ellery", "E460");
		checkSoundex("Gauss", "G200");
		checkSoundex("Ghosh", "G200");
		checkSoundex("Hilbert", "H416");
		checkSoundex("Heilbronn", "H416");
		checkSoundex("Knuth", "K530");
		checkSoundex("Kant", "K530");
		checkSoundex("Lloyd", "L300");
		checkSoundex("Liddy", "L300");
		checkSoundex("Lukasiewicz", "L222");
		checkSoundex("Lissajous", "L222");
		checkSoundex("Wachs", "W200");
		checkSoundex("Waugh", "W200");

		// Table 1
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
		checkSoundex("Ashcroft", "A261");

		// Other
		assertEquals("'h'", "H000", soundex.calculateCode("h"));
		assertEquals("'hood'", "H300", soundex.calculateCode("hood"));
		assertEquals("'eel'", "E400", soundex.calculateCode("eel"));
		assertEquals("'wrod'", "W630", soundex.calculateCode("wrod"));
		assertEquals("'word'", "W630", soundex.calculateCode("word"));
		assertEquals("'where'", "W600", soundex.calculateCode("where"));
		assertEquals(
			"'s' and 'c' combine even though there is an intervening 'h'",
			"P260",
			soundex.calculateCode("pushchair"));
			
	}

	// From org.apache.commons.codec.language.SoundexTest
	 
	void encodeAll(String[] strings, String expectedEncoding) {
		for (int i = 0; i < strings.length; i++) {
			assertEquals(expectedEncoding, soundex.calculateCode(strings[i]));
		}
	}
	public void testB650() {
		this.encodeAll(
			new String[] {
				"BARHAM",
				"BARONE",
				"BARRON",
				"BERNA",
				"BIRNEY",
				"BIRNIE",
				"BOOROM",
				"BOREN",
				"BORN",
				"BOURN",
				"BOURNE",
				"BOWRON",
				"BRAIN",
				"BRAME",
				"BRANN",
				"BRAUN",
				"BREEN",
				"BRIEN",
				"BRIM",
				"BRIMM",
				"BRINN",
				"BRION",
				"BROOM",
				"BROOME",
				"BROWN",
				"BROWNE",
				"BRUEN",
				"BRUHN",
				"BRUIN",
				"BRUMM",
				"BRUN",
				"BRUNO",
				"BRYAN",
				"BURIAN",
				"BURN",
				"BURNEY",
				"BYRAM",
				"BYRNE",
				"BYRON",
				"BYRUM" },
			"B650");
	}

	public void testEncodeBasic() {
		assertEquals("T235", soundex.calculateCode("testing"));
		assertEquals("T000", soundex.calculateCode("The"));
		assertEquals("Q200", soundex.calculateCode("quick"));
		assertEquals("B650", soundex.calculateCode("brown"));
		assertEquals("F200", soundex.calculateCode("fox"));
		assertEquals("J513", soundex.calculateCode("jumped"));
		assertEquals("O160", soundex.calculateCode("over"));
		assertEquals("T000", soundex.calculateCode("the"));
		assertEquals("L200", soundex.calculateCode("lazy"));
		assertEquals("D200", soundex.calculateCode("dogs"));
	}

	/**
	 * Examples from
	 * http://www.bradandkathy.com/genealogy/overviewofsoundex.html
	 */
	public void testEncodeBatch2() {
		assertEquals("A462", soundex.calculateCode("Allricht"));
		assertEquals("E166", soundex.calculateCode("Eberhard"));
		assertEquals("E521", soundex.calculateCode("Engebrethson"));
		assertEquals("H512", soundex.calculateCode("Heimbach"));
		assertEquals("H524", soundex.calculateCode("Hanselmann"));
		assertEquals("H431", soundex.calculateCode("Hildebrand"));
		assertEquals("K152", soundex.calculateCode("Kavanagh"));
		assertEquals("L530", soundex.calculateCode("Lind"));
		assertEquals("L222", soundex.calculateCode("Lukaschowsky"));
		assertEquals("M235", soundex.calculateCode("McDonnell"));
		assertEquals("M200", soundex.calculateCode("McGee"));
		assertEquals("O155", soundex.calculateCode("Opnian"));
		assertEquals("O155", soundex.calculateCode("Oppenheimer"));
		assertEquals("R355", soundex.calculateCode("Riedemanas"));
		assertEquals("Z300", soundex.calculateCode("Zita"));
		assertEquals("Z325", soundex.calculateCode("Zitzmeinn"));
	}

	/**
	 * Examples from
	 * http://www.archives.gov/research_room/genealogy/census/soundex.html
	 */
	public void testEncodeBatch3() {
		assertEquals("W252", soundex.calculateCode("Washington"));
		assertEquals("L000", soundex.calculateCode("Lee"));
		assertEquals("G362", soundex.calculateCode("Gutierrez"));
		assertEquals("P236", soundex.calculateCode("Pfister"));
		assertEquals("J250", soundex.calculateCode("Jackson"));
		assertEquals("T522", soundex.calculateCode("Tymczak"));
		// For VanDeusen: D-250 (D, 2 for the S, 5 for the N, 0 added) is also
		// possible.
		assertEquals("V532", soundex.calculateCode("VanDeusen"));
	}

	/**
	 * Examples from: http://www.myatt.demon.co.uk/sxalg.htm
	 */
	public void testEncodeBatch4() {
		assertEquals("H452", soundex.calculateCode("HOLMES"));
		assertEquals("A355", soundex.calculateCode("ADOMOMI"));
		assertEquals("V536", soundex.calculateCode("VONDERLEHR"));
		assertEquals("B400", soundex.calculateCode("BALL"));
		assertEquals("S000", soundex.calculateCode("SHAW"));
		assertEquals("J250", soundex.calculateCode("JACKSON"));
		assertEquals("S545", soundex.calculateCode("SCANLON"));
		assertEquals("S532", soundex.calculateCode("SAINTJOHN"));

	}

	public void testEncodeIgnoreApostrophes() {
		this.encodeAll(new String[] { "OBrien", "'OBrien", "O'Brien", "OB'rien", "OBr'ien", "OBri'en", "OBrie'n", "OBrien'" }, "O165");
	}

	/**
	 * Test data from http://www.myatt.demon.co.uk/sxalg.htm
	 * 
	 * @throws EncoderException
	 */
	public void testEncodeIgnoreHyphens() {
		this.encodeAll(
			new String[] {
				"KINGSMITH",
				"-KINGSMITH",
				"K-INGSMITH",
				"KI-NGSMITH",
				"KIN-GSMITH",
				"KING-SMITH",
				"KINGS-MITH",
				"KINGSM-ITH",
				"KINGSMI-TH",
				"KINGSMIT-H",
				"KINGSMITH-" },
			"K525");
	}

	public void testEncodeIgnoreTrimmable() {
		assertEquals("W252", soundex.calculateCode(" \t\n\r Washington \t\n\r "));
	}

	/**
	 * Consonants from the same code group separated by W or H are treated as
	 * one.
	 */
	public void testHWRuleEx1() {
		// From
		// http://www.archives.gov/research_room/genealogy/census/soundex.html:
		// Ashcraft is coded A-261 (A, 2 for the S, C ignored, 6 for the R, 1
		// for the F). It is not coded A-226.
		assertEquals("A261", soundex.calculateCode("Ashcraft"));
	}

	/**
	 * Consonants from the same code group separated by W or H are treated as
	 * one.
	 * 
	 * Test data from http://www.myatt.demon.co.uk/sxalg.htm
	 */
	public void testHWRuleEx2() {
		assertEquals("B312", soundex.calculateCode("BOOTHDAVIS"));
		assertEquals("B312", soundex.calculateCode("BOOTH-DAVIS"));
	}

	/**
	 * Consonants from the same code group separated by W or H are treated as
	 * one.
	 * 
	 * Test data from http://www.myatt.demon.co.uk/sxalg.htm
	 */
	public void testHWRuleEx3() {
		assertEquals("S460", soundex.calculateCode("Sgler"));
		assertEquals("S460", soundex.calculateCode("Swhgler"));
		// Also S460:
		this.encodeAll(
			new String[] {
				"SAILOR",
				"SALYER",
				"SAYLOR",
				"SCHALLER",
				"SCHELLER",
				"SCHILLER",
				"SCHOOLER",
				"SCHULER",
				"SCHUYLER",
				"SEILER",
				"SEYLER",
				"SHOLAR",
				"SHULER",
				"SILAR",
				"SILER",
				"SILLER" },
			"S460");
	}

}