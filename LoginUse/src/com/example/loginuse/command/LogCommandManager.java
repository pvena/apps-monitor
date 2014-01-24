package com.example.loginuse.command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import com.example.loginuse.command.LogCommand;
import com.example.loginuse.rule.LogRule;
import com.example.loginuse.rule.LogRuleManager;

public class LogCommandManager {
	private Hashtable<String, LogCommand> commands;
	private Hashtable<String, String> lastLogState; 
	private ArrayList<LogRule> rules;
	
	private static LogCommandManager instance;
	
	private LogCommandManager(){
		this.lastLogState = new Hashtable<String, String>();
		this.buildRules();
		this.buildCommands();
	}
	
	public static LogCommandManager getInstance()
	{			
		if(LogCommandManager.instance != null){			
			return LogCommandManager.instance;
		}
		LogCommandManager.instance = new LogCommandManager();
		return LogCommandManager.instance;
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
	/*	 
	 * Create all LogCommands in Command HashTable
	 */
	private void buildCommands(){
		this.commands = new Hashtable<String, LogCommand>();		
		this.commands.put("SynchLogFile", new LogCommandSynchronize());
		this.commands.put("WifiEnabled", new LogCommandWifiEnabled());
		this.commands.put("WifiDisabled", new LogCommandWifiDisabled());
		this.commands.put("BlueToothEnabled", new LogCommandBlueToothEnabled());
		this.commands.put("BlueToothDisabled", new LogCommandBlueToothDisabled());
		this.commands.put("ConnectionEnabled", new LogCommandConnectionEnabled());
		this.commands.put("ConnectionDisabled", new LogCommandConnectionDisabled());
	}
	
	public void addLogCommandRule(LogRule rule){
		if(rule != null)
			this.rules.add(rule);
	}
	
	public LogCommand getCommand(String key){
		if(this.commands.containsKey(key))
			return this.commands.get(key);
		return null;
	}
	
	public void newState(String key,String value){
		this.lastLogState.put(key, value);		
	}
	
	public void executeCommands(){
		String commandKey = "";
		LogRule rule = null;
		for(int i=0; i< this.rules.size() ; i++){
			rule = this.rules.get(i);
			commandKey = rule.getCommendKey();			
			if(this.commands.containsKey(commandKey) && rule.validate()){			
				this.commands.get(commandKey).execute();
			}
		}
	}
	
	public String getValue(String key){
		if(this.lastLogState.containsKey(key))
			return this.lastLogState.get(key);
		return "NULL";
	}
}
