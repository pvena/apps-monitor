package com.example.loginuse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import android.content.Context;
import android.os.Environment;

public class SaveLog {

		public SaveLog(Context cnt)
		{
			LsLog sl = new LsLog("Inic Log", "Inicio");
			this.saveData(sl);
		}		
		
		public void saveData(LsLog log)
		{
			File file;
	        File root = Environment.getExternalStorageDirectory();
	        try {	        	
	         	file = new File(root,"LoguinUse.txt");
	            if(!file.exists()) {	                        
	              	file.createNewFile();
	            }
	            Writer out = new BufferedWriter(new FileWriter(file, true), 1024);
	            out.write(log.getLog());
	            out.close();   
			}catch(IOException e)
			{e.printStackTrace();}        
		}	
}
