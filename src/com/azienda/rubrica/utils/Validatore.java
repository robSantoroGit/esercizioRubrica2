package com.azienda.rubrica.utils;

import com.azienda.rubrica.exception.ContattoNonValidoException;
import com.azienda.rubrica.model.Contatto;

public class Validatore {
	
	public static void controllaContatto(Contatto contatto) throws ContattoNonValidoException {
		String message = null;
		if ( contatto == null ) {
			message = "Contatto non valido in quanto null";
		}
		else if ( contatto.getNome() == null || contatto.getNome().isEmpty() ) {
			message = "Contatto non valido in quanto il nome non e' stato inserito";
		}
		else if ( contatto.getCognome() == null || contatto.getCognome().isEmpty() ) {
			message = "Contatto non valido in quanto il cognome non e' stato inserito";
		}
		else if ( contatto.getTelefono() == null || contatto.getTelefono().isEmpty() ) {
			message = "Contatto non valido in quanto il telefono non e' stato inserito";
		}
		if ( message != null ) {
			throw new ContattoNonValidoException(message);
		}
	}
	
	public static void controllaContattoPerAggiornamentoParziale(Contatto contatto) throws ContattoNonValidoException {
		String message = null;
		if ( contatto == null ) {
			message = "Contatto non valido in quanto null";
		}
		else if ( (contatto.getNome() == null || contatto.getNome().isEmpty()) &&
				(contatto.getCognome() == null || contatto.getCognome().isEmpty()) &&
				(contatto.getTelefono() == null || contatto.getTelefono().isEmpty()) ) {
			message = "Contatto non valido in quanto tutti i campi non sono stati inseriti";
		}
		if ( message != null ) {
			throw new ContattoNonValidoException(message);
		}
	}

}
