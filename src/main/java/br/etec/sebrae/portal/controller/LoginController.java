package br.etec.sebrae.portal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.etec.sebrae.portal.dtos.LoginDto;
import br.etec.sebrae.portal.dtos.RetornoLogin;
@Controller
public class LoginController {
		
	@RequestMapping("/logar")
	public String logar(LoginDto login, HttpSession session){
		
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
	   
	    Map<String, String> map = new HashMap<>();
	    
	    map.put("senha", login.getPassword());
	    map.put("email", login.getEmail());
		
	    final String uriAuth = "https://api-seetec.herokuapp.com/api/auth";
	    
	    try{
	    	ResponseEntity<String> response = template.postForEntity(uriAuth, map, String.class);
	    	int codestatus = response.getStatusCodeValue();
		    
		    if(codestatus == 200) {
		    	Gson gson = new Gson(); 
			    String jsonInString = response.getBody();
			    RetornoLogin recuperaToken = new RetornoLogin();
			    RetornoLogin recuperaPessoa = new RetornoLogin();
			    RetornoLogin recuperaUsuario = new RetornoLogin();
			    
			    recuperaToken = gson.fromJson(jsonInString, RetornoLogin.class);

			    @SuppressWarnings("unchecked")
				Map<String,Object> jsonNodesPessoa = gson.fromJson(jsonInString, Map.class);
			    String jsonSemToken = gson.toJson(jsonNodesPessoa.get("pessoa"));
			    recuperaPessoa = gson.fromJson(jsonSemToken, RetornoLogin.class);
			    
			    @SuppressWarnings("unchecked")
				Map<String,Object> jsonNodesUser = gson.fromJson(jsonSemToken, Map.class);
			    String jsonSemPessoa = gson.toJson(jsonNodesUser.get("usuario"));
			    recuperaUsuario = gson.fromJson(jsonSemPessoa, RetornoLogin.class);
			    
			    /*if (recuperaPessoa.getTipo().toString() != "FUNCIONARIO") {
			    	System.out.print(recuperaPessoa.getTipo());
			    	session.setAttribute("msg_login", "Acesso negado");
			    	return "redirect:/";
			    }*/
			    
			    String permissao = recuperaUsuario.getPerfil();
			    String user_token = recuperaToken.getToken();
			    String nome = recuperaPessoa.getNome();
			    int id_usuario = recuperaPessoa.getId();
			    
			    session.setAttribute("user_token", user_token);
			    session.setAttribute("permissao", permissao);
			    session.setAttribute("id_usuario", id_usuario);
			    session.setAttribute("nome", nome);
			  
			    System.out.println(user_token);
			    return "redirect:/";
		    }
		    else {
		    	session.setAttribute("msg_login", "Usuário ou senha inválidos");
		    	return "redirect:/login"; 
		    }
	    } 
	    catch (Exception e){
	    	session.setAttribute("msg_login", "Usuário ou senha inválidos");
	    	System.out.println(e);
	    	return "redirect:/login";
	    }	
	}	

	@RequestMapping("/cadastrologin")
	public ModelAndView cadastrarLogin(LoginDto login) {
		ModelAndView view = new ModelAndView("index");		
		view.addObject("sucesso", "Cadastro efetuado com sucesso!");
		return view	;	
	}
	
	
}
