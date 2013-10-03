package com.example.loginuse.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.example.loginuse.util.Constants;
import com.example.loginuse.util.LogFormat;

import android.os.Environment;

public class SaveLog {
		private static SaveLog instance;
	
		private SaveLog()
		{
			String log = LogFormat.getLog("INIC_LOG",true);
			LsLog sl = new LsLog(log, Constants.INICIO);
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
	         	file = new File(root,Constants.LOG_FILE_NAME);
	            if(!file.exists()) {	                        
	              	file.createNewFile();
	            }
	            Writer out = new BufferedWriter(new FileWriter(file, true), 1024);
	            out.write(log.getLog());
	            out.close();   
			}catch(IOException e)
			{e.printStackTrace();}        
		}
		
		public String readLogFile(){
			StringBuilder text = new StringBuilder();

			try {
				File root = Environment.getExternalStorageDirectory();

				File file = new File(root,Constants.LOG_FILE_NAME);
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
