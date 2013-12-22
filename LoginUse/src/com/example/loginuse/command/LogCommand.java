package com.example.loginuse.command;

public abstract class LogCommand {
	
	private int executeCount = 0;
	private int okCount = 0;
	
	protected int minPercent = 90;
	protected int minExecuteCount = 20;
	
 	public abstract boolean internalExecute();
	public abstract boolean internalCondition();

	public LogCommand(){}
	
	public boolean execute(){
		if(this.internalCondition() && this.userAcept())
			return this.internalExecute();
		return false;
	}
	
	private boolean userAcept(){		
		if((this.executeCount > this.minExecuteCount) && (100 * this.okCount / this.executeCount > this.minPercent))
			return true;
		else{			
			this.executeCount++;
			// Preguntar al usuario si acepta el comando y en caso de ok entonces okCount++.
			return true;
		}
	}
	
	public void setExecuteCount(int count){
		this.executeCount = count;
	}
	
	public void setOkCount(int count){
		this.okCount = count;
	}
}
