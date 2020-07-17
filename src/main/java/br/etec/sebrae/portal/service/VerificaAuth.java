package br.etec.sebrae.portal.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class VerificaAuth {
	public ModelMap VerificaAuth(ModelMap model, HttpSession session) {
		try {
			model.addAttribute("user_token", session.getAttribute("user_token").toString());
			model.addAttribute("permissao", session.getAttribute("permissao").toString());
			model.addAttribute("id_usuario", session.getAttribute("id_usuario").toString());
			model.addAttribute("nome", session.getAttribute("nome").toString());
			return model;
		}
		catch (Exception e){
			return null;
		}
	}
}
