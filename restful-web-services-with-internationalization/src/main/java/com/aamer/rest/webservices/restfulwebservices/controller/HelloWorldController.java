package com.aamer.rest.webservices.restfulwebservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aamer.rest.webservices.restfulwebservices.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	// Return string in response
	//@RequestMapping(method=RequestMethod.GET, path="/helloWorld")
	@GetMapping(path="/helloWorld")
	public String helloWorld(){
		return "Hello World!";
	}
	
	
	// Return Bean in response
	//@RequestMapping(method=RequestMethod.GET, path="/helloWorld")
	@GetMapping(path="/helloWorldBean")
	public HelloWorldBean helloWorldBean(){
		return new HelloWorldBean("Hello World");
	}
	
	// Use of path variable : http://localhost:8080/helloWorldPathVariable/aamer
	@GetMapping(path="/helloWorldPathVariable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
		return new HelloWorldBean("Hello World "+name);
	}
	
	// based on the header Accept-language which we will pass with value as 'us',
	// this method will use the properties file message_us and message_fr to display good morning
	@GetMapping(path="/helloWorldInternationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale){
		return messageSource.getMessage("good.morning.message", null,locale);
	}
	
}
