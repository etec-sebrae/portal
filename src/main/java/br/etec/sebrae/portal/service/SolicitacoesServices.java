package br.etec.sebrae.portal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.etec.sebrae.portal.dtos.Resposta;
import br.etec.sebrae.portal.dtos.SolicitacoesDto;

@Service
public class SolicitacoesServices {
	
	public List<SolicitacoesDto> ListaSolicitacoesAluno (int id) {
		
		RestTemplate restTemplate = new RestTemplate();
		final String urilistaSolicitacoes = "https://api-seetec.herokuapp.com/api/solicitacoes/" + Integer.toString(id);
		
		@SuppressWarnings("unchecked")
		List<SolicitacoesDto> result = restTemplate.getForObject(urilistaSolicitacoes, List.class);
		
		return result;
	}
} 