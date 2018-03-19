package com.example.ali.moviedb.Interactors;

import com.example.ali.moviedb.Contracts.APIServices;
import com.example.ali.moviedb.Contracts.PosterFragmentMVP;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.Models.MoviesWrapper;
import com.example.ali.moviedb.RoomDB.MovieDao;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ali on 2/4/2018.
 */

public class PosterFragmentInteractor implements PosterFragmentMVP.InterActor {


    APIServices.TMDbPopular tmDbPopular;
    APIServices.TMDbServiceTopRated tmDbServiceTopRated;
    MovieDao movieDao;

    public PosterFragmentInteractor(APIServices.TMDbPopular tmDbPopular, APIServices.TMDbServiceTopRated tmDbServiceTopRated, MovieDao movieDao) {
        this.tmDbPopular = tmDbPopular;
        this.tmDbServiceTopRated = tmDbServiceTopRated;
        this.movieDao = movieDao;
    }

    @Override
    public Single<MoviesWrapper> getPopularMovies(String API) {


        return tmDbPopular.getMovies(API)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<MoviesWrapper> getAverageMovies(String API) {

        return tmDbServiceTopRated.getMovies(API)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Flowable<List<Movie>> getMoviesFromDB() {

        return movieDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
