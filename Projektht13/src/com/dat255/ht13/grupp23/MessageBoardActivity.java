package com.dat255.ht13.grupp23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView.OnEditorActionListener;
import android.support.v4.app.NavUtils;
import android.text.method.ScrollingMovementMethod;
import android.annotation.TargetApi;
import android.os.Build;

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

public class MessageBoardActivity extends Activity {
	MessagePoint messagePoint;

	protected void sendMessage(String text){
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://web.student.chalmers.se/~wajohan/klotter/postmessage.php");
		Point position = messagePoint.getPosition();
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("x", String.valueOf(position.getX())));
			nameValuePairs.add(new BasicNameValuePair("y", String.valueOf(position.getY())));
			nameValuePairs.add(new BasicNameValuePair("message",text));

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

			// Execute HTTP Post Request
			HttpResponse response = httpClient.execute(httpPost);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}
	private void showMessages(){
		TextView mTextView = (TextView) findViewById(R.id.textView1);
		mTextView.setText("");
		for(Message m : messagePoint.getMessages()){
			mTextView.append(m.getText() + "\n");
		}

	}

	private void updateMP(MessagePoint mp){
		Date after=new Date(0);
		for(Message message : mp.getMessages()){
			if(message.getDate().after(after)){
				after=message.getDate();
			}
		}
		long time = after.getTime()/1000;

		DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost("http://web.student.chalmers.se/~wajohan/klotter/getjson.php?after="+String.valueOf(time)+"&type=point&x="+String.valueOf(messagePoint.getPosition().getX()+"&y="+messagePoint.getPosition().getY()));
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
				messagePoint.AddMessage(new Message(message,date));

			}
		} catch (Exception e) { 
			// Oops
		}
		finally {
			try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_board);
		// Show the Up button in the action bar.
		setupActionBar();
		//messagePoint  = new MessagePoint(new ArrayList<Message>(),new Point(42.0,66.6));
		updateMP(messagePoint);
		showMessages();
		TextView mTextView = (TextView) findViewById(R.id.textView1);
		mTextView.setMovementMethod(new ScrollingMovementMethod());	

		EditText inputText = (EditText) findViewById(R.id.editText1);
		inputText.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int arg1, KeyEvent arg2) {
				boolean handled = false;
				if (arg1 == EditorInfo.IME_ACTION_SEND){
					sendMessage(v.getText().toString());
					updateMP(messagePoint);
					showMessages();
					v.setText("");
					v.clearFocus();
					handled = true;
				}
				return handled;
			}	

		});  
	}
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_board, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
