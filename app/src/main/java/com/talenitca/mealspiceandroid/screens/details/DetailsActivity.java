package com.talenitca.mealspiceandroid.screens.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;
import com.talenitca.mealspiceandroid.R;
import com.talenitca.mealspiceandroid.app.MealSpiceApp;
import com.talenitca.mealspiceandroid.di.DaggerViewComponent;
import com.talenitca.mealspiceandroid.di.DetailsActivityScope;
import com.talenitca.mealspiceandroid.di.ViewModule;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@DetailsActivityScope
public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private static final String TAG_REWARDED_VIDEO = "RewardedVideoAd";
    private static final String TAG_INTERSTITIAL_AD = "InterstitialAd";

    @Inject
    DetailsContract.Presenter presenter;

    // UI elements
    private ImageView imgRestaurant, imgFavourite;
    private RatingBar ratingBar;
    private EditText tvName, tvTagLine, tvLocation, tvCuisine;
    private CollapsingToolbarLayout toolbarLayout;
    private FloatingActionButton fabUpdate;

    private boolean isEditMode = false;
    //INTERSTITIAL_AD
    private InterstitialAd mInterstitialAd;

    //REWARDED_VIDEO_AD
    private RewardedVideoAd mRewardedVideoAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeUI();

        Bundle extras = getIntent().getExtras();

        if (extras == null || !extras.containsKey("slug")) {
            finish();
            return;
        }

        DaggerViewComponent.builder()
                .appComponent(((MealSpiceApp) getApplicationContext()).getAppComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);

        presenter.start(extras.getString("slug"));
        //load interstitial ad in oncreate
        loadInterstitialAd();

        //load rewarded video ad in oncreate
        loadRewardedVideo();
    }

