/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.training.tiennguyen.twitterproject.R;
import com.training.tiennguyen.twitterproject.models.TweetModel;
import com.training.tiennguyen.twitterproject.services.TwitterApplication;
import com.training.tiennguyen.twitterproject.services.TwitterClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    @BindView(R.id.rvTweets)
    protected RecyclerView rvTweets;
    private TwitterClient client;
    private TimelineAdapter adapter;
    private List<TweetModel> tweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        tweets = new ArrayList<>();
        adapter = new TimelineAdapter(this, tweets);
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        rvTweets.setLayoutManager(manager);
        rvTweets.setAdapter(adapter);

        client = TwitterApplication.getRestClient();
        fetchTimeline();
    }

    /**
     * fetchTimeline
     */
    private void fetchTimeline() {
        client.getHomeTimeLine(0, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG_SUCCESS", response.toString());

                adapter.setTweets(TweetModel.fromArrayJson(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG_FAILURE", errorResponse.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.action_setting:
                return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
