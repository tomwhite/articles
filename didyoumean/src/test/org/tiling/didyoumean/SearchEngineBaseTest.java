package org.tiling.didyoumean;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public abstract class SearchEngineBaseTest extends TestCase {
	protected static final String DEFAULT_FIELD = "field";
	protected static final String NAME_FIELD = "name";
	
	protected Directory originalIndex;
	protected SearchEngine searchEngine;
	
	public void setUp() throws IOException {
		originalIndex = new RAMDirectory();
		IndexWriter writer = new IndexWriter(originalIndex, new StandardAnalyzer(),	true);
		writer.addDocument(createDocument("cod and chips", "doc1"));
		writer.addDocument(createDocument("haddock and chips", "doc2"));
		writer.addDocument(createDocument("mackerel", "doc3"));
		writer.optimize();
		writer.close();
	}	
	
	private Document createDocument(String text, String name) {
		Document document = new Document();
		document.add(Field.Text(DEFAULT_FIELD, text));
		document.add(Field.Keyword(NAME_FIELD, name));
		return document;
	}
	
}
