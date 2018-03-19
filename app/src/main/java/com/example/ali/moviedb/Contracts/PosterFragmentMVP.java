package com.example.ali.moviedb.Contracts;

import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.Models.MoviesWrapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;


/**
 * Created by ali on 2/4/2018.
 */

public interface PosterFragmentMVP {

    interface View{
        void showMovies(ArrayList<Movie> movies);

        void showError(String message);

        boolean checkForInternet();

        String getSortMethod();
    }

    interface Presenter {
        void getMovies(String APIKey);
        void onDestroy();

        void setView(View view);

        void getFavoriteMovies();
    }

    interface InterActor {

        Single<MoviesWrapper> getPopularMovies(String ApiKey);

        Single<MoviesWrapper> getAverageMovies(String ApiKey);

        Flowable<List<Movie>> getMoviesFromDB();

    }
}
