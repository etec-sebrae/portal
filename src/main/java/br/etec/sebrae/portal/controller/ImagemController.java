package br.etec.sebrae.portal.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.etec.sebrae.portal.dtos.LoginDto;

@Controller
public class ImagemController {
		
	@RequestMapping("/imagem")
	public ModelAndView imagem(LoginDto login) {
		
		final String imagem = "http://localhost:8082/api/imagem";
		
		RestTemplate restTemplate = new RestTemplate();
		
		Object retorno = restTemplate.getForObject(imagem, Object.class);		
		
		ModelAndView view = new ModelAndView("documentos/imagem");
	
		view.addObject("img", retorno);
		
		return view ;		
	}
	
	@RequestMapping("/upload")
	public ModelAndView upload() {
		
		/*
		 * //final String imagem = "http://localhost:8082/api/imagem";
		 * 
		 * RestTemplate restTemplate = new RestTemplate();
		 * 
		 * Object retorno = restTemplate.getForObject(imagem, Object.class);
		 */	
		
		ModelAndView view = new ModelAndView("documentos/upload");
	
		//view.addObject("img", retorno);
		
		return view ;		
	}	
	
	@RequestMapping("/enviar_imagem")
	//@RequestMapping(value=("/enviar_imagem"),headers=("content-type=multipart/*"))
	public ModelAndView enviarImagem(@RequestParam("file") MultipartFile img) throws IOException {
		
		
		byte[] imgbyte = Base64.encodeBase64(img.getBytes());
		
		System.out.println(imgbyte.toString());
		
		final String urlServico = "http://localhost:8082/api/imagem";
		
		RestTemplate restTemplate = new RestTemplate();
		
		Map<String, byte[]> map = new HashMap<>();
		
		map.put("imagem", imgbyte);
		
		Object retorno = restTemplate.postForEntity(urlServico,map, Object.class);		
		
		ModelAndView view = new ModelAndView("documentos/imagem");
	
		view.addObject("img", retorno);
		
		return view ;		
	}	
	
}
