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
class DishCategoryAdapter extends BaseAdapter {
    private final List<DishCategory> mDishCategories;
    private LayoutInflater mInflater;
    private Context mContext; 
    ImageDownloader downloader;
    
    DishCategoryAdapter(Context context, List<DishCategory> dishCategories) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDishCategories = dishCategories;
        downloader = new ImageDownloader();
    }

    @Override
    public int getCount() {
        return mDishCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return mDishCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	DishCategory dishCategory = mDishCategories.get(position);
		String url = dishCategory.thumbnail;
		
    	if (convertView == null) {
            convertView = mInflater.inflate(R.layout.dish_category_item, null);
        }
        
        ImageView dish_category_image = (ImageView)convertView.findViewById(R.id.dish_category_image);        
        
        downloader.download(url, dish_category_image);
        return convertView;
    }
}