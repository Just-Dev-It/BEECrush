package com.jeu;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class BoiteDialogue extends Builder {
	
	public BoiteDialogue(Context context) {
		super(context);
	}
	
	public BoiteDialogue(Context context, View view,
			String msg1, OnClickListener action1,
			String msg2, OnClickListener action2) {
		
		super(context);
		
		super.setView(view);
		
		if (msg1 != null) {
			super.setPositiveButton(msg1, action1);
		}
		
		if (msg2 != null) {
			super.setNegativeButton(msg2, action2);
		}
	}
	
	public BoiteDialogue(Context context, String message,
			String msgPositive, OnClickListener actionPositive,
			String msgNegative, OnClickListener actionNegative) {
		
		super(context);
		
		TextView textView = new TextView(context);
		textView.setText(message);
		textView.setGravity(Gravity.CENTER_VERTICAL);
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		
		super.setView(textView);
		
		if (msgPositive != null) {
			super.setPositiveButton(msgPositive, actionPositive);
		}
		
		if (msgNegative != null) {
			super.setNegativeButton(msgNegative, actionNegative);
		}
	}
	
}