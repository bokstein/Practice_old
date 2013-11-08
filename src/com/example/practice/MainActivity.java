package com.example.practice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.consts.ExampleConsts;
import com.example.managers.MainManager;
import com.example.managers.ScreenManager;
import com.example.network.INetworkEventListener;
import com.example.network.NetEvent;
import com.example.services.ExampleService;

public class MainActivity extends Activity implements OnClickListener, INetworkEventListener {
	
	private static final int MENU_ITEM1=0;
	
	private Button goToScreen1ButtonByActivity;
	private Button goToScreen1ButtonByBroadcast;
	private Button createNotificationButton;
	private Button serviceButton;
	private Button scrollViewButton;
	private Button listViewButton;
	private Button radioButtonsViewButton;
	private Button alertDialogButtonsViewButton;
	private Button networkViewButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //?
        init();
    }
    
    @SuppressLint("NewApi") private void init() {
    	
    	MainManager.getInstance().setContext(getApplicationContext());
    	RelativeLayout layout = (RelativeLayout) findViewById(R.id.initialLayout);
    	
    	goToScreen1ButtonByActivity = new Button(this);
    	goToScreen1ButtonByActivity.setText("Screen1 activity");
    	goToScreen1ButtonByActivity.setOnClickListener(this);
    	
    	goToScreen1ButtonByBroadcast = new Button(this);
    	goToScreen1ButtonByBroadcast.setText("Screen1 broadcast");
    	goToScreen1ButtonByBroadcast.setOnClickListener(this);
    	
    	createNotificationButton = new Button(this);
    	createNotificationButton.setText("Notification");
    	createNotificationButton.setOnClickListener(this);
    	
    	serviceButton = new Button(this);
    	serviceButton.setText("Service");
    	serviceButton.setOnClickListener(this);
    	
    	scrollViewButton = new Button(this);
    	scrollViewButton.setText("ScrollView");
    	scrollViewButton.setOnClickListener(this);
    	
    	listViewButton = new Button(this);
    	listViewButton.setText("ListView");
    	listViewButton.setOnClickListener(this);
    	
    	radioButtonsViewButton = new Button(this);
    	radioButtonsViewButton.setText("radioButton");
    	radioButtonsViewButton.setOnClickListener(this);
    	
    	alertDialogButtonsViewButton = new Button(this);
    	alertDialogButtonsViewButton.setText("alertDialog");
    	alertDialogButtonsViewButton.setOnClickListener(this);
    	
    	networkViewButton = new Button(this);
    	networkViewButton.setText("network");
    	networkViewButton.setOnClickListener(this);
    	
    	layout.addView(goToScreen1ButtonByActivity);
    	layout.addView(goToScreen1ButtonByBroadcast);
    	layout.addView(createNotificationButton);
    	layout.addView(serviceButton);
    	layout.addView(scrollViewButton);
    	layout.addView(listViewButton);
    	layout.addView(radioButtonsViewButton);
    	layout.addView(alertDialogButtonsViewButton);
    	layout.addView(networkViewButton);
    	
    	goToScreen1ButtonByActivity.setY(60);
    	goToScreen1ButtonByBroadcast.setY(160);
    	createNotificationButton.setY(260);
    	serviceButton.setY(360);
    	scrollViewButton.setY(460);
    	listViewButton.setY(560);
    	radioButtonsViewButton.setY(660);
    	alertDialogButtonsViewButton.setY(760);
    	networkViewButton.setY(860);
    	
//    	IntentFilter filter = new IntentFilter();
//    	filter.addAction(ExampleConsts.SHOW_SCREEN_ACTION);
//    	
//    	BroadCastExample bce = new BroadCastExample();
//    	registerReceiver(bce, filter);
    	
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
		MenuItem menuItem1 =  menu.add( Menu.NONE, MENU_ITEM1, Menu.NONE, "code");
//		menu.add(Menu.NONE, MENU_ITEM1, Menu.NONE, "Save").setIcon(R.drawable.save);
        return true;
    }
    
    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         //Should i use async task instide the case?
        switch (item.getItemId())
        {
        case R.id.menu_bookmark:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            Toast.makeText(MainActivity.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.menu_save:
            Toast.makeText(MainActivity.this, "Save is Selected", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.menu_search:
            Toast.makeText(MainActivity.this, "Search is Selected", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.menu_share:
            Toast.makeText(MainActivity.this, "Share is Selected", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.menu_delete:
            Toast.makeText(MainActivity.this, "Delete is Selected", Toast.LENGTH_SHORT).show();
            return true;
            
        case MENU_ITEM1:
            Toast.makeText(MainActivity.this, "Code is Selected", Toast.LENGTH_SHORT).show();
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
	@Override
	public void onClick(View v) {
		if (v == goToScreen1ButtonByActivity){
			MainManager.getInstance().getScreenManager().showScreen(ScreenManager.SCREEN_1);
		} else if (v == goToScreen1ButtonByBroadcast){
			Intent myIntent = new Intent(ExampleConsts.SHOW_SCREEN_ACTION);
			sendBroadcast(myIntent);
		} else if (v == createNotificationButton){
			createNotification();
		} else if (v == serviceButton){
			Intent intent = new Intent(this, ExampleService.class);
			startService(intent);
		} else if (v == scrollViewButton){
			MainManager.getInstance().getScreenManager().showScreen(ScreenManager.SCREEN_2);
		} else if (v == listViewButton){
			MainManager.getInstance().getScreenManager().showScreen(ScreenManager.SCREEN_3);
		} else if (v == radioButtonsViewButton){
			MainManager.getInstance().getScreenManager().showScreen(ScreenManager.SCREEN_4);
		} else if (v == alertDialogButtonsViewButton){
			MainManager.getInstance().getScreenManager().showAlertDialog(this);
		} else if (v == networkViewButton){
			NetEvent netEvent = new NetEvent("http://www.google.com", null, this);
			MainManager.getInstance().getneNetworkManger().startNetEventProcess(netEvent);
		}    	  	  		 		
	}
	
	//Doesn't work for some reason
	@SuppressLint("NewApi") private void createNotification() 
	{
		// prepare intent which is triggered if the
		// notification is selected

		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

		// build notification
		// the addAction re-use the same intent to keep the example short
		Notification n  = new Notification.Builder(this)
		        .setContentTitle("Practice App")
		        .setContentText("Only for practice")
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentIntent(pIntent)
		        .setAutoCancel(true).build();
//		        .addAction(R.drawable.icon, "Call", pIntent)
//		        .addAction(R.drawable.icon, "More", pIntent)
//		        .addAction(R.drawable.icon, "And more", pIntent).build();
		    		  
		NotificationManager notificationManager = 
		  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		notificationManager.notify(0, n); 

	}

	@Override
	public void onResponseReceived(byte[] data) {
		System.out.println("PAZZZZZ");
		
	}

}
