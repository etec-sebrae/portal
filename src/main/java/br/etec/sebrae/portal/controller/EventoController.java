package br.etec.sebrae.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/evento")
public class EventoController {
	
	@RequestMapping("/cadastrar")
	public String cadastroAluno() {		
		return "evento/cadastro_evento";		
	}
	
	@RequestMapping("/alterar")
	public String alterarAluno() {		
		return "evento/alterar_evento";		
	}
	
	@RequestMapping("/excluir")
	public String excluirAluno() {		
		return "evento/excluir_evento";		
	}

}
