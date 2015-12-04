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

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class JeuActivity extends Activity {

	private MediaPlayer son;
	
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
		jeu.getJoueur().init();
		
		Parametre.activiteDuJeu = this;
		Parametre.layoutDuJeu = (RelativeLayout)
				findViewById(R.id.activity_niveaux_relative_layout);
		Parametre.resources = getResources();
		
		// Inialiser menu haut
		LinearLayout linearLayout = (LinearLayout) findViewById(
				R.id.activity_jeu_linear_layout_haut);
		
		/*SeekBar seekBar = new SeekBar(this);
		linearLayout.addView(seekBar);
		seekBar.setMax(niveau.getScoresAFaire()[2]);
		seekBar.getLayoutParams().width = Parametre.widthEcran / 3;
		jeu.getJoueur().seekBarScore = seekBar;*/
		
		Barre seekBar = new Barre(this);
		linearLayout.addView(seekBar);
		seekBar.setMax(niveau.getScoresAFaire()[2]);
		seekBar.getLayoutParams().width = Parametre.widthEcran / 3;
		jeu.getJoueur().seekBarScore = seekBar;
		
		TextView textViewNiveau = new TextView(this);
		textViewNiveau.setText("" + niveau.getNumero());
		linearLayout.addView(textViewNiveau);
		textViewNiveau.getLayoutParams().width = Parametre.widthEcran / 3;
		
		TextView textViewNbAbeilles = new TextView(this);
		textViewNbAbeilles.setText(joueur.getNbAbeillesUtilises()
				+ "/" + niveau.getNbAbeilles());
		linearLayout.addView(textViewNbAbeilles);
		textViewNbAbeilles.getLayoutParams().width = Parametre.widthEcran / 3;
		jeu.getJoueur().textViewNbAbeilles = textViewNbAbeilles;
		
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
		
		String msg1 = "Retourner";
		
		OnClickListener action1 = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(
						JeuActivity.this, NiveauxActivity.class));
				finish();
			}
		};
		
		BoiteDialogue dialogue = new BoiteDialogue(
				this, "GAME OVER", msg1, action1, null, null);
		
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
	
}
