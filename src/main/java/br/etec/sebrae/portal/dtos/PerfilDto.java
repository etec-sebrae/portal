package br.etec.sebrae.portal.dtos;

import java.io.Serializable;

public class PerfilDto implements Serializable{

	private static final long serialVersionUID = 3275487005189730045L;
	
	private String token;
	private PessoaDto pessoa;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public PessoaDto getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaDto pessoa) {
		this.pessoa = pessoa;
	}

}
