package com.jeu.grille;

import com.domaine.Jeu;
import com.jeu.Fleur;
import com.jeu.Parametre;

public class Grille {

	public static final int NB_CASES_PAR_LIGNE = 7;
	public static final int width_Cases =
			Parametre.widthEcran/ (NB_CASES_PAR_LIGNE + 1);
	public static final int width_Cases_Selectionnables =
			(width_Cases << 3) / 10;
	
	protected Jeu jeu;
	public int nbCasesX;
	public int nbCasesY;
	protected Case[][] cases;
	
	protected Selection selection;
	
	public Grille(Jeu jeu) {
		this.jeu = jeu;
		this.nbCasesX = NB_CASES_PAR_LIGNE;
		this.nbCasesY = NB_CASES_PAR_LIGNE;
		this.selection = new Selection(jeu);
		this.cases = new Case[nbCasesX][nbCasesY];
		
		initCases();
		initFleurs();
	}
	
	public Selection getSelection() {
		return selection;
	}
	
	public void initCases() {
		for (int i = 0; i < nbCasesX; i++) {
			for (int j = 0; j < nbCasesY; j++) {
				int marginGauche = (Parametre.widthEcran -
						(NB_CASES_PAR_LIGNE * width_Cases)) >> 1;
				int marginHaut = (Parametre.heightEcran -
						(NB_CASES_PAR_LIGNE * width_Cases)) >> 1;
			
				int xEcran = marginGauche + (i * width_Cases);
				int yEcran = marginHaut + (j * width_Cases);
				
				cases[i][j] = new Case(i, j, xEcran, yEcran, this);
			}
		}
	}
	
	public void initFleurs() {
		for (int i = 0; i < nbCasesX; i++) {
			for (int j = 0; j < nbCasesY; j++) {
				if (cases[i][j] == null) {
					cases[i][j].ajouterFleur(Fleur.getFleurAleat(cases[i][j]));
				}
			}
		}
	}
	
	public Case[][] getCases() {
		return cases;
	}
	
	public void onTouchDOWN(int x, int y) {
		Fleur fleur = Fleur.getFleurEnPos(x, y);
		if (fleur != null) {
			selection.ajouterFleur(fleur);
		}
	}
	
	public void onTouchMOVE(int x, int y) {
		onTouchDOWN(x, y);
	}
	
	public void onTouchUP() {
		selection.relacher();
	}
	
}
