package com.ssm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.dao.UserDAO;
import com.ssm.pojo.User;

@Controller
public class LoginController {
	 @Autowired
	    private UserDAO userDAO;
	 
	@RequestMapping(value = "/")
	public ModelAndView welcome() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("userData") User user) {
		ModelAndView mav = new ModelAndView();
		User userFromDB = userDAO.getUser(user.getName());
		if(userFromDB.getPassword().equalsIgnoreCase(user.getPassword())){
			mav.setViewName("home");
			mav.addObject("name", "Home Page");
			mav.addObject("description", "Login success");
		}else{
			mav.setViewName("login");
			mav.addObject("error", "true");
			mav.addObject("description", "Login Error. Invalid password");
		}		
		return mav;
	}
}
