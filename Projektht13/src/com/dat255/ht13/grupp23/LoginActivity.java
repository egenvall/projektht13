package com.dat255.ht13.grupp23;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        Button button = (Button) findViewById(R.id.btnLogin);
        
        button.setOnClickListener(this);
        registerScreen.setOnClickListener(this);
}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
        
		case R.id.btnLogin: 
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i);
         break;
        
		case R.id.link_to_register:
        	Intent in = new Intent(getApplicationContext(), RegisterActivity.class);
			startActivity(in);
         break;
     }
  }
}
