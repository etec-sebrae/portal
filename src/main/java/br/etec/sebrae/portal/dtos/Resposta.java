package br.etec.sebrae.portal.dtos;

import java.util.List;

public class Resposta<T> {
	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	

}
