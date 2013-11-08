package com.example.screens;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.practice.R;

public class FourthScreen extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fourth_screen);
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radio0:
	            if (checked)
	                System.out.println("paz0");
	            break;
	        case R.id.radio1:
	            if (checked)
	            	System.out.println("paz1");
	            break;
	        case R.id.radio2:
	            if (checked)
	            	System.out.println("paz2");
	            break;
	        case R.id.radio3:
	            if (checked)
	            	System.out.println("paz3");
	            break;
	    }
	}

}
