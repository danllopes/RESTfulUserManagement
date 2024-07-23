package br.com.danllopes.usermanagment.controllers;

import br.com.danllopes.usermanagment.domain.entities.Users;
import br.com.danllopes.usermanagment.dtos.AuthenticationDTO;
import br.com.danllopes.usermanagment.dtos.LoginResponseDTO;
import br.com.danllopes.usermanagment.exceptions.InvalidCredentialsException;
import br.com.danllopes.usermanagment.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager manager, TokenService service) {
        this.authenticationManager = manager;
        this.tokenService = service;
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Autentica o usuário com as credenciais fornecidas e gera um token JWT.", tags = "Autenticação")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Users) auth.getPrincipal());
            return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException("Invalid credentials. Check your login and password.");
        }
    }
}