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

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link TimelineViewHolder}
 *
 * @author TienNguyen
 */
public class TimelineViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemIvProfileImageUrl)
    ImageView profileImageUrl;
    @BindView(R.id.itemHtmlTvUser)
    HtmlTextView user;
    @BindView(R.id.itemTvText)
    TextView text;
    @BindView(R.id.itemImgTweet)
    ImageView imgTweet;
    @BindView(R.id.itemImgLike)
    ImageView imgLike;
    @BindView(R.id.itemTxtLikeNumber)
    TextView txtLikeNumber;
    @BindView(R.id.itemIvShared)
    ImageView shared;

    TimelineViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
