package br.etec.sebrae.portal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.etec.sebrae.portal.dtos.PessoaDto;

@Service
public class ListaAlunos {
	public PessoaDto ListaAluno (int id) {
		RestTemplate restTemplate = new RestTemplate();
		final String urilistaAlunos = "https://api-seetec.herokuapp.com/api/aluno/" + Integer.toString(id);
		ResponseEntity<PessoaDto> response = restTemplate.getForEntity(urilistaAlunos, PessoaDto.class);
		PessoaDto aluno = response.getBody();
		return aluno;
	}
}
