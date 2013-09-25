package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

public class View implements Subject {

	private ArrayList<Observer> observers;

	/*
	 * Show MessagePoints on map etc.
	 */
	public View() {
		observers = new ArrayList<Observer>();
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		Iterator<Observer> iterator = observers.iterator();
		while (iterator.hasNext()) {
			iterator.next().update();
		}
	}

}
