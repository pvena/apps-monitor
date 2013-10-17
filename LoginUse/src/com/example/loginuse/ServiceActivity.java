package com.example.loginuse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class ServiceActivity extends Activity {
		
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this.onClick_btn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}	
	
	View.OnClickListener onClick_btn = new View.OnClickListener() {
	    public void onClick(View v) {
	    	btnOnClick();
	    }
	};
	public void btnOnClick(){
		startService(new Intent(this,  MyService.class));	
	}
}
