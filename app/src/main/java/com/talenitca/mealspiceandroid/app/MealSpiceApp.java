package com.talenitca.mealspiceandroid.app;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.talenitca.mealspiceandroid.di.AppComponent;
import com.talenitca.mealspiceandroid.di.AppModule;
import com.talenitca.mealspiceandroid.di.DaggerAppComponent;
import com.talenitca.mealspiceandroid.di.NetworkModule;

public class MealSpiceApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
        //Initialize the AdMob SDK in onCreate of Application class
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
