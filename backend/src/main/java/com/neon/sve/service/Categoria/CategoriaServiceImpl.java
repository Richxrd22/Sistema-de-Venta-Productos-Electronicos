package com.neon.sve.service.Categoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.categoria.DatosActualizarCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoria;
import com.neon.sve.dto.categoria.DatosRegistroCategoria;
import com.neon.sve.dto.categoria.DatosRespuesCategoria;
import com.neon.sve.model.Producto.Categoria;
import com.neon.sve.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public DatosRespuesCategoria getCategoriaById(Long id_categoria) {
        
        Optional<Categoria> categorOptional = categoriaRepository.findById(id_categoria);
        if (categorOptional.isPresent()) {
            
            Categoria categoria = categorOptional.get();
            return new DatosRespuesCategoria(categoria);

        } else {
            throw new RuntimeException("Categoria no encontrada");

        }
    }

    @Override
    public Page<DatosListadoCategoria> getAllCategoria(Pageable pageable) {
        Page<Categoria> categoriaPage = categoriaRepository.findAll(pageable);
        return categoriaPage.map(DatosListadoCategoria::new);

    }

    @Override
    public DatosRespuesCategoria createCategoria(DatosRegistroCategoria datosRegistroCategoria) {
        Categoria categoria = categoriaRepository.save(new Categoria(datosRegistroCategoria));
        return new DatosRespuesCategoria(categoria);

    }

    @Override
    public DatosRespuesCategoria updateCategoria(DatosActualizarCategoria datosActualizarCategoria) {
        Categoria categoria = categoriaRepository.getReferenceById(datosActualizarCategoria.id_categoria());
        categoria.actualizar(datosActualizarCategoria);
        categoria = categoriaRepository.save(categoria);
        return new DatosRespuesCategoria(categoria);

    }

    @Override
    public void activarCategoria(Long id_categoria) {
        Categoria categoria = categoriaRepository.findById(id_categoria)
        .orElseThrow(()-> new EntityNotFoundException("Categoria no encontrrada con el ID ingresado :"+id_categoria));

        if (!categoria.getActivo()) {
            categoria.setActivo(true);
            categoriaRepository.save(categoria);
        }

    }

    @Override
    public void desactivarCategoria(Long id_categoria) {
        Categoria categoria = categoriaRepository.findById(id_categoria)
        .orElseThrow(()-> new EntityNotFoundException("Categoria no encontrada con el ID ingresado : " + id_categoria));

        if (categoria.getActivo()) {
            categoria.setActivo(false);
            categoriaRepository.save(categoria);
        }

    }
    
}
