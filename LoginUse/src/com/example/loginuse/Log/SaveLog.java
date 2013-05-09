package com.example.loginuse.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.example.loginuse.receivers.LogTags;
import com.example.loginuse.util.BatteryStatusUtil;

import android.content.Context;
import android.os.Environment;

public class SaveLog {
		private static SaveLog instance;
	
		private SaveLog()
		{
			LsLog sl = new LsLog("Inic Log", "Inicio");
			this.saveData(sl);
		}		
		
		public static SaveLog getInstance()
		{
			if (SaveLog.instance == null)
				SaveLog.instance = new SaveLog();
			return SaveLog.instance;
		}
		
		public void saveData(LsLog log)
		{
			File file;
	        File root = Environment.getExternalStorageDirectory();
	        try {	        	
	         	file = new File(root,LogTags.LogFileName);
	            if(!file.exists()) {	                        
	              	file.createNewFile();
	            }
	            Writer out = new BufferedWriter(new FileWriter(file, true), 1024);
	            out.write(log.getLog());
	            out.close();   
			}catch(IOException e)
			{e.printStackTrace();}        
		}
		
		public void saveDataWithBatteryStatus(String message, String type, Context context){
			String batteryStatus = BatteryStatusUtil.getLog(context);			
			String newMessage = message.concat(batteryStatus);
			saveData(new LsLog(newMessage, type));
		}
		
		public String readLogFile(){
			StringBuilder text = new StringBuilder();

			try {
				File root = Environment.getExternalStorageDirectory();

				File file = new File(root,LogTags.LogFileName);
			    BufferedReader br = new BufferedReader(new FileReader(file));
			    String line;

			    while ((line = br.readLine()) != null) {
			        text.append(line);
			        text.append('\n');
			    }
			    br.close();
			}
			catch (IOException e) {
			    //You'll need to add proper error handling here
			}

			return text.toString();
		}
}
