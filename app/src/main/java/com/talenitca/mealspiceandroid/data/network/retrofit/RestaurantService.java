package com.talenitca.mealspiceandroid.data.network.retrofit;

import com.talenitca.mealspiceandroid.data.models.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestaurantService {

    @GET("restaurants")
    public Call<List<Restaurant>> fetchAllRestaurants();

    @GET
    public Call<Restaurant> fetchRestaurantDetails(@Url String slug);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://pmd-test-app-001.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
