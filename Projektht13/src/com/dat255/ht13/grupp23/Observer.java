package com.dat255.ht13.grupp23;

public interface Observer {

	public void update(EventType eventType, Point position);
	
	public void update(EventType eventType, int id);

}
