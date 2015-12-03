package com.domaine;

public class Joueur {
	protected Jeu jeu;
	protected String nom;
	protected int nbAbeillesUtilises;
	protected int scoreFaitEnCeMoment;
	
	public Joueur(Jeu jeu, String nom) {
		this.jeu = jeu;
		this.nom = nom;
		init();
	}
	
	public void init() {
		nbAbeillesUtilises = 0;
		scoreFaitEnCeMoment = 0;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getNbAbeillesUtilises() {
		return nbAbeillesUtilises;
	}
	
	public int getScoreFaitEnCeMoment() {
		return scoreFaitEnCeMoment;
	}
	
	public void augmenterNbAbeillesUtilises() {
		nbAbeillesUtilises++;
	}
	
	public void augmenterScoreDe(int score) {
		scoreFaitEnCeMoment += score;
	}
	
	@Override
	public String toString() {
		return getNom();
	}
}
