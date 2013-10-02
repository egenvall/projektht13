package com.dat255.ht13.grupp23;

public interface Subject {

	public void addObserver(Observer observer);

	public void removeObserver(Observer observer);

	public void notifyObservers(EventType eventType);

}
