package com.neon.sve.service.Categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.categoria.DatosActualizarCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoria;
import com.neon.sve.dto.categoria.DatosRegistroCategoria;
import com.neon.sve.dto.categoria.DatosRespuesCategoria;

public interface CategoriaService {

    DatosRespuesCategoria getCategoriaById(Long id_categoria);

    Page<DatosListadoCategoria> getAllCategoria(Pageable pageable);

    DatosRespuesCategoria createCategoria(DatosRegistroCategoria datosRegistroCategoria);

    DatosRespuesCategoria updateCategoria(DatosActualizarCategoria datosActualizarCategoria);

    void activarCategoria(Long id_categoria);

    void desactivarCategoria(Long id_categoria);

}
