package com.neon.sve.dto.rol;

import jakarta.validation.constraints.NotBlank;


public record DatosRegistroRol(
        @NotBlank String nombre) {
}