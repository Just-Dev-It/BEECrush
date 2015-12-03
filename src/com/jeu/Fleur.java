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
	
	public Fleur(TypeFleur type, Case caseGrille){
		this.type = type;
		this.caseGrille = caseGrille;
		
		initImage();
	}
	
	public void initImage() {
		image = new ImageView(Parametre.activiteDuJeu);
		Parametre.layoutDuJeu.addView(image);
		image.getLayoutParams().width = width;
		image.getLayoutParams().height = width;
		
		int demiMargin = (Grille.width_Cases - width) >> 1;
		image.setX(demiMargin + caseGrille.getxEcran());
		image.setY(demiMargin + caseGrille.getyEcran());
	}
	
	public Case getCase(){
		return this.caseGrille;
	}
	
	public void ajouter(Case nouvelleCase){
		this.caseGrille = nouvelleCase;
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
	
	public static Fleur getFleurAleat() {
		//TODO
		return null;
	}

}
