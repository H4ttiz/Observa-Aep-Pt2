package com.unicesumar.observa_aep.controller;

import com.unicesumar.observa_aep.dto.login.LoginRequestDTO;
import com.unicesumar.observa_aep.dto.login.LoginResponseDTO;
import com.unicesumar.observa_aep.dto.usuario.CidadaoRequestDTO;
import com.unicesumar.observa_aep.dto.usuario.UsuarioResponseDTO;
import com.unicesumar.observa_aep.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@Valid @RequestBody CidadaoRequestDTO cidadaoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(cidadaoRequestDTO));
    }
}