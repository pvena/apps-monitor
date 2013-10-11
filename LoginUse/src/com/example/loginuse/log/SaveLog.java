package com.example.loginuse.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.example.loginuse.util.LogConstants;

import android.os.Environment;

public class SaveLog {
		private static SaveLog instance;
	
		private SaveLog(){
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
	        	File directory = new File(root,LogConstants.LOG_FOLDER_NAME);
	        	// have the object build the directory structure, if needed.
	        	directory.mkdirs();
	         	file = new File(directory,LogConstants.LOG_FILE_NAME);
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

				File file = new File(root,LogConstants.LOG_FILE_NAME);
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
