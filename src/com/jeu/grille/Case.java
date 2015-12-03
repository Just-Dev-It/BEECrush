package com.jeu.grille;

import javax.swing.text.html.ImageView;

public class Case {
	protected int xGrille;
	protected int yGrille;
	protected int xEcran;
	protected int yEcran 
	
	protected Fleur fleur;
	protected Grille grille;
	
	protected ImageView image;

	public Case(int xGrille, int yGrille, int xEcran, int yEcran, Grille grille){
		this.xEcran = xGrille;
		this.yGrille = yGrille;
		this.xEcran = xEcran;
		this.yEcran = yEcran;
		this.grille = grille;
	}

	public int getxGrille() {
		return this.xGrille;
	}
	
	public int getyGrille() {
		return this.yGrille;
	}
	
	public int getxEcran() {
		return this.xGrille;
	}
	
	public int getyEcran() {
		return this.yEcran;
	}
	
	public int getgrille() {
		return this.grille;
	}
	
	public void 
}
