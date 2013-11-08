package com.example.managers;

import android.app.Application;
import android.content.Context;

public class MainManager {
	
	private static MainManager s_instance;
	
	public ScreenManager m_screenManager;
	public NetworkManger m_networkManager;
	public DataManager m_dataManager;
	
	private Context m_context;
	
	private MainManager()
	{
		
	}
	
	public static MainManager getInstance()
	{
		if (s_instance == null)
		{
			s_instance = new MainManager();
		}
		
		return s_instance;
	}
	
	public ScreenManager getScreenManager()
	{
		if (m_screenManager == null)
		{
			m_screenManager = new ScreenManager(m_context);
		}
		
		return m_screenManager;
	}
	
	public NetworkManger getneNetworkManger()
	{
		if (m_networkManager == null)
		{
			m_networkManager = new NetworkManger(m_context);
		}
		
		return m_networkManager;
	}
	
	public DataManager getdDataManager()
	{
		if (m_dataManager == null)
		{
			m_dataManager = new DataManager(m_context);
		}
		
		return m_dataManager;
	}

	public void setContext(Context applicationContext) {
		m_context = applicationContext;
		
	}

}
