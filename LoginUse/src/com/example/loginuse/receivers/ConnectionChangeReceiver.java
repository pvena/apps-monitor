package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LsLog;
import com.example.loginuse.log.SaveLog;
import com.example.loginuse.util.Constants;

/**
 * Broadcast receiver used to hear and log connection state changes
 *
 */
public class ConnectionChangeReceiver extends GeneralLoggingReceiver {
	
	private static String lastLog;
	/**
	 * Creator
	 */
	public ConnectionChangeReceiver(){
		super();
		super.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
	
	}

	/*
	 * (non-Javadoc)
	 * @see com.example.loginuse.receivers.GeneralLoggingReceiver#logEvent(android.content.Context, android.content.Intent)
	 */
	@Override
	public void logEvent(Context context, Intent intent) {
		try
		{
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
			String info;
			boolean enable = (	netInfo != null && 
								netInfo.getType() != ConnectivityManager.TYPE_WIFI && 
								netInfo.isConnectedOrConnecting());
			
			info = LogFormat.getLog("State", enable);//DATA TRANSMISSION CONNECTED (3G/GSM)
			
			if(!info.equals(lastLog)){
				LsLog log = new LsLog(info,Constants.CONNECTION_STATE_TAG);
				SaveLog.getInstance().saveData(log);
				lastLog = info;
			}
		}
		catch(Exception e)
		{
			Log.e("ERROR", "CONNECTION-LOG" + e.getMessage());
		}
	}

}
