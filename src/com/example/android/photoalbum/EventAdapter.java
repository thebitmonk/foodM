/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.android.photoalbum;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



/**
 * List adapter used to display the content of the photo album to the user.
 */
class EventAdapter extends BaseAdapter {
    private final List<Events> mEvents;
    private LayoutInflater mInflater;
    private Context mContext;
    ImageDownloader downloader;
    
    EventAdapter(Context context, List<Events>events) {
    	mContext = context;
        mInflater = LayoutInflater.from(context);
        mEvents = events;
        downloader = new ImageDownloader();
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public Object getItem(int position) {
        return mEvents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	/**
    	View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.photo_item, parent, false);
        } else {
            view = convertView;
        }

        Photo photo = mDishes.get(position);
        // This will be fast, no need to bother with a ViewHolder
        ((ImageView) view.findViewById(R.id.photo)).setImageDrawable(photo.thumbnail);

        return view;
        **/
    	Events event = mEvents.get(position);
		String url = event.image_link;
		
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.event_item, null);
        }
        
        TextView event_overlay_day = (TextView)convertView.findViewById(R.id.event_overlay_day);
        
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/FIVE.OTF");
        event_overlay_day.setTypeface(tf);
        
        event_overlay_day.setText(event.day);
        //downloader.download(url, null);
        return convertView; 
    }
}
