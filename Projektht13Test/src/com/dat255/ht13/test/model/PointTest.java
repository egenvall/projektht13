package com.dat255.ht13.test.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.dat255.ht13.grupp23.model.Point;

public class PointTest extends TestCase {

	Point point1;
	Point point2;
	
	Point setX_Y;
	
	Point copyPoint;
	
	Point equalsSymmetric_1;
	Point equalsSymmetric_2;
	Point equalsSymmetric_3;
	
	public PointTest() {
	}

	@Override
	@Before
	public void setUp() throws Exception {
		point1 = new Point(11.11,22.22);
		point2 = new Point(33.33, 44.44);
		
		setX_Y = new Point(0.0, 0.0);
		
		copyPoint = new Point(point1);
		equalsSymmetric_1 = new Point(11.11, 22.22);
		equalsSymmetric_2 = new Point(11.11, 22.22);
		equalsSymmetric_3 = new Point(11.22, 22.11);
		
	}

	@Test
	public void testCon() {
		assertTrue(point1 instanceof Point);
	}
	
	@Test
	public void testCopyCon() {
		assertEquals(point1, copyPoint);
	}
	
	@Test
	public void testGetX() {
		assertEquals(11.11, point1.getX());
		assertEquals(33.33, point2.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(22.22, point1.getY());
		assertEquals(44.44, point2.getY());
	}
	
	@Test
	public void testSetX() {
		assertEquals(22.22, point1.getY());
		assertEquals(44.44, point2.getY());
	}
	
	@Test
	public void testSetY() {
		setX_Y.setX(12.12);
		setX_Y.setY(12.12);
		
		assertEquals(12.12, setX_Y.getX());
		assertEquals(12.12, setX_Y.getY());
		
	}
	
	@Test
	public void testEquals_Symmetric() {
		assertTrue(equalsSymmetric_1.equals(equalsSymmetric_2) && equalsSymmetric_2.equals(equalsSymmetric_2));
		assertTrue(equalsSymmetric_1.hashCode() == equalsSymmetric_2.hashCode());
		
		assertFalse(equalsSymmetric_1.equals(equalsSymmetric_3) && equalsSymmetric_2.equals(equalsSymmetric_3));
		assertNotSame(equalsSymmetric_1.hashCode(), equalsSymmetric_3.hashCode());
	}
}