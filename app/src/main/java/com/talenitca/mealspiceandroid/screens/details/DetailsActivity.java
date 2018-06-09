package com.talenitca.mealspiceandroid.screens.details;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.talenitca.mealspiceandroid.R;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private DetailsContract.Presenter presenter;

    // UI elements
    private ImageView imgRestaurant;
    private RatingBar ratingBar;
    private TextView tvName, tvTagline, tvLocation, tvCuisine;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeUI();

        Bundle extras = getIntent().getExtras();

        if(extras==null || !extras.containsKey("slug")){
            finish();
            return;
        }

        presenter = new DetailsPresenter(this);
        presenter.start(extras.getString("slug"));
    }

    private void initializeUI() {
        toolbar = findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imgRestaurant = findViewById(R.id.collapsing_toolbar_image_view);
        ratingBar = findViewById(R.id.ratingBar);
        tvName = findViewById(R.id.tv_restaurant_name);
        tvTagline = findViewById(R.id.tv_restaurant_tagline);
        tvLocation = findViewById(R.id.tv_restaurant_location);
        tvCuisine = findViewById(R.id.tv_restaurant_cuisine);
    }

    @Override
    public void loadRestaurantPic(String url) {
        Picasso.with(this)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imgRestaurant);
        imgRestaurant.setAlpha(1.0f);
    }

    @Override
    public void loadRestaurantName(String name) {
        toolbar.setTitle(name);
        tvName.setText(name);
    }

    @Override
    public void loadRatings(String rating) {
        ratingBar.setRating(Float.parseFloat(rating));
    }

    @Override
    public void loadTagline(String tagline) {
        tvTagline.setText(tagline);
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
