package com.dat255.ht13.grupp23;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MapController extends FragmentActivity implements Observer {

	private MapModel mapModel;
	private MapView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_controller);
		mapModel = new MapModel();
		mapView = new MapView(this);
		mapView.addObserver(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_controller, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_addmarker:
			mapView.addMarker();
			break;
		}
		return true;
	}

	@Override
	public void update(EventType eventType, int id) {
		if (eventType == EventType.MarkerClick) {
			System.out.println("Methods to do stuff when a marker is clicked");
			mapModel.AddMessageToMessagePoint(id, new Message("Text"));
			
			Intent msgIntent = new Intent(getApplicationContext(),
					MessageController.class);
			startActivity(msgIntent);
			finish();
		}
	}

	@Override
	public void update(EventType eventType, Point position) {
		if (eventType == EventType.AddMarker) {
			System.out.println("A marker was added at: " + "X: "
					+ position.getX() + "Y: " + position.getY());
			mapModel.AddMessagePoint(position);
			mapView.updateMap(mapModel.getMessagePoints());
		}
	}

}
