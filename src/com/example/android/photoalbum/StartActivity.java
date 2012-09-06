package com.example.android.photoalbum;
 
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
 
public class StartActivity extends Activity {
 
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		addListenerOnButton();
	}
 
	public void addListenerOnButton() {
 
		final Context context = this;
 
		ImageView menu = (ImageView) findViewById(R.id.startMenu);
		ImageView event = (ImageView) findViewById(R.id.startEvent);
		ImageView feedback = (ImageView) findViewById(R.id.startFeedback);
		
		menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, AlbumActivity.class);
                            startActivity(intent);   
 
			}
		});
		
		event.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, CalendarActivity.class);
                            startActivity(intent);   
 
			}
		});
		
		feedback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context, FeedbackActivity.class);
                            startActivity(intent);   
 
			}
		});				
 
	}
 
}