package br.etec.sebrae.portal.dtos;

import java.io.Serializable;

public class SlDocumentosDto  implements Serializable{
	
	private static final long serialVersionUID = 7450470118472927433L;

	private long idRelatorio;
	private String imagem;
	private int status;
	private String descricao;
	private PessoaDto aluno;
	
	
	public long getIdRelatorio() {
		return idRelatorio;
	}
	public void setIdRelatorio(long idRelatorio) {
		this.idRelatorio = idRelatorio;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public PessoaDto getAluno() {
		return aluno;
	}
	public void setAluno(PessoaDto aluno) {
		this.aluno = aluno;
	}
	
	
}
