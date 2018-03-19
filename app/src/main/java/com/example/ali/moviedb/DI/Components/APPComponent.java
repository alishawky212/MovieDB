package com.example.ali.moviedb.DI.Components;

import com.example.ali.moviedb.DI.Modules.APIModule;
import com.example.ali.moviedb.DI.Modules.RoomModule;
import com.example.ali.moviedb.RoomDB.MovieDao;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by ali on 2/4/2018.
 */

@Component(modules = {RoomModule.class, APIModule.class})
@Singleton
public interface APPComponent {
//    PosterFragmentComponent plus(PosterFragmentModule posterFragmentModule);
//    MovieDetailComponent plus(MovieDetailFragmentModule movieDetailFragmentModule);

    Retrofit RETROFIT();

    MovieDao MOVIE_DAO();
}
