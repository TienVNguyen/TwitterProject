/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.utils.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = TweetDatabase.TWEET, version = TweetDatabase.VERSION)
public class TweetDatabase {

    public static final String TWEET = "tweet";

    public static final int VERSION = 1;
}
