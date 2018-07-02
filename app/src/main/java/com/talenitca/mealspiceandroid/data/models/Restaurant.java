
package com.talenitca.mealspiceandroid.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("city")
    private String mCity;
    @SerializedName("comments")
    private List<Comment> mComments;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("cuisine")
    private String mCuisine;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("established")
    private Long mEstablished;
    @SerializedName("hasBranches")
    private Boolean mHasBranches;
    @SerializedName("latitude")
    private String mLatitude;
    @SerializedName("longitude")
    private String mLongitude;
    @SerializedName("name")
    private String mName;
    @SerializedName("pic")
    private String mPic;
    @SerializedName("rating")
    private Long mRating;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("tagline")
    private String mTagline;
    @SerializedName("updated")
    private String mUpdated;
    @SerializedName("_id")
    private String m_id;

    public void addComment(Comment comment){
        if(mComments == null) {
            mComments = new ArrayList<>();
        }
        mComments.add(comment);
    }
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCuisine() {
        return mCuisine;
    }

    public void setCuisine(String cuisine) {
        mCuisine = cuisine;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Long getEstablished() {
        return mEstablished;
    }

    public void setEstablished(Long established) {
        mEstablished = established;
    }

    public Boolean getHasBranches() {
        return mHasBranches;
    }

    public void setHasBranches(Boolean hasBranches) {
        mHasBranches = hasBranches;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPic() {
        return mPic;
    }

    public void setPic(String pic) {
        mPic = pic;
    }

    public Long getRating() {
        return mRating;
    }

    public void setRating(Long rating) {
        mRating = rating;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getTagline() {
        return mTagline;
    }

    public void setTagline(String tagline) {
        mTagline = tagline;
    }

    public String getUpdated() {
        return mUpdated;
    }

    public void setUpdated(String updated) {
        mUpdated = updated;
    }

    public String getId() {
        return m_id;
    }

    public void setId(String _id) {
        m_id = _id;
    }

}
