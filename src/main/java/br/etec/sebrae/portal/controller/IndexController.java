package br.etec.sebrae.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.etec.sebrae.portal.service.VerificaAuth;

@Controller
public class IndexController {
	
	@Autowired
	VerificaAuth auth;
	
	@RequestMapping("/")
	public ModelAndView index(ModelMap model, HttpSession session) {
		model = auth.VerificaAuth(model, session);
		if (model == null) {
			return new ModelAndView("login");
		}
		
		//RestTemplate template = new RestTemplate();
		model.addAttribute("conteudo", "index");
		return new ModelAndView("template_painel", model);		
	}
	
	@RequestMapping("/home")
	String home() {		
		
		return "redirect:/";		
	}
	
	@RequestMapping("/login")
	String login() {				 
		return ("login");		
	}
	
	@RequestMapping("/logout")
	String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";		
	}
	
	
}
