package com.mulato.mars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulato.mars.model.Posicao;
import com.mulato.mars.model.Robo;
import com.mulato.mars.repository.PosicaoRepository;
import com.mulato.mars.repository.RoboRepository;
import com.mulato.mars.service.exception.AreaInvalidaException;
import com.mulato.mars.service.exception.ParametroInvalidoException;

@Service
public class PosicaoService {

	/**
	 * Área Máxima Permitida
	 */
	final private int areaMaximaPermitida = 5; 

	@Autowired
	private RoboRepository roboRepository;
	
	@Autowired
	private PosicaoRepository posicaoRepository;

	
	/**
	 * Lista de todas as coordenadas existentes
	 * 
	 * @return
	 */
	public List<Posicao> listar() {
		return posicaoRepository.findAll();
	}

	/**
	 * Método publico para recuperar posicao por código
	 * 
	 * @param codigo
	 * @return
	 */
	public Posicao deslocarPara(String parametro, Long codigo) throws ParametroInvalidoException, AreaInvalidaException {
		
		Posicao posicao = null;
		
		if ((codigo == null) || (parametro == null) || (parametro.equals(""))) {
			throw new ParametroInvalidoException("Parâmetro Inválido!");
		}
		else {
			
			Robo robo = roboEmMissao(codigo);
			
			if (robo == null) {
				throw new ParametroInvalidoException("Robô Inválido!");
			}
			
			validarRequisicao(parametro);
		}
		
		posicao = posicaoRepository.findOne(codigo);
		posicao = calcularNovaPosicao(posicao, parametro);
		return salvarPosicao(posicao);

	}

	/**
	 * Método para validar parâmetros da requisição GET
	 * 
	 * @param parametro
	 */
	private void validarRequisicao(String parametro) {
		String parametros = parametro.trim().toUpperCase();
		for (int i = 0; i < parametros.length(); i++) {
			char valor = parametros.charAt(i);		
			if ((valor != 'M') && (valor != 'L') && (valor != 'R')) {
				throw new ParametroInvalidoException("Parâmetro Inválido!");
			}
		}
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
	 * Método para sava a nova posição do robô
	 * 
	 * @param posicao
	 * @return
	 * @throws ParametroInvalidoException
	 */
	private Posicao salvarPosicao(Posicao posicao) throws ParametroInvalidoException {
		posicaoRepository.save(posicao);
		return posicao;
	}
	
	/**
	 * Método para calcular a nova posição do robô 
	 * 
	 * @param posicao
	 * @param parametro
	 * @return
	 */
	private Posicao calcularNovaPosicao(Posicao posicao, String parametro) throws AreaInvalidaException {
		
		int x = posicao.getCoordX();
		int y = posicao.getCoordY();
		char cardeal = posicao.getCardeal().charAt(0);
		parametro = parametro.trim().toUpperCase();

		// regra para movimentação do robô
		for (int i = 0; i < parametro.length(); i++) {
			char passo = parametro.charAt(i);
			switch (passo) {
			case 'L':
				if (cardeal == 'N') {
					cardeal = 'W';
				} else if (cardeal == 'W') {
					cardeal = 'S';
				} else if (cardeal == 'S') {
					cardeal = 'E';
				} else if (cardeal == 'E') {
					cardeal = 'N';
				}
				break;
			case 'R':
				if (cardeal == 'N') {
					cardeal = 'E';
				} else if (cardeal == 'E') {
					cardeal = 'S';
				} else if (cardeal == 'S') {
					cardeal = 'W';
				} else if (cardeal == 'W') {
					cardeal = 'N';
				}
				break;
			case 'M':
				if (cardeal == 'N') {
					y = y + 1;
				} else if (cardeal == 'S') {
					y = y - 1;
				} else if (cardeal == 'E') {
					x = x + 1;
				} else if (cardeal == 'W') {
					x = x - 1;
				}
				break;
			
			} // end switch
		
		} // end for

		if ((x > areaMaximaPermitida) || (x < 0)) {
			throw new AreaInvalidaException("Área Inválida!");
		}

		if ((y > areaMaximaPermitida) || (y < 0)) {
			throw new AreaInvalidaException("Área Inválida!");
		}
		
		posicao.setCoordX(x);
		posicao.setCoordY(y);
		posicao.setCardeal(String.valueOf(cardeal));
		return posicao;
	}
	
}	
	
