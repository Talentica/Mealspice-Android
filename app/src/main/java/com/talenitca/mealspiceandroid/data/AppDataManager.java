package com.talenitca.mealspiceandroid.data;

import com.talenitca.mealspiceandroid.data.models.Restaurant;
import com.talenitca.mealspiceandroid.data.network.retrofit.RestaurantService;
import com.talenitca.mealspiceandroid.data.network.retrofit.RetrofitNWManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AppDataManager implements DataManager {

    private RestaurantService restaurantService;

    @Inject
    AppDataManager(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public Observable<Restaurant> fetchRestaurantDetails(String slug) {
        return restaurantService.fetchRestaurantDetails(slug);
    }

    @Override
    public Observable<List<Restaurant>> fetchAllRestaurants(int page) {
        return restaurantService.fetchAllRestaurants();
    }
}
