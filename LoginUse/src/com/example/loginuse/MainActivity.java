package com.example.loginuse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {
	private Button btn;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.btn = (Button) findViewById(R.id.btnIniciar);
        this.btn.setOnClickListener(this);
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
    
    public void onClick(View src) {
        switch (src.getId()) {
        case R.id.btnIniciar:
        		startService(new Intent(this,  MyService.class));
        		break;
        case R.id.btnSuspender:            
            	stopService(new Intent(this, MyService.class));
            	break;
          }      
    }
}
