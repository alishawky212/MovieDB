package com.example.ali.moviedb.Contracts;

import com.example.ali.moviedb.Models.Movie;

import java.util.ArrayList;

/**
 * Created by ali on 2/4/2018.
 */

public interface PosterFragmentMVP {

    interface View{
        void showMovies(ArrayList<Movie> movies);
        void showError();
    }

    interface Presenter {
        void getMovies();

        void onDestroy();

        void updateSharedPreferance(String sortMethod);
    }

    interface InterActor {

        void getPopularMovies(String ApiKey, OnLoadFinishedListener onLoadFinishedListener);

        void getAverageMovies(String ApiKey, OnLoadFinishedListener onLoadFinishedListener);

        void getMoviesFromDB();
        interface OnLoadFinishedListener{

            void onSuccess(ArrayList<Movie> movies);

            void onError();
        }
    }
}
