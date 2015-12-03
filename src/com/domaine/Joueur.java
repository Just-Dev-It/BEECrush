package com.domaine;

public class Joueur {
	protected Jeu jeu;
	protected String nom;
	
	public Joueur(Jeu jeu, String nom) {
		this.jeu = jeu;
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	@Override
	public String toString() {
		return getNom();
	}
}
