package com.dat255.ht13.grupp23.activites;

import com.dat255.ht13.grupp23.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @copyright (c) 2013 Kim Egenvall, Carl Fredriksson, Jonas Ha, Johan Wallander, Timocin Zaynal,Josef Haddad
 * @license MIT
 *
 */
public class MainActivity extends FragmentActivity {
	final int RQS_GooglePlayServices = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	 @Override
	 protected void onResume() {
	  // TODO Auto-generated method stub
	  super.onResume();

	  int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
	  
	  if (resultCode == ConnectionResult.SUCCESS){
		  setContentView(R.layout.logoscreen);
			Thread splashThread = new Thread() {
				@Override
				public void run() {
					try {
						int waited = 0;
						while (waited < 4000) {
							sleep(100);
							waited += 100;
						}
					} catch (InterruptedException e) {
						// do nothing
					} finally {
						finish();
						Intent mapIntent = new Intent(getApplicationContext(),
								MapController.class);
						startActivity(mapIntent);
						finish();
					}
				}
			};
			splashThread.start();
	  }else{
		  GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices).show();
	  }
	 }
}

