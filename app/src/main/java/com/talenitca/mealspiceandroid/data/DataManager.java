package com.talenitca.mealspiceandroid.data;

import com.talenitca.mealspiceandroid.data.models.Restaurant;

import java.util.List;

import io.reactivex.Observable;

public interface DataManager {

    Observable<Restaurant> fetchRestaurantDetails(String slug);

    Observable<List<Restaurant>> fetchAllRestaurants(int page);

    interface Callback<T> {
        void onResponse(T result);

        void onError(Throwable throwable);
    }

}
