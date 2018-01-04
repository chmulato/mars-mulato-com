package com.mulato.mars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mulato.mars.model.Posicao;
import com.mulato.mars.service.PosicaoService;

@Controller
@RequestMapping("/rest")
public class PosicaoController {

	/**
	 * Robo Gigante de Ferro
	 */
	final private Long roboGiganteDeFerro = Long.valueOf("l"); 
	
	@Autowired
	private PosicaoService posicaoService;

	@GetMapping("/mars")
	public ModelAndView novo(Posicao posicao) {
		ModelAndView mv = new ModelAndView("posicao/nova-posicao");
		mv.addObject(posicao);
		return mv;
	}

	@GetMapping("mars/{parametro}")
	public ModelAndView editar(@PathVariable String parametro) {
		Posicao posicao = posicaoService.deslocarPara(parametro, roboGiganteDeFerro);
		return novo(posicao);
	}

}
