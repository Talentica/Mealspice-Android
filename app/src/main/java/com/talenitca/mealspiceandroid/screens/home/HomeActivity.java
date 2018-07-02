package com.talenitca.mealspiceandroid.screens.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.talenitca.mealspiceandroid.R;
import com.talenitca.mealspiceandroid.data.models.Restaurant;
import com.talenitca.mealspiceandroid.screens.details.DetailsActivity;
import com.talenitca.mealspiceandroid.screens.details.DetailsContract;

import java.util.List;

interface IRestaurantListClickListener{
    public void onRestaurantClicked(String slug);
}
public class HomeActivity extends AppCompatActivity implements HomeContract.View,IRestaurantListClickListener {

    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private HomeContract.Presenter mPresenter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerView = findViewById(R.id.activity_home_recyclerview);
        mProgressBar = findViewById(R.id.activity_home_progressbar);

        mAdapter = new HomeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mPresenter = new HomePresenter(this);
        mPresenter.fetchAllData();
    }


    public void navigateToDetails(View view) {
        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra("slug", "a-b-c");
        startActivity(detailsIntent);
    }

    @Override
    public void loadRestaurants(List<Restaurant> restaurantList) {
        if (mAdapter != null) mAdapter.setData(restaurantList);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this,throwable.getLocalizedMessage(),Toast.LENGTH_LONG).show();
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
    public void onRestaurantClicked(String slug) {
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("slug",slug);
        startActivity(intent);
    }
}
