package com.talenitca.mealspiceandroid.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talenitca.mealspiceandroid.data.models.Restaurant;

import java.lang.reflect.Type;
import java.util.List;

public class TestUtils {

    private static final String singleRestaurant = "{\n" +
            "  \"name\": \"Sun Dance Cafe\",\n" +
            "  \"email\": \"me@gmail.cm\",\n" +
            "  \"tagline\": \"Confluence of herbs, spices and tradition\",\n" +
            "  \"pic\": \"https://punefoodhunt.files.wordpress.com/2017/10/img_20171007_105450-e1509002011194.jpg\",\n" +
            "  \"cuisine\": \"Mexican, Japanese, Seafood\",\n" +
            "  \"address\": \"221B baker street, Paris\",\n" +
            "  \"latitude\": \"40.719269\",\n" +
            "  \"longitude\": \"-73.996846\",\n" +
            "  \"city\": \"Paris\",\n" +
            "  \"country\": \"France\",\n" +
            "  \"hasBranches\": true,\n" +
            "  \"updated\": \"18-Jan-2018\",\n" +
            "  \"established\": \"2011\",\n" +
            "  \"rating\": \"3\",\n" +
            "  \"comments\": [\n" +
            "    {\n" +
            "      \"restaurant_slug\": \"kombi-rocks-diner\",\n" +
            "      \"body\": \"exemplary vegan choices\",\n" +
            "      \"commented_by\": \"Dhruw\",\n" +
            "      \"date\": \"18-Jan-2018\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private static final String restaurantsJson = "[\n" +
            "  {\n" +
            "    \"updated\": \"2018-01-19T00:00:00.000Z\",\n" +
            "    \"established\": 2004,\n" +
            "    \"rating\": 3,\n" +
            "    \"comments\": [],\n" +
            "    \"_id\": \"5aa6433e36e38e0004409d86\",\n" +
            "    \"name\": \"Barbeque Village\",\n" +
            "    \"email\": \"contact@bbqvillage.com\",\n" +
            "    \"tagline\": \"Cook tight eat right\",\n" +
            "    \"pic\": \"https://b.zmtcdn.com/data/pictures/2/18591342/d04aed28b261ffaadd79311c4ccad573.jpg\",\n" +
            "    \"cuisine\": \"North Indian, Awadhi, BBQ\",\n" +
            "    \"address\": \"45, Sus -Parshan Bridge, Near Mahindra, Pune Mumbai -Bangalore Highway, Pashan, Pune\",\n" +
            "    \"city\": \"pune\",\n" +
            "    \"country\": \"India\",\n" +
            "    \"latitude\": \"18.5492644924\",\n" +
            "    \"longitude\": \"73.7723769620\",\n" +
            "    \"hasBranches\": false,\n" +
            "    \"slug\": \"barbeque-village\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"updated\": \"2017-10-06T00:00:00.000Z\",\n" +
            "    \"established\": 2012,\n" +
            "    \"rating\": 4,\n" +
            "    \"comments\": [],\n" +
            "    \"_id\": \"5aa6433e36e38e0004409d87\",\n" +
            "    \"name\": \"Rangla Punjab\",\n" +
            "    \"email\": \"contact@ranglapunjab.com\",\n" +
            "    \"tagline\": \"Carnival of spices and herbs\",\n" +
            "    \"pic\": \"https://b.zmtcdn.com/data/pictures/7/11817/c57e2e745b9267761980e59b2b122523.jpg\",\n" +
            "    \"cuisine\": \"Seafood, Biryani, North Indian, Chinese, Kebab\",\n" +
            "    \"address\": \"Near Kumar Papillon, Bangalore  Mumbai Highway, Off Sus Pashan Road, Pashan, Pune\",\n" +
            "    \"city\": \"pune\",\n" +
            "    \"country\": \"India\",\n" +
            "    \"latitude\": \"18.5425327484\",\n" +
            "    \"longitude\": \"73.7762252614\",\n" +
            "    \"hasBranches\": true,\n" +
            "    \"slug\": \"rangla-punjab\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"updated\": \"2018-02-19T00:00:00.000Z\",\n" +
            "    \"established\": 2017,\n" +
            "    \"rating\": 4,\n" +
            "    \"comments\": [],\n" +
            "    \"_id\": \"5aa6433e36e38e0004409d8f\",\n" +
            "    \"name\": \"Cabaret\",\n" +
            "    \"email\": \"cabaret@peterdonut.com\",\n" +
            "    \"tagline\": \"Dine, wine and, darts\",\n" +
            "    \"pic\": \"https://img1.nbstatic.in/la-webp-l/59c3b0dec9e77c000b1997cf.jpg\",\n" +
            "    \"cuisine\": \"Cafe, Asian, Steak, Korean, Salad, North Indian, Biryani\",\n" +
            "    \"address\": \"Ground floor, Aspirations, Baner Road, Baner, Pune\",\n" +
            "    \"city\": \"pune\",\n" +
            "    \"country\": \"India\",\n" +
            "    \"longitude\": \"18.5660205445\",\n" +
            "    \"latitude\": \"73.7728131562\",\n" +
            "    \"hasBranches\": false,\n" +
            "    \"slug\": \"cabaret\"\n" +
            "  }\n" +
            "]";

    public static List<Restaurant> getMockedRestaurantList() {
        Gson gson = new Gson();
        Type restaurantListType = new TypeToken<List<Restaurant>>() {
        }.getType();
        return gson.fromJson(restaurantsJson, restaurantListType);
    }

    public static Restaurant getMockedRestaurant() {
        Gson gson = new Gson();
        return gson.fromJson(singleRestaurant, Restaurant.class);
    }

}
