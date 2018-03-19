package com.example.ali.moviedb.Contracts;

import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.Models.Review;
import com.example.ali.moviedb.Models.Trailer;

import java.util.ArrayList;

import io.reactivex.Maybe;

/**
 * Created by ali on 2/6/2018.
 */

public interface MovieDetailContracts {

    interface MovieDetailView {
        void showTrailers(ArrayList<Trailer> trailers);

        void showReviews(ArrayList<Review> reviews);

        void ShowError(String Error);

        void onFoundMovieInDataBase();

        void movieNotInDatabase();
    }

    interface MovieDetailPersenter {

        void getTrailers(int id);

        void getReviews(int id);

        void saveMovie(Movie movie);

        void onDestroy();

        void isFavorite(int id);



    }

    interface MovieDetailInteractor {
        void getTrailers(int id, String API, OnLoadTrailersFinishedListener listener);

        void getReviews(int id, String API, OnLoadReviewsFinishedListener listener);

        void saveMovie(Movie movie);

        Maybe<Movie> CheckIsFavorite(int id);

        interface OnIsFoundListener {

            void onFound();

            void onNotFound();
        }

        interface OnLoadTrailersFinishedListener {

            void onTrailersSuccess(ArrayList<Trailer> trailers);

            void onTrailersError();
        }

        interface OnLoadReviewsFinishedListener {

            void onReviewsSuccess(ArrayList<Review> reviews);

            void onReviewsError();
        }
    }
}
