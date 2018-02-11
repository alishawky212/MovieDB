package com.example.ali.moviedb.DI.Components;

import com.example.ali.moviedb.DI.Modules.APIModule;
import com.example.ali.moviedb.DI.Modules.MovieDetailFragmentModule;
import com.example.ali.moviedb.DI.Modules.PosterFragmentModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ali on 2/4/2018.
 */

@Component(modules = {APIModule.class})
@Singleton
public interface APPComponent {
    PosterFragmentComponent plus(PosterFragmentModule posterFragmentModule);
    MovieDetailComponent plus(MovieDetailFragmentModule movieDetailFragmentModule);

}
