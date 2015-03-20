package com.example.loginuse.command;

import com.example.loginuse.configuration.LogConfiguration;

import android.content.Context;
import android.net.wifi.WifiManager;

public class LogCommandWifiEnabled extends LogCommand {

	@Override
	public boolean internalCondition() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean internalExecute() {
		try
		{
			Context context = LogConfiguration.getInstance().getContext();
			WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			if(!wifiManager.isWifiEnabled()){
				wifiManager.setWifiEnabled(true);
				return true;
			}
		}
		catch(Exception e){}
		return false;
	}	
}
