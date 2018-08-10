package com.talenitca.mealspiceandroid.data.network.retrofit;

import com.talenitca.mealspiceandroid.data.models.Restaurant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RestaurantService {

    @GET("restaurants")
    Observable<List<Restaurant>> fetchAllRestaurants();

    @GET("restaurant/{slug}")
    Observable<Restaurant> fetchRestaurantDetails(@Path("slug") String slug);

}
