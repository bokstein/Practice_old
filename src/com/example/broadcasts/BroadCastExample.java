package com.example.broadcasts;

import com.example.consts.ExampleConsts;
import com.example.managers.MainManager;
import com.example.managers.ScreenManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadCastExample extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ExampleConsts.SHOW_SCREEN_ACTION));
		{
			MainManager.getInstance().getScreenManager().showScreen(ScreenManager.SCREEN_1);
		}
	}

}
