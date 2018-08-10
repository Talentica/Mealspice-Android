package com.talenitca.mealspiceandroid.screens.details;

import com.talenitca.mealspiceandroid.data.DataManager;
import com.talenitca.mealspiceandroid.di.Background;
import com.talenitca.mealspiceandroid.di.Foreground;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;
    private DataManager dataManager;

    private Scheduler backgroundScheduler, foregroundScheduler;

    private CompositeDisposable compositeDisposable;

    @Inject
    DetailsPresenter(DetailsContract.View view, DataManager dataManager,
                     @Background Scheduler backgroundScheduler,
                     @Foreground Scheduler foregroundScheduler) {
        this.view = view;
        this.dataManager = dataManager;
        this.backgroundScheduler = backgroundScheduler;
        this.foregroundScheduler = foregroundScheduler;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void start(String restaurantSlug) {
        view.showLoading();
        compositeDisposable.add(
                dataManager.fetchRestaurantDetails(restaurantSlug)
                        .subscribeOn(backgroundScheduler)
                        .observeOn(foregroundScheduler)
                        .doOnComplete(() -> view.hideLoading())
                        .subscribe(restaurant -> {
                            view.loadRestaurantPic(restaurant.getPic());
                            view.loadAddress(restaurant.getAddress() + " " + restaurant.getCountry());
                            view.loadCuisine(restaurant.getCuisine());
                            view.loadRatings(String.valueOf(restaurant.getRating()));
                            view.loadRestaurantName(restaurant.getName());
                            view.loadTagline(restaurant.getTagline());
                        }, throwable -> {
                            view.hideLoading();
                            view.onError(throwable);
                        })
        );
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
        compositeDisposable.dispose();
    }

    public DetailsContract.View getView() {
        return view;
    }
}
