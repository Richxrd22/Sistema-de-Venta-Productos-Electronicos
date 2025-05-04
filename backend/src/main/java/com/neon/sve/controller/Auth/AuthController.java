package com.neon.sve.controller.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neon.sve.dto.login.DatosLoginUsuario;
import com.neon.sve.dto.login.DatosRespuestaLoginUsuario;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;
import com.neon.sve.jwt.JwtService;
import com.neon.sve.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autenticacion")
@RequiredArgsConstructor
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<DatosRespuestaLoginUsuario> login(@RequestBody DatosLoginUsuario request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> register(@RequestBody DatosRegistroUsuarioEmpleado request) {
        try {
            DatosRespuestaLoginUsuario respuesta = authService.register(request);
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping("/validar-token")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no proporcionado");
        }

        try {
            String jwtToken = token.substring(7); 

            String correo = jwtService.getCorreoFromToken(jwtToken);

            if (correo == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(correo);

            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                return ResponseEntity.ok("Token válido");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

}
