package com.dat255.ht13.grupp23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class Model {

	private ArrayList<MessagePoint> messagePoints;

	/**
	 * Returns a deep copy off the messagePoints list.
	 * 
	 * @return copy of messagePoints.
	 */
	public ArrayList<MessagePoint> getMessagePoints() {
		DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost("http://web.student.chalmers.se/~wajohan/klotter/getjson.php");
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
			        
			    // Pulling items from the array
			        
			    String message = oneObject.getString("message");
			    Date date = new Date(Long.parseLong(oneObject.getString("time"))*1000);
			    double x = Double.parseDouble(oneObject.getString("x"));
			    double y = Double.parseDouble(oneObject.getString("y"));
			    Point point = new Point(x,y);
			    Message newMessage = new Message(message,date);
			    addMessageToMessagePoints(newMessage,point);
			    
			}
		} catch (Exception e) { 
		    // Oops
		}
		finally {
		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}
			
		
		ArrayList<MessagePoint> messagePointsCopy = new ArrayList<MessagePoint>();
		Iterator<MessagePoint> iterator = messagePoints.iterator();
		while (iterator.hasNext()) {
			messagePointsCopy.add(new MessagePoint(iterator.next()));
		}
		return messagePointsCopy;
	}
	
	private void addMessageToMessagePoints(Message message,Point point){
		boolean found = false;
	    for(MessagePoint mp : messagePoints){
	    	if(mp.getPosition().getX()==point.getX() && mp.getPosition().getY() == point.getY()){
	    		found=true;
	    		Date after=new Date(0);
	    		for(Message mess: mp.getMessages()){
	    			if(mess.getDate().after(after)){
	    				after=mess.getDate();
	    			}
	    		}
	    		if(message.getDate().after(after))
	    			mp.AddMessage(message);
	    		break;
	    	}
	    }
	    if(!found){ //Add a new messagePoint
	    	ArrayList<Message> messageList = new ArrayList<Message>();
	    	messageList.add(message);			    	
	    	MessagePoint newMessagePoint = new MessagePoint(messageList,point);
	    	messagePoints.add(newMessagePoint);
	    	
	    }
	    
	}
	
	public MessagePoint getMessagePoint(Point point){
		MessagePoint returPoint=null;
		for (MessagePoint mp: messagePoints){
			if(point.getX()==mp.getPosition().getX() && point.getY()==mp.getPosition().getY()){
				returPoint=mp;
				break;
			}
		}
		return returPoint;
	}
	
	public void addMessageToPoint(Message message, Point point){
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://web.student.chalmers.se/~wajohan/klotter/postmessage.php");
		
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("x", String.valueOf(point.getX())));
	        nameValuePairs.add(new BasicNameValuePair("y", String.valueOf(point.getY())));
	        nameValuePairs.add(new BasicNameValuePair("message",message.getText()));	        
	        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
	        // Execute HTTP Post Request
	        HttpResponse response = httpClient.execute(httpPost);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	    addMessageToMessagePoints(message,point);
	}
	/*
	 * Get MessagePoints from database etc.
	 */
	public Model() {
		messagePoints = new ArrayList<MessagePoint>();
	}

	public void AddMessagePoint(MessagePoint messagePoint) {
		messagePoints.add(messagePoint);
	}

	public void AddMessagePoint(Point point) {
		messagePoints.add(new MessagePoint(new ArrayList<Message>(), point));
	}

	public void RemoveMessagePoint(MessagePoint messagePoint) {

	}

	public void RemoveMessagePoint(Point point) {

	}

}
