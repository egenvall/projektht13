package com.dat255.ht13.grupp23;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;



public class MessageActivity extends Activity{
	
	CustomListViewAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		
		ListView lv = (ListView)findViewById(R.id.list);
		List<ListViewItem> items = new ArrayList<ListViewItem>();
		items.add(new ListViewItem()
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
		}});
		
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
}