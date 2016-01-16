package org.jlibsedml.sedmlvalidator.web;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

 public class SEDML2HTMLTransformer {
	
	
	private InputStream xmlFileInputStream;
	InputStream getXML() {
		return xmlFileInputStream;
	}
	public SEDML2HTMLTransformer(InputStream fileInputStream) {
		super();
		this.xmlFileInputStream = fileInputStream;
	}
	
	
	protected String getStyleSheet() {
		return "sedml2html.xsl";
	}
	
	
//	public final String transformToHTML(boolean isDiv) throws XSLTransformException, IOException {
//		if (getXML() != null) {
//			TransformerFactory tFactory = TransformerFactory.newInstance();		
//			Transformer transformer;
//			try {
//				InputStream is = new ClassPathResource(getStyleSheet()).getInputStream();
//				StreamSource ss = new StreamSource(	is);
//				transformer = tFactory.newTransformer(ss);
//				transformer.setParameter("div", Boolean.toString(isDiv));
//				ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
//				transformer.transform(new StreamSource(getXML()), new StreamResult(
//						baos));
//				return baos.toString();
//			} catch (TransformerConfigurationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				throw new XSLTransformException(e.getMessage(), e);
//			} catch (TransformerException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				throw new XSLTransformException("There was a serious error trying to process this file," +
//				" the processor needs  a well-formed SED-ML document to generate HTML.<br>" +
//				" Detailed message is: <br/>" +
//				"" + e.getMessage(),e);
//			} 			
//		} else {
//			return null;
//		}	
//	}
}

