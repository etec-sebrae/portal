package br.etec.sebrae.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.etec.sebrae.portal.dtos.Resposta;
import br.etec.sebrae.portal.dtos.SolicitacoesDto;
import br.etec.sebrae.portal.service.VerificaAuth;

@Controller
@RequestMapping("/solicitacoes")
public class SolicitacoesController {
	
	@Autowired
	VerificaAuth auth;
	
	@RequestMapping("/consultar")
	public ModelAndView consultarSolicitacoes(ModelMap model, HttpSession session) {
		
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		/*RestTemplate template = new RestTemplate();
		
		final String urilistaSolicitacoes = "https://api-seetec.herokuapp.com/api/solicitacoes";
		
		Resposta<SolicitacoesDto> result = template.getForObject(urilistaSolicitacoes, Resposta.class);		
		
		model.addAttribute("solicitacoes", result.getData());*/
		model.addAttribute("conteudo", "/documentos/solicitacoes");
		
		return new ModelAndView("template_painel", model);
		 
	} 

}
