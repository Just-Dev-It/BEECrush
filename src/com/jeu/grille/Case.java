package com.jeu.grille;

import com.example.beecrush.R;
import com.jeu.Fleur;
import com.jeu.Parametre;

import android.annotation.SuppressLint;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class Case {
	protected int xGrille;
	protected int yGrille;
	protected int xEcran;
	protected int yEcran;
	
	protected Fleur fleur;
	protected Grille grille;
	
	protected ImageView image;

	public Case(int xGrille, int yGrille,
			int xEcran, int yEcran, Grille grille) {
		this.xEcran = xGrille;
		this.yGrille = yGrille;
		this.xEcran = xEcran;
		this.yEcran = yEcran;
		this.grille = grille;
		
		image = new ImageView(Parametre.activiteDuJeu);
		Parametre.layoutDuJeu.addView(image);
		image.getLayoutParams().width = Grille.width_Cases;
		image.getLayoutParams().height = Grille.width_Cases;
		image.setX(xEcran);
		image.setY(yEcran);
		image.setBackground(Parametre.resources.
				getDrawable(R.drawable.case_normal));
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

	public void ajouterFleur(Fleur nouvelleFleur){
		this.fleur = nouvelleFleur;
		this.fleur.ajouter(this);
	}
	
	public void enleverFleur(){
		this.fleur.supprimer();
		this.fleur = null;
	}
	
}
