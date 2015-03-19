package com.example.loginuse.receivers;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;

import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.log.LogLine;
import com.example.loginuse.log.LogSave;
import com.example.loginuse.util.BatteryStatusUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * General class structure for logging receivers
 *
 */
public abstract class GeneralLoggingReceiver extends BroadcastReceiver {
	
	protected IntentFilter filter;
	private LogLine line;
	protected String logType;
	
	/**
	 * Creator
	 */
	public GeneralLoggingReceiver(){
		this.filter = new IntentFilter();		
	}
	
	/**
	 * @return IntentFilter filter
	 */
	public IntentFilter getFilter(){
		return filter;
	}
	
	protected LogLine getLogLine(){
		return new LogLine(this.logType); 
	}
	
	private void saveAndsetCommandState(LogLine l){
		boolean change = false;
		String property = null;
		String value = null;
		Hashtable<String, String> properties = l.getProperties();
		Enumeration<String> enumKey = properties.keys();		
		while(enumKey.hasMoreElements()) {		    
			property = enumKey.nextElement();
		    value = properties.get(property);		    
		    change |= LogCommandManager.getInstance().newState(l.getType(), property, value);
		}	
		if(change)
			LogSave.getInstance().saveData(l);
	}
	
	protected void save(LogLine l){
		this.saveAndsetCommandState(l);
	}
	
	/**
	 * @param String action a new Intent action to match against.
	 */
	public void addAction(String action){
		filter.addAction(action);
	}

	private void setGlobalStatus(Context context){
		
		/*
		 * Set current Battery Status in LogFile and LogCommandManager
		 * */
		
		Intent i = new Intent();
		i.setAction("RecognitionActivity");
		i.putExtra(LogConstants.ACTIVITY,"Pepe");
		LogConfiguration.getInstance().getContext().sendBroadcast(i);
		
		this.saveAndsetCommandState(BatteryStatusUtil.getBatteryStatusLine(context));
		
		Calendar c = Calendar.getInstance();
		
		int hour = c.get(Calendar.HOUR_OF_DAY);		
		int quarter = c.get(Calendar.MINUTE) / 15;
		boolean isWeekDay = (c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY); 
		
		LogCommandManager.getInstance().newState(LogConstants.CURRENTDATE_TAG, LogConstants.HOUR, LogFormat.getValue(hour));
		LogCommandManager.getInstance().newState(LogConstants.CURRENTDATE_TAG, LogConstants.QUARTER, LogFormat.getValue(quarter));
		LogCommandManager.getInstance().newState(LogConstants.CURRENTDATE_TAG, LogConstants.ISWEEKDAY, LogFormat.getValue(isWeekDay));
	}
	
	/**
	 * Log the incoming events 
	 */
	@Override
	public void onReceive(Context context, Intent intent) {		
		this.setGlobalStatus(context);		
		this.line = new LogLine(this.logType);
		this.logEvent(context, intent,this.line);
		this.saveAndsetCommandState(this.line);
		
		LogCommandManager.getInstance().executeCommands();		
	}
	
	/**
	 * Save the incoming events in a log file
	 * @param context
	 * @param intent
	 */
	public abstract void logEvent(Context context, Intent intent,LogLine l);
	public abstract void initialize();
	public abstract void finalize();
	
	
}
