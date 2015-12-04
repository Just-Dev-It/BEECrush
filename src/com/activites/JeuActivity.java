package com.activites;

import com.domaine.Base;
import com.domaine.Jeu;
import com.domaine.Joueur;
import com.domaine.Niveau;
import com.example.beecrush.R;
import com.jeu.Barre;
import com.jeu.BoiteDialogue;
import com.jeu.Parametre;
import com.jeu.grille.Grille;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JeuActivity extends Activity {

	private MediaPlayer son;
	public static final int textSize = Parametre.widthEcran / 20;
	
	private Jeu jeu = Base.jeu;
	private Joueur joueur = jeu.getJoueur();
	private Niveau niveau = jeu.getNiveauSelectionne();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_jeu);
		
		init();
	}
	
	@SuppressLint("NewApi")
	private void init() {
		jeu.getJoueur().init();
		
		Parametre.activiteDuJeu = this;
		Parametre.layoutDuJeu = (RelativeLayout)
				findViewById(R.id.activity_niveaux_relative_layout);
		Parametre.resources = getResources();
		
		// Inialiser menu haut
		RelativeLayout layout = Parametre.layoutDuJeu;
		
		int widthFond = Parametre.widthEcran - 100;
		int xPosFond = 50;
		int yPosFond = 50;
		int heightFond = Parametre.heightEcran / 10;
		
		ImageView imageFond = new ImageView(this);
		layout.addView(imageFond);
		imageFond.setBackgroundColor(Color.YELLOW);
		imageFond.setX(xPosFond);
		imageFond.setY(yPosFond);
		imageFond.getLayoutParams().width = widthFond;
		imageFond.getLayoutParams().height = heightFond;
		
		Barre seekBar = new Barre(this, 300);
		jeu.getJoueur().seekBarScore = seekBar;
		
		TextView textViewNiveau = new TextView(this);
		textViewNiveau.setText("Niveau\n" + niveau.getNumero());
		layout.addView(textViewNiveau);
		textViewNiveau.getLayoutParams().width = Parametre.widthEcran / 3;
		textViewNiveau.setX(Parametre.widthEcran / 3);
		textViewNiveau.setY(Parametre.widthEcran / 12);
		textViewNiveau.setGravity(Gravity.CENTER);
		textViewNiveau.setTextColor(Color.BLACK);
		textViewNiveau.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		
		TextView textViewNbAbeilles = new TextView(this);
		textViewNbAbeilles.setText("Abeilles\n" + joueur.getNbAbeillesUtilises()
				+ "/" + niveau.getNbAbeilles());
		layout.addView(textViewNbAbeilles);
		textViewNbAbeilles.getLayoutParams().width = Parametre.widthEcran / 3;
		jeu.getJoueur().textViewNbAbeilles = textViewNbAbeilles;
		textViewNbAbeilles.setX(2 * Parametre.widthEcran / 3);
		textViewNbAbeilles.setY(Parametre.widthEcran / 12);
		textViewNbAbeilles.setGravity(Gravity.CENTER);
		textViewNbAbeilles.setTextColor(Color.BLACK);
		textViewNbAbeilles.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		
		// Initialiser la grille
		jeu.setGrille(new Grille(jeu));
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			jeu.getGrille().onTouchDOWN((int) event.getX(), (int) event.getY());
			break;
		case MotionEvent.ACTION_MOVE:
			jeu.getGrille().onTouchMOVE((int) event.getX(), (int) event.getY());
			break;
		case MotionEvent.ACTION_UP:
			jeu.getGrille().onTouchUP();
			break;
		}
		return true;
	}
	
	public void finir() {
		jeu.getJoueur().enregistrerScoreFait(jeu.getNiveauSelectionne(),
				joueur.getScoreFaitEnCeMoment());
		
		final int nbPots = jeu.getJoueur().getNbEtoiles();
		String msg = (nbPots == 0) ? "Vous avez perdu !" :
			"Vous venez de gagner " + nbPots + " pots de miel !";
		
		String msg1 = "Revenir";
		
		OnClickListener action1 = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(
						JeuActivity.this, NiveauxActivity.class));
				finish();
			}
		};
		
		BoiteDialogue dialogue = new BoiteDialogue(
				this, msg, msg1, action1);
		
		dialogue.show();
	}
	
	public void jouerSonAbeille() {
		if (son != null) {
			son.stop();
			son.release();
		}
		son = MediaPlayer.create(this, R.raw.son_parcours_abeille);
		son.start();
	}
	
	public void demanderQuitter() {
		
		OnClickListener action1 = new OnClickListener() {		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//RIEN
			}
		};
		
		OnClickListener action2 = new OnClickListener() {		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(JeuActivity.this, NiveauxActivity.class));
			}
		};
		
		BoiteDialogue dialogue = new BoiteDialogue(this,
				"Voulez-vous vraiment quitter la partie ?",
				"Non", action1, "Oui", action2);
		
		dialogue.show();
	}
	
	@Override
	public void onBackPressed() {
		demanderQuitter();
	}
	
}
