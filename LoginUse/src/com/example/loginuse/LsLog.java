package com.example.loginuse;

import java.text.DateFormat;
import java.util.Date;

public class LsLog {
	private String message;
	private String type;
	
	public LsLog (String mess, String t)
	{
		this.message = mess;
		this.type = t;
	}
	
	public String getLog()
	{
		return DateFormat.getDateTimeInstance().format(new Date()).concat("-").concat(this.type).concat(this.message);
	}
}
