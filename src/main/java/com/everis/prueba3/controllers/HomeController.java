package com.everis.prueba3.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//session.invalidate();
	    session.setAttribute("registrado",0);
		return "index.jsp";
	}
}
