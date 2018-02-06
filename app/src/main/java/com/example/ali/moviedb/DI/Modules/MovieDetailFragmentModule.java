package com.example.ali.moviedb.DI.Modules;

import android.content.Context;

import com.example.ali.moviedb.Contracts.APIServices;
import com.example.ali.moviedb.Contracts.MovieDetailContracts;
import com.example.ali.moviedb.DI.Scopes.MovieDetailFragmentScope;
import com.example.ali.moviedb.Interactors.MovieDetailInteractor;
import com.example.ali.moviedb.Persenters.MovieDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ali on 2/6/2018.
 */

@MovieDetailFragmentScope
@Module
public class MovieDetailFragmentModule {

    MovieDetailContracts.MovieDetailView movieDetailView;
    Context context;

    public MovieDetailFragmentModule(MovieDetailContracts.MovieDetailView movieDetailView, Context context) {
        this.movieDetailView = movieDetailView;
        this.context = context;
    }

    @MovieDetailFragmentScope
    @Provides
    public MovieDetailContracts.MovieDetailView provideMovieDetailView() {
        return movieDetailView;
    }

    @MovieDetailFragmentScope
    @Provides
    public MovieDetailPresenter provideMovieDetailPersenter(APIServices.TrailersService trailersService, APIServices.ReviewsService reviewsService) {
        return new MovieDetailPresenter(provideMovieDetailView(), provideMovieDetailInteractor(trailersService, reviewsService), context);
    }

    @MovieDetailFragmentScope
    @Provides
    public MovieDetailInteractor provideMovieDetailInteractor(APIServices.TrailersService trailersService, APIServices.ReviewsService reviewsService) {
        return new MovieDetailInteractor(trailersService, reviewsService);
    }

    @MovieDetailFragmentScope
    @Provides
    public MovieDetailContracts.MovieDetailPersenter provideMovieDetailPersenterInterface(APIServices.TrailersService trailersService, APIServices.ReviewsService reviewsService) {
        return provideMovieDetailPersenter(trailersService, reviewsService);
    }

    @MovieDetailFragmentScope
    @Provides
    Context provideContext() {
        return context;
    }
}
