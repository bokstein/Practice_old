package com.example.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.example.managers.NetworkManger;

public class NetEvent {
    private   String            m_url;

    protected byte[]            m_dataToSend;
    protected byte[]            m_dataReceived;
    protected String            m_contentType;

	protected INetworkEventListener m_eventListener;


    public NetEvent(String url, byte[] data, INetworkEventListener eventListener) 
    {
        m_url = url;
        m_dataToSend = data;
        m_eventListener = eventListener;
    }

    public boolean doSend(HttpURLConnection connection) {
        try {
        	
            if ( m_dataToSend == null ) {
                connection.setRequestMethod("GET");
                return true;
            }

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Length", String.valueOf(m_dataToSend.length));
            connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
            connection.setRequestProperty("Connection", "Keep-Alive");

            DataOutputStream outputStream = NetworkManger.openDataOutputStream();

            outputStream.write(m_dataToSend);
            outputStream.flush();
            
            return true;

        }catch ( Throwable t ) {
            System.out.println("NetEvent.doSend() throws exception: " + t);

        }finally {
        	NetworkManger.closeDataOutputStream();
        }

        return false;
    }


    public boolean doReceive(HttpURLConnection connection) {

        try {
            
            m_contentType = getHeader(connection, "Content-Type");

            DataInputStream inputStream = NetworkManger.openDataInputStream();
            
            m_dataReceived = readInputStream(inputStream);
            
            if ( m_dataReceived != null && m_dataReceived.length > 0 ) {
                System.out.println("NetEvent.doReceive()\n" + new String(m_dataReceived));
            }else {
                System.out.println("NetEvent.doReceive() Receive data is null");
            }

        }catch ( Throwable t ) {
            System.out.println("NetEvent.doReceive() failed to get data from: (" + m_url + ")" + t);
            return false;

        }finally {
            NetworkManger.closeDataInputStream();
            NetworkManger.closeConnection();
        }
        
        return true;
    }

    public void handleResponse() {
    	if (m_eventListener != null)
    	{
    		m_eventListener.onResponseReceived(m_dataReceived);
    	}
    }
    

    protected String getHeader(HttpURLConnection connection, String key) {
        try {
            return connection.getHeaderField(key);
        }catch ( Throwable th ) {
        }
        return null;
    }

    
    private byte[] readInputStream(InputStream inputStream)
    {
    	try {
    		byte[] resultBuff = new byte[0];
    		byte[] buff = new byte[8192];
    		int k = -1;

    		while((k = inputStream.read(buff, 0, buff.length)) > -1) {
    			byte[] tbuff = new byte[resultBuff.length + k];
    			System.arraycopy(resultBuff, 0, tbuff, 0, resultBuff.length);
    			System.arraycopy(buff, 0, tbuff, resultBuff.length, k);
    			resultBuff = tbuff;
    		}

    		System.out.println(resultBuff.length + " bytes read.");

    		return resultBuff;
    	} catch (IOException e) {
    		System.out.println("Inputstream read failed");
    		return null;
    	}
    }
    
    public String getUrl(){
    	return m_url;
    }
}
