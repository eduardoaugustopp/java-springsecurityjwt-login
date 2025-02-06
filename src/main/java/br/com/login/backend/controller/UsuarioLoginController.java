package br.com.login.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.login.backend.dto.TokenDeUsuario;
import br.com.login.backend.dto.UsuarioDeLogin;
import br.com.login.backend.dto.UsuarioDeRegistro;
import br.com.login.backend.entity.Usuario;
import br.com.login.backend.service.JWTService;
import br.com.login.backend.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioLoginController {

    private final UsuarioService usuarioService;
    private final JWTService jwtService;


    public UsuarioLoginController(UsuarioService usuarioService, JWTService jwtService) {
      this.usuarioService = usuarioService;
      this.jwtService = jwtService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody UsuarioDeRegistro usuarioDeRegistro) {
        var usuarioParaSalvar = usuarioService.cadastraUsuario(usuarioDeRegistro);
        return ResponseEntity.ok(usuarioParaSalvar);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDeUsuario> autenticaUsuario(@RequestBody UsuarioDeLogin usuarioDeLogin) {
        var tokenDeUsuario = jwtService.autenticarUsuario(usuarioDeLogin);
        return ResponseEntity.ok(tokenDeUsuario);
    }
}
