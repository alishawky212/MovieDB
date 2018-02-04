package com.example.ali.moviedb.DI.Components;

import com.example.ali.moviedb.DI.Modules.PosterFragmentModule;
import com.example.ali.moviedb.DI.Scopes.PosterFragmentScope;
import com.example.ali.moviedb.Views.PosterFragment;

import dagger.Subcomponent;

/**
 * Created by ali on 2/4/2018.
 */

@PosterFragmentScope
@Subcomponent(modules = PosterFragmentModule.class)
public interface PosterFragmentComponent {

    void inject(PosterFragment posterFragment);
}
