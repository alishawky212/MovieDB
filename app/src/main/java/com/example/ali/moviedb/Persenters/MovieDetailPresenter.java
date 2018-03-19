package com.example.ali.moviedb.Persenters;

import android.content.Context;

import com.example.ali.moviedb.Contracts.MovieDetailContracts;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.Models.Review;
import com.example.ali.moviedb.Models.Trailer;
import com.example.ali.moviedb.R;

import java.util.ArrayList;

import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Created by ali on 2/6/2018.
 */

public class MovieDetailPresenter implements MovieDetailContracts.MovieDetailPersenter, MovieDetailContracts.MovieDetailInteractor.OnLoadReviewsFinishedListener,
        MovieDetailContracts.MovieDetailInteractor.OnLoadTrailersFinishedListener {


    private MovieDetailContracts.MovieDetailView movieDetailView;
    private MovieDetailContracts.MovieDetailInteractor movieDetailInteractor;
    private Context context;

    public MovieDetailPresenter(MovieDetailContracts.MovieDetailView movieDetailView, MovieDetailContracts.MovieDetailInteractor movieDetailInteractor, Context context) {
        this.movieDetailView = movieDetailView;
        this.movieDetailInteractor = movieDetailInteractor;
        this.context = context;
    }

    @Override
    public void getTrailers(int MovieId) {
        movieDetailInteractor.getTrailers(MovieId, context.getString(R.string.APIKEY), this);
    }

    @Override
    public void getReviews(int MovieId) {
        movieDetailInteractor.getReviews(MovieId, context.getString(R.string.APIKEY), this);
    }

    @Override
    public void saveMovie(Movie movie) {
        movieDetailInteractor.saveMovie(movie);
    }

    @Override
    public void onDestroy() {
        movieDetailView = null;
    }

    @Override
    public void isFavorite(int id) {
        movieDetailInteractor.CheckIsFavorite(id)
                .subscribeWith(new DisposableMaybeObserver<Movie>() {
                    @Override
                    public void onSuccess(Movie movie) {
                        movieDetailView.onFoundMovieInDataBase();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        movieDetailView.movieNotInDatabase();
                    }
                });
    }

    @Override
    public void onTrailersSuccess(ArrayList<Trailer> trailers) {
        movieDetailView.showTrailers(trailers);
    }

    @Override
    public void onTrailersError() {
        movieDetailView.ShowError("There Is Error Fetching Trailers");
    }

    @Override
    public void onReviewsSuccess(ArrayList<Review> reviews) {
        movieDetailView.showReviews(reviews);
    }

    @Override
    public void onReviewsError() {
        movieDetailView.ShowError("There Error On Fetching Reviews");
    }

}
