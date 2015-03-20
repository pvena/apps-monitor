package com.example.loginuse;

import java.util.ArrayList;

import com.example.loginuse.adapter.CommandListAdapter;
import com.example.loginuse.command.LogCommandManager;
import com.example.loginuse.rule.LogRuleData;

import android.app.ListActivity;
import android.os.Bundle;

public class CurrentRulesActivity extends ListActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
       
        this.loadRules();
    }
    
    @Override
	protected void onResume() {
		this.loadRules();
		super.onResume();
	}
    
    private void loadRules(){  
    	
    	ArrayList<LogRuleData> rules = LogCommandManager.getInstance().getCurrentRules();

        setListAdapter(new CommandListAdapter(this,rules,R.layout.current_rule_item));
    }
}
