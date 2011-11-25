package org.tiling.xmlcatalog.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

import org.xml.sax.InputSource;

public class InputSourceUtil {
	public static InputSource getInputSource(String filename) throws FileNotFoundException {
		return new InputSource(new FileReader(new File("documents\\" + filename)));
	}

	public static InputSource asInputSource(String xmlDocument) {
		return new InputSource(new StringReader(xmlDocument));
	}
}
