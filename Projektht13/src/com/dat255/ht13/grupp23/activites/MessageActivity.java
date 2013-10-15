package com.dat255.ht13.grupp23.activites;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.dat255.ht13.grupp23.R;
import com.dat255.ht13.grupp23.R.id;
import com.dat255.ht13.grupp23.R.layout;
import com.dat255.ht13.grupp23.R.menu;
import com.dat255.ht13.grupp23.model.Message;
import com.dat255.ht13.grupp23.model.ParcelableMessage;
import com.dat255.ht13.grupp23.util.CustomListViewAdapter;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MessageActivity extends Activity {


	private CustomListViewAdapter adapter;
	private ArrayList<ParcelableMessage> messages;
	private EditText inputName;
	private EditText inputMessage;
	private String name;
	private String text;
	private Date date;
	private int msgPID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		msgPID = getIntent().getExtras().getInt("msgPID");
		//PUshtest
		// Input name
		inputName = (EditText) findViewById(R.id.inputName);
		// Input message
		inputMessage = (EditText) findViewById(R.id.inputMessage);
		// Post Button
		Button post = (Button) findViewById(R.id.postMessage);

		createAndShowMessageList();

		//Click event for POST button 
		post.setOnClickListener(new View.OnClickListener() {   
			@Override
			public void onClick(View view) {
				addMsgToMsgPoint();
			}
		});
	}

	public class ListViewItem{

		public int ThumbNailResource;
		public String Name;
		public String Title;
		public String SubTitle;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_controller, menu);
		return true;
	}

	public void createAndShowMessageList(){
		messages = getIntent().getParcelableArrayListExtra("messages");
		System.out.print("There is: " + messages.size() + "messages in this marker");

		ListView lv = (ListView)findViewById(R.id.list);
		List<ListViewItem> items = new ArrayList<ListViewItem>();

		for(Message msg : messages){
			name = msg.getName();
			text = msg.getText();
			date = msg.getDate();
			items.add(new ListViewItem()
			{{
				ThumbNailResource = 1;
				Name = name;
				Title = new SimpleDateFormat("MMMM d 'at' h:mm a").format(date);
				SubTitle = text;
			}});
		}
		Collections.reverse(items);
		adapter = new CustomListViewAdapter(this, items);        
		lv.setAdapter(adapter);

		// Click event for single list row
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});	
	}
	private void addMsgToMsgPoint(){
		String inputNameString = inputName.getText().toString();
		if(inputNameString.length() > 10){
			Toast.makeText(getApplicationContext(), "Your name is too long [Max 10 chars]", Toast.LENGTH_LONG).show();
		}
		String inputMessageString = inputMessage.getText().toString();
		if(inputMessageString.length() < 3){
			Toast.makeText(getApplicationContext(), "Your message is too short [Min 3 chars]", Toast.LENGTH_LONG).show();
		}else{
			Intent msgAddIntent = new Intent("bdr");
			msgAddIntent.putExtra("addMessage", new ParcelableMessage(inputNameString ,inputMessageString));
			msgAddIntent.putExtra("addInMsgPID", msgPID);
			LocalBroadcastManager.getInstance(this).sendBroadcast(msgAddIntent);
			finish();
		}
	}
}




