package com.example.loginuse.log;

//import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.loginuse.util.LogConstants;
import com.example.loginuse.util.LogFormat;

public class LsLog {
	private String message;
	private String type;
		
	public LsLog (String mess, String t)
	{
		this.message = mess;
		this.type = t;
	}
	public String getMessage()
	{
		return this.message;
	}
	public String getType()
	{
		return this.type;
	}
	/**
	 * @return
	 */
	//@SuppressLint("SimpleDateFormat")
	public String getLog()
	{
		String date =  new SimpleDateFormat(LogConstants.DATEFORMAT).format(new Date());
		return   LogFormat.getLogCategories(LogFormat.getCategory(date)
				.concat(LogFormat.getCategory(this.type))
				.concat(LogFormat.getCategory(this.message)))
				.concat("\r\n");
	}
}