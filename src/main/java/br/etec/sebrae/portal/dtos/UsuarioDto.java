package br.etec.sebrae.portal.dtos;

import java.io.Serializable;

public class UsuarioDto implements Serializable{

	private static final long serialVersionUID = -8722611173963225562L;

	private long id;
	private String email;
	private String senha;
	private String perfil;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	
}
