package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogLine;

/**
 * Broadcast receiver used to hear and log connection state changes
 *
 */
public class ConnectionChangeReceiver extends GeneralLoggingReceiver {
	
	/**
	 * Creator
	 */
	public ConnectionChangeReceiver(){
		super();
		super.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		super.logType = LogConstants.CONNECTION_STATE_TAG;
	}

	@Override
	public void initialize() {
		LogConfiguration.getInstance().getContext().registerReceiver(this, this.filter);
	}

	@Override
	public void finalize() {
		LogConfiguration.getInstance().getContext().unregisterReceiver(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.example.loginuse.receivers.GeneralLoggingReceiver#logEvent(android.content.Context, android.content.Intent)
	 */
	@Override
	public void logEvent(Context context, Intent intent,LogLine l) {
		try
		{			
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
			
			boolean enable = (	netInfo != null && 
								netInfo.getType() != ConnectivityManager.TYPE_WIFI && 
								netInfo.isConnectedOrConnecting());
			
			l.addProperty(LogConstants.STATE, enable);//DATA TRANSMISSION CONNECTED (3G/GSM)
		}
		catch(Exception e)
		{
			Log.e("ERROR", "CONNECTION-LOG" + e.getMessage());
		}
	}

}
