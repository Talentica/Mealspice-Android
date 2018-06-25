package com.talenitca.mealspiceandroid.screens.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.talenitca.mealspiceandroid.R;
import com.talenitca.mealspiceandroid.data.models.Restaurant;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.FeedViewHolder> {
    private List<Restaurant> mRestaurants;

    public void setData(List<Restaurant> restaurants) {
        this.mRestaurants = restaurants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.feed_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        if (mRestaurants != null) holder.bind(mRestaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return mRestaurants == null ? 0 : mRestaurants.size();
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {

        private TextView restaurantName;
        private TextView restaurantAddress;
        private TextView restaurantDetails;
        private ImageView restaurantImage;

        public FeedViewHolder(View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantAddress = itemView.findViewById(R.id.restaurant_address);
            restaurantDetails = itemView.findViewById(R.id.restaurant_info);
            restaurantImage = itemView.findViewById(R.id.restaurant_image);
        }

        public void bind(Restaurant restaurant) {
            restaurantName.setText(restaurant.getName());
            restaurantAddress.setText(restaurant.getAddress());
            restaurantDetails.setText("Established on : " + restaurant.getEstablished());
            Picasso.with(restaurantImage.getContext()).load(restaurant.getPic()).centerCrop().fit().into(restaurantImage);
        }
    }
}
