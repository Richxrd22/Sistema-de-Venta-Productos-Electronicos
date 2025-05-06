package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neon.sve.model.Producto.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{
    
}
