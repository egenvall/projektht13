package com.dat255.ht13.grupp23.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.dat255.ht13.grupp23.MapModel;
import com.dat255.ht13.grupp23.Message;
import com.dat255.ht13.grupp23.MessagePoint;
import com.dat255.ht13.grupp23.Point;

public class MapModelTest extends TestCase{

	MapModel model;
	
	Point point1;
	Point point2;
	
	Message msg1;
	Message msg2;
	
	ArrayList<MessagePoint> msgPoints;
	public MapModelTest() {
	}

	@Override
	@Before
	public void setUp() throws Exception {
		model = new MapModel();
		point1 = new Point(11.11,11.11);
		point2 = new Point(22.22,22.22);
		msg1 = new Message("Test1");
		msg2 = new Message("Test2");
	}

	@Test
	public void testGetMessagePoints() {
		model.AddMessagePoint(point1);
		assertEquals(0, model.getMessagePoints().size());
	}

	@Test
	public void testAddMessagePoint_msg() {
		model.AddMessagePoint(point1);
		model.AddMessagePoint(point2);
		assertTrue(model.getMessagePoints() instanceof ArrayList);
		assertEquals(2, model.getMessagePoints().size());
	}
	@Test
	public void testRemoveMessagePoint() {
		model.AddMessagePoint(point1);
		model.AddMessagePoint(point2);
		assertEquals(2, model.getMessagePoints().size());
		
		model.RemoveMessagePointById(1);
		assertEquals(1, model.getMessagePoints().size());
	}
	@Test
	public void testAddMessageToMessagePoint() {
		model.AddMessagePoint(point1);
		model.AddMessagePoint(point2);
		model.AddMessageToMessagePoint(0, msg1);
		model.AddMessageToMessagePoint(1, msg2);
		assertTrue(model.getMessagePoints() instanceof ArrayList);
		assertEquals(2, model.getMessagePoints().size());
		assertEquals(msg1, model.getMessagePointById(0).getMessages().get(0));
	}
}