package com.example.ali.moviedb.Interactors;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ali.moviedb.Contracts.APIServices;
import com.example.ali.moviedb.Contracts.MovieDetailContracts;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.Models.Review;
import com.example.ali.moviedb.Models.ReviewsWrapper;
import com.example.ali.moviedb.Models.Trailer;
import com.example.ali.moviedb.Models.TrailersWrapper;
import com.example.ali.moviedb.RoomDB.MovieDao;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ali on 2/6/2018.
 */

public class MovieDetailInteractor implements MovieDetailContracts.MovieDetailInteractor {


    APIServices.TrailersService trailersService;
    APIServices.ReviewsService reviewsService;
    MovieDao movieDao;

    public MovieDetailInteractor(APIServices.TrailersService trailersService, APIServices.ReviewsService reviewsService, MovieDao movieDao) {
        this.trailersService = trailersService;
        this.reviewsService = reviewsService;
        this.movieDao = movieDao;
    }

    @Override
    public void getTrailers(int MovieId, String API, final OnLoadTrailersFinishedListener listener) {

        Call<TrailersWrapper> call = trailersService.getTrailers(MovieId, API);

        call.enqueue(new Callback<TrailersWrapper>() {
            @Override
            public void onResponse(Call<TrailersWrapper> call, Response<TrailersWrapper> response) {
                listener.onTrailersSuccess((ArrayList<Trailer>) response.body().getResults());
            }

            @Override
            public void onFailure(Call<TrailersWrapper> call, Throwable t) {
                listener.onTrailersError();
            }
        });
    }

    @Override
    public void getReviews(int MovieId, String API, final OnLoadReviewsFinishedListener listener) {

        Call<ReviewsWrapper> call = reviewsService.getReviews(MovieId, API);

        call.enqueue(new Callback<ReviewsWrapper>() {
            @Override
            public void onResponse(Call<ReviewsWrapper> call, Response<ReviewsWrapper> response) {
                listener.onReviewsSuccess((ArrayList<Review>) response.body().getResults());
            }

            @Override
            public void onFailure(Call<ReviewsWrapper> call, Throwable t) {
                listener.onReviewsError();
            }
        });
    }

    @Override
    public void saveMovie(final Movie movie) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                movieDao.insertAll(movie);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("s", "sus");
            }
        }.execute();
    }
}
