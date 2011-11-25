package org.tiling.didyoumean;

import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class DidYouMeanIndexer {
	private static final String DEFAULT_FIELD = "contents";
	
	private static final String FIELD_OPTION = "f";
	private static final String ORIGINAL_INDEX_OPTION = "i";
	private static final String SPELL_INDEX_OPTION = "o";

	public void createSpellIndex(String field,
			Directory originalIndexDirectory,
			Directory spellIndexDirectory) throws IOException {
		
		IndexReader indexReader = null;
		try {
			indexReader = IndexReader.open(originalIndexDirectory);
			Dictionary dictionary = new LuceneDictionary(indexReader, field);
			SpellChecker spellChecker = new SpellChecker(spellIndexDirectory);
			spellChecker.indexDictionnary(dictionary);
		} finally {
			if (indexReader != null) {
				indexReader.close();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Options options = createOptions();
	    CommandLineParser parser = new BasicParser();
		try {
			CommandLine line = parser.parse(options, args);
			String field = line.getOptionValue(FIELD_OPTION, DEFAULT_FIELD);
			String origIndex = line.getOptionValue(ORIGINAL_INDEX_OPTION);
			String spellIndex = line.getOptionValue(SPELL_INDEX_OPTION);
			
			DidYouMeanIndexer indexer = new DidYouMeanIndexer();
			FSDirectory origDir = FSDirectory.getDirectory(origIndex, false);
			FSDirectory spellDir = FSDirectory.getDirectory(spellIndex, true);
			// Call intern() on field to work around bug in LuceneDictionary
			indexer.createSpellIndex(field.intern(), origDir, spellDir);		
		} catch (ParseException exp) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("DidYouMeanIndexer", options, true);			
		}
	}
	
	private static Options createOptions() {
		Option field = OptionBuilder.withArgName("field")
			.hasArg()
			.withDescription("Lucene field name to create a spell index for")
			.create(FIELD_OPTION);		
		Option originalIndex = OptionBuilder.withArgName("originalIndex")
			.hasArg()
			.withDescription("original index directory")
			.isRequired()
			.create(ORIGINAL_INDEX_OPTION);		
		Option spellIndex = OptionBuilder.withArgName("spellIndex")
			.hasArg()
			.withDescription("spell index directory")
			.isRequired()
			.create(SPELL_INDEX_OPTION);		
		
		Option help = new Option("help", "print this message");		
		
		Options options = new Options();
		options.addOption(field);
		options.addOption(originalIndex);
		options.addOption(spellIndex);
		options.addOption(help);
		return options;
	}
}
