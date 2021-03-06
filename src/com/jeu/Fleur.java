package com.jeu;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import com.jeu.grille.Case;
import com.jeu.grille.Grille;

@SuppressLint("NewApi")
public class Fleur {
	public static final int width = (Grille.width_Cases << 3) / 10;
	
	protected TypeFleur type;
	protected Case caseGrille;
	protected ImageView image;
	
	public Fleur(TypeFleur type, Case caseGrille) {
		this.type = type;
		this.caseGrille = caseGrille;
	}
	
	public void initImage() {
		image = new ImageView(Parametre.activiteDuJeu);
		Parametre.layoutDuJeu.addView(image);
		
		int demiMargin = (Grille.width_Cases - width) >> 1;
		image.setX(demiMargin + caseGrille.getxEcran());
		image.setY(demiMargin + caseGrille.getyEcran());
		image.getLayoutParams().width = width;
		image.getLayoutParams().height = width;
		image.setBackground(TypeFleur.getDrawableImage(type));
	}
	
	public Case getCase(){
		return this.caseGrille;
	}
	
	public void ajouter(Case nouvelleCase){
		this.caseGrille = nouvelleCase;
		initImage();
	}
	
	public void supprimer() {
		image.setX(-20000);
		image = null;
	}
	
	public void selectionner() {
		caseGrille.selectionner();
	}
	
	public void deselectionner() {
		caseGrille.deselectionner();
	}
	
	public static Fleur getFleurAleat(Case caseGrille) {
		int nb = (int) (Math.random() * TypeFleur.values().length);
		return new Fleur(TypeFleur.values()[nb], caseGrille);
	}
	
	public static Fleur getFleurEnPos(int x,int  y) {
		Case caseFleur = Case.getCaseInPos(x, y);
		if (caseFleur != null && !caseFleur.isVide()) {
			int wCases = Grille.width_Cases;
			// Def Carre selectionnable
			int wCarreSelec = Grille.width_Cases_Selectionnables;
			int xCarreSelec = caseFleur.getxEcran() +
					((wCases - wCarreSelec) >> 1);
			int yCarreSelec = caseFleur.getyEcran() +
					((wCases - wCarreSelec) >> 1);
			if (Case.isDansCarre(x, y,
					xCarreSelec, yCarreSelec, wCarreSelec)) {
				return caseFleur.getFleur();
			}
		}
		return null;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Fleur)) {
			return false;
		} else {
			Fleur other = (Fleur) o;
			return caseGrille.equals(other.caseGrille);
		}
	}
	
	public boolean memeTypeQue(Fleur fleur) {
		return type == fleur.type;
	}

}
