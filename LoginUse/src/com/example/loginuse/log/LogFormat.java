package com.example.loginuse.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import android.widget.CheckBox;
import android.widget.TextView;


public class LogFormat {
	
	public static String getLog(String property,String value)
	{
		return property + LogConstants.SEP  + value + LogConstants.PSEP;
	}
	public static String getLog(String property,int value)
	{
		return property + LogConstants.SEP + value + LogConstants.PSEP;
	}
	public static String getLog(String property,double value)
	{
		return property + LogConstants.SEP + value + LogConstants.PSEP;
	}
	public static String getLog(String property,boolean value)
	{
		return property + LogConstants.SEP + ((value)?"1":"0") + LogConstants.PSEP;
	}	
	public static String getCategory(String value)
	{
		return LogConstants.CATSEP1 + value + LogConstants.CATSEP2 + LogConstants.CATSEP;
	}
	public static String getLogCategories(String value)	
	{
		String exc = LogConstants.PSEP + LogConstants.CATSEP2 + LogConstants.CATSEP; 
		return value.replace(exc, LogConstants.CATSEP2);
	}
	public static int getTextViewInt(TextView tv){
		return Integer.parseInt(tv.getText().toString());
	}
	public static boolean getCheckBoxBoolean(CheckBox cbx){
		return cbx.isChecked();
	}
	
	public static byte[] fileToArray(File file) throws IOException {
        // Open file
        RandomAccessFile f = new RandomAccessFile(file, "r");
        try {
            // Get and check length
            long longlength = f.length();
            int length = (int) longlength;
            if (length != longlength)
                throw new IOException("File size >= 2 GB");
            // Read file and return data
            byte[] data = new byte[length];
            f.readFully(data);
            return data;
        } finally {
            f.close();
        }
    }
	
	public static byte[] getFileToBytes(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        is.close();
        return bytes;
    }
}
