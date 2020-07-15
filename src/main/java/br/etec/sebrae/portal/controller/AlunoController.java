package br.etec.sebrae.portal.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.etec.sebrae.portal.dtos.AlunosDto;
import br.etec.sebrae.portal.dtos.CursosDto;
import br.etec.sebrae.portal.dtos.PessoaDto;
import br.etec.sebrae.portal.dtos.RetornoLogin;
import br.etec.sebrae.portal.service.VerificaAuth;

import br.etec.sebrae.portal.util.ListCursos;


@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	VerificaAuth auth;
	
	@RequestMapping("/consultar")
	public ModelAndView consultarAluno(ModelMap model, HttpSession session, ModelMapper mapper) {		
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		final String urilistaAlunos = "https://api-seetec.herokuapp.com/api/aluno";

		
		ResponseEntity<PessoaDto[]> response = restTemplate.getForEntity(urilistaAlunos,PessoaDto[].class);
		PessoaDto[] alunos = response.getBody();

		//System.out.println(alunos[0].getUsuario().getEmail());
		
		model.addAttribute("alunos", alunos);
		
		model.addAttribute("conteudo", "/aluno/alunos");
		
		return new ModelAndView("template_painel", model);
	}
	
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrarAluno(ModelMap model, HttpSession session) {		
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		ListCursos cursos = new ListCursos();
		
		model.addAttribute("cursos", cursos.ListCursos()); 
		
		model.addAttribute("conteudo", "/aluno/cadastro_aluno");
		
		return new ModelAndView("template_painel", model);		
	}
	
	 
	@RequestMapping("/cadastro")
	public String cadastroAluno(ModelMap model, AlunosDto aluno) {
		
		Gson gson = new Gson(); 
		String recuperaToken = gson.toJson(aluno);
		
		
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        System.out.println(recuperaToken);
	        return "redirect:/aluno/consultar?msg=success";
	        
		    /*Map<String, Object> map = new HashMap<>();
			//map.put("cursos", aluno.getIdCursos().toString());
		    map.put("nome", aluno.getNome());
		    map.put("rg", aluno.getRg());
		    map.put("cpf", aluno.getCpf());
		    map.put("senha", aluno.getSenha());
		    map.put("email", aluno.getEmail());
		    map.put("data_nasc", "2020-06-25");
		    map.put("matricula", aluno.getMatricula());
		    
		    System.out.println(map);
			RestTemplate template = new RestTemplate();
						
			final String urilistaAlunos = "https://api-seetec.herokuapp.com/api/aluno";
			ResponseEntity<String> response = template.postForEntity(urilistaAlunos, map, String.class);
			int codestatus = response.getStatusCodeValue();
			System.out.println(codestatus);
			
			if (codestatus == 200 || codestatus == 201) {
				return "redirect:/aluno/consultar?msg=success";
			}
			else {
				return "redirect:/aluno/consultar?msg=failure";
			}*/
		}
		catch (Exception e) {
			System.out.println(e);
			return "redirect:/aluno/consultar?msg=failure";
		}
		
	}
	
	@RequestMapping("/alterar")
	public String alterarAluno() {		
		return "aluno/alterar_aluno";		 
	}
	
}
