package org.tiling.misspelling.matcher;

import java.io.LineNumberReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.TreeSet;

import java.util.SortedSet;
public class Dictionary {
	private SortedSet words = new TreeSet();
	private Dictionary() {
	}
	public static Dictionary loadFromFile(File file) throws IOException {
		Dictionary dictionary = new Dictionary();
		LineNumberReader reader =
			new LineNumberReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null) {
			dictionary.add(line.trim());
		}
		return dictionary;
	}
	private void add(String word) {
		words.add(word);
	}
	public SortedSet getWords() {
		return words;
	}
}