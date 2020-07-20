package br.etec.sebrae.portal.dtos;

import java.io.Serializable;

public class LoginDto implements Serializable {

	private static final long serialVersionUID = 4348405788494591028L;

	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password; 
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
