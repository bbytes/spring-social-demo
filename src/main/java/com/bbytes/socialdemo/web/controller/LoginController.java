package com.bbytes.socialdemo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * Spring Controller class which serve request for login page.
 * </p>
 * 
 * @author rajesh
 * @version 1.0
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * <h4>
	 * Shows login page.
	 * </h4>
	 * <p>
	 * Handler will be called on click of login link in page header section.
	 * </p>
	 * 
	 * @return
	 * @throws ErrZeroControllerException
	 */
	@RequestMapping
	public String login() throws Exception{
		return "login";
	}

}
