package com.dat255.ht13.grupp23.model;

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

import android.os.AsyncTask;
/**
 * @copyright (c) 2013 Kim Egenvall, Carl Fredriksson, Jonas Ha, Johan Wallander, Timocin Zaynal,Josef Haddad
 * @license MIT
 *
 */
public class MapModel {

	private ArrayList<MessagePoint> messagePoints;
	private int lastId;
	
	public MapModel() {
		messagePoints = new ArrayList<MessagePoint>();
		lastId = -1;
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
	
	private MessagePoint getMPcustom(int id,ArrayList<MessagePoint> list) {
	    //returns a MessagePoint without calling the Server
		Iterator<MessagePoint> it = list.iterator();
		while (it.hasNext()) {
			MessagePoint msgp = it.next();
			if (msgp.getId() == id) {
				return msgp;
			}
		}
		return null;
	}
	
    public void updateMPs(){
    	new UpdateMPsAsync().execute();
    }
    private class UpdateMPsAsync extends AsyncTask<Object,Void,Void>{
		@Override
		protected Void doInBackground(Object... args){
		   	ArrayList<MessagePoint> newMessagePoints=new ArrayList<MessagePoint>();
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
				    
				    MessagePoint newMessagePoint = new MessagePoint(new ArrayList<Message>(),new Point(x,y),id);
				    newMessagePoints.add(newMessagePoint);
				    if(lastId<id)
				    	lastId=id;	        
				    		    
				}
			    
		    }catch (Exception e) { 
			    // Oops
		    	System.out.println("fetching of MessagePoints failed");
			}
			
		    httpclient = new DefaultHttpClient(new BasicHttpParams());
			httppost = new HttpPost("http://web.student.chalmers.se/~wajohan/mongodb/getmessages.php");
			inputStream = null;
			result = null;
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
				    String name = oneObject.getString("name");
				    Date date = new Date(Long.parseLong(oneObject.getString("time"))*1000);
				    int id = Integer.parseInt(oneObject.getString("mpid"));
				    Message newMessage = new Message(name,message,date);
				    getMPcustom(id,newMessagePoints).AddMessage(newMessage);
				}			
				
			}catch (Exception e) { 
			    // Oops
				System.out.println("Fetching of messages failed");
			}
			
			
			messagePoints=newMessagePoints;
			return null;
		}
		

	}
	public ArrayList<MessagePoint> getMessagePoints() {
    	ArrayList<MessagePoint> messagePointsCopy = new ArrayList<MessagePoint>();
		Iterator<MessagePoint> iterator = messagePoints.iterator();
		while (iterator.hasNext()) {
			messagePointsCopy.add(new MessagePoint(iterator.next()));
		}
		return messagePointsCopy;
	}

	public MessagePoint getMessagePointById(int id) {
		return getMP(id);
	}
	

	/**
	 * Add a MessagePoint to position.
	 * 
	 * @param position
	 *            the position of the MessagePoint.
	 */
	
	
	public void AddMessagePoint(Point position) {
		lastId++;
		MessagePoint newMP = new MessagePoint(new ArrayList<Message>(), position, lastId);
		messagePoints.add(newMP);
		new AddMessagePointAsync().execute(newMP);
			
	}
	private class AddMessagePointAsync extends AsyncTask<MessagePoint,Void,Void>{
		@Override
		protected Void doInBackground(MessagePoint... params){
			MessagePoint newMP = params[0];
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://web.student.chalmers.se/~wajohan/mongodb/postmessagepoint.php");
			
		    try {
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("mpid", String.valueOf(newMP.getId())));
		        System.out.println(" newMP X " + newMP.getPosition().getX() + " newMP Y "+newMP.getPosition().getY());
		        nameValuePairs.add(new BasicNameValuePair("y",String.valueOf(newMP.getPosition().getY())));	        	        
		        nameValuePairs.add(new BasicNameValuePair("x",String.valueOf(newMP.getPosition().getX())));
		        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
		        // Execute HTTP Post Request
		        httpClient.execute(httpPost);	        
		    } catch (ClientProtocolException e) {
		    	System.out.println("adding of messagepoint failed ClientProtocolException");
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		    	System.out.println("ioException");
		    }
		    return null;
		}

		
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
		MessagePoint messagePoint = getMP(id);
		messagePoint.AddMessage(message);
		new AddMessageToMessagePointAsync().execute(id,message);
	}
        
	private class AddMessageToMessagePointAsync extends AsyncTask<Object,Void,Void>{
		@Override
		protected Void doInBackground(Object... args){
			int id = ((Integer)args[0]).intValue();
			Message message = (Message)args[1];
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://web.student.chalmers.se/~wajohan/mongodb/postmessage.php");
			
		    try {
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("mpid", String.valueOf(id)));
		        nameValuePairs.add(new BasicNameValuePair("name", message.getName()));
		        nameValuePairs.add(new BasicNameValuePair("message",message.getText()));	        
		        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
		        // Execute HTTP Post Request
		        httpClient.execute(httpPost);
		      
		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    	System.out.println("uploading message failed clientProtocolExceptino");
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		    	System.out.println("uploading message failed ioexception");
		    }
			return null;
		}

	}
}
