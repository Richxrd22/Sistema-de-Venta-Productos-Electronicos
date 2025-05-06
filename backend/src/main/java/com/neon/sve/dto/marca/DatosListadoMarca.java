package com.neon.sve.dto.marca;

import com.neon.sve.model.Producto.Marca;

public record DatosListadoMarca(

    Long id_marca,
    String nombre_marca

) {

    public DatosListadoMarca(Marca marca){
        this(
            marca.getId_marca(),
            marca.getNombre_marca());
    }

}
