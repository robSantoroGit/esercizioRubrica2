package com.azienda.rubrica.exception;

public class RubricaVuotaException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public RubricaVuotaException(String message,Throwable throwable) {
		super(message,throwable);
	}

	public RubricaVuotaException(String message) {
		super(message);
	}
	
}