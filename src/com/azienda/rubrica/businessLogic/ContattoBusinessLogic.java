// secondo commento per Git
package com.azienda.rubrica.businessLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.azienda.rubrica.exception.ContattoEsistenteException;
import com.azienda.rubrica.exception.ContattoNonTrovatoException;
import com.azienda.rubrica.exception.ContattoNonValidoException;
import com.azienda.rubrica.exception.RubricaVuotaException;
import com.azienda.rubrica.model.Contatto;
import com.azienda.rubrica.model.Rubrica;
import com.azienda.rubrica.utils.ContattoComparatorCognome;
import com.azienda.rubrica.utils.ContattoComparatorCognomeNome;
import com.azienda.rubrica.utils.ContattoComparatorNome;
import com.azienda.rubrica.utils.Validatore;

public class ContattoBusinessLogic {

	public static void aggiungiContatto(Rubrica rubrica,Contatto contatto) throws ContattoEsistenteException, ContattoNonValidoException {
		Validatore.controllaContatto(contatto);
		if ( rubrica.getContatti().contains(contatto)  ) {
			throw new ContattoEsistenteException("Il contatto: " + contatto + " e' gia' presente in rubrica");
		}
		rubrica.getContatti().add(contatto);
	}
	
	public static void aggiornamentoCompletoContatto(Rubrica rubrica,Contatto contattoVecchio,Contatto contattoNuovo) throws ContattoNonValidoException, RubricaVuotaException, ContattoNonTrovatoException {
		Validatore.controllaContatto(contattoNuovo);
		Contatto vecchioContatto = cerca(rubrica,contattoVecchio);
		if(rubrica.getContatti().remove(vecchioContatto)) {
			rubrica.getContatti().add(contattoNuovo);
		}
	}
	
	public static void aggiornamentoParzialeContatto(Rubrica rubrica,Contatto contattoVecchio,Contatto contattoNuovo) throws ContattoNonValidoException, RubricaVuotaException, ContattoNonTrovatoException {
		Validatore.controllaContattoPerAggiornamentoParziale(contattoNuovo);
		Contatto vecchioContatto = cerca(rubrica,contattoVecchio);
		if ( contattoNuovo.getNome() != null && !contattoNuovo.getNome().isEmpty() ) {
			vecchioContatto.setNome(contattoNuovo.getNome());
		}
		if ( contattoNuovo.getCognome() != null && !contattoNuovo.getCognome().isEmpty() ) {
			vecchioContatto.setCognome(contattoNuovo.getCognome());
		}
		if ( contattoNuovo.getTelefono() != null && !contattoNuovo.getTelefono().isEmpty() ) {
			vecchioContatto.setTelefono(contattoNuovo.getTelefono());
		}
	}
	
	public static void cancellazioneContatto(Rubrica rubrica,Contatto contattoDaCancellare) throws ContattoNonValidoException, ContattoNonTrovatoException, RubricaVuotaException {
		Validatore.controllaContatto(contattoDaCancellare);
		Contatto contatto = cerca(rubrica,contattoDaCancellare);
		rubrica.getContatti().remove(contatto);
	}
	
	private static Contatto cerca(Rubrica rubrica,Contatto contatto) throws RubricaVuotaException, ContattoNonTrovatoException {
		if ( rubrica.getContatti().isEmpty() ){
			throw new RubricaVuotaException("La rubrica è vuota");
		}
		for( Contatto c : rubrica.getContatti() ) {
			if ( c.equals(contatto) ) {
				return c;
			}
		}
		throw new ContattoNonTrovatoException("Il contatto " + contatto + " non è presente in rubrica");
	}
	
	public static List<Contatto> cercaPerNomeLike(Rubrica rubrica,String nome) throws RubricaVuotaException {
		if ( rubrica.getContatti().isEmpty() ){
			throw new RubricaVuotaException("La rubrica è vuota");
		}
		List<Contatto> result = new ArrayList<Contatto>();
		for ( int i=0; i<rubrica.getContatti().size(); i++ ) {
			Contatto c = rubrica.getContatti().get(i);
			if ( c.getNome().contains(nome) ){
				result.add(c);
			}
		}
		return result;
	}
	
	public static List<Contatto> cercaPerCognomeLike(Rubrica rubrica,String cognome) throws RubricaVuotaException {
		if ( rubrica.getContatti().isEmpty() ){
			throw new RubricaVuotaException("La rubrica è vuota");
		}
		List<Contatto> result = new ArrayList<Contatto>();
		for ( int i=0; i<rubrica.getContatti().size(); i++ ) {
			Contatto c = rubrica.getContatti().get(i);
			if ( c.getCognome().contains(cognome)  ){
				result.add(c);
			}
		}
		return result;
	}
	
	public static List<Contatto> cercaPerTelefonoLike(Rubrica rubrica,String telefono) throws RubricaVuotaException {
		if ( rubrica.getContatti().isEmpty() ){
			throw new RubricaVuotaException("La rubrica è vuota");
		}
		List<Contatto> result = new ArrayList<Contatto>();
		for ( int i=0; i<rubrica.getContatti().size(); i++ ) {
			Contatto c = rubrica.getContatti().get(i);
			if ( c.getTelefono().contains(telefono)  ){
				result.add(c);
			}
		}
		return result;
	}
	
	public static void stampaRubrica(Rubrica rubrica) throws RubricaVuotaException {
		if ( rubrica.getContatti().isEmpty() ){
			throw new RubricaVuotaException("La rubrica è vuota");
		}
		System.out.println("\n\nSTAMPA COMPLETA DELLA RUBRICA\n");
		for( Contatto contatto : rubrica.getContatti() ) {
			System.out.println(contatto);
		}
	}
	
	public static void stampaRubricaOrdinataPerNome(Rubrica rubrica) throws RubricaVuotaException {
		System.out.println("\n\nSTAMPA COMPLETA DELLA RUBRICA ORDINATA PER NOME\n");
		Collections.sort(rubrica.getContatti(),new ContattoComparatorNome());
		stampaRubrica(rubrica);
	}
	
	public static void stampaRubricaOrdinataPerCognome(Rubrica rubrica) throws RubricaVuotaException {
		System.out.println("\n\nSTAMPA COMPLETA DELLA RUBRICA ORDINATA PER COGNOME\n");
		Collections.sort(rubrica.getContatti(),new ContattoComparatorCognome());
		stampaRubrica(rubrica);
	}
	
	public static void stampaRubricaOrdinataPerCognomeENome(Rubrica rubrica) throws RubricaVuotaException {
		System.out.println("\n\nSTAMPA COMPLETA DELLA RUBRICA ORDINATA PER COGNOME E PER NOME\n");
		Collections.sort(rubrica.getContatti(),new ContattoComparatorCognomeNome());
		stampaRubrica(rubrica);
	}
	
}