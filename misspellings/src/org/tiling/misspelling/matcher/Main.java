package org.tiling.misspelling.matcher;

import java.util.Iterator;

import org.tiling.misspelling.phonetic.KnuthSoundex;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Dictionary dictionary = Dictionary.loadFromFile(new java.io.File("D:\\Apps\\english_dic\\eng_com.dic"));
		WordMatcher matcher;

		matcher = new PhoneticWordMatcher(dictionary, new KnuthSoundex());
		WordMatch match = matcher.match("algorithum");
		for (Iterator i = match.matches(); i.hasNext(); ) {
			System.out.println(i.next());
		}
		for (Iterator i = match.scores(); i.hasNext(); ) {
			System.out.println(i.next());
		}
	}
}