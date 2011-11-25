package uk.co.hapax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

import junit.framework.TestCase;

public class RegexTest extends TestCase {
	public void testJdkRegex() {
		Pattern p = Pattern.compile("a|ab");
		Matcher m = p.matcher("ab"); 
		assertTrue(m.matches());
	}
	public void testJdkRegexShortForm() {
		assertTrue("ab".matches("a|ab"));
	}
	public void testJdkRegexFails() {
		Pattern p = Pattern.compile("a|ab");
		Matcher m = p.matcher("b"); 
		assertFalse(m.matches());
	}
	
	public void testAutomatonRegex() {
        RegExp re = new RegExp("a|ab");
        RunAutomaton ra = new RunAutomaton(re.toAutomaton());
		assertTrue(ra.run("ab"));
	}
	public void testAutomatonRegexFails() {
        RegExp re = new RegExp("a|ab");
        RunAutomaton ra = new RunAutomaton(re.toAutomaton());
		assertFalse(ra.run("b"));
	}
}
