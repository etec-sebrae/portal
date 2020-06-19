package br.etec.sebrae.portal.dtos;

import java.io.Serializable;
import java.util.Date;

public class SolicitacoesDto implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6541520440241118272L;
	
	
	
	private long id;
	private int status;
	private Date data_solicitacao ;
	private String documento;
	private String aluno;
	
	
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
	public Date getData_solicitacao() {
		return data_solicitacao;
	}
	public void setData_solicitacao(Date data_solicitacao) {
		this.data_solicitacao = data_solicitacao;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	
}
