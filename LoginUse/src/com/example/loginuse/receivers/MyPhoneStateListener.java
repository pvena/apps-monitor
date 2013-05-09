package com.example.loginuse.receivers;

import com.example.loginuse.Log.LsLog;
import com.example.loginuse.Log.SaveLog;

import android.content.Context;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

public class MyPhoneStateListener extends PhoneStateListener implements IReceiver{
	private IntentFilter filter;
	private TelephonyManager   tel;   
	
	public MyPhoneStateListener(Context context)
	{
		this.filter = new IntentFilter();
		
		this.tel = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		this.tel.listen(this ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
	}
	
	@Override
	public IntentFilter getFilter(){
		return this.filter; 
	}
	
	@Override
	public void addAction(String action){	
		this.filter.addAction(action);
	}
	
	/* Get the Signal strength from the provider, each tiome there is an update */
	@Override
	public void onSignalStrengthsChanged(SignalStrength signalStrength)
	{
		String signal = String.valueOf(signalStrength.getGsmSignalStrength());
		LsLog l = new LsLog("Signal State: " + signal, LogTags.Signal_State);
		SaveLog.getInstance().saveData(l);
	}
}
