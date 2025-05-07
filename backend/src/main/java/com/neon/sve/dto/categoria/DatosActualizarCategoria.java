package com.neon.sve.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarCategoria(

        @NotNull Long id_categoria,
        @NotBlank String nombre_categoria,
        @NotNull Boolean activo

) {

}
