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

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import java.io.File;
import java.util.List;


public class AlbumActivity extends Activity {
    private ViewGroup mStack;
    private List<Dish> mDishes;
    private List<DishCategory> mDishCategories;
    private View mPanel;
    private boolean mPanelVisible = true;
    private ObjectAnimator mPanelAnimator;
    private ListView dishList;
    private LinearLayout mphoto_info;
    private ImageView mToggleButton;
    private ListView dishCategoryList;
    private View mPhotoInfo;
    private ListView global_visible_view;
    private ImageDownloader mbitCache;
    private DishAdapter mDishAdap;
    private DishCategoryAdapter mDishCategoryAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.album_activity);
        getWindow().setBackgroundDrawable(null);

        mPanel = findViewById(R.id.panel);

        // A real application should do this on a background thread
        mDishes = new FoodLoader(this).loadDishes();
        mDishCategories = new FoodLoader(this).loadDishCategories();
        mDishAdap = new DishAdapter(this, mDishes);
        mDishCategoryAdap = new DishCategoryAdapter(this, mDishCategories);
        setupDeck();
        setupAlbumList();
        setupStack();
        
    }

    /**
     * Prepares the cards deck.
     */
    private void setupDeck() {
        mPhotoInfo = findViewById(R.id.photo_info);
    }

    /**
     * Prepares the photos stack.
     */
    private void setupStack() {
        mStack = (ViewGroup) findViewById(R.id.stack);
    }


    /**
     * Adds the specified photo to the photos stack.
     * 
     *
     * @param photo The photo to display in the stack
     * @param bitmap The bitmap to display in the stack
     * 
     * @return The generated PhotoView used to display the specified photo
     */
    private PhotoView addPhotoInStack(Dish dish, Bitmap bitmap) {
        PhotoView view = new PhotoView(this, bitmap);
        bindDishInfo(dish);
        mStack.addView(view, createStackLayoutParams());
        return view;
    }

    /**
     * Displays info about the specified photo.
     * 
     * @param photo The photo object to bind to the UI
     */
    private void bindDishInfo(Dish dish) {
        setText(R.id.dish_price, dish.price);
        setText(R.id.dish_flavour, dish.flavour);
        setText(R.id.dish_servings, dish.servings);
        setText(R.id.dish_ingredients, dish.ingredients);
    }

    private void setText(int id, String value) {
        ((TextView) mPhotoInfo.findViewById(id)).setText(value);
    }

    private void setText(int id, String value, int format) {
        ((TextView) mPhotoInfo.findViewById(id)).setText(getResources().getString(format, value));
    }

    /**
     * Adds the specified photo in the stack with animations.
     */
    private void addFullPhoto(Dish dish) {
        final View stackTop = mStack.getChildAt(mStack.getChildCount() - 1);
        new PhotoSwap(dish, stackTop).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

    /**
     * Asynchronous task that loads a large bitmap on a background thread.
     * After the bitmap is loaded, it is added to the photos stack.
     */
    private class PhotoSwap extends AsyncTask<Void, Void, Bitmap> {
        private final Dish mDish;
        private final View mStackTop;

        private Drawable mMarker;

        PhotoSwap(Dish dish, View stackTop) {
            mDish = dish;
            mStackTop = stackTop;
        }
        
        @Override
        protected Bitmap doInBackground(Void... params) {
            //Bitmap bitmap = mCache.get(mPhoto.photoResource);
        	String url = mDish.thumbnail;
        	String filename = String.valueOf(url.hashCode());
        	ImageView image = (ImageView)findViewById(R.id.dish_image);
        	File f = new File(mDishAdap.downloader.getCacheDirectory(image.getContext()), filename);
        	//String s = "/storage/sdcard0/data/tac/images/1165949943";
            Bitmap temp_bitmap = (Bitmap)mDishAdap.downloader.imageCache.get(f.getPath());
            Bitmap bitmap = temp_bitmap.createScaledBitmap(temp_bitmap, 1280, 752, true);
            return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            final PhotoView newStackTop = addPhotoInStack(mDish, bitmap);
            animateIn(newStackTop);
        }
    } 

    /**
     * Animates the specified view to appear in the stack.
     */
    private void animateIn(final PhotoView view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX",
                mPanel.getWidth() - view.getContentWidth(), 0.0f);
        animator.setDuration(600);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Remove the previous view in the stack now that we
                // completely cover it
                int index = mStack.indexOfChild(view) - 1;
                if (index >= 0) {
                    View previous = mStack.getChildAt(index);
                    mStack.removeView(previous);
                }
            }
        });
        animator.start();
    }

    /**
     * Animates the specified view to disappear from the stack.
     */
    private void animateOut(final View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX",
                mPanel.getWidth() * 2.0f);
        animator.setDuration(1500);
        animator.start();

    }

    /**
     * Show or hide the album panel.
     */
    private void togglePanel() {
        mPanelVisible = !mPanelVisible;

        mPanel.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        if (mPanelAnimator != null && mPanelAnimator.isRunning()) {
            mPanelAnimator.reverse();
            return;
        }

        if (mPanelVisible) {
            // Display the panel, move it back to its original location
            mPanelAnimator = ObjectAnimator.ofFloat(mPanel, "x", 0.0f);
        } else {
            // Hide the panel, move it out of the screen
            mPanelAnimator = ObjectAnimator.ofFloat(mPanel, "x", -mPanel.getWidth());
        }

        mPanelAnimator.setDuration(250);
        mPanelAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mPanel.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
        mPanelAnimator.start();
    }

    /**
     * Creates layout parameters used by children of the photos stack.
     */
    private static FrameLayout.LayoutParams createStackLayoutParams() {
        return new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    /**
     * Creates the album's list adapter and binds it to the list view.
     * This method also setups the various list controllers (list item click
     * listener for instance.)
     */
    private void setupAlbumList() {
        dishList = (ListView) findViewById(R.id.album_list);
        mToggleButton = (ImageView) findViewById(R.id.up);
        dishCategoryList = (ListView) findViewById(R.id.app_list);
        mphoto_info = (LinearLayout)findViewById(R.id.photo_info);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/JUMPS__.TTF");

        // Setting typeface of TextViews
        // Note: Later override the TextViews and at runtime change their fonts
        ((TextView) findViewById(R.id.dish_flavour_text)).setTypeface(tf);
        ((TextView) findViewById(R.id.dish_ingredients_text)).setTypeface(tf);
        ((TextView) findViewById(R.id.dish_price_text)).setTypeface(tf);
        ((TextView) findViewById(R.id.dish_servings_text)).setTypeface(tf);
        ((TextView) findViewById(R.id.dish_flavour)).setTypeface(tf);
        ((TextView) findViewById(R.id.dish_ingredients)).setTypeface(tf);
        ((TextView) findViewById(R.id.dish_price)).setTypeface(tf);
        ((TextView) findViewById(R.id.dish_servings)).setTypeface(tf);
        
        dishList.setAdapter(mDishAdap);
        dishList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Dish selected_dish = mDishes.get(position);
                addFullPhoto((Dish) selected_dish);
				if(mphoto_info.getVisibility() == View.INVISIBLE){
					mphoto_info.setVisibility(View.VISIBLE);
                }
            }
        });

        mToggleButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
				transitionScreens();
				mToggleButton.setVisibility(View.INVISIBLE);
            }
        });

        dishCategoryList.setAdapter(new DishCategoryAdapter(this, mDishCategories));
        dishCategoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				transitionScreens();
				mToggleButton.setVisibility(View.VISIBLE);
				if(mphoto_info.getVisibility() == View.VISIBLE){
					mphoto_info.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void transitionScreens(){
        if(dishList.getVisibility() == View.VISIBLE)
        {
			//list.setVisibility(View.INVISIBLE);
			dishCategoryList.setVisibility(View.VISIBLE);
			appAnimateIn(dishList, dishCategoryList);
			return;
        }
        if(dishCategoryList.getVisibility() == View.VISIBLE)
        {
			//app_list.setVisibility(View.INVISIBLE);
        	dishList.setVisibility(View.VISIBLE);
			appAnimateIn(dishCategoryList, dishList);
			return;
        }
    }

    private void appAnimateIn(ListView visible_view, ListView invisible_view) {
    	global_visible_view = visible_view;
        ObjectAnimator animator = ObjectAnimator.ofFloat(invisible_view, "translationX",
                invisible_view.getWidth(), 0.0f);
        
        ObjectAnimator animator_2 = ObjectAnimator.ofFloat(visible_view, "translationX",
                  0.0f, -visible_view.getWidth() );
        
        animator.setDuration(3000);
        animator_2.setDuration(3000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Remove the previous view in the stack now that we
                // completely cover it
            	global_visible_view.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
        animator_2.start();

    }
    
}
