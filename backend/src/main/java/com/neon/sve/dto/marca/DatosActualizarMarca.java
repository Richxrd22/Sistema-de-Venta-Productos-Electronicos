package com.neon.sve.dto.marca;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarMarca(

        @NotNull Long id_marca,
        @NotNull String nombre_marca

) {

}
