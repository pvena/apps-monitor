package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.loginuse.log.LsLog;
import com.example.loginuse.log.SaveLog;
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
		    	String info = "Enabled ";
		    	WifiInfo wifiInfo = intent.getParcelableExtra("wifiInfo");
		    	info += getNetInfo(wifiInfo);
		        return info;
		    }
		    else
		    {
		    	return "Disabled";
		    }
		} 
		return "No Data";
	}
	
	/**
	 * Return a <code>String </code> with
	 * relevant wifi info
	 * @param wifiInfo
	 * @return
	 */
	private String getNetInfo(WifiInfo wifiInfo){
		String netInfo = "Connected to: ";
		netInfo += "SSID|" + wifiInfo.getSSID();
		netInfo += " MAC|" + wifiInfo.getMacAddress();
		netInfo += " IP|" + wifiInfo.getIpAddress();
		netInfo += " NetID|" + wifiInfo.getNetworkId();
		netInfo += " BSSID|" + wifiInfo.getBSSID();
		return netInfo;
	}
	
}
