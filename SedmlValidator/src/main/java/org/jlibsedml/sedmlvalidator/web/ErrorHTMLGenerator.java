package org.jlibsedml.sedmlvalidator.web;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ErrorHTMLGenerator extends TagGeneratorBase{
	
	public void showSuccessfulValidation(MultipartFile item,
			StringBuffer response) throws IOException {
	//	response.append(spanOfClass("no_error") +" Syntax OK for " + item.getOriginalFilename()+spanEnd());
	}

	public void getContentForErrorPage(MultipartFile item, StringBuffer response,
			List<IValidationError> errors, final String [] lines) throws IOException {
	//	response.append("<strong><span class=\"error\">Errors found in file: " + item.getOriginalFilename()+  "</span></strong>").append(p());
//		for (IValidationError error: errors ){
//			response.append(error.getMessage() + " at line " + error.getLineNumber() + p());
//		}
//		response.append("Lines with syntax errors are highlighted below:").append(p());
	//	response.append(preStart());
//		for (int lineNo = 0; lineNo <lines.length;lineNo++) {
//			String reformatted=lines[lineNo].replaceAll(">", "&gt;")
//			                                 .replaceAll("<", "&lt;");
//			
//			if (hasErrorAtLine(errors ,lineNo)){
//				response.append( createLineNumber(lineNo) + createFormattedError(reformatted)).append("\n");
//			}
//			else
//			  response.append( createLineNumber(lineNo)+ reformatted.trim()).append("\n");
//		}
	}
	
	
	private String createFormattedError(String line) {
		return spanOfClass("error") + line + spanEnd();
	}

	private String createLineNumber(int lineNo) {
		return spanOfClass("lineNo") + (lineNo+1) +"." + spanEnd();
	}
}
