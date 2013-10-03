package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

public class MapModel {

	private ArrayList<MessagePoint> messagePoints;
	private int lastId;

	/**
	 * Returns a deep copy off the messagePoints list.
	 * 
	 * @return copy of messagePoints.
	 */
	public ArrayList<MessagePoint> getMessagePoints() {
		ArrayList<MessagePoint> messagePointsCopy = new ArrayList<MessagePoint>();
		Iterator<MessagePoint> iterator = messagePoints.iterator();
		while (iterator.hasNext()) {
			messagePointsCopy.add(new MessagePoint(iterator.next()));
		}
		return messagePointsCopy;
	}

	/*
	 * Get MessagePoints from database etc.
	 */
	public MapModel() {
		messagePoints = new ArrayList<MessagePoint>();
		lastId = -1;
	}

	public void AddMessagePoint(Point position) {
		lastId++;
		messagePoints.add(new MessagePoint(new ArrayList<Message>(), position,
				lastId));
	}

	public void RemoveMessagePoint(int id) {
		// TODO
	}

	public void AddMessageToMessagePoint(int id, Message message) {
		// Find messagePoint by id and AddMessage(message)
	}

}
