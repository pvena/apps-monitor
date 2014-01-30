package com.example.loginuse.rule;


import java.util.Hashtable;
import com.example.loginuse.configuration.LogConfiguration;

public class LogRuleManager {
	private static LogRuleManager instance;
	private Hashtable<String, LogRule> rules = null; 
	
	private LogRuleManager(){
		
		this.loadRules();
	}
	
	public static LogRuleManager getInstance()
	{			
		if(LogRuleManager.instance != null){			
			return LogRuleManager.instance;
		}
		LogRuleManager.instance = new LogRuleManager();
		return LogRuleManager.instance;
	}
	
	/*
	 * Load Location Groups from configuration (Shared Preferences).
	 * */
	public void loadRules(){
		if(this.rules != null)
			rules.clear();
		else
			this.rules = new Hashtable<String, LogRule>();
		int count = LogConfiguration.getInstance().getProperty(LogConfiguration.RULECOUNT, 0);		
		String id = "";
		String commandKey = "";
		String prop = "";
		String val = "";
		LogRule rule = null;
		for(int i=0; i < count ; i++){
			id = LogConfiguration.getInstance().getProperty(LogConfiguration.IDRULE + i, "-1");
			commandKey = LogConfiguration.getInstance().getProperty(LogConfiguration.COMMANDKEY + i, "Command" + i);
			prop = LogConfiguration.getInstance().getProperty(LogConfiguration.PROPERTYKEY + i, "-");
			val = LogConfiguration.getInstance().getProperty(LogConfiguration.PROPERTYVALUE + i, "-");			
 			if(rules.containsKey(id)){
				rule = rules.get(id);
			}
			else{
				rule = new LogRule(commandKey);
				rules.put(id, rule);
			}
			rule.addCondition(prop, val);
		}
	}
	
	/*
	 * Save LogRules in configuration (Shared Preferences). 
	 * */	
	public void setRules(String RulesData){
		String[] data = RulesData.split("&");
		LogConfiguration.getInstance().setProperty(LogConfiguration.RULECOUNT, data.length / 4);
		for(int i=0;i<(data.length);i+=4){
			LogConfiguration.getInstance().setProperty(LogConfiguration.IDRULE + i/4  , data[i]);
			LogConfiguration.getInstance().setProperty(LogConfiguration.COMMANDKEY + i/4  , data[i+1]);
			LogConfiguration.getInstance().setProperty(LogConfiguration.PROPERTYKEY + i/4 , data[i+2]);
			LogConfiguration.getInstance().setProperty(LogConfiguration.PROPERTYVALUE + i/4 , data[i+3]);
		}
		this.loadRules();
	}	
	
	public Hashtable<String,LogRule> getRules(){
		return this.rules;
	} 
}
