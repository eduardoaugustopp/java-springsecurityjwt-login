package br.com.login.backend.service;

import java.security.Key;
import java.time.Instant;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.login.backend.dto.TokenDeUsuario;
import br.com.login.backend.dto.UsuarioDeLogin;
import br.com.login.backend.entity.Usuario;
import br.com.login.backend.exception.LoginInvalidoException;
import br.com.login.backend.repository.UsuarioRepository;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JWTService {

  public static final Key TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
  private final UsuarioRepository repository;

  public JWTService(UsuarioRepository repository) {
    this.repository = repository;
  }

  public TokenDeUsuario autenticarUsuario(UsuarioDeLogin usuarioDeLogin) {
    Optional<Usuario> usuarioRetornado = repository.findByEmailAndSenha(usuarioDeLogin.email(), usuarioDeLogin.senha());

    if (usuarioRetornado.isEmpty()) {
      throw new LoginInvalidoException("Login inválido");
    }

    String token = geraToken(usuarioDeLogin.email());
    return new TokenDeUsuario(token);
  }

  private String geraToken(String email) {
    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setSubject(email)
        .signWith(TOKEN_KEY, SignatureAlgorithm.HS512)
        .setExpiration(tempoDoToken())
        .compact();
  }

  private Date tempoDoToken() {
    return Date.from(Instant.now().plusSeconds(60 * 5));
  }

  public String pegaSujeitoPeloToken(String header) {
    if (header == null || !header.startsWith("Bearer ")) {
      throw new SecurityException("Token inválido");
    }
    String token = header.substring(7);

    String sujeito;
    try {
      JwtParser parser = Jwts.parserBuilder().setSigningKey(TOKEN_KEY).build();
      sujeito = parser.parseClaimsJws(token).getBody().getSubject();
    } catch (SignatureException e) {
      throw new SecurityException("Erro na validação do token");
    }
    return sujeito;
  }

}
