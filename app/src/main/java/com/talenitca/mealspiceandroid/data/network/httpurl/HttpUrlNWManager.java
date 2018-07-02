package com.talenitca.mealspiceandroid.data.network.httpurl;

import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.talenitca.mealspiceandroid.data.DataManager;
import com.talenitca.mealspiceandroid.data.models.Comment;
import com.talenitca.mealspiceandroid.data.models.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlNWManager extends AsyncTask<Void, Void, String> {

    private int mPage;
    private DataManager.Callback<List<Restaurant>> mCallback;
    private List<Restaurant> mRestaurantList;

    public HttpUrlNWManager(int page, DataManager.Callback<List<Restaurant>> callback) {
        mPage = page;
        mCallback = callback;
        mRestaurantList = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            URL url = new URL("https://pmd-test-app-001.herokuapp.com/restaurants");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = inputStreamToString(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONArray restaurantArray = new JSONArray(s);
            for (int i = 0; i < restaurantArray.length(); i++) {
                JSONObject restaurantJson = restaurantArray.getJSONObject(i);
                Restaurant restaurant = new Restaurant();

                restaurant.setAddress(restaurantJson.getString("address"));
                restaurant.setCity(restaurantJson.getString("city"));
                restaurant.setCountry(restaurantJson.getString("country"));
                restaurant.setCuisine(restaurantJson.getString("cuisine"));
                restaurant.setEmail(restaurantJson.getString("email"));
                restaurant.setEstablished(restaurantJson.getLong("established"));
                restaurant.setHasBranches(restaurantJson.getBoolean("hasBranches"));
                restaurant.setLatitude(restaurantJson.getString("latitude"));
                restaurant.setLongitude(restaurantJson.getString("longitude"));
                restaurant.setName(restaurantJson.getString("name"));
                restaurant.setPic(restaurantJson.getString("pic"));
                restaurant.setSlug(restaurantJson.getString("slug"));
                restaurant.setRating(restaurantJson.getLong("rating"));
                restaurant.setTagline(restaurantJson.getString("tagline"));
                restaurant.setUpdated(restaurantJson.getString("updated"));
                restaurant.setId(restaurantJson.getString("_id"));

                JSONArray commentsArray = restaurantJson.getJSONArray("comments");

                for (int j = 0; j < commentsArray.length(); j++) {
                    JSONObject commentJson = commentsArray.getJSONObject(j);

                    Comment comment = new Comment();
                    comment.setBody(commentJson.getString("body"));
                    comment.setCommentedBy(commentJson.getString("commented_by"));
                    comment.setDate(commentJson.getString("date"));
                    comment.setId(commentJson.getString("_id"));

                    restaurant.addComment(comment);
                }
                mRestaurantList.add(restaurant);
            }

        } catch (JSONException e) {
            mCallback.onError(new Throwable("Json parsing error"));
            e.printStackTrace();
        }

        mCallback.onResponse(mRestaurantList);
    }

    private String inputStreamToString(InputStream inputStream) {
        String line = "";
        StringBuilder result = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader rd = new BufferedReader(isr);

        try {
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
