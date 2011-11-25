package org.tiling.xmlcatalog.test.transform;

import junit.framework.TestCase;

import org.tiling.xmlcatalog.test.InputSourceUtil;
import org.tiling.xmlcatalog.transform.VanillaXsltTransformer;
import org.tiling.xmlcatalog.transform.XsltTransformer;

public class VanillaXsltTransformerTest extends TestCase {
	
	public void testInclude() throws Exception {
		XsltTransformer transformer = new NetworkCheckXsltTransformer(new VanillaXsltTransformer());
		transformer.transform(
			InputSourceUtil.getInputSource("recipe.xml"),
			InputSourceUtil.getInputSource("recipe_with_include.xslt")
		);
	}

	public void testImport() throws Exception {
		XsltTransformer transformer = new NetworkCheckXsltTransformer(new VanillaXsltTransformer());
		transformer.transform(
			InputSourceUtil.getInputSource("recipe.xml"),
			InputSourceUtil.getInputSource("recipe_with_import.xslt")
		);
	}

}
