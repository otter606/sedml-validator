package org.jlibsedml.sedmlvalidator.web;

import org.jlibsedml.SedMLError;
import org.jlibsedml.SedMLError.ERROR_SEVERITY;



public class SedMLErrorAdapter implements IValidationError {
    private SedMLError error;
	public SedMLErrorAdapter(SedMLError error) {
		super();
		this.error = error;
	
	}

	public int getLineNumber() {
		return error.getLineNo();
	}

	public String getMessage() {
		return error.getMessage();
	}

	public Severity getSeverity() {
		if(error.getSeverity().equals(ERROR_SEVERITY.ERROR) || 
				error.getSeverity().equals(ERROR_SEVERITY.FATAL)){
			return Severity.ERROR;
		}else if(error.getSeverity().equals(ERROR_SEVERITY.WARNING)){
			return Severity.WARNING;
		}else if(error.getSeverity().equals(ERROR_SEVERITY.INFO)){
			return Severity.INFO;
		} 
		else
			return Severity.WARNING;	
	}

}

