package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neon.sve.model.producto.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
    
}
