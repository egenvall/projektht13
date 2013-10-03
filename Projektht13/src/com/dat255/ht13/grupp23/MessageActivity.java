package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView.OnEditorActionListener;



public class MessageActivity extends Activity{
	
	CustomListViewAdapter adapter;
	List<ListViewItem> items;
	MessagePoint messagePoint;
	
	public void createTestPoint(){
		Model model = Model.getModel();
		ArrayList<MessagePoint> messagePoints = model.getMessagePoints();
		
		messagePoint = messagePoints.get(0);
		
	}
	private void getItems(List<ListViewItem> items){
		/*items.add(new ListViewItem()
		{{
			ThumbNailResource = R.drawable.timo;
			Title = "Timo Cengiz";
			SubTitle = "Hi my first post on Klottr";
		}});
		items.add(new ListViewItem()
		{{
			ThumbNailResource = 1;
			Title = "Jonas Ha";
			SubTitle = "I hate Chalmers";
		}}); */
		
		for(final Message message : messagePoint.getMessages()){
			items.add(new ListViewItem(){{
				ThumbNailResource = R.drawable.timo;
				Title = "Timo Cengiz";
				SubTitle = message.getText();
			}});
		}
		
	}
	private void updateMessageWindow(){
		//skriva in kod för att uppdatera meddelandefönstret här
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		
		ListView lv = (ListView)findViewById(R.id.list);
		items = new ArrayList<ListViewItem>();
		createTestPoint();
		getItems(items);
		
		adapter= new CustomListViewAdapter(this, items);        
        lv.setAdapter(adapter);
        

        // Click event for single list row
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
							

			}
		});	
        EditText inputText = (EditText) findViewById(R.id.send);
		inputText.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int arg1, KeyEvent arg2) {
				boolean handled = false;
				if (arg1 == EditorInfo.IME_ACTION_SEND){
					Model model = Model.getModel();
					Message toSend = new Message(v.getText().toString());
					model.addMessageToPoint(toSend,messagePoint.getPosition());
					v.setText("");
					v.clearFocus();
					updateMessageWindow();
					handled = true;
				}
				return handled;
			}	

		}); 
	}
	
	class ListViewItem{
		
		public int ThumbNailResource;
		public String Title;
		public String SubTitle;
		
	}
}