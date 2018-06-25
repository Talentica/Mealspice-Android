package com.talenitca.mealspiceandroid.screens.home;

import com.talenitca.mealspiceandroid.data.DataManager;
import com.talenitca.mealspiceandroid.data.DataManagerProvider;
import com.talenitca.mealspiceandroid.data.models.Restaurant;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private DataManager dataManager;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        dataManager = DataManagerProvider.provide();
    }


    @Override
    public void fetchAllData() {
        view.showLoading();
        dataManager.fetchAllRestaurants(1, new DataManager.Callback<List<Restaurant>>() {
            @Override
            public void onResponse(List<Restaurant> result) {
                view.hideLoading();
                view.loadRestaurants(result);
            }

            @Override
            public void onError(Throwable throwable) {
                view.hideLoading();
                view.onError(throwable);
            }
        });
    }

    @Override
    public void destroy() {

    }
}
