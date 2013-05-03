package com.example.loginuse.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.loginuse.Log.SaveLog;

public class ConnectionChangeReceiver extends BroadcastReceiver implements IReceiver{
	
	private IntentFilter filter;
	
	private static final String TAG = "DATA CONNECTION";
	
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
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
		String info;
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			info = "DATA TRANSMISSION CONNECTED (3G/GSM)";
		}else {
			info = "DATA TRANSMISION DISCONNECTED (3G/GSM)";
			
		}
		SaveLog.getInstance().saveDataWithBatteryStatus(info, TAG, context);
		
	}

}
