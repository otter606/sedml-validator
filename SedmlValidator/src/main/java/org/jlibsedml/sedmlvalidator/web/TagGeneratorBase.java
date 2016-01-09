package org.jlibsedml.sedmlvalidator.web;

public class TagGeneratorBase {

	public String preStart() {
		return "<pre>";
	}
	
	public String preEnd(){
		return "</pre>";
	}
	
	public String spanOfClass(String clazz){
		return "<span class=\"" +clazz +"\">";
	}
	
	public String spanEnd (){
		return "</span>";
	}
	
	public String htmlSt() {
	return "<html>";
	}
	
	public String htmlEnd() {
		return "</html>";
	}
	
	public String bodySt (){
		return "<body>";
		
	}
	
	public String bodyEnd () {
		return "</body>";
	}
	
	public String br (){
		return "<br/>";
	}
	
	public String p(){
		return "<p/>";
	}
	/**
	 * 
	 * @param args An even-numbered, non-null array of Strings in attribute name-value pairs
	 * @throws IllegalArgumentException if there is an odd number of parameters
	 * @return
	 */
	public String link(String ... args){
		if( ((args.length % 2)!=0) ){
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<link ");
		for (int i = 0; i< args.length;i+=2){
			sb.append(args[i]).append("=\"").append(args[i+1]).append("\" ");
		}
		sb.append("/>");
		return sb.toString();
	}
	
	public String title(String text) {
		return "<title>" + text + "</title>";
	}

	public String getXHTMLDocType (){
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
	}
}
