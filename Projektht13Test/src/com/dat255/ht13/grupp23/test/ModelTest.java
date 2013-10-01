package com.dat255.ht13.grupp23.test;


import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.dat255.ht13.grupp23.Message;
import com.dat255.ht13.grupp23.MessagePoint;
import com.dat255.ht13.grupp23.Model;
import com.dat255.ht13.grupp23.Point;

public class ModelTest extends TestCase{

	
	Model model;
	Message message1;
	Message message2;
	
	Message message3;
	Message message4;
	
	ArrayList<Message> msgs1;
	ArrayList<Message> msgs2;
	Point position1;
	Point position2;
	
	MessagePoint msgPoint1;
	MessagePoint msgPoint2;
	
	ArrayList<MessagePoint> msgPoints;
	public ModelTest() {
	}

	@Before
	public void setUp() throws Exception {
		model = new Model();
		
		message1 = new Message("ModelTest");
		message2 = new Message("ModelTest");
		
		message3 = new Message("Model");
		message4 = new Message("Model");
		msgs1 = new ArrayList<Message>();
		msgs1.add(message1);
		msgs1.add(message2);
		
		msgs2 = new ArrayList<Message>();
		msgs2.add(message3);
		msgs2.add(message4);
		
		position1 = new Point(11.11, 11.11);
		position2 = new Point(22.22, 22.22);
		
		msgPoint1 = new MessagePoint(msgs1, position1);
		msgPoint2 = new MessagePoint(msgs2, position2);
		
		model.AddMessagePoint(msgPoint2);
		model.AddMessagePoint(msgPoint2);
		
		msgPoints = new ArrayList<MessagePoint>();
		msgPoints.add(msgPoint1);
		msgPoints.add(msgPoint1);
		
	}

	@Test
	public void testGetMessagePoints() {
		assertFalse(msgPoints.equals(model.getMessagePoints()));
		assertEquals(2, model.getMessagePoints().size());
	}
	@Test
	public void testConstructor() {
	
	}
	@Test
	public void testAddMessagePoint_msgP() {
	
	}
	@Test
	public void testAddMessagePoint_P() {
	
	}
	@Test
	public void testRemoveMessagePoint_msgP() {
	
	}
	@Test
	public void testRemoveMessagePoint_P() {
	
	}	
}
