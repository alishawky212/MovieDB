package com.example.ali.moviedb.DI.Scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ali on 2/4/2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PosterFragmentScope {
}
