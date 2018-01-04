package com.mulato.mars.service.exception;

public class AreaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AreaInvalidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public AreaInvalidaException(String message) {
		super(message);
	}

}
