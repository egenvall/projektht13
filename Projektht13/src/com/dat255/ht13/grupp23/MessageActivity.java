package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MessageActivity extends Activity {

	MapModel model;
	CustomListViewAdapter adapter;
	ArrayList<Message> messages;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		Bundle b = getIntent().getExtras();
		int msgpointID = b.getInt("msgpointID");

		model = MapModel.getModel();
		messages = model.getMessagePointById(msgpointID).getMessages();
		System.out.print("There is: " + messages.size() + "messages in this marker");

		ListView lv = (ListView)findViewById(R.id.list);
		List<ListViewItem> items = new ArrayList<ListViewItem>();

		for(Message msg : messages){
			final String text = msg.getText();
			final Date date = msg.getDate();
			items.add(new ListViewItem()
			{{
				ThumbNailResource = 1;
				Title = date.toString() ;
				SubTitle = text;
			}});
		}

		adapter= new CustomListViewAdapter(this, items);        
		lv.setAdapter(adapter);


		// Click event for single list row
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {


			}
		});		
	}

	class ListViewItem{

		public int ThumbNailResource;
		public String Title;
		public String SubTitle;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_controller, menu);
		return true;
	}
}




