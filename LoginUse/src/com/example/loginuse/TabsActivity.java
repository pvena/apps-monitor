package com.example.loginuse;


import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabsActivity extends TabActivity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        startService(new Intent(this,  MyService.class));
         
        TabHost tabHost = getTabHost();
         
        TabSpec config = tabHost.newTabSpec("Config.");        
        config.setIndicator("Config.", getResources().getDrawable(R.drawable.ic_config));
        Intent songsIntent = new Intent(this, ConfigActivity.class);
        config.setContent(songsIntent);
        
        TabSpec state = tabHost.newTabSpec("State");        
        state.setIndicator("State.", getResources().getDrawable(R.drawable.ic_in));
        Intent currentStateIntent = new Intent(this, CurrentStateActivity.class);
        state.setContent(currentStateIntent);	
        
        TabSpec rules = tabHost.newTabSpec("Rules");        
        rules.setIndicator("Rules.", getResources().getDrawable(R.drawable.ic_rules));
        Intent commandsIntent = new Intent(this, CurrentRulesActivity.class);
        rules.setContent(commandsIntent);	
        
        TabSpec map = tabHost.newTabSpec("Map");        
        map.setIndicator("Map.", getResources().getDrawable(R.drawable.ic_map));
        Intent mapIntent = new Intent(this, MapActivity.class);
        map.setContent(mapIntent);	
         
        tabHost.addTab(config); 
        tabHost.addTab(state);
        tabHost.addTab(rules);
        tabHost.addTab(map);        
        
        //int back = Color.rgb(254, 254 ,254);
                
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) 
        { 
        	Drawable tabd = (Drawable) getResources ().getDrawable(R.drawable.tab_selector);
            tabHost.getTabWidget().getChildAt(i).setBackgroundDrawable (tabd);
        	//tabHost.getTabWidget().getChildAt(i).setBackgroundColor(back);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#000000"));
            
        }        
   }
}
