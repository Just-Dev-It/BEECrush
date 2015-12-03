package com.domaine;

public class Niveau {
	
	protected static final int NB_ETOILES = 3;
	
	protected int numero;
	protected int nbAbeilles;
	protected int scoreMaxiFait;
	protected int[] scoresAFaire;
	protected int nbEtoilesPourDebloquer;
	
	public Niveau(int numero, int nbAbeilles,
			int[] scoresAFaire, int nbEtoilesPourDebloquer) {
		this.numero = numero;
		this.scoreMaxiFait = 0;
		this.nbAbeilles = nbAbeilles;
		this.scoresAFaire = scoresAFaire;
		this.nbEtoilesPourDebloquer = nbEtoilesPourDebloquer;
	}
	
	public void setScoreMaxiFait(int score) {
		scoreMaxiFait = score;
	}

	public int getNumero() {
		return numero;
	}

	public int getNbAbeilles() {
		return nbAbeilles;
	}

	public int getScoreMaxiFait() {
		return scoreMaxiFait;
	}

	public int[] getScoresAFaire() {
		return scoresAFaire;
	}

	public int getNbEtoilesPourDebloquer() {
		return nbEtoilesPourDebloquer;
	}
	
}
