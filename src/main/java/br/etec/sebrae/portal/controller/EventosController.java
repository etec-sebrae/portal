package br.etec.sebrae.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

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
		
		/*ResponseEntity<EventosDto[]> response = restTemplate.getForEntity(urilistaEventos,EventosDto[].class);
		EventosDto[] eventos = response.getBody();
		model.addAttribute("eventos", eventos);*/
		
		Resposta<EventosDto> result = restTemplate.getForObject(urilistaEventos, Resposta.class);		
		
		model.addAttribute("eventos", result.getContent());
		
		model.addAttribute("conteudo", "/eventos/listar");
		
		return new ModelAndView("template_painel", model);
	}
	
}	
