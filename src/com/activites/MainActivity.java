package com.activites;

import com.domaine.Base;
import com.domaine.Joueur;
import com.example.beecrush.R;
import com.jeu.Parametre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		init();
	}
	
	private void init() {
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		Parametre.widthEcran = dm.widthPixels;
		Parametre.heightEcran = dm.heightPixels;
		
		Button bouton = (Button) findViewById(R.id.activity_main_bouton_ok);
		bouton.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				//TODO
				Base.jeu.setJoueur(new Joueur(Base.jeu, "KIRIKOU"));
				
				startActivity(new Intent(
						MainActivity.this, NiveauxActivity.class));
				finish();
			}
		});
	}
}
