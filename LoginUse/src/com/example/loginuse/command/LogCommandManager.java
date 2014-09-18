package com.example.loginuse.command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import com.example.loginuse.command.LogCommand;
import com.example.loginuse.rule.LogRule;
import com.example.loginuse.rule.LogRuleData;
import com.example.loginuse.rule.LogRuleManager;

public class LogCommandManager {
	private Hashtable<String, String> lastLogState; 
	private ArrayList<LogRule> rules;
	
	private static LogCommandManager instance;
	
	private LogCommandManager(){
		this.lastLogState = new Hashtable<String, String>();
		this.buildRules();
	}
	
	public static LogCommandManager getInstance()
	{			
		if(LogCommandManager.instance != null){			
			return LogCommandManager.instance;
		}
		LogCommandManager.instance = new LogCommandManager();
		return LogCommandManager.instance;
	}
	
	public void reload(){
		this.buildRules();
	}
	
	/*	 
	 * Create default rules for the App.
	 */
	private void buildRules(){
		this.rules = new ArrayList<LogRule>();		
		LogRule rule = null;				
		Hashtable<String,LogRule> rules = LogRuleManager.getInstance().getRules();
		Enumeration<String> enumKey = rules.keys();
		while(enumKey.hasMoreElements()) {		    
		    rule = rules.get(enumKey.nextElement());
		    this.rules.add(rule);
		}
	}	

	
	public LogCommand getCommand(String key){		
		if(key.equals("SynchLogFile"))		
			return new LogCommandSynchronize();
		if(key.equals("WifiEnabled"))
			return new LogCommandWifiEnabled();
		if(key.equals("WifiDisabled"))
			return new LogCommandWifiDisabled();
		if(key.equals("BlueToothEnabled"))
			return new LogCommandBlueToothEnabled();
		if(key.equals("BlueToothDisabled"))
			return new LogCommandBlueToothDisabled();
		if(key.equals("ConnectionEnabled"))
			return new LogCommandConnectionEnabled();
		if(key.equals("ConnectionDisabled"))
			return new LogCommandConnectionDisabled();
		if(key.equals("BrightnessDisabled"))
			return new LogCommandBrightnessDisable();
		return null;		
	} 
	
	public void addLogCommandRule(LogRule rule){
		if(rule != null)
			this.rules.add(rule);
	}
	
	public void newState(String key,String value){
		this.lastLogState.put(key, value);		
	}
	
	public ArrayList<String> getCurrentStates(){
		ArrayList<String> states = new ArrayList<String>();		
		Enumeration<String> enumKey = this.lastLogState.keys();
		
		String state = null;
		String key = null;
		while(enumKey.hasMoreElements()) {
			key = enumKey.nextElement();
			state = key + " : ";
	    	state += this.lastLogState.get(key);
	    	states.add(state);
		} 
		return states;
	}
	
	public ArrayList<LogRuleData> getCurrentRules(){
		ArrayList<LogRuleData> list = new ArrayList<LogRuleData>();		
		for(int i=0; i< this.rules.size() ; i++){
			list.add(this.rules.get(i).getRuleData());
		}
		return list;
	}
	
	public void executeCommands(){
		String commandKey = "";
		LogRule rule = null;
		for(int i=0; i< this.rules.size() ; i++){
			rule = this.rules.get(i);
			commandKey = rule.getCommendKey();			
			if(rule.validate()){
				LogCommand cmd = getCommand(commandKey);
				if(cmd != null)
				 cmd.execute();
			}
		}
	}
	
	public String getValue(String key){
		if(this.lastLogState.containsKey(key))
			return this.lastLogState.get(key);
		return "NULL";
	}
}
