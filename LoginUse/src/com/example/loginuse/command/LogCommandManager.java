package com.example.loginuse.command;

import java.util.Hashtable;

import com.example.loginuse.command.LogCommand;

public class LogCommandManager {
	private LogCommand[] commands;
	private Hashtable<String, String> lastLogState; 
	
	private static LogCommandManager instance;
	
	private LogCommandManager(){
		this.lastLogState = new Hashtable<String, String>();
		this.commands = new LogCommand[0];
	}
	
	public static LogCommandManager getInstance()
	{			
		if(LogCommandManager.instance != null){			
			return LogCommandManager.instance;
		}
		LogCommandManager.instance = new LogCommandManager();
		return LogCommandManager.instance;
	}
	
	public void newState(String key,String value){
		this.lastLogState.put(key, value);		
	}
	
	public void executeCommands(){
		for(int i=0; i< this.commands.length ; i++)
			if(this.commands[i].canExecute(this.lastLogState))
				this.commands[i].execute();
	}
	
}
