package com.example.ali.moviedb.Persenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.example.ali.moviedb.Contracts.PosterFragmentMVP;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.R;

import java.util.ArrayList;

/**
 * Created by ali on 2/4/2018.
 */

public class PosterFragmentPresenter implements PosterFragmentMVP.Presenter,PosterFragmentMVP.InterActor.OnLoadFinishedListener {

    private PosterFragmentMVP.View view;
    private PosterFragmentMVP.InterActor interActor;
    private Context context;

    public PosterFragmentPresenter(PosterFragmentMVP.View view, PosterFragmentMVP.InterActor interActor,Context context) {
        this.view = view;
        this.interActor = interActor;
        this.context = context;
    }

    @Override
    public void getMovies() {

        if (isNetworkAvailable())
        {
            switch (getSortMethod()) {
                case "vote_average.desc":
                    interActor.getAverageMovies(context.getString(R.string.APIKEY), this);
                    break;
                case "popularity.desc":
                    interActor.getPopularMovies(context.getString(R.string.APIKEY), this);
                    break;
            }
        }

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void updateSharedPreferance(String sortMethod) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.pref_sort_method_key), sortMethod);
        editor.apply();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private String getSortMethod() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(context.getString(R.string.pref_sort_method_key),
                context.getString(R.string.tmdb_sort_pop_desc));
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
