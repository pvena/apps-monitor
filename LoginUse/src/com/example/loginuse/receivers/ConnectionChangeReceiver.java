package com.example.loginuse.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;
import com.example.loginuse.util.BatteryStatusUtil;

public class ConnectionChangeReceiver extends BroadcastReceiver implements IReceiver{
	
	private IntentFilter filter;
	
	public ConnectionChangeReceiver(){
		filter = new IntentFilter();
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
		LsLog  l = new LsLog(BatteryStatusUtil.getLog(context),LogTags.Battery_Tag);
		SaveLog.getInstance().saveData(l);	
		
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
		String info;
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			info = "DATA TRANSMISSION CONNECTED (3G/GSM)";
		}else {
			info = "DATA TRANSMISION DISCONNECTED (3G/GSM)";
			
		}
		LsLog log = new LsLog(info,LogTags.Connection_Tag);
		SaveLog.getInstance().saveData(log);
		
	}

}
