package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;

public class WifiReceiver extends GeneralLoggingReceiver  {
	
	public WifiReceiver(){
		super.logType = LogConstants.WIFI_STATE_TAG;
		super.filter = new IntentFilter();
		
		this.filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
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
	public void logEvent(Context context, Intent intent, LogLine l) {
		try
		{
			NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if(networkInfo.getType() == ConnectivityManager. TYPE_WIFI){
				this.getWifiConnection(context,intent,l);				
			}			
		}
		catch(Exception e){ Log.e("ERROR", "WIFI-LOG"); }
	}
	
	private LogLine getWifiConnection(Context context, Intent intent, LogLine l)
	{		
		if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
		    NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
		    WifiManager wifiManager = (WifiManager)LogConfiguration.getInstance().getContext().getSystemService(Context.WIFI_SERVICE);
		    WifiInfo wifiInfo = intent.getParcelableExtra("wifiInfo");
		    return getNetInfo(wifiInfo,wifiManager.isWifiEnabled(),networkInfo.isConnected(),l);		    
		} 
		return null;
	}
	
	/**
	 * Return a <code>String </code> with
	 * relevant wifi info
	 * @param wifiInfo
	 * @return
	 */
	private LogLine getNetInfo(WifiInfo wifiInfo,boolean state,boolean connected,LogLine l){		
		l.addProperty(LogConstants.STATE, state);
		
		String SSID = (connected)?LogFormat.getValue(wifiInfo.getSSID()):"-";
		String MacAddress = (connected)?LogFormat.getValue(wifiInfo.getMacAddress()):"-";
		String IpAddress = (connected)? LogFormat.getValue(wifiInfo.getIpAddress()):"-";
		String IAccess = LogFormat.getValue(connected);
		
		l.addProperty(LogConstants.SSID, SSID);
		l.addProperty(LogConstants.MAC,MacAddress);
		l.addProperty(LogConstants.IP,IpAddress);
		l.addProperty(LogConstants.IACCESS,IAccess);
			
		return l;
	}
	
}
