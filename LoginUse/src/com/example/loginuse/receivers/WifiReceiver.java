package com.example.loginuse.receivers;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.Constants;

public class WifiReceiver extends GeneralLoggingReceiver  {
	
	/**
	 * Creator
	 */
	public WifiReceiver(){
		
		filter = new IntentFilter();
		
		this.filter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);		
		this.filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.example.loginuse.receivers.GeneralLoggingReceiver#logEvent(android.content.Context, android.content.Intent)
	 */
	@Override
	public void logEvent(Context context, Intent intent) {
		try
		{
			LsLog l = new LsLog(this.getCurrentSsid(context), Constants.WIFI_STATE_TAG);
			SaveLog.getInstance().saveData(l);
		}
		catch(Exception e){ Log.e("ERROR", "WIFI-LOG"); }
	}
	
	/**
	 * Return the current wifi-network ssid
	 * @param context
	 * @return String ssid
	 */
	private String getCurrentSsid(Context context) {

		  String ssid = null;
		  String etWifiList = null;
		  ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		  if (networkInfo.isConnected()) {
		    final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		    final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
		    if (connectionInfo != null && !(connectionInfo.getSSID().equals(""))) {
		        ssid = connectionInfo.getSSID();
		    }
		    
		    List<ScanResult> results = wifiManager.getScanResults();
		    int count = 1;
		    etWifiList = "No Signal";
		    for (ScanResult result : results) {
		    	etWifiList += "(" + count++ + ". " + 
		    			result.SSID + " : " + 
		    			result.level + "||" +
		    			result.BSSID + "||" + 
		    			result.capabilities + ")";
		    }
		  }
		  if(ssid != null)
			  return ssid;
		  return etWifiList;
		}
}
