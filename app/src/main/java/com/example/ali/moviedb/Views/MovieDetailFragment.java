package com.example.ali.moviedb.Views;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.ali.moviedb.Adapters.ReviewAdapter;
import com.example.ali.moviedb.Adapters.TrailerAdapter;
import com.example.ali.moviedb.Contracts.MovieDetailContracts;
import com.example.ali.moviedb.DI.Components.DaggerMovieDetailComponent;
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
import java.util.List;

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

    MenuItem fav;

    @Inject
    MovieDetailContracts.MovieDetailPersenter movieDetailPersenter;
    List<Trailer> mTrailers;
    List<Review> mReviews;
    private final LinearLayout.OnClickListener layoutOnClickListener = new LinearLayout.OnClickListener() {
        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            AlertDialog dialog;

            switch (v.getId()) {
                case R.id.linear_layout_trailers:
                    TrailerAdapter trailerAdapter = new TrailerAdapter(getActivity(), mTrailers);

                    dialog = builder.setTitle(getString(R.string.trailers))
                            .setAdapter(trailerAdapter, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Trailer trailer = mTrailers.get(which);
                                    String uri = "http://www.youtube.com/watch?v=" + trailer.getKey();
                                    startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(uri)));
                                }
                            })
                            .create();

                    dialog.show();
                    break;

                case R.id.linear_layout_reviews:

                    ReviewAdapter reviewAdapter = new ReviewAdapter(getActivity(), mReviews);
                    dialog = builder.setTitle(getString(R.string.reviews))
                            .setAdapter(reviewAdapter, null)
                            .create();

                    dialog.show();
                    break;
            }

        }
    };
    private Movie mMovie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        DaggerMovieDetailComponent.builder()
                .aPPComponent(MyApplication.getInstance().getComponent())
                .movieDetailFragmentModule(new MovieDetailFragmentModule(this, getActivity()))
                .build().inject(this);

        Bundle arguments = getArguments();
        String key = getString(R.string.parcel_movie);
        if (savedInstanceState != null && savedInstanceState.containsKey(key)) {
            mMovie = savedInstanceState.getParcelable(key);
        } else if (arguments != null && arguments.containsKey(key)) {
            mMovie = arguments.getParcelable(key);
        }

        movieDetailPersenter.getTrailers(mMovie.getId());
        movieDetailPersenter.getReviews(mMovie.getId());

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

        mLlTrailers.setOnClickListener(layoutOnClickListener);
        mLlTrailers.setClickable(false);

        mLlReviews.setOnClickListener(layoutOnClickListener);
        mLlReviews.setClickable(false);

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
    public void showTrailers(ArrayList<Trailer> trailers) {
        if (trailers.size() > 0) {
            mTrailers = trailers;
            mLlTrailers.setClickable(true);
        } else
            Toast.makeText(getActivity(), "There is no Trailers found", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showReviews(ArrayList<Review> reviews) {
        if (reviews.size() > 0) {
            mReviews = reviews;
            mLlReviews.setClickable(true);
        } else
            Toast.makeText(getActivity(), "There is no Reviews found", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ShowError(String Error) {

    }

    @Override
    public void onFoundMovieInDataBase() {
        fav.setIcon(R.drawable.starfill);
    }

    @Override
    public void movieNotInDatabase() {
        fav.setIcon(R.drawable.star);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_favorite, menu);

        fav = menu.findItem(R.id.fav);


        movieDetailPersenter.isFavorite(mMovie.getId());

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.fav:
                movieDetailPersenter.saveMovie(mMovie);
                item.setIcon(R.drawable.starfill);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieDetailPersenter.onDestroy();
    }
}
