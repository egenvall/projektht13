package com.dat255.ht13.grupp23.util;
/**
 * @copyright (c) 2013 Kim Egenvall, Carl Fredriksson, Jonas Ha, Johan Wallander, Timocin Zaynal,Josef Haddad
 * @license MIT
 *
 */
import com.dat255.ht13.grupp23.model.Point;

public interface Observer {

	public void update(EventType eventType, Point position);
	
	public void update(EventType eventType, int id);

}
