package com.talenitca.mealspiceandroid.data;

import com.talenitca.mealspiceandroid.BuildConfig;
import com.talenitca.mealspiceandroid.data.models.Restaurant;
import com.talenitca.mealspiceandroid.data.network.httpurl.HttpUrlNWManager;
import com.talenitca.mealspiceandroid.data.network.retrofit.RetrofitNWManager;

import java.util.List;

class AppDataManager implements DataManager {
    @Override
    public void fetchRestaurantDetails(String slug, Callback<Restaurant> callback) {
        if (BuildConfig.FLAVOR.equals("httpUrl")) {
            new HttpUrlNWManager().fetchRestaurantDetails(slug, callback);
        }else{
            new RetrofitNWManager().fetchRestaurantDetails(slug, callback);
        }
    }

    @Override
    public void fetchAllRestaurants(int page, Callback<List<Restaurant>> callback) {
        if (BuildConfig.FLAVOR.equals("httpUrl")) {
            new HttpUrlNWManager().fetchAllRestaurants(page, callback);
        } else {
            new RetrofitNWManager().fetchAllRestaurants(page, callback);
        }
    }
}
