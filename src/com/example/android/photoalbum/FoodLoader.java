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
class FoodLoader {
    private final Context mContext;

    FoodLoader(Context context) {
        mContext = context;
    }

    List<Dish> loadDishes() {
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        addDish(dishes, "Paneer Pasanda", "Rs 500", "main course","tomato cheese", "mild spicy", "1 for 3", "111","http://farm7.staticflickr.com/6154/6144736141_ea63efa403_z.jpg");
        addDish(dishes, "Veg Hariyali", "Rs 250", "main course", "mushroom broccoli pepper", "normal", "1 for 3","123","http://farm4.staticflickr.com/3295/2968102780_7ca4f9bec7_z.jpg?zz=1");
        addDish(dishes, "Crispy Corn", "Rs 300", "starters","mushroom broccoli pepper", "normal", "1 for 3","123","http://farm4.staticflickr.com/3295/2968102780_7ca4f9bec7_z.jpg?zz=1"); 
        addDish(dishes, "Sweet Tomato Soup", "Rs 80", "starters", "tomato cheese", "mild spicy", "1 for 3", "111","http://farm7.staticflickr.com/6154/6144736141_ea63efa403_z.jpg");
        return dishes;
    }

    private void addDish(ArrayList<Dish> dishes, String name, String price, String category,
            String ingredients, String flavour, String servings, String likes, String thumbnail) {
        Dish dish = new Dish();
        dish.name = name;
        dish.price = price;
        dish.category = category;
        dish.ingredients = ingredients;
        dish.flavour = flavour;
        dish.servings = servings;
        dish.likes = likes;
        dish.thumbnail = thumbnail;
        dishes.add(dish);
    }
    

    List<DishCategory> loadDishCategories() {
        ArrayList<DishCategory> dishCategories = new ArrayList<DishCategory>();
        addDishCategory(dishCategories, "Starters", " the most munching set of dishes :)", "13", "http://192.168.1.27:8000/starters.png");
        addDishCategory(dishCategories, "Main Course", " the paunch craves for it :)", "115", "http://192.168.1.27:8000/main_course.png");
        addDishCategory(dishCategories, "Drinks", " liquid that oozles ...", "60", "http://192.168.1.27:8000/drinks.png");
        addDishCategory(dishCategories, "Desserts", " the ending the awesome day deserves", "33", "http://192.168.1.27:8000/special.png");
        return dishCategories;
    }    
    

    private void addDishCategory(ArrayList<DishCategory> dishCategories, String name, String description, 
    	String number_of_dishes, String thumbnail) {
        DishCategory dishCategory = new DishCategory();
        dishCategory.name = name;
        dishCategory.description = description;
        dishCategory.number_of_dishes = number_of_dishes;
        dishCategory.thumbnail = thumbnail;
        dishCategories.add(dishCategory);
    }    
}
