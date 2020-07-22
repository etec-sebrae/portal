package br.etec.sebrae.portal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.etec.sebrae.portal.dtos.Resposta;
import br.etec.sebrae.portal.dtos.SlDocumentosDto;
import br.etec.sebrae.portal.service.VerificaAuth;

@Controller
@RequestMapping("/documentos")
public class DocumentoController {
	@Autowired
	VerificaAuth auth;
	
	@RequestMapping("/")
	public ModelAndView consultarSolicitacoes(ModelMap model, HttpSession session) {
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		final String urilistaSolicitacoes = "https://api-seetec.herokuapp.com/api/relatorio";		
			
		@SuppressWarnings("unchecked")
		Resposta<SlDocumentosDto> result = restTemplate.getForObject(urilistaSolicitacoes, Resposta.class);
		model.addAttribute("documentos", result.getContent()); 
		model.addAttribute("conteudo", "documentos/listar");
		 
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
		final String uri = "https://api-seetec.herokuapp.com/api/relatorio/" + Integer.toString(id);	
		Map<String, Object> map = new HashMap<>();
	     
	    map.put("status", status);
		try {
			restTemplate.put(uri, map);
			return "redirect:/documentos/?msg=success";
		}
		catch(Exception e){ 
			return "redirect:/documentos/?msg=error"; 
		}
	} 
	
	@RequestMapping(value = "/documento/{id}", method = RequestMethod.GET)
	public ModelAndView verDocumento(ModelMap model, HttpSession session, @PathVariable Integer id) {
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "https://api-seetec.herokuapp.com/api/relatorio/" + Integer.toString(id);	
		
		@SuppressWarnings("unchecked")
		SlDocumentosDto[] result = restTemplate.getForObject(uri, SlDocumentosDto[].class);
		
		model.addAttribute("documento", result[0]); 
		model.addAttribute("conteudo", "documentos/ver");
		return new ModelAndView("template_painel", model); 
	} 
}
