package br.com.login.backend.exception;

public class LoginInvalidoException extends RuntimeException {
  public LoginInvalidoException(String mensagem) {
    super(mensagem);
}
}