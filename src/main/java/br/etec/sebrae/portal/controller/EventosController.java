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
import br.etec.sebrae.portal.dtos.EventosDto;
import br.etec.sebrae.portal.dtos.Resposta;
import br.etec.sebrae.portal.service.VerificaAuth;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	@Autowired
	VerificaAuth auth;
	
	@RequestMapping("/")
	public ModelAndView consultarSolicitacoes(ModelMap model, HttpSession session) {
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		final String urilistaEventos = "https://api-seetec.herokuapp.com/api/evento";		
			
		@SuppressWarnings("unchecked")
		Resposta<EventosDto> result = restTemplate.getForObject(urilistaEventos, Resposta.class);		
		
		model.addAttribute("eventos", result.getContent());
		
		model.addAttribute("conteudo", "eventos/listar");
		
		return new ModelAndView("template_painel", model);
	}
	
	@RequestMapping("/novo")
	public ModelAndView NovoEvento(ModelMap model, HttpSession session) {		
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		model.addAttribute("conteudo", "eventos/cadastrar");  
		
		return new ModelAndView("template_painel", model);		
	}
	 
	@RequestMapping("/cadastrar")
	public String CadastrarCurso(EventosDto evento) {		
		
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
		   
		    Map<String, String> map = new HashMap<>();
		    
		    map.put("nome", evento.getNome());
		    map.put("descricao", evento.getDescricao());
		    map.put("dataInicio", evento.getDataInicio().toString());
		    map.put("dataFim", evento.getDataFim().toString());
		    
		    System.out.println(evento.getDataInicio());
		    
			RestTemplate template = new RestTemplate();
			
			final String uriEvento = "https://api-seetec.herokuapp.com/api/evento";
			ResponseEntity<String> response = template.postForEntity(uriEvento, map, String.class);
			int codestatus = response.getStatusCodeValue();
			
			if (codestatus == 200 || codestatus == 201) {
				return "redirect:/eventos/?msg=success";
				
			}
			else {
				return "redirect:/eventos/?msg=failure";
				
			}
		}
		catch (Exception e) {
			System.out.println(e);
			return "redirect:/eventos/?msg=failure";
		}
				
	}
}	
