package com.dat255.ht13.grupp23;

public class Point {

	private float x;
	private float y;

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	/*
	 * Default constructor.
	 */
	public Point() {
		
	}
	
	/*
	 * Copy constructor.
	 */
	public Point(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}

}
