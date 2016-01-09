package org.jlibsedml.sedmlvalidator.web;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

 public class SEDML2HTMLTransformer {
	
	
	private InputStream XML;
	InputStream getXML() {
		return XML;
	}
	public SEDML2HTMLTransformer(InputStream fileInputStream) {
		super();
		this.XML = fileInputStream;
	}
	
	
	protected String getStyleSheet() {
		return "sedml2html.xsl";
		}
	
	public final String transformToHTML(boolean isDiv) throws XSLTransformException {
		if (getXML() != null) {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			
			
			Transformer transformer;
			try {
				InputStream is = SEDML2HTMLTransformer.class.getClassLoader().getResourceAsStream(getStyleSheet());
				
				StreamSource ss = new StreamSource(	is);
				
				transformer = tFactory.newTransformer(ss);
				transformer.setParameter("div", Boolean.toString(isDiv));
				ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
				
				
			
				transformer.transform(new StreamSource(getXML()), new StreamResult(
						baos));
				
				return baos.toString();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new XSLTransformException(e.getMessage(), e);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new XSLTransformException("There was a serious error trying to process this file," +
				" the processor needs  a well-formed SED-ML document to generate HTML.<br>" +
				" Detailed message is: <br/>" +
				"" + e.getMessage(),e);
			} 
			
		} else {
			return null;
		}	
	}
}

