package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.Producto.SubCategoria;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long>{
    
}
