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
class DishAdapter extends BaseAdapter {
    private final List<Dish> mDishes;
    private LayoutInflater mInflater;
    private Context mContext;
    ImageDownloader downloader;
    
    DishAdapter(Context context, List<Dish>dishes) {
    	mContext = context;
        mInflater = LayoutInflater.from(context);
        mDishes = dishes;
        downloader = new ImageDownloader();
    }

    @Override
    public int getCount() {
        return mDishes.size();
    }

    @Override
    public Object getItem(int position) {
        return mDishes.get(position);
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
    	Dish dish = mDishes.get(position);
		String url = dish.thumbnail;
		
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.dish_item, null);
        }
        
        ImageView dish_image = (ImageView)convertView.findViewById(R.id.dish_image);
        TextView dish_overlay_name = (TextView)convertView.findViewById(R.id.dish_overlay_name);
        TextView dish_overlay_price = (TextView)convertView.findViewById(R.id.dish_overlay_price);
        
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/FIVE.OTF");
        dish_overlay_name.setTypeface(tf);
        dish_overlay_price.setTypeface(tf);
        
        dish_overlay_name.setText(dish.name);
        dish_overlay_price.setText(dish.price);
        downloader.download(url, dish_image);
        return convertView; 
    }
}
