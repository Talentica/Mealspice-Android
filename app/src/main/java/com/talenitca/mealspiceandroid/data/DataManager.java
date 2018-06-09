package com.talenitca.mealspiceandroid.data;

import com.talenitca.mealspiceandroid.data.models.Restaurant;

import java.util.List;

public interface DataManager {

    void fetchRestaurantDetails(String slug, Callback<Restaurant> callback);

    void fetchAllRestaurants(int page, Callback<List<Restaurant>> callback);

    interface Callback<T> {
        void onResponse(T result);

        void onError(Throwable throwable);
    }

}
