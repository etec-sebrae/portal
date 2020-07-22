package br.etec.sebrae.portal.dtos;

import java.io.Serializable;

public class MudancaStatusDto implements Serializable{

	private static final long serialVersionUID = -6431197360810092971L;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
