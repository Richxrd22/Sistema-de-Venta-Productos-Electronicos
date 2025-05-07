package com.neon.sve.dto.categoria;

import com.neon.sve.model.Producto.Categoria;

public record DatosRespuesCategoria(

        Long id_categoria,
        String nombre_categoria,
        int activo

) {

    public DatosRespuesCategoria(Categoria categoria) {
        this(
                categoria.getId_categoria(),
                categoria.getNombre_categoria(),
                categoria.getActivo() != null && categoria.getActivo() ? 1 : 0);
    }

}
