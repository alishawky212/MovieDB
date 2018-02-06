package com.example.ali.moviedb.Views;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ali.moviedb.Contracts.MovieDetailContracts;
import com.example.ali.moviedb.DI.Modules.MovieDetailFragmentModule;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.Models.Review;
import com.example.ali.moviedb.Models.Trailer;
import com.example.ali.moviedb.MyApplication;
import com.example.ali.moviedb.R;
import com.example.ali.moviedb.Utillites.DateTimeHelper;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ali on 2/4/2018.
 */

public class MovieDetailFragment extends Fragment implements MovieDetailContracts.MovieDetailView {

    @BindView(R.id.textview_original_title)
    TextView mTvOriginalTitle;

    @BindView(R.id.imageview_poster)
    ImageView mIvPoster;

    @BindView(R.id.textview_release_date)
    TextView mTvReleaseDate;

    @BindView(R.id.textview_vote_average)
    TextView mTvVoteAverage;

    @BindView(R.id.linear_layout_reviews)
    LinearLayout mLlReviews;

    @BindView(R.id.linear_layout_trailers)
    LinearLayout mLlTrailers;

    @BindView(R.id.textview_overview)
    TextView mTvOverView;
    @Inject
    MovieDetailContracts.MovieDetailPersenter movieDetailPersenter;
    private Movie mMovie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        MyApplication.getInstance().getComponent().plus(new MovieDetailFragmentModule(this, getActivity())).inject(this);

        Bundle arguments = getArguments();
        String key = getString(R.string.parcel_movie);
        if (savedInstanceState != null && savedInstanceState.containsKey(key)) {
            mMovie = savedInstanceState.getParcelable(key);
        } else if (arguments != null && arguments.containsKey(key)) {
            mMovie = arguments.getParcelable(key);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_details, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (mMovie != null) {
            mTvOriginalTitle.setText(mMovie.getOriginalTitle());
            Picasso.with(getActivity())
                    .load(getString(R.string.tmdb_posterpath_w185) + mMovie.getPosterPath())
                    .resize(185,
                            278)
                    .error(R.drawable.x)
                    .placeholder(R.drawable.searching)
                    .into(mIvPoster);

            String overView = mMovie.getOverview();
            if (overView == null) {
                mTvOverView.setTypeface(null, Typeface.ITALIC);
                overView = getResources().getString(R.string.no_summary_found);
            }
            mTvOverView.setText(overView);
            mTvVoteAverage.setText(mMovie.getDetailedVoteAverage());

            // First get the release date from the object - to be used if something goes wrong with
            // getting localized release date (catch).
            String releaseDate = mMovie.getReleaseDate();
            if (releaseDate != null) {
                try {
                    releaseDate = DateTimeHelper.getLocalizedDate(getActivity(),
                            releaseDate, mMovie.getDateFormat());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                mTvReleaseDate.setTypeface(null, Typeface.ITALIC);
                releaseDate = getResources().getString(R.string.no_release_date_found);
            }
            mTvReleaseDate.setText(releaseDate);
        }
    }

    @Override
    public void showMovieDetail() {

    }

    @Override
    public void showTrailers(ArrayList<Trailer> trailers) {

    }

    @Override
    public void showReviews(ArrayList<Review> reviews) {

    }

    @Override
    public void ShowError(String Error) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_favorite, menu);

        final MenuItem fav = menu.findItem(R.id.fav);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.fav:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
