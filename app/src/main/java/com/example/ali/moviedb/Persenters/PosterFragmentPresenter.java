package com.example.ali.moviedb.Persenters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.ali.moviedb.Contracts.PosterFragmentMVP;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.R;

import java.util.ArrayList;

/**
 * Created by ali on 2/4/2018.
 */

public class PosterFragmentPresenter implements PosterFragmentMVP.Presenter,PosterFragmentMVP.InterActor.OnLoadFinishedListener {

    PosterFragmentMVP.View view;
    PosterFragmentMVP.InterActor interActor;
    Context context;

    public PosterFragmentPresenter(PosterFragmentMVP.View view, PosterFragmentMVP.InterActor interActor,Context context) {
        this.view = view;
        this.interActor = interActor;
        this.context = context;
    }

    @Override
    public void getMovies() {

        if (isNetworkAvailable())
        {
            interActor.getPopularMovies(context.getString(R.string.APIKEY),this);
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onSuccess(ArrayList<Movie> movies) {

        view.showMovies(movies);
    }

    @Override
    public void onError() {
        view.showError();
    }
}
