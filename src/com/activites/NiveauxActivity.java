package com.activites;

import java.util.List;

import com.domaine.Base;
import com.domaine.Jeu;
import com.domaine.Niveau;
import com.example.beecrush.R;
import com.jeu.Parametre;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

@SuppressLint("NewApi")
public class NiveauxActivity extends Activity {

	private Jeu jeu = Base.jeu;
	private static final int NB_NIVEAUX_PAR_LIGNE = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_niveaux);
		
		init();
	}
	
	private void init() {
		TableLayout tableLayout = (TableLayout)
				findViewById(R.id.activity_niveaux_tablelayout);
		
		// Afficher le menus (nb etoiles + icone etoiles)
		LinearLayout layoutHaut = (LinearLayout)
				findViewById(R.id.activity_niveaux_layout_linear_haut);
		
		final int widthIcone = Parametre.widthEcran / 10;
		
		ImageView imageViewIconeEtoile = new ImageView(this);
		layoutHaut.addView(imageViewIconeEtoile);
		imageViewIconeEtoile.setBackground(Parametre.resources.
				getDrawable(R.drawable.pot_de_miel));
		imageViewIconeEtoile.getLayoutParams().width = widthIcone;
		imageViewIconeEtoile.getLayoutParams().height = widthIcone;
		
		TextView textViewEtoiles = new TextView(this);
		layoutHaut.addView(textViewEtoiles);
		textViewEtoiles.getLayoutParams().width = widthIcone;
		textViewEtoiles.getLayoutParams().height = widthIcone;
		textViewEtoiles.setText("x " + jeu.getJoueur().getNbEtoiles());
		textViewEtoiles.setGravity(Gravity.CENTER);
		textViewEtoiles.setTextSize(TypedValue.COMPLEX_UNIT_PX, JeuActivity.textSize);
		textViewEtoiles.setTextColor(Color.BLACK);
		
		// Afficher tous les niveaux
		TableRow tableRow = null;
		List<Niveau> niveaux = jeu.getNiveaux();
		for (int i = 0; i < niveaux.size(); i++) {
			if (i % NB_NIVEAUX_PAR_LIGNE == 0) {
				tableRow = new TableRow(this);
				tableLayout.addView(tableRow);
			}
			
			int widthTextView =
					Parametre.widthEcran / (NB_NIVEAUX_PAR_LIGNE + 1);
			
			TextView textView = new TextView(this);
			tableRow.addView(textView);
			textView.setText("" + niveaux.get(i).getNumero());
			textView.setGravity(Gravity.CENTER);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, JeuActivity.textSize);
			textView.setTextColor(Color.BLACK);
			
			if (jeu.getJoueur().getNbEtoiles() <
					niveaux.get(i).getNbEtoilesPourDebloquer()) {
				textView.setEnabled(false);
				textView.setBackground(getResources().getDrawable(R.drawable.img_un_n_b));
			} else {
				textView.setBackground(getResources().getDrawable(R.drawable.img_un));
			}
			
			int numLigne = tableLayout.getChildCount() - 1;
			int numGrille = tableRow.getChildCount() - 1;
			int margin = (Parametre.widthEcran - (NB_NIVEAUX_PAR_LIGNE
					* widthTextView)) / (NB_NIVEAUX_PAR_LIGNE + 1);
			
			LayoutParams layoutParams = new LayoutParams();
			layoutParams.leftMargin = (numGrille == 0) ? 0 : margin;
			layoutParams.topMargin = (numLigne == 0) ? 0 : margin;
			
			textView.setLayoutParams(layoutParams);
			textView.getLayoutParams().width = widthTextView;
			textView.getLayoutParams().height = widthTextView;
			
			final int indice = i;
			textView.setOnClickListener(new OnClickListener() {	
				@Override
				public void onClick(View v) {
					jeu.setNiveauSelectionne(indice);
					startActivity(new Intent(NiveauxActivity.this,
							JeuActivity.class));
				}
			});
			
			// Nombre de miel pour chaque niveaux
			RelativeLayout layoutPrincipal = (RelativeLayout)
					findViewById(R.id.activity_niveaux_relative_layout);
			
			final int nbMiels = niveaux.get(i).getNbMielsDebloques();
			final int widthPetitesIcones = widthTextView / 5;
			
			final int marginXTextView = (Parametre.widthEcran - (3 * widthTextView)) / 4;
			final int xTextView = marginXTextView + ((i % 3) *
					(marginXTextView + widthTextView));
			final int yTextViewDep = (Parametre.heightEcran -
					(4 * widthTextView) - (3 * marginXTextView)) / 2;
			final int yTextView = yTextViewDep + ((i / 3) * (marginXTextView + widthTextView));
			
			final int marginPetiteIcone = (widthTextView - (3 * widthPetitesIcones)) / 4;
			final int xDepPetitIcone = xTextView + marginPetiteIcone;
			final int yDepPetitIcone = yTextView + (widthTextView - widthPetitesIcones);
			
			for (int j = 0; j < 3; j++) {
				ImageView imagePetitIcone = new ImageView(this);
				layoutPrincipal.addView(imagePetitIcone);
				imagePetitIcone.getLayoutParams().width = widthPetitesIcones;
				imagePetitIcone.getLayoutParams().height = widthPetitesIcones;
				imagePetitIcone.setX(xDepPetitIcone + (j * (marginPetiteIcone + widthPetitesIcones)));
				imagePetitIcone.setY(yDepPetitIcone);
				imagePetitIcone.setBackground(getResources().getDrawable(
						(nbMiels >= j+1 ? R.drawable.pot_de_miel :
							R.drawable.pot_de_miel_n_b)));
			}
			
		}
	}
	
	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
	
}
