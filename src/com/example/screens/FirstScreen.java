package com.example.screens;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.consts.ExampleConsts;
import com.example.managers.DataManager;
import com.example.managers.MainManager;
import com.example.practice.R;
import com.example.services.ExampleService;

public class FirstScreen extends Activity implements OnClickListener {
	
	private Button m_calculateButton;
	private Button m_saveButton;
	private Button m_loadButton;
	private Button m_serviceButton;
	private EditText m_editText;
	private TextView m_textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_screen);
		m_calculateButton = (Button) findViewById(R.id.button1);
		m_saveButton = (Button) findViewById(R.id.SaveButton);
		m_loadButton = (Button) findViewById(R.id.LoadButton);
		m_serviceButton = (Button) findViewById(R.id.ServiceButton);
		m_editText = (EditText) findViewById(R.id.editText1);
		m_textView = (TextView) findViewById(R.id.textView2);
		
		m_calculateButton.setOnClickListener(this);
		m_saveButton.setOnClickListener(this);
		m_loadButton.setOnClickListener(this);
		m_serviceButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button1)
		{
			Editable text  = m_editText.getText();
			new AsyncTask<String, Void, String>() {

				@Override
				protected String doInBackground(String... params) {
					int x = 0;
					for (int i = 0; i < 10000 ; i++){
						x++;
					}
					String str  = params[0] + "Paz";
					return str;
				}

				@Override
				protected void onPostExecute(String result) {
					m_editText.setText(result);
				}

				@Override
				protected void onPreExecute() {};

				@Override
				protected void onProgressUpdate(Void... values) {};

			}.execute(text.toString());
		} else if (v.getId() == R.id.SaveButton){
//			MainManager.getInstance().getdDataManager().saveKeyValueString(this, "editTextValue", m_editText.getText().toString());
			MainManager.getInstance().getdDataManager().saveToFile("editTextValue", m_editText.getText().toString());
			m_textView.setText("Saved");
		} else if (v.getId() == R.id.LoadButton){
//			String str = MainManager.getInstance().getdDataManager().getKeyValueString(this, "editTextValue");
			new Thread(){
				public void run()
				{
					runOnUiThread(new Runnable() {
						public void run() {
							byte[] data = MainManager.getInstance().getdDataManager().loadFromFile("editTextValue");
							ByteArrayInputStream bais = new ByteArrayInputStream(data);
							DataInputStream dis = new DataInputStream(bais);
							String str = null;
							try {
								str = dis.readUTF().toString();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (str!= null && !str.equals(DataManager.NO_STRING))
							{
								m_editText.setText(str);
								m_textView.setText("Loaded");
							}
							
						}
					});
				}
			}.start();
		} else if (v.getId() == R.id.ServiceButton)
		{
			Intent myIntent = new Intent(ExampleConsts.CHANGE_STRING_ACTION);
			myIntent.putExtra("String", m_editText.getText().toString());
			sendBroadcast(myIntent);
		}
	}

}
