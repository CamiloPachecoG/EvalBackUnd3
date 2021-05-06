package com.everis.prueba3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.prueba3.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
