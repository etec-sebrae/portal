package br.etec.sebrae.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.etec.sebrae.portal.dtos.EventosDto;
import br.etec.sebrae.portal.dtos.MudancaStatusDto;
import br.etec.sebrae.portal.dtos.Resposta;
import br.etec.sebrae.portal.dtos.SolicitacoesDto;
import br.etec.sebrae.portal.service.ListaAlunos;
import br.etec.sebrae.portal.service.VerificaAuth;

@Controller
@RequestMapping("/solicitacoes")
public class SolicitacoesController {
	
	@Autowired
	VerificaAuth auth;
	
	@RequestMapping("/")
	public ModelAndView consultarSolicitacoes(ModelMap model, HttpSession session) {
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		final String urilistaSolicitacoes = "https://api-seetec.herokuapp.com/api/solicitacoes";		
			
		@SuppressWarnings("unchecked")
		Resposta<SolicitacoesDto> result = restTemplate.getForObject(urilistaSolicitacoes, Resposta.class);

		model.addAttribute("solicitacao", result.getContent());
		model.addAttribute("conteudo", "solicitacoes/listar");
		 
		return new ModelAndView("template_painel", model); 
		
	} 
	  
	
	@RequestMapping(value = "/concluir/{status}/{id}", method = RequestMethod.GET)
	public String concluirSolicitacoes(ModelMap model, HttpSession session, @PathVariable Integer id, @PathVariable Integer status) {
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return "redirect:/login";
		}
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "https://api-seetec.herokuapp.com/api/solicitacoes/" + Integer.toString(id);	
		try {
			restTemplate.put(uri, status);
			return "redirect:/solicitacoes/?msg=success";
		}
		catch(Exception e){ 
			return "redirect:/solicitacoes/?msg=error"; 
		}
	} 

}
