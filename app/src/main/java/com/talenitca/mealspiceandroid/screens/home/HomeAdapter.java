package com.talenitca.mealspiceandroid.screens.home;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;
import com.talenitca.mealspiceandroid.R;
import com.talenitca.mealspiceandroid.data.models.ListItem;
import com.talenitca.mealspiceandroid.data.models.Restaurant;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListItem> mItemList;
    private IRestaurantListClickListener mListener;

    //two types of view
    public static final int ITEM_TYPE_LIST = 0;
    public static final int ITEM_TYPE_NATIVE_AD= 1;

    public HomeAdapter(IRestaurantListClickListener listener) {
        mListener = listener;
    }

    public void setData(List<ListItem> listItems) {
        this.mItemList = listItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ListItem recyclerViewItem = mItemList.get(position);
        return recyclerViewItem.getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_NATIVE_AD:
                View unifiedNativeLayoutView = LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.native_ad_item,
                        parent, false);
                return new NativeAdViewHolder(unifiedNativeLayoutView);
            case ITEM_TYPE_LIST:
            default:
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.feed_item, parent, false);
                return new FeedViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if(mItemList== null) {
            return;
        }
        if(viewType == ITEM_TYPE_LIST) {
            ListItem listItem = mItemList.get(position);
            if(listItem != null) {
                ((FeedViewHolder) holder).bind(listItem.getRestaurant());
            }
        } else if(viewType== ITEM_TYPE_NATIVE_AD) {
            ListItem listItem = mItemList.get(position);
            if(listItem != null) {
                ((NativeAdViewHolder) holder).populateNativeAdView(listItem.getNativeAd());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {

        private TextView restaurantName;
        private TextView restaurantAddress;
        private TextView restaurantDetails;
        private ImageView restaurantImage;
        private CardView mCardView;

        FeedViewHolder(View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantAddress = itemView.findViewById(R.id.restaurant_address);
            restaurantDetails = itemView.findViewById(R.id.restaurant_info);
            restaurantImage = itemView.findViewById(R.id.restaurant_image);
            mCardView = itemView.findViewById(R.id.parent_cardview);
        }

        @SuppressLint("CheckResult")
        void bind(final Restaurant restaurant) {
            restaurantName.setText(restaurant.getName());
            restaurantAddress.setText(restaurant.getAddress());
            restaurantDetails.setText(String.format(itemView.getContext().getString(R.string
                    .txt_established_on), restaurant.getEstablished()));
            Picasso.with(restaurantImage.getContext())
                    .load(restaurant.getPic())
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop().fit().into(restaurantImage);


            RxView.clicks(mCardView)
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe(o -> mListener.onRestaurantClicked(restaurant),
                            throwable -> Log.e("Adapter", "bind: error"));
        }
    }

    interface IRestaurantListClickListener {
        void onRestaurantClicked(Restaurant restaurant);
    }
}
