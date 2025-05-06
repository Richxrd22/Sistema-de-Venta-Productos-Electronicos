package com.neon.sve.service.marca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.marca.DatosActualizarMarca;
import com.neon.sve.dto.marca.DatosListadoMarca;
import com.neon.sve.dto.marca.DatosRegistroMarca;
import com.neon.sve.dto.marca.DatosRespuestaMarca;
import com.neon.sve.model.Producto.Marca;
import com.neon.sve.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public DatosRespuestaMarca getMarcaById(Long id_marca) {

        Optional<Marca> marcaOptional = marcaRepository.findById(id_marca);
        if (marcaOptional.isPresent()) {
            Marca marca = marcaOptional.get();
            return new DatosRespuestaMarca(marca);

        } else {
            throw new RuntimeException("Marca no encontrada");
        }
    }

    @Override
    public Page<DatosListadoMarca> getAllMarca(Pageable pageable) {

        Page<Marca> marcaPage = marcaRepository.findAll(pageable);
        return marcaPage.map(DatosListadoMarca::new);
    }

    @Override
    public DatosRespuestaMarca createMarca(DatosRegistroMarca datosRegistroMarca) {
        Marca marca = marcaRepository.save(new Marca(datosRegistroMarca));
        return new DatosRespuestaMarca(marca);
    }

    @Override
    public void deleteMarca(Long id_marca) {

        Marca marca = marcaRepository.findById(id_marca).orElse(null);
        if (marca != null) {
            marcaRepository.delete(marca);
        } else {
            throw new IllegalArgumentException("No se encontro la marca con el ID");
        }

    }

    @Override
    public DatosRespuestaMarca updateMarca(DatosActualizarMarca datosActualizarmarca) {
        
        Marca marca = marcaRepository.getReferenceById(datosActualizarmarca.id_marca());
        marca.Actualizar(datosActualizarmarca);
        return new DatosRespuestaMarca(marca);

    }

}
