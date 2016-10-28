/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.services;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;


/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    private static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    private static final String REST_URL = "https://api.twitter.com/1.1";
    private static final String REST_CONSUMER_KEY = "XsMhnhLIeEFkZKsVEVKaEVzyc";
    private static final String REST_CONSUMER_SECRET = "VaIEcWLljIez7x2adBvNxzTC74rvEAJao4jBcV8oGCeShN66Lb";
    private static final String REST_CALLBACK_URL = "oauth://codepathtweets";

    /**
     * Constructor
     *
     * @param context {@link Context}
     */
    public TwitterClient(final Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    /**
     * getHomeTimeLine
     *
     * @param page            {@link Integer}
     * @param responseHandler {@link AsyncHttpResponseHandler}
     */
    public void getHomeTimeLine(final int page, final AsyncHttpResponseHandler responseHandler) {
        final String apiUrl = getApiUrl("statuses/home_timeline.json");
        final RequestParams params = new RequestParams();
        params.put("count", 10);
        params.put("since_id", 1);
        params.put("page", String.valueOf(page));
        getClient().get(apiUrl, params, responseHandler);
    }

    /**
     * postTweet
     *
     * @param body            {@link String}
     * @param responseHandler {@link AsyncHttpResponseHandler}
     */
    public void postTweet(final String body, final AsyncHttpResponseHandler responseHandler) {
        final String apiUrl = getApiUrl("statues/update.json");
        final RequestParams params = new RequestParams();
        params.put("status", body);
        getClient().post(apiUrl, params, responseHandler);
    }
}
