package com.mulato.mars.resources;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mulato.mars.model.Posicao;
import com.mulato.mars.service.PosicaoService;
import com.mulato.mars.service.exception.AreaInvalidaException;
import com.mulato.mars.service.exception.ParametroInvalidoException;

@RestController
@RequestMapping("/rest")
public class PosicaoResources {

	@Autowired
	private PosicaoService posicaoService;

	@RequestMapping(value = "mars", method = RequestMethod.GET)
	public ResponseEntity<List<Posicao>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(posicaoService.listar());
	}

	@RequestMapping(value = "mars/{parametro}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("parametro") String parametro) {
		
		Posicao posicao = null;
		
		try {
			posicao = posicaoService.deslocarPara(parametro, Long.valueOf(1));
		} catch (ParametroInvalidoException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 Bad Request");
		} catch (AreaInvalidaException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 Bad Request");
		}
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(posicao);
	
	}


}
