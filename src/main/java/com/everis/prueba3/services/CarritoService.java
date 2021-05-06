package com.everis.prueba3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.prueba3.models.Carrito;
import com.everis.prueba3.repositories.CarritoRepository;

@Service
public class CarritoService {
	@Autowired
	CarritoRepository carroRepo;
	
	public Carrito save(Carrito carrito) {
		return carroRepo.save(carrito);
	}
	
	public List<Carrito> findAll(){
		return carroRepo.findAll();
	}
}
