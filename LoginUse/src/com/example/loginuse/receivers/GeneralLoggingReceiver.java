package com.example.loginuse.receivers;

import java.util.Calendar;

import com.example.loginuse.command.LogCommandManager;
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
	private static String lastLogBattery;
	private String lastLog;
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
	
	protected void save(LogLine l){
		if (!l.getMessage().equals(lastLog))
		{
			LogSave.getInstance().saveData(l);
			lastLog = l.getMessage();
		}
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
		
		LogLine l = BatteryStatusUtil.getBatteryStatusLine(context);		
		if (!l.getMessage().equals(lastLogBattery))
		{
			LogSave.getInstance().saveData(l);
			lastLogBattery = l.getMessage();
		}
		
		/*
		 * Set current day status in LogCommandManager
		 * */
		
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
		this.save(this.line);
		
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
