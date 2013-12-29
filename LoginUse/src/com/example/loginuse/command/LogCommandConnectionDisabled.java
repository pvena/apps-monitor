package com.example.loginuse.command;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.loginuse.configuration.LogConfiguration;

public class LogCommandConnectionDisabled extends LogCommand{

	@Override
	public boolean internalCondition() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean internalExecute() {
		try{			
			final ConnectivityManager conman = (ConnectivityManager) LogConfiguration.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobile = conman.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobile.isConnected()) {
				final Class<?> conmanClass = Class.forName(conman.getClass().getName());
				final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
				iConnectivityManagerField.setAccessible(true);
				final Object iConnectivityManager = iConnectivityManagerField.get(conman);
				final Class<?> iConnectivityManagerClass =     Class.forName(iConnectivityManager.getClass().getName());
				final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
				setMobileDataEnabledMethod.setAccessible(true);
				setMobileDataEnabledMethod.invoke(iConnectivityManager, false);
				return true;
			}
			return false;
		}
		catch(Exception ex){
			return false;
		}
	}
}
