package br.etec.sebrae.portal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.etec.sebrae.portal.dtos.CursosDto;
import br.etec.sebrae.portal.dtos.DocumentosDto;
import br.etec.sebrae.portal.dtos.PessoaDto;
import br.etec.sebrae.portal.service.VerificaAuth;

@Controller
public class AdministracaoController {
	
	@Autowired
	VerificaAuth auth;
	
	@RequestMapping("/administracao/operadores")
	public ModelAndView Operador(ModelMap model, HttpSession session) {
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		final String urilistaAlunos = "https://api-seetec.herokuapp.com/api/operador";

		
		ResponseEntity<PessoaDto[]> response = restTemplate.getForEntity(urilistaAlunos,PessoaDto[].class);
		PessoaDto[] operadores = response.getBody();

		
		model.addAttribute("operadores", operadores);
		
		model.addAttribute("conteudo", "administracao/operadores/listar");
		return new ModelAndView("template_painel", model);		
	}
	
	@RequestMapping("/administracao/operadores/novo")
	public ModelAndView NovoOperador(ModelMap model, HttpSession session) {		
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		model.addAttribute("conteudo", "administracao/operadores/cadastrar");
		
		return new ModelAndView("template_painel", model);		
	}
	
	@RequestMapping("/administracao/operadores/cadastrar")
	public String CadastrarOperador(PessoaDto operador) {		
		
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
		   
		    Map<String, String> map = new HashMap<>();
		    
		    map.put("nome", operador.getNome());
		    map.put("rg", operador.getRg());
		    map.put("cpf", operador.getCpf());
		    map.put("senha", operador.getUsuario().getSenha());
		    map.put("email", operador.getEmail());
		    map.put("data_nasc", "2020-06-06");
		    map.put("matricula", Integer.toString(operador.getMatricula()));
		    map.put("usuario.perfil", operador.getUsuario().getPerfil());
		    
			RestTemplate template = new RestTemplate();
			
			final String urilistaAlunos = "https://api-seetec.herokuapp.com/api/operador";
			ResponseEntity<String> response = template.postForEntity(urilistaAlunos, map, String.class);
			int codestatus = response.getStatusCodeValue();
			
			
			if (codestatus == 200 || codestatus == 201) {
				return "redirect:/administracao/operadores/novo?msg=success";			
			}
			else {
				return "redirect:/administracao/operadores/novo?msg=failure";
				
			}
		}
		catch (Exception e) {
			System.out.println(e);
			return "redirect:/administracao/operadores/novo?msg=failure";
			
		}
				
	}
	
	@RequestMapping("/administracao/cursos")
	public ModelAndView Curso(ModelMap model, HttpSession session) {
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		final String urilistaAlunos = "https://api-seetec.herokuapp.com/api/curso";
		
		
		ResponseEntity<CursosDto[]> response = restTemplate.getForEntity(urilistaAlunos,CursosDto[].class);
		CursosDto[] cursos = response.getBody();

		
		model.addAttribute("cursos", cursos);
		
		model.addAttribute("conteudo", "administracao/cursos/listar");
		return new ModelAndView("template_painel", model);		
	}
	
	@RequestMapping("/administracao/cursos/novo")
	public ModelAndView NovoCurso(ModelMap model, HttpSession session) {		
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		model.addAttribute("conteudo", "administracao/cursos/cadastrar");
		
		return new ModelAndView("template_painel", model);		
	}

	@RequestMapping("/administracao/cursos/cadastrar")
	public String CadastrarCurso(CursosDto curso) {		
		
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
		   
		    Map<String, String> map = new HashMap<>();
		    
		    map.put("nome", curso.getNome());
		    map.put("codigo", curso.getNome());
		    map.put("descricao", curso.getDescricao());
		    
			RestTemplate template = new RestTemplate();
			
			final String uriCurso = "https://api-seetec.herokuapp.com/api/curso";
			ResponseEntity<String> response = template.postForEntity(uriCurso, map, String.class);
			int codestatus = response.getStatusCodeValue();
			
			if (codestatus == 200 || codestatus == 201) {
				return "redirect:/administracao/cursos/novo?msg=success";
				
			}
			else {
				return "redirect:/administracao/cursos/novo?msg=failure";
				
			}
		}
		catch (Exception e) {
			System.out.println(e);
			return "redirect:/administracao/cursos/novo?msg=failure";
		}
				
	}
	
	@RequestMapping("/administracao/documentos")
	public ModelAndView Documentos(ModelMap model, HttpSession session) {
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		final String urilistaDocumentos = "https://api-seetec.herokuapp.com/api/documentos";
		
		
		ResponseEntity<DocumentosDto[]> response = restTemplate.getForEntity(urilistaDocumentos,DocumentosDto[].class);
		DocumentosDto[] documentos = response.getBody();

		
		model.addAttribute("documentos", documentos);
		
		model.addAttribute("conteudo", "administracao/documentos/listar");
		return new ModelAndView("template_painel", model);		
	}
	
	@RequestMapping("/administracao/documentos/novo")
	public ModelAndView NovoDocumento(ModelMap model, HttpSession session) {		
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		model.addAttribute("conteudo", "administracao/documentos/cadastrar");
		
		return new ModelAndView("template_painel", model);		
	}
	
	@RequestMapping("/administracao/documentos/cadastrar")
	public String CadastrarDocumento(DocumentosDto documentos) {		
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
		   
		    Map<String, String> map = new HashMap<>();
		    
		    map.put("nome", documentos.getNome());
		    map.put("descricao", documentos.getDescricao());
		    
			RestTemplate template = new RestTemplate();
			
			final String uriDocumentos = "https://api-seetec.herokuapp.com/api/documentos";
			ResponseEntity<String> response = template.postForEntity(uriDocumentos, map, String.class);
			int codestatus = response.getStatusCodeValue();
			
			if (codestatus == 200 || codestatus == 201) {
				return "redirect:/administracao/documentos/novo?msg=success";
			}
			else {
				return "redirect:/administracao/documentos/novo?msg=failure";
			}
		}
		catch (Exception e) {
			System.out.println(e);
			return "redirect:/administracao/documentos/novo?msg=failure";
		}
				
	}
}