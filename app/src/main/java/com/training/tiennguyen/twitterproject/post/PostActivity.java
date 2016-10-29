/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.training.tiennguyen.twitterproject.R;
import com.training.tiennguyen.twitterproject.services.TwitterApplication;
import com.training.tiennguyen.twitterproject.services.TwitterClient;
import com.training.tiennguyen.twitterproject.timeline.TimelineActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class PostActivity extends AppCompatActivity {
    @BindView(R.id.edtTweet)
    protected EditText tweet;
    @BindView(R.id.btnPost)
    protected Button btnPost;

    private TwitterClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        setUpActionBar();
        setUpAPI();
        setUpPostAction();
    }

    /**
     * setUpActionBar
     */
    private void setUpActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    /**
     * setUpPostAction
     */
    private void setUpPostAction() {
        btnPost.setOnClickListener(view -> {
            String tweetBody = tweet.getText().toString();
            if (0 < tweetBody.length())
                mClient.postTweet(tweetBody, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Intent intent = new Intent(PostActivity.this, TimelineActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("DEBUG_FAILURE", error.toString());
                    }
                });
        });
    }

    /**
     * setUpAPI
     */
    private void setUpAPI() {
        mClient = TwitterApplication.getRestClient();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
