package com.dat255.ht13.grupp23.test.model;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.dat255.ht13.grupp23.model.MapModel;
import com.dat255.ht13.grupp23.model.Message;
import com.dat255.ht13.grupp23.model.MessagePoint;
import com.dat255.ht13.grupp23.model.Point;

public class MapModelTest extends TestCase{

	MapModel model;
	
	Point point1;
	Point point2;
	Point spacePoint;
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
	    spacePoint = new Point(151251,1251251);
	    spaceMessage = new Message("I'm posting this from outher space"); //spacePoint and spaceMessage are intended to be pushed to the database without ever interfering with any position on earth (or in reality)
		msg1 = new Message("Test1");
		msg2 = new Message("Test2");
	}
	
    @Test
    public void testAddMessagePoint(){
        model.AddMessagePoint(point1);
        model.AddMessagePoint(point2);
        assertEquals(2,model.getMessagePoints().size())
    }
    
	
	@Test
	public void testGetMessagePoints() {
		model.AddMessagePoint(point1);
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
	
	@Test(timeout=15)
	public void testDatabaseConnection(){ //test connections to the database
	    int inititalMPSize = model.getMessagePoints().size();
	    model.updateMP();
        while(model.getMessagePoints().size() == initialMPSize); // loop until size changes or until timeout
	    int initialSize = model.getMessagePoints().size();
	    model.addMessagePoint(spacePoint);
	    //find the id for that point;
	    ArrayList<MessagePoint> messagePoints = model.getMessagePoints();
	    MessagePoint messagePoint = null;
	    for(MessagePoint mp : messagePoints){
	        if(mp.getPosition.getX() == spacePoint.getX() && mp.getPosition.getY() == spacePoint.getY()){
	            messagePoint = mp;
	            break;
	        }
	    }
	    int id = messagePoint.getId();
	    model.addMessageToMessagePoint(id,spaceMessage);
	    Thread.sleep(2000);
        //Test if we can download the same data to a new model
        MapModel newModel = new MapModel();
        assertEquals(newModel.getMessagePoints.size(),0); //assert that it is empty
        inititalMPSize = newModel.getMessagePoints().size();
	    newModel.updateMP();
        while(newModel.getMessagePoints().size() == initialMPSize); // loop until size changes or until timeout
        MessagePoint spaceMessagePoint=newModel.getMessagePointById(id);
        assertEquals( spaceMessagePoint.getPosition().getX(),spacePoint.getX());
        assertEquals(spaceMessagePoint.getPosition().getY(),spacePoint.getY());
        Message message = spaceMessagePoint.getMessages().get(0);
        assertEquals(message.getText(),spaceMessage.getText());
 
	    
	
	}
}
