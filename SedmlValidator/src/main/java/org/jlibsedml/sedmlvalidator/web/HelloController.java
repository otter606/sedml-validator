package org.jlibsedml.sedmlvalidator.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller()
@RequestMapping("/hello")
public class HelloController {
	public HelloController(){
		super();
	}

    @ResponseBody
    @RequestMapping(method=RequestMethod.GET)
    String home() {
        return "Hello World!";
    }

 
}

