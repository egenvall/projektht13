package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

public class MapModel {
	static MapModel singletonModel;
	static MapModel getModel(){
		if(singletonModel == null){
			singletonModel = new MapModel();
		}
		return singletonModel;
	}

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

	public MessagePoint getMessagePointById(int id){
		for(MessagePoint msgp : messagePoints){
			if(msgp.getId() == id){
				return msgp;
			}
		}
		return null;
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

	public void RemoveMessagePointById(int id) {
		Iterator<MessagePoint> it = messagePoints.iterator();
		while (it.hasNext()) {
			MessagePoint msgp = it.next();
			if(msgp.getId() == id){
				it.remove();
			}

		}

	}

	public void AddMessageToMessagePoint(int id, Message message) {
		for(MessagePoint msgp : messagePoints){
			if(msgp.getId() == id){
				msgp.AddMessage(message);
				break;
			}
		}
	}
}