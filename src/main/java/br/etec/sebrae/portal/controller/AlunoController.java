package br.etec.sebrae.portal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.etec.sebrae.portal.dtos.AlunosDto;
import br.etec.sebrae.portal.dtos.CursosDto;
import br.etec.sebrae.portal.dtos.EventosDto;
import br.etec.sebrae.portal.dtos.PessoaDto;
import br.etec.sebrae.portal.service.ListaAlunos;
import br.etec.sebrae.portal.service.SolicitacoesServices;
import br.etec.sebrae.portal.service.VerificaAuth;
import br.etec.sebrae.portal.util.ListCursos;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

	static Logger log = LoggerFactory.getLogger(AlunoController.class);

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
		ResponseEntity<PessoaDto[]> response = restTemplate.getForEntity(urilistaAlunos, PessoaDto[].class);
		PessoaDto[] alunos = response.getBody();

		model.addAttribute("alunos", alunos);
		model.addAttribute("conteudo", "aluno/alunos");

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
		model.addAttribute("conteudo", "aluno/cadastro_aluno");
		return new ModelAndView("template_painel", model);
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarAluno(ModelMap model, HttpSession session, @PathVariable Integer id) {

		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		ListaAlunos aluno = new ListaAlunos();
		ListCursos cursos = new ListCursos();

		model.addAttribute("aluno", aluno.ListaAluno(id));
		model.addAttribute("cursos", cursos.ListCursos());
		
		model.addAttribute("conteudo", "aluno/editar"); 
		return new ModelAndView("template_painel", model); 
	}  
 
	@RequestMapping("/cadastro")
	public String cadastroAluno(ModelMap model, AlunosDto aluno) {

		Gson g = new Gson();
		aluno.setCursos(mapperId(aluno.getSelect_cursos()));
		aluno.setSelect_cursos(null);
		String str = g.toJson(aluno);
		Gson gson = g.fromJson(str, Gson.class);

		try {
			final String urilistaAlunos = "https://api-seetec.herokuapp.com/api/aluno";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("content-type", "application/x-www-form-urlencoded");

			RestTemplate template = new RestTemplate();

			@SuppressWarnings("unchecked")
			Map<String, Object> jsonNodesUser = gson.fromJson(str, Map.class);

			ResponseEntity<String> response = template.postForEntity(urilistaAlunos, jsonNodesUser, String.class);
			int codestatus = response.getStatusCodeValue();

			if (codestatus == 200 || codestatus == 201) {
				return "redirect:/aluno/consultar?msg=success";
			} else {
				return "redirect:/aluno/consultar?msg=failure";
			}
		} catch (Exception e) {
			log.info(e.getMessage());			
			return "redirect:/aluno/consultar?msg=failure";
		}

	}
	
	//@RequestMapping("/alterar")
	@RequestMapping(value = "/alterar/{id}", method = RequestMethod.POST)
	public String CadastrarCurso(PessoaDto pessoa, @PathVariable Integer id) {		
		
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
		   
		    Map<String, String> map = new HashMap<>();
		    
		    map.put("nome", pessoa.getNome());
		    map.put("rg", pessoa.getRg());
		    map.put("cpf", pessoa.getCpf());
		    map.put("email", pessoa.getEmail());
		    map.put("data_nasc", "2020-07-07");
		    
			RestTemplate template = new RestTemplate();
			
			final String uriEvento = "https://api-seetec.herokuapp.com/api/aluno/" + Integer.toString(id) ;
			template.put(uriEvento, map);
			
			return "redirect:/aluno/consultar?msg=success";
		}
		catch (Exception e) {
			System.out.println(e);
			return "redirect:/aluno/consultar?msg=failure";
		}
				
	}

	@RequestMapping(value = "/solicitacoes/{id}", method = RequestMethod.GET)
	public ModelAndView solicitacoesAlunos (ModelMap model, HttpSession session,  @PathVariable Integer id) {
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		SolicitacoesServices solicitacoes = new SolicitacoesServices();
		
		model.addAttribute("solicitacao", solicitacoes.ListaSolicitacoesAluno(id));
		model.addAttribute("conteudo", "aluno/solicitacoes");
		return new ModelAndView("template_painel", model);
	} 

	List<CursosDto> mapperId(String ids) {
		String array[] = ids.split(",");
		List<CursosDto> cursos = new ArrayList<CursosDto>();
		for (int i = 0; i < array.length; i++) {
			CursosDto dto = new CursosDto();
			dto.setId(Long.parseLong(array[i]));
			cursos.add(dto);
		}
		return cursos; 
	}

}
