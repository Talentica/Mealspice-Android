package com.talenitca.mealspiceandroid.screens.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.talenitca.mealspiceandroid.R;
import com.talenitca.mealspiceandroid.screens.details.DetailsActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void navigateToDetails(View view) {
        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra("slug", "a-b-c");
        startActivity(detailsIntent);
    }
}
