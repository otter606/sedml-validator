package org.jlibsedml.sedmlvalidator.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class SedmlValidateServlet
 */
@Controller("")
@RequestMapping("sedml/html")
public class Sedml2HtmlController  {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see AbstractSBSIServlet#AbstracSBSIServlet()
//     */
//    public SedmlValidationController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//    @RequestMapping(method=RequestMethod.POST)
//	public void doPost(HttpServletRequest request, HttpServletResponse response,
//			Model model, @RequestParam MultipartFile xfile){
//        
//		
//					
//					SEDML2HTMLTransformer transformer =  new SEDML2HTMLTransformer(xfile);
//					String output = transformer.transformToHTML(true);
//					if (output ==null) {
//						request.setAttribute("Error_message", "Unfortunately this service could not " +
//								"process your file - please check using the validator that it is real SED-ML.");
//						forward("/ValidateSBSIError.jsp", request, response);
//					}
//					request.setAttribute("content", output);
//					forward("/Sedml2HTML.jsp", request, response);
//				} 
//			}
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			request.setAttribute("Error_message", e.getMessage());
//			forward("/ValidateSBSIError.jsp", request, response);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			request.setAttribute("Error_message", e.getMessage());
//			forward("/ValidateSBSIError.jsp", request, response);
//		}
//	}

}
