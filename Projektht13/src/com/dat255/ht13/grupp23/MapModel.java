package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Iterator;

public class MapModel {

	private ArrayList<MessagePoint> messagePoints;
	private int lastId;

	private void addMessageToMessagePoints(Message message,int id){
	    //inserts a message to a messagepoint.
		boolean found = false;
	    MessagePoint mp=getMP(id);
	    if(mp==null)
	        return;        	
	    
	    Date after=new Date(0);
	    for(Message mess: mp.getMessages()){
	    	if(mess.getDate().after(after)){
	    		after=mess.getDate();
	    	}
	    }
	    if(message.getDate().after(after))
	    	mp.AddMessage(message);	    
	}
	
	
	
	
	private MessagePoint getMP(int id) {
	    //returns a MessagePoint without calling the Server
		Iterator<MessagePoint> it = messagePoints.iterator();
		while (it.hasNext()) {
			MessagePoint msgp = it.next();
			if (msgp.getId() == id) {
				return msgp;
			}
		}
		return null;
	}
	
	private void updateMPsWithMessages(){
	    DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost("http://web.student.chalmers.se/~wajohan/mongodb/getmessages.php");
		InputStream inputStream = null;
		String result = null;
        try {
		    HttpResponse response = httpclient.execute(httppost);           
		    HttpEntity entity = response.getEntity();

		    inputStream = entity.getContent();
		    // json is UTF-8 by default
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		    StringBuilder sb = new StringBuilder();
		    String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		        sb.append(line + "\n");
		    }
		    result = sb.toString();
			JSONObject jObject = new JSONObject(result);
			JSONArray jArray = jObject.getJSONArray("messages");
			
			for (int i=0; i < jArray.length(); i++)
			{
			
			    JSONObject oneObject = jArray.getJSONObject(i);

		        sb.append(line + "\n");
		    
			    // Pulling items from the array
			        
			    String message = oneObject.getString("message");
			    Date date = new Date(Long.parseLong(oneObject.getString("time"))*1000);
			    int id = Integer.parseInt(oneObject.getString("mpid"));
			    Message newMessage = new Message(message,date);
			    addMessageToMessagePoints(newMessage,id);
			}			
			
		}catch (Exception e) { 
		    // Oops
		}
	}
	
    private void updateMPs(){
        //This method updates the messagePoints list with all the MessagePoints on the server side
        DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost("http://web.student.chalmers.se/~wajohan/mongodb/getmessagepoints.php");
		InputStream inputStream = null;
		String result = null;
		try {
		    HttpResponse response = httpclient.execute(httppost);           
		    HttpEntity entity = response.getEntity();
		    inputStream = entity.getContent();
		    // json is UTF-8 by default
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		    StringBuilder sb = new StringBuilder();
		    String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		        sb.append(line + "\n");
		    }
		    result = sb.toString();
			JSONObject jObject = new JSONObject(result);
			JSONArray jArray = jObject.getJSONArray("messagepoints");
		    for (int i=0; i < jArray.length(); i++)
			{			
			    JSONObject oneObject = jArray.getJSONObject(i);
		        sb.append(line + "\n");		    
			    // Pulling items from the array			        
			    int id = Integer.parseInt(oneObject.getString("mpid"));
			    double x = Double.parseDouble(oneObject.getString("x"));
			    double y = Double.parseDouble(oneObject.getString("y"));
			    
			    if(getMP(id)==null){ //then we have to create a new messagePoint and add it to the list.
			        Point point = new Point(x,y);
			        MessagePoint newMessagePoint = MessagePoint(new ArrayList<Message>,point,id);
			        messagePoint.add(newMessagePoint);
			        if(lastID<id)
			            lastID=id;	        
			    }		    
			}
	    }catch (Exception e) { 
		    // Oops
		}
		updateMPsWithMessages();     
    }
    


	public ArrayList<MessagePoint> getMessagePoints() {
	    updateMPs(); //update from server
    	ArrayList<MessagePoint> messagePointsCopy = new ArrayList<MessagePoint>();
		Iterator<MessagePoint> iterator = messagePoints.iterator();
		while (iterator.hasNext()) {
			messagePointsCopy.add(new MessagePoint(iterator.next()));
		}
		return messagePointsCopy;
	}

	public MessagePoint getMessagePointById(int id) {
		//This method updates ONE MessagePoint and returns it 
		updateMPs();
		
		return getMP(int id);
	}
	
	public MapModel() {
		messagePoints = new ArrayList<MessagePoint>();
		lastId = -1;
	}

	/**
	 * Add a MessagePoint to position.
	 * 
	 * @param position
	 *            the position of the MessagePoint.
	 */
	public void AddMessagePoint(Point position) {
		lastId++;
		messagePoints.add(new MessagePoint(new ArrayList<Message>(), position,
				lastId));
	}

	/**
	 * Remove the MessagePoint with the matching id.
	 * 
	 * @param id
	 *            the id of the MessagePoint to be removed.
	 */
	public void RemoveMessagePointById(int id) {
		Iterator<MessagePoint> it = messagePoints.iterator();
		while (it.hasNext()) {
			MessagePoint msgp = it.next();
			if (msgp.getId() == id) {
				it.remove();
				break;
			}
		}
	}

	/**
	 * Add a Message to the MessagePoint with the correct id.
	 * 
	 * @param id
	 *            the id of the MessagePoint that the Message should be added
	 *            to.
	 * @param message
	 *            the Message to be added to the MessagePoint.
	 */
	public void AddMessageToMessagePoint(int id, Message message) {
		Iterator<MessagePoint> it = messagePoints.iterator();
		while (it.hasNext()) {
			MessagePoint msgp = it.next();
			if (msgp.getId() == id) {
				msgp.AddMessage(message);
				break;
			}
		}
	}
}