//        INTERSTITIAL_AD
    private void loadInterstitialAd() {

        //create instance of interstitial object
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new InterstitialAdListener());
        //load ad
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    //listener for interstitial ads
    class InterstitialAdListener extends AdListener {
        @Override
        public void onAdClosed() {
            Log.d(TAG_INTERSTITIAL_AD,"interstitial ad opened");
        }

        @Override
        public void onAdFailedToLoad(int i) {
            Log.d(TAG_INTERSTITIAL_AD,"interstitial ad failed to load ad. Reason = "+i);
        }

        @Override
        public void onAdLeftApplication() {
            Log.d(TAG_INTERSTITIAL_AD,"interstitial ad left application");
        }

        @Override
        public void onAdOpened() {
            Log.d(TAG_INTERSTITIAL_AD,"interstitial ad opened");
        }

        @Override
        public void onAdLoaded() {
            Log.d(TAG_INTERSTITIAL_AD,"interstitial ad loaded");
            if(mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }

        @Override
        public void onAdClicked() {
            Log.d(TAG_INTERSTITIAL_AD,"interstitial ad clicked");
        }

        @Override
        public void onAdImpression() {
            Log.d(TAG_INTERSTITIAL_AD,"interstitial ad impression");
        }
    }



//      REWARDED_VIDEO_AD - Load
    private void loadRewardedVideo() {
        Log.d(TAG_REWARDED_VIDEO,"load rewarded video");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(rwdListener);
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
    }

    //      REWARDED_VIDEO_AD - show
    private void showRewardedVideo() {
        Log.d(TAG_REWARDED_VIDEO,"show rewarded video");
        if(mRewardedVideoAd != null && mRewardedVideoAd.isLoaded()){
            mRewardedVideoAd.show();
        } else {
            Log.d(TAG_REWARDED_VIDEO,"rewarded video not loaded yet");
        }
    }

//      REWARDED_VIDEO_AD - callbacks
    public RewardedVideoAdListener rwdListener = new RewardedVideoAdListener() {
        @Override
        public void onRewardedVideoAdLoaded() {
            Log.d(TAG_REWARDED_VIDEO,"rewarded video ad loaded");
        }

        @Override
        public void onRewardedVideoAdOpened() {
            Log.d(TAG_REWARDED_VIDEO,"rewarded video ad opened");
        }

        @Override
        public void onRewardedVideoStarted() {
            Log.d(TAG_REWARDED_VIDEO,"rewarded video ad started");
        }

        @Override
        public void onRewardedVideoAdClosed() {
            Log.d(TAG_REWARDED_VIDEO,"rewarded video ad closed");
            //calling finish on activity when we are done with rewarded video.
            finish();
        }

        @Override
        public void onRewarded(RewardItem rewardItem) {
            Log.d(TAG_REWARDED_VIDEO,"reward received. Type= "+rewardItem.getType()+" Amount= "+rewardItem.getAmount());
        }

        @Override
        public void onRewardedVideoAdLeftApplication() {
            Log.d(TAG_REWARDED_VIDEO,"rewarded video ad left application");
        }

        @Override
        public void onRewardedVideoAdFailedToLoad(int i) {
            Log.d(TAG_REWARDED_VIDEO,"rewarded video ad failed to load ad. Reason = "+i);
        }

        @Override
        public void onRewardedVideoCompleted() {
            Log.d(TAG_REWARDED_VIDEO,"rewarded video ad completed");
        }
    };

    @SuppressLint("CheckResult")
    private void initializeUI() {
        Toolbar toolbar = findViewById(R.id.toolbar_details);
        toolbarLayout = findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imgRestaurant = findViewById(R.id.collapsing_toolbar_image_view);
        imgFavourite = findViewById(R.id.img_detail_favorite);

        // imgFavourite.setOnClickListener(v -> presenter.onFavouriteTapped());
        RxView.clicks(imgFavourite)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(o -> {
                    Log.d("favClick", "initializeUI: click registered");
                    presenter.onFavouriteTapped();
                });

        fabUpdate = findViewById(R.id.fab_edit_details);

        RxView.clicks(fabUpdate)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(o -> {
                    Log.d("edit / save clicked", "initializeUI: click registered");
                    if (isEditMode) {
                        disableEditing();
                        presenter.onSaveTapped(
                                tvName.getText().toString(),
                                tvTagLine.getText().toString(),
                                tvLocation.getText().toString(),
                                tvCuisine.getText().toString()
                        );
                    } else {
                        enableEditing();
                    }
                });

        ratingBar = findViewById(R.id.ratingBar);
        tvName = findViewById(R.id.tv_restaurant_name);
        tvTagLine = findViewById(R.id.tv_restaurant_tagline);
        tvLocation = findViewById(R.id.tv_restaurant_location);
        tvCuisine = findViewById(R.id.tv_restaurant_cuisine);
    }

    @Override
    public void loadRestaurantPic(String url) {
        Picasso.with(this)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(imgRestaurant);
        imgRestaurant.setAlpha(1.0f);
    }

    @Override
    public void loadRestaurantName(String name) {
        toolbarLayout.setTitle(name);
        imgRestaurant.setContentDescription(name);
        tvName.setText(name);
    }

    @Override
    public void loadRatings(String rating) {
        ratingBar.setRating(Float.parseFloat(rating));
    }

    @Override
    public void loadTagline(String tagline) {
        tvTagLine.setText(tagline);
    }

    @Override
    public void loadAddress(String address) {
        tvLocation.setText(address);
    }

    @Override
    public void loadCuisine(String cuisine) {
        tvCuisine.setText(cuisine);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setFavouriteStatus(boolean status) {
        if (status)
            imgFavourite.setImageResource(R.drawable.ic_star_filled);
        else
            imgFavourite.setImageResource(R.drawable.ic_star_empty);
    }

    @Override
    public void dismissWithMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        //showing rewarded video when we are saving the editable restaurant name.
        showRewardedVideo();
    }

    private void enableEditing() {
        isEditMode = true;
        fabUpdate.setImageResource(R.drawable.ic_check_orange);
        tvName.setEnabled(true);
        tvTagLine.setEnabled(true);
        tvLocation.setEnabled(true);
        tvCuisine.setEnabled(true);
    }


    private void disableEditing() {
        isEditMode = false;
        fabUpdate.setImageResource(R.drawable.pencil);
        tvName.setEnabled(false);
        tvTagLine.setEnabled(false);
        tvLocation.setEnabled(false);
        tvCuisine.setEnabled(false);
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Please wait", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public DetailsContract.Presenter getPresenter() {
        return null;
    }
}
