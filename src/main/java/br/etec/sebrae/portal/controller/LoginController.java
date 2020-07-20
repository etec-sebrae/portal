package br.etec.sebrae.portal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import br.etec.sebrae.portal.dtos.LoginDto;
import br.etec.sebrae.portal.dtos.PerfilDto;

@Controller
public class LoginController {
	
		
	@RequestMapping("/logar")
	public String logar(LoginDto login,  HttpSession session){
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
	   
	    Map<String, Object> map = new HashMap<>();
	    
	    map.put("senha", login.getPassword());
	    map.put("email", login.getEmail());
	    
	    final String uriAuth = "https://api-seetec.herokuapp.com/api/auth";
	    
	    try{
	    	ResponseEntity<PerfilDto> response = restTemplate.postForEntity(uriAuth, map, PerfilDto.class);
	    	
	    	int codestatus = response.getStatusCodeValue();
	    	
	    	if(codestatus == 200) {
	    	
		    	PerfilDto perfil = response.getBody();
		    	
		    	String perfil_acesso = perfil.getPessoa().getTipo().toString().trim();
		    	
		    	if (!perfil_acesso.equals("FUNCIONARIO")) {
		    		return "redirect:/login?msg=unauthorized";
		    	}
		    	
		    	session.setAttribute("user_token", perfil.getToken());
			    session.setAttribute("permissao", perfil.getPessoa().getUsuario().getPerfil());
			    session.setAttribute("id_usuario", perfil.getPessoa().getUsuario().getId());
			    session.setAttribute("nome", perfil.getPessoa().getNome());
		    	
		    	return "redirect:/";
	    	}
	    	else {
	    		return "redirect:/login?msg=error";
	    	}
	    } 
	    catch (Exception e){
	    	System.out.println(e);
	    	return "redirect:/login?msg=error";
	    }	
	    
	}	
}
