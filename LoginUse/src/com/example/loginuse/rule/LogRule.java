package com.example.loginuse.rule;

import java.util.Enumeration;
import java.util.Hashtable;

import com.example.loginuse.command.LogCommandManager;

public class LogRule {
	protected Hashtable<String, String> conditions;
	
	private String CommandKey;
	
	public LogRule(String CKey){
		this.CommandKey = CKey;
		this.conditions = new Hashtable<String, String>();		
	}
	
	public void addCondition(String key,String value){
		this.conditions.put(key, value);			
	}
	
	public String getCommendKey(){
		return this.CommandKey;
	}
	public LogRuleData getRuleData(){
		Enumeration<String> keys= this.conditions.keys();
		String key = null;
		String val = null;
		String list = "";		
		while (keys.hasMoreElements()){
			key = keys.nextElement();
			val = this.conditions.get(key);
			list += "(" + key + "=" + val + ")";
		}			
		return new LogRuleData(CommandKey, list);
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
