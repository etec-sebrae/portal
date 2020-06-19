package br.etec.sebrae.portal.dtos;

import java.util.List;

public class Dados {
	
	private List<SolicitacoesDto> solicitacoesDto;

	private List<Errors> erros;

	public List<SolicitacoesDto> getSolicitacoesDto() {
		return solicitacoesDto;
	}

	public void setSolicitacoesDto(List<SolicitacoesDto> solicitacoesDto) {
		this.solicitacoesDto = solicitacoesDto;
	}

	public List<Errors> getErros() {
		return erros;
	}

	public void setErros(List<Errors> erros) {
		this.erros = erros;
	}

		
	
}
