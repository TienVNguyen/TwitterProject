/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.training.tiennguyen.twitterproject.R;
import com.training.tiennguyen.twitterproject.login.LoginActivity;
import com.training.tiennguyen.twitterproject.utils.contants.IntentConstant;

/**
 * {@link SplashActivity}
 *
 * @author TienVNguyen
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        setUpDelayAction();
    }

    /**
     * DelayAction
     */
    private void setUpDelayAction() {
        new Handler().postDelayed(this::runnableAction, IntentConstant.SPLASH_TIME_OUT);
    }

    /**
     * runnableAction
     */
    private void runnableAction() {
        final Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
