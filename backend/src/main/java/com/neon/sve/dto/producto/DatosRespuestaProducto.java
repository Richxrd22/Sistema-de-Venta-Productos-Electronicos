package com.neon.sve.dto.producto;

import java.sql.Timestamp;

import com.neon.sve.model.Producto.Producto;

public record DatosRespuestaProducto(

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
        String nombre_empleado,
        String nombre_categoria,
        String nombre_proveedor,
        String nombre_marca

) {

    public DatosRespuestaProducto(Producto producto) {

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
                producto.getId_empleado().getNombre()+" "+producto.getId_empleado().getApellido(),
                producto.getId_categoria().getNombre_categoria(),
                producto.getId_proveedor().getNombre() + " " + producto.getId_proveedor().getApellido(),
                producto.getId_marca().getNombre_marca());

    }

}
