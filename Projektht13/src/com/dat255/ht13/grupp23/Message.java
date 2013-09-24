package com.dat255.ht13.grupp23;

public class Message {

	private String text;

	public String getText() {
		return text;
	}
	
	/*
	 * Default constructor.
	 */
	public Message() {
		text = "";
	}
	
	public Message(String text) {
		this.text = text;
	}
	
	/*
	 * Copy constructor.
	 */
	public Message(Message message) {
		this.text = message.getText();
	}
	
}
