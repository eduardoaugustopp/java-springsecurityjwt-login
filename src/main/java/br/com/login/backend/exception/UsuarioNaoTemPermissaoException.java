package br.com.login.backend.exception;

public class UsuarioNaoTemPermissaoException extends RuntimeException {
  public UsuarioNaoTemPermissaoException(String mensagem) {
    super(mensagem);
  }
}