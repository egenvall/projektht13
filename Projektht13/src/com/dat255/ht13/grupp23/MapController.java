package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

import com.google.android.gms.maps.model.LatLng;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Class in charge of controlling the MVC pattern
 *
 */

public class MapController extends FragmentActivity implements Observer {

	private MapModel mapModel;
	private MapView mapView;
	private double minDistance = 1; // Minimum Distance (SET TO 1 WHILE TESTING)

	/**
	 * OnCreate for MapController. Sets the layout view for the window,
	 * instantiates Model and View and adds Observer
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_controller);
		mapModel = new MapModel();
		mapView = new MapView(this);
		mapView.addObserver(this);
		mapView.updateMap(mapModel.getMessagePoints()); //Another solution? While tilt, the markers disappears..
		LocalBroadcastManager.getInstance(this).registerReceiver(receiver,
				new IntentFilter("bdr"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_controller, menu);
		return true;
	}

	/**
	 * Determines what action to do when an option from the menu is clicked.
	 * 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_addmarker:
			mapView.addMarker();
			break;
		}
		return true;
	}

	/**
	 * Update method for a markerclick. When a marker is clicked - the method
	 * starts a new intent MessageActivity which displays the list of messages
	 * held by this marker
	 * 
	 * @param id
	 *            The ID of the marker that was clicked
	 * @param eventType
	 *            The type of Event passed will always be MarkerClick for this
	 *            method
	 * 
	 */
	@Override
	public void update(EventType eventType, int id) {
		if (eventType == EventType.MarkerClick) {
			if ((mapModel.getMessagePointById(id)).getMessages().size() == 0) {
				mapModel.AddMessageToMessagePoint(id, new Message("Text"));
			}
			Intent msgIntent = new Intent(MapController.this,
					MessageActivity.class);
			msgIntent.putParcelableArrayListExtra("messages", mapModel
					.getMessagePointById(id).getMessages());
			msgIntent.putExtra("msgPID", id);
			startActivity(msgIntent);
			System.out.println("Initiating a MessageActivity");
		}
		// finish();
	}

	/**
	 * Update method to add a marker on the map.
	 * 
	 * @param eventType
	 *            EventType will always be addMarker for this method
	 * @param position
	 *            The position[x,y] to add the marker at
	 * 
	 */

	@Override
	public void update(EventType eventType, Point position) {
		if (eventType == EventType.AddMarker) {
			if(checkIfMarkersAround(position)){
				System.out.println("A marker was added at: " + "X: "
						+ position.getX() + "Y: " + position.getY());
				mapModel.AddMessagePoint(position);
				mapView.updateMap(mapModel.getMessagePoints());
			}else{
				System.out.println("Too close");
			}
		}
	}

	public boolean checkIfMarkersAround(Point position){
		ArrayList<MessagePoint> messagePoints = mapModel.getMessagePoints();
		Iterator<MessagePoint> it = messagePoints.iterator();
		while (it.hasNext()) {
			MessagePoint msgp = it.next();			
			if(calculateDistance(msgp.getPosition(), position) < minDistance){
				return false;
			}
		}
		return true;

	}
	
	public double calculateDistance(Point anotherPosition, Point currentPosition){
		LatLng latlng1 = new LatLng(anotherPosition.getX(),anotherPosition.getX());
		double lat1 = latlng1.latitude;
		double lng1 = latlng1.longitude;
		
		LatLng latlng2 = new LatLng(currentPosition.getX(),currentPosition.getX());
		double lat2 = latlng2.latitude;
		double lng2 = latlng2.longitude;	
		
		double earthRadius = 3958.75;
		double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
				Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = earthRadius * c;

		double meterConversion = 1609;

		return (dist * meterConversion);

	}

	/**
	 * Inner class for receiving data from MessageActivity
	 */

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// Toast.makeText(context,""+intent.getExtras().getInt("addInMsgPID"),
			// Toast.LENGTH_LONG).show();
			// Toast.makeText(context,""+((Message)intent.getExtras().getParcelable("addMessage")).getText(),
			// Toast.LENGTH_LONG).show();

			mapModel.AddMessageToMessagePoint(
					intent.getExtras().getInt("addInMsgPID"), (Message) intent
							.getExtras().getParcelable("addMessage"));
			update(EventType.MarkerClick,
					intent.getExtras().getInt("addInMsgPID"));

		}
	};

	@Override
	protected void onDestroy() {
		// Unregister since the activity is about to be closed.
		LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
		super.onDestroy();
	}
}
