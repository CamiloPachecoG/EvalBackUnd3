package com.everis.prueba3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.prueba3.models.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

}
