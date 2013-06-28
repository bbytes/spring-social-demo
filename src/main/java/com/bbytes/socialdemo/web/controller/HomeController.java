package com.bbytes.socialdemo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class HomeController {

	
	@RequestMapping
	public String home() throws Exception{
		return "home";
	}

}
