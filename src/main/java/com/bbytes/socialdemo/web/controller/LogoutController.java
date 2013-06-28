package com.bbytes.socialdemo.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * Spring Controller class which serve request for sign up page.
 * </p>
 * 
 * @author rajesh
 * @version 1.0
 * 
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

	/**
	 * 
	 * 
	 * @param user
	 * @param session
	 * @return
	 * @throws ErrZeroControllerException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String  submit(HttpSession session) throws Exception {
		if (session != null) {
			session.invalidate();
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		
		return "redirect:login";
	}

}
