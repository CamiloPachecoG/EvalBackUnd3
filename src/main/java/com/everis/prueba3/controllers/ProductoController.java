package com.everis.prueba3.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.prueba3.models.Carrito;
import com.everis.prueba3.models.Categoria;
import com.everis.prueba3.models.Producto;
import com.everis.prueba3.repositories.CarritoRepository;
import com.everis.prueba3.repositories.ProductoRepository;
import com.everis.prueba3.services.CarritoService;
import com.everis.prueba3.services.CategoriaService;
import com.everis.prueba3.services.ProductoService;



@Controller
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	ProductoService prodService;
	@Autowired
	ProductoRepository prodRepo;
	@Autowired
	CategoriaService catService;
	@Autowired
	CarritoService carroService;
	@Autowired
	CarritoRepository carroRepo;
	
	private static final int CANT_PRODUCTOS = 3;
	
	@RequestMapping("")
	public String inicio(HttpSession session, Model model) {
		
		Integer registrado = (Integer) session.getAttribute("registrado");
		if(registrado == 1) {
			model.addAttribute("listaCategorias", catService.findAll());
			
			Page<Producto> productos = prodService.productosPaginados(0, CANT_PRODUCTOS);
			
			int totalPagina = productos.getTotalPages();
			model.addAttribute("totalPagina", totalPagina);
			model.addAttribute("productos", productos);
			
			return "producto.jsp";	
		}
		
		return "login.jsp";
	}
	
	@RequestMapping("/busqueda")
	public String buscProducto(HttpSession session) {
		Integer registrado = (Integer) session.getAttribute("registrado");
		
		if(registrado == 1) {
			return "busqproducto.jsp";
		}
		
		return "login.jsp";
	}
	
	@RequestMapping("/busquedaCat")
	public String buscProductoCat(HttpSession session) {
		Integer registrado = (Integer) session.getAttribute("registrado");
		
		if(registrado == 1) {
			return "busqcategoria.jsp";
		}
		
		return "login.jsp";
	}
	
	@RequestMapping("/carrito")
	public String carro(HttpSession session, Model model) {
		
		Integer registrado = (Integer) session.getAttribute("registrado");
		
		if(registrado == 1) {
			
			List<Carrito> prodCarrito = carroService.findAll();
			model.addAttribute("listaProductosCarro", prodCarrito);
			
			float totalProd = 0;
			
			for(Carrito prod : prodCarrito) {
				totalProd = totalProd + prod.getPrecio();
			}
			
			model.addAttribute("totalProd", totalProd);
			
			return "carrito.jsp";
		}
		
		return "login.jsp";
	}
	
	@RequestMapping("/insertar")
	public String insertar(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("precio") String precio,
			@RequestParam("cantidad") String cantidad,
			@RequestParam("categoria") Categoria categoria,
			Model model) {
		
		int validaciones = 0;
		
		if(nombre.length() > 0 && descripcion.length() > 0 && precio.length() > 0 && cantidad.length() > 0) {
			validaciones++;
		}
		
		if(validaciones == 1) {
			Producto producto = new Producto();
			
			producto.setNombre(nombre);
			producto.setDescripcion(descripcion);
			producto.setPrecio(Float.parseFloat(precio));
			producto.setCantidad(Integer.parseInt(cantidad));
			producto.setCategoria(categoria);
			
			producto = prodService.save(producto);
			
			return "redirect:";
		}else {
			model.addAttribute("mensaje", "Todos los campos son requeridos!");
			return "redirect:/producto";
		}
		
	}
	
	@RequestMapping("/busqueda/prod")
	public String resulBusq(@RequestParam("nombre") String producto, Model model) {
		
		List<Producto> listaProductos = prodService.findAll();
		List<Producto> listaBusqueda = new ArrayList<>();
		
		for(Producto prod : listaProductos) {
			if(prod.getNombre().toLowerCase().equals(producto)) {
				listaBusqueda.add(prod);
			}
		}
		
		if(listaBusqueda != null) {
			model.addAttribute("listaBusqueda", listaBusqueda);
		}
		
		return "busqproducto.jsp";
	}
	
	@RequestMapping("/busqueda/cat")
	public String resulBusqCat(@RequestParam("nombre") String categoria, Model model) {
		
		List<Producto> listaProductos = prodService.findAll();
		List<Producto> listaBusqueda = new ArrayList<>();
		
		for(Producto cat : listaProductos) {
			if(cat.getCategoria().getNombre().toLowerCase().equals(categoria)) {
				listaBusqueda.add(cat);
			}
		}
		
		if(listaBusqueda != null) {
			model.addAttribute("listaBusqueda", listaBusqueda);
		}
		
		return "busqcategoria.jsp";
	}
	
	@RequestMapping("/paginacion/{numeroPagina}")
	public String getProductosPagina(@PathVariable("numeroPagina") int numeroPagina,
			Model model) {
		
		Page<Producto> productos = prodService.productosPaginados(numeroPagina-1, CANT_PRODUCTOS);
		
		int totalPagina = productos.getTotalPages();
		
		model.addAttribute("totalPagina", totalPagina);
		model.addAttribute("productos", productos);
		model.addAttribute("listaCategorias", catService.findAll());
		
		return "producto.jsp";
				
	}
	
	@RequestMapping("/agregar/{id}")
	public String agregar(@PathVariable("id") Long id) {
		
		Producto producto = prodService.findbyId(id);
		Carrito carritoProd = new Carrito();
		
		carritoProd.setNombre(producto.getNombre());
		carritoProd.setPrecio(producto.getPrecio());
		
		carroService.save(carritoProd);
		
		return "redirect:/producto/carrito";
		
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		prodRepo.deleteById(id);
		return "redirect:/producto";
	}
	
	@RequestMapping("/carro/eliminar/{id}")
	public String eliminarProd(@PathVariable("id") Long id) {
		carroRepo.deleteById(id);
		return "redirect:/producto/carrito";
	}
	
	@RequestMapping("/pagar")
	public String pagar() {
		carroRepo.deleteAll();
		return "redirect:/producto/carrito";
	}
}
