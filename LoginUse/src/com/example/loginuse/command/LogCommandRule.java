package com.example.loginuse.command;

import java.util.Enumeration;
import java.util.Hashtable;

public class LogCommandRule {
	protected Hashtable<String, String> conditions;
	
	private String CommandKey;
	
	public LogCommandRule(String CKey){
		this.CommandKey = CKey;
		this.conditions = new Hashtable<String, String>();		
	}
	
	public void addCondition(String key,String value){
		this.conditions.put(key, value);			
	}
	
	public String getCommendKey(){
		return this.CommandKey;
	}
	
	public boolean validate(){
		Enumeration<String> keys= this.conditions.keys();
		String key = null;
		while (keys.hasMoreElements()){
			key = keys.nextElement();
			if(!this.conditions.get(key).equals(LogCommandManager.getInstance().getValue(key)))
				return false;
		}			
		return true;
	}
}