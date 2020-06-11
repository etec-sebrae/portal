package br.etec.sebrae.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.etec.sebrae.portal.dtos.LoginDto;

@Controller
public class LoginController {
		
	@RequestMapping("/logar")
	public String logar(LoginDto login) {
		
		String emailApi = "admin";
		String senhaApi = "123456";
		
		if(emailApi.equals(login.getEmail()) && senhaApi.equals(login.getPassword())) {
			return "redirect:loginOperador";	
		}		
		return "login/cadastro";		
	}	
	
	@RequestMapping("/solicitacoes")
	public ModelAndView solicitacoes() {			
		ModelAndView view = new ModelAndView("documentos/solicitacoes");		
		return view;		
	}

	@RequestMapping("/cadastrologin")
	public ModelAndView cadastrarLogin(LoginDto login) {
		ModelAndView view = new ModelAndView("index");		
		view.addObject("sucesso", "Cadastro efetuado com sucesso!");
		return view	;	
	}	
	
	@RequestMapping("/loginOperador")
	public ModelAndView loginOperador() {			
		ModelAndView view = new ModelAndView("operador/home_operador");		
		return view;		
	}
}
