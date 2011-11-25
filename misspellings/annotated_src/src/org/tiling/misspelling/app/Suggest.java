@snip_start@package org.tiling.misspelling.app;

@snip_end@import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.swabunga.spell.engine.SpellDictionary;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;

public class Suggest {
	
	public static class SuggestionListener implements SpellCheckListener {
		
		public void spellingError(SpellCheckEvent event) {
			System.out.println("Misspelling: " + event.getInvalidWord());
			
			List suggestions = event.getSuggestions();
			if (suggestions.isEmpty()) {
				System.out.println("No suggestions found.");
			} else {
				System.out.print("Suggestions: ");
				
				for (Iterator i = suggestions.iterator(); i.hasNext();) {
					System.out.print(i.next());
					if (i.hasNext()) {
						System.out.print(", ");
					}
				}
				System.out.println();
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("Usage: Suggest <dictionary file>");
			System.exit(1);
		}
		
		SpellDictionary dictionary = new SpellDictionaryHashMap(new File(args[0]));
		SpellChecker spellChecker = new SpellChecker(dictionary);
		spellChecker.addSpellCheckListener(new SuggestionListener());
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.print("Enter line to spell check (return to exit): ");
			String line = in.readLine();

			if (line.length() == 0) {
				break;
			}
			spellChecker.checkSpelling(new StringWordTokenizer(line));
		}
	}

}
