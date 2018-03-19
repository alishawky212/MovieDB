package com.example.ali.moviedb.Persenters;

import android.util.Log;

import com.example.ali.moviedb.Contracts.PosterFragmentMVP;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.Models.MoviesWrapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subscribers.DisposableSubscriber;


/**
 * Created by ali on 2/4/2018.
 */

public class PosterFragmentPresenter implements PosterFragmentMVP.Presenter {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PosterFragmentMVP.View view;
    private PosterFragmentMVP.InterActor interActor;

    public PosterFragmentPresenter(PosterFragmentMVP.InterActor interActor) {
        this.interActor = interActor;
    }

    @Override
    public void getMovies(String APIKEY) {

        if (view.checkForInternet())
        {
            switch (view.getSortMethod()) {
                case "vote_average.desc":
                    compositeDisposable.add(interActor.getAverageMovies(APIKEY)
                            .subscribeWith(new DisposableSingleObserver<MoviesWrapper>() {
                                @Override
                                public void onSuccess(MoviesWrapper moviesWrapper) {
                                    view.showMovies((ArrayList<Movie>) moviesWrapper.getResults());
                                }

                                @Override
                                public void onError(Throwable e) {
                                    view.showError(e.getMessage());
                                }
                            }));
                    break;
                case "popularity.desc":
                    compositeDisposable.add(interActor.getPopularMovies(APIKEY)
                            .subscribeWith(new DisposableSingleObserver<MoviesWrapper>() {
                                @Override
                                public void onSuccess(MoviesWrapper moviesWrapper) {
                                    view.showMovies((ArrayList<Movie>) moviesWrapper.getResults());
                                }

                                @Override
                                public void onError(Throwable e) {
                                    view.showError(e.getMessage());
                                }
                            }));

                    break;
            }
        } else {
            view.showError("Check your Internet");
        }

    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        view = null;
    }

    @Override
    public void setView(PosterFragmentMVP.View view) {
        this.view = view;
    }

    @Override
    public void getFavoriteMovies() {

        compositeDisposable.add(interActor.getMoviesFromDB()
                .subscribeWith(new DisposableSubscriber<List<Movie>>() {
                    @Override
                    public void onNext(List<Movie> movies) {
                        if (view.getSortMethod().equalsIgnoreCase("Favorite"))
                            view.showMovies((ArrayList<Movie>) movies);
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("onComplete", "onComplete");
                    }
                }));
    }
}
