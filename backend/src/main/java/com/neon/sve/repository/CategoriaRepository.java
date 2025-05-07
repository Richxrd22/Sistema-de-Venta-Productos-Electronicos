package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.Producto.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
