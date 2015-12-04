package com.jeu;

import com.activites.JeuActivity;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class BoiteDialogue extends Builder {
	
	public BoiteDialogue(Context context) {
		super(context);
	}
	
	public BoiteDialogue(Context context, String message,
			String msgPositive, OnClickListener actionPositive) {
		
		super(context);
		
		TextView textView = new TextView(context);
		textView.setText(message);
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, JeuActivity.textSize);
		textView.setTextColor(Color.BLACK);
		
		super.setView(textView);
		super.setNegativeButton(msgPositive, actionPositive);
	}
	
	public BoiteDialogue(Context context, String message,
			String msgPositive, OnClickListener actionPositive,
			String msgNegative, OnClickListener actionNegative) {
		
		super(context);
		
		TextView textView = new TextView(context);
		textView.setText(message);
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, JeuActivity.textSize);
		textView.setTextColor(Color.BLACK);
		
		super.setView(textView);
		super.setPositiveButton(msgPositive, actionPositive);
		super.setNegativeButton(msgNegative, actionNegative);
	}
	
}