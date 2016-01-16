package org.jlibsedml.sedmlvalidator.web.xml2html;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jlibsedml.sedmlvalidator.web.SEDML2HTMLTransformer;
import org.jlibsedml.sedmlvalidator.web.XSLTransformException;
import org.junit.Test;

public class XSLTTransformTest {
	
	File testFile = new File("src/test/resources/sedMLBIOM21.xml");
	SEDML2HTMLTransformer transformer;

	@Test
	public void testTransformToHTML() throws XSLTransformException, IOException {
		FileInputStream fis = new FileInputStream(testFile);
		transformer = new SEDML2HTMLTransformer(fis);
//		String html = transformer.transformToHTML(true);
//		File tempFile = File.createTempFile("abc", ".html");
//		 FileUtils.writeStringToFile(tempFile, html);
	}

}
