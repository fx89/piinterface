package com.desolatetimelines.piinterface.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloRESTController {
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World!";
	}
}
