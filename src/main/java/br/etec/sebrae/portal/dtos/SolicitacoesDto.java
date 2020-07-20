package br.etec.sebrae.portal.dtos;

import java.io.Serializable;
import java.util.Date;

public class SolicitacoesDto implements Serializable{	

	private static final long serialVersionUID = -6541520440241118272L;
	
	private long id;
	private int status;
	private Date data_abertura;
	private Date data_conclusao;
	private PessoaDto aluno;
	private CursosDto curso;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getData_abertura() {
		return data_abertura;
	}
	public void setData_abertura(Date data_abertura) {
		this.data_abertura = data_abertura;
	}
	public Date getData_conclusao() {
		return data_conclusao;
	}
	public void setData_conclusao(Date data_conclusao) {
		this.data_conclusao = data_conclusao;
	}
	public PessoaDto getAluno() {
		return aluno;
	}
	public void setAluno(PessoaDto aluno) {
		this.aluno = aluno;
	}
	public CursosDto getCurso() {
		return curso;
	}
	public void setCurso(CursosDto curso) {
		this.curso = curso;
	}
	

}
