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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * List adapter used to display the content of the photo album to the user.
 */
class AppAdapter extends BaseAdapter {
    private final List<App> mApps;
    private LayoutInflater mInflater;

    AppAdapter(Context context, List<App> apps) {
        mInflater = LayoutInflater.from(context);
        mApps = apps;
    }

    @Override
    public int getCount() {
        return mApps.size();
    }

    @Override
    public Object getItem(int position) {
        return mApps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.app_item, parent, false);
        } else {
            view = convertView;
        }

        App app = mApps.get(position);
        // This will be fast, no need to bother with a ViewHolder
        ((ImageView) view.findViewById(R.id.app)).setImageDrawable(app.thumbnail);

        return view;
    }
}