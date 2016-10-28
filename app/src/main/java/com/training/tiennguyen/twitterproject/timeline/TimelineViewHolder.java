/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.tiennguyen.twitterproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link TimelineViewHolder}
 *
 * @author TienNguyen
 */
public class TimelineViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemIvProfileImageUrl)
    protected ImageView profileImageUrl;
    @BindView(R.id.itemTvText)
    protected TextView text;
    @BindView(R.id.itemTvCreatedAt)
    protected TextView createdAt;
    @BindView(R.id.itemIvShared)
    protected ImageView shared;

    public TimelineViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
