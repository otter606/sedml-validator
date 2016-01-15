package org.jlibsedml.sedmlvalidator.web;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/sedml/validate")
public class SedmlValidationController {
	private static final long serialVersionUID = 1L;
	Logger log = LoggerFactory.getLogger(SedmlValidationController.class);

	/**
	 * @see AbstractSBSIServlet#AbstracSBSIServlet()
	 */
	public SedmlValidationController() {
		super();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getValidationForm(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
			return "validateForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String validate(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@RequestParam("xfile") MultipartFile xfile)
			throws ServletException, IOException {
        log.info("Validating with {}", xfile.getOriginalFilename());
		try {
			ValidationData validation = new SedMLFormatHTMLErrorPageGenerator()
					.processUploadedFile(xfile);
			model.addAttribute("data", validation);
			model.addAttribute("item", xfile);
			return "validationResult";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "validateError";
		} 
	}

}

