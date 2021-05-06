package com.everis.prueba3.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.prueba3.models.Categoria;
import com.everis.prueba3.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository catRepo;
	
	public Categoria save(Categoria categoria) {
		return catRepo.save(categoria);
	}
	
	public List<Categoria> findAll(){
		return catRepo.findAll();
	}
	
	public Categoria buscarPorId(Long id) {
		return catRepo.findById(id).get();
	}

	public @Valid Categoria actualizar(@Valid Categoria categoria) {
		return catRepo.save(categoria);
	}
}
