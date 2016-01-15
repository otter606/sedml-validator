package org.jlibsedml.sedmlvalidator.web;

import java.util.List;

public class ValidationData {

	private List<IValidationError> errors;
		
	private List<LineData> lineData;

	public List<IValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<IValidationError> errors) {
		this.errors = errors;
	}

	public List<LineData> getLineData() {
		return lineData;
	}

	public void setLineData(List<LineData> lineData) {
		this.lineData = lineData;
	}
}
