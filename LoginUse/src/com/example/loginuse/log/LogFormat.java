package com.example.loginuse.log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import android.util.Base64;
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
	
	public static String fileToArray(File file) throws IOException {
        byte[] data = new byte[3000];
        FileInputStream fin = null;
        StringBuffer sb = new StringBuffer();
        try {
            fin = new FileInputStream(file);
            while(fin.read(data) >= 0) {
               sb.append(Base64.encode(data,Base64.DEFAULT));
           }
            return sb.toString();
        } catch (Exception e) {
            // TODO: handle exception
        } finally{
            fin.close();
        }
        return null;
    }	
}
