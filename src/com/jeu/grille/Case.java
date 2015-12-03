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
	
	public boolean estACoteDe(Case autre) {
		return getDirection(this, autre) != null;
	}
	
	public static Direction getDirection(Case cDep, Case cArr) {
		int xArr, yArr, xDep, yDep;
		xArr = cArr.getxGrille();
		yArr = cArr.getyGrille();
		xDep = cDep.getxGrille();
		yDep = cDep.getyGrille();
		
		if (xArr-1==xDep && yArr==yDep)
			return Direction.DROITE;
		if (xArr-1==xDep && yArr-1==yDep)
			return Direction.BAS_DROITE;
		if (xArr==xDep && yArr-1==yDep)
			return Direction.BAS;
		if (xArr+1==xDep && yArr-1==yDep)
			return Direction.BAS_GAUCHE;
		if (xArr+1==xDep && yArr==yDep)
			return Direction.GAUCHE;
		if (xArr+1==xDep && yArr+1==yDep)
			return Direction.HAUT_GAUCHE;
		if (xArr==xDep && yArr+1==yDep)
			return Direction.HAUT;
		if (xArr-1==xDep && yArr+1==yDep)
			return Direction.HAUT_DROITE;
		return null;
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
