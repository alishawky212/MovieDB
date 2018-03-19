package com.example.ali.moviedb.DI.Modules;


import com.example.ali.moviedb.Contracts.APIServices;
import com.example.ali.moviedb.Contracts.PosterFragmentMVP;
import com.example.ali.moviedb.DI.Scopes.PosterFragmentScope;
import com.example.ali.moviedb.Interactors.PosterFragmentInteractor;
import com.example.ali.moviedb.Persenters.PosterFragmentPresenter;
import com.example.ali.moviedb.RoomDB.MovieDao;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ali on 2/4/2018.
 */

@PosterFragmentScope
@Module
public class PosterFragmentModule {


    @PosterFragmentScope
    @Provides
    public PosterFragmentPresenter providePresenter(PosterFragmentMVP.InterActor interActor) {
        return new PosterFragmentPresenter(interActor);
    }


    @PosterFragmentScope
    @Provides
    public PosterFragmentInteractor provideInteractor(APIServices.TMDbPopular tmDbPopular, APIServices.TMDbServiceTopRated tmDbServiceTopRated, MovieDao movieDao) {
        return new PosterFragmentInteractor(tmDbPopular, tmDbServiceTopRated, movieDao);
    }

    @PosterFragmentScope
    @Provides
    public PosterFragmentMVP.Presenter providePresenterInterface(APIServices.TMDbPopular tmDbPopular, APIServices.TMDbServiceTopRated tmDbServiceTopRated, MovieDao movieDao) {
        return providePresenter(provideInteractor(tmDbPopular, tmDbServiceTopRated, movieDao));
    }


    @Provides
    @PosterFragmentScope
    public APIServices.TMDbPopular provideTmDbPopular(Retrofit retrofit) {
        return retrofit.create(APIServices.TMDbPopular.class);
    }

    @Provides
    @PosterFragmentScope
    public APIServices.TMDbServiceTopRated provideTmDbServiceTopRated(Retrofit retrofit) {
        return retrofit.create(APIServices.TMDbServiceTopRated.class);
    }
}
