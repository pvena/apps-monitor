package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.loginuse.Configuration.LogConfiguration;
import com.example.loginuse.Configuration.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;

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
	public void logEvent(Context context, Intent intent) {
		try
		{
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
			String info;
			boolean enable = (	netInfo != null && 
								netInfo.getType() != ConnectivityManager.TYPE_WIFI && 
								netInfo.isConnectedOrConnecting());
			
			info = LogFormat.getLog(LogConstants.STATE, enable);//DATA TRANSMISSION CONNECTED (3G/GSM)
			
			if(!info.equals(lastLog)){
				LogLine log = new LogLine(info,LogConstants.CONNECTION_STATE_TAG);
				LogSave.getInstance().saveData(log);
				lastLog = info;
			}
		}
		catch(Exception e)
		{
			Log.e("ERROR", "CONNECTION-LOG" + e.getMessage());
		}
	}

}
