package com.activites;

import java.util.List;

import com.domaine.Base;
import com.domaine.Jeu;
import com.domaine.Niveau;
import com.example.beecrush.R;
import com.jeu.Parametre;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

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
			textView.setBackgroundColor(Color.GRAY);
			
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
			
			textView.setOnClickListener(new OnClickListener() {	
				@Override
				public void onClick(View v) {
					//TODO
				}
			});
			
		}
	}
	
}
