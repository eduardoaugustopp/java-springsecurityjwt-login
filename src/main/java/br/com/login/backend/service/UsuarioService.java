package br.com.login.backend.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.login.backend.dto.UsuarioDeRegistro;
import br.com.login.backend.entity.Usuario;
import br.com.login.backend.exception.UsuarioJaExisteException;
import br.com.login.backend.exception.UsuarioNaoExisteException;
import br.com.login.backend.exception.UsuarioNaoTemPermissaoException;
import br.com.login.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {
  private final UsuarioRepository repository;

  private final JWTService service;

  public UsuarioService(UsuarioRepository repository, JWTService service) {
    this.repository = repository;
    this.service = service;
  }

  public Usuario cadastraUsuario(UsuarioDeRegistro usuarioDeRegistro) {
    Optional<Usuario> usuarioRetornado = repository.findByEmail(usuarioDeRegistro.email());

    if (usuarioRetornado.isPresent()) {
      throw new UsuarioJaExisteException("Usuário já existe");
    }

    Usuario usuarioParaSalvar = new Usuario(usuarioDeRegistro);
    repository.save(usuarioParaSalvar);

    return usuarioParaSalvar;
  }

  private boolean usuarioTemPermissao(String header, String email) {
    String sujeitoDoToken = service.pegaSujeitoPeloToken(header);
    Optional<Usuario> usuarioRetornado = repository.findByEmail(sujeitoDoToken);
    return usuarioRetornado.isPresent() && usuarioRetornado.get().getEmail().equals(email);
  }

  public Usuario buscaUsuario(String email, String header) {
    Optional<Usuario> usuarioRetornado = repository.findByEmail(email);
    if (usuarioRetornado.isEmpty()) {
      throw new UsuarioNaoExisteException("Usuário não existe!");
    }
    if (!usuarioTemPermissao(header, email)) {
      throw new UsuarioNaoTemPermissaoException("O Usuario nao tem permissão");
    }
    return usuarioRetornado.get();
  }

  public void removeUsuario(String email, String header) {
    Optional<Usuario> usuarioParaRemover = repository.findByEmail(email);
    if (usuarioParaRemover.isEmpty()) {
      throw new UsuarioNaoExisteException("Usuário não existe!");
    }
    if (!usuarioTemPermissao(header, email)) {
      throw new UsuarioNaoTemPermissaoException("O Usuario não tem permissão");
    }
    repository.delete(usuarioParaRemover.get());
  }
}