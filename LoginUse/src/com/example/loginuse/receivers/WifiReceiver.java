package com.example.loginuse.receivers;

import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.BatteryStatusUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

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
		LsLog  l = new LsLog(BatteryStatusUtil.getLog(context),LogTags.Battery_Tag);
		SaveLog.getInstance().saveData(l);	
		if(WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction()))
		{
			l = new LsLog("Wifi State: " + intent.getStringExtra(WifiManager.EXTRA_WIFI_STATE), LogTags.WifiState_Tag);
			SaveLog.getInstance().saveData(l);			
		}
		if(WifiManager.NETWORK_IDS_CHANGED_ACTION.equals(intent.getAction()))
		{
			WifiManager wifiMgr = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
			l = new LsLog("Wifi Change Ids" + wifiInfo.getSSID(), LogTags.WifiName_Tag);
			SaveLog.getInstance().saveData(l);
		}
	}
}
