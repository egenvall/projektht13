package com.dat255.ht13.grupp23.activites;

import com.dat255.ht13.grupp23.R;
import com.dat255.ht13.grupp23.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	}

	
}

