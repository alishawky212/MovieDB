package com.example.ali.moviedb.Views;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ali.moviedb.Adapters.PosterAdapter;
import com.example.ali.moviedb.Contracts.OnPosterSelectedListener;
import com.example.ali.moviedb.Contracts.PosterFragmentMVP;
import com.example.ali.moviedb.DI.Components.DaggerPosterFragmentComponent;
import com.example.ali.moviedb.DI.Modules.PosterFragmentModule;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.MyApplication;
import com.example.ali.moviedb.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ali on 2/4/2018.
 */

public class PosterFragment extends Fragment implements PosterFragmentMVP.View{

    @BindView(R.id.gridview_frag)
    GridView mGridView;

    @Inject
    PosterFragmentMVP.Presenter presenter;

    PosterAdapter adapter;


    private ArrayList<Movie> moviesssss;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerPosterFragmentComponent.builder()
                .aPPComponent(MyApplication.getInstance().getComponent())
                .posterFragmentModule(new PosterFragmentModule())
                .build().inject(this);

        presenter.setView(this);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.movie_overview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        adapter = new PosterAdapter(getActivity(), new ArrayList<Movie>());

        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable(getResources().getString(R.string.parcel_movie), movie);
                ((OnPosterSelectedListener) getActivity()).onPosterSelected(bundle);
            }
        });

        if (savedInstanceState != null) {
            moviesssss = savedInstanceState.getParcelableArrayList("movies");
            List<Movie> movieList = savedInstanceState.getParcelableArrayList("movies");
            adapter.updateData(movieList);
        } else {
            presenter.getMovies(getActivity().getString(R.string.APIKEY));
        }

    }

    public void updateSharedPreferance(String sortMethod) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getActivity().getString(R.string.pref_sort_method_key), sortMethod);
        editor.apply();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies",moviesssss);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.popl:
                updateSharedPreferance(getString(R.string.tmdb_sort_pop_desc));
                presenter.getMovies(getActivity().getString(R.string.APIKEY));
                return true;

            case R.id.rate:
                updateSharedPreferance(getString(R.string.tmdb_sort_top_desc));
                presenter.getMovies(getActivity().getString(R.string.APIKEY));
                return true;

            case R.id.favor:
                updateSharedPreferance(getString(R.string.favorite));
                presenter.getFavoriteMovies();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        adapter.updateData(movies);
        moviesssss = movies;
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean checkForInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public String getSortMethod() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.getString(getActivity().getString(R.string.pref_sort_method_key),
                getActivity().getString(R.string.tmdb_sort_pop_desc));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
