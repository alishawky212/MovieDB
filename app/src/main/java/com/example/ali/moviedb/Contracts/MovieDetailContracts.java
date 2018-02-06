package com.example.ali.moviedb.Contracts;

import com.example.ali.moviedb.Models.Review;
import com.example.ali.moviedb.Models.Trailer;

import java.util.ArrayList;

/**
 * Created by ali on 2/6/2018.
 */

public interface MovieDetailContracts {

    interface MovieDetailView {
        void showMovieDetail();

        void showTrailers(ArrayList<Trailer> trailers);

        void showReviews(ArrayList<Review> reviews);

        void ShowError(String Error);
    }

    interface MovieDetailPersenter {

        void getTrailers(int id);

        void getReviews(int id);

        void onDestroy();

    }

    interface MovieDetailInteractor {
        void getTrailers(int id, String API, OnLoadTrailersFinishedListener listener);

        void getReviews(int id, String API, OnLoadReviewsFinishedListener listener);

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
