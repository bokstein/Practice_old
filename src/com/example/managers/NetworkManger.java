package com.example.managers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;

import com.example.network.NetEvent;

public class NetworkManger extends Thread{

	public NetworkManger(Context context) {
		// TODO Auto-generated constructor stub
	}
	
    private static HttpURLConnection  s_connection;

    private static DataOutputStream   s_dataOutputStream;

    private static DataInputStream    s_dataInputStream;

    static byte[]                     s_buffer;
    
    private static NetEvent           s_netEvent;
    
	public void startNetEventProcess(NetEvent netEvent) {
		s_netEvent = netEvent;
		start();
		
	}

	@Override
    public void run()
    {
    	try {
    		s_connection = (HttpURLConnection) new URL(s_netEvent.getUrl()).openConnection();

    		boolean sendDone = s_netEvent.doSend(s_connection);

    		if ( sendDone ) {

    			int respCode = s_connection.getResponseCode();

    			if ( respCode != HttpURLConnection.HTTP_OK ) {
    
    				System.out.println("Network.receive() - repsonse code: " + respCode + "  - is not HTTP OK");
    				return;
    			} else {
    				
    				boolean receiveDone = s_netEvent.doReceive(s_connection);
   
    				if ( receiveDone ) {
    					s_netEvent.handleResponse();
    				}
    			}

    		}

    		closeStreamsConnection();

    	} catch (MalformedURLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    }
    
    private final static void closeStreamsConnection() {
        closeDataOutputStream();
        closeDataInputStream();
        closeConnection();
    }
    
    public static final void closeDataOutputStream() {
        if ( s_dataOutputStream != null ) {
            try {
                s_dataOutputStream.close();

            }catch ( Throwable th ) {
                System.out.println("Network.closeDataOutputStream() Throwable occurred" + th);
            }

            s_dataOutputStream = null;
        }
    }
    
    public static final void closeDataInputStream() {
        if ( s_dataInputStream != null ) {
            try {
                s_dataInputStream.close();

            }catch ( Throwable th ) {
                System.out.println("Network.closeDataInputStream() Throwable occurred" + th);
            }

            s_dataInputStream = null;
        }
    }
    
    public static final boolean closeConnection() {
        if ( s_connection != null ) {
            try {
                s_connection.disconnect();
            }catch ( Throwable th ) {
                System.out.println("Network.closeConnection() Throwable occurred: ");
            }

            s_connection = null;
            return true;

        }else {
            return false;
        }
    }
    
    public static final DataInputStream openDataInputStream() {

        closeDataInputStream();

        try {
            s_dataInputStream = new DataInputStream(s_connection.getInputStream());

        }catch ( IOException e ) {
            System.out.println("Network.openDataInputStream() IOException " + e);
        }

        return s_dataInputStream;
    }

    public static final DataOutputStream openDataOutputStream() {

        closeDataOutputStream();

        try {
            s_dataOutputStream = new DataOutputStream(s_connection.getOutputStream());

        }catch ( IOException e ) {
            System.out.println("Network.openDataOutputStream() IOException " + e);
        }

        return s_dataOutputStream;
    }


}
