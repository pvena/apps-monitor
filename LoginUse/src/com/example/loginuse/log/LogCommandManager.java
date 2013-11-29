package com.example.loginuse.log;

import java.util.Hashtable;

import com.example.loginuse.command.command;



public class LogCommandManager {
	private command[] commands;
	private Hashtable<String, String> lastLogState; 
	
	public LogCommandManager(){
		this.lastLogState = new Hashtable<String, String>();
		this.commands = new command[0];
	}
	
	public void newState(String key,String value){
		this.lastLogState.put(key, value);
		this.executeCommands();
	}
	
	public void executeCommands(){
		for(int i=0; i< this.commands.length ; i++)
			if(this.commands[i].canExecute(this.lastLogState))
				this.commands[i].execute();
	}
	
}
