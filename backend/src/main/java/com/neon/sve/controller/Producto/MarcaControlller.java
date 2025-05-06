package com.neon.sve.controller.Producto;

import org.springframework.data.domain.Page;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.neon.sve.dto.marca.DatosActualizarMarca;
import com.neon.sve.dto.marca.DatosListadoMarca;
import com.neon.sve.dto.marca.DatosRegistroMarca;
import com.neon.sve.dto.marca.DatosRespuestaMarca;
import com.neon.sve.service.marca.MarcaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/marca")
public class MarcaControlller {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoMarca>> listarMarca(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {

        Page<DatosListadoMarca> marcaPage = marcaService.getAllMarca(paginacion);
        return ResponseEntity.ok(marcaPage);
    }

    @GetMapping("/buscar/{id_marca}")
    public ResponseEntity<?> buscarMarca(@PathVariable Long id_marca) {

        try {
            DatosRespuestaMarca marca = marcaService.getMarcaById(id_marca);
            return ResponseEntity.ok(marca);

        } catch (Exception e) {

            String mensajeError = "Error al obtener la marca con el ID" + id_marca;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }

    }

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DatosRespuestaMarca> registrarMarca(
            @Valid @RequestBody DatosRegistroMarca datosRegistroMarca,
            UriComponentsBuilder uriComponentsBuilder) {

        DatosRespuestaMarca datosRespuestaMarca = marcaService.createMarca(datosRegistroMarca);
        URI url = uriComponentsBuilder.path("/buscar/{id_marca}")
                .buildAndExpand(datosRespuestaMarca.id_marca())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaMarca);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaMarca> actualizarMarca(
            @Valid @RequestBody DatosActualizarMarca actualizarMarca) {
        DatosRespuestaMarca datosRespuestaMarca = marcaService.updateMarca(actualizarMarca);
        return ResponseEntity.ok(datosRespuestaMarca);
    }

    @DeleteMapping("/eliminar/{id_marca}")
    public ResponseEntity<?> eliminarMarca(@PathVariable Long id_marca) {
        try {
            marcaService.deleteMarca(id_marca);
            return ResponseEntity.ok("Eliminacion exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
