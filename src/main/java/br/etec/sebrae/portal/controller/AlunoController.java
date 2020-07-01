package br.etec.sebrae.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.etec.sebrae.portal.dtos.AlunosDto;
import br.etec.sebrae.portal.dtos.Resposta;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@RequestMapping("/consultar")
	public ModelAndView consultarAluno(ModelMap model) {		
		
		
		RestTemplate template = new RestTemplate();
		
		final String urilistaAlunos = "http://seetecc.herokuapp.com/api/aluno";
		
		Resposta<AlunosDto> result = template.getForObject(urilistaAlunos, Resposta.class);		
		
		model.addAttribute("alunos", result.getData());
		
		model.addAttribute("conteudo", "/aluno/alunos");
		
		return new ModelAndView("template_painel", model);
	}
	
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
