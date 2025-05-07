package com.neon.sve.service.producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.producto.DatosActualizarProducto;
import com.neon.sve.dto.producto.DatosListadoProducto;
import com.neon.sve.dto.producto.DatosRegistroProducto;
import com.neon.sve.dto.producto.DatosRespuestaProducto;

public interface ProductoService {

    DatosRespuestaProducto getProductoById(Long id_producto);

    Page<DatosListadoProducto> getAllProducto(Pageable pageable);

    DatosRespuestaProducto createProducto(DatosRegistroProducto datosRegistroProducto);

    DatosRespuestaProducto updateProducto(DatosActualizarProducto datosActualizarProducto);

    void activarProducto(Long id_producto);

    void desactivarProducto(Long id_producto);

}
