package com.example.loginuse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener{
		
	private final String Iniciar = "Iniciar";
	private final String Detener = "Detener";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.btn); 
        b.setOnClickListener(this);
        
        this.setButtonText(b);
        //finish();
    }
	
	@Override
	public void onClick(View v) {
			switch(v.getId()){
        	case R.id.btn:
             	Button b = (Button)findViewById(R.id.btn);
             	if(b.getText().equals(this.Iniciar) && (!MyService.State))
             	{
             		startService(new Intent(this,  MyService.class));
             		this.setButtonText(b);
             		//b.setText(this.Detener);
             	}
             	/*if(b.getText().equals(this.Detener) && (MyService.State))
             	{
             		stopService(new Intent(this,  MyService.class));
             		this.setButtonText(b);
             		b.setText(this.Iniciar);
             	}*/
             break;	            
			}
	}
	
	private void setButtonText(Button b)
	{
		b.setEnabled(!MyService.State);
		if(!MyService.State)
        {
        	b.setText(this.Iniciar);
        }
        else
        {     
        	b.setText("Servicio Activo.");
        	//b.setText(this.Detener);
        }
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
		Button b = (Button)findViewById(R.id.btn); 
        this.setButtonText(b);
	} 

}
