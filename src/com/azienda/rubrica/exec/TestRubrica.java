package com.azienda.rubrica.exec;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.azienda.rubrica.businessLogic.ContattoBusinessLogic;
import com.azienda.rubrica.exception.ContattoEsistenteException;
import com.azienda.rubrica.exception.ContattoNonTrovatoException;
import com.azienda.rubrica.exception.ContattoNonValidoException;
import com.azienda.rubrica.exception.RubricaVuotaException;
import com.azienda.rubrica.model.Contatto;
import com.azienda.rubrica.model.Rubrica;

public class TestRubrica {

	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)){
			menu(scanner);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore imprevisto");
		}
	}
	
	private static void menu(Scanner scanner) throws InterruptedException, IOException {
		Rubrica rubrica = new Rubrica("Rubrica contatti",2022);
		int scelta;
		do {
			String message = "\n\nMENU:\n1=LISTA CONTATTI\n2=CREA CONTATTO\n3=AGGIORNAMENTO COMPLETO CONTATTO\n4=AGGIORNAMENTO PARZIALE CONTATTO\n5=CANCELLAZIONE CONTATTO"
					+ "\n6=RICERCA PER NOME\n7=RICERCA PER COGNOME\n8=RICERCA PER TELEFONO\n9=LISTA CONTATTI ORDINATA PER NOME\n10=LISTA CONTATTI ORDINATA PER COGNOME"
					+ "\n11=LISTA CONTATTI ORDINATA PER COGNOME E PER NOME\n0=TERMINA PROGRAMMA";
			scelta = controlloNumero(message,scanner);
			switch ( scelta ) {
				case 0: {
					System.out.println("\nArrivederci");
					break;
				}
				case 1: {
					stampaRubrica(rubrica);
					break;
				}
				case 2: {
					inserimento(scanner,rubrica);
					break;
				}
				case 3: {
					aggiornamentoCompleto(scanner,rubrica);
					break;
				}
				case 4: {
					aggiornamentoParziale(scanner,rubrica);
					break;
				}
				case 5: {
					cancellazione(scanner,rubrica);
					break;
				}
				case 6: {
					ricercaPerNome(scanner,rubrica);
					break;
				}
				case 7: {
					ricercaPerCognome(scanner,rubrica);
					break;
				}
				case 8: {
					ricercaPerTelefono(scanner,rubrica);
					break;
				}
				case 9: {
					stampaRubricaOrdinataPerNome(rubrica);
					break;
				}
				case 10: {
					stampaRubricaOrdinataPerCognome(rubrica);
					break;
				}
				case 11: {
					stampaRubricaOrdinataPerCognomeENome(rubrica);
					break;
				}
				default: {
					System.out.println("Scelta non valida");
				}
			}
		}while( scelta != 0);
	}
	
	private static int controlloNumero(String message,Scanner scanner) {
		do {
			System.out.println(message);
			try {
				return Integer.parseInt(scanner.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Non hai digitato un numero");
			}
		}while(true);
	}
	
	private static void stampaRubrica(Rubrica rubrica) {
		try {
			ContattoBusinessLogic.stampaRubrica(rubrica);
		} catch (RubricaVuotaException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void stampaRubricaOrdinataPerNome(Rubrica rubrica) {
		try {
			ContattoBusinessLogic.stampaRubricaOrdinataPerNome(rubrica);
		} catch (RubricaVuotaException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void stampaRubricaOrdinataPerCognome(Rubrica rubrica) {
		try {
			ContattoBusinessLogic.stampaRubricaOrdinataPerCognome(rubrica);
		} catch (RubricaVuotaException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void stampaRubricaOrdinataPerCognomeENome(Rubrica rubrica) {
		try {
			ContattoBusinessLogic.stampaRubricaOrdinataPerCognomeENome(rubrica);
		} catch (RubricaVuotaException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void inserimento(Scanner scanner,Rubrica rubrica) {
		System.out.println("\nNome:");
		String nome = scanner.nextLine();
		System.out.println("\nCognome:");
		String cognome = scanner.nextLine();
		System.out.println("\nTelefono:");
		String telefono = scanner.nextLine();
		Contatto contatto = new Contatto(nome,cognome,telefono);
		try {
			ContattoBusinessLogic.aggiungiContatto(rubrica,contatto);
			System.out.println("Inserimento effettuato");
		} catch (ContattoEsistenteException e) {
			System.out.println("\nContatto già esistente - " + e.getMessage());
		} catch (ContattoNonValidoException e) {
			System.out.println("\nContatto inserito non valido - " + e.getMessage());
		}
	}
	
	private static void aggiornamentoCompleto(Scanner scanner,Rubrica rubrica) {
		System.out.println("\nNome contatto da aggiornare:");
		String vecchioNome = scanner.nextLine();
		System.out.println("\nCognome contatto da aggiornare:");
		String vecchioCognome = scanner.nextLine();
		System.out.println("\nTelefono contatto da aggiornare:");
		String vecchioTelefono = scanner.nextLine();
		System.out.println("\nNuovo nome:");
		String nome = scanner.nextLine();
		System.out.println("\nNuovo cognome:");
		String cognome = scanner.nextLine();
		System.out.println("\nNuovo telefono:");
		String telefono = scanner.nextLine();
		Contatto contattoVecchio = new Contatto(vecchioNome,vecchioCognome,vecchioTelefono);
		Contatto contattoNuovo = new Contatto(nome,cognome,telefono);
		try {
			ContattoBusinessLogic.aggiornamentoCompletoContatto(rubrica,contattoVecchio,contattoNuovo);
			System.out.println("Aggiornamento effettuato");
		} catch (ContattoNonValidoException e) {
			System.out.println("Contatto da aggiornare non valido - " + e.getMessage());
		} catch (RubricaVuotaException e) {
			System.out.println("Rubrica vuota - " + e.getMessage());
		} catch (ContattoNonTrovatoException e) {
			System.out.println("Contatto da aggiornare non trovato - " + e.getMessage());
		}
	}
	
	private static void aggiornamentoParziale(Scanner scanner,Rubrica rubrica) {
		System.out.println("\nNome contatto da aggiornare:");
		String vecchioNome = scanner.nextLine();
		System.out.println("\nCognome contatto da aggiornare:");
		String vecchioCognome = scanner.nextLine();
		System.out.println("\nTelefono contatto da aggiornare:");
		String vecchioTelefono = scanner.nextLine();
		System.out.println("\nNome (se da aggiornare, altrimenti digitare invio):");
		String nome = scanner.nextLine();
		System.out.println("\nCognome (se da aggiornare, altrimenti digitare invio):");
		String cognome = scanner.nextLine();
		System.out.println("\nTelefono (se da aggiornare, altrimenti digitare invio):");
		String telefono = scanner.nextLine();
		Contatto contattoVecchio = new Contatto(vecchioNome,vecchioCognome,vecchioTelefono);
		Contatto contattoNuovo = new Contatto(nome,cognome,telefono);
		try {
			ContattoBusinessLogic.aggiornamentoParzialeContatto(rubrica,contattoVecchio,contattoNuovo);
			System.out.println("Aggiornamento effettuato");
		} catch (ContattoNonValidoException e) {
			System.out.println("Contatto da aggiornare non valido - " + e.getMessage());
		} catch (RubricaVuotaException e) {
			System.out.println("Rubrica vuota - " + e.getMessage());
		} catch (ContattoNonTrovatoException e) {
			System.out.println("Contatto da aggiornare non trovato - " + e.getMessage());
		}
	}
	
	private static void cancellazione(Scanner scanner,Rubrica rubrica) {
		System.out.println("\nNome contatto da cancellare:");
		String vecchioNome = scanner.nextLine();
		System.out.println("\nCognome contatto da cancellare:");
		String vecchioCognome = scanner.nextLine();
		System.out.println("\nTelefono contatto da cancellare:");
		String vecchioTelefono = scanner.nextLine();
		Contatto contatto = new Contatto(vecchioNome,vecchioCognome,vecchioTelefono);
		try {
			ContattoBusinessLogic.cancellazioneContatto(rubrica,contatto);
			System.out.println("Cancellazione effettuata");
		} catch (ContattoNonValidoException e) {
			System.out.println("Contatto da aggiornare non valido - " + e.getMessage());
		} catch (RubricaVuotaException e) {
			System.out.println("Rubrica vuota - " + e.getMessage());
		} catch (ContattoNonTrovatoException e) {
			System.out.println("Contatto da aggiornare non trovato - " + e.getMessage());
		}
	}
	
	private static void ricercaPerNome(Scanner scanner,Rubrica rubrica) {
		System.out.println("\nNome da ricercare:");
		String nome = scanner.nextLine();
		try {
			List<Contatto> risultati = ContattoBusinessLogic.cercaPerNomeLike(rubrica,nome);
			System.out.println("\nContatti trovati:");
			for ( Contatto c: risultati ) {
				System.out.println(c);
			}
		} catch (RubricaVuotaException e) {
			System.out.println("Rubrica vuota - " + e.getMessage());
		} 
	}
	
	private static void ricercaPerCognome(Scanner scanner,Rubrica rubrica) {
		System.out.println("\nCognome da ricercare:");
		String cognome = scanner.nextLine();
		try {
			List<Contatto> risultati = ContattoBusinessLogic.cercaPerCognomeLike(rubrica,cognome);
			System.out.println("\nContatti trovati:");
			for ( Contatto c: risultati ) {
				System.out.println(c);
			}
		} catch (RubricaVuotaException e) {
			System.out.println("Rubrica vuota - " + e.getMessage());
		} 
	}
	
	private static void ricercaPerTelefono(Scanner scanner,Rubrica rubrica) {
		System.out.println("\nTelefono da ricercare:");
		String telefono = scanner.nextLine();
		try {
			List<Contatto> risultati = ContattoBusinessLogic.cercaPerTelefonoLike(rubrica,telefono);
			System.out.println("\nContatti trovati:");
			for ( Contatto c: risultati ) {
				System.out.println(c);
			}
		} catch (RubricaVuotaException e) {
			System.out.println("Rubrica vuota - " + e.getMessage());
		} 
	}

}
