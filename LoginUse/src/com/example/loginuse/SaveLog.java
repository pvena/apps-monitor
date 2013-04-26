package com.example.loginuse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class SaveLog {

		public SaveLog(Context cnt)
		{
			String start = DateFormat.getDateTimeInstance().format(new Date()).concat("-").concat("Inic Log");
			LsLog sl = new LsLog(start, "Inicio");
			this.saveData(sl);
		}		
		
		public void saveData(LsLog log)
		{
			File file;
	        File root = Environment.getExternalStorageDirectory();
	        FileWriter nmea_writer = null;
	        try {	        	
	         	file = new File(root,"LoguinUse.txt");
	            if(!file.exists()) {	                        
	              	file.createNewFile();
	            }
	            nmea_writer = new FileWriter(file);
	            nmea_writer.append(log.getLog());
	            nmea_writer.flush();
	            nmea_writer.close();				
			}catch(IOException e)
			{e.printStackTrace();}	
	        finally 
            {
                if (nmea_writer != null) 
                {
                    try 
                    {
                        nmea_writer.close();
                    } 
                    catch (IOException e)
                    { e.printStackTrace();}
                }
            }
		}		
}
