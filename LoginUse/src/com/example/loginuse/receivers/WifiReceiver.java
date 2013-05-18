package com.example.loginuse.receivers;

import java.util.List;

import com.example.loginuse.Log.LogTags;
import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.BatteryStatusUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiReceiver extends BroadcastReceiver implements IReceiver  {
	private IntentFilter filter;
	
	public WifiReceiver(){
		
		filter = new IntentFilter();
		
		this.filter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);		
		this.filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);		
	}
	
	@Override
	public IntentFilter getFilter(){
		return filter;
	}
	
	@Override
	public void addAction(String action){
		filter.addAction(action);
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		try
		{
			LsLog  l = new LsLog(BatteryStatusUtil.getLog(context),LogTags.Battery_Tag);
			SaveLog.getInstance().saveData(l);			
			l = new LsLog(this.getCurrentSsid(context), LogTags.WifiState_Tag);
			SaveLog.getInstance().saveData(l);
		}
		catch(Exception e){ Log.e("ERROR", "WIFI-LOG"); }
	}
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
		    
		    // Get WiFi status MARAKANA
		    /*
		    WifiInfo info = wifiManager.getConnectionInfo();
		    String textStatus = "";
		    textStatus += "\n\nWiFi Status: " + info.toString();
		    String BSSID = info.getBSSID();
		    String MAC = info.getMacAddress();
		    */	
		    List<ScanResult> results = wifiManager.getScanResults();
		    //ScanResult bestSignal = null;
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
