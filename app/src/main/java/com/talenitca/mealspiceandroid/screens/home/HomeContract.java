package com.talenitca.mealspiceandroid.screens.home;

import com.talenitca.mealspiceandroid.data.models.Restaurant;
import com.talenitca.mealspiceandroid.screens.BasePresenter;
import com.talenitca.mealspiceandroid.screens.BaseView;
import com.talenitca.mealspiceandroid.screens.details.DetailsContract;

import java.util.List;

public interface HomeContract {

    interface View extends BaseView<DetailsContract.Presenter> {
        void loadRestaurants(List<Restaurant> restaurantList);

        void onError(Throwable throwable);
    }

    interface Presenter extends BasePresenter {
        void fetchAllData();
    }
}
