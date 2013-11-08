package com.example.managers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.consts.ExampleConsts;
import com.example.practice.MainActivity;
import com.example.screens.FourthScreen;
import com.example.screens.SecondScreen;
import com.example.screens.ThirdScreen;

public class ScreenManager {
	
	public static final int SCREEN_1 = 0;
	public static final int SCREEN_2 = 1;
	public static final int SCREEN_3 = 2;
	public static final int SCREEN_4 = 3;
	
	private Context m_context;
	
	public ScreenManager(Context context) {
		m_context = context;
	}



	public void showScreen(int screen){
		
		Intent myIntent = null;
		
		switch (screen) {
		case SCREEN_1:
//			Intent myIntent = new Intent(m_context, FirstScreen.class);
			myIntent = new Intent(ExampleConsts.SHOW_SCREEN_ACTION);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			m_context.startActivity(myIntent);
			break;
			
		case SCREEN_2:
			myIntent = new Intent(m_context, SecondScreen.class);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			m_context.startActivity(myIntent);
			break;
			
		case SCREEN_3:
			myIntent = new Intent(m_context, ThirdScreen.class);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			m_context.startActivity(myIntent);
			break;
			
		case SCREEN_4:
			myIntent = new Intent(m_context, FourthScreen.class);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			m_context.startActivity(myIntent);
			break;
			
		default:
			break;
		}
		
	}



	public void showAlertDialog(Context context) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
 
			// set title
			alertDialogBuilder.setTitle("Alert Title");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Click yes to exit!")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
//						MainActivity.this.finish();
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
			}
		
}


