package com.example.loginuse.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.loginuse.Configuration.LogConfiguration;
import com.example.loginuse.Configuration.LogConstants;
import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;

public class WifiReceiver extends GeneralLoggingReceiver  {
	
	/**
	 * Creator
	 */
	private static String lastLog;
	
	public WifiReceiver(){
		
		filter = new IntentFilter();
		
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
	public void logEvent(Context context, Intent intent) {
		try
		{
			NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if(networkInfo.getType() == ConnectivityManager. TYPE_WIFI){
				String newLog = this.getWifiConnection(context,intent);
				if(!newLog.equals(lastLog))
				{
					LogLine l = new LogLine(newLog, LogConstants.WIFI_STATE_TAG);
					LogSave.getInstance().saveData(l);
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
		    WifiManager wifiManager = (WifiManager)LogConfiguration.getInstance().getContext().getSystemService(Context.WIFI_SERVICE);
		    WifiInfo wifiInfo = intent.getParcelableExtra("wifiInfo");
		    return getNetInfo(wifiInfo,wifiManager.isWifiEnabled(),networkInfo.isConnected());		    
		} 
		return "ND";
	}
	
	/**
	 * Return a <code>String </code> with
	 * relevant wifi info
	 * @param wifiInfo
	 * @return
	 */
	private String getNetInfo(WifiInfo wifiInfo,boolean state,boolean connected){
		String netInfo = "";
		netInfo += LogFormat.getLog(LogConstants.STATE, state);
		LogCommandManager.getInstance().newState(LogConstants.WIFI_STATE_TAG + "-", LogFormat.getValue(state));
		
		String SSID = (connected)?LogFormat.getValue(wifiInfo.getSSID()):"-";
		String MacAddress = (connected)?LogFormat.getValue(wifiInfo.getMacAddress()):"-";
		String IpAddress = (connected)? LogFormat.getValue(wifiInfo.getIpAddress()):"-";
		String NetworkId = (connected)?LogFormat.getValue(wifiInfo.getNetworkId()):"-";
		String BSSID = (connected)?LogFormat.getValue(wifiInfo.getBSSID()):"-";
		
		netInfo += LogFormat.getLog(LogConstants.SSID, SSID);
		netInfo += LogFormat.getLog(LogConstants.MAC,MacAddress);
		netInfo += LogFormat.getLog(LogConstants.IP,IpAddress);
		netInfo += LogFormat.getLog(LogConstants.NetID,NetworkId);
		netInfo += LogFormat.getLog(LogConstants.BSSID,BSSID);
			
		LogCommandManager.getInstance().newState(LogConstants.WIFI_STATE_TAG + "-" + LogConstants.SSID,SSID);
		LogCommandManager.getInstance().newState(LogConstants.WIFI_STATE_TAG + "-" + LogConstants.MAC, MacAddress);
		LogCommandManager.getInstance().newState(LogConstants.WIFI_STATE_TAG + "-" + LogConstants.IP, IpAddress);
		LogCommandManager.getInstance().newState(LogConstants.WIFI_STATE_TAG + "-" + LogConstants.NetID,NetworkId);
		LogCommandManager.getInstance().newState(LogConstants.WIFI_STATE_TAG + "-" + LogConstants.BSSID, BSSID);		
			
		return netInfo;
	}
	
}
