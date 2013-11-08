package com.example.managers;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {
	
	private Context m_context;
	
	public static final String NO_STRING = "NO_STRING";
	
	public DataManager(Context context) {
		m_context = context;		
	}
	
	public void saveKeyValueString(Activity activity, String key, String value)
	{
		SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public String getKeyValueString(Activity activity, String key)
	{
		SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
		String str = sharedPref.getString(key, NO_STRING);
		
		return str;
	}
	
	public void saveToFile(String fileName, String str)
	{
		FileOutputStream outputStream;
		DataOutputStream dos;

		try {
		  outputStream = m_context.openFileOutput(fileName, Context.MODE_PRIVATE);
		  dos = new DataOutputStream(outputStream);
		  dos.writeUTF(str);
		  outputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}
	
	public byte[] loadFromFile(String fileName)
	{
		FileInputStream inputStream;
		byte[] data;

		try {
			inputStream = m_context.openFileInput(fileName);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		  return null;
		}
		
		return data;
	}

}
