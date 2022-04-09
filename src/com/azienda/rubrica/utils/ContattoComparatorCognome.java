package com.azienda.rubrica.utils;

import java.util.Comparator;

import com.azienda.rubrica.model.Contatto;

public class ContattoComparatorCognome implements Comparator<Contatto>{

	@Override
	public int compare(Contatto o1, Contatto o2) {
		return o1.getCognome().compareTo(o2.getCognome());
	}

}
