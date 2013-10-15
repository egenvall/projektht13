package com.dat255.ht13.grupp23.util;

import com.dat255.ht13.grupp23.model.Point;

public interface Subject {

	public void addObserver(Observer observer);

	public void removeObserver(Observer observer);
	
	public void notifyObservers(EventType eventType, int id);
	
	public void notifyObservers(EventType eventType, Point position);
	
}
