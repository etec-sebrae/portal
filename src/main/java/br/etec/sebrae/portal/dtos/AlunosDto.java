package br.etec.sebrae.portal.dtos;

import java.io.Serializable;
import java.util.Date;

public class AlunosDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 787241975083815575L;
	
	private long id;
	private String nome;
    private String rg;
    private String cpf;
    private String email;
    private String data_nasc;
    private String curso;
    private String senha;
	
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
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
	
}
