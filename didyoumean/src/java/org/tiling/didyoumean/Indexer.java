package org.tiling.didyoumean;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.Date;

/**
 * This code was originally written for
 * Erik's Lucene intro java.net article
 */
public class Indexer {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			throw new Exception("Usage: java " + Indexer.class.getName()
					+ " <index dir> <data dir>");
		}
		File indexDir = new File(args[0]);
		File dataDir = new File(args[1]);

		long start = new Date().getTime();
		int numIndexed = index(indexDir, dataDir);
		long end = new Date().getTime();

		System.out.println("Indexing " + numIndexed + " files took "
				+ (end - start) + " milliseconds");
	}

	public static int index(File indexDir, File dataDir) throws IOException {

		if (!dataDir.exists() || !dataDir.isDirectory()) {
			throw new IOException(dataDir
					+ " does not exist or is not a directory");
		}

		IndexWriter writer = new IndexWriter(indexDir, new StandardAnalyzer(),
				true);
		writer.setUseCompoundFile(false);

		indexDirectory(writer, dataDir);

		int numIndexed = writer.docCount();
		writer.optimize();
		writer.close();
		return numIndexed;
	}

	private static void indexDirectory(IndexWriter writer, File dir)
			throws IOException {

		File[] files = dir.listFiles();

		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(writer, f); // recurse
			} else if (f.getName().endsWith(".txt")) {
				indexFile(writer, f);
			}
		}
	}

	private static void indexFile(IndexWriter writer, File f)
			throws IOException {

		if (f.isHidden() || !f.exists() || !f.canRead()) {
			return;
		}

		System.out.println("Indexing " + f.getCanonicalPath());

		Document doc = new Document();
		doc.add(Field.Text("contents", new FileReader(f)));
		doc.add(Field.Keyword("filename", f.getCanonicalPath()));
		doc.add(Field.Keyword("name", f.getName()));
		doc.add(Field.Keyword("url", f.toURI().toURL().toExternalForm()));
		writer.addDocument(doc);
	}
}
