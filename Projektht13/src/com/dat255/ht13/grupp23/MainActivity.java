package com.dat255.ht13.grupp23;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity{
	Controller controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		finish();
		controller = Controller.getInstance();
		controller.initiate(this);
		
	}

}
