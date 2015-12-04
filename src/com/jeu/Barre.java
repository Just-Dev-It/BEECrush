package com.jeu;

public class Barre {
	protected int max;
	protected int miel;
	protected int progression;
	
	public Barre(int max){
		this.max = max;
	}

	public void init(){
		this.miel = 0;
	}
	
	public void setMiel(int m){
		this.miel = m;
		initImage();
	}
	
	public int getMiel(){
		return this.miel;
	}
	
	public int getMielMax(){
		return this.max;
	}
	
	public void initImage(){
		progression = (max-miel)/max;
	}
}
