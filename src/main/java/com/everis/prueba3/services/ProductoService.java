package com.everis.prueba3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.everis.prueba3.models.Producto;
import com.everis.prueba3.repositories.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	ProductoRepository prodRepo;
	
	public Producto save(Producto producto) {
		return prodRepo.save(producto);
	}
	
	public List<Producto> findAll(){
		return prodRepo.findAll();
	}
	
	public Page<Producto> productosPaginados(int numeroPagina, int cantElementos){
		PageRequest pageRequest = PageRequest.of(numeroPagina, cantElementos);
		
		return prodRepo.findAll(pageRequest);
	}
	
	public Producto findbyId(Long id) {
		return prodRepo.findById(id).get();
	}

}
