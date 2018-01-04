package com.mulato.mars.service.exception;

public class ParametroInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParametroInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParametroInvalidoException(String message) {
		super(message);
	}

}
