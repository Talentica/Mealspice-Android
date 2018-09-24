package com.talenitca.mealspiceandroid.data.models;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

public class ListItem {
    private int type;
    private Restaurant restaurant;
    private UnifiedNativeAd nativeAd;

    public ListItem(int type, Restaurant restaurant) {
        this.type = type;
        this.restaurant = restaurant;
    }

    public ListItem(int type, UnifiedNativeAd nativeAd) {
        this.type = type;
        this.nativeAd = nativeAd;
    }

    public int getType() {
        return type;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public UnifiedNativeAd getNativeAd() {
        return nativeAd;
    }
}
