package com.neon.sve.dto.marca;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroMarca(

        @NotBlank String nombre_marca

) {

}
