package com.example.loginuse.command;

import java.util.ArrayList;
import java.util.Hashtable;

import com.example.loginuse.command.LogCommand;
import com.example.loginuse.configuration.LogConstants;

public class LogCommandManager {
	private Hashtable<String, LogCommand> commands;
	private Hashtable<String, String> lastLogState; 
	private ArrayList<LogCommandRule> rules;
	
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
		this.rules = new ArrayList<LogCommandRule>();
		
		LogCommandRule rule = new LogCommandRule("SynchLogFile");
		rule.addCondition(LogConstants.WIFI_STATE_TAG + "-" + LogConstants.STATE, "1");
		rule.addCondition(LogConstants.WIFI_STATE_TAG + "-" + LogConstants.IACCESS, "1");
		rule.addCondition(LogConstants.BATTERY_STATE_TAG + "-" + LogConstants.DISCHARGING, "0");		
	}	
	/*	 
	 * Create all LogCommands in Command HashTable
	 */
	private void buildCommands(){
		this.commands = new Hashtable<String, LogCommand>();		
		this.commands.put("SynchLogFile", new CommandSynchronize());
		this.commands.put("WifiEnabled", new LogCommandWifiEnabled());
		this.commands.put("WifiDisabled", new LogCommandWifiDisabled());
		this.commands.put("BlueToothEnabled", new LogCommandBlueToothEnabled());
		this.commands.put("BlueToothDisabled", new LogCommandBlueToothDisabled());
		this.commands.put("ConnectionEnabled", new LogCommandConnectionEnabled());
		this.commands.put("ConnectionDisabled", new LogCommandConnectionDisabled());
	}
	
	public void addLogCommandRule(LogCommandRule rule){
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
		LogCommandRule rule = null;
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
