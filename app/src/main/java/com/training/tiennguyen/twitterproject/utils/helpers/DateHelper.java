/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.utils.helpers;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * {@link DateHelper}
 *
 * @author TienNguyen
 */
public class DateHelper {

    /**
     * getRelativeTimeAgo
     *
     * @param rawJsonDate {@link String}
     * @return {@link String}
     */
    public String getRelativeTimeAgo(final String rawJsonDate) {
        final String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        final SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        try {
            final long dateMillis = sf.parse(rawJsonDate).getTime();
            return DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
}
