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
	protected int width = (Parametre.widthEcran / 3) - xPos;
	protected int height = width / 4;
	
	protected int epaisseurContour = height / 20;
	
	protected int max;
	protected int miel;
	
	protected ImageView imageContour;
	protected ImageView imageVide;
	protected ImageView imagePleine;
	
	protected ImageView imageViewAbeille1;
	protected ImageView imageViewAbeille2;
	protected ImageView imageViewAbeille3;
		
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
		
		imageVide.getLayoutParams().width = width - (2 * epaisseurContour);
		imageVide.getLayoutParams().height = height - (2 * epaisseurContour);
		
		imagePleine.getLayoutParams().height = height - (2 * epaisseurContour);
		
		majImages();
		
		// Les abeilles
		final int withPots = 2 * height / 3;
		final int xPosPots = xPos - (withPots / 2);
		final int yPosPots = yPos + height + 5;
		final int marginPots = width / 3;
		
		imageViewAbeille1 = new ImageView(activity);
		imageViewAbeille2 = new ImageView(activity);
		imageViewAbeille3 = new ImageView(activity);
		
		layout.addView(imageViewAbeille1);
		layout.addView(imageViewAbeille2);
		layout.addView(imageViewAbeille3);
		
		imageViewAbeille1.setBackground(Parametre.resources.
				getDrawable(R.drawable.pot_de_miel_n_b));
		imageViewAbeille2.setBackground(Parametre.resources.
				getDrawable(R.drawable.pot_de_miel_n_b));
		imageViewAbeille3.setBackground(Parametre.resources.
				getDrawable(R.drawable.pot_de_miel_n_b));
		
		imageViewAbeille1.getLayoutParams().width = withPots;
		imageViewAbeille2.getLayoutParams().width = withPots;
		imageViewAbeille3.getLayoutParams().width = withPots;
		imageViewAbeille1.getLayoutParams().height = withPots;
		imageViewAbeille2.getLayoutParams().height = withPots;
		imageViewAbeille3.getLayoutParams().height = withPots;
		
		imageViewAbeille1.setX(xPosPots + marginPots);
		imageViewAbeille1.setY(yPosPots);
		imageViewAbeille2.setX(xPosPots + (2 * marginPots));
		imageViewAbeille2.setY(yPosPots);
		imageViewAbeille3.setX(xPosPots + (3 * marginPots));
		imageViewAbeille3.setY(yPosPots);
		
	}
	
	public void majImages() {
		imagePleine.getLayoutParams().width = (int) (((float) miel / (float) max)
				* (float) (width - (2 * epaisseurContour)));
		if (imagePleine.getLayoutParams().width > width - (2 * epaisseurContour)) {
			imagePleine.getLayoutParams().width = width - (2 * epaisseurContour);
		}
		
		if (miel >= 100) {
			imageViewAbeille1.setBackground(Parametre.resources.
					getDrawable(R.drawable.pot_de_miel));
		}
		if (miel >= 200) {
			imageViewAbeille2.setBackground(Parametre.resources.
					getDrawable(R.drawable.pot_de_miel));
		}
		if (miel >= 300) {
			imageViewAbeille3.setBackground(Parametre.resources.
					getDrawable(R.drawable.pot_de_miel));
		}
	}
}
