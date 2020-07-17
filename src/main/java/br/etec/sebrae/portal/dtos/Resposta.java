package br.etec.sebrae.portal.dtos;

import java.io.Serializable;
import java.util.List;

public class Resposta<T> implements Serializable {
	private static final long serialVersionUID = 6872951051020696931L;
	
	private List<T> content;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	
	
}
