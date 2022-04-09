package com.azienda.rubrica.exception;

public class ContattoNonValidoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ContattoNonValidoException(String message,Throwable throwable) {
		super(message,throwable);
	}

	public ContattoNonValidoException(String message) {
		super(message);
	}
	
}