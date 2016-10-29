/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * UserModel
 *
 * @author TienNguyen
 */
public class UserModel {

    @SerializedName("id")
    Long id;
    @SerializedName("name")
    String name;
    @SerializedName("default_profile")
    String defaultImage;
    @SerializedName("profile_image_url")
    String profileImageUrl;
    @SerializedName("favourites_count")
    int favouritesCount;


    public UserModel() {
        super();
    }

    /**
     * Parse model from JSON
     *
     * @param object {@link JSONObject}
     */
    public UserModel(JSONObject object) {
        super();

        try {
            this.id = object.getLong("id");
            this.name = object.getString("name");
            this.defaultImage = object.getString("default_profile");
            this.profileImageUrl = object.getString("profile_image_url");
            this.favouritesCount = object.getInt("favourites_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * fromJson
     *
     * @param userJson {@link JSONObject}
     * @return {@link UserModel}
     */
    public static UserModel fromJson(final JSONObject userJson) {
        return new UserModel(userJson);
    }

    /**
     * fromArrayJson
     *
     * @param jsonArray {@link JSONArray}
     * @return {@link ArrayList <UserModel>}
     */
    public static ArrayList<UserModel> fromArrayJson(final JSONArray jsonArray) {
        final ArrayList<UserModel> users = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userJson;
            try {
                userJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            if (userJson == null) continue;

            final UserModel user = fromJson(userJson);
            users.add(user);
        }

        return users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public int getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }
}
