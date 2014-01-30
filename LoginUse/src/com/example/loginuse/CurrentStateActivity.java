package com.example.loginuse;

import java.util.ArrayList;
import java.util.Collections;

import com.example.loginuse.command.LogCommandManager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CurrentStateActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_state_activity);
        
        this.loadStates();
    }
	
	@Override
	protected void onResume() {
		this.loadStates();
		super.onResume();
	}
	
	private void loadStates(){
		ListView listView1 = (ListView) findViewById(R.id.listViewState);
        
        ArrayList<String> states = LogCommandManager.getInstance().getCurrentStates();
        Collections.sort(states);
        		
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.current_state_item, states);
        
        listView1.setAdapter(adapter);
	}
}
