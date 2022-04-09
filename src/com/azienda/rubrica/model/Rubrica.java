package com.azienda.rubrica.model;

import java.util.ArrayList;
import java.util.List;

public class Rubrica {
	
	private String nome;
	private Integer anno;
	private List<Contatto> contatti;
	
	public Rubrica(String nome, Integer anno) {
		this.nome = nome;
		this.anno = anno;
		contatti = new ArrayList<Contatto>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public List<Contatto> getContatti() {
		return contatti;
	}

	public void setContatti(List<Contatto> contatti) {
		this.contatti = contatti;
	}
	
}