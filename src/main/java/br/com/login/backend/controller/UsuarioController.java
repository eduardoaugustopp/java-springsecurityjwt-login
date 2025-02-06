package br.com.login.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.login.backend.entity.Usuario;
import br.com.login.backend.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
      this.service = service;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> buscaUsuario(@PathVariable String email, @RequestHeader("Authorization") String header) {
        var usuarioRetornado = service.buscaUsuario(email, header);
        return ResponseEntity.ok(usuarioRetornado); 
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> removeUsuario(@PathVariable String email, @RequestHeader("Authorization") String header) {
        service.removeUsuario(email, header);
        return ResponseEntity.ok().build();
    }
}
