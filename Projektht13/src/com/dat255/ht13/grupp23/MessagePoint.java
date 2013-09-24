package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

public class MessagePoint {

	private ArrayList<Message> messages;
	private Point position;

	/**
	 * Returns a deep copy off the messages list.
	 * @return copy of messages.
	 */
	public ArrayList<Message> getMessages() {
		ArrayList<Message> messagesCopy = new ArrayList<Message>();
		Iterator<Message> iterator = messages.iterator();
		while (iterator.hasNext()) {
			messagesCopy.add(new Message(iterator.next()));
		}
		return messagesCopy;
	}

	/**
	 * Returns a copy off the position.
	 * @return copy of position.
	 */
	public Point getPosition() {
		return new Point(position);
	}

	public void AddMessage(Message message) {
		messages.add(message);
	}

	/*
	 * Default constructor.
	 */
	public MessagePoint() {
		this.messages = new ArrayList<Message>();
		this.position = new Point(0, 0);
	}

	public MessagePoint(ArrayList<Message> messages, Point position) {
		this.messages = messages;
		this.position = position;
	}
	
	/*
	 * Copy constructor.
	 */
	public MessagePoint(MessagePoint messagePoint) {
		this.messages = messagePoint.getMessages();
		this.position = messagePoint.getPosition();
	}

}
