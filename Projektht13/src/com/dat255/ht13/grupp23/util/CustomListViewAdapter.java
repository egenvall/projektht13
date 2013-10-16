package com.dat255.ht13.grupp23.util;

import java.util.List;

import com.dat255.ht13.grupp23.R;
import com.dat255.ht13.grupp23.R.drawable;
import com.dat255.ht13.grupp23.R.id;
import com.dat255.ht13.grupp23.R.layout;
import com.dat255.ht13.grupp23.activites.MessageActivity.ListViewItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @copyright (c) 2013 Kim Egenvall, Carl Fredriksson, Jonas Ha, Johan Wallander, Timocin Zaynal,Josef Haddad
 * @license MIT
 *
 */
public class CustomListViewAdapter extends BaseAdapter {
    
	LayoutInflater inflater;
	List<ListViewItem> items;
    
    public CustomListViewAdapter(Activity context, List<ListViewItem> items) {
        super();
        
        this.items = items;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    
    public View getView(final int position, View convertView, ViewGroup parent) {
    	ListViewItem item = items.get(position);
    	View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // Datum
        TextView author = (TextView)vi.findViewById(R.id.message); // Message
        TextView date = (TextView)vi.findViewById(R.id.date); // Name
        
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        if(item.ThumbNailResource == 1)			// Unkown picture on those who have not uploaded pic
        	item.ThumbNailResource = R.drawable.unkown;		
        
        title.setText(item.Title);  // Datum
        author.setText(item.SubTitle);
        date.setText(item.Name);   // Namn
        
        
        thumb_image.setImageResource(item.ThumbNailResource);
        
        return vi;
    }
}