package br.etec.sebrae.portal.dtos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EventosDto implements Serializable{

	private static final long serialVersionUID = -2602376134651152456L;
	
	private long id;
	private String nome;
	private String descricao;
	private int status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDataInicio() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		String data_string = this.dataInicio.toString();
		String separados[] = data_string.split(",");
		data_string = separados[0];
		Date dataFormatada = formato.parse(data_string); 
		return dataFormatada;
	}
	public void setDataInicio(Date dataInicio) throws ParseException {
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		String data_string = this.dataInicio.toString();
		String separados[] = data_string.split(",");
		data_string = separados[0];
		Date dataFormatada = formato.parse(data_string); 
		this.dataInicio = dataFormatada;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
		
}
