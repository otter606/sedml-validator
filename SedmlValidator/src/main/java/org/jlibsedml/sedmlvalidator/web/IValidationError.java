package org.jlibsedml.sedmlvalidator.web;

public interface IValidationError {
	
	String getMessage();
	
	int getLineNumber();
	
	Severity getSeverity();
	
	

}
