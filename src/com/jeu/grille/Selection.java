package com.jeu.grille;

import java.util.ArrayList;
import java.util.List;

import com.domaine.Jeu;
import com.jeu.Fleur;

public class Selection {

	private Jeu jeu;
	private List<Fleur> fleursSelectionnees;
	
	public Selection(Jeu jeu) {
		this.jeu = jeu;
		mettreA0();
	}
	
	public void ajouterFleur(Fleur fleur) {
		if (isACoteDeLaDerniereFleurSelectionnee(fleur)) {
			if (!fleursSelectionnees.contains(fleur)) {
				fleursSelectionnees.add(fleur);
				fleur.selectionner();
			} else {
				if (fleursSelectionnees.size() >= 2
						&& fleur.equals(fleursSelectionnees.
								get(fleursSelectionnees.size() - 2))) {
					deselectionnerDerniere();
				}
			}
		}
	}
	
	private void deselectionnerDerniere() {
		if (fleursSelectionnees.size() > 0) {
			Fleur fleur = fleursSelectionnees.
					get(fleursSelectionnees.size() - 1);
			fleur.deselectionner();
			fleursSelectionnees.remove(fleur);
		}
	}
	
	public int getScore() {
		//TODO
		return fleursSelectionnees.size();
	}
	
	/**
	 * Renvoie vrai si la fleur est a cote de la derniere selectionne
	 * et qu'ils sont de memes types.
	 * @param fleur
	 * @return
	 */
	private boolean isACoteDeLaDerniereFleurSelectionnee(Fleur fleur) {
		if (fleursSelectionnees.size() == 0) {
			return true;
		} else {
			Case caseNouvelleFleur = fleur.getCase();
			Case caseDerniereFleurSeletionnee = fleursSelectionnees.
					get(fleursSelectionnees.size() - 1).getCase();
			return caseNouvelleFleur.estACoteDe(caseDerniereFleurSeletionnee);
		}
	}
	
	private void mettreA0() {
		fleursSelectionnees = new ArrayList<>();
	}
	
	public void relacher() {
		if (fleursSelectionnees.size() > 1) {
			for (Fleur fleur: fleursSelectionnees) {
				fleur.getCase().enleverFleur();
			}
			
			jeu.getGrille().initFleurs();
			jeu.getJoueur().augmenterScoreDe(getScore());
			mettreA0();
		} else {
			deselectionnerDerniere();
		}
	}
	
	public void retirerDerniereFleur() {
		fleursSelectionnees.remove(fleursSelectionnees.size() - 1);
	}
	
}
