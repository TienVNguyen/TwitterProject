/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.twitterproject.timeline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.training.tiennguyen.twitterproject.R;
import com.training.tiennguyen.twitterproject.models.TweetModel;
import com.training.tiennguyen.twitterproject.utils.helpers.DateHelper;
import com.training.tiennguyen.twitterproject.utils.helpers.IntentHelpers;

import java.util.List;

/**
 * {@link TimelineAdapter}
 *
 * @author TienNguyen
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineViewHolder> {
    private List<TweetModel> tweets;
    private Context context;

    public TimelineAdapter(final Context context, final List<TweetModel> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    /**
     * setTweet
     *
     * @param tweets {@link List<TweetModel>}
     */
    public void setTweets(final List<TweetModel> tweets) {
        this.tweets.clear();
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }

    /**
     * setMoreArticles
     *
     * @param tweets {@link List<TweetModel>}
     */
    public void setMoreTweets(final List<TweetModel> tweets) {
        this.tweets.addAll(tweets);
        notifyItemRangeChanged(getItemCount() - 1, tweets.size());
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimelineViewHolder(LayoutInflater.from(context).inflate(R.layout.item_timeline, parent, false));
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        final TweetModel model = tweets.get(position);

        setUpProfileImageUrl(model, holder);
        setUpText(model, holder.text);
        setUpTimestamp(model, holder.createdAt);

        holder.shared.setOnClickListener(view ->
                IntentHelpers.shareAction(model.getText(), holder.itemView.getContext())); // TODO: URL
    }

    /**
     * setUpTimestamp
     *
     * @param model {@link TweetModel}
     * @param view  {@link TextView}
     */
    private void setUpTimestamp(final TweetModel model, final TextView view) {
        final DateHelper dateHelper = new DateHelper();
        final String timestamp = dateHelper.getRelativeTimeAgo(model.getCreatedAt());
        if (timestamp.length() > 0) {
            view.setText(timestamp);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * setUpText
     *
     * @param model {@link TweetModel}
     * @param body  {@link TextView}
     */
    private void setUpText(final TweetModel model, final TextView body) {
        if (null != model.getText() && 0 < model.getText().length()) {
            body.setText(model.getText());
        } else {
            body.setVisibility(View.GONE);
        }
    }

    /**
     * setUpProfileImageUrl
     *
     * @param model  {@link TweetModel}
     * @param holder {@link TimelineViewHolder}
     */
    private void setUpProfileImageUrl(final TweetModel model, final TimelineViewHolder holder) {
       /* Glide.with(holder.itemView.getContext())
                .load(model.getUserModel().getProfileImageUrl())
                .placeholder(R.drawable.ic_launcher)
                .into(holder.profileImageUrl);*/
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }
}
