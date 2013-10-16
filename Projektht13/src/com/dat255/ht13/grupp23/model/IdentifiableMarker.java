package com.dat255.ht13.grupp23.model;

import com.google.android.gms.maps.model.Marker;
/**
 * @copyright (c) 2013 Kim Egenvall, Carl Fredriksson, Jonas Ha, Johan Wallander, Timocin Zaynal,Josef Haddad
 * @license MIT
 *
 */
public class IdentifiableMarker {

	Marker marker;
	int id;
	
	public IdentifiableMarker(Marker marker, int id) {
		this.marker = marker;
		this.id = id;
	}
	
	public Marker getMarker() {
		return marker;
	}
	
	public int getId() {
		return id;
	}
	
}
