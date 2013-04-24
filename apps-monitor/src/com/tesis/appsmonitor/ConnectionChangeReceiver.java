package com.tesis.appsmonitor;

import com.tesis.appsmonitor.util.BatteryStatusUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConnectionChangeReceiver extends BroadcastReceiver {
	
	private static final String TAG = "3G";
	private static final String  BATTERY_STATUS =  "BATTERY_STATUS: ";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			Log.d(TAG, "CONNECTED " + BATTERY_STATUS + BatteryStatusUtil.getBatteryPercentage(context)
					+ " " + BatteryStatusUtil.getBatteryStatus(context));
		}else {
			Log.d(TAG, "DISCONNECTED " + BATTERY_STATUS + BatteryStatusUtil.getBatteryPercentage(context)
					+ " " + BatteryStatusUtil.getBatteryStatus(context));
			
		}
	}
}