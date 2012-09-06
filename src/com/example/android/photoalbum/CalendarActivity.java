package com.example.android.photoalbum;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;

public class CalendarActivity extends Activity{

	private List<Events> mEvents;
	private EventAdapter mEventAdap;
	private ListView eventList;
	private Context mContext;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mContext = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_activity);
		mEvents = new EventsLoader(this).loadEvents();
		mEventAdap = new EventAdapter(this, mEvents);
		setUpStage();
	}

	public void setUpStage(){
		eventList = (ListView) findViewById(R.id.event_list);
		eventList.setAdapter(mEventAdap);
		eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	showEvent(position);
            }
        });
	}
	
	public void showEvent(int position){
		Events event = mEvents.get(position);
		Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/FIVE.OTF");
		Typeface hd = Typeface.createFromAsset(mContext.getAssets(), "fonts/HOMESTEAD-DISPLAY.TTF");
		
		TextView event_name = (TextView)findViewById(R.id.event_name);
		event_name.setTypeface(hd);
		event_name.setTextSize(25);
		event_name.setTextColor(Color.BLACK);
		event_name.setText(event.name);
		
		TextView event_description = (TextView)findViewById(R.id.event_desc);
		event_description.setMovementMethod(new ScrollingMovementMethod());
		event_description.setText(event.description);
		
		
		TextView event_timing = (TextView)findViewById(R.id.event_timing);
		event_timing.setTypeface(tf);
		event_timing.setTextSize(25);
		event_timing.setText(event.timing);
		
		TextView event_tags = (TextView)findViewById(R.id.event_tags);
		event_tags.setTypeface(tf);
		event_tags.setTextSize(25);
		event_tags.setText(event.tags);
		
		ImageView event_image = (ImageView)findViewById(R.id.event_image);
		String image_name = event.image_link;
		String[] image_parts;
		image_parts = image_name.split("[.]+");
		String uri = "drawable/" + image_parts[0];
		int imageResource = getResources().getIdentifier(uri, null, getPackageName());
		Drawable image = getResources().getDrawable(imageResource);
		event_image.setImageDrawable(image);
	}
}
