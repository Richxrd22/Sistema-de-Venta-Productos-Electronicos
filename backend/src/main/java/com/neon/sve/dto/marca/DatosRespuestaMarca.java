package com.neon.sve.dto.marca;

import com.neon.sve.model.Producto.Marca;

import jakarta.validation.constraints.NotNull;

public record DatosRespuestaMarca(

        Long id_marca,
        String nombre_marca

) {

        public DatosRespuestaMarca(Marca marca){
                this(
                        marca.getId_marca(),
                        marca.getNombre_marca());
        }
}
