package com.azienda.rubrica.utils;

import java.util.Comparator;

import com.azienda.rubrica.model.Contatto;

public class ContattoComparatorCognomeNome implements Comparator<Contatto>{

	@Override
	public int compare(Contatto o1, Contatto o2) {
		int compare = o1.getCognome().compareTo(o2.getCognome());
		if ( compare != 0 ) {
			return compare;
		}
		return o1.getNome().compareTo(o2.getNome());
	}

}
