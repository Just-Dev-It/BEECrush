package com.jeu;

import android.graphics.drawable.Drawable;

public enum TypeFleur {
	UN, DEUX, TROIS, QUATRE, CINQ;
	
	public static Drawable getDrawableImage(TypeFleur type) {
		String imageNom = "img_" + type.toString().toLowerCase();
		int imageId = Parametre.resources.getIdentifier(imageNom, "drawable",
				Parametre.activiteDuJeu.getPackageName());
		
		return Parametre.resources.getDrawable(imageId);
	}
}
