package org.jlibsedml.sedmlvalidator.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jlibsedml.Libsedml;
import org.jlibsedml.SEDMLDocument;
import org.jlibsedml.SedMLError;
import org.jlibsedml.sedmlvalidator.web.SedMLFormatHTMLErrorPageGenerator.ParseValidationError;
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
@Controller("")
@RequestMapping("sedml/tohtml")
public class Sedml2HtmlController {
	// private static final long serialVersionUID = 1L;
	Logger log = LoggerFactory.getLogger(Sedml2HtmlController.class);

	@RequestMapping(method = RequestMethod.POST)
	public String toHTML(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@RequestParam("xfile") MultipartFile xfile)
			throws ServletException, IOException {
		log.info("Converting file {} to HTML", xfile.getOriginalFilename());
		try {
			byte[] bytes = xfile.getBytes();
			String docAsString = new String(bytes, "UTF-8");
			List<SedMLError> errors = new ArrayList<SedMLError>();
			SEDMLDocument doc = null;

			doc = Libsedml.readDocumentFromString(docAsString);
			model.addAttribute("item", xfile);
			model.addAttribute("sedml", doc.getSedMLModel());
			return "htmlView/l1v1";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "validateError";
		}

	}
}
//
// /**
// * @see AbstractSBSIServlet#AbstracSBSIServlet()
// */
// public SedmlValidationController() {
// super();
// // TODO Auto-generated constructor stub
// }
//
// /**
// * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
// response)
// */
// @RequestMapping(method=RequestMethod.POST)
// public void doPost(HttpServletRequest request, HttpServletResponse response,
// Model model, @RequestParam MultipartFile xfile){
//
//
//
// SEDML2HTMLTransformer transformer = new SEDML2HTMLTransformer(xfile);
// String output = transformer.transformToHTML(true);
// if (output ==null) {
// request.setAttribute("Error_message", "Unfortunately this service could not "
// +
// "process your file - please check using the validator that it is real SED-ML.");
// forward("/ValidateSBSIError.jsp", request, response);
// }
// request.setAttribute("content", output);
// forward("/Sedml2HTML.jsp", request, response);
// }
// }
// } catch (FileUploadException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// request.setAttribute("Error_message", e.getMessage());
// forward("/ValidateSBSIError.jsp", request, response);
// } catch (Exception e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// request.setAttribute("Error_message", e.getMessage());
// forward("/ValidateSBSIError.jsp", request, response);
// }
// }

