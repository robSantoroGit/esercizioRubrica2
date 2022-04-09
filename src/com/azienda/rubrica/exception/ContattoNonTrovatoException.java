package com.azienda.rubrica.exception;

public class ContattoNonTrovatoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ContattoNonTrovatoException(String message,Throwable throwable) {
		super(message,throwable);
	}

	public ContattoNonTrovatoException(String message) {
		super(message);
	}
	
}