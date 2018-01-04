package com.mulato.mars.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Posicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull(message = "Coordenada X é obrigatória!")
	private Integer coordX;
	
	@NotNull(message = "Coordenada Y é obrigatória!")
	private Integer coordY;
	
	@NotNull(message = "O ponto cardeal é obrigatório!")
	private Character pontoCardeal;

	public Posicao() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getCoordX() {
		return coordX;
	}

	public void setCoordX(Integer coordX) {
		this.coordX = coordX;
	}

	public Integer getCoordY() {
		return coordY;
	}

	public void setCoordY(Integer coordY) {
		this.coordY = coordY;
	}

	public Character getPontoCardeal() {
		return pontoCardeal;
	}

	public void setPontoCardeal(Character pontoCardeal) {
		this.pontoCardeal = pontoCardeal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((coordX == null) ? 0 : coordX.hashCode());
		result = prime * result + ((coordY == null) ? 0 : coordY.hashCode());
		result = prime * result + ((pontoCardeal == null) ? 0 : pontoCardeal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicao other = (Posicao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (coordX == null) {
			if (other.coordX != null)
				return false;
		} else if (!coordX.equals(other.coordX))
			return false;
		if (coordY == null) {
			if (other.coordY != null)
				return false;
		} else if (!coordY.equals(other.coordY))
			return false;
		if (pontoCardeal == null) {
			if (other.pontoCardeal != null)
				return false;
		} else if (!pontoCardeal.equals(other.pontoCardeal))
			return false;
		return true;
	}

}
