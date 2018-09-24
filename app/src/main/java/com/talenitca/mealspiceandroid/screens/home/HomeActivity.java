package com.talenitca.mealspiceandroid.screens.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.talenitca.mealspiceandroid.R;
import com.talenitca.mealspiceandroid.app.MealSpiceApp;
import com.talenitca.mealspiceandroid.data.models.ListItem;
import com.talenitca.mealspiceandroid.data.models.Restaurant;
import com.talenitca.mealspiceandroid.di.DaggerViewComponent;
import com.talenitca.mealspiceandroid.di.HomeActivityScope;
import com.talenitca.mealspiceandroid.di.ViewModule;
import com.talenitca.mealspiceandroid.screens.details.DetailsActivity;
import com.talenitca.mealspiceandroid.screens.details.DetailsContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@HomeActivityScope
public class HomeActivity extends AppCompatActivity implements HomeContract.View,
        HomeAdapter.IRestaurantListClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private HomeAdapter mAdapter;
    private ProgressBar mProgressBar;
    private List<ListItem> itemList;

    //NATIVE_ADS
    public static final int NUMBER_OF_ADS = 3; // number of native ad we want to load
    private AdLoader adLoader; // ad loader object, will be used to load native ads
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>(); //to keep the references of native ads

    @Inject
    HomeContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RecyclerView mRecyclerView = findViewById(R.id.activity_home_recyclerview);
        mProgressBar = findViewById(R.id.activity_home_progressbar);

        mAdapter = new HomeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DaggerViewComponent.builder()
                .appComponent(((MealSpiceApp) getApplication()).getAppComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.txt_restautant_list);
        }

        mPresenter.fetchAllData();

        //show the banner ad
        showBannerAd();

        loadNativeAds();

    }


    private void showBannerAd(){
        //create an instance of banner view
        AdView adView = findViewById(R.id.adView);

        //create ad request
        AdRequest adRequest = new AdRequest.Builder().build();

        //set event listeners
        adView.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                Log.d(TAG,"ad closed");
            }
            @Override
            public void onAdFailedToLoad(int i) {
                Log.d(TAG,"failed to load ad: "+i);
            }
            @Override
            public void onAdLeftApplication() {
                Log.d(TAG,"ad left app");
            }
            @Override
            public void onAdOpened() {
                Log.d(TAG,"ad opened");
            }

            @Override
            public void onAdLoaded() {
                Log.d(TAG,"ad loaded");
            }

            @Override
            public void onAdClicked() {
                Log.d(TAG,"ad clicked");
            }

            @Override
            public void onAdImpression() {
                Log.d(TAG,"ad impression");
            }
        });

        //load banner ad
        adView.loadAd(adRequest);
    }

    @Override
    public void loadRestaurants(List<Restaurant> restaurantList) {
        itemList = new ArrayList<>();
        for(Restaurant restaurant: restaurantList){
            itemList.add(new ListItem(0,restaurant));
        }
        if (mAdapter != null) mAdapter.setData(itemList);
    }

     //NATIVE_ADS - load native ads
     private void loadNativeAds() {
        AdLoader.Builder builder = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110");
        adLoader = builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {

                        mNativeAds.add(unifiedNativeAd);
                        if (!adLoader.isLoading()) {
                            // when loading is complete insert the ads between list items
                            insertInListItems();
                        }
                    }
                }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {

                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
                                + " load another.");

                    }
                }).build();

        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }

    //insert ads in list items
    private void insertInListItems() {
        if (mNativeAds.size() > 0) {

            int offset = (itemList.size() / mNativeAds.size()) + 1;
            int index = itemList.size()/3;
            for (UnifiedNativeAd ad: mNativeAds) {
                itemList.add(index, new ListItem(1,ad));
                index = index + offset;
            }
        } else {
            Log.d(TAG,"no native ads");
        }

        if (mAdapter != null) {
            mAdapter.setData(itemList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToDetails(Restaurant restaurant) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("slug", restaurant.getSlug());
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public DetailsContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public void onRestaurantClicked(Restaurant restaurant) {
        mPresenter.onRestaurantClicked(restaurant);
    }
}
