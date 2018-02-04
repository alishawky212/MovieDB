package com.example.ali.moviedb.DI.Components;

import com.example.ali.moviedb.DI.Modules.APIModule;
import com.example.ali.moviedb.DI.Modules.PosterFragmentModule;
import com.example.ali.moviedb.MyApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ali on 2/4/2018.
 */

@Component(modules = {APIModule.class})
@Singleton
public interface APPComponent {

    void inject(MyApplication app);
    PosterFragmentComponent plus(PosterFragmentModule posterFragmentModule);

}
