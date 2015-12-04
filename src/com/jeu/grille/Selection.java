package com.jeu.grille;



import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.ImageView;

import com.activites.JeuActivity;
import com.domaine.Jeu;
import com.example.beecrush.R;
import com.jeu.Fleur;
import com.jeu.Parametre;

public class Selection {
	
	public static final int valeur_defaut = 8;
	
	private Thread thread;
	
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
		int nbFleurs = fleursSelectionnees.size();
		int lvl = jeu.getNiveauSelectionne().getNumero();
		
		int score = valeur_defaut * (nbFleurs - (lvl + 1));
		return score > 0 ? score : 0;
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
			Case caseDerniereFleurSelectionnee = fleursSelectionnees.
					get(fleursSelectionnees.size() - 1).getCase();
			return caseNouvelleFleur.estACoteDe(caseDerniereFleurSelectionnee)
					&& fleur.memeTypeQue(
							caseDerniereFleurSelectionnee.getFleur());
		}
	}
	
	private void mettreA0() {
		fleursSelectionnees = new ArrayList<>();
	}
	
	public void relacher() {
		if (fleursSelectionnees.size() > 1) {
			((JeuActivity) Parametre.activiteDuJeu).jouerSonAbeille();
			animationAbeille(new ArrayList<Fleur> (fleursSelectionnees));
			for (Fleur fleur: fleursSelectionnees) {
				fleur.getCase().enleverFleur();
			}
			
			jeu.getGrille().initFleurs();
			jeu.getJoueur().augmenterScoreDe(getScore());
			jeu.getJoueur().incrementerNbAbeilles();
			mettreA0();
		} else {
			deselectionnerDerniere();
		}
	}
	
	@SuppressLint("NewApi")
	private void animationAbeille(List<Fleur> fleurs) {
		
		final ImageView image = new ImageView(Parametre.activiteDuJeu);
		Parametre.layoutDuJeu.addView(image);
		image.getLayoutParams().width = Grille.width_Cases;
		image.getLayoutParams().height = Grille.width_Cases;
		image.setBackground(Parametre.resources.getDrawable(R.drawable.abeille));
		
		final int temps = 500;
		final int nbFois = 30;
		final Point depart = new Point(0, Parametre.heightEcran * 3 / 4);
		final Point arrive = new Point(Parametre.widthEcran + 10, Parametre.heightEcran / 4);
		
		final int pasX = (arrive.x - depart.x) / nbFois;
		final int pasY = (arrive.y - depart.y) / nbFois;
		
		image.setX(depart.x);
		image.setY(depart.y);
		
		final Handler handler = new Handler();
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Parametre.activiteDuJeu.runOnUiThread(new Runnable() {
							@Override
							public void run(){
								if (image.getX() >= arrive.x) {
									thread.interrupt();
								} else {
									image.setX(image.getX() + pasX);
									image.setY(image.getY() + pasY);
									handler.postDelayed(this, temps / nbFois);
								}
					        }
						});
					}
				}, 0);
			}
		});
		
		thread.start();
		
	}
	
	private class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	/*
	 * 
	 * final Handler handler = new Handler();
		threadBouge = new Thread(new Runnable() {
			@Override
			public void run() {
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Parametre.activiteGrille.runOnUiThread(new Runnable() {
							@Override
							public void run(){
								replacer(banane);
								caseCourante = parcours.suivante();
								if (caseCourante == null) {
									banane.retirer();
									Parametre.jeu.getJoueur().setALeDroitDeToucherGrille(true);
									threadBouge.interrupt();
								} else {
									banane.allerVers(caseCourante, true);
									Banane.this.multiplierSiSinge(caseCourante);
									handler.postDelayed(this, tempsBougeParCase);
								}
					        }
							
							private void replacer(Pion pion) {
								pion.imageView.setX(caseCourante.getXEcran());
								pion.imageView.setY(caseCourante.getYEcran());
							}
						});
					}
				}, 0);
			}
		});
		
		threadBouge.start();
	 * 
	 * 
	 */
	
}
