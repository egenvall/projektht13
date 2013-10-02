package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

public class MessagePoint {

	private ArrayList<Message> messages;
	private Point position;
	private int id;

	/**
	 * Returns a deep copy off the messages list.
	 * 
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
	 * 
	 * @return copy of position.
	 */
	public Point getPosition() {
		return new Point(position);
	}

	public int getId() {
		return id;
	}

	public void AddMessage(Message message) {
		messages.add(message);
	}

	/*
	 * Default constructor.
	 */
	public MessagePoint(int id) {
		this.messages = new ArrayList<Message>();
		this.position = new Point(0, 0);
		this.id = id;
	}

	public MessagePoint(ArrayList<Message> messages, Point position, int id) {
		this.messages = messages;
		this.position = position;
		this.id = id;
	}

	/*
	 * Copy constructor.
	 */
	public MessagePoint(MessagePoint messagePoint) {
		this.messages = messagePoint.getMessages();
		this.position = messagePoint.getPosition();
		this.id = messagePoint.getId();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof MessagePoint) {
			MessagePoint other = (MessagePoint) object;
			if (this.messages.equals(other.getMessages())
					&& this.position.equals(other.getPosition())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((messages == null) ? 0 : messages.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

}
