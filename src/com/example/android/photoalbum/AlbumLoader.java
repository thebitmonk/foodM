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
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads the photos of an album. This implementation is very simple
 * and simply returns a static list of photos.
 */
class AlbumLoader {
    private final Context mContext;

    AlbumLoader(Context context) {
        mContext = context;
    }

    List<Photo> loadPhotos() {
        ArrayList<Photo> photos = new ArrayList<Photo>();
        addPhoto(photos, "Rs 450", "13 mins", "bitmonk", "tomato cheese", "1 for 2","123","111","sweet","http://farm7.staticflickr.com/6154/6144736141_ea63efa403_z.jpg");
        addPhoto(photos, "Rs 500", "13 mins", "rebelvarun", "mushroom broccoli pepper", "1 for 3","123","111","spicy","http://farm4.staticflickr.com/3295/2968102780_7ca4f9bec7_z.jpg?zz=1");
        addPhoto(photos, "Bitmonk", "13 mins", "bitmonk", "ubergeeks", "hacking world primer","123","111","Jan 1, 2012","http://www.project-humanity-earth.org/yahoo_site_admin/assets/images/city-2.226121338_std.jpg");
        addPhoto(photos, "Bitmonk", "13 mins", "bitmonk", "ubergeeks", "hacking world primer","123","111","Jan 1, 2012","http://www.futuretechnology500.com/images/future-airplane.jpg");
        return photos;
    }

    private void addPhoto(ArrayList<Photo> photos, String title, String duration, String posted_by,
            String channel, String description, String views, String likes, String date, String thumbnail) {
        Photo photo = new Photo();
        photo.title = title;
        photo.duration = duration;
        photo.posted_by = posted_by;
        photo.channel = channel;
        photo.description = description;
        photo.views = views;
        photo.likes = likes;
        photo.date = date;
        photo.thumbnail = thumbnail;
        photos.add(photo);
    }
    

    List<App> loadApps() {
        ArrayList<App> apps = new ArrayList<App>();
        addApp(apps, "Snap", "Canon 5D Mk II", "1/200", "8", "200", "1000",
                R.drawable.photo_9, R.drawable.starters1, 37719218, -121657233);
        addApp(apps, "Snap", "Canon 5D Mk II", "1/200", "8", "200", "1000",
                R.drawable.photo_9, R.drawable.main_course1, 37719218, -121657233);
        addApp(apps, "Facebook", "Canon 5D Mk II", "1/100", "4", "98", "2000",
                R.drawable.photo_10, R.drawable.drinks, 37322683, -122210696);
        addApp(apps, "History", "Canon 5D Mk II", "1/100", "4", "98", "2000",
                R.drawable.photo_10, R.drawable.special, 37322683, -122210696);
        return apps;
    }    
    

    private void addApp(ArrayList<App> apps, String name, String camera, String exposure,
            String aperture, String focal, String iso, int resource, int resourceSmall,
            int latitude, int longitude) {
        App app = new App();
        app.name = name;
        app.camera = camera;
        app.exposure = exposure;
        app.aperture = aperture;
        app.focal = focal;
        app.iso = iso;
        app.photoResource = resource;
        app.thumbnail = mContext.getResources().getDrawable(resourceSmall);
        app.latitude = latitude;
        app.longitude = longitude;
        apps.add(app);
    }    
}
