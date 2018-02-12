package com.example.ali.moviedb.DI.Modules;

import android.content.Context;

import com.example.ali.moviedb.Adapters.PosterAdapter;
import com.example.ali.moviedb.Contracts.APIServices;
import com.example.ali.moviedb.Contracts.PosterFragmentMVP;
import com.example.ali.moviedb.DI.Scopes.PosterFragmentScope;
import com.example.ali.moviedb.Interactors.PosterFragmentInteractor;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.Persenters.PosterFragmentPresenter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ali on 2/4/2018.
 */

@PosterFragmentScope
@Module
public class PosterFragmentModule {

    PosterFragmentMVP.View view;
    Context mContext;

    public PosterFragmentModule(PosterFragmentMVP.View view,Context mContext) {
        this.view = view;
        this.mContext = mContext;
    }

    @PosterFragmentScope
    @Provides
    public PosterFragmentPresenter providePresenter(PosterFragmentMVP.View view , PosterFragmentMVP.InterActor interActor){
        return new PosterFragmentPresenter(view,interActor,mContext);
    }

    @PosterFragmentScope
    @Provides
    public PosterFragmentMVP.View provideView(){
        return view;
    }

    @PosterFragmentScope
    @Provides
    public PosterFragmentInteractor provideInteractor(APIServices.TMDbPopular tmDbPopular,APIServices.TMDbServiceTopRated tmDbServiceTopRated){
        return new PosterFragmentInteractor(tmDbPopular,tmDbServiceTopRated);
    }

    @PosterFragmentScope
    @Provides
    public PosterFragmentMVP.Presenter providePresenterInterface(APIServices.TMDbPopular tmDbPopular,APIServices.TMDbServiceTopRated tmDbServiceTopRated){
        return providePresenter(view,provideInteractor(tmDbPopular,tmDbServiceTopRated));
    }

    @Provides
    @PosterFragmentScope
    Context provideContext() {
        return mContext;
    }

    @Provides
    @PosterFragmentScope
    public PosterAdapter provideAdapter(){
        return new PosterAdapter(mContext,new ArrayList<Movie>());
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
