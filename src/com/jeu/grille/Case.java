package com.jeu.grille;


import com.domaine.Base;
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
		this.xGrille = xGrille;
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
		deselectionner();
	}

	public int getxGrille() {
		return this.xGrille;
	}
	
	public int getyGrille() {
		return this.yGrille;
	}
	
	public int getxEcran() {
		return this.xEcran;
	}
	
	public int getyEcran() {
		return this.yEcran;
	}
	
	public boolean estACoteDe(Case autre) {
		return getDirection(this, autre) != null;
	}
	
	public static Direction getDirection(Case cDep, Case cArr) {
		int xArr, yArr, xDep, yDep;
		xArr = cArr.xGrille;
		yArr = cArr.yGrille;
		xDep = cDep.xGrille;
		yDep = cDep.yGrille;
		
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
		deselectionner();
		this.fleur.supprimer();
		this.fleur = null;
	}
	
	public void selectionner() {
		image.setBackground(Parametre.resources.
				getDrawable(R.drawable.case_selectionnee));
	}
	
	public void deselectionner() {
		image.setBackground(Parametre.resources.
				getDrawable(R.drawable.case_normal));
	}
	
	public static boolean isDansCarre(int x, int y,
			int xCarre, int yCarre, int wCarre) {
		return (x >= xCarre && x <= xCarre + wCarre) &&
				(y >= yCarre && y <= yCarre + wCarre);
	}
	
	public Fleur getFleur() {
		return fleur;
	}
	
	public static Case getCaseInPos(int x, int y) {
		int wCases = Grille.width_Cases;
		Grille grille = Base.jeu.getGrille();
		Case[][] cases = grille.getCases();
		
		for (int j = 0; j < grille.nbCasesY; j++) {
			for (int i = 0; i < grille.nbCasesX; i++) {
				Case c = cases[i][j];
				if (Case.isDansCarre(x, y,
						c.getxEcran(), c.getyEcran(), wCases)) {
					return c;
				}
			}
		}
		return null;
	}
	
	public boolean isVide() {
		return fleur == null;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Case)) {
			return false;
		} else {
			Case other = (Case) o;
			return xGrille == other.xGrille && yGrille == other.yGrille;
		}
	}
	
}
