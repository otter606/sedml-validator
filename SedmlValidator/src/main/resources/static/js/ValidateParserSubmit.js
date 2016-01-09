/*
 *  Function that tests the text value of a node for emptiness. If it is empty,
 *  inserts a prompt  in the form to add a value to the text.
 *  The parameter to this must be a tring ID of a text-based form element.
 */

function check(submitID) {
	
	if( typeof submitID == "string" ) {
	
		 submitEl = document.getElementById(submitID);
		
	    var content =  submitEl.value;
	    if(content.match(/^\s*$/)) {
	    	
	     var x = document.getElementById("errorLine"); 
	     // test if we already have an error line...
	     if(x) {
	    	 return false;
	     } else {
	      p  = document.createElement('p');
	     
	      p.style.cssText ='color: red; font-size:0.8em; font-style:italic;';
	      p.setAttribute("id", "errorLine");
	      
	      text = document.createTextNode("* Please supply a file for upload... *");
	      p.appendChild(text);
	      
	      submitEl.parentNode.insertBefore(p, submitEl);
	     }
	     
		 return false;
	    }
	    return true;
	}
	
	return true;

}


