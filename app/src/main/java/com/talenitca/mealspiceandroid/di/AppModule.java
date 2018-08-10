package com.talenitca.mealspiceandroid.di;

import android.app.Application;
import android.content.Context;

import com.talenitca.mealspiceandroid.data.DataManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {

    private final Application mApp;

    public AppModule(Application app){
        this.mApp = app;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    @Background
    Scheduler providesBackGroundScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Foreground
    Scheduler providesForeGroundScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
