package com.example.ali.moviedb.Contracts;

import com.example.ali.moviedb.Models.Movie;

import java.util.ArrayList;

import retrofit2.Response;

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
    }

    interface InterActor {

        interface OnLoadFinishedListener{

            void onSuccess(ArrayList<Movie> movies);

            void onError();
        }

        void getPopularMovies(String ApiKey,OnLoadFinishedListener onLoadFinishedListener);
        void getAverageMovies();
        void getMoviesFromDB();
    }
}
