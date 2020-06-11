package br.etec.sebrae.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@RequestMapping("/cadastrar")
	public String cadastroAluno() {		
		return "aluno/cadastro_aluno";		
	}
	
	@RequestMapping("/alterar")
	public String alterarAluno() {		
		return "aluno/alterar_aluno";		
	}
	
	@RequestMapping("/excluir")
	public String excluirAluno() {		
		return "aluno/excluir_aluno";		
	}

}
