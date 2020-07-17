package br.etec.sebrae.portal.dtos;

import java.io.Serializable;

public class DocumentosDto implements Serializable{

	private static final long serialVersionUID = 4749794979621804510L;
	
	private long id;
	private String nome;
	private String descricao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
