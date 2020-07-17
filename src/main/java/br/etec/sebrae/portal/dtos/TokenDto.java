package br.etec.sebrae.portal.dtos;

import java.io.Serializable;

public class TokenDto implements Serializable{
	
	private static final long serialVersionUID = -8402813286411903476L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
