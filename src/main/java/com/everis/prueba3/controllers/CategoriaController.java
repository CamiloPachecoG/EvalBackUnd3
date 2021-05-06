package com.everis.prueba3.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.prueba3.models.Categoria;
import com.everis.prueba3.repositories.CategoriaRepository;
import com.everis.prueba3.services.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaService catService;
	@Autowired
	CategoriaRepository catRepo;
	
	@RequestMapping("")
	public String inicio(HttpSession session, Model model) {
		
		Integer registrado = (Integer) session.getAttribute("registrado");
		
		if(registrado == 1) {
			model.addAttribute("listaCategorias", catService.findAll());
			return "categoria.jsp";			
		}
		
		return "login.jsp";
	}
	
	@RequestMapping("/insertar")
	public String insertar(@RequestParam("nombre") String nombre, Model model) {
		
		int validaciones = 0;
		
		if(nombre.length() > 0) {
			validaciones++;
		}
		
		if(validaciones == 1) {
			
			Categoria categoria = new Categoria();
			categoria.setNombre(nombre);
			
			categoria = catService.save(categoria);
			
			return "redirect:";
		}else {
			model.addAttribute("mensaje", "Campo Requerido!!!");
			return "redirect:";
		}
	}
	
	@RequestMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		
		Categoria categoria = catService.buscarPorId(id);
		model.addAttribute("categoria", categoria);
		return "editCategoria.jsp";
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@Valid @ModelAttribute("categoria") Categoria categoria) {
		categoria = catService.actualizar(categoria);
		return "redirect:/categoria";
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		catRepo.deleteById(id);
		return "redirect:/categoria";
	}

}
