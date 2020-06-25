package br.etec.sebrae.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.etec.sebrae.portal.dtos.Resposta;
import br.etec.sebrae.portal.dtos.SolicitacoesDto;

@Controller
@RequestMapping("/solicitacoes")
public class SolicitacoesController {
	
	@RequestMapping("/consultar")
	public ModelAndView consultarSolicitacoes(ModelMap model) {
		
		RestTemplate template = new RestTemplate();
		
		final String urilistaSolicitacoes = "http://seetecc.herokuapp.com/api/solicitacoes";
		
		Resposta<SolicitacoesDto> result = template.getForObject(urilistaSolicitacoes, Resposta.class);		
		
		model.addAttribute("solicitacoes", result.getData());
		model.addAttribute("conteudo", "/documentos/solicitacoes");
		
		return new ModelAndView("template", model);
		
		//ModelAndView view = new ModelAndView("documentos/solicitacoes");	 
		
		//view.addObject("solicitacoes", result.getData());
		//view.addObject(attributeValue)
		//return view;
	} 

}
