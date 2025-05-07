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

import com.neon.sve.dto.producto.DatosActualizarProducto;
import com.neon.sve.dto.producto.DatosListadoProducto;
import com.neon.sve.dto.producto.DatosRegistroProducto;
import com.neon.sve.dto.producto.DatosRespuestaProducto;
import com.neon.sve.service.producto.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoProducto>> listarProducto(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<DatosListadoProducto> productoPage = productoService.getAllProducto(paginacion);
        return ResponseEntity.ok(productoPage);
    }

    @GetMapping("/buscar/{id_producto}")
    public ResponseEntity<?> buscarProducto(@PathVariable Long id_producto) {
        try {
            DatosRespuestaProducto producto = productoService.getProductoById(id_producto);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            String mensajeError = "Error al obtener el producto requerido, verifique ID ingresado : " + id_producto;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaProducto> registroProducto(
            @Valid @RequestBody DatosRegistroProducto datosRegistroProducto,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaProducto datosRespuestaProducto = productoService.createProducto(datosRegistroProducto);
        URI url = uriComponentsBuilder.path("/buscar/{id_producto}")
                .buildAndExpand(datosRespuestaProducto.id_producto())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaProducto);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaProducto> actualizarProducto(
            @Valid @RequestBody DatosActualizarProducto datosActualizarProducto) {
        DatosRespuestaProducto datosRespuestaProducto = productoService.updateProducto(datosActualizarProducto);
        return ResponseEntity.ok(datosRespuestaProducto);
    }

    @PutMapping("/activar/{id_producto}")
    public ResponseEntity<String> activarProducto(@PathVariable Long id_producto) {
        productoService.activarProducto(id_producto);
        return ResponseEntity.ok("Producto activado exitosamente");
    }

    @PutMapping("/desactivar/{id_producto}")
    public ResponseEntity<String> desactivarProducto(@PathVariable Long id_producto) {
        productoService.desactivarProducto(id_producto);
        return ResponseEntity.ok("Producto desactivado exitosamente");
    }

}
