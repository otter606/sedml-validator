package org.jlibsedml.sedmlvalidator.web;

import java.util.List;
/**
 * Encapsulates the validation results
 * @author richard
 *
 */
public class ValidationData {

	private List<IValidationError> errors;
	
	private List<SedmlFileLineValidationData> lineData;

	/**
	 * Get validation or parse errors
	 * @return
	 */
	public List<IValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<IValidationError> errors) {
		this.errors = errors;
	}

	/**
	 * Get information about each line in the SED-ML file
	 * @return
	 */
	public List<SedmlFileLineValidationData> getLineData() {
		return lineData;
	}

	public void setLineData(List<SedmlFileLineValidationData> lineData) {
		this.lineData = lineData;
	}
}
