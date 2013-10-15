package com.dat255.ht13.grupp23.util;

import com.dat255.ht13.grupp23.model.Point;

public interface Observer {

	public void update(EventType eventType, Point position);
	
	public void update(EventType eventType, int id);

}
