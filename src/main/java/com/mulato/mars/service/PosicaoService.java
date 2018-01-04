package com.mulato.mars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulato.mars.model.Posicao;
import com.mulato.mars.repository.PosicaoRepository;
import com.mulato.mars.repository.RoboRepository;
import com.mulato.mars.service.exception.ValorInvalidoException;

@Service
public class PosicaoService {


	@Autowired
	private RoboRepository roboRepository;
	
	@Autowired
	private PosicaoRepository posicaoRepository;

	
	/**
	 * Método publico para recuperar posicao por código
	 * 
	 * @param codigo
	 * @return
	 */
	public Posicao deslocarPara(String parametro) throws ValorInvalidoException {
		
		Long codigo = Long.valueOf(0);
		
		if ((parametro == null) || (parametro.equals(""))) {
			throw new ValorInvalidoException("Valor Inválido!");
		}
		else {
			int tamanho = parametro.length();
			for (int i = 0; i < tamanho; i++) {
				String valor = parametro.substring(i, i + 1);
			}
		}
		
		return posicaoRepository.findOne(codigo);
	}
	
	
	
}
