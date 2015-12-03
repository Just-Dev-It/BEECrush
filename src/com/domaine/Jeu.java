package com.domaine;

import java.util.ArrayList;
import java.util.List;

import com.jeu.grille.Grille;

public class Jeu {
	
	protected Joueur joueur;
	protected List<Niveau> niveaux;
	protected Niveau niveauSelectionne;
	
	protected Grille grille;

	
	public Jeu() {
		niveauSelectionne = null;
		this.niveaux = new ArrayList<>();
		
		//TODO
		int[] tab = {100, 200, 300};
		niveaux.add(new Niveau(this, 1, 15, tab, 0));
		niveaux.add(new Niveau(this, 2, 15, tab, 0));
		niveaux.add(new Niveau(this, 3, 15, tab, 0));
		niveaux.add(new Niveau(this, 4, 15, tab, 0));
		niveaux.add(new Niveau(this, 5, 15, tab, 0));
		niveaux.add(new Niveau(this, 6, 15, tab, 0));
		niveaux.add(new Niveau(this, 7, 15, tab, 0));
		niveaux.add(new Niveau(this, 8, 15, tab, 0));
		niveaux.add(new Niveau(this, 9, 15, tab, 0));
		niveaux.add(new Niveau(this, 10, 15, tab, 0));
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public List<Niveau> getNiveaux() {
		return niveaux;
	}
	
	public Niveau getNiveauSelectionne() {
		return niveauSelectionne;
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public void setNiveauSelectionne(int indice) {
		niveauSelectionne = niveaux.get(indice);
	}
	
	public void setGrille(Grille grille) {
		this.grille = grille;
		grille.initFleurs();
	}
	
	public Grille getGrille() {
		return grille;
	}
}
