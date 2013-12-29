package com.example.loginuse.command;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.example.loginuse.configuration.LogConfiguration;

public class LogCommandWifiDisabled extends LogCommand {

	@Override
	public boolean internalCondition() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean internalExecute() {
		Context context = LogConfiguration.getInstance().getContext();
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	 	if(wifiManager.isWifiEnabled()){
	 		wifiManager.setWifiEnabled(false);
	 		return true;
	 	}
		return false;
	}	
}