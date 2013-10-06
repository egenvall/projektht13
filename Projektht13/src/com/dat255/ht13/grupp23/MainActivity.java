package com.dat255.ht13.grupp23;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent mapIntent = new Intent(getApplicationContext(),
				MapController.class);
		startActivity(mapIntent);
		finish();
	}
}
