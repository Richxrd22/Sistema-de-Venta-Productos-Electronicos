package com.neon.sve.dto.producto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.neon.sve.model.Producto.Categoria;
import com.neon.sve.model.Producto.Marca;
import com.neon.sve.model.Producto.Producto;
import com.neon.sve.model.Producto.Proveedor;
import com.neon.sve.model.Usuario.Empleado;

public record DatosListadoProducto(

        Long id_producto,
        String nombre,
        String descripcion,
        String SKU,
        Double precio,
        int min_stock,
        int max_stock,
        int stock,
        int garantia_meses,
        int activo,
        Timestamp fecha_creacion,
        Empleado id_empleado,
        Categoria id_categoria,
        Proveedor id_proveedor,
        Marca id_marca

) {

    public DatosListadoProducto(Producto producto) {
        this(
                producto.getId_producto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getSku(),
                producto.getPrecio(),
                producto.getMin_stock(),
                producto.getMax_stock(),
                producto.getStock(),
                producto.getGarantia_meses(),
                producto.getActivo() != null && producto.getActivo() ? 1 : 0,
                producto.getFecha_creacion(),
                producto.getId_empleado(),
                producto.getId_categoria(),
                producto.getId_proveedor(),
                producto.getId_marca());
    }
}
