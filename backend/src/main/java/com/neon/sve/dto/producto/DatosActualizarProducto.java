package com.neon.sve.dto.producto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarProducto(

        @NotNull Long id_producto,
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotBlank String SKU,
        @NotBlank Double precio,
        @NotBlank int min_stock,
        @NotBlank int max_stock,
        @NotBlank int stock,
        @NotBlank int garantia_meses,
        @NotNull Boolean activo,
        @NotBlank Timestamp fecha_creacion,
        @NotNull Long id_empleado,
        @NotNull Long id_categoria,
        @NotNull Long id_proveedor,
        @NotNull Long id_marca

) {

}
