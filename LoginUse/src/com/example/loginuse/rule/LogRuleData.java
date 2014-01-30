package com.example.loginuse.rule;

public class LogRuleData {
	private String name;
	private String conditionsData;
	
	public LogRuleData(String n,String c){
		this.name = n;
		this.conditionsData = c;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String n){
		this.name = n;
	}
	public String getConditionsData(){
		return this.conditionsData;
	}
	public void setConditionsData(String c){
		this.conditionsData = c;
	}
	
}
