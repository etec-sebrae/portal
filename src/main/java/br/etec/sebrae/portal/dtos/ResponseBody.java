package br.etec.sebrae.portal.dtos;

import java.util.List;

public class ResponseBody {

	private Data data;
	private List<Errors> errors;
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public List<Errors> getErrors() {
		return errors;
	}
	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}
	
	
}
