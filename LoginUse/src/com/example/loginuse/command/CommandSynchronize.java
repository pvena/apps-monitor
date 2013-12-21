package com.example.loginuse.command;

import java.io.File;

import android.os.Environment;

import com.example.loginuse.Configuration.LogConfiguration;
import com.example.loginuse.Configuration.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.soap.SoapFileTask;
import com.example.loginuse.util.Compress;

public class CommandSynchronize extends LogCommand {
	
	public void CommandSynchonize(){
		this.rules.put(LogConstants.WIFI_STATE_TAG + "-" + LogConstants.STATE, "1");		
	}
	
	@Override
	public boolean internalCondition(){
		String pluggedAc = LogConstants.BATTERY_STATE_TAG + "-" + LogConstants.PLUGGED_AC;
		String pluggedUsb = LogConstants.BATTERY_STATE_TAG + "-" + LogConstants.PLUGGED_USB;
		String ipLogKey = LogConstants.WIFI_STATE_TAG + "-" + LogConstants.IP;			
		
		boolean sentToday = (LogConfiguration.LASTSYNCHRONIZEFILE == LogFormat.getCurrentDate());
		boolean internetAccess = (LogCommandManager.getInstance().getValue(ipLogKey) != "-");			
		boolean isCharging =	(LogCommandManager.getInstance().getValue(pluggedAc) == "1") || 
								(LogCommandManager.getInstance().getValue(pluggedUsb) == "1");
		
		return (internetAccess && isCharging && !sentToday);
	}
	
	@Override
	public boolean execute(){
		try {			
			if(	this.internalCondition() ){			
			
				Compress compress = new Compress(LogConstants.LOG_FOLDER_NAME, LogConfiguration.getInstance().getCurrentDayZip());
				compress.zip();
				if(compress.compressOK()){
					File root = Environment.getExternalStorageDirectory();
					File directory = new File(root,LogConstants.LOG_FOLDER_NAME);
					File file = new File(directory,LogConfiguration.getInstance().getCurrentDayZip());
			
					new SoapFileTask(file,compress).execute();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}
}
