package com.example.loginuse.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import android.os.Environment;

public class LogSave {
		private static LogSave instance;
	
		private LogSave(){
		}		
		
		public static LogSave getInstance()
		{
			if (LogSave.instance == null)
				LogSave.instance = new LogSave();
			return LogSave.instance;
		}
		
		public void saveData(LogLine log)
		{
			File file;
	        File root = Environment.getExternalStorageDirectory();
	        try {	        	
	        	File directory = new File(root,LogConstants.LOG_FOLDER_NAME);
	        	// have the object build the directory structure, if needed.
	        	directory.mkdirs();
	         	file = new File(directory,LogConfiguration.getInstance().getFileLogName());
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

				File file = new File(root,LogConfiguration.getInstance().getFileLogName());
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