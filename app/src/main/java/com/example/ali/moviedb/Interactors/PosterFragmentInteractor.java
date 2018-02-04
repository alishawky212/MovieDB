package com.example.ali.moviedb.Interactors;

import com.example.ali.moviedb.Contracts.APIServices;
import com.example.ali.moviedb.Contracts.PosterFragmentMVP;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.Models.MoviesWrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ali on 2/4/2018.
 */

public class PosterFragmentInteractor implements PosterFragmentMVP.InterActor {


    APIServices.TMDbPopular tmDbPopular;
    APIServices.TMDbServiceTopRated tmDbServiceTopRated;

    public PosterFragmentInteractor(APIServices.TMDbPopular tmDbPopular, APIServices.TMDbServiceTopRated tmDbServiceTopRated) {
        this.tmDbPopular = tmDbPopular;
        this.tmDbServiceTopRated = tmDbServiceTopRated;
    }

    @Override
    public void getPopularMovies(String API ,final OnLoadFinishedListener listener) {

        Call<MoviesWrapper> call = tmDbPopular.getMovies(API);

        call.enqueue(new Callback<MoviesWrapper>() {
            @Override
            public void onResponse(Call<MoviesWrapper> call, Response<MoviesWrapper> response) {

                ArrayList<Movie> movies = (ArrayList<Movie>) response.body().getResults();

                listener.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<MoviesWrapper> call, Throwable t) {
                listener.onError();
            }
        });
    }

    @Override
    public void getAverageMovies() {

    }

    @Override
    public void getMoviesFromDB() {

    }
}
