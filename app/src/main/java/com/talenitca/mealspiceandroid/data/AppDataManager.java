package com.talenitca.mealspiceandroid.data;

import com.talenitca.mealspiceandroid.data.models.Restaurant;

import java.util.List;

class AppDataManager implements DataManager {
    @Override
    public void fetchRestaurantDetails(String slug, Callback<Restaurant> callback) {
        // TODO Make an API call to fetch results
    }

    @Override
    public void fetchAllRestaurants(int page, Callback<List<Restaurant>> callback) {
        // TODO Make an API call to fetch results
    }
}
