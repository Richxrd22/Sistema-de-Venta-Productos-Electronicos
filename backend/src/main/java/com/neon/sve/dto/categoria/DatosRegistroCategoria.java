package com.neon.sve.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCategoria(

        @NotBlank String nombre_categoria,
        @NotNull Boolean activo

) {

}
