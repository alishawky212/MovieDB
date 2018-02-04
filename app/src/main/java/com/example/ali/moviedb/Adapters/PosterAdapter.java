package com.example.ali.moviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ali on 4/4/2017.
 */

public class PosterAdapter extends BaseAdapter {


    private final Context mContext;
    private List<Movie> mMovies;

    private final LayoutInflater inflater;

    public PosterAdapter(Context context, List<Movie> movies) {
        mContext = context;
        mMovies = movies;
        inflater = null;
    }


    @Override
    public int getCount() {
        if (mMovies == null || mMovies.isEmpty()) {


            return -1;
        }
        return mMovies.size();
    }

    @Override
    public Movie getItem(int position) {
        if (mMovies == null || mMovies.isEmpty()) {
            return null;
        }
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.poster_with_title, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Picasso.with(mContext)
                .load(mContext.getString(R.string.tmdb_posterpath_w185) + mMovies.get(position)
                        .getPosterPath())
                .resize(185,
                        278)
                .error(R.drawable.x)
                .placeholder(R.drawable.searching)
                .into(holder.imageView);
        return convertView;
    }

    public void updateData(List<Movie> movies) {
        this.mMovies = movies;
        notifyDataSetChanged();
    }

    static final class ViewHolder {

        @BindView(R.id.poster)
        ImageView imageView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
