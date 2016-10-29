/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.training.tiennguyen.twitterproject.R;
import com.training.tiennguyen.twitterproject.models.ErrorModel;
import com.training.tiennguyen.twitterproject.models.TweetModel;
import com.training.tiennguyen.twitterproject.post.PostActivity;
import com.training.tiennguyen.twitterproject.services.TwitterApplication;
import com.training.tiennguyen.twitterproject.services.TwitterClient;
import com.training.tiennguyen.twitterproject.utils.listeners.EndlessRecyclerViewScrollListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {
    @BindView(R.id.toolbarTimeline)
    protected Toolbar toolbar;
    @BindView(R.id.rLLoading)
    protected RelativeLayout rLLoading;
    @BindView(R.id.pBMore)
    protected ProgressBar pBMore;
    @BindView(R.id.sRLMain)
    protected SwipeRefreshLayout sRLMain;
    @BindView(R.id.rvTweets)
    protected RecyclerView rvTweets;

    private TwitterClient mClient;
    private TimelineAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onResume() {
        super.onResume();

        rLLoading.setVisibility(View.VISIBLE);
        fetchTimeline(20, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setUpAPI();
        setUpRecyclerView();
    }

    /**
     * setUpAPI
     */
    private void setUpAPI() {
        mClient = TwitterApplication.getRestClient();
    }

    /**
     * setUpRecyclerView
     */
    private void setUpRecyclerView() {
        setUpAdapter();
        setUpManager();
        setUpScrollListener();
        setUpPullRefresh();
    }

    /**
     * setUpPullRefresh
     */
    private void setUpPullRefresh() {
        sRLMain.setOnRefreshListener(() -> TimelineActivity.this.fetchTimeline(20, 0));

        sRLMain.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    /**
     * setUpScrollListener
     */
    private void setUpScrollListener() {
        final EndlessRecyclerViewScrollListener mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("PAGE_NUMBER", String.valueOf(page));
                pBMore.setVisibility(View.VISIBLE);
                fetchTimelineMore(10, page);
            }
        };

        rvTweets.addOnScrollListener(mScrollListener);
    }

    /**
     * setUpManager
     */
    private void setUpManager() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvTweets.setLayoutManager(mLayoutManager);
    }

    /**
     * setUpAdapter
     */
    private void setUpAdapter() {
        mAdapter = new TimelineAdapter(this, new ArrayList<>());
        rvTweets.setAdapter(mAdapter);
    }

    /**
     * fetchTimelineMore
     *
     * @param count {@link Integer}
     * @param page  {@link Integer}
     */
    private void fetchTimelineMore(final int count, final int page) {
        mClient.getHomeTimeLine(count, page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG_SUCCESS", response.toString());

                mAdapter.setMoreTweets(TweetModel.fromArrayJson(response));
                sRLMain.setRefreshing(false);
                pBMore.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG_FAILURE", errorResponse.toString());
            }
        });
    }

    /**
     * fetchTimeline
     *
     * @param count {@link Integer}
     * @param page  {@link Integer}
     */
    private void fetchTimeline(final int count, final int page) {
        mClient.getHomeTimeLine(count, page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG_SUCCESS", response.toString());

                mAdapter.setTweets(TweetModel.fromArrayJson(response));
                sRLMain.setRefreshing(false);
                rLLoading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG_FAILURE", errorResponse.toString());

                List<ErrorModel> errorModels = null;
                try {
                    JSONArray object = errorResponse.getJSONArray("errors");
                    errorModels = ErrorModel.fromArrayJson(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (errorModels == null || errorModels.size() == 0)
                    return;

                final ErrorModel errorModel = errorModels.get(0);
                int errorId = errorModel.getId();
                String errorMessage = errorModel.getMessage();
                if (errorId == 88) {
                    // TODO: Server behavior
                } else {
                    // TODO: Handle error
                }
                Log.d("DEBUG_FAILURE_CODE", String.valueOf(errorId));
                Log.d("DEBUG_FAILURE_CODE", errorMessage);
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
        switch (item.getItemId()) {
            case R.id.miTweet:
                final Intent intent = new Intent(TimelineActivity.this, PostActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
