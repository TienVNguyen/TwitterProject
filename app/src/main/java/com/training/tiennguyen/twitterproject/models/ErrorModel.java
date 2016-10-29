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
 * ErrorModel
 *
 * @author TienNguyen
 */
public class ErrorModel {

    @SerializedName("id")
    int id;
    @SerializedName("message")
    String message;

    public ErrorModel() {
        super();
    }

    public ErrorModel(JSONObject object) {
        super();

        try {
            this.id = object.getInt("id");
            this.message = object.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * fromJson
     *
     * @param errorJson {@link JSONObject}
     */
    public static ErrorModel fromJson(final JSONObject errorJson) {
        return new ErrorModel(errorJson);
    }

    /**
     * fromArrayJson
     *
     * @param jsonArray {@link JSONArray}
     * @return {@link ArrayList <ErrorModel>}
     */
    public static ArrayList<ErrorModel> fromArrayJson(final JSONArray jsonArray) {
        final ArrayList<ErrorModel> errors = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject errorJson;
            try {
                errorJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            if (errorJson == null) continue;

            final ErrorModel error = fromJson(errorJson);
            errors.add(error);
        }

        return errors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
