package com.neon.sve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.producto.SubCategoria;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long>{
    Optional<SubCategoria> findByNombre(String nombre);
}
