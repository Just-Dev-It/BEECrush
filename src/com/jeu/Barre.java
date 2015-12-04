package com.jeu;

import com.example.beecrush.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class Barre extends View {
	
	protected int xPos = Parametre.widthEcran / 12;
	protected int yPos = xPos;
	protected int width = (Parametre.widthEcran / 3) - (2 * xPos);
	protected int height = width / 4;
	
	protected int epaisseurContour = height / 20;
	
	protected int max;
	protected int miel;
	protected int progression;
	
	protected ImageView imageContour;
	protected ImageView imageVide;
	protected ImageView imagePleine;
	
	public Barre(Context context, int max){
		super(context);
		this.miel = 0;
		this.max = max;
		
		initImage();
		majImages();
	}

	public void init(){
		this.miel = 0;
	}
	
	public void setMiel(int m){
		this.miel = m;
		majImages();
	}
	
	public int getMiel(){
		return this.miel;
	}
	
	public int getMielMax(){
		return this.max;
	}
	
	public void initImage(){
		Activity activity = Parametre.activiteDuJeu;
		RelativeLayout layout = Parametre.layoutDuJeu;
		
		imageContour = new ImageView(activity);
		imageVide = new ImageView(activity);
		imagePleine = new ImageView(activity);
		
		layout.addView(imageContour);
		layout.addView(imageVide);
		layout.addView(imagePleine);
		
		imageContour.setBackground(activity.getResources().
				getDrawable(R.drawable.carre_noir));
		imageVide.setBackground(activity.getResources().
				getDrawable(R.drawable.carre_blanc));
		imagePleine.setBackground(activity.getResources().
				getDrawable(R.drawable.carre_jaune));
		
		imageContour.setX(xPos);
		imageContour.setY(yPos);
		imageVide.setX(xPos + epaisseurContour);
		imageVide.setY(yPos + epaisseurContour);
		imagePleine.setX(xPos + epaisseurContour);
		imagePleine.setY(yPos + epaisseurContour);
		
		imageContour.getLayoutParams().width = width;
		imageContour.getLayoutParams().height = height;
		
		majImages();
		
	}
	
	public void majImages() {
		
	}
}
