package com.mulato.mars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulato.mars.model.Posicao;
import com.mulato.mars.model.Robo;
import com.mulato.mars.repository.PosicaoRepository;
import com.mulato.mars.repository.RoboRepository;
import com.mulato.mars.service.exception.ParametroInvalidoException;

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
	public Posicao deslocarPara(String parametro, Long codigo) throws ParametroInvalidoException {
		
		Posicao posicao = null;
		
		if ((codigo == null) || (parametro == null) || (parametro.equals(""))) {
			throw new ParametroInvalidoException("Parâmetro Inválido!");
		}
		else {
			
			Robo robo = roboEmMissao(codigo);
			
			if (robo == null) {
				throw new ParametroInvalidoException("Robo Inválido!");
			}
			
			int tamanho = parametro.length();

			for (int i = 0; i < tamanho; i++) {
				String valor = parametro.substring(i, i + 1).toUpperCase();
				if ((valor == null) || ((!valor.equals("M")) && (!valor.equals("L")) && (!valor.equals("R")))) {
					throw new ParametroInvalidoException("Parâmetro Inválido!");
				}
			}
		}
		
		posicao = posicaoRepository.findOne(codigo);
		return novaPosicao(posicao);

	}
	
	/**
	 * Método para validar se este robô esta em missão
	 * 
	 * @param codigo
	 * @return
	 * @throws ParametroInvalidoException
	 */
	private Robo roboEmMissao(Long codigo) throws ParametroInvalidoException {
		Robo robo = null;
		try {
			robo = roboRepository.findOne(codigo); 
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return robo;
	}
	
	/**
	 * Método para calcular a nova posição do robô
	 * 
	 * @param posicao
	 * @return
	 * @throws ParametroInvalidoException
	 */
	private Posicao novaPosicao(Posicao posicao) throws ParametroInvalidoException {
		return posicao;
	}
	
}
