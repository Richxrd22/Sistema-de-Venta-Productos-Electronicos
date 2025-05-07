package com.neon.sve.dto.categoria;

import java.util.Locale.Category;

import com.neon.sve.model.Producto.Categoria;

public record DatosListadoCategoria(

        Long id_categoria,
        String nombre_categoria,
        int activo

) {

    public DatosListadoCategoria(Categoria categoria) {
        this(
                categoria.getId_categoria(),
                categoria.getNombre_categoria(),
                categoria.getActivo() != null && categoria.getActivo() ? 1 : 0);
    }

}
