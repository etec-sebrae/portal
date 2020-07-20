package br.etec.sebrae.portal.dtos;

import java.io.Serializable;

public class AutenticacaoDto  implements Serializable{

	private static final long serialVersionUID = -8102035465700656404L;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
