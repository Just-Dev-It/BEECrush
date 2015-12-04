package com.domaine;

import android.widget.SeekBar;
import android.widget.TextView;

public class Joueur {
	protected Jeu jeu;
	protected String nom;
	protected int nbAbeillesUtilises;
	protected int scoreFaitEnCeMoment;
	
	public TextView textViewNbAbeilles;
	public SeekBar seekBarScore;
	
	public Joueur(Jeu jeu, String nom) {
		this.jeu = jeu;
		this.nom = nom;
		init();
	}
	
	public void init() {
		nbAbeillesUtilises = 0;
		scoreFaitEnCeMoment = 0;
	}
	
	public void incrementerNbAbeilles() {
		nbAbeillesUtilises ++;
		ecrireNbAbeilles();
		
		if (nbAbeillesUtilises == jeu.getNiveauSelectionne().getNbAbeilles()) {
			jeu.finir();
		}
	}
	
	public void ecrireScore() {
		seekBarScore.setProgress(scoreFaitEnCeMoment);
	}
	
	public void ecrireNbAbeilles() {
		textViewNbAbeilles.setText(nbAbeillesUtilises
				+ "/" + jeu.getNiveauSelectionne().getNbAbeilles());
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
		ecrireScore();
	}
	
	@Override
	public String toString() {
		return getNom();
	}
}
