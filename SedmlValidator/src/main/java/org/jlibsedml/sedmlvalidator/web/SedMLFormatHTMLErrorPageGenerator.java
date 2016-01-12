package org.jlibsedml.sedmlvalidator.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jlibsedml.Libsedml;
import org.jlibsedml.SEDMLDocument;
import org.jlibsedml.SedMLError;
import org.jlibsedml.XMLException;
import org.springframework.web.multipart.MultipartFile;

public class SedMLFormatHTMLErrorPageGenerator {

	class ParseValidationError implements IValidationError {
		int line;
		Severity sev;
		String message;

		public ParseValidationError(int line, String message, Severity sev) {
			super();
			this.line = line;
			this.message = message;
			this.sev = sev;
		}

		public int getLineNumber() {
			return line;
		}

		public String getMessage() {
			return message;
		}

		public Severity getSeverity() {
			return sev;
		}

	}

	TagGeneratorBase tg = new TagGeneratorBase();
	private IValidationError parseError;

	public String processUploadedFile(MultipartFile item) throws Exception {
		// reset
		parseError = null;
		TagGeneratorBase tg = new TagGeneratorBase();

		File temp = File.createTempFile("sedmlData", "sedmlData");
		StringBuffer sb = new StringBuffer();
		byte[] bytes = item.getBytes();
	
	
		String docAsString= new String(bytes);
	 
		List<SedMLError> errors = new ArrayList<SedMLError>();
		SEDMLDocument doc=null;
		
		try {
			if(!Libsedml.isSEDML(new ByteArrayInputStream(bytes))){
				parseError = new ParseValidationError(0, "File is not SED-ML - the file should be an XML document" +
						" with namespace http://sed-ml.org",
						Severity.ERROR);
			}
			 doc = Libsedml.readDocumentFromString(docAsString);
			errors = doc.getErrors();
		}catch (final XMLException e2) {
			StringBuffer sb2 = new StringBuffer();
			sb2.append("Invalid XML syntax - could not parse file, please ensure XML is well-formed.\n");
			for ( StackTraceElement el :e2.getStackTrace())
				sb2.append(el.toString()).append("\n");
		   
			String message=e2.getMessage()+" - "+ sb2.toString();
			if (StringUtils.isEmpty(e2.getMessage()) && e2.getCause() != null) {
				if (e2.getCause().getMessage() != null) {
					message = e2.getCause().getMessage();
				}
			}
			parseError = new ParseValidationError(0, message,
					Severity.ERROR);
		}// jaxb does not catch these exceptions, need to catch here!! (o r in library) 
		 catch (NumberFormatException nfe) {
				parseError = new ParseValidationError(0, "A number format exception was thrown",
						Severity.ERROR);
		}catch(Exception e) {
			
			parseError = new ParseValidationError(0, "An unexpected exception was thrown:[" + e.getMessage() + "], please report this to the SBSI Team."
					 , Severity.ERROR);
		}
		List<IValidationError> adapted = new ArrayList<IValidationError>();
		ErrorHTMLGenerator gen = new ErrorHTMLGenerator();
		if (errors.isEmpty() && parseError == null) {
			gen.showSuccessfulValidation(item, sb);

		} else {
			for (SedMLError error : errors) {
				adapted.add(new SedMLErrorAdapter(error));
			}
			if (parseError != null) {
				adapted.add(parseError);
			}
			String output;
			if(doc==null || parseError!=null){
				output=docAsString;
			}else
				output=doc.writeDocumentToString();
			gen.getContentForErrorPage(item, sb, adapted, docAsString.split("\\n"));

		}
		sb.append(tg.preEnd());
		temp.delete();

		return sb.toString();
	}



	private void showSuccessfulValidation(MultipartFile item, StringBuffer response)
			throws IOException {
		response.append("<span class=\"no_error\">Syntax OK for " + item.getName()+"</span>");
	}

}

