package org.jlibsedml.sedmlvalidator.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
	
	   public static void main(String[] args) throws Exception {
	        SpringApplication.run(Configuration.class, args);
	    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

 
}
