package br.etec.sebrae.portal.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AlunosDto implements Serializable {
	
	private static final long serialVersionUID = 787241975083815575L;
	
	private long id;
	private String nome;
    private String rg;
    private String cpf;
    private String email;
    private String data_nasc;
    private String senha;
    private String matricula;
    private List<CursosDto> cursos;
    private String select_cursos;
    private String[] idCursos;
 
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
		
	
	public List<CursosDto> getCursos() {
		return cursos;
	}
	public void setCursos(List<CursosDto> cursos) {
		this.cursos = cursos;
	}
	public String  getSelect_cursos() {
		return select_cursos;
	}
	public void setSelect_cursos(String select_cursos) {
		
		/*String array[] = select_cursos.split(",");
		
		for (int i=0; i <array.length; i++ ) {
			curso[i].setId(Long.parseLong(array[i]));
		}*/
		
		this.select_cursos = select_cursos;
	}
	
}
