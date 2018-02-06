package com.example.ali.moviedb.DI.Components;

import com.example.ali.moviedb.DI.Modules.MovieDetailFragmentModule;
import com.example.ali.moviedb.DI.Scopes.MovieDetailFragmentScope;
import com.example.ali.moviedb.Views.MovieDetailFragment;

import dagger.Subcomponent;

/**
 * Created by ali on 2/6/2018.
 */

@MovieDetailFragmentScope
@Subcomponent(modules = {MovieDetailFragmentModule.class})
public interface MovieDetailComponent {

    void inject(MovieDetailFragment movieDetailFragment);
}
