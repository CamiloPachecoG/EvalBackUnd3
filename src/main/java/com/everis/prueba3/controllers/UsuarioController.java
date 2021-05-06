package com.everis.prueba3.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.prueba3.models.Usuario;
import com.everis.prueba3.services.UsuarioService;

@Controller
@RequestMapping("/login")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuService;
	
	@RequestMapping("")
	public String login(HttpSession session) {
		session.setAttribute("registrado",0);
		return "login.jsp";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "registro.jsp";
	}
	
	@RequestMapping("/registerAdd")
	public String registro(@RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido,
			@RequestParam("email") String email, 
			@RequestParam("password") String password, Model model) {
		
		int validaciones = 0;
		
		if(nombre.length() > 0 && apellido.length() > 0 && password.length() > 0 && email.length() > 0) {
			validaciones++;
		}
		
		if(validaciones == 1) {
			Usuario usuario = new Usuario();
			
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setEmail(email);
			usuario.setPassword(password);
			
			usuario = usuService.save(usuario);
			
			return "redirect:/login";
		}else {
			model.addAttribute("mensajeReg", "Todos los datos son requeridos!");
			return "registro.jsp";
		}
		
	}
	
	@RequestMapping("/ingresar")
	public String ingresar(@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session,
			Model model) {
		
		if(usuService.autenticacion(email, password)) {
			session.setAttribute("email", email);
			session.setAttribute("registrado", 1);
			session.setAttribute("contador", 100);
			
			String mail = (String) session.getAttribute("email");
			Integer registrado = (Integer) session.getAttribute("registrado");
			Integer contador = (Integer) session.getAttribute("contador");
			
			return "index.jsp";
		}else {
			session.removeAttribute("registrado");
			session.setAttribute("registrado", 0);
			model.addAttribute("mensajeLogin", "Datos erroneos!!");
			return "login.jsp";
		}
		
	}
}
