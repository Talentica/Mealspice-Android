package com.talenitca.mealspiceandroid.screens.details;

import com.talenitca.mealspiceandroid.data.DataManager;
import com.talenitca.mealspiceandroid.data.models.Restaurant;

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;
    private DataManager dataManager;

    public DetailsPresenter(DetailsContract.View view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    @Override
    public void start(String restaurantSlug) {
        view.showLoading();
        dataManager.fetchRestaurantDetails(restaurantSlug, new DataManager.Callback<Restaurant>() {
            @Override
            public void onResponse(Restaurant result) {
                view.hideLoading();
                view.loadRestaurantPic(result.getPic());
                view.loadAddress(result.getAddress() + " " + result.getCountry());
                view.loadCuisine(result.getCuisine());
                view.loadRatings(String.valueOf(result.getRating()));
                view.loadRestaurantName(result.getName());
                view.loadTagline(result.getTagline());
            }

            @Override
            public void onError(Throwable throwable) {
                view.hideLoading();
                view.onError(throwable);
            }
        });
    }

    @Override
    public void onEditTapped() {

    }

    @Override
    public void onSaveTapped() {

    }

    @Override
    public void destroy() {
        this.view = null;
    }

    public DetailsContract.View getView() {
        return view;
    }
}
