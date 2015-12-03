package com.jeu;

import javax.swing.text.html.ImageView;

public class Fleur {
	protected TypeFleur type;
	protected Case caseGrille;
	protected ImageView image;
	
	public Fleur(TypeFleur type, int caseGrille, ImageView image){
		this.type = type;
		this.caseGrille = caseGrille;
		this.image = image;
		
	}
	
	public Case getCase(){
		return this.laCase;
	}
	
	public void ajouter(Case nouvelleCase){
		this.caseGrille = nouvelleCase;
	}

}
