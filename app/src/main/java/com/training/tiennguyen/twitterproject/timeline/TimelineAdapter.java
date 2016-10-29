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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.training.tiennguyen.twitterproject.R;
import com.training.tiennguyen.twitterproject.models.TweetModel;
import com.training.tiennguyen.twitterproject.utils.helpers.DateHelper;
import com.training.tiennguyen.twitterproject.utils.helpers.IntentHelpers;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * {@link TimelineAdapter}
 *
 * @author TienNguyen
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineViewHolder> {
    private List<TweetModel> mTweets;
    private Context mContext;

    public TimelineAdapter(final Context context, final List<TweetModel> tweets) {
        this.mContext = context;
        this.mTweets = tweets;
    }

    /**
     * setTweet
     *
     * @param tweets {@link List<TweetModel>}
     */
    public void setTweets(final List<TweetModel> tweets) {
        this.mTweets.clear();
        this.mTweets.addAll(tweets);
        notifyDataSetChanged();
    }

    /**
     * setMoreArticles
     *
     * @param tweets {@link List<TweetModel>}
     */
    public void setMoreTweets(final List<TweetModel> tweets) {
        this.mTweets.addAll(tweets);
        notifyItemRangeChanged(getItemCount() - 1, tweets.size());
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimelineViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_timeline, parent, false));
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        final TweetModel model = mTweets.get(position);

        setUpImageRound(model.getUserModel().getProfileImageUrl(), holder.profileImageUrl);
        setUpText(model.getText(), holder.text);
        setUpText(String.valueOf(model.getUserModel().getFavouritesCount()), holder.txtLikeNumber);
        setUpUser(model, holder.user);

        holder.shared.setOnClickListener(view ->
                IntentHelpers.shareAction(model.getText(), holder.itemView.getContext()));
    }

    /**
     * setUpUser
     *
     * @param model {@link TweetModel}
     * @param user  {@link HtmlTextView}
     */
    private void setUpUser(final TweetModel model, final HtmlTextView user) {
        final DateHelper dateHelper = new DateHelper();

        final String userName = model.getUserModel().getName() +
                "  <font color=\"#E0E0E0\" ><i>@" +
                model.getUserModel().getName() +
                "</i></font> . <font color=\"#E0E0E0\" ><i>" +
                dateHelper.getRelativeTimeAgo(model.getCreatedAt()) +
                "</i></font>";

        user.setHtml(userName);
    }

    /**
     * setUpText
     *
     * @param text     {@link String}
     * @param textView {@link TextView}
     */
    private void setUpText(final String text, final TextView textView) {
        if (null != text && 0 < text.length()) {
            textView.setText(text);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    /**
     * setUpImage
     *
     * @param url       {@link String}
     * @param imageView {@link ImageView}
     */
    private void setUpImage(final String url, final ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.ic_launcher)
                .into(imageView);
    }

    /**
     * setUpImageRound
     *
     * @param url       {@link String}
     * @param imageView {@link ImageView}
     */
    private void setUpImageRound(final String url, final ImageView imageView) {
        final CropCircleTransformation circleTransformation = new CropCircleTransformation(mContext);

        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.ic_launcher)
                .bitmapTransform(circleTransformation)
                .into(imageView);
    }


    @Override
    public int getItemCount() {
        return mTweets.size();
    }
}
