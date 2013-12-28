package com.example.loginuse.command;

import java.io.File;

import android.os.Environment;

import com.example.loginuse.configuration.LogConfiguration;
import com.example.loginuse.configuration.LogConstants;
import com.example.loginuse.log.LogFormat;
import com.example.loginuse.soap.SoapFileTask;
import com.example.loginuse.util.Compress;

public class CommandSynchronize extends LogCommand {
	
	public void CommandSynchonize(){
		
	}
	
	@Override
	public boolean internalCondition(){		
		boolean sentToday = (LogConfiguration.LASTSYNCHRONIZEFILE == LogFormat.getCurrentDate());		
		return !sentToday;
	}
	
	@Override
	public boolean internalExecute(){
		try {			
			Compress compress = new Compress(LogConstants.LOG_FOLDER_NAME, LogConfiguration.getInstance().getCurrentDayZip());
			compress.zip();
			if(compress.compressOK()){
				File root = Environment.getExternalStorageDirectory();
				File directory = new File(root,LogConstants.LOG_SENT_FOLDER_NAME);
				File file = new File(directory,LogConfiguration.getInstance().getCurrentDayZip());
		
				new SoapFileTask(file,compress).execute();
			}
			else
					return false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}
}
