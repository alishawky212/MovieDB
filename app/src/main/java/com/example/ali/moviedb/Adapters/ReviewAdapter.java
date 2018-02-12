package com.example.ali.moviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ali.moviedb.Models.Review;
import com.example.ali.moviedb.R;

import java.util.List;

/**
 * Created by ali on 4/5/2017.
 */

public class ReviewAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Review> mReviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        mContext = context;
        mReviews = reviews;
    }

    @Override
    public int getCount() {
        if (mReviews == null || mReviews.isEmpty()) {
            return -1;
        }

        return mReviews.size();
    }

    @Override
    public Review getItem(int position) {
        if (mReviews == null || mReviews.isEmpty()) {
            return null;
        }

        return mReviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvReviewer;
        TextView tvReview;

        // Will be null if it's not recycled. Will initialize ImageView if new.
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.review_listitem, null);
        }

        tvReviewer = convertView.findViewById(R.id.reviewer);
        tvReviewer.setText(mReviews.get(position).getAuthor());

        tvReview = convertView.findViewById(R.id.review);
        tvReview.setText(mReviews.get(position).getContent());

        return convertView;
    }
}
