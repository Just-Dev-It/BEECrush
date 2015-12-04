package com.activites;

import com.domaine.Base;
import com.domaine.Jeu;
import com.domaine.Joueur;
import com.domaine.Niveau;
import com.example.beecrush.R;
import com.jeu.Parametre;
import com.jeu.grille.Grille;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class JeuActivity extends Activity {

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
	
	private void init() {
		Parametre.activiteDuJeu = this;
		Parametre.layoutDuJeu = (RelativeLayout)
				findViewById(R.id.activity_jeu_relative_layout);
		Parametre.resources = getResources();
		
		// Inialiser menu haut
		LinearLayout linearLayout = (LinearLayout) findViewById(
				R.id.activity_jeu_linear_layout_haut);
		
		SeekBar seekBar = new SeekBar(this);
		linearLayout.addView(seekBar);
		seekBar.setMax(niveau.getScoresAFaire()[2]);
		seekBar.getLayoutParams().width = Parametre.widthEcran / 3;
		
		TextView textViewNiveau = new TextView(this);
		textViewNiveau.setText("" + niveau.getNumero());
		linearLayout.addView(textViewNiveau);
		textViewNiveau.getLayoutParams().width = Parametre.widthEcran / 3;
		
		TextView textViewNbAbeilles = new TextView(this);
		textViewNbAbeilles.setText(joueur.getNbAbeillesUtilises()
				+ "/" + niveau.getNbAbeilles());
		linearLayout.addView(textViewNbAbeilles);
		textViewNbAbeilles.getLayoutParams().width = Parametre.widthEcran / 3;
		
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
	
}
