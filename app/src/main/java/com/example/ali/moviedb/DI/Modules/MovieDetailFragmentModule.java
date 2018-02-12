package com.example.ali.moviedb.DI.Modules;

import android.content.Context;

import com.example.ali.moviedb.Contracts.APIServices;
import com.example.ali.moviedb.Contracts.MovieDetailContracts;
import com.example.ali.moviedb.DI.Scopes.MovieDetailFragmentScope;
import com.example.ali.moviedb.Interactors.MovieDetailInteractor;
import com.example.ali.moviedb.Persenters.MovieDetailPresenter;
import com.example.ali.moviedb.RoomDB.MovieDao;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

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
    public MovieDetailPresenter provideMovieDetailPersenter(APIServices.TrailersService trailersService, APIServices.ReviewsService reviewsService, MovieDao movieDao) {
        return new MovieDetailPresenter(provideMovieDetailView(), provideMovieDetailInteractor(trailersService, reviewsService, movieDao), context);
    }

    @MovieDetailFragmentScope
    @Provides
    public MovieDetailInteractor provideMovieDetailInteractor(APIServices.TrailersService trailersService, APIServices.ReviewsService reviewsService, MovieDao movieDao) {
        return new MovieDetailInteractor(trailersService, reviewsService, movieDao);
    }

    @MovieDetailFragmentScope
    @Provides
    public MovieDetailContracts.MovieDetailPersenter provideMovieDetailPersenterInterface(APIServices.TrailersService trailersService, APIServices.ReviewsService reviewsService, MovieDao movieDao) {
        return provideMovieDetailPersenter(trailersService, reviewsService, movieDao);
    }

    @MovieDetailFragmentScope
    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    @MovieDetailFragmentScope
    public APIServices.TrailersService provideTrailersService(Retrofit retrofit) {
        return retrofit.create(APIServices.TrailersService.class);
    }

    @Provides
    @MovieDetailFragmentScope
    public APIServices.ReviewsService provideReviewsService(Retrofit retrofit) {
        return retrofit.create(APIServices.ReviewsService.class);
    }
}
