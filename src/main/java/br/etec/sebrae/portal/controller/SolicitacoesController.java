package br.etec.sebrae.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.etec.sebrae.portal.dtos.Resposta;
import br.etec.sebrae.portal.dtos.SolicitacoesDto;

@Controller
@RequestMapping("/solicitacoes")
public class SolicitacoesController {
	
	@RequestMapping("/consultar")
	public ModelAndView consultarSolicitacoes() {
		
		RestTemplate template = new RestTemplate();
		
		final String urilistaSolicitacoes = "https://seetecc.herokuapp.com/api/solicitacoes";
		
		Resposta<SolicitacoesDto> result = template.getForObject(urilistaSolicitacoes, Resposta.class);		
		
		ModelAndView view = new ModelAndView("documentos/solicitacoes");
		
		List<SolicitacoesDto> lista = result.getData();
		
		
		view.addObject("solicitacoes", lista);
		
		return view;
		
	}

}
