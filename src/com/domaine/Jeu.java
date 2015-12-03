package com.domaine;

public class Jeu {
	
	protected Joueur joueur;
	protected Niveau[] niveaux;
	
	public Jeu() {
		this.niveaux = new Niveau[Base.NB_NIVEAUX];
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public Niveau[] getNiveaux() {
		return niveaux;
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}
