package org.jlibsedml.sedmlvalidator.web;

public class LineData {
	
	private boolean error;
	
	private String line;
	
	private int lineNo;

	public LineData(boolean isError, String line, int lineNo) {
		super();
		this.error = isError;
		this.line = line;
		this.lineNo = lineNo;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public String toString() {
		return "LineData [isError=" + error + ", line=" + line + "]";
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean isError) {
		this.error = isError;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

}
