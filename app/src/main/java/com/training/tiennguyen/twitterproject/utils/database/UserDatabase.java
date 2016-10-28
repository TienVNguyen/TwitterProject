/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.utils.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = UserDatabase.USER, version = UserDatabase.VERSION)
public class UserDatabase {

    public static final String USER = "user";

    public static final int VERSION = 1;
}
