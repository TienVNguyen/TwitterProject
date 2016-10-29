/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.training.tiennguyen.twitterproject.R;
import com.training.tiennguyen.twitterproject.services.TwitterClient;
import com.training.tiennguyen.twitterproject.timeline.TimelineActivity;

/**
 * {@link LoginActivity}
 *
 * @author TienVNguyen
 */
public class LoginActivity extends OAuthLoginActionBarActivity<TwitterClient> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public void onLoginSuccess() {
        final Intent i = new Intent(this, TimelineActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onLoginFailure(Exception e) {
        Toast.makeText(this, R.string.login_issue_message, Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    // Click handler method for the button used to start OAuth flow
    // Uses the client to initiate OAuth authorization
    // This should be tied to a button used to menu_login
    public void loginToRest(View view) {
        getClient().connect();
    }

}
