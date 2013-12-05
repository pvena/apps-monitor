package com.example.loginuse.command;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class LogCommand {
	
	private Hashtable<String, String> rules;
	
	public abstract boolean execute();

	public LogCommand(){
		this.rules = new Hashtable<String, String>();
	}
	
	public boolean canExecute(Hashtable<String, String> state){
		Enumeration<String> keys= this.rules.keys();
		String key = null;
		while (keys.hasMoreElements()){
			key = keys.nextElement();
			if(!this.rules.get(key).equals(state.get(key)))
				return false;
		}			
		return true;
	}	
}
