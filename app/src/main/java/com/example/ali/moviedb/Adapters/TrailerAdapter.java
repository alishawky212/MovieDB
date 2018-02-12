package com.example.ali.moviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ali.moviedb.Models.Trailer;
import com.example.ali.moviedb.R;

import java.util.List;

/**
 * Created by ali on 4/5/2017.
 */

public class TrailerAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Trailer> mTrailers;

    public TrailerAdapter(Context context, List<Trailer> trailers) {
        this.mContext = context;
        this.mTrailers = trailers;
    }

    @Override
    public int getCount() {
        if (mTrailers == null || mTrailers.isEmpty()) {
            return -1;
        }
        return mTrailers.size();
    }

    @Override
    public Trailer getItem(int position) {
        if (mTrailers == null || mTrailers.isEmpty()) {

            return null;
        }
        return mTrailers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.trailer_listitem, null);
        }

        textView = convertView.findViewById(R.id.trailer_name);

        textView.setText(mTrailers.get(position).getName());

        return convertView;
    }
}
