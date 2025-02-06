package br.com.login.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import br.com.login.backend.dto.UsuarioDeRegistro;

@NoArgsConstructor
@Data

@Entity(name = "tb_usuario")
public class Usuario implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private String senha;

  public Usuario(String email, String senha, String nome) {

    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }

  public Usuario (UsuarioDeRegistro usuarioDeRegistro) {
    this.nome = usuarioDeRegistro.nome();
    this.email = usuarioDeRegistro.email();
    this.senha = usuarioDeRegistro.senha();
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Usuario other = (Usuario) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    return result;
  }

}
