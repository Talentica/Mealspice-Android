package com.talenitca.mealspiceandroid.data;

import com.talenitca.mealspiceandroid.data.models.Restaurant;
import com.talenitca.mealspiceandroid.data.network.httpurl.HttpUrlNWManager;

import java.util.List;

class AppDataManager implements DataManager {
    @Override
    public void fetchRestaurantDetails(String slug, Callback<Restaurant> callback) {

    }

    @Override
    public void fetchAllRestaurants(int page, Callback<List<Restaurant>> callback) {
        new HttpUrlNWManager(page,callback).execute();
    }
}
