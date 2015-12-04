package com.domaine;

import java.util.ArrayList;
import java.util.List;

import com.activites.JeuActivity;
import com.jeu.Parametre;
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
		niveaux.add(new Niveau(this, 2, 15, tab, 1));
		niveaux.add(new Niveau(this, 3, 15, tab, 3));
		niveaux.add(new Niveau(this, 4, 15, tab, 5));
		niveaux.add(new Niveau(this, 5, 15, tab, 8));
		niveaux.add(new Niveau(this, 6, 15, tab, 10));
		niveaux.add(new Niveau(this, 7, 15, tab, 13));
		niveaux.add(new Niveau(this, 8, 15, tab, 18));
		niveaux.add(new Niveau(this, 9, 15, tab, 22));
		niveaux.add(new Niveau(this, 10, 15, tab, 25));
		niveaux.add(new Niveau(this, 11, 15, tab, 28));
		niveaux.add(new Niveau(this, 12, 15, tab, 33));
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
	
	public void finir() {
		((JeuActivity) Parametre.activiteDuJeu).finir();
	}
}
