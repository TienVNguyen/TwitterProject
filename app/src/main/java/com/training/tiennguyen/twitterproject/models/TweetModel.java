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

public class TweetModel {

    @SerializedName("id")
    Long id;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("text")
    String text;
    @SerializedName("user")
    UserModel userModel;

    public TweetModel() {
        super();
    }

    /**
     * Parse model from JSON
     *
     * @param object {@link JSONObject}
     */
    public TweetModel(JSONObject object) {
        super();

        try {
            this.id = object.getLong("id");
            this.createdAt = object.getString("created_at");
            this.text = object.getString("text");
            this.userModel = UserModel.fromJson(object.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * fromJson
     *
     * @param tweetJson {@link JSONObject}
     * @return {@link ArrayList <TweetModel>}
     */
    public static TweetModel fromJson(final JSONObject tweetJson) {
        return new TweetModel(tweetJson);
    }

    /**
     * fromArrayJson
     *
     * @param jsonArray {@link JSONArray}
     * @return {@link ArrayList<TweetModel>}
     */
    public static ArrayList<TweetModel> fromArrayJson(final JSONArray jsonArray) {
        final ArrayList<TweetModel> tweets = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject tweetJson;
            try {
                tweetJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            if (tweetJson == null) continue;

            final TweetModel tweet = fromJson(tweetJson);
            tweets.add(tweet);
        }

        return tweets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
