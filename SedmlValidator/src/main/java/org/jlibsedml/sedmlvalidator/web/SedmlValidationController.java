package org.jlibsedml.sedmlvalidator.web;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servlet implementation class SedmlValidateServlet
 */
@Controller
@RequestMapping(value = "/sedml/validate")
public class SedmlValidationController {
	private static final long serialVersionUID = 1L;

	/**
	 * @see AbstractSBSIServlet#AbstracSBSIServlet()
	 */
	public SedmlValidationController() {
		super();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String validate(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@RequestParam("xfile") MultipartFile xfile)
			throws ServletException, IOException {

		try {
			String output = new SedMLFormatHTMLErrorPageGenerator()
					.processUploadedFile(xfile);
			model.addAttribute("content", output);
			return "validationResult";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "validateError";
		} 
	}

}

