package com.domaine;

import java.util.List;

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
	
	public int getNbEtoiles() {
		int nbEtoiles = 0;
		List<Niveau> niveaux = jeu.getNiveaux();
		for (int i = 0; i < niveaux.size(); i++) {
			Niveau niveauI = niveaux.get(i);
			nbEtoiles += (niveauI.getScoreMaxiFait() / 100 > 3) ? 3 : niveauI.getScoreMaxiFait() / 100;
		}
		return nbEtoiles;
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
	
	public void enregistrerScoreFait(Niveau niveau, int score) {
		niveau.enregistrer(score);
		
	}
}
