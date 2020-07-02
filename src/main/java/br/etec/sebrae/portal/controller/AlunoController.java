package br.etec.sebrae.portal.controller;

import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
	public ModelAndView cadastrarAluno(ModelMap model) {		
		
		model.addAttribute("conteudo", "/aluno/cadastro_aluno");
		
		return new ModelAndView("template_painel", model);		
	}
	
	 
	@RequestMapping("/cadastro")
	public String cadastroAluno(ModelMap model, AlunosDto aluno) {
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
	       
	    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		
	    map.add("nome", aluno.getNome());
	    map.add("rg", aluno.getRg());
	    map.add("cpf", aluno.getCpf());
	    map.add("email", aluno.getEmail());
	    map.add("data_nasc", aluno.getData_nasc().toString());
	    map.add("curso", aluno.getCurso());
		
	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
	    
		RestTemplate template = new RestTemplate();
		
		final String urilistaAlunos = "http://seetecc.herokuapp.com/api/aluno";
		
		Resposta<AlunosDto> result = template.postForObject(urilistaAlunos, request, Resposta.class);
		
		return "aluno/cadastrar";		
	}
	
	@RequestMapping("/alterar")
	public String alterarAluno() {		
		return "aluno/alterar_aluno";		 
	}
	
}
