package com.azienda.rubrica.exception;

public class ContattoEsistenteException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ContattoEsistenteException(String message,Throwable throwable) {
		super(message,throwable);
	}

	public ContattoEsistenteException(String message) {
		super(message);
	}
	
}