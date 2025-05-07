package com.neon.sve.controller.Producto;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.neon.sve.dto.categoria.DatosActualizarCategoria;
import com.neon.sve.dto.categoria.DatosListadoCategoria;
import com.neon.sve.dto.categoria.DatosRegistroCategoria;
import com.neon.sve.dto.categoria.DatosRespuesCategoria;
import com.neon.sve.service.Categoria.CategoriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoCategoria>> listarCategoria(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<DatosListadoCategoria> categoraPage = categoriaService.getAllCategoria(paginacion);
        return ResponseEntity.ok(categoraPage);
    }

    @GetMapping("/buscar/{id_categoria}")
    public ResponseEntity<?> buscarCategoria(@PathVariable Long id_categoria) {
        try {
            DatosRespuesCategoria categoria = categoriaService.getCategoriaById(id_categoria);
            return ResponseEntity.ok(categoria);

        } catch (Exception e) {
            String mensajeError = "Error al obtener el ID de la categoria : " + id_categoria;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuesCategoria> registrarCategoria(
            @Valid @RequestBody DatosRegistroCategoria datosRegistroCategoria,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuesCategoria datosRespuesCategoria = categoriaService.createCategoria(datosRegistroCategoria);
        URI url = uriComponentsBuilder.path("/buscar/{id_categoria}")
                .buildAndExpand(datosRespuesCategoria.id_categoria())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuesCategoria);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuesCategoria> actualizarCategoria(
            @Valid @RequestBody DatosActualizarCategoria actualizarCategoria) {
        DatosRespuesCategoria datosRespuesCategoria = categoriaService.updateCategoria(actualizarCategoria);
        return ResponseEntity.ok(datosRespuesCategoria);
    }

    @PutMapping("/activar/{id_categoria}")
    public ResponseEntity<String> activarCategoria(@PathVariable Long id_categoria) {
        categoriaService.activarCategoria(id_categoria);
        return ResponseEntity.ok("Categoria activada correctamente");
    }

    @PutMapping("/desactivar/{id_categoria}")
    public ResponseEntity<String> desactivarCategoria(@PathVariable Long id_categoria) {
        categoriaService.desactivarCategoria(id_categoria);
        return ResponseEntity.ok("Categoria desactivada correctamente");

    }

}
