package com.neon.sve.service.marca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.marca.DatosActualizarMarca;
import com.neon.sve.dto.marca.DatosListadoMarca;
import com.neon.sve.dto.marca.DatosRegistroMarca;
import com.neon.sve.dto.marca.DatosRespuestaMarca;

public interface MarcaService {

    DatosRespuestaMarca getMarcaById(Long id_marca);

    Page<DatosListadoMarca> getAllMarca (Pageable pageable);

    DatosRespuestaMarca createMarca(DatosRegistroMarca datosRegistroMarca);

    DatosRespuestaMarca updateMarca(DatosActualizarMarca datosActualizarmarca);

    void deleteMarca(Long id_marca);
    
}
