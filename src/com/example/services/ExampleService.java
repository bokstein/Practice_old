package com.example.services;

import com.example.consts.ExampleConsts;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ExampleService extends Service {
	
	private static String m_str = null;
	private static Object stringLock = new Object();
	
	public ExampleService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {

	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run() {
				while (true){
					synchronized (stringLock) 
					{
						if (m_str != null)
						{
							System.out.println(m_str);							
						}
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public static class StringReceiver extends BroadcastReceiver
	{
		
		public StringReceiver() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(ExampleConsts.CHANGE_STRING_ACTION));
			{
				synchronized (stringLock)
				{
					m_str = intent.getStringExtra("String");
				}
				
			}
			
		}
		
	}

}
