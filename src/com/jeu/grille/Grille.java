package com.jeu.grille;

import com.domaine.Jeu;
import com.jeu.Fleur;
import com.jeu.Parametre;

public class Grille {

	public static final int NB_CASES_PAR_LIGNE = 7;
	public static final int width_Cases =
			Parametre.widthEcran/ (NB_CASES_PAR_LIGNE + 1);
	
	protected Jeu jeu;
	protected int nbCasesX;
	protected int nbCasesY;
	protected Case[][] cases;
	
	public Grille(Jeu jeu) {
		this.jeu = jeu;
		this.nbCasesX = NB_CASES_PAR_LIGNE;
		this.nbCasesY = NB_CASES_PAR_LIGNE;
		this.cases = new Case[nbCasesX][nbCasesY];
		
		initCases();
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
				cases[i][j].ajouterFleur(Fleur.getFleurAleat());
			}
		}
	}
	
}
