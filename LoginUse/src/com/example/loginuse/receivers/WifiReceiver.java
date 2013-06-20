package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.Constants;

public class WifiReceiver extends GeneralLoggingReceiver  {
	
	/**
	 * Creator
	 */
	private static String lastLog;
	
	public WifiReceiver(){
		
		filter = new IntentFilter();
		
		this.filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.example.loginuse.receivers.GeneralLoggingReceiver#logEvent(android.content.Context, android.content.Intent)
	 */
	@Override
	public void logEvent(Context context, Intent intent) {
		try
		{
			NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if(networkInfo.getType() == ConnectivityManager. TYPE_WIFI){
				String newLog = this.getWifiConnection(context,intent);
				if(!newLog.equals(lastLog))
				{
					LsLog l = new LsLog(newLog, Constants.WIFI_STATE_TAG);
					SaveLog.getInstance().saveData(l);
					lastLog = newLog;
				}
			}			
		}
		catch(Exception e){ Log.e("ERROR", "WIFI-LOG"); }
	}
	
	private String getWifiConnection(Context context, Intent intent)
	{
		if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
		    NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
		    if(networkInfo.isConnected()) {
		        // Wifi is connected
		        return "True";
		    }
		    else
		    {
		    	return "False";
		    }
		} 
		return "No Data";
	}
	
}
