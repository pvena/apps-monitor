package com.example.loginuse.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.loginuse.LsLog;
import com.example.loginuse.SaveLog;
import com.example.loginuse.util.BatteryStatusUtil;

public class ConnectionChangeReceiver extends BroadcastReceiver implements IReceiver{
	
	private IntentFilter filter;
	private SaveLog log;
	private static final String  BATTERY_STATUS =  "BATTERY_STATUS: ";
	
	public ConnectionChangeReceiver(){
		filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
	}

	@Override
	public IntentFilter getFilter() {
		return filter;
	}

	@Override
	public void addAction(String action) {
		filter.addAction(action);
		
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
		String info;
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			info = "CONNECTED " + BATTERY_STATUS + BatteryStatusUtil.getBatteryPercentage(context)
					+ " " + BatteryStatusUtil.getBatteryStatus(context);
		}else {
			info = "DISCONNECTED " + BATTERY_STATUS + BatteryStatusUtil.getBatteryPercentage(context)
					+ " " + BatteryStatusUtil.getBatteryStatus(context);
			
		}
		log.saveData(new LsLog(intent.getAction(), info));
		
	}

}
