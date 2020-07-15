package br.etec.sebrae.portal.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.etec.sebrae.portal.dtos.CursosDto;


public class ListCursos {
	
	@Autowired
	CursosDto[] cursos;
	
	public CursosDto[] ListCursos() {
		RestTemplate restTemplate = new RestTemplate();
		final String urilistaAlunos = "https://api-seetec.herokuapp.com/api/curso";

		ResponseEntity<CursosDto[]> response = restTemplate.getForEntity(urilistaAlunos,CursosDto[].class);
		cursos = response.getBody();
		return cursos;
	}
	
}
