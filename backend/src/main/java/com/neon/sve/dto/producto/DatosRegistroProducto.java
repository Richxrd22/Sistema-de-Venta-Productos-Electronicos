package com.neon.sve.dto.producto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroProducto(

        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotBlank String SKU,
        @NotNull Double precio,
        @NotNull int min_stock,
        @NotNull int max_stock,
        @NotNull int stock,
        @NotNull int garantia_meses,
        @NotNull Boolean activo,
        @NotNull Timestamp fecha_creacion,
        @NotNull Long id_empleado,
        @NotNull Long id_categoria,
        @NotNull Long id_proveedor,
        @NotNull Long id_marca

) {

}
