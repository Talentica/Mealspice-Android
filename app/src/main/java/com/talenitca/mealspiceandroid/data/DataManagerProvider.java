package com.talenitca.mealspiceandroid.data;

import com.talenitca.mealspiceandroid.BuildConfig;
import com.talenitca.mealspiceandroid.data.models.Restaurant;
import com.talenitca.mealspiceandroid.utils.TestUtils;

import java.util.List;

public class DataManagerProvider {

    public static DataManager provide() {
        if (BuildConfig.FLAVOR.equals("mock")) {
            return getMockImplementation();
        } else {
            return getRealImplementation();
        }
    }

    public static DataManager getRealImplementation() {
        return new AppDataManager();
    }

    public static DataManager getMockImplementation() {
        return new DataManager() {
            @Override
            public void fetchRestaurantDetails(String slug, Callback<Restaurant> callback) {
                callback.onResponse(TestUtils.getMockedRestaurant());
                // callback.onError(new Throwable("save me"));
            }

            @Override
            public void fetchAllRestaurants(int page, Callback<List<Restaurant>> callback) {
                callback.onResponse(TestUtils.getMockedRestaurantList());
            }
        };
    }
    public static DataManager getMockErrorImplementation() {
        return new DataManager() {
            @Override
            public void fetchRestaurantDetails(String slug, Callback<Restaurant> callback) {
                //callback.onResponse(TestUtils.getMockedRestaurant());
                 callback.onError(new Throwable("save me"));
            }

            @Override
            public void fetchAllRestaurants(int page, Callback<List<Restaurant>> callback) {
               // callback.onResponse(TestUtils.getMockedRestaurantList());
                callback.onError(new Throwable("save me"));
            }
        };
    }
}
