package com.neon.sve.dto.login;

public record DatosRespuestaLoginUsuario(
    String token,
    Boolean clave_cambiada
) {
}
