package com.example.loginuse.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LsLog {
	private String message;
	private String type;
	private String separator;
	
	public LsLog (String mess, String t)
	{
		this.message = "[" + mess + "]";
		this.type = "[" + t + "]";
		this.separator = "&";
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
	 * TODO Mejorar logeo de fechas para saber cuando es am o pm
	 * @return
	 */
	public String getLog()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("[yyyy-MM-dd|kk:mm:ss]");
		return sdf.format(new Date()).concat(this.separator).concat(this.type).concat(this.separator).concat(this.message).concat("\r\n");
	}
}